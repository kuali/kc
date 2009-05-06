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
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.dao.ProtocolDao;
import org.kuali.kra.irb.lookup.ProtocolLookupConstants;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.protocol.ProtocolFundingSource;
import org.kuali.kra.irb.protocol.ProtocolLocation;
import org.kuali.kra.irb.protocol.ProtocolResearchArea;
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
public class ProtocolDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, ProtocolDao {

    private LookupDao lookupDao;
    private DataDictionaryService dataDictionaryService;
    private Map<String, String> searchMap = new HashMap<String, String>();
    private List<String> investigatorRole = new ArrayList<String>();
    private List<String> personRole = new ArrayList<String>();
    private Map<String, String> baseLookupFieldValues;
    private Map<String, CritField> collectionFieldValues;
    private List<String> excludedFields = new ArrayList<String>();
    private List<String> collectionFieldNames = new ArrayList<String>();
    
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
        baseLookupFieldValues = new HashMap<String, String>();
        collectionFieldValues = new HashMap<String, CritField>();
        setupCritMaps(fieldValues);
        if (!baseLookupFieldValues.isEmpty()) {
            crit = getCollectionCriteriaFromMap(new Protocol(), baseLookupFieldValues);
        }

        if (!collectionFieldValues.isEmpty()) {
            for (Entry<String, CritField> entry : collectionFieldValues.entrySet()) {
                crit.addExists(getCollectionReportQuery(entry.getKey(), entry.getValue()));
            }
        }
 
        Query q = QueryFactory.newQuery(Protocol.class, crit, true);
      
        return (List<Protocol>) getPersistenceBrokerTemplate().getCollectionByQuery(q);
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
    }
    
    /*
     * set up the list to include the property that will be used to map to collection classes
     */
    private void initCollectionFields() {
        collectionFieldNames.add(ProtocolLookupConstants.Property.KEY_PERSON);
        collectionFieldNames.add(ProtocolLookupConstants.Property.INVESTIGATOR);
        collectionFieldNames.add(ProtocolLookupConstants.Property.FUNDING_SOURCE);
        collectionFieldNames.add(ProtocolLookupConstants.Property.RESEARCH_AREA_CODE);
        collectionFieldNames.add(ProtocolLookupConstants.Property.PERFORMING_ORGANIZATION_ID);
    }
    
    /*
     * set up key->enum lookup map
     */
    private void initEnumSearchMap() {
        // map to enum
        searchMap.put(ProtocolLookupConstants.Property.KEY_PERSON, "KEYPERSON");
        searchMap.put(ProtocolLookupConstants.Property.INVESTIGATOR, "INVESTIGATOR");
        searchMap.put(ProtocolLookupConstants.Property.FUNDING_SOURCE, "FUNDINGSOURCE");
        searchMap.put(ProtocolLookupConstants.Property.PERFORMING_ORGANIZATION_ID, "ORGANIZATION");
        searchMap.put(ProtocolLookupConstants.Property.RESEARCH_AREA_CODE, "RESEARCHAREA");        
    }

    /*
     * investigator include PI & COI roles, key person includes SP/CA/CRC
     */
    private void initRoleLists() {
        investigatorRole.add("PI");
        investigatorRole.add("COI");
        personRole.add("SP");
        personRole.add("CA");
        personRole.add("CRC");        
    }

    /*
     * this is to set up criteria that will check existence in collection tables.
     */
    private ReportQueryByCriteria getCollectionReportQuery(String key, CritField critField) {
        Criteria crit = new Criteria();
        crit.addEqualToField(ProtocolLookupConstants.Property.PROTOCOL_ID, Criteria.PARENT_QUERY_PREFIX + ProtocolLookupConstants.Property.PROTOCOL_ID);
        String nameValue = critField.getFieldValue().replace('*', '%');
        // need to use upper case
        String propertyName = getDbPlatform().getUpperCaseFunction() + "("+critField.getCritFieldName()+")";
        crit.addLike(propertyName, nameValue.toUpperCase());

        if (isProtocolPersonField (key)) {
            addPersonRoleId(key, crit);
        } else if (key.equals(ProtocolLookupConstants.Property.PERFORMING_ORGANIZATION_ID)){
            crit.addLike(ProtocolLookupConstants.Property.PROTOCOL_ORGANIZATION_TYPE_CODE, ProtocolLookupConstants.Property.PERFORMING_ORGANIZATION_CODE);
        }
        return QueryFactory.newReportQuery(critField.getClazz(), crit);
    }
    
    /*
     * This is for personid & principalinvestigatorid.  
     */
    private void addPersonRoleId(String key, Criteria crit) {
                if (key.equals(ProtocolLookupConstants.Property.KEY_PERSON)) {
                    crit.addIn(ProtocolLookupConstants.Property.PROTOCOL_PERSON_ROLE_ID, personRole);
                } else {
                    crit.addIn(ProtocolLookupConstants.Property.PROTOCOL_PERSON_ROLE_ID, investigatorRole);                    
                }
    }
    
    /*
     * filter excluded field.  Also group fields to base lookup and collection lookup fields.
     */
    private void setupCritMaps(Map<String, String> fieldValues) {

        for (Entry<String, String> entry : fieldValues.entrySet()) {
            if (!excludedFields.contains(entry.getKey()) && StringUtils.isNotBlank(entry.getValue())) {
                if (collectionFieldNames.contains(entry.getKey())) {
                    collectionFieldValues.put(entry.getKey(), getCriteriaEnum(entry));
                } else {
                    baseLookupFieldValues.put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    /*
     * This is to get the proper enum CritField based on parameters.
     */
    private CritField getCriteriaEnum(Entry <String, String>entry) {
        
        String searchKeyName = entry.getKey();
        CritField critField = Enum.valueOf(CritField.class, searchMap.get(searchKeyName));
        critField.setFieldValue(entry.getValue());
        return critField;
    }

        
    private boolean isProtocolPersonField (String fieldName) {
        return fieldName.equals(ProtocolLookupConstants.Property.KEY_PERSON) || fieldName.equals(ProtocolLookupConstants.Property.INVESTIGATOR);    
    }
    
    /**
     * 
     * This class set up the criteria field name and class for ojb criteria set up to use later.
     */
    public enum CritField {

        KEYPERSON(ProtocolLookupConstants.Property.PERSON_NAME, ProtocolPerson.class), 
        INVESTIGATOR(ProtocolLookupConstants.Property.PERSON_NAME, ProtocolPerson.class), 
        FUNDINGSOURCE(ProtocolLookupConstants.Property.FUNDING_SOURCE,ProtocolFundingSource.class), 
        ORGANIZATION(ProtocolLookupConstants.Property.ORGANIZATION_ID, ProtocolLocation.class), 
        RESEARCHAREA(ProtocolLookupConstants.Property.RESEARCH_AREA_CODE,ProtocolResearchArea.class);

        private String critFieldName;
        private String fieldValue;
        private Class<? extends KraPersistableBusinessObjectBase> clazz;

        private CritField(String critFieldName, Class<? extends KraPersistableBusinessObjectBase> clazz) {
            this.critFieldName = critFieldName;
            this.clazz = clazz;
        }

        public String getFieldValue() {
            return fieldValue;
        }

        public void setFieldValue(String fieldValue) {
            this.fieldValue = fieldValue;
        }

        public String getCritFieldName() {
            return critFieldName;
        }

        public void setCritFieldName(String critFieldName) {
            this.critFieldName = critFieldName;
        }

        public Class<? extends KraPersistableBusinessObjectBase> getClazz() {
            return clazz;
        }

        public void setClazz(Class<? extends KraPersistableBusinessObjectBase> clazz) {
            this.clazz = clazz;
        }

    }

    /*
     * 
     * Builds up criteria object based on the object and map.
     * This method is copied from lookdaoojb, but not published in lookupdao, so can't access it directly.
     * @param businessObject
     * @param formProps
     * @return
     */
    @SuppressWarnings("unchecked") 
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
