package org.francis.framework.beans.factory;

/**
 * @author Franc1s
 * @date 2022/8/27
 * @apiNote
 */
public interface BeanFactory {
    Object getBean(String beanName) throws ClassNotFoundException, IllegalAccessException, InstantiationException, Exception;

    <T> T getBean(String name,Class<? extends T> requiredType) throws Exception;

}
