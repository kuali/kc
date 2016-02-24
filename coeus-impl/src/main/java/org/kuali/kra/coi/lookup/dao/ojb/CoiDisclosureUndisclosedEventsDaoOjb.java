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
package org.kuali.kra.coi.lookup.dao.ojb;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.coi.CoiDisclosureUndisclosedEvents;
import org.kuali.kra.coi.lookup.dao.CoiDisclosureUndisclosedEventsDao;
import org.kuali.kra.iacuc.personnel.IacucProtocolPerson;
import org.kuali.kra.iacuc.protocol.funding.IacucProtocolFundingSource;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSource;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;
import org.kuali.rice.krad.service.util.OjbCollectionAware;
import org.kuali.rice.krad.util.ObjectUtils;

import java.sql.Date;
import java.util.List;
import java.util.Map;


public class CoiDisclosureUndisclosedEventsDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, CoiDisclosureUndisclosedEventsDao {

    private static final Log LOG = LogFactory.getLog(CoiDisclosureUndisclosedEventsDaoOjb.class);

    private static final String PERSON_ID = "personId";
    private static final String DEV_PROPOSAL_SAVE_DATE = "developmentProposal.updateTimestamp";
    private static final String INST_PROPOSAL_SAVE_DATE = "institutionalProposal.updateTimestamp";
    private static final String AWARD_SAVE_DATE = "award.updateTimestamp";
    private static final String IRB_PROTOCOL_SAVE_DATE = "protocol.updateTimestamp";
    private static final String IACUC_PROTOCOL_SAVE_DATE = "protocol.updateTimestamp";
    
    private static final String DEV_PROPOSAL_SPONSOR_CODE = "developmentProposal.sponsorCode";
    private static final String INST_PROPOSAL_SPONSOR_CODE = "institutionalProposal.sponsorCode";
    private static final String AWARD_SPONSOR_CODE = "award.sponsorCode";
    
    private static final String FUNDING_SOURCE_TYPE_FIELD = "protocol.protocolFundingSources.fundingSourceTypeCode";
    private static final String FUNDING_SOURCE_NUMBER_FIELD = "protocol.protocolFundingSources.fundingSourceNumber";
    private static final String FUNDING_SOURCE_COLLECTION = "protocol.protocolFundingSources";
    
    @SuppressWarnings("unchecked")
    public List<InstitutionalProposalPerson> getInstituteProposalPersons(Map<String, String> fieldValues) {
        QueryByCriteria query = QueryFactory.newQuery(InstitutionalProposalPerson.class, getSearchCriteria(fieldValues, INST_PROPOSAL_SAVE_DATE, INST_PROPOSAL_SPONSOR_CODE));
        return (List<InstitutionalProposalPerson>) getPersistenceBrokerTemplate().getCollectionByQuery(query);
    }

    @SuppressWarnings("unchecked")
    public List<AwardPerson> getAwardPersons(Map<String, String> fieldValues) {
        QueryByCriteria query = QueryFactory.newQuery(AwardPerson.class, getSearchCriteria(fieldValues, AWARD_SAVE_DATE, AWARD_SPONSOR_CODE));
        return (List<AwardPerson>) getPersistenceBrokerTemplate().getCollectionByQuery(query);
    }

    @SuppressWarnings("unchecked")
    public List<IacucProtocolPerson> getIacucProtocolPersons(Map<String, String> fieldValues) {
        Criteria searchCriteria = getSearchCriteria(fieldValues, IACUC_PROTOCOL_SAVE_DATE);
        String sponsorCode = getSponsorCriteria(fieldValues);
        if(StringUtils.isNotBlank(sponsorCode)) {
            Criteria sponsorCriteria = getIacucProtocolSponsorCriteria(sponsorCode);
            searchCriteria.addAndCriteria(sponsorCriteria);        
        }
        QueryByCriteria query = QueryFactory.newQuery(IacucProtocolPerson.class, searchCriteria);
        return (List<IacucProtocolPerson>) getPersistenceBrokerTemplate().getCollectionByQuery(query);
    }

    @SuppressWarnings("unchecked")
    public List<ProtocolPerson> getIrbProtocolPersons(Map<String, String> fieldValues) {
        Criteria searchCriteria = getSearchCriteria(fieldValues, IRB_PROTOCOL_SAVE_DATE);
        String sponsorCode = getSponsorCriteria(fieldValues);
        if(StringUtils.isNotBlank(sponsorCode)) {
            Criteria sponsorCriteria = getIrbProtocolSponsorCriteria(sponsorCode);
            searchCriteria.addAndCriteria(sponsorCriteria);        
        }
        QueryByCriteria query = QueryFactory.newQuery(ProtocolPerson.class, searchCriteria);
        return (List<ProtocolPerson>) getPersistenceBrokerTemplate().getCollectionByQuery(query);
    }

    /**
     * This method is to extract search criteria values and build a query criteria.
     * First get common criteria and add sponsor criteria.
     * This method is invoked for dev proposal, institute proposal and awards.
     * Here we add criteria applicable for all projects.
     * @param fieldValues
     * @param saveDateField
     * @param sponsorField
     * @return
     */
    private Criteria getSearchCriteria(Map<String, String> fieldValues, String saveDateField, String sponsorField) {
        Criteria searchCriteria = getSearchCriteria(fieldValues, saveDateField);
        String sponsorCode = getSponsorCriteria(fieldValues);
        if(StringUtils.isNotBlank(sponsorCode)) {
            searchCriteria.addEqualTo(sponsorField, sponsorCode);
        }
        return searchCriteria;
    }
    
    /**
     * This method is to extract search criteria values and build a query criteria.
     * Here we add criteria applicable for all projects.
     * @param fieldValues
     * @param saveDateField
     * @return
     */
    private Criteria getSearchCriteria(Map<String, String> fieldValues, String saveDateField) {
        Criteria searchCriteria = new Criteria();
        String personId = fieldValues.get(CoiDisclosureUndisclosedEvents.SEARCH_CRITERIA_REPORTER);
        String strDateFrom = fieldValues.get(CoiDisclosureUndisclosedEvents.SEARCH_CRITERIA_CREATE_DATE_FROM);
        String strDateTo = fieldValues.get(CoiDisclosureUndisclosedEvents.SEARCH_CRITERIA_CREATE_DATE_TO);
        
        Date createDateFrom = null;
        Date createDateTo = null;
        try {
            if(StringUtils.isNotBlank(strDateFrom)) {
                createDateFrom = CoreApiServiceLocator.getDateTimeService().convertToSqlDate(strDateFrom);
            }
            if(StringUtils.isNotBlank(strDateTo)) {
                createDateTo = CoreApiServiceLocator.getDateTimeService().convertToSqlDate(strDateTo);
            }
        }catch(Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
        
        if(!StringUtils.isBlank(personId)) {
            searchCriteria.addEqualTo(PERSON_ID, personId);
        }
        
        if(ObjectUtils.isNotNull(createDateFrom)) {
            searchCriteria.addGreaterThan(saveDateField, createDateFrom);
        }
        
        if(ObjectUtils.isNotNull(createDateTo)) {
            searchCriteria.addLessThan(saveDateField, createDateTo);
        }
        
        return searchCriteria;
    }
    
    private Criteria getIacucProtocolSponsorCriteria(String sponsorCode) {
        return getProtocolSponsorCriteria(sponsorCode, IacucProtocolFundingSource.class);
    }
    
    private Criteria getIrbProtocolSponsorCriteria(String sponsorCode) {
        return getProtocolSponsorCriteria(sponsorCode, ProtocolFundingSource.class);
    }

    /**
     * This method is to build criteria for sponsor linked to protocols (irb and iacuc)
     * sponsors are tied to funding sources collection with type sponsor.
     * @param sponsorCode
     * @param fundingSourceBusinessObject
     * @return
     */
    private Criteria getProtocolSponsorCriteria(String sponsorCode, Class<? extends ProtocolFundingSourceBase> fundingSourceBusinessObject) {
        Criteria sponsorCriteria = new Criteria();
        if(StringUtils.isNotBlank(sponsorCode)) {
            sponsorCriteria.addEqualTo(FUNDING_SOURCE_TYPE_FIELD, FundingSourceType.SPONSOR);
            sponsorCriteria.addEqualTo(FUNDING_SOURCE_NUMBER_FIELD, sponsorCode);
            sponsorCriteria.addPathClass(FUNDING_SOURCE_COLLECTION, fundingSourceBusinessObject);
        }
        return sponsorCriteria;
    }
    
    private String getSponsorCriteria(Map<String, String> fieldValues) {
        return fieldValues.get(CoiDisclosureUndisclosedEvents.SEARCH_CRITERIA_SPONSOR);
    }

}
