package jp.noxi.ingress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

import jp.noxi.ingress.data.entity.Portal;
import jp.noxi.persistence.geometry.Point;

/**
 * {@link jp.noxi.ingress.data.entity.Portal}のレポジトリ
 *
 * @author noxi
 */
public interface PortalRepository extends JpaRepository<Portal, UUID> {

    /**
     * ID一覧を取得します。
     *
     * @return ID一覧
     */
    @Nonnull
    @Query("SELECT id FROM Portal")
    List<UUID> getIds();

    /**
     * ポータル一覧をポリゴンから取得します。
     */
    @Nonnull
    @Query(value = "SELECT p.id, p.title, p.image, p.location FROM portal p WHERE ST_CONTAINS(?, p.location)",
            nativeQuery = true)
    List<Portal> findPortalByLocation(byte[] polygon);

    /**
     * {@link jp.noxi.ingress.data.entity.Portal#id}でカウントします。
     *
     * @param id ユニークID
     * @return カウント結果
     */
    long countIdById(UUID id);

    long countIdByLocation(Point pint);
}
