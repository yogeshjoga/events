package org.api.events.designpattern_DemoNotUsedInProject.decorator;

public class BackCamera extends CarFeatures {

    public BackCamera(Car car){
        super(car);
    }

    @Override
    public String run() {
        return "Back Camera added to the car";
    }

    @Override
    public double getPrice() {
        return car.getPrice() + 40;
    }
}
