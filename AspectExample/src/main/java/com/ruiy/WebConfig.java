package com.ruiy;

import com.ruiy.service.DocumentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by ruiyang on 15-05-21.
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass=false)
@ComponentScan(value={"com.ruiy"})
public class WebConfig {

    @Bean
    public DocumentService documentService() throws Exception {
        return new DocumentService();
    }

    @Bean
    public PermissionChecker permissionChecker() throws Exception {
        return new PermissionChecker();
    }

}
