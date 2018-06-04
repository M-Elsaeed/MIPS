package MIPS_PROCESSOR;

import static MIPS_PROCESSOR.ALU.isZeroFlag;
import static MIPS_PROCESSOR.Control.*;
import static MIPS_PROCESSOR.InstructionMemory.neededBinary;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *This class contains all ViewOperation Tab related Code.
 *It contains all GUI components of the ViewOperation Tab
 */
public class XRayPanel extends JPanel {

    public Font txtFont = new JButton().getFont().deriveFont(18f);
    final static double scale = 0.5;
    //Lines
    public JButton pclinebtn = new JButton();
    public JButton registerslinebtn1 = new JButton();
    public JButton registerslinebtn2 = new JButton();
    public JButton registerslinebtn3 = new JButton();
    public JButton jumplinebtn = new JButton();
    public JButton signextendlinebtn = new JButton();
    public JButton nextPCLine = new JButton();
    public JButton PCplus4 = new JButton();
    public JButton register1ToALU = new JButton();
    public JButton register2ToALU = new JButton();
    public JButton ALUControlLine = new JButton();
    public JButton ALUResultLine = new JButton();
    public JButton finalToRegLine = new JButton();

    //Main components
    public JButton pcBtn = new JButton("PC");
    public JButton instructionMemoryBtn = new JButton("<html>Instruction<br/>Memory</html>");
    public JButton registersBtn = new JButton("Register");
    public JButton dataMemorybtn = new JButton("Data Memory");
    public JButton aluBtn = new JButton("ALU");

    //MUX
    public JButton muxRegisterBtn = new JButton("MUX");
    public JButton muxAluBtn = new JButton("MUX");
    public JButton muxDataMemoryBtn = new JButton("MUX");
    public JButton muxBranchBtn = new JButton("MUX");
    public JButton muxJumpBtn = new JButton("MUX");

    //Adder
    public JButton adderPlus4btn = new JButton("Adder");
    public JButton adderPlusAddressbtn = new JButton("Adder");

    //Shifters
    public JButton shifter1Btn = new JButton("Shifter");
    public JButton shifter2Btn = new JButton("Shifter");

    //signextend
    public JButton signextendBtn = new JButton("<html>Sign<br/>Extend</html>");

    //ALU control
    public JButton aluControlBtn = new JButton("ALU Control");

    //AND gate
    public JButton andBtn = new JButton();

    //Control Data
    private JButton registerwrite = new JButton("Reg Write  ");
    private JButton dataWriteCntrl = new JButton("Data Write  ");
    private JButton dataReadCntrl = new JButton("Data Read  ");
    private JButton zeroFlagCntrl = new JButton("Zero Flag  ");
    private JButton branchCntrl = new JButton("Branch  ");
    private JButton ALUOpCntrl = new JButton("ALU OP  ");

    //Clock Label
    public JLabel clocksLbl;

    /**
     * Empty constructor.
     * This constructors calls JPanel constructor since
     * it is the parent and calls the intializing funtion (init).
     */
    public XRayPanel() {
        super(null, true);
        init();
    }

    private void init() {
        setLayout(null);
        this.setBounds(0, 0, 900, 900);
        JLabel imgLabel = new JLabel(new ImageIcon("mips datapath.png"));
        imgLabel.setBounds(-10, -48, 900, 900);

        clocksLbl = new JLabel("Clocks = 0");
        clocksLbl.setBounds(500, 800, 100, 80);
        this.add(clocksLbl);

        pcBtn.setSize(42, 73);
        pcBtn.setEnabled(false);
        pcBtn.setLocation(12, 414);
        pcBtn.setMargin(new Insets(0, 0, 0, 0));
        this.add(pcBtn);

        registerwrite.setBounds(368, 412, 80, 30);
        registerwrite.setEnabled(false);
        registerwrite.setBorder(null);
        registerwrite.setOpaque(false);
        registerwrite.setMargin(new Insets(0, 0, 0, 0));
        this.add(registerwrite);

        dataWriteCntrl.setBounds(650, 465, 80, 30);
        dataWriteCntrl.setEnabled(false);
        dataWriteCntrl.setBorder(null);
        this.add(dataWriteCntrl);

        dataReadCntrl.setBounds(650, 590, 80, 30);
        dataReadCntrl.setEnabled(false);
        dataReadCntrl.setBorder(null);
        this.add(dataReadCntrl);

        zeroFlagCntrl.setBounds(680, 315, 80, 30);
        zeroFlagCntrl.setEnabled(false);
        zeroFlagCntrl.setBorder(null);
        this.add(zeroFlagCntrl);

        branchCntrl.setBounds(565, 315, 80, 30);
        branchCntrl.setEnabled(false);
        branchCntrl.setBorder(null);
        this.add(branchCntrl);

        ALUOpCntrl.setBounds(521, 700, 80, 30);
        ALUOpCntrl.setEnabled(false);
        ALUOpCntrl.setBorder(null);
        this.add(ALUOpCntrl);

        instructionMemoryBtn.setSize(102, 200);
        instructionMemoryBtn.setEnabled(false);
        instructionMemoryBtn.setLocation(82, 420);
        instructionMemoryBtn.setMargin(new Insets(0, 0, 0, 0));
        this.add(instructionMemoryBtn);

        registersBtn.setSize(103, 162);
        registersBtn.setEnabled(false);
        registersBtn.setLocation(352, 420);
        registersBtn.setMargin(new Insets(0, 0, 0, 0));
        this.add(registersBtn);

        dataMemorybtn.setSize(103, 159);
        dataMemorybtn.setEnabled(false);
        dataMemorybtn.setLocation(639, 464);
        dataMemorybtn.setMargin(new Insets(0, 0, 0, 0));
        this.add(dataMemorybtn);

        aluBtn.setSize(58, 138);
        aluBtn.setEnabled(false);
        aluBtn.setLocation(545, 432);
        aluBtn.setMargin(new Insets(0, 0, 0, 0));
        this.add(aluBtn);

        muxRegisterBtn.setSize(43, 100);
        muxRegisterBtn.setEnabled(false);
        muxRegisterBtn.setLocation(286, 485);
        muxRegisterBtn.setMargin(new Insets(0, 0, 0, 0));
        this.add(muxRegisterBtn);

        muxAluBtn.setSize(43, 100);
        muxAluBtn.setEnabled(false);
        muxAluBtn.setLocation(495, 487);
        muxAluBtn.setMargin(new Insets(0, 0, 0, 0));
        this.add(muxAluBtn);

        muxDataMemoryBtn.setSize(43, 100);
        muxDataMemoryBtn.setEnabled(false);
        muxDataMemoryBtn.setLocation(757, 500);
        muxDataMemoryBtn.setMargin(new Insets(0, 0, 0, 0));
        this.add(muxDataMemoryBtn);

        muxBranchBtn.setSize(43, 108);
        muxBranchBtn.setEnabled(false);
        muxBranchBtn.setLocation(657, 115);
        muxBranchBtn.setMargin(new Insets(0, 0, 0, 0));
        this.add(muxBranchBtn);

        muxJumpBtn.setSize(43, 108);
        muxJumpBtn.setEnabled(false);
        muxJumpBtn.setLocation(716, 115);
        muxJumpBtn.setMargin(new Insets(0, 0, 0, 0));
        this.add(muxJumpBtn);

        adderPlus4btn.setSize(40, 140);
        adderPlus4btn.setMargin(new Insets(0, 0, 0, 0));
        adderPlus4btn.setEnabled(false);
        adderPlus4btn.setLocation(117, 162);
        adderPlus4btn.setMargin(new Insets(0, 0, 0, 0));
        this.add(adderPlus4btn);

        adderPlusAddressbtn.setSize(59, 139);
        adderPlusAddressbtn.setMargin(new Insets(0, 10, 0, 0));
        adderPlusAddressbtn.setEnabled(false);
        adderPlusAddressbtn.setLocation(545, 136);
        adderPlusAddressbtn.setMargin(new Insets(0, 0, 0, 0));
        this.add(adderPlusAddressbtn);

        shifter1Btn.setSize(60, 70);
        shifter1Btn.setEnabled(false);
        shifter1Btn.setLocation(248, 75);
        shifter1Btn.setMargin(new Insets(0, 0, 0, 0));
        this.add(shifter1Btn);

        shifter2Btn.setSize(60, 69);
        shifter2Btn.setEnabled(false);
        shifter2Btn.setLocation(473, 212);
        shifter2Btn.setMargin(new Insets(0, 0, 0, 0));
        this.add(shifter2Btn);

        signextendBtn.setSize(72, 110);
        signextendBtn.setEnabled(false);
        signextendBtn.setLocation(390, 589);
        signextendBtn.setMargin(new Insets(0, 0, 0, 0));
        this.add(signextendBtn);

        aluControlBtn.setSize(80, 100);
        aluControlBtn.setEnabled(false);
        aluControlBtn.setLocation(520, 625);
        aluControlBtn.setMargin(new Insets(0, 0, 0, 0));
        this.add(aluControlBtn);

        andBtn.setSize(35, 39);
        andBtn.setEnabled(false);
        andBtn.setLocation(655, 255);
        this.add(andBtn);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////LINES
        registerslinebtn1.setSize(70, 13);
        registerslinebtn1.setBorder(new EmptyBorder(0, 0, 0, 0));
        registerslinebtn1.setBackground(Color.white);
        registerslinebtn1.setEnabled(false);
        this.add(registerslinebtn1);

        registerslinebtn2.setSize(70, 13);
        registerslinebtn2.setBorder(new EmptyBorder(0, 0, 0, 0));
        registerslinebtn2.setBackground(Color.white);
        registerslinebtn2.setEnabled(false);
        this.add(registerslinebtn2);

        registerslinebtn3.setSize(70, 13);
        registerslinebtn3.setBorder(new EmptyBorder(0, 0, 0, 0));
        registerslinebtn3.setBackground(Color.white);
        registerslinebtn3.setEnabled(false);
        this.add(registerslinebtn3);

        signextendlinebtn.setSize(115, 13);
        signextendlinebtn.setBorder(new EmptyBorder(0, 0, 0, 0));
        signextendlinebtn.setBackground(Color.white);
        signextendlinebtn.setEnabled(false);
        this.add(signextendlinebtn);

        jumplinebtn.setSize(225, 13);
        jumplinebtn.setBorder(new EmptyBorder(0, 0, 0, 0));
        jumplinebtn.setBackground(Color.white);
        jumplinebtn.setEnabled(false);
        this.add(jumplinebtn);

        pclinebtn.setSize(40, 13);
        pclinebtn.setBorder(new EmptyBorder(0, 0, 0, 0));
        pclinebtn.setBackground(Color.white);
        pclinebtn.setEnabled(false);
        this.add(pclinebtn);

        nextPCLine.setSize(80, 13);
        nextPCLine.setBorder(new EmptyBorder(0, 0, 0, 0));
        nextPCLine.setBackground(Color.white);
        nextPCLine.setEnabled(false);
        this.add(nextPCLine);

        PCplus4.setSize(40, 13);
        PCplus4.setBorder(new EmptyBorder(0, 0, 0, 0));
        PCplus4.setBackground(Color.white);
        PCplus4.setEnabled(false);
        this.add(PCplus4);

        register1ToALU.setSize(40, 13);
        register1ToALU.setBorder(new EmptyBorder(0, 0, 0, 0));
        register1ToALU.setBackground(Color.white);
        register1ToALU.setEnabled(false);
        this.add(register1ToALU);

        register2ToALU.setSize(40, 13);
        register2ToALU.setBorder(new EmptyBorder(0, 0, 0, 0));
        register2ToALU.setBackground(Color.white);
        register2ToALU.setEnabled(false);
        this.add(register2ToALU);

        ALUControlLine.setSize(40, 13);
        ALUControlLine.setBorder(new EmptyBorder(0, 0, 0, 0));
        ALUControlLine.setBackground(Color.white);
        ALUControlLine.setEnabled(false);
        this.add(ALUControlLine);

        ALUResultLine.setSize(40, 13);
        ALUResultLine.setBorder(new EmptyBorder(0, 0, 0, 0));
        ALUResultLine.setBackground(Color.white);
        ALUResultLine.setEnabled(false);
        this.add(ALUResultLine);

        finalToRegLine.setSize(40, 13);
        finalToRegLine.setBorder(new EmptyBorder(0, 0, 0, 0));
        finalToRegLine.setBackground(Color.white);
        finalToRegLine.setEnabled(false);
        finalToRegLine.setLocation(550, 756);
        this.add(finalToRegLine);

        this.add(imgLabel);

        int countie = 1;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("# " + (countie) + "X coord " + e.getX() + " Y coord " + e.getY());

            }

        });

    }

    /**
     * This function changes components color according to the control unit
     * output. 1 will produce green color. 0 will produce red color.
     */
    public void color() {

        if (isRegDst()) {
            muxRegisterBtn.setBackground(Color.green);
        } else {
            muxRegisterBtn.setBackground(Color.red);
        }

        if (isJump()) {
            muxJumpBtn.setBackground(Color.green);
        } else {
            muxJumpBtn.setBackground(Color.red);
        }

        if (isMemToReg()) {
            muxDataMemoryBtn.setBackground(Color.green);
        } else {
            muxDataMemoryBtn.setBackground(Color.red);
        }

        if (isAluSrc()) {
            muxAluBtn.setBackground(Color.green);
        } else {
            muxAluBtn.setBackground(Color.red);
        }

        if (isBranch() && isZeroFlag()) {
            andBtn.setBackground(Color.green);
            muxBranchBtn.setBackground(Color.green);
        } else {
            andBtn.setBackground(Color.red);
            muxBranchBtn.setBackground(Color.red);
        }

        if (isBranch()) {
            branchCntrl.setBackground(Color.green);
        } else {
            branchCntrl.setBackground(Color.red);
        }

        if (isZeroFlag()) {
            zeroFlagCntrl.setBackground(Color.green);
        } else {
            zeroFlagCntrl.setBackground(Color.red);
        }

        if (isMemRead() || isMemWrite()) {
            if (isMemRead()) {
                dataReadCntrl.setBackground(Color.green);
            }
            if (isMemWrite()) {
                dataWriteCntrl.setBackground(Color.green);
            }
        } else {
            dataWriteCntrl.setBackground(Color.red);
            dataReadCntrl.setBackground(Color.red);
        }

        if (isRegWrite()) {
            registersBtn.setBackground(Color.green);
        } else {
            registersBtn.setBackground(Color.red);
        }
    }

    /**
     * This function outputs correct data in each wire to the screen.
     */
    public void writeLinesLabels() {
        registerslinebtn1.setText(neededBinary(25, 21));
        registerslinebtn1.setFont(txtFont);
        registerslinebtn1.setLocation(220, 425);

        registerslinebtn2.setText(neededBinary(20, 16));
        registerslinebtn2.setFont(txtFont);
        registerslinebtn2.setLocation(220, 467);

        registerslinebtn3.setText(neededBinary(15, 11));
        registerslinebtn3.setFont(txtFont);
        registerslinebtn3.setLocation(220, 548);

        signextendlinebtn.setText(neededBinary(15, 0));
        signextendlinebtn.setLocation(220, 630);

        pclinebtn.setText("" + (InstructionMemory.getPCForDisplay()));///////////////////////////////////////////////////////////////////problem
        pclinebtn.setFont(txtFont);
        pclinebtn.setLocation(10, 490);

        jumplinebtn.setText(neededBinary(25, 0) + "00");
        jumplinebtn.setLocation(350, 92);

        nextPCLine.setText(Integer.toString((InstructionMemory.nextPC() * 4) + DataMemory.getStartAddress()));
        nextPCLine.setFont(txtFont);
        nextPCLine.setLocation(394, 40);

        PCplus4.setText("" + (InstructionMemory.getPCForDisplay() + 4));
        PCplus4.setFont(txtFont);
        PCplus4.setLocation(287, 197);

        register1ToALU.setText("" + Registers.registers(Integer.parseInt(neededBinary(25, 21), 2)));
        register1ToALU.setFont(txtFont);
        register1ToALU.setLocation(461, 445);

        register2ToALU.setText("" + Registers.registers(Integer.parseInt(neededBinary(20, 16), 2)));
        register2ToALU.setFont(txtFont);
        register2ToALU.setLocation(457, 486);

        ALUControlLine.setText("" + Control.getAluControl());
        ALUControlLine.setFont(txtFont);
        ALUControlLine.setLocation(555, 613);

        ALUResultLine.setText(ALU.getRes() + "");
        ALUResultLine.setFont(txtFont);
        ALUResultLine.setLocation(605, 500);

        if (isBranch()) {
            branchCntrl.setBackground(Color.green);
        } else {
            branchCntrl.setBackground(Color.red);
        }
        if (isZeroFlag()) {
            zeroFlagCntrl.setBackground(Color.green);
        } else {
            zeroFlagCntrl.setBackground(Color.red);
        }
        if (Control.isMemRead()) {
            dataReadCntrl.setBackground(Color.green);
        } else {
            dataReadCntrl.setBackground(Color.red);
        }
        if (Control.isMemWrite()) {
            dataWriteCntrl.setBackground(Color.green);
        } else {
            dataWriteCntrl.setBackground(Color.red);
        }
        if (Control.isRegWrite()) {
            registerwrite.setBackground(Color.green);
        } else {
            registerwrite.setBackground(Color.red);
        }
        color();

        finalToRegLine.setText("" + DataMemory.memoryAndRegesterOutput());

    }

    /**
     * This function clears all data in wires(visually).
     */
    public void clearLinesLabels() {
        pclinebtn.setText("");
        registerslinebtn1.setText("");
        registerslinebtn2.setText("");
        registerslinebtn3.setText("");
        jumplinebtn.setText("");
        signextendlinebtn.setText("");
        nextPCLine.setText("");
        PCplus4.setText("");
        register1ToALU.setText("");
        register2ToALU.setText("");
        ALUControlLine.setText("");
        ALUResultLine.setText("");
        finalToRegLine.setText("");
    }
}
