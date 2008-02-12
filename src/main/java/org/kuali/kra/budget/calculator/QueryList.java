/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.budget.calculator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;

import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.query.*;


/**
 * This class...
 */
public class QueryList<E> extends ArrayList<E> {
    /** Operator which filters active beans(i.e not deleted beans) */    
//    public static Operator FILTER_ACTIVE_BEANS = new Or(new Equals("acType", null),new NotEquals("acType",TypeConstants.DELETE_RECORD));

    public QueryList() {
    }
    public QueryList(List dataList) {
        this();
        addAll(dataList);
    }
    
    /** Overridden method for sorting. By default it will sort in ascending order.
     * sorts the QueryList by the fieldName in ascending or descending order.
     * Note: the field Object should be of Comparable type.
     * @param fieldName field which is used to sort the bean.
     * @return boolean indicating whether the sort is completed successfully or not.
     */
    public boolean sort(String fieldName) {
        return sort(fieldName, true);
    }
    
    /**
     * sorts the QueryList by the fieldName in ascending or descending order.
     * Note: the field Objects should be of Comparable type.
     * @param fieldName field which is used to sort the bean.
     * @param ascending if true sorting is done in ascending order,
     * else sorting is done in descending order.
     * @return boolean indicating whether the sort is completed successfully or not.
     */    
    public boolean sort(String fieldName, boolean ascending) {
        return sort(fieldName, ascending, false);
    }
    
    /**
     * sorts the QueryList by the fieldName in ascending or descending order.
     * Note: the field Object should be of Comparable type.
     * @return boolean indicating whether the sort is completed successfully or not.
     * @param ignoreCase use only when comparing strings. as default implementation uses case sensitive comparison.
     * @param fieldName field which is used to sort the bean.
     * @param ascending if true sorting is done in ascending order,
     * else sorting is done in descending order.
     */
    public boolean sort(String fieldName, boolean ascending, boolean ignoreCase) {
        Object current, next;
        int compareValue = 0;
        Field field = null;
        Method method = null;
        if (this.size() == 0) {
            return false;
        }
        Class dataClass = get(0).getClass();
        
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
            //field not available. Use method invokation.
            try{
                String methodName = "get" + (fieldName.charAt(0)+"").toUpperCase()+ fieldName.substring(1);
                //System.out.println(methodName);
                method = dataClass.getMethod(methodName, null);
            }catch (NoSuchMethodException noSuchMethodException) {
                noSuchMethodException.printStackTrace();
                return false;
            }
        }
        
        for(int index = 0; index < size()-1; index++) {
            for(int nextIndex = index+1; nextIndex < size(); nextIndex++){
                current  = get(index);
                next = get(nextIndex);
                //Check if current and next implements Comparable else can't compare.
                //so return without comparing.May be we can have an exception for this purpose.
                try{
                    if(field != null && field.isAccessible()) {
                        Comparable thisObj = (Comparable)field.get(current);
                        Comparable otherObj = (Comparable)field.get(next);
                        if (thisObj == null) {
                            compareValue = -1;
                        } else if (otherObj == null) {
                            compareValue = 1;
                        } else {
                            if(thisObj instanceof String && ignoreCase) {
                                compareValue = ((String)thisObj).compareToIgnoreCase((String)otherObj);
                            }else {
                                compareValue = thisObj.compareTo(otherObj);
                            }
                        }
                    }
                    else{
                        //Object obj1 = method.invoke(current, null);
                        //Object obj2 = method.invoke(next, null);
                        
                        Comparable thisObj = (Comparable)method.invoke(current, null);
                        Comparable otherObj = (Comparable)method.invoke(next, null);
                        if (thisObj == null) {
                            compareValue = -1;
                        } else if (otherObj == null) {
                            compareValue = 1;
                        } else {
                            if(thisObj instanceof String && ignoreCase) {
                                compareValue = ((String)thisObj).compareToIgnoreCase((String)otherObj);
                            }else {
                                compareValue = thisObj.compareTo(otherObj);
                            }
                        }
                        //compareValue = ((Comparable)method.invoke(current, null)).compareTo((Comparable)method.invoke(next, null));
                    }
                }catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                    return false;
                }catch (InvocationTargetException invocationTargetException) {
                    invocationTargetException.printStackTrace();
                    return false;
                }
                
                if(ascending && compareValue > 0) {
                    E temp = get(index);
                    set(index, get(nextIndex));
                    set(nextIndex, temp);
                }else if(! ascending && compareValue < 0) {
                    E temp = get(index);
                    set(index, get(nextIndex));
                    set(nextIndex, temp);
                }
                
            }//End For - Inner
        }//End For - Outer
        return true; //sort completed successfully
    }//End Sort
    
       
    /** sorts the QueryList by the fieldNames in ascending or descending order.
     * Note: the field Objects should be of Comparable type.
     * @param fieldNames fields which is used to sort the bean.
     * @param ascending if true sorting is done in ascending order,
     * else sorting is done in descending order.
     * @return boolean indicating whether the sort is completed successfully or not.
     * Modified code for the better performance and proper sorting.
     *Added by Sharath and Nadh
     */    
    public boolean sort(String fieldNames[], boolean ascending) {
        boolean[] asce = new boolean[1];
        asce[0] = ascending;
        return sort(fieldNames, asce);
    }
    
    /** sorts the QueryList by the fieldNames in ascending or descending order.
     * Note: the field Objects should be of Comparable type.
     * @param fieldNames fields which is used to sort the bean.
     * @param ascending if true sorting is done in ascending order,
     * else sorting is done in descending order.
     * @return boolean indicating whether the sort is completed successfully or not.
     * Modified code for the better performance and proper sorting.
     *Added by Sharath and Nadh
     */    
    public boolean sort(String fieldNames[], boolean ascending[]) {
        int index, nextIndex;
        E current, other;
        boolean swap;
        Operator operator;
        
        for (index=1; index < size(); index++) {
            current = get(index);
            nextIndex = index;
            
            Operator relOperator[] = getRelationalOperators(fieldNames, current, ascending);
            Operator eqOperator[] = getEqualsOperators(fieldNames, current);
            
            while(nextIndex > 0) {
                other = get(nextIndex-1);
                swap = false;
                for(int operatorIndex = 0; operatorIndex < relOperator.length; operatorIndex++) {
                    operator = relOperator[operatorIndex];
                    if(operator.getResult(other)) {
                        swap = true;
                        break;
                    }else {
                        //check with equals
                        operator = eqOperator[operatorIndex];
                        if(operator.getResult(other)) {
                            continue;
                        }else {
                            break;
                        }
                    }
                }//End For
                
                if(swap) { 
                    set(nextIndex, get(nextIndex-1));
                    nextIndex = nextIndex - 1;
                }else {
                    break;
                }//End if-else
            }//End while
            set(nextIndex, current);
        }
        return true;
    }
    
    
    /** For a given fields, it will compare the values in the value objects
     * and returns an array of relational operator for the given fields
     */
    private Operator[] getRelationalOperators(String field[], Object baseBean, boolean ascending[]) {
        //Make all Relational Operators.
        RelationalOperator relationalOperator[] = new RelationalOperator[field.length];
        boolean isascending = !ascending[0];
        for(int index = 0; index < field.length; index++) {
            if(ascending.length>1)
                isascending = !ascending[index];
            
            if(! isascending) {
                GreaterThan gt = new GreaterThan(field[index], (Comparable)getFieldValue(field[index], baseBean));
                relationalOperator[index] = gt;
            }else if(isascending) {
                LesserThan lt = new LesserThan(field[index], (Comparable)getFieldValue(field[index], baseBean));
                relationalOperator[index] = lt;
            }
        }
        return relationalOperator;
    }
    /** For a given fields, it will compare the values in the value objects
     * and returns an array of equal operator for the given fields
     */
    private Operator[] getEqualsOperators(String field[], Object baseBean) {
        //Make all Equals Operators.
        Equals equals[] = new Equals[field.length];
            for(int index = 0; index < equals.length; index++) {
                Equals eq = new Equals(field[index], (Comparable)getFieldValue(field[index], baseBean));
                equals[index] = eq;
            }
        return equals;
    }
    
    /** filters querylist which contains only those beans which satisfies the operator condition.
     * @param operator Operator.
     * @return querylist which contains only those beans which satisfies the operator condition.
     */
    public QueryList<E> filter(Operator operator) {
        QueryList<E> filterResult = new QueryList<E>();
        if (this.size() > 0) {
            E  baseBean = null;
            for(int index = 0; index < this.size() ; index++){
                baseBean = this.get(index);
                if(operator.getResult(baseBean) == true){
                    filterResult.add(baseBean);
                }
            }
        }
        return filterResult;
    }
    
    /** calculates the sum of the field in this QueryList.
     * @param fieldName field of bean whose sum has to be calculated.
     * @return sum.
     */    
    public double sum(String fieldName) {
        return sum(fieldName, null, null);
    }//End Sum

    /** calculates the sum of the Objects of the specified field in this QueryList.
     * @param fieldName field of bean whose sum has to be calculated.
     * @return sum as BigDecimal Object.
     */    
    public BudgetDecimal sumObjects(String fieldName) {
        return new BudgetDecimal(sum(fieldName, null, null));
    }//End Sum
    
    /** calculates the sum of the field in this QueryList.
     * @param fieldName field of bean whose sum has to be calculated.
     * @param arg argument for the getter method of field if it takes any argumnt,
     * else can be null.
     * @param value value for the argument, else can be null.
     * @return returns sum.
     */    
    public double sum(String fieldName, Class arg, Object value) {
        if(size() == 0) {
            return 0;
        }
        
        Object current;
        Field field = null;
        Method method = null;
        Class dataClass = get(0).getClass();
        double sum = 0;
        
        try{
            field = dataClass.getDeclaredField(fieldName);
            
            Class fieldClass = field.getType();
            String fieldTypeName = fieldClass.getName();
            if(! (fieldClass.equals(Integer.class) ||
            fieldClass.equals(Long.class) ||
            fieldClass.equals(Double.class) ||
            fieldClass.equals(Float.class) ||
            fieldClass.equals(BigDecimal.class) ||
            fieldClass.equals(BigInteger.class) ||
            fieldClass.equals(BudgetDecimal.class) ||
            fieldClass.equals(KualiDecimal.class) ||
            fieldTypeName.equalsIgnoreCase("int") ||
            fieldTypeName.equalsIgnoreCase("long") ||
            fieldTypeName.equalsIgnoreCase("float") ||
            fieldTypeName.equalsIgnoreCase("double") )) {
                throw new UnsupportedOperationException("Data Type not numeric");
            }
            
            if(! field.isAccessible()) {
                throw new NoSuchFieldException();
            }
        }catch (NoSuchFieldException noSuchFieldException) {
            try{
                String methodName = "get" + (fieldName.charAt(0)+"").toUpperCase()+ fieldName.substring(1);
                if(arg != null) {
                    Class args[]  = {arg};
                    method = dataClass.getMethod(methodName, args);
                }else {
                    method = dataClass.getMethod(methodName, null);
                }
            }catch (NoSuchMethodException noSuchMethodException) {
                noSuchMethodException.printStackTrace();
            }
        }
        
        
        
        for(int index = 0; index < size(); index++) {
            current  = get(index);
            
            try{
                if(field != null && field.isAccessible()) {
                    sum = sum + Double.parseDouble(((Comparable)field.get(current)).toString());
                }
                else{
                    Comparable dataValue;
                    if(value != null) {
                        Object values[] = {value};
                        dataValue = (Comparable)method.invoke(current, values);
//                        dblVal = Double.parseDouble(((Comparable)method.invoke(current, values)).toString());
//                        sum = sum + Double.parseDouble(((Comparable)method.invoke(current, values)).toString());
                    }else {
                        dataValue = (Comparable)method.invoke(current, null);
//                        dblVal = Double.parseDouble(((Comparable)method.invoke(current, null)).toString());
//                        sum = sum + Double.parseDouble(((Comparable)method.invoke(current, null)).toString());
                    }
                    if(dataValue!=null){
                        sum+=Double.parseDouble(dataValue.toString());
                    }
                    
                }
            }catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }catch (InvocationTargetException invocationTargetException) {
                invocationTargetException.printStackTrace();
            }
        }
        return sum;
    }
    
    /** calculates the sum of the field in this QueryList.
     * @param fieldName field of bean whose sum has to be calculated.
     * @param operator to get filrtered vector on which sum will be called.
     * @return returns sum.
     */    
    public double sum(String fieldName, Operator operator) {
        return filter(operator).sum(fieldName);
    }
    
    /** calculates the sum of the field in this QueryList.
     * @param fieldName field of bean whose sum has to be calculated.
     * @param operator to get filrtered vector on which sum will be called.
     * @return returns sum.
     */    
    public BudgetDecimal sumObjects(String fieldName, Operator operator) {
        return filter(operator).sumObjects(fieldName);
    }
    /** returns the field value in the base bean for the specified field.
     * @param fieldName fieldname whose value has to be got.
     * @param baseBean Bean containing the field.
     * @return value of the field.
     */    
    private Object getFieldValue(String fieldName, Object baseBean) {
        Field field = null;
        Method method = null;
        Class dataClass = baseBean.getClass();
        Object value = null;
        
        try{
            field = dataClass.getDeclaredField(fieldName);
            if(! field.isAccessible()) {
                throw new NoSuchFieldException();
            }
        }catch (NoSuchFieldException noSuchFieldException) {
            try{
                String methodName = "get" + (fieldName.charAt(0)+"").toUpperCase()+ fieldName.substring(1);
                method = dataClass.getMethod(methodName, null);
            }catch (NoSuchMethodException noSuchMethodException) {
                noSuchMethodException.printStackTrace();
            }
        }
        
        try{
            if(field != null && field.isAccessible()) {
                value = field.get(baseBean);
            }
            else{
                value = method.invoke(baseBean, null);
            }
        }catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }catch (InvocationTargetException invocationTargetException) {
            invocationTargetException.printStackTrace();
        }
        return value;
    }
    
    /** returns multiple field sort operator for the fields in the base bean in
     * either ascending or descending order.
     * @param field fields to sort.
     * @param baseBean base bean containing fields.
     * @param ascending sorting order.
     * true - ascending
     * false - descending
     * @return multiple field sort Operator
     */    
    private Operator getMultipleFieldSortOperator(String field[], Object baseBean, boolean ascending) {
        int fields = field.length;
        Or multipleFieldSortOperator;
        
        And andArray[] = new And[fields - 1];
        
        //Make all Relational Operators.
        RelationalOperator relationalOperator[] = new RelationalOperator[fields];
        for(int index = 0; index < fields; index++) {
            if(! ascending) {
                GreaterThan gt = new GreaterThan(field[index], (Comparable)getFieldValue(field[index], baseBean));
                relationalOperator[index] = gt;
            }else if(ascending) {
                LesserThan lt = new LesserThan(field[index], (Comparable)getFieldValue(field[index], baseBean));
                relationalOperator[index] = lt;
            }
        }
        
        //Generate Equals
        for(int index = 1; index < fields; index++) {
            Equals equals[] = new Equals[index];
            for(int count = 0; count < index; count++) {
                Equals eq = new Equals(field[count], (Comparable)getFieldValue(field[count], baseBean));
                equals[count] = eq;
            }
            
            //Combine Equals and make And Operators
            And and = null;
            boolean even = (equals.length % 2) == 0;
            for(int count = 0; count < equals.length; count++) {
                if(equals.length == 1) {
                    and = new And(equals[count], relationalOperator[index]);
                    andArray[index - 1] = and;
                    break;
                }else if(even && count == index - 1) {
                    and = new And(and, relationalOperator[index]);
                    andArray[index - 1] = and;
                    break;
                }else if(!even && count == index - 1) {
                    and = new And(and, relationalOperator[index]);
                    andArray[index - 1] = and;
                    break;
                }else {
                    and = new And(equals[count], equals[count + 1]);
                }
                
            }//End For count
        }//End For index
        
        //Combine Greater Than and Equals and make Or operators
        multipleFieldSortOperator = new Or(relationalOperator[0], andArray[0]);
        for(int index = 1; index < andArray.length; index++) {
            multipleFieldSortOperator = new Or(multipleFieldSortOperator, andArray[index]);
        }
        
        return multipleFieldSortOperator;
    }
    
    /**
     *  Overridden method of super class, java.util.Vector.
     * @return string representation of this.
     */
    public String toString(){
        return super.toString();
    }
    

}
