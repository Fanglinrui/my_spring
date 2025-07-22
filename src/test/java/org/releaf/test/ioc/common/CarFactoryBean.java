package org.releaf.test.ioc.common;

import org.releaf.beans.factory.FactoryBean;
import org.releaf.test.ioc.bean.Car;

public class CarFactoryBean implements FactoryBean<Car> {

    private String brand;

    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        car.setBrand(brand);
        return car;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    // JavaBean规范要求setter方法，所以如果没有这个方法，xml那里会爆红，实际上我们的项目里不影响
    public void setBrand(String brand) {
        this.brand = brand;
    }
}
