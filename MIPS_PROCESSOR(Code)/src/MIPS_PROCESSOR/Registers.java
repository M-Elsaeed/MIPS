package MIPS_PROCESSOR;

import static MIPS_PROCESSOR.InstructionMemory.*;

/**
 * This class contains all Register component related Code.
 */
public class Registers {

    private static int[] registers = new int[32];
    private static int data1;
    private static int data2;
    private static int writeReg;

    /**
     * Loading data into registers
     */
    public static void registerLodingData() {
        data1 = registers(Integer.parseInt(neededBinary(25, 21), 2));
        data2 = registers(Integer.parseInt(neededBinary(20, 16), 2));
    }

    /**
     * storing data into registers
     */
    public static void registerStoringData() {
        if (Control.isRegDst() == true) {
            writeReg = Integer.parseInt(neededBinary(15, 11), 2);
        } else {
            writeReg = Integer.parseInt(neededBinary(20, 16), 2);
        }
        if (Control.isRegWrite() == true) {
            registers(writeReg, DataMemory.memoryAndRegesterOutput());
        }
    }

    /**
     *
     * @return Data in register 1
     */
    public static int getData1() {
        return data1;
    }

    /**
     *
     * @param data1 to be added in register 1
     */
    public static void setData1(int data1) {
        Registers.data1 = data1;
    }

    /**
     *
     * @return Data in register 2
     */
    public static int getData2() {
        return data2;
    }

    /**
     *
     * @param data2 to be added in register 1
     */
    public static void setData2(int data2) {
        Registers.data2 = data2;
    }

    /**
     * Clears registers' contents
     */
    public static void clrRegisters() {
        for (int i = 0; i < registers.length; i++) {
            registers[i] = 0;
        }
    }

    /**
     *
     * @param i index of register to set.
     * @param d data to enter in the required register(int) Warning : if you
     * choose register zero , it won't be modified.
     */
    public static void registers(int i, int d) {
        if (i != 0) {
            registers[i] = d;
        }
    }

    /**
     *
     * @param i index of register
     * @return content of register as an int.
     */
    public static int registers(int i) {
        return registers[i];
    }
}
