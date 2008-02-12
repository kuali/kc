/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.budget.calculator.query;

import java.lang.reflect.*;


public abstract class RelationalOperator implements Operator {
    
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
                    //String methodName = "get" + (fieldName.charAt(0)+"").toUpperCase()+ fieldName.substring(1);
                    //System.out.println(methodName);
                    //method = dataClass.getMethod(methodName, null);
                    throw new NoSuchFieldException();
                }
            }catch (NoSuchFieldException noSuchFieldException) {
                //noSuchFieldException.printStackTrace();
                try{
                    String methodName="";
                    
                    if(isBoolean) {
                        methodName = "is" + (fieldName.charAt(0)+"").toUpperCase()+ fieldName.substring(1);
                    }else{
                        methodName = "get" + (fieldName.charAt(0)+"").toUpperCase()+ fieldName.substring(1);
                    }
                    //System.out.println(methodName);
                    method = dataClass.getMethod(methodName, null);
                }catch (NoSuchMethodException noSuchMethodException) {
                    noSuchMethodException.printStackTrace();
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
            illegalAccessException.printStackTrace();
        }catch (InvocationTargetException invocationTargetException) {
            invocationTargetException.printStackTrace();
        }
        return compareValue;
    }
    
    
}// end RelationalOperator



