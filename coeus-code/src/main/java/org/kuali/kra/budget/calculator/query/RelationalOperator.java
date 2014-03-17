/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget.calculator.query;

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
        //Field field = null;
        //Method method = null;
        //Class dataClass;
        
        if(dataClass == null || !dataClass.equals(baseBean.getClass())){
            
            dataClass = baseBean.getClass();
            
            try{
                field = dataClass.getDeclaredField(fieldName);
                if(! field.isAccessible()) {
                    throw new NoSuchFieldException();
                }
            }catch (NoSuchFieldException noSuchFieldException) {
                try{
                    String methodName="";
                    
                    if(isBoolean) {
                        methodName = "is" + (fieldName.charAt(0)+"").toUpperCase()+ fieldName.substring(1);
                    }else{
                        methodName = "get" + (fieldName.charAt(0)+"").toUpperCase()+ fieldName.substring(1);
                    }
                    method = dataClass.getMethod(methodName, null);
                }catch (NoSuchMethodException noSuchMethodException) {
                    LOG.error(noSuchMethodException.getMessage(), noSuchMethodException);
                }
            }
        }//End if field==null && method==null
        
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
    

    public And and(Operator relatesTo) {
        return new And(this, relatesTo);
    }

    public Or or(Operator relatesTo) {
        return new Or (this, relatesTo);
    }
    
}// end RelationalOperator



