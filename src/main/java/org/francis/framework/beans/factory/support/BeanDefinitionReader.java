package org.francis.framework.beans.factory.support;

import org.dom4j.DocumentException;
import org.francis.framework.beans.BeanDefinition;

/**
 * @author Franc1s
 * @date 2022/8/27
 * @apiNote
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    void loadBeanDefinitions(String configLocation) throws DocumentException, Exception;
}
