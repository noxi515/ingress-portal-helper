package jp.noxi.persistence.geometry;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Test;

@SuppressWarnings("all")
public class PolygonTest {

    //<editor-fold desc="valueOf">

    @Test
    public void testValueOf_null() throws Exception {
        Polygon polygon = Polygon.valueOf(null);

        assertThat(polygon, is(empty()));
    }

    @Test
    public void testValueOf_empty() throws Exception {
        Polygon polygon = Polygon.valueOf("");

        assertThat(polygon, is(empty()));
    }

    @Test
    public void testValueOf() throws Exception {
        String coordination = "139.76051350000003,35.263702300000006,0.0" +
                " 139.6920143,35.291031,0.0" +
                " 139.6719127,35.2776357,0.0" +
                " 139.6921984,35.26076599999999,0.0" +
                " 139.7496983,35.2470206,0.0" +
                " 139.76051350000003,35.263702300000006,0.0";

        Polygon polygon = Polygon.valueOf(coordination);

        assertThat(polygon, is(contains(
                new Point(35.263702300000006, 139.76051350000003),
                new Point(35.291031, 139.6920143),
                new Point(35.2776357, 139.6719127),
                new Point(35.26076599999999, 139.6921984),
                new Point(35.2470206, 139.7496983),
                new Point(35.263702300000006, 139.76051350000003))));
    }

    //</editor-fold>

    //<editor-fold desc="tryParse">

    @Test
    public void testTryParse_null() throws Exception {
        Optional<Polygon> polygon = Polygon.tryParse(null);

        assertThat(polygon.isPresent(), is(false));
    }

    @Test
    public void testTryParse_empty() throws Exception {
        Optional<Polygon> polygon = Polygon.tryParse("");

        assertThat(polygon.isPresent(), is(false));
    }

    @Test
    public void testTryParse() throws Exception {
        String coordination = "139.76051350000003,35.263702300000006,0.0" +
                " 139.6920143,35.291031,0.0" +
                " 139.6719127,35.2776357,0.0" +
                " 139.6921984,35.26076599999999,0.0" +
                " 139.7496983,35.2470206,0.0" +
                " 139.76051350000003,35.263702300000006,0.0";

        Optional<Polygon> polygon = Polygon.tryParse(coordination);

        assertThat(polygon.get(), is(contains(
                new Point(35.263702300000006, 139.76051350000003),
                new Point(35.291031, 139.6920143),
                new Point(35.2776357, 139.6719127),
                new Point(35.26076599999999, 139.6921984),
                new Point(35.2470206, 139.7496983),
                new Point(35.263702300000006, 139.76051350000003))));
    }

    @Test
    public void testTryParse_error() throws Exception {
        Optional<Polygon> polygon = Polygon.tryParse("hoge");

        assertThat(polygon.isPresent(), is(false));
    }

    //</editor-fold>

}