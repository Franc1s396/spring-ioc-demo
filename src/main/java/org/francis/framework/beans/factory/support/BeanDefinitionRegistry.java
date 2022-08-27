package org.francis.framework.beans.factory.support;

import org.francis.framework.beans.BeanDefinition;

/**
 * @author Franc1s
 * @date 2022/8/27
 * @apiNote Bean标签注册接口
 */
public interface BeanDefinitionRegistry {
    /**
     * 注册
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 删除
     */
    void removeBeanDefinition(String beanName);

    /**
     * 获取
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * 是否存在
     */
    boolean containBeanDefinition(String beanName);

    /**
     * 获取数量
     */
    int getBeanDefinitionCount();

    /**
     * 获取BeanNames数组
     */
    String[] getBeanDefinitionNames();
}
