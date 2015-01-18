package jp.noxi.ingress.http.converter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Strings;
import com.google.common.escape.Escaper;
import com.google.common.xml.XmlEscapers;

import jp.noxi.ingress.data.kml.PortalsProperty;
import jp.noxi.ingress.http.MediaTypes;

/**
 * @author noxi
 */
@Component
public class PortalKmlHttpMessageConverter implements HttpMessageConverter<PortalsProperty> {

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return clazz == PortalsProperty.class && mediaType == MediaTypes.APPLICATION_GOOGLE_EARTH_KML;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Arrays.asList(MediaTypes.APPLICATION_GOOGLE_EARTH_KML);
    }

    @Override
    public PortalsProperty read(Class<? extends PortalsProperty> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write(PortalsProperty property, MediaType contentType, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {

        Escaper escaper = XmlEscapers.xmlContentEscaper();
        String escapedTitle = escaper.escape(property.getTitle());
        String escapedAreaStyle = escaper.escape(Strings.nullToEmpty(property.getAreaStyle()));
        String escapedPortalStyle = escaper.escape(Strings.nullToEmpty(property.getPortalStyle()));

        StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\">")
          .append("<Document><name>").append(escapedTitle).append("</name>");

        sb.append("<Placemark><styleUrl>#").append(escapedAreaStyle).append("</styleUrl>")
          .append("<name>").append(escapedTitle).append("</name>")
          .append("<ExtendedData></ExtendedData>")
          .append("<Polygon><outerBoundaryIs><LinearRing>")
          .append("<tessellate>1</tessellate>")
          .append("<coordinates>").append(property.getArea()).append("</coordinates>")
          .append("</LinearRing></outerBoundaryIs></Polygon></Placemark>");

        property.getPortals().forEach(
                p -> sb.append("<Placemark><styleUrl>#").append(escapedPortalStyle).append("</styleUrl>")
                       .append("<name>").append(escaper.escape(p.getTitle())).append("</name>")
                       .append("<ExtendedData></ExtendedData>")
                       .append("<Point><coordinates>")
                       .append(p.getLocation().getLongitude()).append(",")
                       .append(p.getLocation().getLatitude()).append(",0.0")
                       .append("</coordinates></Point>")
                       .append("</Placemark>"));

        sb.append("<Style id='icon-503-3F5BA9'>" +
                          "<IconStyle>" +
                          "<color>ffA95B3F</color>" +
                          "<scale>1.1</scale>" +
                          "<Icon>" +
                          "<href>http://www.gstatic.com/mapspro/images/stock/503-wht-blank_maps.png</href>" +
                          "</Icon>" +
                          "</IconStyle>" +
                          "</Style>" +
                          "<Style id='icon-503-DB4436'>" +
                          "<IconStyle>" +
                          "<color>ff3644DB</color>" +
                          "<scale>1.1</scale>" +
                          "<Icon>" +
                          "<href>http://www.gstatic.com/mapspro/images/stock/503-wht-blank_maps.png</href>" +
                          "</Icon>" +
                          "</IconStyle>" +
                          "</Style>" +
                          "<Style id='line-000000-1'>" +
                          "<LineStyle>" +
                          "<color>ff000000</color>" +
                          "<width>1</width>" +
                          "</LineStyle>" +
                          "</Style>" +
                          "<Style id='poly-009D57-1-107'>" +
                          "<LineStyle>" +
                          "<color>ff579D00</color>" +
                          "<width>1</width>" +
                          "</LineStyle>" +
                          "<PolyStyle>" +
                          "<color>6B579D00</color>" +
                          "<fill>1</fill>" +
                          "<outline>1</outline>" +
                          "</PolyStyle>" +
                          "</Style>" +
                          "<Style id='poly-009D57-1-117'>" +
                          "<LineStyle>" +
                          "<color>ff579D00</color>" +
                          "<width>1</width>" +
                          "</LineStyle>" +
                          "<PolyStyle>" +
                          "<color>75579D00</color>" +
                          "<fill>1</fill>" +
                          "<outline>1</outline>" +
                          "</PolyStyle>" +
                          "</Style>" +
                          "<Style id='poly-0BA9CC-1-76'>" +
                          "<LineStyle>" +
                          "<color>ffCCA90B</color>" +
                          "<width>1</width>" +
                          "</LineStyle>" +
                          "<PolyStyle>" +
                          "<color>4CCCA90B</color>" +
                          "<fill>1</fill>" +
                          "<outline>1</outline>" +
                          "</PolyStyle>" +
                          "</Style>" +
                          "<Style id='poly-795046-1-76'>" +
                          "<LineStyle>" +
                          "<color>ff465079</color>" +
                          "<width>1</width>" +
                          "</LineStyle>" +
                          "<PolyStyle>" +
                          "<color>4C465079</color>" +
                          "<fill>1</fill>" +
                          "<outline>1</outline>" +
                          "</PolyStyle>" +
                          "</Style>" +
                          "<Style id='poly-795046-1-96'>" +
                          "<LineStyle>" +
                          "<color>ff465079</color>" +
                          "<width>1</width>" +
                          "</LineStyle>" +
                          "<PolyStyle>" +
                          "<color>60465079</color>" +
                          "<fill>1</fill>" +
                          "<outline>1</outline>" +
                          "</PolyStyle>" +
                          "</Style>" +
                          "<Style id='poly-DB4436-1-76'>" +
                          "<LineStyle>" +
                          "<color>ff3644DB</color>" +
                          "<width>1</width>" +
                          "</LineStyle>" +
                          "<PolyStyle>" +
                          "<color>4C3644DB</color>" +
                          "<fill>1</fill>" +
                          "<outline>1</outline>" +
                          "</PolyStyle>" +
                          "</Style>" +
                          "<Style id='poly-F4B400-1-76'>" +
                          "<LineStyle>" +
                          "<color>ff00B4F4</color>" +
                          "<width>1</width>" +
                          "</LineStyle>" +
                          "<PolyStyle>" +
                          "<color>4C00B4F4</color>" +
                          "<fill>1</fill>" +
                          "<outline>1</outline>" +
                          "</PolyStyle>" +
                          "</Style>" +
                          "<Style id='poly-F4EB37-1-76'>" +
                          "<LineStyle>" +
                          "<color>ff37EBF4</color>" +
                          "<width>1</width>" +
                          "</LineStyle>" +
                          "<PolyStyle>" +
                          "<color>4C37EBF4</color>" +
                          "<fill>1</fill>" +
                          "<outline>1</outline>" +
                          "</PolyStyle>" +
                          "</Style>");
        sb.append("</Document></kml>");

        HttpHeaders headers = outputMessage.getHeaders();
        headers.setContentType(MediaTypes.APPLICATION_GOOGLE_EARTH_KML);
        headers.setContentLength(sb.length());

        Writer writer = new OutputStreamWriter(outputMessage.getBody());
        writer.write(sb.toString());
        writer.flush();
    }

}
