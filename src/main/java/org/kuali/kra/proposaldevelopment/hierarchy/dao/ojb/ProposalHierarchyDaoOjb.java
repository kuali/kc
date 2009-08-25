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
package org.kuali.kra.proposaldevelopment.hierarchy.dao.ojb;

import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.NoticeOfOpportunity;
import org.kuali.kra.bo.NsfCode;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.proposaldevelopment.bo.DeadlineType;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.hierarchy.bo.HierarchyProposalSummary;
import org.kuali.kra.proposaldevelopment.hierarchy.dao.ProposalHierarchyDao;
import org.kuali.rice.kns.dao.impl.PlatformAwareDaoBaseOjb;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.OjbCollectionAware;

/**
 * This class...
 */
public class ProposalHierarchyDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, ProposalHierarchyDao {
    private BusinessObjectService businessObjectService;
    
    public static String[] HIERARCHY_PROPOSAL_ATTRIBUTES = {
        "proposalNumber",
        "requestedStartDateInitial",
        "requestedEndDateInitial",
        "ownedByUnit.organizationId", "ownedByUnit.unitName",
        "activityType.description",
        
        "proposalState.description",
        "proposalType.description",
        "title", // TODO replace with some indication of narrative status
        "title", // TODO replace with some indication of budget status
        "title",
        
        "deadlineDate",
        "deadlineType",
        "sponsor.sponsorCode",
        "primeSponsorCode",
        "nsfCode",
        "agencyDivisionCode",
        "programAnnouncementTitle",
        
        "noticeOfOpportunityCode",
        "cfdaNumber",
        "programAnnouncementNumber",
        "sponsorProposalNumber",
        "subcontracts",
        "agencyProgramCode"
        
    };

    /**
     * @see org.kuali.kra.proposaldevelopment.hierarchy.dao.ProposalHierarchyDao#getProposalSummary(java.lang.String)
     */
    public HierarchyProposalSummary getProposalSummary(String proposalNumber) {
        Criteria crit = new Criteria();
        crit.addEqualTo("proposalNumber", proposalNumber);
        
        ReportQueryByCriteria query = new ReportQueryByCriteria(DevelopmentProposal.class, crit);
        query.setAttributes(HIERARCHY_PROPOSAL_ATTRIBUTES);
        
        Iterator iter = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(query);
        Object[] result = (Object[])iter.next();
        HierarchyProposalSummary retval = new HierarchyProposalSummary();
        retval.setProposalNumber((String)result[0]);
        retval.setRequestedStartDateInitial((Date)result[1]);
        retval.setRequestedEndDateInitial((Date)result[2]);
        retval.setOwnedByUnitName((String)result[3] + " : " + (String)result[4]);
        retval.setActivityTypeName((String)result[5]);
        
        retval.setProposalStateName((String)result[6]);
        retval.setProposalTypeName((String)result[7]);
        retval.setNarrative("??"); // TODO 
        retval.setBudget("??"); // TODO
        retval.setTitle((String)result[10]);
        
        retval.setDeadlineDate((Date)result[11]);
        retval.setDeadlineType(lookupSponsorDeadlineTypeDescription(result[12]));
        retval.setSponsorName((String)result[13]);
        retval.setPrimeSponsorCode(lookupSponsorName(result[14]));
        retval.setNsfCode(lookupNsfCodeDescription(result[15]));
        retval.setAgencyDivisionCode((String)result[16]);
        retval.setProgramAnnouncementTitle((String)result[17]);
        
        retval.setNoticeOfOpportunityName(lookupNoticeOfOpportunityDescription(result[18]));
        retval.setCfdaNumber((String)result[19]);
        retval.setProgramAnnouncementNumber((String)result[20]);
        retval.setSponsorProposalNumber((String)result[21]);
        retval.setSubcontracts((Boolean)result[22]);
        retval.setAgencyProgramCode((String)result[23]);
        
        
        return retval;
    }
    
    private String lookupNoticeOfOpportunityDescription(Object o){
        String retval = " ";
        if (o != null) {
            Map primaryKeys = new HashMap();
            primaryKeys.put("noticeOfOpportunityCode", o);
            Object result = businessObjectService.findByPrimaryKey(NoticeOfOpportunity.class, primaryKeys);
            if (result != null) {
                retval = ((NoticeOfOpportunity)result).getDescription();
            }
        }
        return retval;
    }
    
    
    private String lookupSponsorDeadlineTypeDescription(Object o){
        String retval = " ";
        if (o != null) {
            Map primaryKeys = new HashMap();
            primaryKeys.put("deadlineTypeCode", o);
            Object result = businessObjectService.findByPrimaryKey(DeadlineType.class, primaryKeys);
            if (result != null) {
                retval = ((DeadlineType)result).getDescription();
            }
        }
        return retval;
    }

    private String lookupNsfCodeDescription(Object o){
        String retval = " ";
        if (o != null) {
            Map primaryKeys = new HashMap();
            primaryKeys.put("nsfCode", o);
            Object result = businessObjectService.findByPrimaryKey(NsfCode.class, primaryKeys);
            if (result != null) {
                retval = ((NsfCode)result).getDescription();
            }
        }
        return retval;
    }

    private String lookupSponsorName(Object o){
        String retval = " ";
        if (o != null) {
            Map primaryKeys = new HashMap();
            primaryKeys.put("sponsorCode", o);
            Object result = businessObjectService.findByPrimaryKey(Sponsor.class, primaryKeys);
            if (result != null) {
                retval = ((Sponsor)result).getSponsorCode() + " : " + ((Sponsor)result).getSponsorName();
            }
        }
        return retval;
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
