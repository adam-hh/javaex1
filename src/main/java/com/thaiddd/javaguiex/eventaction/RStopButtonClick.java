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
        Dump.stoploop();
    }
}