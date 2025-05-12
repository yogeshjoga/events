package org.api.events.designpattern_DemoNotUsedInProject.decorator;

public abstract class CarFeatures implements Car{

    protected  Car car;
    public CarFeatures(Car car) {
       this.car = car;
    }

    @Override
    public String run() {
        return car.run();
    }

    @Override
    public double getPrice() {
        return car.getPrice();
    }


}
