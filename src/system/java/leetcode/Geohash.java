package system.java.leetcode;

import java.util.BitSet;
import java.util.HashMap;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by khuang on 10/13/15.
 */
public class Geohash {

    private static int numbits = 6 * 5; // 32based value has 5bits per number
    final static char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };
    final static HashMap<Character, Integer> lookup = new HashMap<>();
    static {
        int i = 0;
        for (char c : digits)
            lookup.put(c, i++);
    }

    public static String encode(double latitude, double longitude) {
        BitSet latbits = getBits(latitude, -90, 90);
        BitSet lonbits = getBits(longitude, -180, 180);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbits; i++) {
            sb.append(lonbits.get(i) ? '1' : '0');
            sb.append(latbits.get(i) ? '1' : '0');
        }

        return base32(Long.parseLong(sb.toString(), 2));
    }

    private static BitSet getBits(double val, double floor, double ceiling) {
        BitSet buffer = new BitSet(numbits);
        for (int i = 0; i < numbits; i++) {
            double mid = (floor + ceiling) / 2;
            if (val >= mid) {
                buffer.set(i);
                floor = mid;
            } else {
                ceiling = mid;
            }
        }
        return buffer;
    }

    public static String base32(long val) {
        StringBuilder sb = new StringBuilder();
        boolean isNegative = (val < 0);

        if (isNegative)
            val = -val;

        while (val > 0) {
            int digit = (int)(val % 32);
            sb.append(digits[digit]);
            val /= 32;
        }
        if (isNegative) sb.append('-');
        return sb.reverse().toString();
    }

    public static double[] decode(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            int i = lookup.get(c) + 32; // 2 -> 100010, we plus 32 to keep the 0s in the front
            sb.append(Integer.toString(i, 2).substring(1));
        }

        BitSet lonset = new BitSet();
        BitSet latset = new BitSet();
        // for even bits
        int j = 0;
        for (int i = 0; i < numbits*2; i += 2) {
            if (sb.charAt(i) == '1')
                lonset.set(j, true);
            j++;
        }

        j = 0;
        for (int i = 1; i < numbits*2; i += 2) {
            if (sb.charAt(i) == '1')
                latset.set(j, true);
            j++;
        }
        double lon = decodeBitToDouble(lonset, -180, 180);
        double lat = decodeBitToDouble(latset, -90, 90);
        return new double[] {lat, lon};
    }

    static double decodeBitToDouble(BitSet bs, double l, double h) {
        double mid = 0;
        for (int i = 0; i < numbits; i++) {
            mid = (l + h) / 2;
            if (bs.get(i))
                l = mid;
            else
                h = mid;
        }
        return mid;

    }

    @Test
    public void testEncode() {
        String s = Geohash.encode(39.92324, 116.3906);
        System.out.println(s);
        assertEquals(s, "wx4g0ec19x3d");

        System.out.println(Integer.toString(34, 2));
    }

    @Test
    public void testDecode() {
        String s = Geohash.encode(39.92324, 116.3906);
        double[] res = Geohash.decode(s);
        System.out.println(res[0]+" "+res[1]);
    }
}
