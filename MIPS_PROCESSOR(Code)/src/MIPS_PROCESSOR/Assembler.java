package MIPS_PROCESSOR;

import General_Functions.RegInfo;
import General_Functions.miscellaneous;
import java.util.Scanner;
/**
 * This Class has a main responsibility of Reading and passing the Assembly code to Assembler Which,
 * Does its computations on respective Strings.
 */
class InitiateFromGUI {

    public static int GUIcount = 0;
    public static int count = 0;

    /**
     * Calling this Constructor reads all instructions from the Assembly text area in the
     * GUI then puts them in instStrs Array;
     * When computed, instructions are loaded into theinstructionMem array of instructions.
     *
     * @param f1 is the GUI component that contains the Assembly-containing textField.
     */
    public InitiateFromGUI(MIPS_PROCESSOR.MainGUI f1) {
        String textEntered = f1.assemblyTxt.getText();

        int i = 0;
        Scanner sc = new Scanner(textEntered);
        while (sc.hasNextLine()) {
            Assembler.instStrs[i] = sc.nextLine();
            i++;
        }
        count = i;
        sc.close(); 
        for (int j = 0; j < i; j++) {
            Assembler.instructionMem[j] = new Assembler(j, Assembler.instStrs[j]);
        }
    }
}
/**
 * This class represents an Assembler, Its basic functionality is to convert the
 * Read Strings from InitiateFromGUI to a 32, MIPS-readable binary String.
 */
class Assembler {

    final private String text;
    final private int address;
    private char type;//L if label,R,I,J
    private String Binary;
    private boolean immPos = true;
    public static Assembler[] instructionMem = new Assembler[100];
    public static String[] instStrs = new String[100];

    /**
     * For an R type instruction, this function computes the binary representations
     * of rs,rt,rd numbers and loads them into a String Array respectively.
     * @param inst The instruction as assembly text(String)
     * @param rsrtrd This is the String array to which the rs,rt and rd will be
     * returned respectively.
     */
    private static void getregsR(String inst, String[] rsrtrd) {
        int x = 0;

        rsrtrd[2] = "";
        while (inst.charAt(x) != '$') {
            x++;
        }
        rsrtrd[2] = inst.substring(x + 1, x + 3);
        x++;

        rsrtrd[0] = "";
        while (inst.charAt(x) != '$') {
            x++;
        }

        rsrtrd[0] = inst.substring(x + 1, x + 3);
        x++;

        rsrtrd[1] = "";
        while (inst.charAt(x) != '$') {
            x++;
        }

        rsrtrd[1] = inst.substring(x + 1, x + 3);
    }

    /**
     * This function returns the number of the line of a Label in the Assembly code.
     * @param lbl is the label (String) to look for in the instructionMem[]
     * @return returns an int which resembles the label's address in the
     * instructionMem[] ps:The label itself is considered an instruction;
     */
    public static int getLabelAddress(String lbl) {

        for (int i = 0; i < instStrs.length; i++) {
            if (instStrs[i] != null) {
                if (instStrs[i].startsWith(lbl + " :")) {
                    return i;

                }
            }
        }

        return -1;

    }
        /**
     * Tells weather an Immediate value of a Binary Instruction is Positive
     * and a big number,or if it a negative number.
     * @return if the immediate is positive it returns true
     */
    public boolean isImmPos() {
        return immPos;
    }

    /**
     * @param i
     * @param inst this integer i indicates the Array index (instructionMem[]
     * address). Array addresses must start at index 0(Zero); each
     * instruction takes One array index. ie.you must disregard that it takes 4
     * bytes. String inst is the read assembly line an Assembler object has : An
     * address in instructionMem[](int). A text which is the read instruction as
     * is(String). Binary representation of the read Assembler(String) which is
     * this class main computation. A type Label(L),R,I,J (char).
     */
    public Assembler(int i, String inst) {

        address = i;
        inst = inst.trim();
        text = inst;
        Binary = "";

        // if it is a Label then the Binary will be the same as the address (Decimal) but written in Binary
        if (inst.contains(":")) {
            type = 'L';
            Binary = miscellaneous.extend(Integer.toBinaryString(i), 32);

        } //If it is an R type Assembler
        else if (inst.startsWith("add ") || inst.startsWith("slt ") || inst.startsWith("nor ") || inst.startsWith("sll ") || inst.startsWith("jr ")) {
            type = 'R';
            Binary = "";
            Binary += "000000";

            if (text.startsWith("add") || text.startsWith("slt") || text.startsWith("nor")) {
                String[] regarr = new String[3];
                getregsR(inst, regarr);
                //adding rs rt and rd
                Binary += miscellaneous.extend(Integer.toBinaryString(RegInfo.getRegNum(regarr[0])), 5);
                Binary += miscellaneous.extend(Integer.toBinaryString(RegInfo.getRegNum(regarr[1])), 5);
                Binary += miscellaneous.extend(Integer.toBinaryString(RegInfo.getRegNum(regarr[2])), 5);
                Binary += "00000";//shift amount = 0
                //Here we add the function code
                if (text.startsWith("add")) {
                    Binary += miscellaneous.extend(Integer.toBinaryString(32), 6);
                } else if (text.startsWith("nor")) {
                    Binary += miscellaneous.extend(Integer.toBinaryString(39), 6);
                } else if (text.startsWith("slt")) {
                    Binary += miscellaneous.extend(Integer.toBinaryString(42), 6);
                }
            } /*
            jr Assembler is encoded as follows
            jr rs
            op 6 zeros
            rs contains the number of the register to jump to its contents
            rt 5 zeros
            rd 5 zeros
            shamt 5 zeros
            funtion code is 001000(8 in Decimal)
             */ else if (inst.startsWith("jr ")) {
                int x = 0;
                while (inst.charAt(x) != '$') {
                    x++;
                }
                String jumpreg = inst.substring(x + 1, x + 3);
                Binary += miscellaneous.extend(Integer.toBinaryString(RegInfo.getRegNum(jumpreg)), 5);//adding rs to the Binary code
                Binary += "00000";//rt amount = 0
                Binary += "00000";//rd amount = 0
                Binary += "00000";//shift amount = 0
                Binary += miscellaneous.extend(Integer.toBinaryString(8), 6);//adding function code
            } /*
            sll  rd,rt,shamt
             */ else if (inst.startsWith("sll ")) {
                int x = 0;

                while (inst.charAt(x) != '$') {
                    x++;
                }
                String rd = inst.substring(x + 1, x + 3);
                x++;

                while (inst.charAt(x) != '$') {
                    x++;
                }
                String rt = inst.substring(x + 1, x + 3);
                x++;

                while (inst.charAt(x) != ',') {
                    x++;
                }

                int shamt = Integer.parseInt(inst.substring(x + 1, inst.length()));

                Binary += "00000";//adding rs to the Binary code
                Binary += miscellaneous.extend(Integer.toBinaryString(RegInfo.getRegNum(rt)), 5);
                Binary += miscellaneous.extend(Integer.toBinaryString(RegInfo.getRegNum(rd)), 5);//adding rd to the Binary code
                Binary += miscellaneous.extend(Integer.toBinaryString(shamt), 5);//shift amount = shamt
                Binary += miscellaneous.extend(Integer.toBinaryString(0), 6);//adding function code
            }

        } // if I type loading/storing
        else if (text.startsWith("lw ") || text.startsWith("sw ") || text.startsWith("lb ") || text.startsWith("lbu ") || text.startsWith("sb ")) {

            type = 'I';
            int x = 0;
            while (inst.charAt(x) != '$') {
                x++;
            }
            String rt = inst.substring(x + 1, x + 3);
            rt = miscellaneous.extend(Integer.toBinaryString(RegInfo.getRegNum(rt)), 5);
            x++;
            // y finds index of the comma;

            int y = x;
            while (inst.charAt(y) != ',') {
                y++;
            }
            while (inst.charAt(x) != '(') {
                x++;
            }
            if (Integer.parseInt(inst.substring((y + 1), x)) < 0) {
                immPos = false;
            }
            String imm = Integer.toBinaryString(Integer.parseInt(inst.substring((y + 1), x)));
            if (imm.length() <= 16) {
                imm = miscellaneous.extend(imm, 16);
            } else {
                imm = imm.substring(16, 32);
            }

            x += 2;

            String rs = inst.substring(x, x + 2);
            rs = miscellaneous.extend(Integer.toBinaryString(RegInfo.getRegNum(rs)), 5);

            if (inst.startsWith("lw")) {
                Binary += miscellaneous.extend(Integer.toBinaryString(35), 6);
            } else if (inst.startsWith("sw")) {
                Binary += miscellaneous.extend(Integer.toBinaryString(43), 6);
            } else if (inst.startsWith("lb ")) {
                Binary += miscellaneous.extend(Integer.toBinaryString(32), 6);
            } else if (inst.startsWith("lbu")) {
                Binary += miscellaneous.extend(Integer.toBinaryString(36), 6);
            } else if (inst.startsWith("sb "))//if sb
            {
                Binary += miscellaneous.extend(Integer.toBinaryString(40), 6);
            }

            Binary += rs;

            Binary += rt;

            Binary += imm;

        } //If I type ALU operation
        else if (text.startsWith("addi ") || text.startsWith("slti ")) {

            int x = 0;
            type = 'I';
            while (inst.charAt(x) != '$') {
                x++;
            }
            String rt = miscellaneous.extend(Integer.toBinaryString(RegInfo.getRegNum(inst.substring(x + 1, x + 3))), 5);
            x++;
            while (inst.charAt(x) != '$') {
                x++;
            }
            String rs = miscellaneous.extend(Integer.toBinaryString(RegInfo.getRegNum(inst.substring(x + 1, x + 3))), 5);
            x++;

            while (inst.charAt(x) != ',') {
                x++;
            }

            if (Integer.parseInt(inst.substring(x + 1, inst.length())) < 0) {
                immPos = false;
            }
            String imm;
            imm = Integer.toBinaryString(Integer.parseInt(inst.substring((x + 1), inst.length())));
            if (imm.length() <= 16) {
                imm = miscellaneous.extend(imm, 16);
            } else {
                imm = imm.substring(16, 32);
            }

            if (inst.startsWith("addi ")) {
                Binary += miscellaneous.extend(Integer.toBinaryString(8), 6);

            }
            if (inst.startsWith("slti ")) {
                Binary += miscellaneous.extend(Integer.toBinaryString(10), 6);
            }

            Binary += rs;

            Binary += rt;

            Binary += imm;

        } else if (text.startsWith("beq ")) {

            int x = 0;
            type = 'I';

            while (inst.charAt(x) != '$') {
                x++;
            }
            String rs = inst.substring(x + 1, x + 3);
            x++;
            while (inst.charAt(x) != '$') {
                x++;
            }
            String rt = inst.substring(x + 1, x + 3);
            x++;

            while (inst.charAt(x) != ',') {
                x++;
            }

            int imma = getLabelAddress(inst.substring(x + 1, inst.length())) - address - 1;
            if (imma < 0) {
                immPos = false;
            }
            String imm = Integer.toBinaryString(imma);
            if (imm.length() <= 16) {
                imm = miscellaneous.extend(imm, 16);
            } else {
                imm = imm.substring(16, 32);
            }

            Binary += miscellaneous.extend(Integer.toBinaryString(4), 6);
            Binary += miscellaneous.extend(Integer.toBinaryString(RegInfo.getRegNum(rs)), 5);
            Binary += miscellaneous.extend(Integer.toBinaryString(RegInfo.getRegNum(rt)), 5);
            Binary += imm;
        } else if (inst.startsWith("jal ")) {

            type = 'I';
            Binary += miscellaneous.extend(Integer.toBinaryString(3), 6);
            Binary += miscellaneous.extend(Integer.toBinaryString((getLabelAddress(inst.substring(4, inst.length())) * 4) + DataMemory.getStartAddress()), 26);

        } else if (inst.startsWith("j ")) {

            type = 'I';
            Binary += miscellaneous.extend(Integer.toBinaryString(2), 6);
            Binary += miscellaneous.extend(Integer.toBinaryString((getLabelAddress(inst.substring(2, inst.length())) * 4) + DataMemory.getStartAddress()), 26);

        }
        Binary = Binary.trim();
        instructionMem[address] = this;

    }

    public int getAddress() {
        return address;
    }

    public char getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public String getBinary() {
        return Binary;
    }

    @Override
    /**
     * Returns a Hexadecimal Representation of the instruction's Machine code.
     */
    public String toString() {
        return Long.toHexString(Long.parseLong(Binary, 2));
    }

}
