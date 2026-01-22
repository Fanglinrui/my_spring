package org.releaf.test.bean;

import org.releaf.beans.factory.annotation.Value;
import org.releaf.stereotype.Component;

@Component
public class Car {

    @Value("${brand}")
    private String brand;

    public  String getBrand() {
        return brand;
    }
    public void setBrand(String brand) { this.brand = brand; }

    @Override
    public String toString(){
        return "Car{" +
                "brand='" + brand +'\'' +
                '}';
    }
}
