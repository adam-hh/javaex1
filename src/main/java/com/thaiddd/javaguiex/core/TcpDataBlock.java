package com.thaiddd.javaguiex.core;

public class TcpDataBlock
{
    public byte[] extraInfo;
    public byte[] ipSrc;
    public byte[] ipDst;
    public short portSrc;
    public short portDst;
    public long dataLen;
    public byte[] data;
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for(byte b : extraInfo)
        {
            sb.append(String.format("%c", b)); 
            if(b == 0)
            {
                sb.append("\n");
                break;
            }
        }                   
        sb.append(String.format("%d.%d.%d.%d:%d", (int)(0xFF & ipSrc[0]), (int)(0xFF & ipSrc[1]),(int)(0xFF & ipSrc[2]), (int)(0xFF & ipSrc[3]), (int)(0xFFFF & portSrc)) + " --> " + 
            String.format("%d.%d.%d.%d:%d", (int)(0xFF &ipDst[0]), (int)(0xFF & ipDst[1]), (int)(0xFF & ipDst[2]), (int)(0xFF & ipDst[3]), (int)(0xFFFF & portDst)) + "\n");
        sb.append("Data:\n");
        sb.append(NativeC.byteArrayToHexview(data) + "\n");

        return sb.toString();
    }
}