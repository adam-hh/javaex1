package com.thaiddd.javaguiex;

/**
 * Hello world!
 *
 */
public class App 
{
    public native void displayHelloworld();
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
        helloworld.displayHelloworld();
    }
}
