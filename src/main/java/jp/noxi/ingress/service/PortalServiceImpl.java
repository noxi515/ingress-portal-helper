package jp.noxi.ingress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.noxi.ingress.data.entity.Portal;
import jp.noxi.ingress.data.json.JsonGameEntities;
import jp.noxi.ingress.repository.PortalRepository;
import jp.noxi.persistence.geometry.Point;
import jp.noxi.persistence.geometry.Polygon;
import jp.noxi.persistence.geometry.PolygonToBytesConverter;
import jp.noxi.util.DateTimeUtils;
import jp.noxi.util.UUIDUtils;

/**
 * @author noxi
 */
@Service
public class PortalServiceImpl implements PortalService {

    private static final Logger LOG = LoggerFactory.getLogger(PortalService.class);

    @Autowired
    PortalRepository repository;

    /**
     * IDのキャッシュ
     */
    final ConcurrentMap<UUID, UUID> ids = new ConcurrentHashMap<>();
    final PolygonToBytesConverter converter = new PolygonToBytesConverter();

    /**
     * 初期化処理。ID一覧のキャッシュなど
     */
    @PostConstruct
    void initialize() {
        LOG.trace("initialize begin");
        repository.getIds().stream().forEach(i -> ids.put(i, i));
        LOG.trace("initialize end. IDs: {}", ids.size());
    }

    @Override
    public void addJson(@Nonnull JsonGameEntities entities) {
        if (entities.portals == null || entities.portals.isEmpty()) {
            LOG.warn("entities has no portal data.");
            return;
        }

        LocalDateTime now = DateTimeUtils.getCurrentDateTime();
        entities.portals.parallelStream()
                        .map(p -> new Portal(UUIDUtils.fromNonSeparatedString(p.guid.substring(0, 32)),
                                             p.title, p.image, new Point(p.lat / 1000000d, p.lng / 1000000d), now))
                        .forEach(p -> {
                            UUID id = p.getId();
                            if (ids.putIfAbsent(id, id) != null) {
                                // Already exists
                                Portal exists = repository.findOne(id);
                                if (exists != null)
                                    return;
                            }

                            try {
                                repository.save(p);
                            } catch (Exception e) {
                                LOG.warn(null, e);
                            }
                        });
    }

    @Nonnull
    @Override
    public Optional<Portal> findOne(@Nonnull UUID id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Nonnull
    @Override
    public List<Portal> findByPolygon(@Nonnull Polygon polygon) {
        return repository.findPortalByLocation(converter.convertToDatabaseColumn(polygon));
    }
}
