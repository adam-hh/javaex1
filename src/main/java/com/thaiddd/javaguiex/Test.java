package com.thaiddd.javaguiex;
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
        M8583Str dec = new M8583Str();  
        if((null == args) || (args.length == 0))
        {
            System.out.println("heil");
            return;    
        }         
        dec.fillWholeMsg(NativeC.nativeDecStr(args[0]));       

        M8583Bin decb = new M8583Bin();
        byte[] bt = new byte[4];
        decb.fillWholeMsg(NativeC.nativeDecBin(bt));
    }
}