package jp.noxi.ingress.data.json;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * ポータル情報
 *
 * @author noxi
 */
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JsonPortal implements Serializable {

    /**
     * ユニークID
     */
    public String guid;

    /**
     * ポータル名
     */
    public String title;

    /**
     * ポータル画像
     */
    public String image;

    /**
     * 緯度
     */
    public int lat;

    /**
     * 経度
     */
    public int lng;

}
