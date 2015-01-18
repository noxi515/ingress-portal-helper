package jp.noxi.ingress.data.kml;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

import jp.noxi.ingress.data.entity.Portal;

/**
 * ポータル一覧のKML出力プロパティー
 *
 * @author noxi
 */
@Data
@AllArgsConstructor
public class PortalsProperty implements Serializable {

    private static final long serialVersionUID = 1L;

    private String area;

    private String title;

    private String areaStyle;

    private String portalStyle;

    private List<Portal> portals;

}
