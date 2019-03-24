package com.thaiddd.javaguiex.eventaction;

import com.thaiddd.javaguiex.core.*;
import com.thaiddd.javaguiex.frame.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

public class DecodeButtonClick extends MouseAdapter
{
    M8583Frame m = M8583Frame.getInstance();
    //@Override
    public void mouseClicked(MouseEvent e)
    {        
        if(null != m.getFromJTextInput())
        {
            SwingUtilities.invokeLater( new Runnable(){            
                public void run() {
                    String[] rlt = new String[68];
                    rlt = NativeC.nativeDecStr(m.getFromJTextInput());
                    if(m.updateTableFieldVal(rlt))
                        m.updateConsole("decode sucess\n");
                    else    
                        m.updateConsole("decode failed\n");
                }
            });
            
        }
    }
}