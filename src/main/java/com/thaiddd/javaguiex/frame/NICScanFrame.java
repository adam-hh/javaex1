package com.thaiddd.javaguiex.frame;

import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.thaiddd.javaguiex.eventaction.ConfirmClick;
import com.thaiddd.javaguiex.eventaction.ScanRealClick;

public class NICScanFrame extends JDialog
{
    private static final long serialVersionUID = 1L;
    private static NICScanFrame NICScanInstance = null;
    public static NICScanFrame getInstance(JFrame owner)
    {
        if(null == NICScanInstance)
        {
            NICScanInstance = new NICScanFrame(owner, "Title", true);
            NICScanInstance.initFrame();
        }        
        return NICScanInstance;
    }    
    public static NICScanFrame getInstance()
    {
        return NICScanInstance;
    }
    private JPanel panelContent = new JPanel();
    private TextAreaMenu textContent = new TextAreaMenu();
    private JLabel labelHint = new JLabel();
    private JTextField textHint = new JTextField();
    private JButton buttonConfirm = new JButton();
    private JScrollPane textContentSrc = new JScrollPane(textContent);
    private JButton buttonScan = new JButton();

    private NICScanFrame()
    {
        setTitle("扫描网卡");
        setLayout(null);
    }
    private NICScanFrame(Frame owner, String title, boolean modal)
    {
        super(owner, title, modal);
        setTitle("扫描网卡");
        setLayout(null);
    }
    public void initFrame()
    {
        setSize(380, 240);
        setLocation(560,400);
        add(panelContent);
        panelContent.setBounds(10, 10, 370, 200);
        panelContent.setLayout(null);

        panelContent.add(textContentSrc);
        textContentSrc.setBounds(0, 0, 370, 160);
        panelContent.add(labelHint);
        labelHint.setBounds(70, 170, 130, 25);

        panelContent.add(textHint);
        textHint.setBounds(200, 170, 50, 25);
        panelContent.add(buttonConfirm);
        buttonConfirm.setBounds(260, 170, 60, 25);

        panelContent.add(buttonScan);
        buttonScan.setBounds(0, 170, 60, 25);

        buttonScan.setText("扫描");
        buttonConfirm.setText("确定");
        labelHint.setText("在右边填网卡序号：");

        buttonScan.addMouseListener(new ScanRealClick());
        buttonConfirm.addMouseListener(new ConfirmClick());

        textContent.setEditable(false);

        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    public void SetTextContent(String s)
    {
        textContent.append(s);
    }
    public void ClearTextContent()
    {
        textContent.setText(null);
    }
    public int GetContentFromTextField()
    {
        return Integer.parseInt(textHint.getText());
    }

}