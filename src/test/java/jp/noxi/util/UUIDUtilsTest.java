package jp.noxi.util;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.UUID;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author noxi
 */
public class UUIDUtilsTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testFromNonSeparatedString() throws Exception {
        String origin = "1234567890abcdef1234567890abcdef";
        UUID result = UUIDUtils.fromNonSeparatedString(origin);

        assertThat(result, is(UUID.fromString("12345678-90ab-cdef-1234-567890abcdef")));
    }

    @Test
    public void testFromNonSeparatedString_length_long() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("UUID string length invalid");

        String origin = "1234567890abcdef1234567890abcdef1";
        UUIDUtils.fromNonSeparatedString(origin);

        fail();
    }

    @Test
    public void testFromNonSeparatedString_length_short() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("UUID string length invalid");

        String origin = "1234567890abcdef1234567890abcde";
        UUIDUtils.fromNonSeparatedString(origin);

        fail();
    }

}