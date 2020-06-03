package net.abadguy.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContexUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context=applicationContext;
    }

    /**
     * 根据bean的名字获取工厂中的bean 对象
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName){
        Object bean = context.getBean(beanName);
        return bean;
    }
}
