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
package org.kuali.kra.award.awardhierarchy.sync.helpers;


import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncException;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncableProperty;
import org.kuali.kra.award.awardhierarchy.sync.service.AwardSyncServiceImpl;
import org.kuali.kra.award.awardhierarchy.sync.service.AwardSyncUtilityService;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

/**
 * Base class for award hierarchy sync data access objects.
 */
public abstract class AwardSyncHelperBase implements AwardSyncHelper {
    
    protected final Log LOG = LogFactory.getLog(AwardSyncServiceImpl.class);
    /**
     * Delimiter used between fields for the object data description
     */
    protected final String DELIMITER = " : ";
    
    private AwardSyncUtilityService awardSyncUtilityService;
   
    /**
     * @see org.kuali.kra.award.awardhierarchy.sync.helpers.AwardSyncHelper#createAwardSyncChange(org.kuali.kra.award.awardhierarchy.sync.AwardSyncType, org.kuali.rice.krad.bo.BusinessObject, java.lang.String, java.lang.String)
     */
    public AwardSyncChange createAwardSyncChange(AwardSyncType syncType, PersistableBusinessObject syncableObject, 
            String awardAttrName, String boAttrName) throws NoSuchFieldException, IntrospectionException, 
            IllegalAccessException, InvocationTargetException {
        AwardSyncChange syncChange = new AwardSyncChange();
        syncChange.setClassName(syncableObject.getClass().getCanonicalName());
        syncChange.setAttrName(awardAttrName);
        syncChange.setSyncType(syncType.getSyncValue());
        syncChange.setObjectDesc(getObjectDesc(syncableObject, boAttrName));
        syncChange.setDataDesc(getDataDesc(syncableObject, boAttrName));
        syncChange.setXmlExport(buildXmlExport(syncableObject, boAttrName));
        return syncChange;
    }
    
    /**
     * Returns the object description for this object type.
     * @param syncableObject
     * @param attrName
     * @return
     */
    protected abstract String getObjectDesc(PersistableBusinessObject syncableObject, String attrName);
    /**
     * Returns the data description for this object type.
     * @param syncableObject
     * @param attrName
     * @return
     */
    protected abstract String getDataDesc(PersistableBusinessObject syncableObject, String attrName);
    
    /**
     * @see org.kuali.kra.award.awardhierarchy.sync.helpers.AwardSyncHelper#buildXmlExport(org.kuali.kra.award.awardhierarchy.sync.AwardHierarchySyncable, java.lang.String)
     */
    public AwardSyncXmlExport buildXmlExport(PersistableBusinessObject syncable, String attrName) 
        throws NoSuchFieldException, IntrospectionException,
        IllegalAccessException, InvocationTargetException {
        return buildXmlExport(syncable, attrName, null, null, true);
    }
    
    /**
     * Using annotations set on the class and reflection creates a map based hierarchy of objects
     * from the award attribute down to the BO this is initially called on.
     * 
     * @param syncable
     * @param attrName
     * @param childAttr
     * @param childExport
     * @param walkParents
     * @return
     * @throws NoSuchFieldException
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @SuppressWarnings("unchecked")
    protected AwardSyncXmlExport buildXmlExport(PersistableBusinessObject syncable, String attrName, 
            String childAttr, AwardSyncXmlExport childExport, boolean walkParents) 
        throws NoSuchFieldException, IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class clazz = syncable.getClass();
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        AwardSyncXmlExport parentExport = null;
        AwardSyncXmlExport xmlExport = new AwardSyncXmlExport();
        xmlExport.setClassName(syncable.getClass().getName());
        xmlExport.setKeys(new HashMap<String, Object>());
        xmlExport.setValues(new HashMap<String, Object>());
        //if this is the parent of another BO we will get the childAttr and the childExport
        //and to make the export tree complete we need to add the attr and the export to our values
        if (childExport != null) {
            xmlExport.getValues().put(childAttr, childExport);
        }
        //if we should walk to parent nodes then the syncable BO is part of the 
        //key to the original object because we are either on the actual synced BO or 
        //walking up the object graph.
        if (walkParents) {
            xmlExport.setPartOfObjectKey(true);
        }
        //loop through all properties on the bean and if the property is annotated as a syncable property
        //then export that property
        for (PropertyDescriptor propDescriptor : beanInfo.getPropertyDescriptors()) {
            Field propertyField = findField(clazz, propDescriptor.getName());
            if (propertyField != null) {
                AwardSyncableProperty annotation = propertyField.getAnnotation(AwardSyncableProperty.class);
                if (annotation != null) {
                    Object propertyValue = propDescriptor.getReadMethod().invoke(syncable);
                    if (walkParents && annotation.parent()) {
                        parentExport = buildXmlExport((PersistableBusinessObject) propertyValue, null, annotation.parentProperty(), xmlExport, true);
                        parentExport.setAddIfNotFound(false);
                    } else if (!annotation.parent()) {
                        Object mapValue = getValueForExport(propertyValue);
                        if (annotation.key()) {
                            xmlExport.getKeys().put(propDescriptor.getName(), mapValue);
                        } else if ((attrName == null || StringUtils.equals(attrName, propDescriptor.getName()))
                                && childAttr == null) {
                            //if we don't have a specific attribute or this is the attribute we want to save
                            //and this is not a parent as we do not want to save parent values
                            xmlExport.getValues().put(propDescriptor.getName(), mapValue);
                        }
                    }
                }
            }
        }
        if (parentExport != null) {
            return parentExport;
        } else {
            return xmlExport;
        }
    }
    
    /**
     * Returns a value for export based on the property type. If it is a collection, the
     * return will be a list of {@link AwardSyncXmlExport}. If the value is a simple
     * business object it will return an AwardSyncXmlExport. Otherwise the return will
     * be the value itself.
     * @param propertyValue
     * @return
     * @throws NoSuchFieldException
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @SuppressWarnings("unchecked")
    protected Object getValueForExport(Object propertyValue) 
        throws NoSuchFieldException, IntrospectionException, IllegalAccessException, InvocationTargetException {
        Object mapValue = null;
        if (propertyValue instanceof Collection) {
            mapValue = buildXmlExport((Collection) propertyValue);
        } else if (propertyValue instanceof PersistableBusinessObject) {
            mapValue = buildXmlExport((PersistableBusinessObject) propertyValue, null, null, null, false);
        } else {
            mapValue = propertyValue;
        }
        return mapValue;
    }
    
    /**
     * Loops through a collection of business objects to return a list of {@link AwardSyncXmlExport}.
     * @param syncables
     * @return
     * @throws NoSuchFieldException
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @SuppressWarnings("unchecked")
    protected List<AwardSyncXmlExport> buildXmlExport(Collection syncables) 
        throws NoSuchFieldException, IntrospectionException, IllegalAccessException, InvocationTargetException {
        List<AwardSyncXmlExport> xmls = new ArrayList<AwardSyncXmlExport>();
        for (Object object : (Collection) syncables) {
            if (object instanceof PersistableBusinessObject) {
                xmls.add(buildXmlExport((PersistableBusinessObject) object, null, null, null, false));
            }
        } 
        return xmls;
    }
    
    /**
     * Recursively ascends the class tree to find the property field.
     * This is done because you cannot get the field for a private
     * property unless called on the defining class itself.
     * @param clazz
     * @param propertyName
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Field findField(Class clazz, String propertyName) {
        if (clazz != null) {
            Field retVal = null; 
            try {
                retVal = clazz.getDeclaredField(propertyName);
            } catch (SecurityException e) {
                //exceptions are likely as we walk the class tree to find the
                //property. As the child class won't necessarily have the property.
                LOG.info("Security exception when trying to find " + propertyName + " for class" + clazz.getSimpleName(), e);
            } catch (NoSuchFieldException e) { 
                //this is so likely given we are walking a tree of objects to find the property, do nothing
            }
            if (retVal != null) {
                return retVal;
            } else {
                return findField(clazz.getSuperclass(), propertyName);
            }
        } else {
            return null;
        }
    }    
    
    /**
     * @see org.kuali.kra.award.awardhierarchy.sync.helpers.AwardSyncHelper#applySyncChange(org.kuali.kra.award.home.Award, org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange, boolean)
     */
    public void applySyncChange(Award award, AwardSyncChange change) 
        throws NoSuchFieldException, IntrospectionException, IllegalAccessException, InvocationTargetException, 
        ClassNotFoundException, NoSuchMethodException, InstantiationException, AwardSyncException {
        applySyncChange(award, change, change.getAttrName(), change.getXmlExport());
    }
        
    /**
     * Applies the xmlExport keys and values to the object. This will recursively descend the tree of objects defined in the
     * xmlExport by calling {@link #setValuesOnSyncable}.
     * @param object
     * @param change
     * @param attrName
     * @param xmlExport
     * @throws NoSuchFieldException
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     */
    @SuppressWarnings("unchecked")
    protected void applySyncChange(PersistableBusinessObject object, AwardSyncChange change, String attrName, AwardSyncXmlExport xmlExport) 
        throws NoSuchFieldException, IntrospectionException, 
            IllegalAccessException, InvocationTargetException, ClassNotFoundException, 
            NoSuchMethodException, InstantiationException {   
        Object propertyValue = getPropertyValue(object, attrName);
        if (propertyValue instanceof Collection) {
            Collection items = (Collection) propertyValue;
            PersistableBusinessObject matchedBo = getAwardSyncUtilityService().findMatchingBo(items, xmlExport.getKeys());
            Map<String, Object> values = xmlExport.getValues();
            if (StringUtils.equals(change.getSyncType(), AwardSyncType.ADD_SYNC.getSyncValue())) {
                if (matchedBo == null) {
                    if (xmlExport.isAddIfNotFound()) {
                        matchedBo = createNewItem(change, xmlExport);
                        items.add(matchedBo);
                    }
                } else {
                    setValuesOnSyncable(matchedBo, values, change);
                }           
            } else if (StringUtils.equals(change.getSyncType(), AwardSyncType.DELETE_SYNC.getSyncValue())) {
                items.remove(matchedBo);
            }
        } else if (StringUtils.equals(change.getSyncType(), AwardSyncType.ADD_SYNC.getSyncValue())) {
            if (propertyValue != null && !getAwardSyncUtilityService().doKeyValuesMatch((PersistableBusinessObject) propertyValue, xmlExport.getKeys())) {
                if (xmlExport.isAddIfNotFound()) {
                    Object newObject = createNewItem(change, xmlExport);
                    Method setter = getPropertyDescriptor(object, attrName).getWriteMethod();
                    setter.invoke(object, newObject);
                }
            } else {
                setValuesOnSyncable((PersistableBusinessObject) propertyValue, xmlExport.getValues(), change);
            }
        } else if (propertyValue != null && getAwardSyncUtilityService().doKeyValuesMatch((PersistableBusinessObject) propertyValue, xmlExport.getKeys())) {
            Method setter = getPropertyDescriptor(object, attrName).getWriteMethod();
            setter.invoke(object, (Object[]) null);
        }
    }
    
    /**
     * Creates all items defined by xmlExports and adds them to the attribute.
     * @param object
     * @param change
     * @param attrName
     * @param xmlExports
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchFieldException
     * @throws IntrospectionException
     */
    protected void applySyncChange(PersistableBusinessObject object, AwardSyncChange change, String attrName, 
            Collection<AwardSyncXmlExport> xmlExports) 
        throws ClassNotFoundException, NoSuchMethodException, InstantiationException, 
        IllegalAccessException, InvocationTargetException, NoSuchFieldException, IntrospectionException {
        Collection<Object> newCollection = new ArrayList<Object>();
        for (AwardSyncXmlExport xmlExport : xmlExports) {
            if (xmlExport.isAddIfNotFound()) {
                Object newObject = createNewItem(change, xmlExport);
                newCollection.add(newObject);
            }
        } 
        Method setter = getPropertyDescriptor(object, attrName).getWriteMethod();
        setter.invoke(object, newCollection);
    }
    
    /**
     * Creates a new item based on the key values and values passed in.
     * @param change
     * @param xmlExport
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchFieldException
     * @throws IntrospectionException
     */
    @SuppressWarnings("unchecked")
    protected PersistableBusinessObject createNewItem(AwardSyncChange change, AwardSyncXmlExport xmlExport)
        throws ClassNotFoundException, NoSuchMethodException, InstantiationException, 
            IllegalAccessException, InvocationTargetException, NoSuchFieldException, IntrospectionException {
        String className = xmlExport.getClassName();
        Map<String, Object> keyValues = xmlExport.getKeys();
        Map<String, Object> values = xmlExport.getValues();
        Class subClazz = Class.forName(className);
        Constructor constructor = subClazz.getConstructor((Class[]) null);
        PersistableBusinessObject newObject = (PersistableBusinessObject) constructor.newInstance((Object[]) null);
        setValuesOnSyncable(newObject, keyValues, change);
        setValuesOnSyncable(newObject, values, change);
        return newObject;
    }
    
    /**
     * Recursively sets values on this syncable working way down in the object tree found in the values map.
     * @param syncable
     * @param values
     * @param change
     * @throws NoSuchFieldException
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     */
    @SuppressWarnings("unchecked")
    protected void setValuesOnSyncable(PersistableBusinessObject syncable, Map<String, Object> values, AwardSyncChange change) 
        throws NoSuchFieldException, IntrospectionException, IllegalAccessException, 
            InvocationTargetException, ClassNotFoundException, NoSuchMethodException, 
            InstantiationException {
        Class clazz = syncable.getClass();
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        boolean setEntry = false;
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            setEntry = false;
            for (PropertyDescriptor propDescriptor : beanInfo.getPropertyDescriptors()) {
                if (StringUtils.equals(propDescriptor.getName(), entry.getKey())) {
                    if (entry.getValue() instanceof AwardSyncXmlExport) {
                        AwardSyncXmlExport xmlExport = (AwardSyncXmlExport) entry.getValue();
                        applySyncChange(syncable, change, entry.getKey(), xmlExport);
                        setEntry = true;
                    } else if (Collection.class.isAssignableFrom(propDescriptor.getPropertyType()) 
                            && entry.getValue() instanceof List) {
                        applySyncChange(syncable, change, entry.getKey(), (Collection<AwardSyncXmlExport>)entry.getValue());
                        setEntry = true;
                    } else {
                        Method setter = propDescriptor.getWriteMethod();
                        setter.invoke(syncable, entry.getValue());
                        setEntry = true;
                    }
                }
            }
            if (!setEntry) {
                throw new NoSuchFieldException();
            }
        }
    }    
    
    /**
     * Finds the bean property descriptor for the names attribute.
     * @param object
     * @param attributeName
     * @return
     * @throws IntrospectionException
     */
    @SuppressWarnings("unchecked")
    protected PropertyDescriptor getPropertyDescriptor(PersistableBusinessObject object, String attributeName) throws IntrospectionException {
        Class clazz = object.getClass();
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        for (PropertyDescriptor propDescriptor : beanInfo.getPropertyDescriptors()) {
            if (StringUtils.equals(propDescriptor.getName(), attributeName)) {
                return propDescriptor;
            }
        }
        return null;
    }

    /**
     * Finds the property descriptor for the named attribute and returns the value retrieved using the read method
     * @param object
     * @param attributeName
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    protected Object getPropertyValue(PersistableBusinessObject object, String attributeName) 
        throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
        for (PropertyDescriptor propDesc : beanInfo.getPropertyDescriptors()) {
            if (StringUtils.equals(propDesc.getName(), attributeName)) {
                Method getter = propDesc.getReadMethod();
                return getter.invoke(object);
            }
        }
        return null;
    }

    public AwardSyncUtilityService getAwardSyncUtilityService() {
        return awardSyncUtilityService;
    }

    public void setAwardSyncUtilityService(AwardSyncUtilityService awardSyncUtilityService) {
        this.awardSyncUtilityService = awardSyncUtilityService;
    }
        
}

