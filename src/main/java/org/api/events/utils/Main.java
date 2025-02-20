package org.api.events.utils;

public class Main {

    public void add(){
        System.out.println("Hello World");
    }

    public void print(){
        this.add();
    }


    public static void main(String[] args) {
      Main m = new Main();
      m.print();
    }
}
