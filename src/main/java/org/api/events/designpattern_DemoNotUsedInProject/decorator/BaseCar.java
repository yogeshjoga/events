package org.api.events.designpattern_DemoNotUsedInProject.decorator;

public class BaseCar implements Car {

    @Override
    public String run() {
        return "This is a base car";
    }

    @Override
    public double getPrice() {
        return 50.00;
    }
}
