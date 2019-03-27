package com.thaiddd.javaguiex.eventaction;

import com.thaiddd.javaguiex.core.*;
import com.thaiddd.javaguiex.frame.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;

public class DecodeButtonClick extends MouseAdapter {
    M8583Frame m = M8583Frame.getInstance();

    // @Override
    public void mouseClicked(MouseEvent e) {
        //m.disableButtonDecode();
        if (null != m.getFromJTextInput()) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    //String[] rlt = new String[68];
                    String[] rlt;
                    System.out.println("debug tag0");
                    if(m.getFromJTextInput() == null)
                        return;
                    rlt = NativeC.nativeDecStr(m.getFromJTextInput());
                    if (m.updateTableFieldVal(rlt))
                        m.updateConsole("decode sucess\n");
                    else
                        m.updateConsole("decode failed\n");                    
                    //m.enableButtonDecode();
                }
            });
            
        }
        //m.enableButtonDecode();
    }
}