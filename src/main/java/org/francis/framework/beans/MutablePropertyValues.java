package org.francis.framework.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author Franc1s
 * @date 2022/8/27
 * @apiNote 封装多个PropertyValue对象
 */
public class MutablePropertyValues implements Iterable<PropertyValue> {

    private final List<PropertyValue> propertyValueList;

    public MutablePropertyValues() {
        this.propertyValueList = new ArrayList<>();
    }

    public MutablePropertyValues(List<PropertyValue> propertyValueList) {
        this.propertyValueList = propertyValueList;
    }

    /**
     * 获取PropertyValue数组
     */
    public PropertyValue[] getPropertyValues() {
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    /**
     * 根据name获取PropertyValue
     */
    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue propertyValue : propertyValueList) {
            if (propertyName.equals(propertyValue.getName())) {
                return propertyValue;
            }
        }
        return null;
    }

    /**
     * 集合判空
     */
    public boolean isNull() {
        return propertyValueList.isEmpty();
    }

    /**
     * 添加元素
     */
    public MutablePropertyValues add(PropertyValue propertyValue) {
        for (int i = 0; i < propertyValueList.size(); i++) {
            PropertyValue currentPropertyValue = propertyValueList.get(i);
            if (currentPropertyValue.getName().equals(propertyValue.getName())) {
                propertyValueList.set(i, propertyValue);
                return this;
            }
        }
        propertyValueList.add(propertyValue);
        return this;
    }

    public boolean contain(String propertyName){
        return getPropertyValue(propertyName)!=null;
    }

    @Override
    public Iterator<PropertyValue> iterator() {
        return propertyValueList.iterator();
    }
}
