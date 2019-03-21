package com.thaiddd.javaguiex;

/**
 * Hello world!
 *
 */
public class Msgdec 
{
    public Msgdec()
    {
    	fieldLen = "";
		fieldTPDU = "";
		fieldMsghead = "";
		fieldBody = new String[65];
		isFinished = false;
		for(String i : fieldBody)
			i = "";
    }
	private String fieldLen;
	private String fieldTPDU;
	private String fieldMsghead;
	private String fieldBody[];
	private boolean isFinished;
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("fieldLen:" + fieldLen + "\n");
		sb.append("fieldTPDU:" + fieldTPDU + "\n");
		sb.append("fieldMsghead:" + fieldMsghead + "\n");
		for(int i = 0; i < fieldBody.length; i++)
			sb.append("field" + i + ":" + fieldBody[i] + "\n");
		return sb.toString();
	}

	public String getfieldLen()
	{
		return fieldLen;
	}
	
	public String getfieldTPDU()
	{
		return fieldTPDU;
	}

	public String getfieldMsghead()
	{
		return fieldMsghead;
	}

	public String getWholefieldBody()
	{
		return fieldBody.toString();
	}

	public String getfieldBody(int index)
	{
		return fieldBody[index];
	}

	public boolean fillWholeMsgdec(String... str)
	{
		if(68 != str.length)
			return false;
		fieldLen = str[0];
		fieldTPDU = str[1];
		fieldMsghead = str[2];
		for(int i = 0;i < fieldBody.length; i++)
			fieldBody[i] = str[3 + i]; 
		isFinished = true;
		return true;
	}

	public boolean isFilled()
	{
		return isFinished;
	}
	
}
