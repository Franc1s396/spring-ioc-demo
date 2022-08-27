package org.francis.framework.beans;

import org.francis.framework.beans.MutablePropertyValues;

/**
 * @author Franc1s
 * @date 2022/8/27
 * @apiNote
 */
public class BeanDefinition {
    private String id;
    private String className;
    private MutablePropertyValues mutablePropertyValues;

    public BeanDefinition() {
        mutablePropertyValues=new MutablePropertyValues();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public MutablePropertyValues getMutablePropertyValues() {
        return mutablePropertyValues;
    }

    public void setMutablePropertyValues(MutablePropertyValues mutablePropertyValues) {
        this.mutablePropertyValues = mutablePropertyValues;
    }
}
