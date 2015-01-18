package jp.noxi.ingress.service;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jp.noxi.ingress.data.entity.Portal;
import jp.noxi.ingress.data.json.JsonGameEntities;
import jp.noxi.persistence.geometry.Polygon;

/**
 * ポータル関連
 *
 * @author noxi
 */
public interface PortalService {

    /**
     * JSONでPOSTされたポータル情報を保存します。
     *
     * @param entities ポータル情報
     */
    void addJson(@Nonnull JsonGameEntities entities);

    /**
     * IDに対応するポータル情報を取得します。
     *
     * @param id ID
     * @return ポータル情報
     */
    @Nonnull
    Optional<Portal> findOne(@Nonnull UUID id);

    /**
     * ポリゴンで指定した範囲のポータル一覧を取得します。
     *
     * @param polygon 範囲を指定するポリゴン
     * @return ポリゴン範囲に含まれるポータル一覧
     */
    @Nonnull
    List<Portal> findByPolygon(@Nonnull Polygon polygon);

}
