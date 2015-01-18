package jp.noxi.ingress.http;

import org.springframework.http.MediaType;

/**
 * @author noxi
 */
public final class MediaTypes {

    public static final String APPLICATION_GOOGLE_EARTH_KML_VALUE = "application/vnd.google-earth.kml";
    public static final MediaType APPLICATION_GOOGLE_EARTH_KML = MediaType.valueOf(APPLICATION_GOOGLE_EARTH_KML_VALUE);

    private MediaTypes() {
    }
}
