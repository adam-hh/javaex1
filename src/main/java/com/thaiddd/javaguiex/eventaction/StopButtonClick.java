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
public class StopButtonClick extends MouseAdapter
{
    M8583Frame m = M8583Frame.getInstance();
    public void mouseClicked(MouseEvent ev)
    {
        new Thread(new Runnable(){            
            public void run() {
                m.disableStopButton();
                TcpDataBlock db = new TcpDataBlock();
                db.extraInfo = new byte[30];
                db.ipSrc = new byte[4];
                db.ipDst = new byte[4];     
                while(true)
                {
                    int rlt = Dump.readFromUserBuff(db);
                    if(1 == rlt)
                    {
                        m.setMessage(db.toString() + "\n");
                        for(int i=0; i<db.extraInfo.length; i++)
                            db.extraInfo[i] = 0;
                        db.data = null;
                    }
                    if(0 == rlt)
                    {
                        try{
                            TimeUnit.MILLISECONDS.sleep(1000);
                        }catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                        continue;
                    }                        
                    if(-1 == rlt)
                    {
                        m.setMessage("readFromUserBuff fatal error occured." + "\n");
                        m.enableStopButton();
                        return;
                    }
                }
            }
        }).start();  
    }
}