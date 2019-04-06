package com.thaiddd.javaguiex.test;

import java.util.concurrent.TimeUnit;

import com.thaiddd.javaguiex.core.*;
import com.thaiddd.javaguiex.frame.*;
/*
Test Class
*/
public class Test
{
    static{
        try{
            //System.loadLibrary("native");
            System.loadLibrary("test");
        } catch(Exception e){
            System.out.println("load failed");
        }
        
    }
    public static void main(String args[])
    {
        // M8583Str dec = new M8583Str();  
        // if((null == args) || (args.length == 0))
        // {
        //     System.out.println("heil");
        //     return;    
        // }         
        // dec.fillWholeMsg(NativeC.nativeDecStr(args[0]));       

        // M8583Bin decb = new M8583Bin();
        // byte[] bt = new byte[4];
        // decb.fillWholeMsg(NativeC.nativeDecBin(bt));

        // M8583Frame fr = M8583Frame.getInstance();
        // System.out.println("8583报文解码小工具，目前支持的协议如下:");
        // System.out.println("----综合支付平台(立刷)网关接入规范 ( 1.09 ).doc");
        // System.out.println("操作规则：");
        // System.out.println("在右下角用户输入区填入字符串，然后点击左下角\"开始解码按钮\"");
        // System.out.println("解码结果显示在左上角表格视图");
        // System.out.println("--------------------------------------分割线--------------------------------------");

        NICDevice[] ndv = new NICDevice[10];
        try{
            Dump.devList(ndv);
        }catch(Exception e){
            e.printStackTrace();
        }
        for(NICDevice n: ndv)
            if(n != null)
                System.out.println(n);
        if(1 == Dump.openDev(6))
            System.out.println("openDev 6 sucess.");
        if(1 == Dump.setFilter("tcp port 443"))
            System.out.println("setFilter tcp port 443 sucess");        
        
        new Thread(new Runnable(){        
            public void run() {
                while(true)
                {
                    System.out.println("loop(0) begin:");
                    int it = Dump.loop(0);
                    System.out.println("loop(0) returned:" + it);
                    try{
                        TimeUnit.SECONDS.sleep(3);
                    }catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }                
            }
        }).start();        

        try{
            TimeUnit.SECONDS.sleep(1);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        new Thread(new Runnable(){            
            public void run() {
                TcpDataBlock db = new TcpDataBlock();
                db.extraInfo = new byte[30];
                db.ipSrc = new byte[4];
                db.ipDst = new byte[4];     
                while(true)
                {
                    int rlt = Dump.readFromUserBuff(db);
                    if(1 == rlt)
                    {
                        System.out.println(db);
                        for(int i=0; i<db.extraInfo.length; i++)
                            db.extraInfo[i] = 0;
                        db.data = null;
                    }                        
                    if(0 == rlt)
                        continue;
                    if(-1 == rlt)
                    {
                        System.out.println("readFromUserBuff fatal error occured.");
                        System.exit(1);
                    } 
                }
                                   
            }            
        }).start();   
        
        try{
            TimeUnit.SECONDS.sleep(10);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        new Thread(new Runnable(){
        
            public void run() {
                while(true)
                {
                    Dump.stoploop();
                    System.out.println("stoploop after 10s.");
                    try{
                        TimeUnit.SECONDS.sleep(10);
                    }catch(Exception e)
                    {
                        e.printStackTrace();
                }
                }                
            }
        }).start();
    }
}