package MIPS_PROCESSOR;

import static MIPS_PROCESSOR.InstructionMemory.neededBinary;

public class ALU {

    private static boolean zeroFlag;
    private static int res;

    /**
     * This method calls the AluOp to decide which operation to perform
     * and accordingly,performs the desired function.
     * @return the result of the operation done according to the number of the
     * case.
     */
    public static int calcRes() {
          
            int rs = Registers.getData1();
            int rt = Registers.getData2();
            int imm = Integer.parseInt(neededBinary(15, 0), 2);

            switch (Control.getAluOp()) {
                case 0:
                    if(Assembler.instructionMem[InstructionMemory.PC].getText().startsWith("j")&&!Assembler.instructionMem[InstructionMemory.PC].getText().startsWith("jr"))
                    {
                       res = (Integer.parseInt(neededBinary(26, 0), 2)/4) - DataMemory.getStartAddress();
                    }
                    else if (Assembler.instructionMem[InstructionMemory.PC].isImmPos()) {
                        res = rs + imm;
                    } else {
                        res = rs - imm;
                    }
                    break;
                case 1:
                    res = rs - rt;
                    if (res == 0) {
                        zeroFlag = true;

                    } else {
                        zeroFlag = false;
                    }
                    break;
                case 2:
                    switch (Control.getFunctCode()) {
                        case 32:
                            res = rs + rt;
                            break;
                        case 0:
                            int shamt = Integer.parseInt(neededBinary(10, 6), 2);
                            res = (int) (Math.pow(2, shamt) * rt);
                            break;
                        case 39:
                            res = ~(rs | rt);
                            break;
                        case 42:
                            if (rs < rt) {
                                res = 1;
                            } else {
                                res = 0;
                            }
                            break;

                    }
                    break;
                case 3:

                    if (Assembler.instructionMem[InstructionMemory.PC].isImmPos()) {
                        if (rs < imm) {
                            res = 1;
                        } else {
                            res = 0;
                        }
                    } else {
                        int negImm = -imm;
                        if (rs < negImm) {
                            res = 1;
                        } else {
                            res = 0;
                        }

                    }

            }

            return res;
            
          
        }
    

    /**
     *
     * @return state of ZeroFlag
     */
    public static boolean isZeroFlag() {
        return zeroFlag;
    }

    /**
     *
     * @return integer with ALU result
     */
    public static int getRes() {
        return res;
    }
}
