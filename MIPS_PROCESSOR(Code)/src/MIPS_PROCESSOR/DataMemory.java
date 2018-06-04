package MIPS_PROCESSOR;

import General_Functions.int32bits_to_4bytes;
import static General_Functions.int32bits_to_4bytes.*;
import static MIPS_PROCESSOR.InstructionMemory.PC;

public class DataMemory {

    static int off;
    static byte[] dataMem = new byte[400];
    private static int startAddress;

    /**
     * This method reads or writes data according to the inputs; if the memRead
     * control boolean is "true" the method will read data from memory,and if
     * the memWrite control boolean is "true" , the method will write data in
     * the memory; N.B memRead and memWrite are mutually exclusive.
     */
    public static void data() {
        if (Control.isMemRead() == true) {
            off = ALU.getRes() - (DataMemory.getStartAddress() + (InitiateFromGUI.count * 4));
            if (!(Assembler.instructionMem[PC].getText().startsWith("lb ") || Assembler.instructionMem[PC].getText().startsWith("lbu "))) {
                off /= 4;
                off *= 4;

                int32bits_to_4bytes.setFirst(dataMem[off]);
                int32bits_to_4bytes.setSecond(dataMem[off + 1]);
                int32bits_to_4bytes.setThird(dataMem[off + 2]);
                int32bits_to_4bytes.setForth(dataMem[off + 3]);
            } else {
                int32bits_to_4bytes.setForth(dataMem[off]);

                if (Assembler.instructionMem[PC].getText().startsWith("lbu ")) {

                    int32bits_to_4bytes.setForth((byte) Math.abs(dataMem[off]));
                }

            }

        }
        if (Control.isMemWrite() == true) {
            bytes(Registers.getData2());
            off = ALU.getRes() - (DataMemory.getStartAddress() + (InitiateFromGUI.count * 4));
            if (!Assembler.instructionMem[PC].getText().startsWith("sb ")) {
                off /= 4;
                off *= 4;
                dataMem[off] = int32bits_to_4bytes.getFirst();
                dataMem[off + 1] = int32bits_to_4bytes.getSecond();
                dataMem[off + 2] = int32bits_to_4bytes.getThird();
                dataMem[off + 3] = int32bits_to_4bytes.getForth();

            } else {
                dataMem[off] = (byte) Registers.getData2();
            }
        }
    }

    /**
     * This method represents the MUX in front of the Data Memory; if the MUX is
     * "false" the input to the registers will be the output from the ALU else,
     * it will be the data loaded from the memory.
     *
     * @return the result from memory or ALU.
     */
    public static int memoryAndRegesterOutput() {
        if (Control.isMemToReg() == true) {
            if (Assembler.instructionMem[PC].getText().startsWith("lbu ")) {
                String x = Integer.toBinaryString(dataMem[off]).substring(0, 8);
                int res = 0;
                for (int i = x.length() - 1; i >= 0; i--) {
                    char c = x.charAt(i);
                    if (c == '1') {
                        res += (int) (Math.pow(2, x.length() - 1 - i));
                    }
                }
                return res-Math.abs(dataMem[off])+1;
            } else {
                return ints();
            }
        }
        return ALU.getRes();
    }

    /**
     * Clears all memory data.
     */
    public static void clrMemory() {
        for (int i = 0; i < dataMem.length; i++) {
            dataMem[i] = 0;
        }
    }

    public static int getStartAddress() {
        return startAddress;

    }

    public static void setStartAddress(int startAddress) {
        DataMemory.startAddress = startAddress;

    }

}
