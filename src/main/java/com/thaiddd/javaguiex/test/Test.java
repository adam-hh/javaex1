package com.thaiddd.javaguiex.test;

import com.thaiddd.javaguiex.core.*;
import com.thaiddd.javaguiex.frame.*;
/*
Test Class
*/
public class Test
{
    static{
        try{
            System.loadLibrary("native");
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

        M8583Frame fr = M8583Frame.getInstance();
        System.out.println("8583报文解码小工具，目前支持的协议如下:");
        System.out.println("----综合支付平台(立刷)网关接入规范 ( 1.09 ).doc");
        System.out.println("操作规则：");
        System.out.println("在右下角用户输入区填入字符串，然后点击左下角\"开始解码按钮\"");
        System.out.println("解码结果显示在左上角表格视图");
        System.out.println("--------------------------------------分割线--------------------------------------");
    }
}