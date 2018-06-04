package MIPS_PROCESSOR;

import static MIPS_PROCESSOR.ALU.*;
import static MIPS_PROCESSOR.Control.*;
import static MIPS_PROCESSOR.DataMemory.*;
import static MIPS_PROCESSOR.InstructionMemory.*;
import static MIPS_PROCESSOR.OutputingToGUI.*;
import static MIPS_PROCESSOR.Registers.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.*;

/**
 * This class contains all GUI Code except for XRayPanel. It contains all GUI
 * components of the whole program except for XRayPanel
 */
public class MainGUI extends JFrame implements ActionListener {

    //Main 4 Panels
    public JPanel btnsPnl = new JPanel();
    public JPanel dataPnl = new JPanel();
    public JPanel assemblyPnl = new JPanel();
    public XRayPanel xraypnl = new XRayPanel();

    //Buttons
    public JButton runBtn = new JButton("Run");
    public JButton stepBtn = new JButton("Step by Step");
    public JButton resetBtn = new JButton("Reset");
    public JButton setValsBtn = new JButton("Set Memory Values");
    public JButton setStartAddBtn = new JButton("Set Starting Address");
    public JButton saveBtn = new JButton("Save");

    //Text Areas
    public JTextArea regTxt = new JTextArea();
    public JTextArea memTxt = new JTextArea();
    public JTextArea assemblyTxt = new JTextArea();

    //Tabs
    public JTabbedPane assemblyTabbedPane = new JTabbedPane();
    public JTabbedPane dataTabbedPane = new JTabbedPane();

    //Font
    public Font txtFont = regTxt.getFont().deriveFont(18f);

    //Clocks counter
    public static int Clocks = 0;

    /**
     * Empty constructor. Calls the intializing funtion (init).
     *
     * @throws IOException
     */
    public MainGUI() throws IOException {
        init();
    }

    private void init() throws IOException {
        Dimension thisDimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Mips Processor");
        this.setResizable(true);

        Container c = this.getContentPane();

        //Setting textArea Attributes
        assemblyTxt.setFont(txtFont);
        regTxt.setFont(txtFont);
        memTxt.setFont(txtFont);

        regTxt.setBorder(new CompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2, true), "Registers"), regTxt.getBorder()));
        regTxt.setColumns(
                20);
        regTxt.setRows(
                1);
        regTxt.setEditable(false);

        memTxt.setBorder(new CompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2, true), "Memory"), memTxt.getBorder()));
        memTxt.setColumns(
                20);
        memTxt.setRows(
                1);
        memTxt.setEditable(
                false);

        assemblyTxt.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 0, 0, 0), new EtchedBorder()));
        assemblyTxt.setColumns(
                60);
        assemblyTxt.setRows(
                33);

        //Buttons Panel Border setting and Button Adding. 
        btnsPnl.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 0, 0, 0), new EtchedBorder()));

        runBtn.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new EtchedBorder()));
        btnsPnl.add(runBtn);
        stepBtn.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new EtchedBorder()));
        btnsPnl.add(stepBtn);
        resetBtn.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new EtchedBorder()));
        btnsPnl.add(resetBtn);
        setValsBtn.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new EtchedBorder()));
        btnsPnl.add(setValsBtn);
        setStartAddBtn.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new EtchedBorder()));
        btnsPnl.add(setStartAddBtn);
        saveBtn.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new EtchedBorder()));
        btnsPnl.add(saveBtn);

        btnsPnl.setLayout(new GridLayout(1, 6));
        c.add(btnsPnl, BorderLayout.NORTH);

        //Right Data Panel content adding.
        dataPnl.setBorder(new EmptyBorder(10, 10, 10, 10));
        dataTabbedPane.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(20, 20, 20, 20), new EtchedBorder()));
        dataTabbedPane.setSize(dataPnl.getMaximumSize());

        JScrollPane memTxtScrl = new JScrollPane(memTxt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //JScrollPane instructionTxtScrl = new JScrollPane(instructionTxt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        dataTabbedPane.addTab("Register", regTxt);
        dataTabbedPane.addTab("Memory", memTxtScrl);
        //dataTabbedPane.addTab("Instruction", instructionTxtScrl);
        dataTabbedPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        dataPnl.add(dataTabbedPane);
        c.add(dataPnl, BorderLayout.EAST);

        //Assembly and X-ray Panel        
        assemblyTabbedPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        assemblyTabbedPane.addTab("Assembler", assemblyTxt);
        assemblyTabbedPane.addTab("View Operation", xraypnl);

        assemblyPnl.setBorder(BorderFactory.createLineBorder(Color.black));
        assemblyPnl.setLayout(new BorderLayout());
        assemblyPnl.add(assemblyTabbedPane);

        c.add(assemblyPnl, BorderLayout.CENTER);

        saveBtn.addActionListener(this);
        runBtn.addActionListener(this);
        stepBtn.addActionListener(this);
        resetBtn.addActionListener(this);
        setValsBtn.addActionListener(this);
        setStartAddBtn.addActionListener(this);
    }

    /**
     * This is the function which defines all the ActionEvents happening by main
     * GUI buttons Contains 6 actions listener for each button.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == runBtn) {

            while (InstructionMemory.PC < InitiateFromGUI.count) {
                if (Clocks == 1000000) {
                    break;
                }
                if (Assembler.instructionMem[PC].getType() != 'L') {
                    Clocks++;
                    xraypnl.clocksLbl.setText("Clocks = " + Clocks);
                    controlUnit();
                    xraypnl.color();
                    xraypnl.writeLinesLabels();
                    registerLodingData();
                    calcRes();
                    data();
                    xraypnl.writeLinesLabels();
                    registerStoringData();
                }
                if (InstructionMemory.PC == 5) {
                    System.out.println("");
                }
                incrementPC();
                clrControl();
            }
            regTxt.setText(regOut());
            memTxt.setText(memOut());

        } else if (e.getSource() == stepBtn) {
            if (InstructionMemory.PC < InitiateFromGUI.count) {
                if (Assembler.instructionMem[PC].getType() != 'L') {
                    Clocks++;
                    xraypnl.clocksLbl.setText("Clocks = " + Clocks);
                    controlUnit();
                    xraypnl.color();
                    xraypnl.writeLinesLabels();
                    registerLodingData();
                    calcRes();
                    data();
                    xraypnl.writeLinesLabels();
                    registerStoringData();
                }

                incrementPC();
                clrControl();
            }
            regTxt.setText(regOut());
            memTxt.setText(memOut());
        } else if (e.getSource() == resetBtn) {
            Clocks = 0;
            xraypnl.clocksLbl.setText("Clocks = " + Clocks);
            xraypnl.clearLinesLabels();
            clrControl();
            clrRegisters();
            clrMemory();
            xraypnl.color();
            PC = 0;
            regTxt.setText(null);
            memTxt.setText(null);
        } else if (e.getSource() == saveBtn) {
            Clocks = 0;
            xraypnl.clocksLbl.setText("Clocks = " + Clocks);
            xraypnl.clearLinesLabels();
            clrControl();
            clrRegisters();
            clrMemory();
            xraypnl.color();
            PC = 0;
            new InitiateFromGUI(MainGUI.this);
            new Registers();
            new DataMemory();
            regTxt.setText(regOut());
            memTxt.setText(memOut());

        } else if (e.getSource() == setValsBtn) {
            JFrame frame = new JFrame("Modify Memory Values");
            frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            frame.setBounds(600, 350, 400, 100);
            frame.setResizable(false);
            Container c1 = frame.getContentPane();

            c1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

            JLabel indexLbl = new JLabel("Index");
            indexLbl.setFont(regTxt.getFont());
            indexLbl.setSize(200, 30);

            JTextArea indexTxtArea = new JTextArea();
            indexTxtArea.setFont(regTxt.getFont());
            indexTxtArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            indexTxtArea.setSize(200, 30);
            indexTxtArea.setColumns(5);

            JLabel dataLbl = new JLabel("Data");
            dataLbl.setFont(regTxt.getFont());
            dataLbl.setSize(200, 30);

            JTextArea dataTxtArea = new JTextArea();
            dataTxtArea.setFont(regTxt.getFont());
            dataTxtArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            dataTxtArea.setSize(200, 30);
            dataTxtArea.setColumns(5);

            JButton okBtn = new JButton("OK");
            okBtn.setFont(regTxt.getFont());
            okBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            okBtn.setSize(200, 30);
            okBtn.addActionListener(
                    new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int index = Integer.parseInt(indexTxtArea.getText()) - DataMemory.getStartAddress() - (InitiateFromGUI.count * 4);
                    byte data = Byte.parseByte(dataTxtArea.getText());

                    if (index <= 400 && data < 256 && data >= -256) {
                        try {
                            dataMem[index] = data;
                        } catch (ArrayIndexOutOfBoundsException x) {
                            JOptionPane.showMessageDialog(frame, "Cannot OverWrite an Instruction.");
                        }
                        regTxt.setText(regOut());
                        memTxt.setText(memOut());
                    }
                }
            });

            c1.add(indexLbl);
            c1.add(indexTxtArea);
            c1.add(dataLbl);
            c1.add(dataTxtArea);
            c1.add(okBtn);
            frame.setVisible(true);

        } else if (e.getSource() == setStartAddBtn) {
            JFrame frame = new JFrame("Set Starting Address");
            frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            frame.setBounds(600, 350, 400, 100);
            frame.setResizable(false);
            Container c1 = frame.getContentPane();

            c1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

            JTextArea strtAddrTxt = new JTextArea();
            strtAddrTxt.setFont(regTxt.getFont());
            strtAddrTxt.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            strtAddrTxt.setSize(200, 30);
            strtAddrTxt.setColumns(5);

            JLabel strtAddrLbl = new JLabel("Address");
            strtAddrLbl.setFont(regTxt.getFont());
            strtAddrLbl.setSize(200, 30);

            JButton okBtn = new JButton("OK");
            okBtn.setFont(regTxt.getFont());
            okBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            okBtn.setSize(200, 30);
            okBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int strtAddr = Integer.parseInt(strtAddrTxt.getText());
                    DataMemory.setStartAddress(strtAddr);
                }
            });
            c1.add(strtAddrLbl);
            c1.add(strtAddrTxt);
            c1.add(okBtn);
            frame.setVisible(true);
        }
    }
}
