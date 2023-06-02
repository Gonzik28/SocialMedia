package com.gonzik28.SocialMedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SocialMediaApplication {
    public static void main(String[] args) {
        String uploadDir = extractUploadDirFromCommandLineArgs(args);
        System.setProperty("upload-dir", uploadDir);
        SpringApplication.run(SocialMediaApplication.class, args);
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gonzik28.SocialMedia"))
                .build();
    }
    private static String extractUploadDirFromCommandLineArgs(String[] args) {
        for (String arg : args) {
            if (arg.startsWith("--upload-dir=")) {
                return arg.substring(arg.indexOf("=") + 1);
            }
        }
        return "C:/Users/Alena/Desktop/image";
    }
}