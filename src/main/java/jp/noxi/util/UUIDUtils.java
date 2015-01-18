package jp.noxi.util;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * {@link java.util.UUID}ユーティリティ
 *
 * @author noxi
 */
public final class UUIDUtils {

    /**
     * &quot;85435d499aea47099b93bd17072f2458&quot;形式の文字列から{@link java.util.UUID}を取得します。
     */
    @Nonnull
    public static UUID fromNonSeparatedString(@Nonnull String str) {
        if (str.length() != 32)
            throw new IllegalArgumentException("UUID string length invalid");

        str = str.toUpperCase();
        long mostSigBits = Long.decode("0x" + str.substring(0, 8));
        mostSigBits <<= 16;
        mostSigBits |= Long.decode("0x" + str.substring(8, 12));
        mostSigBits <<= 16;
        mostSigBits |= Long.decode("0x" + str.substring(12, 16));

        long leastSigBits = Long.decode("0x" + str.substring(16, 20));
        leastSigBits <<= 48;
        leastSigBits |= Long.decode("0x" + str.substring(20, 32));

        return new UUID(mostSigBits, leastSigBits);
    }

    private UUIDUtils() {
    }
}
