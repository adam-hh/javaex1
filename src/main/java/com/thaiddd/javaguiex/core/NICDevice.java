package com.thaiddd.javaguiex.core;
public class NICDevice
{
    public String name;
    public String description;
    public String flags;
    public String[] addresses;       

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(name + "\n");
        sb.append("\tDescription: " + description +"\n");
        sb.append("\t" + flags +"\n");
        for(String s: addresses)
            if(s != null)
                sb.append(s + "\n");
        return sb.toString();
    }
}