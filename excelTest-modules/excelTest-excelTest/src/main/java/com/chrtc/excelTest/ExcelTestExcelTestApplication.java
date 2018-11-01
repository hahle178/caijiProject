package com.chrtc.excelTest;

import com.chrtc.excelTest.excelTest.utils.kettle.StartInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = {"com.chrtc.system","com.chrtc.excelTest"})
public class ExcelTestExcelTestApplication {

public static void main(String[] args) {
SpringApplication.run(ExcelTestExcelTestApplication.class, args);
   /* StartInit startInit = new StartInit();
    try {
        startInit.afterPropertiesSet();
    } catch (Exception e) {
        e.printStackTrace();
    }*/
}
}
