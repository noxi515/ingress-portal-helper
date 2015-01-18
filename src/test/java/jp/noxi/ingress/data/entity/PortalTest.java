package jp.noxi.ingress.data.entity;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.Test;

import jp.noxi.persistence.geometry.Point;

public class PortalTest {

    @Test
    public void testEqualsIgnoreLastUpdate() throws Exception {
        Portal a = new Portal(UUID.randomUUID(), "title", "image", new Point(1, 1), LocalDateTime.now());
        Portal b = new Portal(a.getId(), a.getTitle(), a.getImage(), a.getLocation(), a.getLastUpdate());
        Portal c = new Portal(a.getId(), a.getTitle(), a.getImage(), a.getLocation(), LocalDateTime.MAX);
        Portal d = new Portal(a.getId(), "title1", a.getImage(), a.getLocation(), a.getLastUpdate());

        assertThat(a.equalsIgnoreLastUpdate(b), is(true));
        assertThat(a.equalsIgnoreLastUpdate(c), is(true));
        assertThat(a.equalsIgnoreLastUpdate(d), is(false));
    }
}