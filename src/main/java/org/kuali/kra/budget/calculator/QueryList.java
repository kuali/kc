/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.budget.calculator;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.query.Equals;
import org.kuali.kra.budget.calculator.query.GreaterThan;
import org.kuali.kra.budget.calculator.query.LesserThan;
import org.kuali.kra.budget.calculator.query.Operator;
import org.kuali.kra.budget.calculator.query.RelationalOperator;
import org.kuali.rice.core.api.util.type.KualiDecimal;


/**
 * This class...
 */
public final class QueryList<E> implements List<E>, RandomAccess, Cloneable, Serializable {

    private static final long serialVersionUID = -3215265492607686197L;
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(QueryList.class);
    //using delegation to make it easier to swap out underlying implementation
    //also helps with possible inheritance bugs...
    //cannot make final because of clone otherwise this should always assumed to be non-null upon construction
    private /*final*/ ArrayList<E> backingList;
    
    public QueryList() {
        this.backingList = new ArrayList<E>();
    }
    
    public QueryList(Collection<E> dataList) {
        this.backingList = new ArrayList<E>(dataList);
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
    @SuppressWarnings("rawtypes")
    public boolean sort(String fieldName, boolean ascending, boolean ignoreCase) {
        Object current, next;
        int compareValue = 0;
        Field field = null;
        Method method = null;
        if (this.size() == 0) {
            return false;
        }
        Class dataClass = get(0).getClass();
        String methodName=null;
        try{
            field = dataClass.getDeclaredField(fieldName);
            if(! field.isAccessible()) {
                throw new NoSuchFieldException();
            }
        }catch (NoSuchFieldException noSuchFieldException) {
            //field not available. Use method invokation.
            try{
                methodName = "get" + (fieldName.charAt(0)+"").toUpperCase()+ fieldName.substring(1);
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
                        Comparable thisObj = null;
                        Comparable otherObj = null;
                        if(methodName!=null){
                            Method thisObjMethod = current.getClass().getMethod(methodName, null);
                            Method otherObjMethod = next.getClass().getMethod(methodName, null);
                            thisObj = (Comparable)thisObjMethod.invoke(current, null);
                            otherObj = (Comparable)otherObjMethod.invoke(next, null);
                        }else{
                            thisObj = (Comparable)method.invoke(current, null);
                            otherObj = (Comparable)method.invoke(next, null);
                        }
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
                }catch (IllegalAccessException illegalAccessException) {
                    LOG.warn(illegalAccessException.getMessage());
                    return false;
                }catch (InvocationTargetException invocationTargetException) {
                    LOG.warn(invocationTargetException.getMessage(),invocationTargetException);
                    return false;
                }
                catch (SecurityException e) {
                    LOG.warn(e.getMessage(),e);
                    return false;
                }
                catch (NoSuchMethodException e) {
                    LOG.warn(e.getMessage(),e);
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
                
            }
        }
        return true; 
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
            if(! (fieldClass.equals(Integer.class) ||
            fieldClass.equals(Long.class) ||
            fieldClass.equals(Double.class) ||
            fieldClass.equals(Float.class) ||
            fieldClass.equals(BigDecimal.class) ||
            fieldClass.equals(BigInteger.class) ||
            fieldClass.equals(BudgetDecimal.class) ||
            fieldClass.equals(KualiDecimal.class) ||
            fieldClass.equals(int.class) ||
            fieldClass.equals(long.class) ||
            fieldClass.equals(float.class) ||
            fieldClass.equals(double.class) )) {
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
                    }else {
                        dataValue = (Comparable)method.invoke(current, null);
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
    
    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public QueryList<E> clone() {
        
        final QueryList<E> ql;
        
        try {
            ql = (QueryList<E>) super.clone();
        } catch (CloneNotSupportedException e) { 
            throw new AssertionError("not Cloneable");
        }

        ArrayList<E> bl = (ArrayList<E>) this.backingList.clone();
        ql.backingList = new ArrayList(bl);
        
        return ql;
    }
    
    //delegate methods.
    
    /** {@inheritDoc} */
    public boolean add(E o) {
        return this.backingList.add(o);
    }

    /** {@inheritDoc} */
    public void add(int index, E element) {
        this.backingList.add(index, element);
    }

    /** {@inheritDoc} */
    public boolean addAll(Collection<? extends E> c) {
        return this.backingList.addAll(c);
    }

    /** {@inheritDoc} */
    public boolean addAll(int index, Collection<? extends E> c) {
        return this.backingList.addAll(index, c);
    }

    /** {@inheritDoc} */
    public void clear() {
        this.backingList.clear();
    }

    /** {@inheritDoc} */
    public boolean contains(Object o) {
        return this.backingList.contains(o);
    }

    /** {@inheritDoc} */
    public boolean containsAll(Collection<?> c) {
        return this.backingList.containsAll(c);
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        return this.backingList.equals(o);
    }

    /** {@inheritDoc} */
    public E get(int index) {
        return this.backingList.get(index);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return this.backingList.hashCode();
    }

    /** {@inheritDoc} */
    public int indexOf(Object o) {
        return this.backingList.indexOf(o);
    }

    /** {@inheritDoc} */
    public boolean isEmpty() {
        return this.backingList.isEmpty();
    }

    /** {@inheritDoc} */
    public Iterator<E> iterator() {
        return this.backingList.iterator();
    }

    /** {@inheritDoc} */
    public int lastIndexOf(Object o) {
        return this.backingList.lastIndexOf(o);
    }

    /** {@inheritDoc} */
    public ListIterator<E> listIterator() {
        return this.backingList.listIterator();
    }

    /** {@inheritDoc} */
    public ListIterator<E> listIterator(int index) {
        return this.backingList.listIterator(index);
    }

    /** {@inheritDoc} */
    public E remove(int index) {
        return this.backingList.remove(index);
    }

    /** {@inheritDoc} */
    public boolean remove(Object o) {
        return this.backingList.remove(o);
    }

    /** {@inheritDoc} */
    public boolean removeAll(Collection<?> c) {
        return this.backingList.removeAll(c);
    }

    /** {@inheritDoc} */
    public boolean retainAll(Collection<?> c) {
        return this.backingList.retainAll(c);
    }

    /** {@inheritDoc} */
    public E set(int index, E element) {
        return this.backingList.set(index, element);
    }

    /** {@inheritDoc} */
    public int size() {
        return this.backingList.size();
    }

    /** {@inheritDoc} */
    public List<E> subList(int fromIndex, int toIndex) {
        return this.backingList.subList(fromIndex, toIndex);
    }

    /** {@inheritDoc} */
    public Object[] toArray() {
        return this.backingList.toArray();
    }

    /** {@inheritDoc} */
    public <T> T[] toArray(T[] a) {
        return this.backingList.toArray(a);
    }
    
    /** {@inheritDoc} */
    @Override
    public String toString() {
        return this.backingList.toString();
    }

    public List<E> toArrayList() {
        return this.backingList;
    }
}
