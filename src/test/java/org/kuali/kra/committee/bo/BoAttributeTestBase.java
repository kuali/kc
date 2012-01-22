/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.committee.bo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * This class is abstract template BO test class could use to test toStringMapper. 
 * a. It checks for number of attributes used in toStringMapper.
 * b. It also test for fields and values passed in toStringMapper.
 */
public abstract class BoAttributeTestBase<T extends KraPersistableBusinessObjectBase> extends TestCase {

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
     * Concrete implementer should make a call to getToStringMapper() of BO class returning Map.
     * @return
     */
    protected final Map<String, String> getToStringMapper() {
        String boToString = bo.toString();
        Map<String, String> fieldMap = new HashMap<String, String>();
        //ReflectionToStringBuilder uses ToStringStyle DEFAULT_STYLE by default
        Pattern p = Pattern.compile("(?<=\\[).*(?=\\])");
        Matcher m = p.matcher(boToString);
        if(m.find()) {
            String[] matches = m.group().split(",");
            for(String match: matches) {
                String[] fieldValue = match.split("=");
                fieldMap.put(fieldValue[0], fieldValue[1]);
            }
        }
        return fieldMap;
    }
    /**
     * Concrete implementer should return Map<String, Object>, using bo's field and value. Map should include all the fields used in
     * definition of toStringMapper().
     * 
     * @return Map<String, Object> of bo's key & value.
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
     * This method tests attributes count in BO of toStringMapper.
     * 
     * @throws Exception
     */
    @Test
    public void testBoAttributesCount() throws Exception {
        Assert.assertEquals(attributeCount, getToStringMapper().size());
        ;
    }

    /**
     * This method tests fields added to Map of toStringMapper in BO
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testBoFields() throws Exception {
        Map map = getToStringMapper();
        Map fieldMap = getFieldMap();
        Set<String> set = fieldMap.keySet();
        for (String field : set) {
            assertEquals(fieldMap.get(field), map.get(field));
        }
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
