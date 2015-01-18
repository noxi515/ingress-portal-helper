package jp.noxi.ingress.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.noxi.ingress.data.entity.Portal;
import jp.noxi.ingress.data.json.JsonGameEntities;
import jp.noxi.ingress.data.kml.PortalsProperty;
import jp.noxi.ingress.http.MediaTypes;
import jp.noxi.ingress.service.PortalService;
import jp.noxi.persistence.geometry.Polygon;

/**
 * ポータルAPI
 *
 * @author noxi
 */
@RestController
@RequestMapping("/api/portal")
public class PortalController {

    private static final Logger LOG = LoggerFactory.getLogger(PortalController.class);

    @Autowired
    private PortalService service;

    /**
     * ポータル情報を追加します。
     *
     * @param data ポータル情報
     */
    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addPortals(@RequestBody JsonGameEntities data) {
        service.addJson(data);
    }

    /**
     * ポリゴンで指定されたエリアに存在するポータル一覧を取得します。
     *
     * @param area 指定エリア
     * @return ポータル一覧。ポリゴンのパースに失敗した場合は空のリスト。
     */
    @Nonnull
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Portal> findPortalsByPolygon(@RequestParam String area) {
        return Polygon.tryParse(area).map(service::findByPolygon).orElse(new ArrayList<>());
    }

    /**
     * ポリゴンで指定されたエリアに存在するポータル一覧を取得します。
     *
     * @param area 指定エリア
     * @return ポータル一覧。ポリゴンのパースに失敗した場合は空のリスト。
     */
    @Nonnull
    @RequestMapping(method = RequestMethod.GET, produces = MediaTypes.APPLICATION_GOOGLE_EARTH_KML_VALUE)
    public PortalsProperty findPortalsByPolygonKml(@RequestParam String area,
                                                   @RequestParam String title,
                                                   @RequestParam(defaultValue = "") String areaStyle,
                                                   @RequestParam(defaultValue = "") String portalStyle) {
        List<Portal> portals = findPortalsByPolygon(area);
        return new PortalsProperty(area, title, areaStyle, portalStyle, portals);
    }

}
