package jp.noxi.ingress.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jp.noxi.persistence.UUIDToBytesConverter;
import jp.noxi.persistence.geometry.Point;
import jp.noxi.persistence.geometry.PointToBytesConverter;
import jp.noxi.persistence.time.LocalDateTimeToTimestampConverter;

/**
 * ポータル情報
 *
 * @author noxi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor()

@Entity
@Table(name = "portal")
public class Portal implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ユニークID
     */
    @Id
    @Convert(converter = UUIDToBytesConverter.class)
    private UUID id;

    /**
     * ポータル名
     */
    @Column(name = "title", length = 255, nullable = false)
    private String title;

    /**
     * ポータル画像URL
     */
    @Column(name = "image", length = 255, nullable = false)
    private String image;

    /**
     * ポータルロケーション
     */
    @Column(name = "location", unique = true, nullable = false)
    @Convert(converter = PointToBytesConverter.class)
    private Point location;

    /**
     * 最終更新日時
     */
    @JsonIgnore
    @Column(name = "last_update", nullable = false)
    @Convert(converter = LocalDateTimeToTimestampConverter.class)
    private LocalDateTime lastUpdate;

    /**
     * 最終更新日時以外の値を比較します。
     */
    public boolean equalsIgnoreLastUpdate(@Nullable Portal other) {
        if (other == null)
            return false;

        if (id != null ? !id.equals(other.id) : other.id != null) return false;
        if (image != null ? !image.equals(other.image) : other.image != null) return false;
        if (location != null ? !location.equals(other.location) : other.location != null) return false;
        if (title != null ? !title.equals(other.title) : other.title != null) return false;

        return true;
    }

}
