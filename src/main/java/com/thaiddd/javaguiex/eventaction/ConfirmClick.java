package com.thaiddd.javaguiex.eventaction;

import com.thaiddd.javaguiex.core.*;
import com.thaiddd.javaguiex.frame.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;
public class ConfirmClick extends MouseAdapter
{
    final M8583Frame mm = M8583Frame.getInstance();
    final NICScanFrame m = NICScanFrame.getInstance();       
    public void mouseClicked(MouseEvent e) 
    {
        String st = m.GetContentFromTextField();
        int in = 0;
        try{
            in = Integer.parseInt(st);
        }catch(Exception ep)
        {
            //ep.printStackTrace();
            m.SetTextContent("Illegal Input!\n");
            return;
        }
        if(1 == Dump.openDev(in))
        {
            m.SetTextContent("Open " +m.GetContentFromTextField() + " sucessed\n");
            System.out.println("Open No." +m.GetContentFromTextField() + " device sucessed");
            mm.disableScanButton();
            m.dispose();
        }
        else   
        {
            m.SetTextContent("Open " +m.GetContentFromTextField() + " failed, check the index\n");
            //m.setVisible(false);
        }
            
    }
}