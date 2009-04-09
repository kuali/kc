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
package org.kuali.kra.irb.dao.ojb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.core.bo.PersistableBusinessObject;
import org.kuali.core.dao.LookupDao;
import org.kuali.core.dao.ojb.PlatformAwareDaoBaseOjb;
import org.kuali.core.service.DataDictionaryService;
import org.kuali.core.util.OjbCollectionAware;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.dao.ProtocolDao;
import org.kuali.kra.irb.lookup.ProtocolLookupConstants;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.rice.kns.util.KNSConstants;

public class ProtocolDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, ProtocolDao {

    private LookupDao lookupDao;
    private DataDictionaryService dataDictionaryService;
    private Map<String, String> searchMap = new HashMap<String, String>();
    private List<String> investigatorRole = new ArrayList<String>();
    private List<String> personRole = new ArrayList<String>();
    private Map<String, String> baseLookupFields;
    private Map<String, CritField> personCollectionFields;
    private List<String> excludedFields = new ArrayList<String>();
    private List<String> collectionFieldNames = new ArrayList<String    >();
    
    public ProtocolDaoOjb() {
        super();
        initialization();
    }

    /**
     * 
     * @see org.kuali.kra.irb.dao.ProtocolDao#getProtocols(java.util.Map)
     */
    public List<Protocol> getProtocols(Map<String, String> fieldValues) {
        Criteria crit = new Criteria();
        baseLookupFields = new HashMap<String, String>();
        personCollectionFields = new HashMap<String, CritField>();
        setupCritMaps(fieldValues);
        if (!baseLookupFields.isEmpty()) {
            crit = getCollectionCriteriaFromMap(new Protocol(), baseLookupFields);
        }

        if (!personCollectionFields.isEmpty()) {
            for (Entry<String, CritField> entry : personCollectionFields.entrySet()) {
                crit.addExists(getPersonReportQuery(entry.getKey(), entry.getValue()));
            }
        }
 
        Query q = QueryFactory.newQuery(Protocol.class, crit, true);
      
        return (List) getPersistenceBrokerTemplate().getCollectionByQuery(q);
    }


    /*
     * initialize several maps & lists for use
     */
    private void initialization() {
        initExcludedFields();
        initCollectionFields();
        initEnumSearchMap();
        initRoleLists();
    }

    /*
     * 
     * This method to set up the list of fields that should not be included in the query criteria.
     */
    private void initExcludedFields() {
        excludedFields.add(KNSConstants.BACK_LOCATION);
        excludedFields.add(KNSConstants.DOC_FORM_KEY);
        excludedFields.add(ProtocolLookupConstants.Property.PERSON_EMPLOYEE_INDICATOR);
        excludedFields.add(ProtocolLookupConstants.Property.INVESTIGATOR_EMPLOYEE_INDICATOR);
        excludedFields.add(ProtocolLookupConstants.Property.FUNDING_SOURCE_TYPE_CODE);
    }
    
    private void initCollectionFields() {
        collectionFieldNames.add(ProtocolLookupConstants.Property.PERSON_ID);
        collectionFieldNames.add(ProtocolLookupConstants.Property.PRINCIPAL_INVESTIGATOR_ID);
        collectionFieldNames.add(ProtocolLookupConstants.Property.FUNDING_SOURCE);
        collectionFieldNames.add(ProtocolLookupConstants.Property.RESEARCH_AREA_CODE);
        collectionFieldNames.add(ProtocolLookupConstants.Property.PERFORMING_ORGANIZATION_ID);
    }
    
    private void initEnumSearchMap() {
        // map to enum
        searchMap.put(ProtocolLookupConstants.Key.EMPLOYEE_PERSON, "EMPLOYEEPERSON");
        searchMap.put(ProtocolLookupConstants.Key.ROLODEX_PERSON, "ROLODEXPERSON");
        searchMap.put(ProtocolLookupConstants.Key.EMPLOYEE_INVESTIGATOR, "EMPLOYEEINVESTIGATOR");
        searchMap.put(ProtocolLookupConstants.Key.ROLODEX_INVESTIGATOR, "ROLODEXINVESTIGATOR");
        searchMap.put(ProtocolLookupConstants.Property.FUNDING_SOURCE, "FUNDINGSOURCE");
        searchMap.put(ProtocolLookupConstants.Property.PERFORMING_ORGANIZATION_ID, "ORGANIZATION");
        searchMap.put(ProtocolLookupConstants.Property.RESEARCH_AREA_CODE, "RESEARCHAREA");        
    }

    private void initRoleLists() {
        // TODO : only principal investigator
        investigatorRole.add("PI");
        // investigatorRole.add("COI");
        // TODO : what should be in personRole ?
        personRole.add("SP");
        personRole.add("CA");
        personRole.add("CRC");        
    }
    
    /*
     * This is for personid & principalinvestigatorid.  They all from same table, so they
     * have to be handled this way.
     */
    private ReportQueryByCriteria getPersonReportQuery(String key, CritField critField) {
        Criteria crit = new Criteria();
        crit.addEqualToField(ProtocolLookupConstants.Property.PROTOCOL_ID, Criteria.PARENT_QUERY_PREFIX + ProtocolLookupConstants.Property.PROTOCOL_ID);
        String nameValue = critField.getFieldValue().replace('*', '%');
        crit.addLike(critField.getFieldName(), nameValue);
        if (StringUtils.isNotBlank(critField.getSecondCritName())) {
                if (key.equals(ProtocolLookupConstants.Property.PERSON_ID)) {
                    crit.addIn(critField.getSecondCritName(), personRole);
                } else {
                    crit.addIn(critField.getSecondCritName(), investigatorRole);
                    
                }
        }

        return QueryFactory.newReportQuery(ProtocolPerson.class, crit);

    }

    
    /*
     * filter excluded field.  Also group fields to base lookup and collection lookup fields.
     */
    private void setupCritMaps(Map<String, String> fieldValues) {

        for (Entry<String, String> entry : fieldValues.entrySet()) {
            if (!excludedFields.contains(entry.getKey()) && StringUtils.isNotBlank(entry.getValue())) {
                if (collectionFieldNames.contains(entry.getKey())) {
                    if (isProtocolPersonField (entry.getKey())) {
                        personCollectionFields.put(entry.getKey(), getQueryCriteria(entry, findIndicator(entry.getKey(), fieldValues)));
                    } else {
                        setCololectionToBaseLookup(entry,getQueryCriteria(entry, findIndicator(entry.getKey(), fieldValues)));
                    }
                } else {
                    baseLookupFields.put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    /*
     * find funding source type code specified
     */
    private String findFundingSourceTypeCode(Map<String, String> fieldValues) {
        String fundingSourceTypeCode = "";
        for (Entry<String, String> entry : fieldValues.entrySet()) {
            if (entry.getKey().equals(ProtocolLookupConstants.Property.FUNDING_SOURCE_TYPE_CODE)) {
                fundingSourceTypeCode = entry.getValue();
                break;
            }
        }
        return fundingSourceTypeCode;
    }

    /*
     * find employee indicator for person and principal investigator
     */
    private String findEmployeeIndicator(String key, Map<String, String> fieldValues) {
        String empIndicator="";
        for (Entry<String, String> entry : fieldValues.entrySet()) {
            if ((key.equals(ProtocolLookupConstants.Property.PRINCIPAL_INVESTIGATOR_ID) 
                    && entry.getKey().equals(ProtocolLookupConstants.Property.INVESTIGATOR_EMPLOYEE_INDICATOR))
                    || (key.equals(ProtocolLookupConstants.Property.PERSON_ID) 
                            && entry.getKey().equals(ProtocolLookupConstants.Property.PERSON_EMPLOYEE_INDICATOR))) {
                empIndicator = entry.getValue();
            }
        }
        return empIndicator;

    }

    /*
     * get employee indicator/fundingsourcetypecode. and also set organizationtypecode to "1"
     * for performing organization.
     */
    private String findIndicator(String key, Map<String, String> fieldValues) {
        String indicator = "";
        if (key.equals(ProtocolLookupConstants.Property.FUNDING_SOURCE)) {
            indicator = findFundingSourceTypeCode(fieldValues);
        } else if (isProtocolPersonField(key)) {
            indicator = findEmployeeIndicator(key, fieldValues);
        } else if (key.equals(ProtocolLookupConstants.Property.PERFORMING_ORGANIZATION_ID)) {
            indicator = "1";
        }

        return indicator;
    }

    /*
     * This is to get the proper enum CritField based on parameters.
     */
    private CritField getQueryCriteria(Entry <String, String>entry, String indicator) {

        
        String searchKeyName = entry.getKey();
        if (isProtocolPersonField(entry.getKey())) {
            searchKeyName = entry.getKey() + indicator;
        }
        CritField critField = Enum.valueOf(CritField.class, searchMap.get(searchKeyName));
        critField.setFieldValue(entry.getValue());
        critField.setIndicatorValue(indicator);
        return critField;
    }

    
    private void setCololectionToBaseLookup (Entry <String, String>entry, CritField critField) {
        baseLookupFields.put(critField.getFieldName(), critField.getFieldValue());
        if (StringUtils.isNotBlank(critField.getSecondCritName())) {
            baseLookupFields.put(critField.getSecondCritName(), critField.getIndicatorValue());
        }

    }
    
    private boolean isProtocolPersonField (String fieldName) {
        return fieldName.equals(ProtocolLookupConstants.Property.PERSON_ID) || fieldName.equals(ProtocolLookupConstants.Property.PRINCIPAL_INVESTIGATOR_ID);    
    }
    
    /**
     * 
     * This class set up the criteria field name and some value for criteria set up to use later.
     */
    public enum CritField {

        EMPLOYEEPERSON(ProtocolLookupConstants.Property.PERSON_ID, ProtocolLookupConstants.Property.PROTOCOL_PERSON_ROLE_ID), 
        ROLODEXPERSON(ProtocolLookupConstants.Property.ROLODEX_ID, ProtocolLookupConstants.Property.PROTOCOL_PERSON_ROLE_ID), 
        EMPLOYEEINVESTIGATOR(ProtocolLookupConstants.Property.PERSON_ID, ProtocolLookupConstants.Property.PROTOCOL_PERSON_ROLE_ID), 
        ROLODEXINVESTIGATOR(ProtocolLookupConstants.Property.ROLODEX_ID, ProtocolLookupConstants.Property.PROTOCOL_PERSON_ROLE_ID), 
        FUNDINGSOURCE("protocolFundingSources.fundingSource","protocolFundingSources.fundingSourceTypeCode"), 
        ORGANIZATION("protocolLocations.organizationId","protocolLocations.protocolOrganizationTypeCode"), 
        RESEARCHAREA("protocolResearchAreas.researchAreaCode", "");

        private String secondCritName;
        private String fieldName;
        private String fieldValue;
        private String indicatorValue;

        private CritField(String fieldName, String secondCritName) {
            this.secondCritName = secondCritName;
            this.fieldName = fieldName;
        }

        public String getSecondCritName() {
            return secondCritName;
        }

        public void setSecondCritName(String secondCritName) {
            this.secondCritName = secondCritName;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFieldValue() {
            return fieldValue;
        }

        public void setFieldValue(String fieldValue) {
            this.fieldValue = fieldValue;
        }

        public String getIndicatorValue() {
            return indicatorValue;
        }

        public void setIndicatorValue(String indicatorValue) {
            this.indicatorValue = indicatorValue;
        }
    }

    /**
     * 
     * Builds up criteria object based on the object and map.
     * This method is copied from lookdaoojb, but not published in lookupdao, so can't access it directly.
     * @param businessObject
     * @param formProps
     * @return
     */
    private Criteria getCollectionCriteriaFromMap(PersistableBusinessObject businessObject, Map formProps) {
        Criteria criteria = new Criteria();
        Iterator propsIter = formProps.keySet().iterator();
        while (propsIter.hasNext()) {
            String propertyName = (String) propsIter.next();
            if (formProps.get(propertyName) instanceof Collection) {
                Iterator iter = ((Collection) formProps.get(propertyName)).iterator();
                while (iter.hasNext()) {
                    if (!lookupDao.createCriteria(businessObject, (String) iter.next(), propertyName, 
                            isCaseSensitive(businessObject,  propertyName), criteria)) {
                        throw new RuntimeException("Invalid value in Collection");
                    }
                }
            } else {
                if (!lookupDao.createCriteria(businessObject, (String) formProps.get(propertyName), propertyName, 
                        isCaseSensitive(businessObject,  propertyName), criteria)) {
                    continue;
                }
            }
        }
        return criteria;
    }


    /*
     * extract method for casesensitive in method getCollectionCriteriaFromMap
     */
    private boolean isCaseSensitive (PersistableBusinessObject persistBo, String  propertyName) {
        
        boolean caseInsensitive = false;
        if (dataDictionaryService.isAttributeDefined(persistBo.getClass(), propertyName)) {
            caseInsensitive = !dataDictionaryService.getAttributeForceUppercase(persistBo.getClass(), propertyName);
        }
        return caseInsensitive;
    }
    
    public void setLookupDao(LookupDao lookupDao) {
        this.lookupDao = lookupDao;
    }


    public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
        this.dataDictionaryService = dataDictionaryService;
    }

}
