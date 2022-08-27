package org.francis.framework.context.support.xml;

import org.francis.framework.beans.factory.support.XmlBeanDefinitionReader;
import org.francis.framework.context.support.AbstractApplicationContext;

/**
 * @author Franc1s
 * @date 2022/8/27
 * @apiNote
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {
    public ClassPathXmlApplicationContext(String configLocation) {
        this.configLocation=configLocation;
        this.beanDefinitionReader=new XmlBeanDefinitionReader();
    }

    @Override
    public Object getBean(String beanName) {
        return null;
    }

    @Override
    public <T> T getBean(String name, Class<? extends T> requiredType) {
        return null;
    }
}
