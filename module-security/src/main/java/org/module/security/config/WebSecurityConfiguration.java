//package org.module.security.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Administrator
 *	问题：ProductApp启动类，启动的时候，默认是扫描的所在包下所有配置。
 *	如果安全认证授权相关的，放在security.config下，就无法扫描到WebSecurityConfiguration配置，那么授权就无法生效。
 *	改成provider.config包下，即可解决。
 */
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//    	auth.inMemoryAuthentication().passwordEncoder(
//    			new BCryptPasswordEncoder()).withUser("root").password(new BCryptPasswordEncoder().encode("enjoy")).roles("USER")
//    			.and().withUser("admin").password(new BCryptPasswordEncoder().encode("enjoy")).roles("USER", "ADMIN");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic().and().authorizeRequests().anyRequest()
//                .fullyAuthenticated();
//        //有状态时，服务多的情况下会产生大量的cookies session
//        //设置为无状态:STATELESS，可以减少cookies session的消耗
//        http.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
//
//}