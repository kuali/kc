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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.core.dao.ojb.PlatformAwareDaoBaseOjb;
import org.kuali.core.util.OjbCollectionAware;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.bo.ProtocolPerson;
import org.kuali.kra.irb.dao.ProtocolDao;
import org.kuali.rice.kns.util.KNSConstants;

public class ProtocolDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, ProtocolDao {

    private static final String PROTOCOL_ID = "protocolId";
    private static final String PERSON_ID = "personId";
    private static final String PRINCIPAL_INVESTIGATOR_ID = "principalInvestigatorId";
    private static final String PERSON_EMPLOYEE_INDICATOR = "personEmployeeIndicator";
    private static final String INVESTIGATOR_EMPLOYEE_INDICATOR = "investigatorEmployeeIndicator";
    private static final String FUNDING_SOURCE_TYPE_CODE ="fundingSourceTypeCode";
    private static final String FUNDING_SOURCE ="fundingSource";
    private static final String RESEARCH_AREA_CODE = "researchAreaCode";
    private static final String PERFORMING_ORGANIZATION_ID = "performingOrganizationId";
    private Map<String, String> searchMap = new HashMap<String, String>();
    private List<String> investigatorRole = new ArrayList<String>();
    private List<String> personRole = new ArrayList<String>();
    private Map<String, String> baseLookupFields;
    private Map<String, CritField> collectionFields;
    {
        // map to enum
        searchMap.put("personIdY", "EMPLOYEEPERSON");
        searchMap.put("personIdN", "ROLODEXPERSON");
        searchMap.put("principalInvestigatorIdY", "EMPLOYEEINVESTIGATOR");
        searchMap.put("principalInvestigatorIdN", "ROLODEXINVESTIGATOR");
        searchMap.put(FUNDING_SOURCE, "FUNDINGSOURCE");
        searchMap.put(PERFORMING_ORGANIZATION_ID, "ORGANIZATION");
        searchMap.put(RESEARCH_AREA_CODE, "RESEARCHAREA");
        // TODO : only principal investigator
        investigatorRole.add("PI");
        // investigatorRole.add("COI");
        // TODO : what should be in personRole ?
        personRole.add("SP");
        personRole.add("CA");
        personRole.add("CRC");
    }

    /**
     * 
     * @see org.kuali.kra.irb.dao.ProtocolDao#getProtocols(java.util.Map)
     */
    public List<Protocol> getProtocols(Map<String, String> fieldValues) {
        Criteria crit = new Criteria();
        baseLookupFields = new HashMap<String, String>();
        collectionFields = new HashMap<String, CritField>();
        setupCritMaps(fieldValues);
        for (Entry<String, String> entry : baseLookupFields.entrySet()) {
            if (StringUtils.isNotBlank(entry.getValue())) {
                crit.addLike(entry.getKey(), entry.getValue());
            }
        }

        if (!collectionFields.isEmpty()) {
            for (Entry<String, CritField> entry : collectionFields.entrySet()) {
                setupCriteriaForCollections(entry.getKey(), crit, entry.getValue());
            }
        }

        Query q = QueryFactory.newQuery(Protocol.class, crit);

        return (List) getPersistenceBrokerTemplate().getCollectionByQuery(q);
    }


    /*
     * set up criteria for collections in protocol with lookup field
     */
    private void setupCriteriaForCollections(String key, Criteria crit, CritField critField) {
        if (isProtocolPersonField(key)) {
            crit.addExists(getPersonReportQuery(key, critField));
        } else {
            crit.addLike(critField.getFieldName(), critField.getFieldValue());
            if (StringUtils.isNotBlank(critField.getSecondCritName())) {
                crit.addEqualTo(critField.getSecondCritName(), critField.getIndicatorValue());
            }
        }

    }

    /*
     * This is for personid & principalinvestigatorid.  They all from same table, so they
     * have to be handled this way.
     */
    private ReportQueryByCriteria getPersonReportQuery(String key, CritField critField) {
        Criteria crit = new Criteria();
        crit.addEqualToField(PROTOCOL_ID, Criteria.PARENT_QUERY_PREFIX + PROTOCOL_ID);
        crit.addLike(critField.getFieldName(), critField.getFieldValue());
        if (StringUtils.isNotBlank(critField.getSecondCritName())) {
                if (key.equals(PERSON_ID)) {
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
        List<String> excludedFields = new ArrayList<String>();
        excludedFields.add(KNSConstants.BACK_LOCATION);
        excludedFields.add(KNSConstants.DOC_FORM_KEY);
        excludedFields.add(PERSON_EMPLOYEE_INDICATOR);
        excludedFields.add(INVESTIGATOR_EMPLOYEE_INDICATOR);
        excludedFields.add(FUNDING_SOURCE_TYPE_CODE);


        List<String> collectionFieldNames = new ArrayList<String>();
        collectionFieldNames.add(PERSON_ID);
        collectionFieldNames.add(PRINCIPAL_INVESTIGATOR_ID);
        collectionFieldNames.add(FUNDING_SOURCE);
        collectionFieldNames.add(RESEARCH_AREA_CODE);
        collectionFieldNames.add(PERFORMING_ORGANIZATION_ID);
        // collectionFieldNames.add(FUNDING_SOURCE_TYPE_CODE);
        for (Entry<String, String> entry : fieldValues.entrySet()) {
            if (!excludedFields.contains(entry.getKey()) && StringUtils.isNotBlank(entry.getValue())) {
                String nameValue = entry.getValue().replace('*', '%');
                if (collectionFieldNames.contains(entry.getKey())) {
                    collectionFields.put(entry.getKey(), getQueryCriteria(entry.getKey(), nameValue, findIndicator(entry.getKey(),
                            fieldValues)));
                }
                else {
                    baseLookupFields.put(entry.getKey(), nameValue);
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
            if (entry.getKey().equals(FUNDING_SOURCE_TYPE_CODE)) {
                fundingSourceTypeCode = entry.getValue();
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
            if ((key.equals(PRINCIPAL_INVESTIGATOR_ID) && entry.getKey().equals(INVESTIGATOR_EMPLOYEE_INDICATOR))
                    || (key.equals(PERSON_ID) && entry.getKey().equals(PERSON_EMPLOYEE_INDICATOR))) {
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
        if (key.equals(FUNDING_SOURCE)) {
            indicator = findFundingSourceTypeCode(fieldValues);
        }
        else if (isProtocolPersonField(key)) {
            indicator = findEmployeeIndicator(key, fieldValues);
        }
        else if (key.equals(PERFORMING_ORGANIZATION_ID)) {
            indicator = "1";
        }

        return indicator;
    }

    /*
     * 
     */
    private CritField getQueryCriteria(String key, String nameValue, String indicator) {

        String searchKeyName = key;
        if (isProtocolPersonField(key)) {
            searchKeyName = key + indicator;
        }
        CritField critField = Enum.valueOf(CritField.class, searchMap.get(searchKeyName));
        critField.setFieldValue(nameValue);
        critField.setIndicatorValue(indicator);
        return critField;
    }

    private boolean isProtocolPersonField (String fieldName) {
        return fieldName.equals(PERSON_ID) || fieldName.equals(PRINCIPAL_INVESTIGATOR_ID);    
    }
    
    /**
     * 
     * This class set up the criterial field name and some value for criteria set up to use later.
     */
    public enum CritField {

        EMPLOYEEPERSON("personId", "protocolPersonRoleId"), 
        ROLODEXPERSON("rolodexId", "protocolPersonRoleId"), 
        EMPLOYEEINVESTIGATOR("personId", "protocolPersonRoleId"), 
        ROLODEXINVESTIGATOR("rolodexId", "protocolPersonRoleId"), 
        FUNDINGSOURCE("protocolFundingSources.fundingSource","protocolFundingSources.fundingSourceTypeCode"), 
        ORGANIZATION("protocolLocations.organizationId","protocolLocations.protocolOrganizationTypeCode"), 
        RESEARCHAREA("protocolResearchAreas.researchAreaCode", "");

        private CritField(String fieldName, String secondCritName) {
            this.secondCritName = secondCritName;
            this.fieldName = fieldName;
        }

        private String secondCritName;
        private String fieldName;
        private String fieldValue;
        private String indicatorValue;

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


}
