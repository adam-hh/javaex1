package com.thaiddd.javaguiex.core;

/**
 * 8583 message holder, binary mode
 *
 */
public class M8583Bin
{
    private byte[] msgBody;
    private byte[] fieldLen;
    private byte[] fieldTPDU;
    private byte[] fieldMsghead;
    private byte[][] fieldBody;
    private boolean isFilled;

    public M8583Bin()
    {
        fieldLen = new byte[2];
        fieldTPDU = new byte[5];
        fieldMsghead = new byte[6];
        fieldBody = new byte[65][];
        isFilled = false;
    }
    public M8583Bin(byte[] msg)
    {
        this();
        msgBody = msg;
    }

    public byte[] getMsgBody()
    {
        return msgBody;
    }
    public byte[] getFieldLen()
    {
        return fieldLen;
    }
    public byte[] getFieldTPDU()
    {
        return fieldTPDU;
    }
    public byte[] getFieldMsghead()
    {
        return fieldMsghead;
    }
    public byte[][] getFieldBody()
    {
        return fieldBody;
    }
    public boolean isMsgFilled() 
    {
        return isFilled;
    }

    public boolean fillWholeMsg(byte[][] bt)
    {
        if(null == bt)
            return false;
        if(68 != bt.length)
            return false;
        fieldLen = bt[0];
        fieldTPDU = bt[1];
        fieldMsghead = bt[2];
        for(int i = 0; i < fieldBody.length; i++)
        {
            fieldBody[i] = bt[i+3];
        }
        isFilled = true;
        return true;
    }

    public String toString()
    {
        if(isFilled)
        {
            StringBuilder sb = new StringBuilder();
            sb.append("fieldLen:" + fieldLen + "\n");
			sb.append("fieldTPDU:" + fieldTPDU + "\n");
			sb.append("fieldMsghead:" + fieldMsghead + "\n");
			for(int i = 0; i < fieldBody.length; i++)
			{
				sb.append("field" + i + ":" + fieldBody[i] + "\n");
			}
			return sb.toString();
        }
        return "empty!\n";
    }
    public String toHexviewString()
    {
        if(isFilled)
        {
            StringBuilder sb = new StringBuilder();
            sb.append("fieldLen" + NativeC.byteArrayToHexview(fieldLen) + "\n");
            sb.append("fieldTPDU" + NativeC.byteArrayToHexview(fieldTPDU) + "\n");
            sb.append("fieldMsghead" + NativeC.byteArrayToHexview(fieldMsghead) + "\n");
            for(int i = 0; i<fieldBody.length; i++)
            {
                sb.append("field" + i + ":" + NativeC.byteArrayToHexview(fieldBody[i]) + "\n");
            }
            return sb.toString();
        }
        return "empty!\n";
    }
}