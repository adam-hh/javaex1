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

import javax.swing.JDialog;
import javax.swing.SwingUtilities;

public class ScanRealClick extends MouseAdapter
{
    final NICScanFrame m = NICScanFrame.getInstance();       
    public void mouseClicked(MouseEvent e) 
    {
        final NICDevice nd[] = new NICDevice[15];
            try{
                Dump.devList(nd);
            }catch(Exception ee){
                ee.printStackTrace();
            }
            m.ClearTextContent();
            for(int i = 0; i<nd.length; i++)
                if(nd[i] != null)
                {
                    m.SetTextContent((i+1) + ". " + nd[i].name + "\n");
                    if(nd[i].description != null)
                        m.SetTextContent("\t" + nd[i].description + "\n");
                }
                     
    }
}