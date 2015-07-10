/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.committee.bo;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * This class is abstract template BO test class could use to test toStringMapper. 
 * a. It checks for number of attributes used in toStringMapper.
 * b. It also test for fields and values passed in toStringMapper.
 */
public abstract class BoAttributeTestBase<T extends KcPersistableBusinessObjectBase> {

    private int attributeCount;

    private T bo;

    /**
     * Constructs a BoAttributeTestBase.java.
     * @param attributeCount is total count of persistent fields in BO used by toStringMapper().
     * @param bo is instance of BO.
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public BoAttributeTestBase(int attributeCount, T bo) throws IllegalArgumentException, IllegalAccessException,
                                                        InvocationTargetException {
        super();
        this.attributeCount = attributeCount;
        this.bo = bo;
        boPrerequisite();
        setUpBo();
        boPostrequisite();
    }

    /**
     * Concrete implementer should return Map&lt;String, Object&gt;, using bo's field and value. Map should include all the fields used in
     * definition of toStringMapper().
     * 
     * @return Map&lt;String, Object&gt; of bo's key &amp; value.
     */
    protected abstract Map<String, Object> getFieldMap();

    /**
     * This method can be used to set BO's post-requisites.
     */
    protected void boPrerequisite() {
    }

    /**
     * This method can be used to set BO's prerequisites.
     */
    protected void boPostrequisite() {
    }

    /**
     * This method is accessory of generic T type object.
     * 
     * @return
     */
    public T getT() {
        return bo;
    }

    /**
     * This method uses reflection to set values of BO.
     * 
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @SuppressWarnings("unchecked")
    private void setUpBo() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        Method[] methods = bo.getClass().getDeclaredMethods();
        Map map = getFieldMap();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.contains("set")) {
                methodName = methodName.substring(3);
                methodName = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);
                Object value = map.get(methodName);
                if (null != value) {
                    Object[] values = new Object[1];
                    values[0] = value;
                    method.invoke(bo, values);
                }
            }
        }
    }
}
