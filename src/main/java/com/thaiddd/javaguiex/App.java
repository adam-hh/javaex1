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
            //System.setProperty("java.library.path", System.getProperty("java.library.path")
            //   + ":.");       
            //final Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
            //fieldSysPath.setAccessible(true);
            //fieldSysPath.set(null,null);
            System.loadLibrary("HelloWorldImpl");
            
        }catch(Exception e) {}        
    }
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
        App helloworld = new App();
		Msgdec dec = new Msgdec();
        helloworld.displayHelloworld();
		if(helloworld.msgdec("args[0]", dec))
			System.out.print(dec);
		else
			System.out.println("Decode failed.");
    }
}
