package com.thaiddd.javaguiex.core;

/**
 * NativeC interface
 *
 */
public class NativeC 
{
    private NativeC()
    {}
    public static native String[] nativeDecStr(String msgBody);
    public static native byte[][] nativeDecBin(byte[] msgBody); 
    /*
    byte to hexview string ,with even align
    */
    public static String byteToHexview(byte bt)
    {
        int val = bt & 0xFF;
        StringBuffer sb = new StringBuffer();
        String rlt = Integer.toHexString(val);
        if(rlt.length() < 2)
            sb.append("0");
        sb.append(rlt);
        return sb.toString();
    }   
     /*
    bytes to hexview string ,with even align
    */
    public static String byteArrayToHexview(byte[] bt)
    {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i<bt.length; i++)
        {
            int val = bt[i] & 0xFF;        
            String rlt = Integer.toHexString(val);
            if(rlt.length() == 1)
                sb.append("0");
            sb.append(rlt);
        }        
        return sb.toString();
    }   
}
