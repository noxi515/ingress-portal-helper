package jp.noxi.persistence.geometry;

import javax.annotation.Nonnull;
import java.io.Serializable;

import lombok.Value;

/**
 * Geometry Point class
 *
 * @author noxi
 */
@Value
public final class Point implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * latitude
     */
    protected final double latitude;

    /**
     * longitude
     */
    protected final double longitude;

    @Nonnull
    public Point setLatitude(double latitude) {
        return new Point(latitude, longitude);
    }

    @Nonnull
    public Point setLongitude(double longitude) {
        return new Point(latitude, longitude);
    }

    public static final Point INVALID = new Point(Double.NaN, Double.NaN);
}
