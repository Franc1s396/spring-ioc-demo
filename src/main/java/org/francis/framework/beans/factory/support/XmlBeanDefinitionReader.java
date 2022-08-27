package org.francis.framework.beans.factory.support;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.francis.framework.beans.BeanDefinition;
import org.francis.framework.beans.MutablePropertyValues;
import org.francis.framework.beans.PropertyValue;

import java.io.InputStream;
import java.util.List;

/**
 * @author Franc1s
 * @date 2022/8/27
 * @apiNote 针对XML配置文件进行解析
 */
public class XmlBeanDefinitionReader implements BeanDefinitionReader {

    private BeanDefinitionRegistry beanDefinitionRegistry;

    public XmlBeanDefinitionReader() {
        beanDefinitionRegistry = new SimpleBeanDefinitionRegistry();
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return beanDefinitionRegistry;
    }

    @Override
    public void loadBeanDefinitions(String configLocation) throws Exception {
        //使用dom4j进行解析xml
        SAXReader saxReader = new SAXReader();
        //获取路径参数下的配置文件输入流
        InputStream inputStream = XmlBeanDefinitionReader.class.getClassLoader().getResourceAsStream(configLocation);
        Document document = saxReader.read(inputStream);
        //获取根标签(beans)
        Element rootElement = document.getRootElement();
        //获取bean标签集合
        List<Element> beanElementList = rootElement.elements("bean");
        //遍历bean标签集合
        for (Element beanElement : beanElementList) {
            //获取id属性
            String id = beanElement.attributeValue("id");
            //获取class全限定类名
            String className = beanElement.attributeValue("class");
            //实例化beanDefinition
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setId(id);
            beanDefinition.setClassName(className);

            MutablePropertyValues propertyValues = new MutablePropertyValues();

            //获取bean标签下所有property标签对象
            List<Element> propertyElementList = beanElement.elements("property");
            //遍历获取property标签对象
            for (Element propertyElement : propertyElementList) {
                String name = propertyElement.attributeValue("name");
                String ref = propertyElement.attributeValue("ref");
                String value = propertyElement.attributeValue("value");
                PropertyValue propertyValue = new PropertyValue(name, ref, value);
                //将所有pv添加到mutablePropertyValues
                propertyValues.add(propertyValue);
            }
            //mutablePropertyValue注入到beanDefinition
            beanDefinition.setMutablePropertyValues(propertyValues);

            beanDefinitionRegistry.registerBeanDefinition(id, beanDefinition);
        }
    }
}
