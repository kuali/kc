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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.kra.bo.NoticeOfOpportunity;
import org.kuali.kra.bo.NsfCode;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.DeadlineType;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalBudgetStatus;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.hierarchy.bo.HierarchyProposalSummary;
import org.kuali.kra.proposaldevelopment.hierarchy.dao.ProposalHierarchyDao;
import org.kuali.rice.kns.dao.impl.PlatformAwareDaoBaseOjb;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.util.OjbCollectionAware;

/**
 * This class...
 */
public class ProposalHierarchyDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, ProposalHierarchyDao {
    private BusinessObjectService businessObjectService;
    private ParameterService parameterService;
    public static String[] HIERARCHY_PROPOSAL_ATTRIBUTES = {
        "proposalDocument.documentNumber",
        "requestedStartDateInitial",
        "requestedEndDateInitial",
        "ownedByUnit.organizationId", "ownedByUnit.unitName",
        "activityType.description",
        
        "proposalState.description",
        "proposalType.description",
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
        "agencyProgramCode",
        
    };

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * Sets the parameterService attribute value.
     * @param parameterService The parameterService to set.
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

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
        retval.setProposalNumber(proposalNumber);
        retval.setRequestedStartDateInitial((Date)result[1]);
        retval.setRequestedEndDateInitial((Date)result[2]);
        retval.setOwnedByUnitName((String)result[3] + " : " + (String)result[4]);
        retval.setActivityTypeName((String)result[5]);
        
        retval.setProposalStateName((String)result[6]);
        retval.setProposalTypeName((String)result[7]);
        retval.setNarrative(lookupNarrativeStatus(proposalNumber)); 
        retval.setTitle((String)result[8]);
        
        retval.setDeadlineDate((Date)result[9]);
        retval.setDeadlineType(lookupSponsorDeadlineTypeDescription(result[10]));
        retval.setSponsorName(lookupSponsorName(result[11]));
        retval.setPrimeSponsorCode(lookupSponsorName(result[12]));
        retval.setNsfCode(lookupNsfCodeDescription(result[13]));
        retval.setAgencyDivisionCode((String)result[14]);
        retval.setProgramAnnouncementTitle((String)result[15]);
        
        retval.setNoticeOfOpportunityName(lookupNoticeOfOpportunityDescription(result[16]));
        retval.setCfdaNumber((String)result[17]);
        retval.setProgramAnnouncementNumber((String)result[18]);
        retval.setSponsorProposalNumber((String)result[19]);
        retval.setSubcontracts((Boolean)result[20]);
        retval.setAgencyProgramCode((String)result[21]);
        retval.setBudgetVersionOverviews(getBudgetVersionOverviews(result[0].toString()));
        
        retval.setBudget("Incomplete");
        for (BudgetVersionOverview budget : retval.getBudgetVersionOverviews()) {
            if (budget.isFinalVersionFlag()) {
                String budgetStatusCompleteCode = this.parameterService.getParameterValue(BudgetDocument.class,
                        Constants.BUDGET_STATUS_COMPLETE_CODE);
                if (StringUtils.equalsIgnoreCase(budgetStatusCompleteCode, budget.getBudgetStatus())) {
                    retval.setBudget("Complete");
                }
                else {
                    retval.setBudget("Final");
                }
                break;
            }
        }
        
        populatePersonnel(retval, proposalNumber);
        
        return retval;
    }
    
    private String lookupNoticeOfOpportunityDescription(Object o){
        String retval = " ";
        if (o != null) {
            Map<String,Object> primaryKeys = new HashMap<String,Object>();
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
            Map<String,Object> primaryKeys = new HashMap<String,Object>();
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
            Map<String,Object> primaryKeys = new HashMap<String,Object>();
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
            Map<String,Object> primaryKeys = new HashMap<String,Object>();
            primaryKeys.put("sponsorCode", o);
            Object result = businessObjectService.findByPrimaryKey(Sponsor.class, primaryKeys);
            if (result != null) {
                retval = ((Sponsor)result).getSponsorCode() + " : " + ((Sponsor)result).getSponsorName();
            }
        }
        return retval;
    }

    private List<BudgetVersionOverview> getBudgetVersionOverviews(String proposalDocumentNumber) {
        Map<String,String> fieldValues = new HashMap<String,String>();
        fieldValues.put("parentDocumentKey", proposalDocumentNumber);
        Collection budgetDocumentVersions = businessObjectService.findMatchingOrderBy(BudgetDocumentVersion.class, fieldValues, "documentNumber", true);
        List<BudgetVersionOverview> retval = new ArrayList<BudgetVersionOverview>();
        for (Object version : budgetDocumentVersions) {
            retval.add(((BudgetDocumentVersion)version).getBudgetVersionOverview());
        }
        return retval;
    }
    
    private void populatePersonnel(HierarchyProposalSummary summary, String proposalNumber) {
        Map<String,String> fieldValues = new HashMap<String, String>();
        fieldValues.put("proposalNumber", proposalNumber);
        Collection personnel = businessObjectService.findMatching(ProposalPerson.class, fieldValues);
        List<ProposalPerson> coInvestigators = new ArrayList<ProposalPerson>();
        List<ProposalPerson> keyPersons = new ArrayList<ProposalPerson>();
        ProposalPerson person;
        String roleId;
        for (Object o : personnel) {
            person = (ProposalPerson)o;
            roleId = person.getProposalPersonRoleId();
            if (StringUtils.equalsIgnoreCase(roleId,"PI")) {
                summary.setPrincipleInvestigator(person);
            }
            else if(StringUtils.equalsIgnoreCase(roleId, "COI")) {
                coInvestigators.add(person);
            }
            else {
                keyPersons.add(person);
            }
        }
        summary.setCoInvestigators(coInvestigators);
        summary.setKeyPersons(keyPersons);
    }
    
    private String lookupNarrativeStatus(String proposalNumber) {
        String retval = "none";
        Map<String,String> fieldValues = new HashMap<String, String>();
        fieldValues.put("proposalNumber", proposalNumber);
        fieldValues.put("narrativeType.narrativeTypeCode", "1");
        Collection narratives = businessObjectService.findMatching(Narrative.class, fieldValues);
        Narrative narrative;
        for (Object o: narratives) {
            narrative = (Narrative)o;
            retval = narrative.getNarrativeStatus().getDescription();
        }
        return retval;
    }

    public List<String> getHierarchyChildProposalNumbers(String proposalNumber) {
        List<String> retval = new ArrayList<String>();
        
        ReportQueryByCriteria query = createHierarchyChildProposalNumberQuery(proposalNumber);
        Iterator iter = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(query);
        
        while(iter.hasNext()) {
            Object[] result = (Object[])iter.next();
            retval.add((String)result[0]);
        }
        return retval;
    }
    
    public List<ProposalBudgetStatus> getHierarchyChildProposalBudgetStatuses(String proposalNumber) {
        Criteria crit = new Criteria();
        crit.addIn("proposalNumber", createHierarchyChildProposalNumberQuery(proposalNumber));
        QueryByCriteria statusQuery = new QueryByCriteria(ProposalBudgetStatus.class, crit);
        
        return new ArrayList((Collection<ProposalBudgetStatus>)getPersistenceBrokerTemplate().getCollectionByQuery(statusQuery));
    }
    
    private ReportQueryByCriteria createHierarchyChildProposalNumberQuery(String proposalNumber) {
        Criteria crit = new Criteria();
        crit.addEqualTo("hierarchyParentProposalNumber", proposalNumber);
        
        ReportQueryByCriteria query = new ReportQueryByCriteria(DevelopmentProposal.class, crit);
        query.setAttributes(new String[]{ "proposalNumber" });
        return query;
    }
}
