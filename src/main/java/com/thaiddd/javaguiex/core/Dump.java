package com.thaiddd.javaguiex.core;

public class Dump
{
    private Dump()
    {}
    public static native int devList(NICDevice[] nicDevices);
    public static native int openDev(int index);
    public static native int setFilter(String flt);
    public static native int loop(int cnt);
    public static native int readFromUserBuff(TcpDataBlock tdb);
    public static native int stoploop();
}