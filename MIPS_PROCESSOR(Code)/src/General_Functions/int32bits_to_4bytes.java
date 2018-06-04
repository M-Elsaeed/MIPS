package General_Functions;

public class int32bits_to_4bytes {

    private static boolean positive;

    private static byte first = 0;//most significant byte
    private static byte second = 0;//2nd most significant byte
    private static byte third = 0;//3nd most significant byte
    private static byte forth = 0;//least significant byte

    private static int a = 0;//first byte converted to decimal
    private static int b = 0;//second byte converted to decimal
    private static int c = 0;//third byte converted to decimal
    private static int d = 0;//fourth byte converted to decimal
    private static int integer;//all 4 bytes converted to decimal

    /**
     * Converts an int into 4 bytes.
     * @param i decimal you need to turn to 4 binaries.
     * it returns its operations by setting the static variables 
     * first,second,third and fourth.
     */
    public static void bytes(int i) {

        first = (byte) (i >> 24);
        second = (byte) (i >> 16);
        third = (byte) (i >> 8);
        forth = (byte) (i /*>> 0*/);

    }

    /**
     * changes 4 binary back to integer
     * reverse process for bytes function
     * @return integer from 4 bytes
     */
    public static int ints() {
        if (positive == true) {
            //calculate a
            if (first >= 0) {
                a = (int) (first * Math.pow(256, 3));
            } else {
                a = (int) ((256 + first) * Math.pow(256, 3));
            }

            //calculate b
            if (second >= 0) {
                b = (int) (second * Math.pow(256, 2));
            } else {
                b = (int) ((256 + second) * Math.pow(256, 2));
            }

            //calculate c
            if (third >= 0) {
                c = (int) (third * Math.pow(256, 1));
            } else {
                c = (int) ((256 + third) * Math.pow(256, 1));
            }

            //calculate d
            if (forth >= 0) {
                d = (int) (forth * Math.pow(256, 0));
            } else {
                d = (int) ((256 + forth) * Math.pow(256, 0));
            }
            integer = a + b + c + d;
        } else {
            //calculate a
            if (first >= -1) {
                a = (int) (first * Math.pow(256, 3));
            } else {
                a = (int) ((256 + first) * Math.pow(256, 3));
            }

            //calculate b
            if (second >= 0) {
                b = (int) (second * Math.pow(256, 2));
            } else {
                b = (int) ((256 + second) * Math.pow(256, 2));
            }

            //calculate c
            if (third >= 0) {
                c = (int) (third * Math.pow(256, 1));
            } else {
                c = (int) ((256 + third) * Math.pow(256, 1));
            }

            //calculate d
            if (forth >= 0) {
                d = (int) (forth * Math.pow(256, 0));
            } else {
                d = (int) ((256 + forth) * Math.pow(256, 0));
            }
            integer = a + b + c + d;
        }
        return integer;
    }

    /**
     *
     * @return most significant byte
     */
    public static byte getFirst() {
        return first;
    }

    /**
     *
     * @return 2nd most significant byte
     */
    public static byte getSecond() {
        return second;
    }

    /**
     *
     * @return 3nd most significant byte
     */
    public static byte getThird() {
        return third;
    }

    /**
     *
     * @return least significant byte
     */
    public static byte getForth() {
        return forth;
    }

    /**
     *
     * @param first byte (most significant)
     */
    public static void setFirst(byte first) {
        int32bits_to_4bytes.first = first;
    }

    /**
     *
     * @param second byte (2nd most significant)
     */
    public static void setSecond(byte second) {
        int32bits_to_4bytes.second = second;
    }

    /**
     *
     * @param third byte (3nd most significant)
     */
    public static void setThird(byte third) {
        int32bits_to_4bytes.third = third;
    }

    /**
     *
     * @param forth byte (least significant)
     */
    public static void setForth(byte forth) {
        int32bits_to_4bytes.forth = forth;
    }
}
