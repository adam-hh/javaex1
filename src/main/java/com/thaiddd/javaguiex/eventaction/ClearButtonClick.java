package com.thaiddd.javaguiex.eventaction;

import javax.swing.SwingUtilities;
import com.thaiddd.javaguiex.frame.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClearButtonClick extends MouseAdapter
{
    M8583Frame m = M8583Frame.getInstance();
    public void mouseClicked(MouseEvent e)
    {
        SwingUtilities.invokeLater(new Runnable(){
        
            public void run() {
                m.clearInputJText();
            }
        });
        
    }
}