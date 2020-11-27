package cloud.sunmao.framework.spring.basic.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring 获取 Spring 上下文工具类
 * <p>
 * 使用前，需要注入到Spring中，待Spring启动完成后，方可使用
 * <p>
 * 本工具类并不安全，使用时请注意
 *
 * @author xdatk
 */
public class SpringContextUtil implements ApplicationContextAware {

    /**
     * Spring 上下文
     */
    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }
}
