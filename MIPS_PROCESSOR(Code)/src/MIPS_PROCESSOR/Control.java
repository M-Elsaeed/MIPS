package MIPS_PROCESSOR;

import static MIPS_PROCESSOR.InstructionMemory.neededBinary;

public class Control {

    private static boolean regDst, jump, branch, memRead, memToReg, memWrite, aluSrc, regWrite;
    private static byte aluOp;
    private static int functCode;

    /**
     * This method sets the control values to either true or false and sets the
     * aluOp based on the opCode of the instruction; if the opCode was zero,
     * another method is called to set the functCode in order to be used in the
     * AlU_Control.
     */
    public static void controlUnit() {

        int opCode = Integer.parseInt(neededBinary(31, 26), 2);

        switch (opCode) {

            case 8:
                aluSrc = regWrite = true;
                aluOp = 0;
                break;
            case 35:
            case 32:
            case 36:
                memRead = memToReg = aluSrc = regWrite = true;
                aluOp = 0;
                break;
            case 43:
            case 40:
                memWrite = aluSrc = true;
                aluOp = 0;
                break;
            case 4:
                branch = true;
                aluOp = 1;
                break;
            case 2:
            case 3:
                jump = true;
                aluOp = 0;
                break;
            case 10:
                aluSrc = regWrite = true;
                aluOp = 3;
                break;

        }
        if (opCode == 0) {
            aluOp = 2;
            functCode = Integer.parseInt(neededBinary(5, 0), 2);
            if (functCode == 8) {
                jump = true;
            } else {
                regDst = regWrite = true;
            }

        }
    }

    /**
     * This method sets all control values to false resetting the control
     * signals.
     */
    public static void clrControl() {
        regDst = jump = branch = memRead = memToReg = memWrite = aluSrc = regWrite = false;

    }

/**
 * This method returns the AluControl Value so that the ALU operation can be decided.
 * @return ALU control value
 */
    public static int getAluControl() {
        int x = getAluOp();
        switch (x) {
            case 0:
                return 2;

            case 1:
                return 6;

            case 2:
                switch (getFunctCode()) {
                    case 32:
                        return 2;
                    case 0:
                        return 5;//sll Alucontrol
                    case 39:
                        return 6;//nor Alucontrol
                    case 42:
                        return 7;

                }
            case 3:
                return 7;

        }
        return 0;

    }

    public static boolean isRegDst() {
        return regDst;
    }

    public static boolean isJump() {
        return jump;
    }

    public static boolean isBranch() {
        return branch;
    }

    public static boolean isMemRead() {
        return memRead;
    }

    public static boolean isMemToReg() {
        return memToReg;
    }

    public static boolean isMemWrite() {
        return memWrite;
    }

    public static boolean isAluSrc() {
        return aluSrc;
    }

    public static boolean isRegWrite() {
        return regWrite;
    }

    public static byte getAluOp() {
        return aluOp;
    }

    public static int getFunctCode() {
        return functCode;
    }

}
