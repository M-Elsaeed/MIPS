package MIPS_PROCESSOR;

import General_Functions.RegInfo;
import General_Functions.miscellaneous;
import static MIPS_PROCESSOR.Assembler.*;

/**
 * This class contains all Instruction Memory component related Code.
 */
public class InstructionMemory {

    static int PC = 0;

    /**
     * @param End BIG NUMBER
     * @param Start SMALL NUMBER
     * @return A STRING WITH BINARY REPRESENTATION USE STATIC FUNCTION CONVERT
     * IN BINARY DECIMAL CONVERSIONN TO CONVERT STRING TO DECIMAL INT
     */
    public static final String neededBinary(int End, int Start) //Sends Needed Bits of current Instruction
    {
        String BINARY = "";
        String currentInstruction = "";

        if (Assembler.instructionMem[InstructionMemory.PC].getType() == 'I' && (!Assembler.instructionMem[InstructionMemory.PC].getText().startsWith("j"))) {
            currentInstruction = Assembler.instructionMem[InstructionMemory.PC].getBinary().substring(0, 16) + getImmediateVal(Assembler.instructionMem[InstructionMemory.PC]);
        } else {
            currentInstruction = Assembler.instructionMem[InstructionMemory.PC].getBinary();
        }
        for (int i = 31 - End; i <= 31 - Start; i++) {
            char c = currentInstruction.charAt(i);
            BINARY = BINARY + c;
        }
        return BINARY;
    }

    /**
     * @param inst Assembler
     * @return immediate value of i type Assembler with sign
     */
    private static String getImmediateVal(Assembler inst) {
        String immediateVal = inst.getBinary().substring(16, 32);
        if (inst.isImmPos()) {
            return immediateVal;
        } else {
            int res = 0;
            for (int i = immediateVal.length() - 1; i >= 0; i--) {
                char c = immediateVal.charAt(i);
                if (c == '1') {
                    if (i == 0) {
                        res -= (int) Math.pow(2, immediateVal.length() - 1);
                    } else {
                        res += (int) (Math.pow(2, immediateVal.length() - 1 - i));
                    }
                }
                res = Math.abs(res);
            }
            return miscellaneous.extend(Integer.toBinaryString(res), 16);
        }
    }

    /**
     * This class performs all incrementing conditions. E.G Jumping call,Normal
     * incrementing
     */
    public static void incrementPC() {
        if (instructionMem[PC].getText().startsWith("beq ") && ALU.isZeroFlag()) {
            int x = 0;
            String s = instructionMem[PC].getText();
            while (s.charAt(x) != ',') {
                x++;
            }
            x++;
            while (s.charAt(x) != ',') {
                x++;
            }
            s = s.substring(x + 1, s.length());
            PC = getLabelAddress(s);

        } else if (instructionMem[PC].getText().startsWith("jr ") && Control.isJump()) {

            PC = (Registers.registers(Integer.parseInt(neededBinary(25, 21), 2)) - DataMemory.getStartAddress()) / 4;

        } else if (instructionMem[PC].getText().startsWith("jal ") && Control.isJump()) {
            Registers.registers(RegInfo.getRegNum("ra"), getPCForDisplay() + 4);

            PC = (Integer.parseInt(instructionMem[PC].getBinary().substring(6, 32), 2) - DataMemory.getStartAddress()) / 4;

        } else if (instructionMem[PC].getText().startsWith("j") && Control.isJump()) {

            PC = (Integer.parseInt(instructionMem[PC].getBinary().substring(6, 32), 2) - DataMemory.getStartAddress()) / 4;

        } else {
            PC++;
        }
    }

    /**
     * Resets PC to zero.
     */
    public static void resetPC() {
        PC = 0;
    }

    public static int getPCForDisplay() {
        return (PC * 4) + DataMemory.getStartAddress();
    }

    public static int nextPC() {
        if (instructionMem[PC].getText().startsWith("beq ") && ALU.isZeroFlag()) {
            int x = 0;
            String s = instructionMem[PC].getText();
            while (s.charAt(x) != ',') {
                x++;
            }
            x++;
            while (s.charAt(x) != ',') {
                x++;
            }
            s = s.substring(x + 1, s.length());
            return getLabelAddress(s);

        } else if (instructionMem[PC].getText().startsWith("jr ") && Control.isJump()) {

            return (Registers.registers(Integer.parseInt(neededBinary(25, 21), 2)) + DataMemory.getStartAddress()) / 4;

        } else if (instructionMem[PC].getText().startsWith("jal ") && Control.isJump()) {
            Registers.registers(RegInfo.getRegNum("ra"), getPCForDisplay() + 4);

            return (Integer.parseInt(instructionMem[PC].getBinary().substring(6, 32), 2) - DataMemory.getStartAddress()) / 4;

        } else if (instructionMem[PC].getText().startsWith("j") && Control.isJump()) {

            return (Integer.parseInt(instructionMem[PC].getBinary().substring(6, 32), 2) - DataMemory.getStartAddress()) / 4;

        } else {
            return PC + 1;
        }
    }
}
