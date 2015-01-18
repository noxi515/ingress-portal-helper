package jp.noxi.ingress.web.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import jp.noxi.ingress.http.MediaTypes;

/**
 * Web設定
 *
 * @author noxi
 */
@Component
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.mediaType("kml", MediaTypes.APPLICATION_GOOGLE_EARTH_KML);
    }

}
