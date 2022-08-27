package org.francis.framework.context.support.xml;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.francis.framework.beans.BeanDefinition;
import org.francis.framework.beans.MutablePropertyValues;
import org.francis.framework.beans.PropertyValue;
import org.francis.framework.beans.factory.support.BeanDefinitionRegistry;
import org.francis.framework.beans.factory.support.XmlBeanDefinitionReader;
import org.francis.framework.context.support.AbstractApplicationContext;

import java.lang.reflect.Method;

/**
 * @author Franc1s
 * @date 2022/8/27
 * @apiNote
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {
    public ClassPathXmlApplicationContext(String configLocation) {
        this.configLocation = configLocation;
        this.beanDefinitionReader = new XmlBeanDefinitionReader();
        try {
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String beanName) throws Exception {
        //判断对象容器中是否包含指定参数的Bean，如果有则直接返回，如果没有，需要自行创建
        if (singletonObjects.containsKey(beanName)) {
            return singletonObjects.get(beanName);
        }
        //没有指定BeanName
        BeanDefinitionRegistry registry = beanDefinitionReader.getRegistry();
        //获取beanDefinition
        BeanDefinition beanDefinition = registry.getBeanDefinition(beanName);
        //通过BeanDefinition的全限定类名对类进行加载并获取到class对象
        String className = beanDefinition.getClassName();
        Class<?> clazz = Class.forName(className);
        //利用反射进行实例化bean
        Object bean = clazz.newInstance();
        //进行依赖注入
        MutablePropertyValues mutablePropertyValues = beanDefinition.getMutablePropertyValues();
        for (PropertyValue propertyValue : mutablePropertyValues) {
            String name = propertyValue.getName();
            String value = propertyValue.getValue();
            String ref = propertyValue.getRef();
            //利用setter方法进行注入
            if (!StringUtils.isEmpty(ref) && !StringUtils.isBlank(ref)) {
                Object bean1 = getBean(ref);
                //获取setter方法名
                String methodName = org.francis.framework.utils.StringUtils.getSetterMethodName(name);
                //通过反射调用setter方法进行注入
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if (methodName.equals(method.getName())){
                        method.invoke(bean,bean1);
                    }
                }
            }
            if (!StringUtils.isEmpty(value) && !StringUtils.isBlank(value)){
                String methodName = org.francis.framework.utils.StringUtils.getSetterMethodName(name);
                Method method = clazz.getMethod(methodName, String.class);
                method.invoke(bean,value);
            }
        }
        //在返回之前将bean存储到bean容器中(map)
        singletonObjects.put(beanName,bean);
        return bean;
    }

    @Override
    public <T> T getBean(String name, Class<? extends T> requiredType) throws Exception {
        Object bean = getBean(name);
        if (ObjectUtils.isEmpty(bean)) {
            return null;
        }
        return requiredType.cast(bean);
    }
}
