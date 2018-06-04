package MIPS_PROCESSOR;

import static General_Functions.RegInfo.*;

/**
 * Sends data of registers and memory to GUI
 */
public class OutputingToGUI {

    /**
     * @return all Registers' data
     */
    public static String regOut() {
        String regOut = "";
        for (int i = 0; i < 32; i++) {
            regOut = regOut + "Register $" + getRegName(i) + " : " + Registers.registers(i) + "\n";
        }
        return regOut;
    }

    /**
     *
     * @return all memory and instruction memory data
     */
    public static String memOut() {
        String memOut = "";
        int instrCount = InitiateFromGUI.count;
        int startAddress = DataMemory.getStartAddress();

        for (int k = 0; k < (instrCount * 4) - 1; k += 4) {
            for (int j = 0; j < 32; j += 8) {
                memOut = memOut + "Memory Address " + ((k + (j / 8)) + startAddress) + " : " + Assembler.instructionMem[k / 4].getBinary().substring(j, j + 8) + "\n";
            }
        }

        for (int i = 0; i < 400; i++) {
            memOut = memOut + "Memory Address " + (instrCount * 4 + i + startAddress) + " : " + DataMemory.dataMem[i] + "\n";
        }
        return memOut;
    }
}
