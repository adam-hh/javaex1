package com.thaiddd.javaguiex.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.ScrollPane;
import java.io.File;
import java.io.PrintStream;
import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.stream.BaseStream;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.thaiddd.javaguiex.eventaction.ClearButtonClick;
import com.thaiddd.javaguiex.eventaction.DecodeButtonClick;


public class M8583Frame extends JFrame implements BaseFrame
{
    private static final long serialVersionUID = 1L;
    private static final String ver = "Ver 2.0";
    private static M8583Frame m8583Frame = null;
    public static M8583Frame getInstance()
    {
        if(null == m8583Frame)
        {
            m8583Frame = new M8583Frame();
            m8583Frame.initFrame();
        }        
        return m8583Frame;
    }
    /* 
    GUI elements, level 1, panels
    */
    //private JPanel left = new JPanel();
    //private JPanel right = new JPanel();
    private JPanel leftTop = new JPanel();
    private JPanel leftBottom = new JPanel();
    private JPanel rightTop = new JPanel();
    private JPanel rightBottom = new JPanel();
    private MyPicPanel leftBottom_center;
    /*
    GUI elements, level 2, labels
    */
    private JLabel labelFieldVal = new JLabel();
    private JLabel labelControlPanel = new JLabel();
    private JLabel labelConsole = new JLabel();
    private JLabel labelInput = new JLabel();
    /*
    GUI elements, level 2, buttons
    */
    private JButton buttonDecode = new JButton();
    private JButton buttonClear = new JButton();
    /*
    GUI elememts, level 2, tabel and text areas
    */
    Object[][] tableContent = new Object[68][2];
    Object[] tableColumnName = {"域", "值"};
    private JTable tabelFieldVal = new JTable(tableContent, tableColumnName);
    private JScrollPane tabelFieldValScp = new JScrollPane(tabelFieldVal);
    private JTextArea jtextConsole = new JTextArea();
    private JScrollPane jtextConsoleScp = new JScrollPane(jtextConsole);
    private JTextArea jtextInput = new JTextArea();
    private JScrollPane jtextInputScp = new JScrollPane(jtextInput);

    private M8583Frame()
    {
        setTitle("8583 报文解码 " + ver);
        setLayout(null);
        addPanels();
        setPanelsBounds();
    }

    public void initFrame()
    {
        initLayouts();
        initDatas();
        initAction();
        setMain();
    }

    public void addPanels()
    {
        add(leftTop);
        add(leftBottom);
        add(rightTop);
        add(rightBottom);
    }
    public void setPanelsBounds()
    {
        leftTop.setBounds(10, 10, 380, 400);
        leftBottom.setBounds(10, 410, 380, 190);
        rightTop.setBounds(410, 10, 380,400);
        rightBottom.setBounds(410, 410, 380, 190);
    }
    
    public void initLayouts()
    {
        leftTop.setLayout(new BorderLayout());
        leftTop.add(labelFieldVal, BorderLayout.NORTH);
        leftTop.add(tabelFieldValScp, BorderLayout.SOUTH);
        tabelFieldValScp.setPreferredSize(new Dimension(380, 380));

        rightTop.setLayout(new BorderLayout());
        rightTop.add(labelConsole, BorderLayout.NORTH);
        rightTop.add(jtextConsoleScp, BorderLayout.SOUTH);   
        jtextConsoleScp.setPreferredSize(new Dimension(380, 380));

        leftBottom.setLayout(new BorderLayout());
        leftBottom.add(labelControlPanel, BorderLayout.NORTH);
        leftBottom.add(buttonDecode, BorderLayout.SOUTH);
        try{
            ImageIcon icon = new ImageIcon(M8583Frame.class.getResource("MAP.png"));
            leftBottom_center = new MyPicPanel(icon.getImage(), 380, 160);
        }catch(Exception e){
            e.printStackTrace();
        }
        leftBottom.add(leftBottom_center, BorderLayout.CENTER);

        rightBottom.setLayout(new BorderLayout());
        rightBottom.add(labelInput, BorderLayout.NORTH);
        rightBottom.add(jtextInputScp, BorderLayout.CENTER);
        rightBottom.add(buttonClear, BorderLayout.SOUTH);
    }
    public void initDatas()
    {
        labelConsole.setText("控制台区域");
        labelControlPanel.setText("操作面板");
        labelInput.setText("用户输入区");
        labelFieldVal.setText("当前报文数据域视图");

        buttonDecode.setText("开始解码");
        buttonClear.setText("清除");

        tabelFieldVal.getModel().setValueAt("Len", 0, 0);
        tabelFieldVal.getModel().setValueAt("TPDU", 1, 0);
        tabelFieldVal.getModel().setValueAt("MsgHead", 2, 0);
        for( int i = 0; i<65; i++)
        {
            tabelFieldVal.getModel().setValueAt("Field" + i, i + 3, 0);
        }

        jtextInput.setLineWrap(true);
        jtextConsole.setLineWrap(true);
        jtextConsole.setEditable(false);

        JTextAreaOutputStream out = new JTextAreaOutputStream (jtextConsole);
        System.setOut (new PrintStream(out)); 
        System.setErr(new PrintStream(out));
    }
    public void initAction()
    {
        buttonDecode.addMouseListener(new DecodeButtonClick());
        buttonClear.addMouseListener(new ClearButtonClick());
    }
    public void setMain()
    {
        setSize(800, 640);
        setLocation(200,200);

        ImageIcon icon = new ImageIcon(M8583Frame.class.getResource("NEXGO.png"));
        setIconImage(icon.getImage());

        setResizable(false);
        setVisible(true);
        //leftBottom.setVisible(true);
        //leftBottom_center.repaint();
        //paintImage();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String getFromJTextInput()
    {
        return jtextInput.getText();
    }
    public boolean updateTableFieldVal(String[] str)
    {
        if((null == str) || (68 != str.length))
            return false;
        for(int i = 0; i<68; i++)
        { 
            tabelFieldVal.getModel().setValueAt(str[i], i, 1);
        }       
        return true; 
    }
    public void clearTableFieldVal()
    {
        for(int i = 0; i<68; i++)
        { 
            tabelFieldVal.getModel().setValueAt(null, i, 1);
        }
    }
    public void updateConsole(String str)
    {
        jtextConsole.append(str);
    }
    public void clearInputJText()
    {
        jtextInput.setText(null);
    }
    public void disableButtonDecode()
    {
        buttonDecode.setEnabled(false);
    }
    public void enableButtonDecode()
    {
        buttonDecode.setEnabled(true);
    }
}

class MyPicPanel extends JPanel{
    
    private static final long serialVersionUID = 1L;
    Image image = null;
    public MyPicPanel(Image m, int width, int height)
    {
        super();
        image = m.getScaledInstance(width, height, Image.SCALE_DEFAULT);
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        try {
            g.drawImage(image, 0, 0, null);
        }catch(Exception e){
            e.printStackTrace();
        }        
    }
}