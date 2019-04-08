package com.thaiddd.javaguiex.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.ScrollPane;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.PrintStream;
import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.stream.BaseStream;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.thaiddd.javaguiex.eventaction.ClearButtonClick;
import com.thaiddd.javaguiex.eventaction.DecodeButtonClick;
import com.thaiddd.javaguiex.eventaction.LunchButtonClick;
import com.thaiddd.javaguiex.eventaction.RStopButtonClick;
import com.thaiddd.javaguiex.eventaction.ScanButtonClick;
import com.thaiddd.javaguiex.eventaction.SpaceButtonClick;
import com.thaiddd.javaguiex.eventaction.StopButtonClick;
import com.thaiddd.javaguiex.eventaction.TPDUButtonClick;
import com.thaiddd.javaguiex.eventaction.TrimButtonClick;


public class M8583Frame extends JFrame implements BaseFrame
{
    private static final long serialVersionUID = 1L;
    private static final String ver = "Ver 3.0";
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
    private JPanel rTop = new JPanel();
    private JPanel rBot = new JPanel();
    private JPanel midBottom = new JPanel();    
    /*
    GUI elements, level 2, labels
    */
    private JLabel labelFieldVal = new JLabel();
    private JLabel labelControlPanel = new JLabel();
    private JLabel labelConsole = new JLabel();
    private JLabel labelInput = new JLabel();
    private JLabel labelMessage = new JLabel();
    private JLabel labelMonitor = new JLabel();
    private JLabel labelTPDU = new JLabel();
    private JLabel labelPort = new JLabel();

    /*
    GUI elements, textfield
    */
    private JTextField textTPDU = new JTextField();
    private JTextField textPort = new JTextField();
    /*
    GUI elements, level 2, buttons
    */
    private JButton buttonDecode = new JButton();
    private JButton buttonClear = new JButton();
    private JButton buttonTrim = new JButton();
    private JButton buttonSpace = new JButton();
    private JButton buttonTPDU = new JButton();
    private JButton buttonLunch = new JButton();
    private JButton buttonStop = new JButton();
    private JButton buttonScan = new JButton();
    private JButton buttonRStop = new JButton();
    /*
    GUI elememts, level 2, tabel and text areas
    */
    Object[][] tableContent = new Object[68][2];
    Object[] tableColumnName = {"域", "值"};
    private JTable tabelFieldVal = new JTable(tableContent, tableColumnName);
    private JScrollPane tabelFieldValScp = new JScrollPane(tabelFieldVal);
    private TextAreaMenu jtextConsole = new TextAreaMenu();
    private JScrollPane jtextConsoleScp = new JScrollPane(jtextConsole);
    private TextAreaMenu jtextInput = new TextAreaMenu();
    private JScrollPane jtextInputScp = new JScrollPane(jtextInput);
    private TextAreaMenu jtextMessage = new TextAreaMenu();
    private JScrollPane jtextMessageScp = new JScrollPane(jtextMessage);

    //Listener
    private MouseListener mScan;// = new ScanButtonClick();
    private MouseListener mSetScan; // set on filter button
    private MouseListener mLunch; //set on lunch button

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
        add(rTop);
        add(rBot);
    }
    public void setPanelsBounds()
    {
        leftTop.setBounds(10, 10, 330, 440);
        leftBottom.setBounds(10, 450, 330, 215);
        rightTop.setBounds(350, 10, 330,440);
        rightBottom.setBounds(350, 450, 330, 215);
        rTop.setBounds(690, 10, 330, 440);
        rBot.setBounds(690, 450, 330, 215);
    }
    
    public void initLayouts()
    {
        leftTop.setLayout(new BorderLayout());
        leftTop.add(labelFieldVal, BorderLayout.NORTH);
        leftTop.add(tabelFieldValScp, BorderLayout.SOUTH);
        tabelFieldValScp.setPreferredSize(new Dimension(330, 420));

        rightTop.setLayout(new BorderLayout());
        rightTop.add(labelConsole, BorderLayout.NORTH);
        rightTop.add(jtextConsoleScp, BorderLayout.SOUTH);   
        jtextConsoleScp.setPreferredSize(new Dimension(330, 420));

        leftBottom.setLayout(new BorderLayout());
        leftBottom.add(labelControlPanel, BorderLayout.NORTH);
        leftBottom.add(buttonDecode, BorderLayout.SOUTH);
        try{
            ImageIcon icon = new ImageIcon(M8583Frame.class.getResource("MAP.png"));
            leftBottom_center = new MyPicPanel(icon.getImage(), 330, 150);
        }catch(Exception e){
            e.printStackTrace();
        }
        leftBottom.add(leftBottom_center, BorderLayout.CENTER);

        rightBottom.setLayout(new BorderLayout());
        rightBottom.add(labelInput, BorderLayout.NORTH);
        rightBottom.add(jtextInputScp, BorderLayout.CENTER);

        rTop.setLayout(new BorderLayout());
        rTop.add(labelMessage, BorderLayout.NORTH);
        rTop.add(jtextMessageScp, BorderLayout.SOUTH);
        jtextMessageScp.setPreferredSize(new Dimension(330, 420));

        rBot.setLayout(null);
        rBot.add(labelMonitor);
        labelMonitor.setBounds(0, 0, 60, 20);
        rBot.add(buttonLunch);
        buttonLunch.setBounds(0, 90, 155,25);
        rBot.add(buttonStop);
        buttonStop.setBounds(160, 90, 155, 25);
        rBot.add(buttonRStop);
        buttonRStop.setBounds(80, 120, 155, 25);
        rBot.add(labelPort);
        labelPort.setBounds(0, 60, 160, 25);
        rBot.add(textPort);
        textPort.setBounds(185, 60, 130, 25);
        rBot.add(buttonScan);
        buttonScan.setBounds(0, 25, 320, 25);

        rightBottom.add(midBottom, BorderLayout.SOUTH);
        midBottom.setPreferredSize(new Dimension(330, 60));        
        midBottom.add(labelTPDU);
        midBottom.add(textTPDU);
        labelTPDU.setPreferredSize(new Dimension(100, 23));
        textTPDU.setPreferredSize(new Dimension(220, 23));

        midBottom.add(buttonTPDU);        
        midBottom.add(buttonTrim);
        midBottom.add(buttonSpace);  
        midBottom.add(buttonClear);      
    }
    public void initDatas()
    {
        labelConsole.setText("控制台区域");
        labelControlPanel.setText("操作面板");
        labelInput.setText("用户输入区");
        labelFieldVal.setText("当前报文数据域视图");
        labelMessage.setText("报文捕获");
        labelMonitor.setText("监控设置");
        labelTPDU.setText("在这里输入TPDU:");
        labelPort.setText("端口号：");
        textPort.setText("443");

        buttonDecode.setText("开始解码");
        buttonClear.setText("清除");
        buttonTrim.setText("去空格");
        buttonSpace.setText("加空格");
        buttonTPDU.setText("测试TPDU");
        buttonLunch.setText("设置过滤器");
        buttonStop.setText("启动抓包");
        buttonScan.setText("扫描网卡（启动抓包前要先打开活动网卡");
        buttonRStop.setText("停止");

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
        jtextMessage.setEditable(false);        

        JTextAreaOutputStream out = new JTextAreaOutputStream (jtextConsole);
        System.setOut (new PrintStream(out)); 
        System.setErr(new PrintStream(out));

        jtextConsole.setFont(new Font("宋体", Font.BOLD, 11));
        jtextMessage.setFont(new Font("宋体", Font.BOLD, 11));
        jtextInput.setFont(new Font("宋体", Font.BOLD, 10));
    }
    public void initAction()
    {
        buttonDecode.addMouseListener(new DecodeButtonClick());
        buttonClear.addMouseListener(new ClearButtonClick());
        mScan = new ScanButtonClick();
        buttonScan.addMouseListener(mScan);
        mLunch = new LunchButtonClick();
        buttonLunch.addMouseListener(mLunch);
        mSetScan = new StopButtonClick();
        buttonStop.addMouseListener(mSetScan);
        buttonTrim.addMouseListener(new TrimButtonClick());
        buttonSpace.addMouseListener(new SpaceButtonClick());
        buttonRStop.addMouseListener(new RStopButtonClick());
        buttonTPDU.addMouseListener(new TPDUButtonClick());
    }
    public void setMain()
    {
        setSize(1035, 700);
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
    public void setMessage(String s)
    {
        jtextMessage.append(s);
    }
    public void disableStopButton()
    {
        buttonStop.setEnabled(false);
        buttonStop.removeMouseListener(mSetScan);
    }
    public void enableStopButton()
    {
        buttonStop.setEnabled(true);
        buttonStop.addMouseListener(mSetScan);
    }
    public void disableLunchButton()
    {
        buttonLunch.setEnabled(false);
        buttonLunch.removeMouseListener(mLunch);
    }
    public void enableLunchButton()
    {
        buttonLunch.setEnabled(true);
        buttonLunch.addMouseListener(mLunch);
    }
    public void enableScanButton()
    {
        buttonScan.setEnabled(true);
    }
    public void disableScanButton()
    {
        buttonScan.setEnabled(false);
        buttonScan.removeMouseListener(mScan);
    }
    public String getFilter()
    {
        return textPort.getText();
    }
    public void trimInput()
    {
        char[] achar = jtextInput.getText().toCharArray();
        StringBuilder sb = new StringBuilder();
        //int i = 0;
        for(char c: achar)
        {                  
            if((c != 0x0D)&&(c != 0x0A)&&(c != 0x09)&&(c != 0x20))
            {                
                sb.append(c);
                // if(((i+1)%32 == 0)&&(i!=0))
                //     sb.append("\n");
                //i++; 
            }
        }
        //sb.append("\n");
        jtextInput.setText(sb.toString());
    }
    public void spaceInput()
    {
        StringBuilder sb = new StringBuilder();
        String str = jtextInput.getText();
        char[] achar = str.toCharArray();
        int i = 0;
        for(char c: achar)
        {                  
            if((c != 0x0D)&&(c != 0x0A)&&(c != 0x09)&&(c != 0x20))
            {
                i++; 
                sb.append(c);
                if(i%2 == 0)
                    sb.append(" ");
                if((i%32 == 0) &&(i!=1))
                    sb.append("\n");
            }
        }
        jtextInput.setText(sb.toString());
    }
    public String GetUserInput()
    {
        return jtextInput.getText();
    }
    public String getFromTPDU()
    {
        return textTPDU.getText();
    }
    public void UpdateUserInput(String s)
    {
        jtextInput.setText(s);
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