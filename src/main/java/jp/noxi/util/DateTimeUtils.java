package jp.noxi.util;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.function.Supplier;

import com.google.common.annotations.VisibleForTesting;

/**
 * @author noxi
 */
public final class DateTimeUtils {

    private static final Supplier<LocalDateTime> DEFAULT_CURRENT_DATE_TIME = LocalDateTime::now;

    private static Supplier<LocalDateTime> currentDateTime = DEFAULT_CURRENT_DATE_TIME;

    /**
     * 現在日時を取得します。
     *
     * @return 現在日時
     */
    public static LocalDateTime getCurrentDateTime() {
        return currentDateTime.get();
    }

    /**
     * 現在日時の取得方法を設定します。
     */
    @VisibleForTesting
    public static void setCurrentDateTime(@Nonnull Supplier<LocalDateTime> currentDateTime) {
        DateTimeUtils.currentDateTime = currentDateTime;
    }

    /**
     * 現在日時の取得方法をデフォルトに設定します。
     */
    @VisibleForTesting
    public static void resetCurrentDateTime() {
        DateTimeUtils.currentDateTime = DEFAULT_CURRENT_DATE_TIME;
    }

    private DateTimeUtils() {
    }
}
