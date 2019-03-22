package com.thaiddd.javaguiex;

/**
 * Hello world!
 *
 */
public class App 
{
    public native void displayHelloworld();
    public native boolean msgdec(String in, Msgdec out);    
    static {
        try{
            System.loadLibrary("HelloWorldImpl");
            
        }catch(Exception e) {}        
    }
    public static void main( String[] args )
    {
        App helloworld = new App();
		Msgdec dec = new Msgdec();
        //helloworld.displayHelloworld();
		if(helloworld.msgdec("args[0]", dec))
			System.out.print(dec);
		else
			System.out.println("Decode failed.");
    }
}
