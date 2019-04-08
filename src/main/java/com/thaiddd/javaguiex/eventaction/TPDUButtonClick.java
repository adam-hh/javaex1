package com.thaiddd.javaguiex.eventaction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import com.thaiddd.javaguiex.frame.M8583Frame;

public class TPDUButtonClick extends MouseAdapter
{
    M8583Frame m = M8583Frame.getInstance();
    // @Override
    public void mouseClicked(MouseEvent ev) {
        SwingUtilities.invokeLater(new Runnable(){            
            public void run() {
                String pa = m.getFromTPDU();
                if((pa == null) || (pa.length() < 5))
                {
                    System.out.println("illgal TPDU:" + pa);
                    return;
                }
                //m.trimInput();                
                char[] achar = m.getFromJTextInput().toCharArray();
                StringBuilder sb = new StringBuilder();
                for(char c: achar)
                {
                    if((c != 0x0D)&&(c != 0x0A)&&(c != 0x09)&&(c != 0x20))
                    {
                        sb.append(c);
                    }
                }
                String mpa = sb.toString();
                Pattern p = Pattern.compile(pa);
                Matcher m = p.matcher(mpa);
                int count = 0;
                while(m.find()) {
                    count++;
                    System.out.println("TPDU " + pa +" 匹配成功次数 "+count);
                    System.out.println("start(): "+m.start());
                    System.out.println("end(): "+m.end());
                }
                if(count == 0)
                    System.out.println("TPDU测试失败");
            }
        });
    }
}