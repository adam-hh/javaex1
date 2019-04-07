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
public class LunchButtonClick extends MouseAdapter
{
    M8583Frame m = M8583Frame.getInstance();
    public void mouseClicked(MouseEvent ee){         
        new Thread(new Runnable(){        
            public void run() {                
                int in = m.getFilter();
                if(0 == in)
                {
                    System.out.println("illegal port number:" + in);
                    return;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("tcp port ");
                sb.append(in & 0xFFFF);                
                if(1 == Dump.setFilter(sb.toString()))
                {
                    m.disableLunchButton();
                    System.out.println("setFilter:" +sb.toString());
                }                    
                else
                {
                    System.out.println("setFilter error");
                    return;
                }
                
                System.out.println("loop(0) begin:");
                int it = Dump.loop(0);
                System.out.println("loop(0) returned:" + it);                
                try{
                    TimeUnit.SECONDS.sleep(3);
                }catch(Exception e1)
                {
                    e1.printStackTrace();
                }               
            }
        }).start();     
    }
}