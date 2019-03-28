package com.thaiddd.javaguiex.eventaction;

import com.thaiddd.javaguiex.core.*;
import com.thaiddd.javaguiex.frame.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                    String[] rlt; 
                    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");                   
                    rlt = NativeC.nativeDecStr(m.getFromJTextInput());
                    if (m.updateTableFieldVal(rlt))
                        m.updateConsole(date.format(new Date()) + " DECODE SUCESS!\n");
                    else
                        {
                            m.updateConsole(date.format(new Date()) + " DECODE FAIL!\n");  
                            m.clearTableFieldVal();
                        }
                            
                    try{
                        String s;
                        BufferedReader in = new BufferedReader(new FileReader("c_console"));                        
                        StringBuilder sb = new StringBuilder(date.format(new Date()) + " MESSAGES FROM DECODER CORE AS FOLLOWING:\n");
                        while((s = in.readLine()) != null)
                            sb.append(s + "\n");
                        in.close();
                        m.updateConsole(sb.toString());
                    }catch(Exception e)
                    {
                        e.printStackTrace();
                    }                                  
                    //m.enableButtonDecode();
                }
            });
            
        }
        //m.enableButtonDecode();
    }
}