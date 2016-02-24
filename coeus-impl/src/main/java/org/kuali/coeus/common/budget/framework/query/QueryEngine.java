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

package org.kuali.coeus.common.budget.framework.query;


import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.budget.framework.query.operator.Operator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains different sets of Data Collection
 * in Hashtable. can execute query to get a
 * subSets of the Data Collection or
 * can add, update and delete data to a Data Collection.
 * This class is a Singleton since it has to be
 * accessed globally.
 */
public class QueryEngine {

    private static final Log LOG = LogFactory.getLog(QueryEngine.class);

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
     * @param operator Operator
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
     * @param operator Operator
     */
    public QueryList executeQuery(List dataList, Operator operator) {
        if(dataList == null) return new QueryList();
        return cloneListData(new QueryList(dataList).filter(operator));
    }
    
    /** returns a clone(copy) of dataList which is passed as the parameter.
     * @param queryList QueryList
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
     */
    public void removeData(Object baseBean) {
        List dataList = (List)dataCollection.get(baseBean.getClass());
        dataList.remove(baseBean);
    }
    
    /** removes a CoeusBean from the DataCollection with the specified key
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
     * where the Object has to be removed
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
                LOG.error(e.getMessage(), e);
            }catch (InvocationTargetException e) {
                LOG.error(e.getMessage(), e);
            }catch (NoSuchMethodException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        
    }
    
}

