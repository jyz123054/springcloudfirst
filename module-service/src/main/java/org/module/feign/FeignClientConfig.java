package org.module.feign;

import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {
	
    /**
     * 	安全认证授权
     * @return
     */
    @Bean
    public BasicAuthRequestInterceptor getBasicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("admin", "enjoy");
    }

    /**
     * Feign客户端默认的logger.level对象定义为none级别。
     * 	所以如果需要打印日志，这里需要设置为具体的日志级别。
     * @return
     */
    @Bean
    public Logger.Level getFeignLoggerLevel() {
        return feign.Logger.Level.FULL;
    }
}