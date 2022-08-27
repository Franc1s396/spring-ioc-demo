package org.francis.framework.context.support;

import org.francis.framework.beans.factory.support.BeanDefinitionReader;
import org.francis.framework.beans.factory.support.BeanDefinitionRegistry;
import org.francis.framework.context.ApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Franc1s
 * @date 2022/8/27
 * @apiNote
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    /**
     * Bean定义的加载器
     */
    protected BeanDefinitionReader beanDefinitionReader;

    /**
     * bean容器
     */
    protected Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    /**
     * 配置文件的加载路径
     */
    protected String configLocation;

    @Override
    public void refresh() throws Exception {
        //加载配置文件
        beanDefinitionReader.loadBeanDefinitions(configLocation);
        finishInitialization();
    }

    /**
     * Bean初始化
     */
    private void finishInitialization() throws Exception {
        //获取注册表
        BeanDefinitionRegistry beanDefinitionRegistry = beanDefinitionReader.getRegistry();
        //获取BeanDefinition对象
        String[] beanDefinitionNames = beanDefinitionRegistry.getBeanDefinitionNames();
        //进行bean初始化
        for (String beanName : beanDefinitionNames) {
            getBean(beanName);
        }
    }
}
