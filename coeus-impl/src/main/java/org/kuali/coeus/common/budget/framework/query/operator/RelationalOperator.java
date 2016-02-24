/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.coeus.common.budget.framework.query.operator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public abstract class RelationalOperator implements Operator {

    private static final Log LOG = LogFactory.getLog(RelationalOperator.class);

    protected String fieldName;
    protected Comparable fixedData;
    protected boolean booleanFixedData;
    
    protected boolean isBoolean;
    
    //variables to improve performance
    private Field field;
    private Method method;
    private Class dataClass;
    
    public  RelationalOperator(String fieldName, Comparable fixedData) {
        this.fieldName = fieldName;
        this.fixedData = fixedData;
    } // end RelationalOperator
    
    public  RelationalOperator(String fieldName, boolean booleanFixedData) {
        this.fieldName = fieldName;
        this.booleanFixedData = booleanFixedData;
        isBoolean = true;
    } // end RelationalOperator
    
    /**Compares this object with the specified object for order.
     *Returns a negative integer, zero, or a positive integer as this object
     *is less than, equal to, or greater than the specified object.
     */
    protected int compare(Object baseBean){
        int compareValue = 0;

        if(dataClass == null || !dataClass.equals(baseBean.getClass())){
            dataClass = baseBean.getClass();
            try {
                field = dataClass.getDeclaredField(fieldName);
                if(! field.isAccessible()) {
                    throw new NoSuchFieldException();
                }
            } catch (NoSuchFieldException noSuchFieldException) {
                tryAccessingWithGetters();
            }
        }
        
        try{
            if(field != null && field.isAccessible()) {
                
                if (! isBoolean) {
                    Comparable comparable = (Comparable)field.get(baseBean);
                    if(comparable == null && fixedData == null) {
                        compareValue = 0;
                    }
                    else if(comparable == null) {
                        throw new UnsupportedOperationException();
                    }else{
                        compareValue = comparable.compareTo(fixedData);
                    }
                    
                } else {
                    if ( ((Boolean)field.get(baseBean)).booleanValue() == booleanFixedData )
                        compareValue = 0;
                    else
                        compareValue = 1;
                }
            }
            else{
                if (! isBoolean) {
                    Comparable comparable = (Comparable)method.invoke(baseBean, null);
                    if(comparable == null && fixedData == null) {
                        compareValue = 0;
                    }
                    else if(comparable == null) {
                        throw new UnsupportedOperationException();
                    }
                    else if(comparable != null && fixedData == null) {
                        compareValue = -1;
                    }
                    else {
                        compareValue = comparable.compareTo(fixedData);
                    }
                } else {
                    Boolean booleanObj = (Boolean)method.invoke(baseBean, null);
                    if (booleanObj == null) {
                        compareValue = -1;
                    } else {
                        if ( booleanObj.booleanValue() == booleanFixedData )
                            compareValue = 0;
                        else
                            compareValue = 1;
                    }
                }
            }
        }catch (IllegalAccessException illegalAccessException) {
            LOG.error(illegalAccessException.getMessage(),illegalAccessException);
        }catch (InvocationTargetException invocationTargetException) {
            LOG.error(invocationTargetException.getMessage(), invocationTargetException);
        }
        return compareValue;
    }

    protected void tryAccessingWithGetters() {
        String methodName;
        try {
            if(isBoolean) {
                methodName = "is" + (fieldName.charAt(0)+"").toUpperCase()+ fieldName.substring(1);
            } else {
                methodName = "get" + (fieldName.charAt(0)+"").toUpperCase()+ fieldName.substring(1);
            }
            method = dataClass.getMethod(methodName, null);
        } catch (NoSuchMethodException noSuchMethodException) {
            tryAccessingBooleanWithGet();
        }
    }

    protected void tryAccessingBooleanWithGet() {
        String methodName;
        try {
            methodName = "get" + (fieldName.charAt(0) + "").toUpperCase() + fieldName.substring(1);
            method = dataClass.getMethod(methodName, null);
        } catch(NoSuchMethodException exception) {
            throw new RuntimeException(exception);
        }
    }


    public And and(Operator relatesTo) {
        return new And(this, relatesTo);
    }

    public Or or(Operator relatesTo) {
        return new Or (this, relatesTo);
    }
    
}// end RelationalOperator



