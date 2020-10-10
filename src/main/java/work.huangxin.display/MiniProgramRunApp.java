package work.huangxin.display;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MiniProgramRunApp extends SpringBootServletInitializer {
//
//    public static void main(String args[]) {
//        SpringApplication.run(MiniProgramRunApp.class, args);
//
//    }


    /**
     * 以下为Tomcat启动
     *
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MiniProgramRunApp.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MiniProgramRunApp.class, args);
    }


}




