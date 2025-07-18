package org.releaf.test.ioc.bean;

import org.releaf.beans.factory.DisposableBean;
import org.releaf.beans.factory.InitializingBean;

public class Person implements InitializingBean, DisposableBean {

    private String name;

    private int age;

    private Car car;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public Car getCar() { return car; }

    public void setCar(Car car) { this.car = car; }

    @Override
    public String toString(){
        return "Preson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", car=" + car +
                '}';
    }

    public void customInitMethod() {
        System.out.println("I was born in the method named customInitMethod");
    }

    public void customDestroyMethod() {
        System.out.println("I died in the method named customDestroyMethod");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("I was born in the method named afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("I died in the method named destroy");
    }
}
