package com.thaiddd.javaguiex.eventaction;

import com.thaiddd.javaguiex.core.*;
import com.thaiddd.javaguiex.frame.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class RStopButtonClick extends MouseAdapter
{
    M8583Frame m = M8583Frame.getInstance();
    public void mouseClicked(MouseEvent ev) {
        System.out.println("Trying stop...");
        if(1 == Dump.stoploop())
        //Dump.stoploop();
            m.disableRStopButton();
        else
            System.out.println("try stopping failed,no live capture is onging.");
    }
}