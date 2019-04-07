package com.thaiddd.javaguiex.eventaction;

import com.thaiddd.javaguiex.core.*;
import com.thaiddd.javaguiex.frame.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;
public class TrimButtonClick extends MouseAdapter{
    M8583Frame m = M8583Frame.getInstance();

    // @Override
    public void mouseClicked(MouseEvent ev){
        SwingUtilities.invokeLater(new Runnable(){
            
            public void run() {
                m.trimInput();
            }
        });
    }
}