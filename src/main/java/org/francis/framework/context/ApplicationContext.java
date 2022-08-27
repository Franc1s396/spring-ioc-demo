package org.francis.framework.context;

import org.francis.framework.beans.factory.BeanFactory;

/**
 * @author Franc1s
 * @date 2022/8/27
 * @apiNote
 */
public interface ApplicationContext extends BeanFactory {
    void refresh() throws Exception;
}
