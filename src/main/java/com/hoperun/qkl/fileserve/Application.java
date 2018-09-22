package com.hoperun.qkl.fileserve;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;import java.util.Arrays;


@SpringBootApplication
@Slf4j
public class Application {

    @Autowired
    Environment env;

    @PostConstruct
    public void initApplication() {
        log.info("Running with Spring profiles :{} ", Arrays.toString(env.getActiveProfiles()));
    }


    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(Application.class);
        Environment env = springApplication.run(args).getEnvironment();

        try {
            log.info("\n-----------------------------------------\n\t"
                            + "Application '{}' is running! Access URLs:\n\t" + "Local:\t\thttp://localhost:{}\n\t"
                            + "External:\thttp://{}:{}\n-----------------------------------------"
                    , "FILE", env.getProperty("server.port"), InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


}
