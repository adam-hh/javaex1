package com.thaiddd.javaguiex.core;

public class Connector{
    private Connector(){}
    private static byte[] consoleBuffer;
    public static int connect(byte[] b){
        try{
            consoleBuffer = b;
        }catch(Exception ep){
            ep.printStackTrace();
            return -1;
        }        
        return 1;
    }
    public static String toPrt(){
        StringBuilder sb = new StringBuilder();
        for(byte b : consoleBuffer){
            if(b != 0x0)
                sb.append(b);
        }
        return sb.toString();
    }
}