package others.java.leetcode.Conversion;

public class IntegerToEnglishWords {
    String[] map1 = new String [] {"", " One", " Two", " Three", " Four", " Five",
            " Six", " Seven", " Eight", " Nine", " Ten", " Eleven", " Twelve",
            " Thirteen", " Fourteen", " Fifteen", " Sixteen", " Seventeen",
            " Eighteen", " Nineteen" };

    String[] map2 = new String[] {"", "", " Twenty", " Thirty", " Forty", " Fifty", " Sixty",
            " Seventy", " Eighty", " Ninety" };

    String[] map3 = new String[] {"", " Thousand", " Million", " Billion" };
    final String HUNDRED = " Hundred";

    public String threeDigitToWords(int num) {
        String res = "";
        // 1. add hundreds
        if (num > 99)
            res = map1[num/100] + HUNDRED;
        // add digit, teen and ty
        num %= 100;
        if (num < 20)
            res += map1[num];
        else
            res += map2[num/10] + map1[num%10];
        return res;
    }

    public String numberToWords(int num) {
        if (num == 0) return "Zero";

        String res = "";
        int i = 0;
        while (num != 0) {
            if (num % 1000 != 0) {
                res = threeDigitToWords(num % 1000) + map3[i] + res;
            }
            i++;
            num /= 1000;
        }

        return res.trim();
    }

}
