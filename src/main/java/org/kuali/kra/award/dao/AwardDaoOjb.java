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
package org.kuali.kra.award.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.dao.LookupDao;
import org.kuali.rice.kns.dao.impl.PlatformAwareDaoBaseOjb;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.OjbCollectionAware;

/**
 * 
 * This class is the implementation for ProtocolDao interface.
 */
class AwardDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, AwardDao {
    private static final String OSP_ADMINISTRATOR = "ospAdministrator";
    private static final String AWARD_ID = "awardId";
    
    private LookupDao lookupDao;
    private DataDictionaryService dataDictionaryService;
    private Map<String, String> baseLookupFieldNameValuePairs;
    private Map<String, CollectionCriteria> collectionFieldValues;
    private List<String> excludedFields = new ArrayList<String>();
    private Set<String> unitFieldNames;
    
    /**
     * Constructs a AwardDaoOjb.java.
     */
    public AwardDaoOjb() {
        super();
        initExcludedFields();
        initUnitFieldNames();
    }

    /**
     * @see org.kuali.kra.award.dao.AwardDao#getAwards(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    public List<Award> getAwards(Map<String, String> lookupFieldNameValuePairs) {
        prepareCriteriaMaps(lookupFieldNameValuePairs);
        return new ArrayList<Award>(getPersistenceBrokerTemplate().getCollectionByQuery(createQuery()));
    }

    /**
     * @param dataDictionaryService
     */
    public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
        this.dataDictionaryService = dataDictionaryService;
    }
    
    /**
     * @param lookupDao
     */
    public void setLookupDao(LookupDao lookupDao) {
        this.lookupDao = lookupDao;
    }

    /**
     * This method...
     * @return
     */
    @SuppressWarnings("deprecation")
    String getDatabaseUppercaseFunctionName() {
        return getDbPlatform().getUpperCaseFunction();
    }

    /**
     * @param criteria
     */
    private void addAssociationCriteria(Criteria criteria) {
        if (!collectionFieldValues.isEmpty()) {
            for (String fieldName : collectionFieldValues.keySet()) {
                criteria.addExists(getCollectionReportQuery(fieldName, collectionFieldValues.get(fieldName)));
            }
        }
    }

    /**
     * @param criteria
     */
    private void addBaseCriteria(Criteria criteria) {
        if (!baseLookupFieldNameValuePairs.isEmpty()) {
            getCollectionCriteriaFromMap(criteria);
        }
    }

    private void addOspAdminCriteria(Criteria baseCriteria) {
        if(baseLookupFieldNameValuePairs.get(OSP_ADMINISTRATOR) != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("select distinct a.AWARD_ID from AWARD a, AWARD_UNIT_CONTACTS auc ");
            sb.append("where auc.AWARD_ID = a.AWARD_ID ");
            sb.append("and auc.CONTACT_ROLE_CODE = '2' ");
            
            addWhereClause(sb, "auc", "FULL_NAME", baseLookupFieldNameValuePairs.get(OSP_ADMINISTRATOR));
            baseCriteria.addIn(AWARD_ID, QueryFactory.newQuery(Award.class, sb.toString()));
        }  
    }

    /**
     * This method adds a Unit column criteria to the StringBuilder
     * TODO: This exposes a vulnerability for an SQL Injection attack
     * Since Rice only exposes an "addSql(String)" method in Criteria instead of a Statement factory,
     * there's no obvious way to use a parameterized statement instead of String sql
     * 
     * @param sb
     * @param unitCriteria
     */
    private void addUnitColumnCriteria(StringBuilder sb, UnitCriteria unitCriteria) {
        if(baseLookupFieldNameValuePairs.keySet().contains(unitCriteria.getLookupFieldName())) {
            addWhereClause(sb, "u", unitCriteria.name(), baseLookupFieldNameValuePairs.get(unitCriteria.getLookupFieldName()));
        }
    }

    private void addUnitCriteria(Criteria baseCriteria) {
        if(isUnitFieldInvolvedInLookup()) {
            StringBuilder sb = new StringBuilder();
            sb.append("select a.AWARD_ID from AWARD a, AWARD_PERSONS ap, AWARD_PERSON_UNITS apu, UNIT u ");
            sb.append("where ap.AWARD_ID = a.AWARD_ID ");
            sb.append("and apu.AWARD_PERSON_ID = ap.AWARD_PERSON_ID ");
            sb.append("and apu.LEAD_UNIT_FLAG = 'Y' ");
            sb.append("and apu.UNIT_NUMBER = u.UNIT_NUMBER ");
            
            for(UnitCriteria uc: UnitCriteria.values()) {
                addUnitColumnCriteria(sb, uc);
            }
            baseCriteria.addIn(AWARD_ID, QueryFactory.newQuery(Award.class, sb.toString()));
        }  
    }

    /**
     * This method adds an AND where clause to a query in a StringBuilder 
     * @param sb
     * @param unitCriteria
     */
    private void addWhereClause(StringBuilder sb, String tableAlias, String columnName, String columnValue) {
        sb.append(String.format("and %s.%s = '%s' ", tableAlias, columnName, columnValue));
    }
    
    /**
     * This method...
     * @return
     */
    private Criteria createCriteria() {
        Criteria criteria = new Criteria();
        addBaseCriteria(criteria);
        addAssociationCriteria(criteria);
        addUnitCriteria(criteria);
        addOspAdminCriteria(criteria);
        return criteria;
    }

    /**
     * This method...
     * @return
     */
    private QueryByCriteria createQuery() {
        return QueryFactory.newQuery(Award.class, createCriteria(), true);
    }

    /*
     * 
     * Builds up criteria object based on the object and map.
     * This method is copied from lookDaoOjb, but not published in lookupdao, so can't access it directly.
     * @param businessObject
     * @param formProps
     * @return
     */ 
    private Criteria getCollectionCriteriaFromMap(Criteria criteria) {
        Award award = new Award();
        for(String fieldName: baseLookupFieldNameValuePairs.keySet()) {
            String fieldValue = (String) baseLookupFieldNameValuePairs.get(fieldName);
            if (!lookupDao.createCriteria(award, fieldValue, fieldName, isCaseSensitive(award,  fieldName), criteria)) {
                continue;
            }
        }
        return criteria;
    }

    /*
     * Set up criteria that will check existence in collection tables.
     */
    private ReportQueryByCriteria getCollectionReportQuery(String fieldName, CollectionCriteria collectionCriteria) {
        Criteria criteria = new Criteria();
        criteria.addEqualToField(AWARD_ID, Criteria.PARENT_QUERY_PREFIX + AWARD_ID);
        String fieldValue = collectionCriteria.getFieldValue().replace('*', '%');
        String propertyName = wrapFieldNameInUpperCaseFunction(collectionCriteria);
        criteria.addLike(propertyName, fieldValue.toUpperCase());

        return QueryFactory.newReportQuery(collectionCriteria.getClazz(), criteria);
    }

    /*
     * 
     * This method to set up the list of fields that should not be included in the query criteria.
     */
    private void initExcludedFields() {
        excludedFields.add(KNSConstants.BACK_LOCATION);
        excludedFields.add(KNSConstants.DOC_FORM_KEY);
    }
    
    private void initUnitFieldNames() {
        unitFieldNames = new HashSet<String>();
        for(UnitCriteria unitCriteria: UnitCriteria.values()) {
            unitFieldNames.add(unitCriteria.lookupFieldName);
        }
    }

    /*
     * extract method for casesensitive in method getCollectionCriteriaFromMap
     */
    private boolean isCaseSensitive(PersistableBusinessObject persistBo, String  propertyName) {     
        boolean caseInsensitive = false;
        if (dataDictionaryService.isAttributeDefined(persistBo.getClass(), propertyName)) {
            caseInsensitive = !dataDictionaryService.getAttributeForceUppercase(persistBo.getClass(), propertyName);
        }
        return caseInsensitive;
    }

    /**
     * This method...
     * @return
     */
    private boolean isUnitFieldInvolvedInLookup() {
        Set<String> fieldNames = new HashSet<String>(baseLookupFieldNameValuePairs.keySet());
        fieldNames.retainAll(unitFieldNames);
        boolean unitFieldInvolvedInLookup = fieldNames.size() > 0;
        return unitFieldInvolvedInLookup;
    }
    
    /*
     * Collect fields to base lookup and collection lookup fields, excluding specified excludedFields
     */
    private void prepareCriteriaMaps(Map<String, String> lookupFieldNameValuePairs) {
        prepareBaseLookupFieldMap(lookupFieldNameValuePairs);
        prepareCollectionLookupFieldMap(lookupFieldNameValuePairs);
    }

    private void prepareBaseLookupFieldMap(Map<String, String> lookupFieldNameValuePairs) {
        baseLookupFieldNameValuePairs = new HashMap<String, String>();
        
        for(String fieldName: lookupFieldNameValuePairs.keySet()) {
            if(excludedFields.contains(fieldName)) {
                continue;
            }
            String fieldValue = lookupFieldNameValuePairs.get(fieldName);
            if (StringUtils.isNotBlank(fieldValue) && !CollectionCriteria.isFieldNamePresent(fieldName)) {
                baseLookupFieldNameValuePairs.put(fieldName, fieldValue);
            }
        }
    }
    
    private void prepareCollectionLookupFieldMap(Map<String, String> lookupFieldNameValuePairs) {
        collectionFieldValues = new HashMap<String, CollectionCriteria>();
        
        for(String fieldName: lookupFieldNameValuePairs.keySet()) {
            if(excludedFields.contains(fieldName)) {
                continue;
            }
            String fieldValue = lookupFieldNameValuePairs.get(fieldName);
            if (StringUtils.isNotBlank(fieldValue) && CollectionCriteria.isFieldNamePresent(fieldName)) {
                CollectionCriteria enumCriteria = CollectionCriteria.findForFieldName(fieldName);
                enumCriteria.setFieldValue(fieldValue);
                collectionFieldValues.put(fieldName, enumCriteria);
            }
        }
    }
    
    /**
     * This method results in the field name (and hence the column name) being wrapped in an upper case function
     * @param collectionCriteria
     * @return
     */
    private String wrapFieldNameInUpperCaseFunction(CollectionCriteria collectionCriteria) {
        return String.format("%s(%s)", getDatabaseUppercaseFunctionName(), collectionCriteria.getCriteriaFieldName());
    }

    /**
     * 
     * This class set up the criteria field name and class for ojb criteria set up to use later.
     */
    private enum CollectionCriteria {
        INVESTIGATOR(PI_NAME, "fullName", AwardPerson.class);
        
        private String lookupFieldName;
        private String criteriaFieldName;
        private String fieldValue;
        private Class<? extends KraPersistableBusinessObjectBase> clazz;

        private CollectionCriteria(String fieldName, String columnName, Class<? extends KraPersistableBusinessObjectBase> clazz) {
            this.lookupFieldName = fieldName;
            this.criteriaFieldName = columnName;
            this.clazz = clazz;
        }

        private static CollectionCriteria findForFieldName(String fieldName) {
            CollectionCriteria foundCriteria = null;
            for(CollectionCriteria criteria: values()) {
                if(criteria.lookupFieldName.equals(fieldName)) {
                    foundCriteria = criteria;
                    break;
                }
            }
            return foundCriteria;
        }
        
        private static boolean isFieldNamePresent(String fieldName) {
            return findForFieldName(fieldName) != null;
        }
        
        public Class<? extends KraPersistableBusinessObjectBase> getClazz() {
            return clazz;
        }
        
        public String getCriteriaFieldName() {
            return criteriaFieldName;
        }

        public String getFieldValue() {
            return fieldValue;
        }

        public String getLookupFieldName() {
            return lookupFieldName;
        }

        public void setFieldValue(String fieldValue) {
            this.fieldValue = fieldValue;
        }   
    }
    
    /**
     * This class...
     */
    private enum UnitCriteria {
        UNIT_NAME("unitName"),
        UNIT_NUMBER("unitNumber");

        private String lookupFieldName;

        private UnitCriteria(String fieldName) {
            this.lookupFieldName = fieldName;
        }

        private static UnitCriteria findForFieldName(String fieldName) {
            UnitCriteria foundCriteria = null;
            for(UnitCriteria criteria: values()) {
                if(criteria.lookupFieldName.equals(fieldName)) {
                    foundCriteria = criteria;
                    break;
                }
            }
            return foundCriteria;
        }

        public String getLookupFieldName() {
            return lookupFieldName;
        }   
    }
}
