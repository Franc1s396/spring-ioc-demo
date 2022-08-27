package org.francis.framework.beans;

/**
 * @author Franc1s
 * @date 2022/8/27
 * @apiNote Property对象
 * 用来封装bean标签下的property标签中的属性
 */
public class PropertyValue {
    /**
     * 属性名字
     */
    private String name;
    /**
     * 属性引用
     */
    private String ref;
    /**
     * 属性值
     */
    private String value;

    public PropertyValue(String name, String ref) {
        this.name = name;
        this.ref = ref;
    }

    public PropertyValue(String name, String ref, String value) {
        this.name = name;
        this.ref = ref;
        this.value = value;
    }

    @Override
    public String toString() {
        return "PropertyValue{" +
                "name='" + name + '\'' +
                ", ref='" + ref + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
