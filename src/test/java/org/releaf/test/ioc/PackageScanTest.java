package org.releaf.test.ioc;

import org.junit.Test;
import org.releaf.context.support.ClassPathXmlApplicationContext;
import org.releaf.test.bean.Car;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class PackageScanTest {

    @Test
    public void testScanPackage(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:package-scan.xml");

        Car car = (Car) context.getBean("car");
        assertThat(car).isNotNull();
    }

}
