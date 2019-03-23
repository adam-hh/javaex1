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
        if(1 == args.length)
            dec.fillWholeMsg(NativeC.nativeDecStr(args[0]));
        else
            System.out.println("no input");

        M8583Bin decb = new M8583Bin();
        byte[] bt = new byte[4];
        decb.fillWholeMsg(NativeC.nativeDecBin(bt));
    }
}