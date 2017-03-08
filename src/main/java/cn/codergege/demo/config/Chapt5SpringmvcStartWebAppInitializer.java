package cn.codergege.demo.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletRegistration;

public class Chapt5SpringmvcStartWebAppInitializer
    extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }

    // 将 DispatcherServlet 映射到 "/"
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    // 设置 ServletContext 初始化参数
    // todo profile 设置
    // 下面的设置不对
    /*
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        // 设置默认的 profile 为 dev
        // 部署到生产环境时, 可以在 系统属性, 环境变量, JNDI 设置 spring.profiles.active 为 prod 就可以了
        registration.setInitParameter("spring.profiles.default", "dev");
    }
    */
}
			