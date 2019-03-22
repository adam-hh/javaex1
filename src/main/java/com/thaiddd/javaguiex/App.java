package com.thaiddd.javaguiex;

/**
 * Hello world!
 *
 */
public class App 
{
    public native void displayHelloworld();
    public native boolean msgdec(String in, Msgdec out);
    private Msgdec dec;
    static {
        try{
            System.loadLibrary("HelloWorldImpl");
            
        }catch(Exception e) {}        
    }

    public App()
    {
        dec = new Msgdec();
    }

    public void nativeTest()
    {
        System.out.println("call method nativeTest");
    }
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
        App helloworld = new App();
		//Msgdec dec = new Msgdec();
        helloworld.displayHelloworld();
		/*if(helloworld.msgdec("args[0]", dec))
			System.out.print(dec);
		else
			System.out.println("Decode failed.");*/
    }
}
