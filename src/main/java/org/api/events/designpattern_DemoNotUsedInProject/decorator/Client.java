package org.api.events.designpattern_DemoNotUsedInProject.decorator;

public class Client {


    public static void main(String[] args) {

        Car car = new BaseCar();
        System.out.println(car.run() + "  " + car.getPrice());

        car = new BackCamera(car);
        System.out.println(car.run() + "  " + car.getPrice());
    }
}
