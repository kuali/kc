/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.AwardSynchronizable;
import org.kuali.kra.award.bo.AwardTemplate;
import org.kuali.kra.award.service.AwardTemplateSyncService;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * This class is the implementation of AwardTemplateSyncService.
 */
public class AwardTemplateSyncServiceImpl implements AwardTemplateSyncService {

    private BusinessObjectService businessObjectService;
    private static final Log LOG = LogFactory.getLog(AwardTemplateSyncServiceImpl.class);

    /**
     * 
     * This method is used to sync the main AwardTemplate object to Award object.
     * It sync all the declared fields first and then sync all list objects
     * @param templateObject
     * @param awardObject
     * @param clazz
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private void syncToAward(AwardTemplate templateObject, Award awardObject, Class clazz) throws Exception{
        syncDeclaredFields(templateObject, awardObject, clazz);
        syncListObjects(templateObject, awardObject, clazz);
    }

    /**
     * This method is to sync the list objects. It goes through the declared methods of AwardBase class and
     * find out all required properties that need to be synced. Invoke appropriate method from AwardTemplate 
     * object, create new objects list for Award. It then then syncs the declared fields from AwardTemplate 
     * list object to the new list.
     * 
     * @param templateObject
     * @param awardObject
     * @param clazz
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
    private void syncListObjects(AwardTemplate templateObject, Award awardObject, Class clazz) 
            throws Exception {
        Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            String methodName = methods[i].getName();
            if (methods[i].getReturnType().isAssignableFrom(List.class) && methodName.startsWith("get")) {
                invokeListMethodAndSyncObjects(templateObject, awardObject,  methodName);
            }
        }
    }

    /**
     * This method is to invoke the list property from AwardTemplate object.
     * 
     * @param templateObject
     * @param awardObject
     * @param clazz
     * @param methodName
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private void invokeListMethodAndSyncObjects(AwardTemplate templateObject, Award awardObject,  String methodName) 
        throws Exception {
        Method method = templateObject.getClass().getMethod(methodName, (Class[]) null);
        List<AwardSynchronizable> list = (List) method.invoke( templateObject, (Object[])null);
        if (list.size() > 0) {
            copyTemplateListPropToAward(awardObject, methodName, list);
        }
    }

    /**
     * This method is to copy objects from the list property of AwardTemplate to list property of Award
     * 
     * @param awardObject
     * @param clazz
     * @param methodName
     * @param list
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private void copyTemplateListPropToAward(Award awardObject, String methodName, List<AwardSynchronizable> list) 
            throws Exception {
        AwardSynchronizable syncObject = list.get(0);
        Class clazzInList = syncObject.getAwardSyncClass();
//        if (clazzInList.isAssignableFrom(AwardSynchronizable.class)) {
            Class syncBaseClass = syncObject.getSyncBaseClass();
            List listToSync = createNewListWithSyncedProps(syncBaseClass, list, clazzInList,awardObject);
            String fieldName = StringUtils.uncapitalize(StringUtils.substring(methodName, 3));
            BeanUtils.setProperty(awardObject, fieldName, listToSync);
//        }
    }

    /**
     * This method is to create a new list of objects and copy properties from AwardTemplate list property
     * 
     * @param name of the base class needed to be synced 
     * @param list of objects from AwardTemplate
     * @param class which needs to created as new instance and add to the list in Award.
     * @return List of synced objects
     * @throws Exception from reflection APIs
     */
    @SuppressWarnings("unchecked")
    private List<Object> createNewListWithSyncedProps(Class clazz, List<AwardSynchronizable> list, Class clazzInList,Award award) 
            throws Exception {
        List<Object> listToSync = new ArrayList<Object>();
        for (Object templateListObject : list) {
            Object newObjectToSync = clazzInList.newInstance();
            syncDeclaredFields(templateListObject, newObjectToSync, clazz);
            BeanUtils.setProperty(newObjectToSync, "award", award);
            listToSync.add(newObjectToSync);
        }
        return listToSync;
    }

    /**
     * This method is used to sync member properties of an award template object to an award object
     * 
     * @param source
     * @param destination
     * @param clazz
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private void syncDeclaredFields(Object source, Object destination, Class clazz) 
            throws Exception {
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (!fields[i].getType().isAssignableFrom(BusinessObject.class) && 
                    !fields[i].getType().isAssignableFrom(List.class)) {
                Object value = BeanUtils.getProperty(source, fields[i].getName());
                BeanUtils.copyProperty(destination, fields[i].getName(), value);
            }
        }
    }

    /**
     * 
     * This method checks whether any of the list properties in Award object has already data in it. 
     * If yes, it should not sync any of the details from the list but sync only declared properties.
     * @param Award object
     * @return true, if sync needs to be applied to all list properties, else false.
     */
    private boolean isSyncAll(Award awardObject) {
        boolean syncAll = awardObject.getAwardComments().isEmpty() && awardObject.getAwardReportTermItems().isEmpty()
                && awardObject.getSponsorContacts().isEmpty() && awardObject.getAwardSponsorTerms().isEmpty();

        return syncAll;
    }

    /**
     * This method is to fetch the award template by using template code from Award object.
     * 
     * @param Award object
     * @return AwardTemplate object
     */
    private AwardTemplate fetchAwardTemplate(Award award) {
        award.refreshReferenceObject("awardTemplate");
        AwardTemplate awardTemplate = award.getAwardTemplate();
        if (awardTemplate == null && award.getTemplateCode() != null) {
            Map<String, Integer> primaryKeys = new HashMap<String, Integer>();
            primaryKeys.put("templateCode", award.getTemplateCode());
            awardTemplate = (AwardTemplate) businessObjectService.findByPrimaryKey(AwardTemplate.class, primaryKeys);
        }
        return awardTemplate;
    }

    /**
     * Gets the businessObjectService attribute.
     * 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * @see org.kuali.kra.award.service.AwardTemplateSyncService#syncToAward(java.lang.Object, java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    public boolean syncToAward(Award award) {
        boolean success;
        try {
            Class syncClass = award.getSyncBaseClass();
            AwardTemplate awardTemplate = fetchAwardTemplate(award);
            if (isSyncAll(award))
                syncToAward(awardTemplate, award, syncClass);
            else
                syncDeclaredFields(awardTemplate, award, syncClass);
            success=true;
        }catch (Exception e) {
            success=false;
            LOG.error(e.getCause(),e);
        }
        return success;
    }

    /**
     * 
     * @see org.kuali.kra.award.service.AwardTemplateSyncService#syncToAward(org.kuali.kra.award.bo.Award, java.lang.String)
     */
    public boolean syncToAward(Award award, String syncPropertyName) {
        boolean success;
        try {
            String methodName = "get" + StringUtils.capitalize(syncPropertyName);
            invokeListMethodAndSyncObjects(fetchAwardTemplate(award), award, methodName);
            success=true;
        }catch (Exception e) {
            success=false;
            LOG.error(e.getCause(),e);
        }
        return success;
    }

}
