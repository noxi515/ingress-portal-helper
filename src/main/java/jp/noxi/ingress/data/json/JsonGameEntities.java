package jp.noxi.ingress.data.json;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Intel情報
 *
 * @author noxi
 */
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JsonGameEntities implements Serializable {

    public List<JsonPortal> portals;

}
