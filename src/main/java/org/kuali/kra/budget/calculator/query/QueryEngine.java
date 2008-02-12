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


import java.lang.reflect.InvocationTargetException;
import java.util.*;

import org.apache.commons.beanutils.PropertyUtils;
import org.kuali.core.document.Document;
import org.kuali.kra.budget.calculator.QueryList;

/**
 * Contains different sets of Data Collection
 * in Hashtable. can execute query to get a
 * subSets of the Data Collection or
 * can add, update and delete data to a Data Collection.
 * This class is a Singleton since it has to be
 * accessed globally.
 */
public class QueryEngine {
    
    private Map dataCollection;
    
    /** Creates a new instance of QueryEngine */
    public QueryEngine() {
        dataCollection = new HashMap();
    }
    
    
    /** returns a subset of Data Collection. This subset is got by applying the
     * operator parameter. Operator can be a combination of multiple logical and
     * relational conditions.
     * @return Query List
     * @param beanClass bean Class
     * @param key dataCollection key
     * @param operator Operator
     * @CoeusException
     */
    public QueryList executeQuery(Class beanClass, Operator operator){
        List dataList = (List)dataCollection.get(beanClass);
        if(dataList == null) return new QueryList();
        return cloneListData(new QueryList(dataList).filter(operator));
    }
    
    /**
     * returns a subset of Data Collection. This subset is got by applying the
     * operator parameter. Operator can be a combination of multiple logical and
     * relational conditions.
     * @return returns results in this Query List
     * @param collectionKey collection key
     * @param key data key
     * @param operator Operator
     */
    public QueryList executeQuery(Object key, Operator operator) {
        List dataList = (List)dataCollection.get(key);
        if(dataList == null) return new QueryList();
        return cloneListData(new QueryList(dataList).filter(operator));
    }
    
    /**
     * returns a subset of Data Collection. This subset is got by applying the
     * operator parameter. Operator can be a combination of multiple logical and
     * relational conditions.
     * @return returns results in this Query List
     * @param collectionKey collection key
     * @param key data key
     * @param operator Operator
     */
    public QueryList executeQuery(List dataList, Operator operator) {
        if(dataList == null) return new QueryList();
        return cloneListData(new QueryList(dataList).filter(operator));
    }
    
    /** returns a clone(copy) of dataList which is passed as the parameter.
     * @param queryList QueryList
     * @CoeusException
     * @return QueryList
     */
    public QueryList cloneListData(QueryList queryList){
        QueryList resultClone = new QueryList();
        if(queryList == null) return resultClone;
        //return the clone not the original instances
        for(int index = 0; index < queryList.size(); index++) {
            resultClone.add(ObjectCloner.deepCopy(queryList.get(index)));
        }
        return resultClone;
    }
    
    /** returna all beans of this Class type
     * @return QueryList
     * @param key dataCollection key
     * @param beanClass beanClass
     * @CoeusException
     */
    public QueryList getDetails(Object key, Class beanClass) {
        List dataList = (List)dataCollection.get(beanClass);
        if(dataList == null) return new QueryList();
        return cloneListData(new QueryList(dataList));
    }
    
    /** returna all beans of this Class type
     * @return QueryList
     * @param key dataCollection key
     * @param collectionKey collectionKey
     * @CoeusException
     */
    public QueryList getDetails(Object key, Object collectionKey){
        List dataList = (List)dataCollection.get(collectionKey);
        if(dataList == null) return new QueryList();
        return cloneListData(new QueryList(dataList));
    }
    
    /** adds a DatCollection(i.e. Hashtable) to QueryEngine.
     * @param key DatCollection key
     * @param value DataCollection(i.e. Hashtable)
     */
    public void addDataCollection(Object key, List value) {
        dataCollection.put(key, value);
    }

    /** removes a DatCollection(i.e. Hashtable) with the specified key from QueryEngine.
     * @param key key
     */
    public void removeDataCollection(Object key) {
        dataCollection.remove(key);
    }
    
    /** adds a CoeusBean to the DataCollection with the specified key
     * @param baseBean BaseBean
     * @param key key of the DataCollection
     */
    public void addData(Object baseBean) {
        List dataList = (List)dataCollection.get(baseBean.getClass());
        if(dataList == null) dataCollection.put(baseBean.getClass(), new QueryList());
        
        ((QueryList)dataCollection.get(baseBean.getClass())).add(baseBean);
    }

    /** adds a CoeusBean to the DataCollection with the specified key
     * @param baseBean BaseBean
     * @param key key of the DataCollection
     */
    public void addData(Object key, Object baseBean) {
        List dataList = (List)dataCollection.get(key);
        if(dataList == null) dataCollection.put(key, new ArrayList());
        
        ((List)dataCollection.get(key)).add(baseBean);
    }
    
    /** removes CoeusBean from the DataCollection with the specified key
     * @param baseBean BaseBean
     * @param key DataCollection key
     */
    public void removeData(Object baseBean) {
        List dataList = (List)dataCollection.get(baseBean.getClass());
        dataList.remove(baseBean);
    }
    
    /** removes a CoeusBean from the DataCollection with the specified key
     * @param key DataCollection key
     * @param beanClass Class type of the bean to locate QueryList from
     * where the Object has to be removed
     * @param index index at which the Object has to be removed.
     */
    public void removeData(Class beanClass,  int index) {
        List dataList = (List)dataCollection.get(beanClass);
        dataList.remove(index);
    }
    
    /** removes a CoeusBean from the DataCollection with the specified key
     * @param key DataCollection key
     * @param beanClass Class type of the bean to locate QueryList from
     * where the Object has to be removed
     * @param index index at which the Object has to be removed.
     */
    public void removeData(Object key, Operator operator) {
        if(key == null || operator == null) return ;
        List dataList = (List)dataCollection.get(key);
        if(dataList == null || dataList.size() == 0) return ;
        
        Object  baseBean = null;
        for(int index = 0; index < dataList.size() ; index++){
            baseBean = dataList.get(index);
            if(baseBean == null) continue;
            if(operator.getResult(baseBean) == true){
                dataList.remove(index);
                index--;
            }
        }
    }
    
    /** Removes bean instances from query engine.
     * @param key data collection key.
     * @param beanClass bean class.
     * @param operator operator.
     */
    public void removeData(Class beanClass, Operator operator) {
        if(operator == null) return ;
        
        List dataList = (List)dataCollection.get(beanClass);
        if(dataList == null || dataList.size() == 0) return ;
        
        Object  baseBean = null;
        for(int index = 0; index < dataList.size() ; index++){
            baseBean = dataList.get(index);
            if(baseBean == null) continue;
            if(operator.getResult(baseBean) == true){
                dataList.remove(index);
                index--;
            }
        }
    }


    public void addDataCollection(Object object, String... strings ) {
        for (int i = 0; i < strings.length; i++) {
            try {
                List dataList = (List)PropertyUtils.getProperty(object, strings[i]);
                if(dataList!=null && !dataList.isEmpty()){
                    dataCollection.put(dataList.get(0).getClass(), dataList);
                }
            }catch (IllegalAccessException e) {
                e.printStackTrace();
            }catch (InvocationTargetException e) {
                e.printStackTrace();
            }catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        
    }
    
}

