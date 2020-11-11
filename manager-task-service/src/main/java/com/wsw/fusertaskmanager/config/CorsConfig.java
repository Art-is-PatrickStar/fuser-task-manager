//package com.wsw.fusertaskmanager.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @Author WangSongWen
// * @Date: Created in 16:01 2020/11/10
// * @Description:
// */
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//    /**
//     * 跨域设置
//     * @param registry
//     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")  // 允许跨域访问的路径
//                .allowedOrigins("*")  // 允许跨域访问的资源
//                .allowedHeaders("*")  // 允许头部设置
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // 允许请求方法
//                .allowCredentials(true)  // 是否发送cookie
//                .maxAge(168000);  // 预检时间间隔
//    }
//}
