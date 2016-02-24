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
package org.kuali.kra.protocol;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ojb.broker.query.*;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentBase;
import org.kuali.kra.protocol.noteattachment.TypedAttachment;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.protocol.personnel.ProtocolUnitBase;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.dao.LookupDao;
import org.kuali.rice.krad.service.util.OjbCollectionAware;
import org.kuali.rice.krad.util.KRADConstants;

import java.sql.Date;
import java.util.*;
import java.util.Map.Entry;

/**
 * 
 * This class is the implementation for ProtocolDao interface.
 */
public abstract class ProtocolDaoOjbBase<GenericProtocol extends ProtocolBase> extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, ProtocolDao<GenericProtocol> {

    private static final Log LOG = LogFactory.getLog(ProtocolDaoOjbBase.class);
    
    private static final String LEAD_UNIT = "leadUnit";
    private static final String PROTOCOL_NUMBER = "protocolNumber";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    private static final String EXPIRATION_DATE = "expirationDate";
    private static final String PROTOCOL_STATUS_CODE = "protocolStatusCode";
    private static final String SUBMISSION_STATUS_CODE = "submissionStatusCode";
    private static final String PROTOCOL_SUBMISSIONS_COMMITTEE_ID = "protocolSubmissions.committeeId";
    private static final String PROTOCOL_SUBMISSIONS_SUBMISSION_NUMBER = "protocolSubmissions.submissionNumber";
    private static final String ACTUAL_ACTION_DATE = "actualActionDate";
    private static final String PROTOCOL_ACTION_TYPE_CODE = "protocolActionTypeCode";
 
    private LookupDao lookupDao;
    private DataDictionaryService dataDictionaryService;
    private List<String> investigatorRole = new ArrayList<>();
    private List<String> personRole = new ArrayList<>();
    private List<String> excludedFields = new ArrayList<>();
    protected List<String> collectionFieldNames = new ArrayList<>();
    
    public ProtocolDaoOjbBase() {
        super();
        initialization();
    }

    @Override
    public List<GenericProtocol> getProtocols(Map<String, String> fieldValues) {
        Criteria crit = new Criteria();
        final Map<String, String> baseLookupFieldValues = new HashMap<>();
        final Map<String, CriteriaFieldHelper> collectionFieldValues = new HashMap<>();
        setupCritMaps(fieldValues, baseLookupFieldValues, collectionFieldValues);
        
        if (!baseLookupFieldValues.isEmpty()) {
            try {
                crit = getCollectionCriteriaFromMap(getProtocolBOClassHook().newInstance(), baseLookupFieldValues);
            }catch(Exception ex) {
                LOG.error(ex.getMessage(), ex);
            }
        }

        if (!collectionFieldValues.isEmpty()) {
            for (Entry<String, CriteriaFieldHelper> entry : collectionFieldValues.entrySet()) {
                crit.addExists(getCollectionReportQuery(entry.getKey(), entry.getValue()));
            }
        }
 
        for (Entry<String, String> entry : fieldValues.entrySet()) {
            if (entry.getKey().startsWith(LEAD_UNIT) && StringUtils.isNotBlank(entry.getValue())) {                
                crit.addExists(getUnitReportQuery(entry));
            }
        }
        
        Long matchingResultsCount = null;
        Integer searchResultsLimit = LookupUtils.getSearchResultsLimit(getProtocolBOClassHook());
        if (searchResultsLimit != null) {
        	matchingResultsCount = Long.valueOf(getPersistenceBrokerTemplate().getCount(QueryFactory.newQuery(getProtocolBOClassHook(), crit)));
            LookupUtils.applySearchResultsLimit(getProtocolBOClassHook(), crit, getDbPlatform());
        }
        if ((matchingResultsCount == null) || (matchingResultsCount <= searchResultsLimit)) {
            matchingResultsCount = new Long(0);
        }
        
        Query q = QueryFactory.newQuery(getProtocolBOClassHook(), crit, true);
        logQuery(q);
        
        return (List<GenericProtocol>) getPersistenceBrokerTemplate().getCollectionByQuery(q);
    }

    @Override
    public <T extends ProtocolAttachmentBase & TypedAttachment> List<T> retrieveAttachmentVersions(T attachment, Class<T> type) {
        
        final List<T> attachments;
        
        if (this.validAttachmentForVersionLookup(attachment)) {
            final Criteria crit = new Criteria();
            
            //this assumes the all owners have the same protocol number.  If this is not the case then there is
            //a programming error somewhere.
            crit.addEqualTo(PROTOCOL_NUMBER, attachment.getProtocolNumber());
            crit.addEqualTo("typeCode", attachment.getTypeCode());
            crit.addEqualTo("documentId", attachment.getDocumentId());
            
            final QueryByCriteria q = QueryFactory.newQuery(type, crit, true);
            q.addOrderBy("file.sequenceNumber", false);
            
            logQuery(q);
            attachments = (List<T>) getPersistenceBrokerTemplate().getCollectionByQuery(q);
        } else {
            attachments = new ArrayList<T>();
        }
        
        return attachments;
    }
    
    @Override
    public List<GenericProtocol> getExpiringProtocols(String committeeId, Date startDate, Date endDate) {
        Criteria crit = new Criteria();
        crit.addEqualTo(PROTOCOL_SUBMISSIONS_COMMITTEE_ID, committeeId);
        if (startDate != null) {
            crit.addGreaterOrEqualThan(EXPIRATION_DATE, startDate);
        }
        if (endDate != null) {
            crit.addLessOrEqualThan(EXPIRATION_DATE, nextDay(endDate));
        }
        crit.addIn(PROTOCOL_STATUS_CODE, getActiveProtocolStatusCodesHook());
        crit.addIn(SUBMISSION_STATUS_CODE, getApprovedSubmissionStatusCodesHook());
        crit.addEqualTo(SEQUENCE_NUMBER, getSubQueryMaxSequenceNumber());
        crit.addEqualTo(PROTOCOL_SUBMISSIONS_SUBMISSION_NUMBER, getsubQueryMaxProtocolSubmission());
        Query q = QueryFactory.newQuery(getProtocolBOClassHook(), crit, true);
        logQuery(q);
        return (List<GenericProtocol>) getPersistenceBrokerTemplate().getCollectionByQuery(q);
    }
    

    
    protected abstract Collection<String> getApprovedSubmissionStatusCodesHook();

    protected abstract Collection<String> getActiveProtocolStatusCodesHook();

    public List<ProtocolBase> getNotifiedProtocols(String committeeId, Date startDate, Date endDate) {
        Criteria subCritProtocolAction = new Criteria();
        subCritProtocolAction.addEqualToField("protocolId", Criteria.PARENT_QUERY_PREFIX + "protocolId");
        subCritProtocolAction.addIn(PROTOCOL_ACTION_TYPE_CODE, getRevisionRequestedProtocolActionTypeCodesHook());
        if (startDate != null) {
            subCritProtocolAction.addGreaterOrEqualThan(ACTUAL_ACTION_DATE, startDate);
        }
        if (endDate != null) {
            subCritProtocolAction.addLessThan(ACTUAL_ACTION_DATE, nextDay(endDate));
        }
        ReportQueryByCriteria subQueryProtocolAction = QueryFactory.newReportQuery(getProtocolActionBOClassHoook(), subCritProtocolAction);
        
        Criteria crit = new Criteria();
        crit.addIn(PROTOCOL_STATUS_CODE, getRevisionRequestedProtocolStatusCodesHook());
        crit.addEqualTo(PROTOCOL_SUBMISSIONS_COMMITTEE_ID, committeeId);
        crit.addEqualTo(SEQUENCE_NUMBER, getSubQueryMaxSequenceNumber());
        crit.addEqualTo(PROTOCOL_SUBMISSIONS_SUBMISSION_NUMBER, getsubQueryMaxProtocolSubmission());
        crit.addExists(subQueryProtocolAction);
        Query q = QueryFactory.newQuery(getProtocolBOClassHook(), crit, true);
        logQuery(q);
        return (List<ProtocolBase>) getPersistenceBrokerTemplate().getCollectionByQuery(q);
    }
    
    
    protected abstract Collection<String> getRevisionRequestedProtocolActionTypeCodesHook();

    protected abstract Collection<String> getRevisionRequestedProtocolStatusCodesHook();

    protected abstract Class<? extends ProtocolActionBase> getProtocolActionBOClassHoook();
    
    
    private ReportQueryByCriteria getSubQueryMaxSequenceNumber() {
        Criteria subCritMaxSequenceNumber = new Criteria();
        subCritMaxSequenceNumber.addEqualToField(PROTOCOL_NUMBER, Criteria.PARENT_QUERY_PREFIX + PROTOCOL_NUMBER);
        ReportQueryByCriteria subQueryMaxSequenceNumber = QueryFactory.newReportQuery(getProtocolBOClassHook(), subCritMaxSequenceNumber);
        subQueryMaxSequenceNumber.setAttributes(new String[] { "max(sequence_number)" });
        return subQueryMaxSequenceNumber;
    }
    
    private ReportQueryByCriteria getsubQueryMaxProtocolSubmission() {
        Criteria subCritMaxSubmissionNumber = new Criteria();
        subCritMaxSubmissionNumber.addEqualToField(PROTOCOL_NUMBER, Criteria.PARENT_QUERY_PREFIX + PROTOCOL_NUMBER);
        ReportQueryByCriteria subQueryMaxProtocolSubmission = QueryFactory.newReportQuery(getProtocolSubmissionBOClassHook(), subCritMaxSubmissionNumber);
        subQueryMaxProtocolSubmission.setAttributes(new String[] { "max(submission_number)" });
        return subQueryMaxProtocolSubmission;
    }
    
    /**
     * This method calculates the next day (i.e. adds one day to the date).
     *
     * @return date the next day
     */
    private Date nextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return new Date(calendar.getTimeInMillis());
    }
    
    /**
     * Logs the Query
     * @param q the query
     */
    private static void logQuery(Query q) {
        if (LOG.isDebugEnabled()) {
            LOG.debug(q.toString());
        }
    }
    
    /**
     * Checks that an attachment is valid for a version attachment lookup.
     * @param attachment the attachment.
     * @return true if valid false if not
     */
    private <T extends ProtocolAttachmentBase & TypedAttachment> boolean validAttachmentForVersionLookup(T attachment) {
        return attachment != null && attachment.getProtocolNumber() != null && attachment.getSequenceNumber() != null
            && attachment.getProtocolId() != null && attachment.getTypeCode() != null && attachment.getDocumentId() != null;
    }

    /*
     * initialize several maps &amp; lists for use
     */
    private void initialization() {
        initExcludedFields();
        initCollectionFields();
        initRoleLists();
    }

    /*
     * 
     * This method to set up the list of fields that should not be included in the query criteria.
     */
    private void initExcludedFields() {
        excludedFields.add(KRADConstants.BACK_LOCATION);
        excludedFields.add(KRADConstants.DOC_FORM_KEY);
    }
    
    /*
     * set up the list to include the property that will be used to map to collection classes
     */
    protected void initCollectionFields() {
        for(CriteriaFieldHelper criteriaHelper : getCriteriaFields()) {
            collectionFieldNames.add(criteriaHelper.getSearchKey());
        }
    }
    

    /*
     * investigator include PI &amp; COI roles, key person includes SP/CA/CRC
     */
    private void initRoleLists() {
        initRoleListsHook(investigatorRole, personRole);
    }

    protected abstract void initRoleListsHook(List<String> investigatorRoles, List<String> personRoles);

    /*
     * this is to set up criteria that will check existence in collection tables.
     */
    protected ReportQueryByCriteria getCollectionReportQuery(String key, CriteriaFieldHelper critField) {
        Criteria crit = new Criteria();
        crit.addEqualToField(ProtocolLookupConstants.Property.PROTOCOL_ID, Criteria.PARENT_QUERY_PREFIX + ProtocolLookupConstants.Property.PROTOCOL_ID);
        String nameValue = critField.getFieldValue().replace('*', '%');
        // need to use upper case
        String propertyName = getDbPlatform().getUpperCaseFunction() + "(" + critField.getCritFieldName() + ")";
        crit.addLike(propertyName, nameValue.toUpperCase());

        if (isProtocolPersonField(key)) {
            addPersonRoleId(key, crit);
        } else if (key.equals(ProtocolLookupConstants.Property.PERFORMING_ORGANIZATION_ID)) {
            crit.addLike(ProtocolLookupConstants.Property.PROTOCOL_ORGANIZATION_TYPE_CODE, ProtocolLookupConstants.Property.PERFORMING_ORGANIZATION_CODE);
        }
        return QueryFactory.newReportQuery(critField.getClazz(), crit);
    }
    
    /*
     * This is for personid &amp; principalinvestigatorid.
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
    private void setupCritMaps(Map<String, String> fieldValues, Map<String, String> baseLookupFieldValues, Map<String, CriteriaFieldHelper> collectionFieldValues) {

        fieldValues.entrySet().stream().filter(entry -> !excludedFields.contains(entry.getKey()) && StringUtils.isNotBlank(entry.getValue())).forEach(entry -> {
            if (collectionFieldNames.contains(entry.getKey())) {
                collectionFieldValues.put(entry.getKey(), getCriteria(entry));
            } else if (!entry.getKey().startsWith(LEAD_UNIT)) {
                baseLookupFieldValues.put(entry.getKey(), entry.getValue());
            }
        });
    }

    /*
     * This is to get the proper CritField based on parameters.
     */
    private CriteriaFieldHelper getCriteria (Entry<String, String> entry) {
        String searchKeyName = entry.getKey();
        CriteriaFieldHelper critFieldHelper = new CriteriaFieldHelper();
        for(CriteriaFieldHelper criteriaField : getCriteriaFields()) {
            if(criteriaField.getSearchKey().equalsIgnoreCase(searchKeyName)) {
                criteriaField.setFieldValue(entry.getValue());
                critFieldHelper = criteriaField;
                break;
            }
        }
        return critFieldHelper;
    }

        
    private boolean isProtocolPersonField(String fieldName) {
        return fieldName.equals(ProtocolLookupConstants.Property.KEY_PERSON) || fieldName.equals(ProtocolLookupConstants.Property.INVESTIGATOR);    
    }
    
    /*
     * This method is to set up the criteria for unitnumber and unitname.
     * It is using 'leadunitxxx' property name, but the criteria is for all protocol units.
     * Just like coeus, any unit match, then it will be in the result list.
     */
    private ReportQueryByCriteria getUnitReportQuery(Entry<String, String> entry) {
        Criteria crit = new Criteria();
        crit.addEqualToField(ProtocolLookupConstants.Property.PROTOCOL_ID, Criteria.PARENT_QUERY_PREFIX + ProtocolLookupConstants.Property.PROTOCOL_ID);

        // unitnumber
        Criteria subCrit = new Criteria();
        String nameValue = entry.getValue().replace('*', '%');
        final String propertyName;
        if (entry.getKey().equals(ProtocolLookupConstants.Property.LEAD_UNIT_NUMBER)) {
            propertyName = getDbPlatform().getUpperCaseFunction() + "(unitNumber)";
        } else {
            propertyName = getDbPlatform().getUpperCaseFunction() + "(unit.unitName)";
            
        }
        subCrit.addLike(propertyName, nameValue.toUpperCase());
        subCrit.addEqualToField(ProtocolLookupConstants.Property.PROTOCOL_PERSON_ID, Criteria.PARENT_QUERY_PREFIX 
                + ProtocolLookupConstants.Property.PROTOCOL_PERSON_ID);
        crit.addExists(QueryFactory.newReportQuery(getProtocolUnitBOClassHook(), subCrit));

        return QueryFactory.newReportQuery(getProtocolPersonBOClassHook(), crit);
    }

    
    /**
     * Builds up criteria object based on the object and map.
     * This method is copied from lookdaoojb, but not published in lookupdao, so can't access it directly.
     */
    private Criteria getCollectionCriteriaFromMap(PersistableBusinessObject businessObject, Map formProps) {
        Criteria criteria = new Criteria();
        for (Object o : formProps.keySet()) {
            String propertyName = (String) o;
            if (formProps.get(propertyName) instanceof Collection) {
                for (Object o1 : ((Collection) formProps.get(propertyName))) {
                    if (!lookupDao.createCriteria(businessObject, (String) o1, propertyName,
                            isCaseSensitive(businessObject, propertyName), false, criteria)) {
                        throw new RuntimeException("Invalid value in Collection");
                    }
                }
            } else {
                if (!lookupDao.createCriteria(businessObject, (String) formProps.get(propertyName), propertyName,
                        isCaseSensitive(businessObject, propertyName), false, criteria)) {
                    continue;
                }
            }
        }
        return criteria;
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
    
    public void setLookupDao(LookupDao lookupDao) {
        this.lookupDao = lookupDao;
    }


    public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
        this.dataDictionaryService = dataDictionaryService;
    }
    
    /**
     * select count(b.protocol_number)
     *           into li_SubmissionCount
     *           from osp$protocol b
     *               where b.protocol_number like as_protocol_number || '%'  and
     *               b.sequence_number = (select max(a.sequence_number) from osp$protocol a
     *                               where a.protocol_number = b.protocol_number) and
     *               b.protocol_status_code in (100, 101, 102, 103, 104, 105, 106);
     *
     *   Replaced above query with following query by removing sub-query and added java code to to get sub-query behaviour.
     *
     *   select (b.protocol_number) from protocol b where b.protocol_number like '001%' and
     *   b.protocol_status_code in (100, 101, 102, 103, 104, 105, 106);
     *
     */
    public boolean getProtocolSubmissionCountFromProtocol(String protocolNumber) {
        Criteria crit = new Criteria();
        
        crit.addLike(PROTOCOL_NUMBER, protocolNumber + "%");
        crit.addEqualTo(SEQUENCE_NUMBER, getMaxSequenceNumberQuery());
        crit.addIn(PROTOCOL_STATUS_CODE, getPendingAmendmentRenewalsProtocolStatusCodesHook());
        
        ReportQueryByCriteria query = QueryFactory.newReportQuery(getProtocolBOClassHook(), crit);
        
        logQuery(query);
        
        return getPersistenceBrokerTemplate().getCollectionByQuery(query).isEmpty();
        
    }    
    
    protected abstract Collection<String> getPendingAmendmentRenewalsProtocolStatusCodesHook();
    
    
    private ReportQueryByCriteria getMaxSequenceNumberQuery() {
        ReportQueryByCriteria subQuery;
        Criteria subCrit = new Criteria();
        subCrit.addEqualToField(PROTOCOL_NUMBER, Criteria.PARENT_QUERY_PREFIX + PROTOCOL_NUMBER);
        subQuery = QueryFactory.newReportQuery(getProtocolBOClassHook(), subCrit);
        subQuery.setAttributes(new String[] { "max(sequence_number)" });
        return subQuery;
    }
    
    protected abstract Class<? extends ProtocolBase> getProtocolBOClassHook();
    protected abstract Class<? extends ProtocolPersonBase> getProtocolPersonBOClassHook();
    protected abstract Class<? extends ProtocolUnitBase> getProtocolUnitBOClassHook();
    protected abstract Class<? extends ProtocolSubmissionBase> getProtocolSubmissionBOClassHook();
    
    protected abstract List<CriteriaFieldHelper> getCriteriaFields();

}
