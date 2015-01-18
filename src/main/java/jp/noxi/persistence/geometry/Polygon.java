package jp.noxi.persistence.geometry;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.base.Strings;

/**
 * Geometry Polygon class
 *
 * @author noxi
 */
public class Polygon extends ArrayList<Point> {

    private static final long serialVersionUID = 1L;


    /**
     * KMLのcoordinationタグ情報からポリゴンを取得します。
     *
     * @param coordination KMLのcoordinationタグの中身
     * @return ポリゴン情報
     */
    @Nonnull
    public static Polygon valueOf(@Nonnull String coordination) {
        if (Strings.isNullOrEmpty(coordination))
            return new Polygon();

        return new Polygon(Arrays.stream(coordination.split(" "))
                                 .map(s -> s.split(","))
                                 .map(a -> new Point(Double.parseDouble(a[1]), Double.parseDouble(a[0])))
                                 .collect(Collectors.toList()));
    }

    /**
     * KMLのcoordinationタグ情報からポリゴンを取得します。
     *
     * @param coordination KMLのcoordinationタグの中身
     * @return 正常にパース出来た場合ポリゴン情報、それ以外は空
     */
    @Nonnull
    public static Optional<Polygon> tryParse(@Nonnull String coordination) {
        try {
            Polygon polygon = valueOf(coordination);
            return Optional.ofNullable(polygon.isEmpty() ? null : polygon);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Polygon() {
    }

    public Polygon(int initialCapacity) {
        super(initialCapacity);
    }

    public Polygon(@Nonnull Collection<? extends Point> c) {
        super(c);
    }
}
