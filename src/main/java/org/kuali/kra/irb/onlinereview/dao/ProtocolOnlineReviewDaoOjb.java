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
package org.kuali.kra.irb.onlinereview.dao;

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
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolLookupConstants;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.dao.LookupDao;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.OjbCollectionAware;
import org.springmodules.orm.ojb.PersistenceBrokerTemplate;

public class ProtocolOnlineReviewDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, ProtocolOnlineReviewDao {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProtocolOnlineReviewDaoOjb.class);
    
    private LookupDao lookupDao;
    private DataDictionaryService dataDictionaryService;
    private Map<String, String> searchMap = new HashMap<String, String>();
    private List<String> investigatorRole = new ArrayList<String>();
    private List<String> personRole = new ArrayList<String>();
    private Map<String, String> baseLookupFieldValues;
    private Map<String, CritField> collectionFieldValues;
    private List<String> excludedFields = new ArrayList<String>();
    private List<String> collectionFieldNames = new ArrayList<String>();

    private PersistenceBrokerTemplate persistenceBrokerTemplate = createPersistenceBrokerTemplate();

    
    public ProtocolOnlineReviewDaoOjb() {
        super();
        initialization();
    }
    
    public List<ProtocolOnlineReview> getProtocolOnlineReviews(Map<String, String> fieldValues) {
        Criteria crit = new Criteria();
        baseLookupFieldValues = new HashMap<String, String>();
        collectionFieldValues = new HashMap<String, CritField>();
        
        setupCritMaps(fieldValues);
        
        //translate lookup fields and remove them.
        if( fieldValues.containsKey(ProtocolOnlineReviewLookupConstants.Property.REVIEWER_EMPLOYEE) && !StringUtils.isEmpty(fieldValues.get(ProtocolOnlineReviewLookupConstants.Property.REVIEWER_EMPLOYEE))) {
            Criteria personCrit = new Criteria();
            personCrit.addEqualToField(ProtocolOnlineReviewLookupConstants.Property.REVIEWER_EMPLOYEE, Criteria.PARENT_QUERY_PREFIX + ProtocolLookupConstants.Property.PROTOCOL_ID);
        }
        
        
        
        
//        for (Entry<String, String> entry : fieldValues.entrySet()) {
//            if (entry.getKey().startsWith(LEAD_UNIT) && StringUtils.isNotBlank(entry.getValue())){                
//                crit.addExists(getUnitReportQuery(entry));
//            }
//        }
        Query q = QueryFactory.newQuery(ProtocolOnlineReview.class, crit, true);
        logQuery(q);
        return (List<ProtocolOnlineReview>) getPersistenceBrokerTemplate().getCollectionByQuery(q);
    }


    
    
    /*
     * filter excluded field.  Also group fields to base lookup and collection lookup fields.
     */
    private void setupCritMaps(Map<String, String> fieldValues) {

        for (Entry<String, String> entry : fieldValues.entrySet()) {
            if (!excludedFields.contains(entry.getKey()) && StringUtils.isNotBlank(entry.getValue())) {
                if (collectionFieldNames.contains(entry.getKey())) {
                    collectionFieldValues.put(entry.getKey(), getCriteriaEnum(entry));
                }
            }
        }
    }
    
    
    /*
     * initialize several maps & lists for use
     */
    private void initialization() {
        initExcludedFields();
        initCollectionFields();
        initEnumSearchMap();
    }

    /*
     * 
     * This method to set up the list of fields that should not be included in the query criteria.
     */
    private void initExcludedFields() {
        excludedFields.add(KRADConstants.BACK_LOCATION);
        excludedFields.add(KRADConstants.DOC_FORM_KEY);
        excludedFields.add(ProtocolOnlineReviewLookupConstants.Property.REVIEWER_EMPLOYEE);
        excludedFields.add(ProtocolOnlineReviewLookupConstants.Property.REVIEWER_NONEMPLOYEE);
    }
    
    /*
     * set up the list to include the property that will be used to map to collection classes
     */
    private void initCollectionFields() {
    }
    
    /*
     * set up key->enum lookup map
     */
    private void initEnumSearchMap() {
        // map to enum
        searchMap.put(ProtocolOnlineReviewLookupConstants.Property.REVIEWER_EMPLOYEE, "REVIEWER_EMPLOYEE");
        searchMap.put(ProtocolOnlineReviewLookupConstants.Property.REVIEWER_NONEMPLOYEE, "REVIEWER_NONEMPLOYEE");
        searchMap.put(ProtocolOnlineReviewLookupConstants.Property.PROTOCOL_ID, "PROTOCOL_ID");
        searchMap.put(ProtocolOnlineReviewLookupConstants.Property.PROTOCOL_NUMBER, "PROTOCOL_NUMBER");
        searchMap.put(ProtocolOnlineReviewLookupConstants.Property.SUBMISSION_ID, "SUBMISSION_ID");        
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

    /**
     * Logs the Query
     * @param q the query
     */
    private static void logQuery(Query q) {
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("logQuery:%s",q.toString()));
            LOG.debug(q.toString());
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
                            isCaseSensitive(businessObject,  propertyName), false, criteria)) {
                        throw new RuntimeException("Invalid value in Collection");
                    }
                }
            } else {
                if (!lookupDao.createCriteria(businessObject, (String) formProps.get(propertyName), propertyName, 
                        isCaseSensitive(businessObject,  propertyName), false, criteria)) {
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

    /*
     * this is to set up criteria that will check existence in collection tables.
     */
    private ReportQueryByCriteria getCollectionReportQuery(String key, CritField critField) {
        Criteria crit = new Criteria();
        
        String propertyName = getDbPlatform().getUpperCaseFunction() + "("+critField.getCritFieldName()+")";
        
        if (isProtocolReviewerField (key)) {
            crit.addEqualTo("protocolReviewer.personId", critField.getFieldValue());
        } 
        
        return QueryFactory.newReportQuery(critField.getClazz(), crit);
    }
    
    private boolean isProtocolReviewerField (String fieldName) {
        return fieldName.equals(ProtocolOnlineReviewLookupConstants.Property.REVIEWER_NONEMPLOYEE) || fieldName.equals(ProtocolOnlineReviewLookupConstants.Property.REVIEWER_EMPLOYEE);    
    }
  
    /*
     * This is for personid & principalinvestigatorid.  
     */
    private void addReviewer(String key, Criteria crit) {
        if (key.equals(ProtocolOnlineReviewLookupConstants.Property.REVIEWER_EMPLOYEE)) {
            crit.addIn(ProtocolOnlineReviewLookupConstants.Property.REVIEWER_EMPLOYEE, personRole);
        } else {
            crit.addIn(ProtocolLookupConstants.Property.PROTOCOL_PERSON_ROLE_ID, investigatorRole);                    
        }
    }
    
    /**
     * 
     * This class set up the criteria field name and class for ojb criteria set up to use later.
     */
    public enum CritField {

        EMPLOYEE_REVIEWER(ProtocolOnlineReviewLookupConstants.Property.REVIEWER_EMPLOYEE, ProtocolReviewer.class),
        NONEMPLOYEE_REVIEWER(ProtocolOnlineReviewLookupConstants.Property.REVIEWER_NONEMPLOYEE,ProtocolReviewer.class),
        PROTOCOL_ID(ProtocolOnlineReviewLookupConstants.Property.PROTOCOL_ID, Protocol.class),
        PROTOCOL_NUMBER(ProtocolOnlineReviewLookupConstants.Property.PROTOCOL_NUMBER,Protocol.class),
        SUBMISSION_ID(ProtocolOnlineReviewLookupConstants.Property.SUBMISSION_ID,ProtocolSubmission.class);
        
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

    public void setLookupDao(LookupDao lookupDao) {
        this.lookupDao = lookupDao;
    }


    public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
        this.dataDictionaryService = dataDictionaryService;
    }

    
    
    
    
}
