package jp.noxi.persistence;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * {@link java.util.UUID} &lt;=&gt; {@code byte[]}
 *
 * @author noxi
 */
@Converter(autoApply = true)
public class UUIDToBytesConverter implements AttributeConverter<UUID, byte[]> {

    @Override
    public byte[] convertToDatabaseColumn(UUID attribute) {
        if (attribute == null)
            return null;

        return ByteBuffer.allocate(16)
                         .putLong(attribute.getMostSignificantBits())
                         .putLong(attribute.getLeastSignificantBits())
                         .array();
    }

    @Override
    public UUID convertToEntityAttribute(byte[] dbData) {
        if (dbData == null)
            return null;

        ByteBuffer buffer = ByteBuffer.wrap(dbData);
        long most = buffer.getLong();
        long least = buffer.getLong();
        return new UUID(most, least);
    }

}
