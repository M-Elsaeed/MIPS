package General_Functions;
public class miscellaneous {

    /**
     * Extends a Binary String preserving its sign.
     * @param s This is the Binary String you want to extend. NOTE:It must be
     * bigger than the original String.
     * @param len This is the length you want to extend to String to.
     * @return The string extended preserving the sign.
     */
    public static String extendSigned(String s, int len) {
        if (len > s.length()) {
            String res = "";
            char MSBc = s.charAt(0);
            String MSB = MSBc + "";
            for (int i = 0; i < len - s.length(); i++) {
                res += MSB;
            }
            res += s;
            return res;
        } else {
            return s;
        }
    }

    /**
     * Extends a Binary String ignoring its sign.
     * @param s This is the Binary String you want to extend. NOTE:It must be
     * bigger than the original String.
     * @param len This is the length you want to extend to String to.
     * @return The string extended NOT preserving the sign.
     */
    public static String extend(String s, int len) {
        if (len > s.length()) {
            String res = "";
            for (int i = 0; i < len - s.length(); i++) {
                res += "0";
            }
            res += s;
            return res;
        } else {
            return s;
        }
    }

    
    
}
