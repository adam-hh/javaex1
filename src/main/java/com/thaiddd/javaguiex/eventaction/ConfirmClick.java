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
        if(1 == Dump.openDev(m.GetContentFromTextField()))
        {
            m.SetTextContent("Open " +m.GetContentFromTextField() + " sucessed\n");
            m.setVisible(false);
        }            
        else   
        {
            m.SetTextContent("Open " +m.GetContentFromTextField() + " failed, check the index\n");
            //m.setVisible(false);
        }
            
    }
}