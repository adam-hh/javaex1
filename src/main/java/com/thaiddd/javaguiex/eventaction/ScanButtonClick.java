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

public class ScanButtonClick extends MouseAdapter
{
    final M8583Frame m = M8583Frame.getInstance();       
    public void mouseClicked(MouseEvent e) 
    {
        final NICScanFrame nf = NICScanFrame.getInstance(m); 
        nf.releaseInstance();
        //m.disableScanButton();
        //nf.setVisible(true);      
    }
}