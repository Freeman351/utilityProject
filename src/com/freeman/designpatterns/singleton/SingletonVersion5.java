package com.freeman.designpatterns.singleton;

import java.util.ArrayList;
import java.util.List;

public enum  SingletonVersion5 {

    INSTANCE;

    private List<String> contentList = new ArrayList<String>();

    private SingletonVersion5() {
        contentList.add("Item1");
    }

    public List<String> getInstance() {
        return contentList;
    }

    public static void main(String[] args) {
        System.out.println(SingletonVersion5.INSTANCE.getInstance());
       
        SingletonVersion5 INSTANCE2 = SingletonVersion5.INSTANCE;
        System.out.println("COMPARE RESULT = " + INSTANCE.equals(INSTANCE2));
        INSTANCE.getInstance().add("Item2");
        System.out.println("COMPARE RESULT = " + INSTANCE.equals(INSTANCE2));
       
        System.out.println(INSTANCE.getInstance());
       
        INSTANCE2.getInstance().add("Item3");
        System.out.println("COMPARE RESULT = " + INSTANCE.equals(INSTANCE2));
        System.out.println(INSTANCE2.getInstance());
        System.out.println(SingletonVersion5.INSTANCE.getInstance());
    }	
}
