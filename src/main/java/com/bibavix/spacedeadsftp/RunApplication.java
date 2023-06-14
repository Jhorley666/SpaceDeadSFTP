package com.bibavix.spacedeadsftp;

import com.bibavix.spacedeadsftp.UI.MainSdsftpFrame;
/**import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;*/


/**@Configuration
@AutoConfigurationPackage
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})*/
public class RunApplication {

    public static void main(String[] args){
        MainSdsftpFrame mainSdsftpFrame = new MainSdsftpFrame();
        mainSdsftpFrame.init();
        //SpringApplication.run(RunApplication.class, args);
    }

}
