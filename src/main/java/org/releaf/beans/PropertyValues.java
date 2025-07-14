package org.releaf.beans;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv){
        for(int i = 0; i < propertyValueList.size(); i++){
            PropertyValue currentPv = this.propertyValueList.get(i);
            if(currentPv.getName().equals(pv.getName())){
                this.propertyValueList.set(i,pv);
                return;
            }
        }
        this.propertyValueList.add(pv);
    }

    // 传 [0] 是一个常见写法，它让 toArray() 方法根据 propertyValueList 的大小创建正确长度的数组。这样可以避免手动指定数组大小，更加简洁、高效。
    public PropertyValue[] getPropertyValues(){ return propertyValueList.toArray(new PropertyValue[0]); }

    public PropertyValue getPropertyValue(String propertyName){
        for(PropertyValue pv : propertyValueList){
            if( pv.getName().equals(propertyName) ){
                return pv;
            }
        }
        return null;
    }
}
