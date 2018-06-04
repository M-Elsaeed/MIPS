package General_Functions;

public class RegInfo {
    /**
     * Returns the name of a register as a String Without the Dollar sign
     * @param i is the index (Number) of the register you want the name of.
     * @return The Name of the register is returned as a String (Without the Dollar sign)
     */
    public static String getRegName(int i) {
        switch (i) {

            case 0:
                return "zero";
            case 1:
                return "at";
            case 2:
                return "v0";
            case 3:
                return "v1";
            case 4:
                return "a0";
            case 5:
                return "a1";
            case 6:
                return "a2";
            case 7:
                return "a3";
            case 8:
                return "t0";
            case 9:
                return "t1";
            case 10:
                return "t2";
            case 11:
                return "t3";
            case 12:
                return "t4";
            case 13:
                return "t5";
            case 14:
                return "t6";
            case 15:
                return "t7";
            case 16:
                return "s0";
            case 17:
                return "s1";
            case 18:
                return "s2";
            case 19:
                return "s3";
            case 20:
                return "s4";
            case 21:
                return "s5";
            case 22:
                return "s6";
            case 23:
                return "s7";
            case 24:
                return "t8";
            case 25:
                return "t9";
            case 26:
                return "k0";
            case 27:
                return "k1";
            case 28:
                return "gp";
            case 29:
                return "sp";
            case 30:
                return "fp";
            case 31:
                return "ra";

        }
        return null;
    }

    /**
     *
     * Returns the number(index) of a register given its name as a String
     * Without the Dollar sign.
     * @param name name of the register(String) WITHOUT THE DOLLAR SIGN
     * @return The index of the register as an int.
     */
    public static int getRegNum(String name) {
        //Register $zero written strictly as $00
        if (name.startsWith("00")) {
            return 0;
        } else if (name.equalsIgnoreCase("at")) {
            return 1;
        } else if (name.equalsIgnoreCase("v0")) {
            return 2;
        } else if (name.equalsIgnoreCase("v1")) {
            return 3;
        } else if (name.equalsIgnoreCase("a0")) {
            return 4;
        } else if (name.equalsIgnoreCase("a1")) {
            return 5;
        } else if (name.equalsIgnoreCase("a2")) {
            return 6;
        } else if (name.equalsIgnoreCase("a3")) {
            return 7;
        } else if (name.equalsIgnoreCase("t0")) {
            return 8;
        } else if (name.equalsIgnoreCase("t1")) {
            return 9;
        } else if (name.equalsIgnoreCase("t2")) {
            return 10;
        } else if (name.equalsIgnoreCase("t3")) {
            return 11;
        } else if (name.equalsIgnoreCase("t4")) {
            return 12;
        } else if (name.equalsIgnoreCase("t5")) {
            return 13;
        } else if (name.equalsIgnoreCase("t6")) {
            return 14;
        } else if (name.equalsIgnoreCase("t7")) {
            return 15;
        } else if (name.equalsIgnoreCase("s0")) {
            return 16;
        } else if (name.equalsIgnoreCase("s1")) {
            return 17;
        } else if (name.equalsIgnoreCase("s2")) {
            return 18;
        } else if (name.equalsIgnoreCase("s3")) {
            return 19;
        } else if (name.equalsIgnoreCase("s4")) {
            return 20;
        } else if (name.equalsIgnoreCase("s5")) {
            return 21;
        } else if (name.equalsIgnoreCase("s6")) {
            return 22;
        } else if (name.equalsIgnoreCase("s7")) {
            return 23;
        } else if (name.equalsIgnoreCase("t8")) {
            return 24;
        } else if (name.equalsIgnoreCase("t9")) {
            return 25;
        } else if (name.equalsIgnoreCase("sp")) {
            return 29;
        } else if (name.equalsIgnoreCase("fp")) {
            return 30;
        } else if (name.equalsIgnoreCase("ra")) {
            return 31;
        }
        return -1;
    }
}
