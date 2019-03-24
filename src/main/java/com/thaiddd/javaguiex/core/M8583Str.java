package com.thaiddd.javaguiex.core;

/**
 * 8583 message holder, string mode
 *
 */
public class M8583Str 
{
	private String msgBody;
	private String fieldLen;
	private String fieldTPDU;
	private String fieldMsghead;
	private String[] fieldBody;	
	private boolean isFilled;

	public M8583Str()
	{
		isFilled = false;
		fieldBody = new String[65];
	}
	public M8583Str(String msg)
	{
		this();
		msgBody = msg; 		
	}

	public String GetMsgBody()
	{
		return msgBody;
	}
	public String getFieldLen()
	{
		return fieldLen;
	}
	public String getFieldTPDU()
	{
		return fieldTPDU;
	}
	public String getFieldMsghead()
	{
		return fieldMsghead;
	}
	public String[] getFieldBody()
	{
		return fieldBody;
	}
	public boolean isMsgFilled()
	{
		return isFilled;
	}

	public boolean fillWholeMsg(String...str)
	{
		if(null == str)
			return false;
		if(str.length != 68)
			return false;
		fieldLen = str[0];
		fieldTPDU = str[1];
		fieldMsghead = str[2];
		for( int i = 0; i < 65; i++)
		{
			fieldBody[i] = str[i + 3];
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
}
