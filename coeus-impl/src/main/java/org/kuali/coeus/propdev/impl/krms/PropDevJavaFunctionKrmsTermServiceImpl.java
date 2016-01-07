/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.krms;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.person.attr.PersonAppointment;
import org.kuali.coeus.common.framework.sponsor.hierarchy.SponsorHierarchy;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentUtils;
import org.kuali.coeus.propdev.impl.core.ProposalTypeService;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewApprovalType;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewType;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.AppointmentType;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.impl.krms.KcKrmsJavaFunctionTermServiceBase;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.coeus.propdev.impl.person.ProposalPersonUnit;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwards;
import org.kuali.coeus.propdev.impl.person.question.ProposalPersonModuleQuestionnaireBean;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.coeus.propdev.impl.s2s.S2sOppForms;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kew.api.action.ActionRequest;
import org.kuali.rice.kew.api.action.ActionType;
import org.kuali.rice.kim.api.identity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("propDevJavaFunctionKrmsTermService")
public class PropDevJavaFunctionKrmsTermServiceImpl extends KcKrmsJavaFunctionTermServiceBase implements PropDevJavaFunctionKrmsTermService {

    private static final int INT_U_S_CITIZEN_OR_NONCITIZEN_NATIONAL = 1;
    private static final int INT_PERMANENT_RESIDENT_OF_U_S = 2;
    private static final int INT_NON_U_S_CITIZEN_WITH_TEMPORARY_VISA = 3;
    private static final int INT_PERMANENT_RESIDENT_OF_U_S_PENDING = 4;
    private static final Log LOG = LogFactory.getLog(PropDevJavaFunctionKrmsTermServiceImpl.class);

    @Autowired
    @Qualifier("dateTimeService")
    private DateTimeService dateTimeService;
    
    
    @Autowired
    @Qualifier("questionnaireAnswerService")
    private QuestionnaireAnswerService questionnaireAnswerService;

    @Autowired
    @Qualifier("unitService")
    private UnitService unitService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;
    
    @Autowired
    @Qualifier("proposalTypeService")
    private ProposalTypeService proposalTypeService;

    /**
     * 
     * This method checks if the formName is included in the given proposal
     * @return 'true' if true
     */
    @Override
    public Boolean specifiedGGForm(DevelopmentProposal developmentProposal, String formNames) {
        for (String formName :  buildArrayFromCommaList(formNames)) {
            for (S2sOppForms s2sOppForm : developmentProposal.getS2sOppForms()) {
                if(s2sOppForm.getInclude() != null && s2sOppForm.getInclude() && s2sOppForm.getFormName().equals(formName.trim())){
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }
    
    /**
     * This method checks if the proposal has multiple PIs set.
     * see FN_MULTIPI_RULE.
     * @return 'true' if true
     */
    @Override
    public String multiplePI(DevelopmentProposal developmentProposal) {
        List<ProposalPerson> people = developmentProposal.getProposalPersons();
        for (ProposalPerson person : people) {
            if (person.isMultiplePi()) {
                return TRUE;
            }
        }
        return FALSE;
        
    }
    /**
     * 
     * This method checks if the passed in forms are included.
     * see FN_S2S_BUDGET_RULE.
     * @param formNames a comma delimited list of s2s forms to check against.
     * @return 'true' if true
     */
    @Override
    public String s2sBudgetRule(DevelopmentProposal developmentProposal, String formNames){
        /**
         * F.FORM_NAME in ('RR Budget V1-1','RR SubAward Budget V1.2','RR_FedNonFed_SubawardBudget-V1.2',
         * 'RR_FedNonFed_SubawardBudget-V1.1','RR SubAward Budget V1.1','PHS398 Modular Budget V1-1', 'PHS398 Modular Budget V1-2')
         */
        String[] formNamesArray = buildArrayFromCommaList(formNames);
        int li_count_bud = 0;
        for (String formName : formNamesArray) {
            for(S2sOppForms form: developmentProposal.getS2sOppForms()) {
                if (StringUtils.equalsIgnoreCase(formName, form.getFormName()) && form.getInclude()) {
                    li_count_bud++;
                }
            }
        }
        int li_count_s2s = developmentProposal.getS2sOpportunity() != null ? 1 : 0;
        
        if (li_count_bud != 0 && li_count_s2s <= 0) {
            return TRUE;
        }
        return FALSE;
    }
    
    /**
     * 
     * This method checks if the proposal is associated with one of monitored sponsored hierarchies. 
     * see FN_COI_MONITORED_SPONSOR_RULE.
     * @param monitoredSponsorHirearchies a comma delimited list of sponsored hirearchies.
     * @return 'true' if true
     */
    @Override
    public String monitoredSponsorRule(DevelopmentProposal developmentProposal, String monitoredSponsorHirearchies) {
        String[] sponsoredHierarchyArray = buildArrayFromCommaList(monitoredSponsorHirearchies);
        ArrayList<SponsorHierarchy> hierarchies = new ArrayList<>();
        for (String hierarchyName : sponsoredHierarchyArray) {
            Map<String, String> fieldValues = new HashMap<>();
            fieldValues.put("HIERARCH_NAME", hierarchyName);
            hierarchies.addAll(this.getBusinessObjectService().findMatching(SponsorHierarchy.class, fieldValues));
        }
        for (SponsorHierarchy sh : hierarchies) {
            if (StringUtils.equalsIgnoreCase(sh.getSponsorCode(), developmentProposal.getSponsor().getSponsorCode())
                    || StringUtils.equalsIgnoreCase(sh.getSponsorCode(), developmentProposal.getPrimeSponsor().getSponsorCode())) {
                return TRUE;
            }
        }
        
        return FALSE;
    }
    
    /**
     * 
     * This method determines if the proposal has more than the maximum number of attachments of the types provided in the narativeTypes list.
     * see FN_S2S_RESPLAN_RULE.
     * @param narativeTypes a comma delimited list of narrative types.
     * @param maxNumber the maximum number to check.
     * @return 'true' if true
     */
    @Override
    public String s2sResplanRule(DevelopmentProposal developmentProposal, String narativeTypes, String maxNumber) {
        //- max of 10 narrative types PHS_ResearchPlan_Appendix
        String[] narrativeTypesArray = buildArrayFromCommaList(narativeTypes);
        int[] narrativeCounts = new int[narrativeTypesArray.length];
        int maxNumberInt = Integer.parseInt(maxNumber);
        for (Narrative narrative : developmentProposal.getNarratives()) {
            int narrativePosition = 0;
            for (String narrativeType : narrativeTypesArray) {
                if (StringUtils.equalsIgnoreCase(narrativeType, narrative.getNarrativeType().getDescription())) {
                    narrativeCounts[narrativePosition]++;
                    if (narrativeCounts[narrativePosition] > maxNumberInt) {
                        return TRUE;
                    }
                }
                narrativePosition++;
            }
        }
        return FALSE;
    }
    
    /**
     * 
     * This method checks if the proposal is associated the grants form passed in. 
     * see FN_GG_FORM_RULE.
     * @param formName the grants form to check against.
     * @return 'true' if true
     */
    @Override
    public String grantsFormRule(DevelopmentProposal developmentProposal, String formName) {
        for(S2sOppForms form: developmentProposal.getS2sOppForms()) {
            if (StringUtils.equalsIgnoreCase(formName, form.getFormName())) {
                return TRUE;
            }
        }
        return FALSE;
    }
    
    /**
     * 
     * This method checks to see if the biosketch file names contain any restricted special characters.
     * See  fn_prop_pers_att_name_rule 
     * @return 'true' if no special characters are found.
     */
    @Override
    public String biosketchFileNameRule(DevelopmentProposal developmentProposal) {
        for (ProposalPersonBiography ppb : developmentProposal.getPropPersonBios()) {
            if (StringUtils.equalsIgnoreCase(ppb.getPropPerDocType().getDescription(), "Biosketch")) {
                if (StringUtils.equals(FALSE, specialCharacterRule(ppb.getName()))) {
                    return FALSE;
                }
            }
        }
        return TRUE;
        
    }
    
    /**
     * 
     * This method checks to see if the OSP administrator is also a personal person.
     * See  FN_OSP_ADMIN_IS_PERSON 
     * @return 'true' if the OSP admin is also a proposal person, otherwise 'false'
     */
    @Override
    public String ospAdminPropPersonRule(DevelopmentProposal developmentProposal) {
        List<UnitAdministrator> ospAdmins = developmentProposal.getUnit().getUnitAdministrators();
        if (ospAdmins != null && ospAdmins.size() > 0) {
            for (ProposalPerson person : developmentProposal.getProposalPersons()) {
                for (UnitAdministrator admin : ospAdmins) {
                    if (StringUtils.equals(person.getPersonId(), admin.getPersonId())) {
                        return TRUE;
                    }
                }
            }
        }
        return FALSE;
    }
    
    /**
     * 
     * This method is used to check if , in any period of the given version of budget, the given cost element has crossed the given limit or not.
     * See  fn_cost_element_ver_per_limit  AND fn_cost_element_limit_in_ver
     * @param versionNumber the version number to be checked
     * @param costElementName the cost element to be checked
     * @param limit the amount limit to be checked
     * @return 'true' - if the total cost of the CE crossed the limit in any one of the period, otherwise 'false'
     */
    @Override
    public String costElementVersionLimit(DevelopmentProposal developmentProposal, String versionNumber, String costElementName, String limit) {
        Long versionNumberLong = Long.parseLong(versionNumber);
        float limitLong = Float.parseFloat(limit);
        for (Budget budget : developmentProposal.getBudgets()) {
            if (budget.getVersionNumber().equals(versionNumberLong)) {
                try {
                    if (budget.getVersionNumber().equals(versionNumberLong)) {
                        for (BudgetPeriod period : budget.getBudgetPeriods()) {
                            float costElementTotal = 0;
                            for (BudgetLineItem item : period.getBudgetLineItems()) {
                                if (StringUtils.equalsIgnoreCase(costElementName, item.getCostElementName())) {
                                    costElementTotal = costElementTotal + item.getLineItemCost().floatValue();
                                }
                            }
                            if (costElementTotal > limitLong) {
                                return TRUE;
                            }
                        }
                    }
                } catch (Exception e) {
                    //lets just ignor and return false.
                }
            }
        }
        return FALSE;
    }
    
    /**
     * 
     * This method is used to CHECK IF a proposal's division code field  is  null
     * See  fn_agency_divcode_is_null_rule 
     * @return 'true' - if the division field is null, otherwise return false.
     */
    @Override
    public String divisionCodeRule(DevelopmentProposal developmentProposal) {
        return StringUtils.isEmpty(developmentProposal.getAgencyDivisionCode()) ? TRUE : FALSE;
    }
    
    /**
     * 
     * This method is used to CHECK IF THIS PROPOSAL IS FOR A FELLOWSHIP.  Typically 3 or 7 is a fellowship code.
     * See  FN_IS_FELLOWSHIP 
     * @return 'true' - if the division field is a fellowship code, otherwise return false.
     */
    @Override
    public String divisionCodeIsFellowship(DevelopmentProposal developmentProposal, String fellowshipCodes) {
        String[] fellowShipCodeArray = buildArrayFromCommaList(fellowshipCodes);
        for (String code : fellowShipCodeArray) {
            if (StringUtils.equalsIgnoreCase(code, developmentProposal.getActivityTypeCode())) {
                return TRUE;
            }
        }
        return FALSE;
    }
    
    /**
     * 
     * This method is used to check if a budget sub award organization name contains special characters.  
     * See  fn_sub_awd_org_name_rule  
     * @return 'true' - if it does not contain special characters.  If it does have special characters, otherwise returns 'false'.
     */
    @Override
    public String budgetSubawardOrganizationnameRule(DevelopmentProposal developmentProposal) {
    	if (developmentProposal.getFinalBudget() != null) {
            for (BudgetSubAwards bsa : developmentProposal.getFinalBudget().getBudgetSubAwards()) {
                if (StringUtils.equals(FALSE, specialCharacterRule(bsa.getOrganizationName()))) {
                    return FALSE;
                }
            }
        }
        return TRUE;
    }
    
    /**
     * 
     * This method is used to check if the passed in personId, is among the proposal people.  
     * See FN_CHECK_PROPOSAL_KEY_PERSON   
     * @return 'true' - if the logged in user is a proposal person, otherwise returns 'false'.
     */
    @Override
    public String checkProposalPerson(DevelopmentProposal developmentProposal, String personId) {
        for (ProposalPerson person : developmentProposal.getProposalPersons()) {
            if (StringUtils.equals(person.getPersonId(), personId)) {
                return TRUE;
            }
        }
        return FALSE;
    }
    
    /**
     * 
     * This method is a rule to CHECK IF a proposal's agency program code field  is  null  
     * See fn_ag_progcode_is_null_rule   
     * @return 'true' - if the agency program code is NULL, otherwise returns 'false'.
     */
    @Override
    public String agencyProgramCodeNullRule(DevelopmentProposal developmentProposal){
        return StringUtils.isEmpty(developmentProposal.getAgencyProgramCode()) ? TRUE : FALSE;
    }
    
    /**
     * 
     * This method returns 'true', and is implemented because the original function existed.  
     * See FN_ALL_PROPOSALS_RULE   
     * @return 'true' always
     */
    @Override
    public String allProposalsRule(DevelopmentProposal developmentProposal) {
        return TRUE;
    }
    
    /**
     * 
     * This method checks to see if the proposal lead unit is in the unit hierarchy of the passed in unit.  
     * See FN_LEAD_UNIT_BELOW   
     * @return 'true' if the lead unit is in the unit hiearchy of the passed in unit, otherwise returns 'false'.
     */
    @Override
    public String proposalLeadUnitInHierarchy(DevelopmentProposal developmentProposal, String unitNumberToCheck) {
        if (StringUtils.equals(developmentProposal.getUnitNumber(), unitNumberToCheck)) {
            return TRUE;
        }
        List<Unit> units = this.getUnitService().getAllSubUnits(unitNumberToCheck);
        if (units != null && units.size() > 0) {
            for (Unit unit : units) {
                if (StringUtils.equals(developmentProposal.getUnitNumber(), unit.getUnitNumber())) {
                    return TRUE;
                }
            }
        }
        return FALSE;
    }
    
    /**
     * 
     * This method verifies that there is not both a restricted set of RR forms along with PHS forms.  
     * See FN_S2S_SUBAWARD_RULE   
     * @return 'true' if there are RR forms and PHS forms, otherwise returns 'false'.
     */
    @Override
    public String s2sSubawardRule(DevelopmentProposal developmentProposal, String rrFormNames, String phsFromNames) {
        
        /**
         * And     F.FORM_NAME in ('RR SubAward Budget V1.2','RR_FedNonFed_SubawardBudget-V1.2',
         * 'RR_FedNonFed_SubawardBudget-V1.1','RR SubAward Budget V1.1')
         * 
         * And     F.FORM_NAME in ('PHS398 Modular Budget V1-1','PHS398 Modular Budget V1-0', 'PHS398 Modular Budget V1-2')
         */
        
        boolean foundRRforms = false;
        boolean foundPHSforms = false;
        String[] rrFormNamesArray =  buildArrayFromCommaList(rrFormNames);
        String[] phsFromNamesArray =  buildArrayFromCommaList(phsFromNames);
        
        for(S2sOppForms form: developmentProposal.getS2sOppForms()) {
            if (form.getInclude()) {
                for (String formName : rrFormNamesArray) {
                    if (StringUtils.equalsIgnoreCase(formName, form.getFormName())) {
                        foundRRforms = true;
                    }
                }
                for (String formName : phsFromNamesArray) {
                    if (StringUtils.equalsIgnoreCase(formName, form.getFormName())) {
                        foundPHSforms = true;
                    }
                }
            }
        }
        return foundRRforms && foundPHSforms ? TRUE : FALSE;
    }
    
    /**
     * 
     * This method verifies that there are grans.gov submissions.  
     * See FN_IS_GG_RULE   
     * @return 'true' if there are grants.gov submssion, otherwise returns 'false'.
     */
    @Override
    public String proposalGrantsRule(DevelopmentProposal developmentProposal) {
        return developmentProposal.getS2sOpportunity()!=null ? TRUE : FALSE;
    }
    
    /**
     * 
     * This method verifies that the activity type is specified.  
     * See FN_NARRATIVE_TYPE_RULE   
     * @return 'true' if there an activity type, otherwise returns 'false'.
     */
    @Override
    public String narrativeTypeRule(DevelopmentProposal developmentProposal,String narrativeTypeCode) {
        List<Narrative> attachments = new ArrayList<>();
        attachments.addAll(developmentProposal.getNarratives());
        attachments.addAll(developmentProposal.getInstituteAttachments());
        for (Narrative narrative : attachments) {
            if(narrative.getNarrativeTypeCode().equals(narrativeTypeCode)){
                return TRUE;
            }
        }
        return FALSE;
    }

    @Override
    public String activityTypeRule(DevelopmentProposal developmentProposal, String activityTypeCode) {
        if (StringUtils.equals(developmentProposal.getActivityTypeCode(), activityTypeCode)) {
            return TRUE;
        } else {
            return FALSE;
        }
    }

    @Override
    public String attachmentFileNameRule(DevelopmentProposal developmentProposal) {
        for (Narrative narr : developmentProposal.getNarratives()) {
            for (String character : restrictedElements) {
                if (StringUtils.containsIgnoreCase(narr.getName(), character)) {
                    return FALSE;
                }
            }
        }
        return TRUE;
    }

    @Override
    public String checkProposalCoiRule(DevelopmentProposal developmentProposal, String principalId) {
        for (ProposalPerson person : developmentProposal.getInvestigators()) {
            if (person.isInvestigator() && person.isPrincipalInvestigator()
                    && StringUtils.equals(principalId, person.getPersonId())) {
                return TRUE;
            }
        }
        return FALSE;
    }

    @Override
    public String checkProposalPiRule(DevelopmentProposal developmentProposal, String principalId) {
        for (ProposalPerson person : developmentProposal.getInvestigators()) {
            if (person.isInvestigator() && person.isPrincipalInvestigator()
                    && StringUtils.equals(principalId, person.getPersonId())) {
                return TRUE;
            }
        }
        return FALSE;
    }

    @Override
    public String costElement(DevelopmentProposal developmentProposal, String costElement) {
        for (Budget budgetVersion : developmentProposal.getBudgets()) {
            Map<String, Object> values = new HashMap<String, Object>();
            values.put("costElement", costElement);
            values.put("budgetId", budgetVersion.getBudgetId());
            List<BudgetLineItem> matchingLineItems = 
                (List<BudgetLineItem>) getBusinessObjectService().findMatching(BudgetLineItem.class, values);
            if (matchingLineItems != null && !matchingLineItems.isEmpty()) {
                return TRUE;
            }
        }
        return FALSE;
    }

    @Override
    public String leadUnitBelowRule(DevelopmentProposal developmentProposal, String unitNumber) {
        if (StringUtils.equals(developmentProposal.getUnitNumber(), unitNumber)) {
            return TRUE;
        } else {
            for (Unit unit : getUnitService().getAllSubUnits(unitNumber)) {
                if (StringUtils.equals(unit.getUnitNumber(), developmentProposal.getUnitNumber())) {
                    return TRUE;
                }
            }
        }
        return FALSE;
    }

    @Override
    public String leadUnitRule(DevelopmentProposal developmentProposal, String unitNumber) {
        if (StringUtils.equals(developmentProposal.getUnitNumber(), unitNumber)) {
            return TRUE;
        } else {
            return FALSE;
        }
    }

    @Override
    public String mtdcDeviation(DevelopmentProposal developmentProposal) {
        if (mtdcDeviationInBudget(developmentProposal.getFinalBudget())) {
            return TRUE;
        } else {
            return FALSE;
        }
    }

    @Override
    public String mtdcDeviationInVersion(DevelopmentProposal developmentProposal, String versionNumber) {
        if (mtdcDeviationInBudget(getBudgetVersion(developmentProposal, versionNumber))) {
            return TRUE;
        } else {
            return FALSE;
        }
    }
    
    protected Budget getBudgetVersion(DevelopmentProposal developmentProposal, String versionNumber) {
        Integer versionNumberLong = Integer.valueOf(versionNumber);
        for (Budget bdv : developmentProposal.getBudgets()) {
            if (bdv.getBudgetVersionNumber().equals(versionNumberLong)) {
            	return bdv;
            }
        }
        return null;
    }
    
    protected boolean mtdcDeviationInBudget(Budget budget) {
        return budget != null && StringUtils.equals(budget.getOhRateClassCode(), "1");
    }

    @Override
    public String nonFacultyPi(DevelopmentProposal developmentProposal) {
        if (developmentProposal.getPrincipalInvestigator()!=null && developmentProposal.getPrincipalInvestigator().getFacultyFlag()) {
            return FALSE;
        } else {
            return TRUE;
        }
    }

    @Override
    public String proposalAwardTypeRule(DevelopmentProposal developmentProposal, Integer awardTypeCode) {
        if (awardTypeCode.equals(developmentProposal.getAnticipatedAwardTypeCode())) {
            return TRUE;
        } else {
            return FALSE;
        }
    }

    @Override
    public String proposalTypeRule(DevelopmentProposal developmentProposal, String proposalTypeCode) {
        if (StringUtils.equals(developmentProposal.getProposalTypeCode(), proposalTypeCode)) {
            return TRUE;
        } else {
            return FALSE;
        }
    }

    @Override
    public String proposalUnitRule(DevelopmentProposal developmentProposal, String unitNumber) {
        for (ProposalPerson person : developmentProposal.getProposalPersons()) {
            for (ProposalPersonUnit unit : person.getUnits()) {
                if (StringUtils.equals(unit.getUnitNumber(), unitNumber)) {
                    return TRUE;
                }
            }
        }
        return FALSE;
    }

    @Override
    public String s2sAttachmentNarrativeRule(DevelopmentProposal developmentProposal) {
        if (developmentProposal.getS2sOpportunity() != null) {
            for (Narrative narrative : developmentProposal.getNarratives()) {
                if (StringUtils.equals(narrative.getNarrativeTypeCode(), "61")
                        && narrative.getModuleTitle() == null) {
                    return FALSE;
                }
            }
        }
        return TRUE;
    }

    @Override
    public String s2sExemptionRule(DevelopmentProposal developmentProposal) {
        boolean irbLinkingEnabled = getParameterService().getParameterValueAsBoolean(ProposalDevelopmentDocument.class, Constants.ENABLE_PROTOCOL_TO_DEV_PROPOSAL_LINK);
        if (!irbLinkingEnabled) {
            for (ProposalSpecialReview specialReview : developmentProposal.getPropSpecialReviews()) {
                if (SpecialReviewApprovalType.EXEMPT.equals(specialReview.getApprovalTypeCode())
                        && SpecialReviewType.HUMAN_SUBJECTS.equals(specialReview.getSpecialReviewTypeCode())) {
                    if (specialReview.getComments() == null 
                            || !specialReview.getComments().matches("\\w*E[1-6](\\w*,\\w*E[1-6])*[\\w,]*")) {
                        return FALSE;
                    }
                }
            }
        }
        return TRUE;
    }

    @Override
    public String s2sFederalIdRule(DevelopmentProposal developmentProposal) {
        if (developmentProposal.getS2sOpportunity() != null) {
            String renewalType = getProposalTypeService().getRenewProposalTypeCode();
            if (StringUtils.equals(developmentProposal.getProposalTypeCode(), renewalType)
                    || StringUtils.equals(developmentProposal.getProposalTypeCode(), renewalType)
                    || StringUtils.equals(developmentProposal.getProposalTypeCode(), renewalType)
                    || StringUtils.equals(developmentProposal.getProposalTypeCode(), renewalType)) {
                if (StringUtils.isBlank(developmentProposal.getSponsorProposalNumber())
                        || !developmentProposal.getSponsorProposalNumber().matches("[a-zA-Z]{2}\\d{6}")) {
                    return FALSE;
                }
            }
        }
        return TRUE;
    }

    @Override
    public String s2sLeadershipRule(DevelopmentProposal developmentProposal) {
        if (developmentProposal.getS2sOpportunity() != null) {
            int piNumber = 0;
            for (ProposalPerson person : developmentProposal.getProposalPersons()) {
                if (person.isMultiplePi()) {
                    piNumber++;
                }
            }
            if (piNumber > 0) {
                int neededAttachmentCount = 0;
                for (Narrative narrative : developmentProposal.getNarratives()) {
                    if (StringUtils.equals(narrative.getNarrativeTypeCode(), Constants.PHS_RESEARCHPLAN_MULTIPLEPILEADERSHIPPLAN)
                            || StringUtils.equals(narrative.getNarrativeTypeCode(), Constants.PHS_RESTRAININGPLAN_PILEADERSHIPPLAN_ATTACHMENT)) {
                        neededAttachmentCount++;
                    }
                }
                if (neededAttachmentCount < 1) {
                    return FALSE;
                }                
            }
        }
        return TRUE;
    }

    @Override
    public String s2sModularBudgetRule(DevelopmentProposal developmentProposal) {
        List<String> allowedForms = Arrays.asList(new String[]{"PHS398 Modular Budget V1-1", "PHS398 Modular Budget V1-2"});
        boolean s2sProp = (developmentProposal.getS2sOpportunity() != null);
        Budget finalBudgetVersion = developmentProposal.getFinalBudget();
        if (s2sProp && finalBudgetVersion != null && finalBudgetVersion.getModularBudgetFlag()) {
            int matchingForms = 0;
            for (S2sOppForms form : developmentProposal.getS2sOppForms()) {
                if (form.getInclude() && allowedForms.contains(form.getFormName())) {
                    matchingForms++;
                }
            }
            if (matchingForms == 0) {
                return FALSE;
            } else {
                return TRUE;
            }
        } else {
            return TRUE;
        }
    }

    @Override
    public String specialReviewRule(DevelopmentProposal developmentProposal, String specialReviewTypeCode) {
        for (ProposalSpecialReview review : developmentProposal.getPropSpecialReviews()) {
            if (StringUtils.equals(review.getSpecialReviewTypeCode(), specialReviewTypeCode)) {
                return TRUE;
            }
        }
        return FALSE;
    }

    @Override
    public String sponsor(DevelopmentProposal developmentProposal, String sponsorCode) {
        if (StringUtils.equals(developmentProposal.getSponsorCode(), sponsorCode)) {
            return TRUE;
        } else {
            return FALSE;
        }
    }

    @Override
    public String sponsorGroupRule(DevelopmentProposal developmentProposal, String sponsorGroup) {
        Map<String, Object> values = new HashMap<>();
        values.put("hierarchyName", Constants.SPONSOR_HIERARCHY_ROUTING);
        values.put("sponsorCode", developmentProposal.getSponsorCode());
        List<SponsorHierarchy> hierarchies = (List<SponsorHierarchy>) getBusinessObjectService().findMatching(SponsorHierarchy.class, values);
        if (hierarchies != null && !hierarchies.isEmpty()
                && StringUtils.equals(hierarchies.get(0).getLevel1(), sponsorGroup)) {
            return TRUE;
        }
        values.put("sponsorCode", developmentProposal.getPrimeSponsorCode());
        hierarchies = (List<SponsorHierarchy>) getBusinessObjectService().findMatching(SponsorHierarchy.class, values);
        if (hierarchies != null && !hierarchies.isEmpty()
                && StringUtils.equals(hierarchies.get(0).getLevel1(), sponsorGroup)) {
            return TRUE;
        }
        return FALSE;
    }
    
    /**
     * 
     * This method is used to verify PHS Cover letter narrative(39) is attached, must include s2s cover letter form.
     * See  FN_S2S_398COVER_RULE 
     * @param PHSCoverLetters PHS Cover letter names, comma separated list
     * @param narrativeTypeCode The narrative type code to check.
     * @return 'false' if the passed in narrative type is used, and a PHS cover letter is not attached, otherwise returns 'true'
     */
    @Override
    public String s2s398CoverRule(DevelopmentProposal developmentProposal, String PHSCoverLetters, String narrativeTypeCode) {
        if (developmentProposal.getS2sAppSubmission().size() > 0) {
            boolean foundNarrative = false;
            for (Narrative narrative : developmentProposal.getNarratives()) {
                if (StringUtils.equalsIgnoreCase(narrative.getNarrativeTypeCode(), narrativeTypeCode)) {
                    foundNarrative = true;
                }
            }
            if (foundNarrative) {
                String[] coverLetters = this.buildArrayFromCommaList(PHSCoverLetters);
                boolean foundForm = false;
                for (S2sOppForms form : developmentProposal.getS2sOppForms()) {
                    for (String coverLetter : coverLetters) {
                        if (StringUtils.equals(form.getFormName(), coverLetter)) {
                            foundForm = true;
                        }
                    }
                }
                if (!foundForm) {
                    return FALSE;
                }
            }
        }
        return TRUE;
    }
    
    /**
     * 
     * This method is used to verify no special characters are used.
     * See  fn_narrative_file_name_rule  
     * @return 'false' if there is a special character in the file name, otherwise returns 'true'
     */
    @Override
    public String narrativeFileName(DevelopmentProposal developmentProposal) {
        for (Narrative narrative : developmentProposal.getNarratives()) {
            if (StringUtils.equalsIgnoreCase(narrative.getNarrativeType().getNarrativeTypeGroup(), "P")
                    && StringUtils.equals(FALSE, specialCharacterRule(narrative.getName()))) {
                return FALSE;
            }
            if (StringUtils.equalsIgnoreCase(narrative.getNarrativeTypeCode(), "8")
                    && StringUtils.equals(FALSE, specialCharacterRule(narrative.getModuleTitle()))) {
                return FALSE;
            }
            
        }
        return TRUE;
    }
    
    /**
     * 
     * This method is used to verify that a cost element is used in the specified version of the proposal.
     * See  fn_cost_element_in_version  
     * @return 'false' if the cost element is not in the version of the proposal, otherwise returns 'true'
     */
    @Override
    public String costElementInVersion(DevelopmentProposal developmentProposal, String versionNumber, String costElement) {
        Long versionNumberLong = Long.parseLong(versionNumber);
        for (Budget budget : developmentProposal.getBudgets()) {
            if (budget.getVersionNumber().equals(versionNumberLong)) {
                for (BudgetPeriod period : budget.getBudgetPeriods()) {
                    if (!period.getBudgetLineItems().isEmpty()) {
                        return TRUE;
                    }
                }
            }
        }
        return FALSE;
    }
    
    /**
     * 
     * This method is used to verify that each investigator and key person are certified
     * See  FN_IS_EPS_PROP_INVKEY_CERT  
     * @return 'true' if all the investigators and key person have completed their questionaires, otherwise return 'false'
     */
    @Override
    public String investigatorKeyPersonCertificationRule(DevelopmentProposal developmentProposal) {
        for (ProposalPerson person : developmentProposal.getProposalPersons()) {
            ProposalPersonModuleQuestionnaireBean moduleQuestionnaireBean = 
                new ProposalPersonModuleQuestionnaireBean(developmentProposal, person);
            List<AnswerHeader> answerHeaders = getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleQuestionnaireBean);
            for (AnswerHeader ah : answerHeaders) {
                if (!ah.isCompleted()) {
                    return FALSE;
                }
            }
        }
        return TRUE;
    }

    @Override
    public String sponsorTypeRule(DevelopmentProposal developmentProposal, String sponsorTypeCode) {
        if (StringUtils.equals(developmentProposal.getSponsor().getSponsorTypeCode(), sponsorTypeCode)) {
            return TRUE;
        } else {
            return FALSE;
        }
    }

    @Override
    public String completeNarrativeRule(DevelopmentProposal developmentProposal) {
        for (Narrative narrative : developmentProposal.getNarratives()) {
            if (StringUtils.equals(narrative.getModuleStatusCode(), "I")) {
                return FALSE;
            }
        }
        return TRUE;
    }

    /**
     * This method is to CHECK  PI citizenship type
     * Check if the citizenship type of the Principal Investigator equal to the specified value
     * FN_PI_CITIZENSHIP_TYPE_RULE
     */
    public String investigatorCitizenshipTypeRule(DevelopmentProposal developmentProposal, String citizenshipTypeToCheck) {
        String RETURN_VALUE = FALSE;
        ProposalPerson principalInvestigator = developmentProposal.getPrincipalInvestigator();
        char citizenType = citizenshipTypeToCheck != null ? citizenshipTypeToCheck.charAt(0) : '0';
        Integer citizenshipTypeCode = principalInvestigator.getCitizenshipTypeCode();
        switch(citizenType){
            case 'A' :
                if(citizenshipTypeCode.equals(INT_NON_U_S_CITIZEN_WITH_TEMPORARY_VISA)) {
                    RETURN_VALUE = TRUE;
                }
                break;
            case 'C' :
                if(citizenshipTypeCode.equals(INT_U_S_CITIZEN_OR_NONCITIZEN_NATIONAL)) {
                    RETURN_VALUE = TRUE;
                }
                break;
            case 'N' :
                if(citizenshipTypeCode.equals(INT_PERMANENT_RESIDENT_OF_U_S)) {
                    RETURN_VALUE = TRUE;
                }
                break;
            case 'P' :
                if(citizenshipTypeCode.equals(INT_PERMANENT_RESIDENT_OF_U_S_PENDING)) {
                    RETURN_VALUE = TRUE;
                }
                break;
            default :
                Collection<String> citizenshipTypeParams = getParameterService().getParameterValuesAsString(ProposalDevelopmentDocument.class, 
                        ProposalDevelopmentUtils.PROPOSAL_PI_CITIZENSHIP_TYPE_PARM);
                if(citizenshipTypeParams != null && citizenshipTypeParams.contains(citizenshipTypeToCheck)) {
                    RETURN_VALUE = TRUE;
                }
                break;
        }
        return RETURN_VALUE;
    }

    /**
     * This method is to check if the PI or any multi-PI has PI status
     * FN_PI_APPOINTMENT_TYPE_RULE
     */
    public String piAppointmentTypeRule(DevelopmentProposal developmentProposal) {
        List<ProposalPerson> people = developmentProposal.getProposalPersons();
        List<AppointmentType> appointmentTypes = (List<AppointmentType>)getBusinessObjectService().findAll(AppointmentType.class);
        for (ProposalPerson person : people) {
            if ((person.isInvestigator() && person.isPrincipalInvestigator()) || (person.isMultiplePi())
            		&& person.getPerson() != null && person.getPerson().getExtendedAttributes() != null) {
                List<PersonAppointment> appointments = person.getPerson().getExtendedAttributes().getPersonAppointments();
                for(PersonAppointment personAppointment : appointments) {
                    if(isAppointmentTypeEqualsJobTitle(appointmentTypes, personAppointment.getJobTitle())) {
                        return TRUE;
                    }
                }
            }
        }
        return FALSE;
    }
    
    private boolean isAppointmentTypeEqualsJobTitle(List<AppointmentType> appointmentTypes, String jobTitle) {
        for(AppointmentType appointmentType : appointmentTypes) {
            if(appointmentType.getDescription().equalsIgnoreCase(jobTitle)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * This method is to check campus
     * Check if the lead unit of the Proposal belong to campus
     * FN_PROPOSAL_CAMPUS_RULE
     */
    public String proposalCampusRule(DevelopmentProposal developmentProposal, String a2SCampusCode) {
        for (ProposalPerson person : developmentProposal.getProposalPersons()) {
            for (ProposalPersonUnit unit : person.getUnits()) {
                if(unit.isLeadUnit() && StringUtils.equals(unit.getUnitNumber().substring(1, 3), a2SCampusCode)) {
                        return TRUE;
                }
            }
        }
        return FALSE;
    }
    
    /**
     * This method is to check document in routing
     * Check if the proposal has been approved or rejected by OSP
     * FN_ROUTED_TO_OSP_RULE
     */
    public String routedToOSPRule(DevelopmentProposal developmentProposal) {
        if(developmentProposal.getProposalDocument().getDocumentHeader().getWorkflowDocument().isApproved() ||
                developmentProposal.getProposalDocument().getDocumentHeader().getWorkflowDocument().isDisapproved()) {
            return TRUE;
        }
        return FALSE;
    }
    
    /**
     * This method to check submit user
     * Check if the submit/given user is the PI of the proposal
     */
    public String isUserProposalPI(DevelopmentProposal developmentProposal) {
        Person loggedInUser = globalVariableService.getUserSession().getPerson();
        String principalId = loggedInUser.getPrincipalId();
        return checkProposalPiRule(developmentProposal, principalId);
    }

    /**
     * This method is to check units
     * Check if any proposal unit is below a specified unit in the hierarchy
     * FN_PROPOSAL_UNIT_BELOW
     */
    public String proposalUnitBelow(DevelopmentProposal developmentProposal, String unitNumberToCheck) {
        List<Unit> units = this.getUnitService().getAllSubUnits(unitNumberToCheck);
        if (units != null && units.size() > 0) {
            for (Unit unit : units) {
                for (ProposalPerson person : developmentProposal.getProposalPersons()) {
                    for (ProposalPersonUnit proposalUnit : person.getUnits()) {
                        if (StringUtils.equals(proposalUnit.getUnitNumber(), unit.getUnitNumber())) {
                            return TRUE;
                        }
                    }
                }
            }
        }
        return FALSE;
    }

    /**
     * This method is to check rolodex person
     * Check if the proposal involves a specific rolodex id
     * FN_USES_ROLODEX
     */
    public String usesRolodex(DevelopmentProposal developmentProposal, Integer rolodexId) {
        for (ProposalPerson person : developmentProposal.getProposalPersons()) {
            if(person.getRolodexId() != null && person.getRolodexId().equals(rolodexId)) {
                return TRUE;
            }
        }
        return FALSE;
    }
    
    /**
     * This method is to check s2s competition id
     * FN_COMPETITION_ID_RULE
     */
    public String competitionIdRule(DevelopmentProposal developmentProposal, String competitionId) {
        if(developmentProposal.getS2sOpportunity().getCompetetionId().equals(competitionId)) {
            return TRUE;
        }
        return FALSE;
    }
    
    /**
     * This method is to check if proposal Animal or Human Special review approval date 
     * is in the future
     * FN_SPECIAL_REV_DATE_RULE
     */
    public String specialReviewDateRule(DevelopmentProposal developmentProposal) {
        Date currentDate = getDateTimeService().getCurrentSqlDateMidnight();
        for (ProposalSpecialReview proposalSpecialReview : developmentProposal.getPropSpecialReviews()) {
            if(proposalSpecialReview.getSpecialReviewTypeCode().equals(SpecialReviewType.HUMAN_SUBJECTS) || 
                    proposalSpecialReview.getSpecialReviewTypeCode().equals(SpecialReviewType.ANIMAL_USAGE)) {
                if(proposalSpecialReview.getApplicationDate().after(currentDate)) {
                    return TRUE;
                }
            }
        }
        return FALSE;
    }

    /**
     * This method is to check if the proposal's deadline date is before a specified date.
     * @param developmentProposal
     * FN_DEADLINE_DATE
     */
    public String deadlineDateRule(DevelopmentProposal developmentProposal, String deadlineDate) {
        try {
            Date checkDeadLineDate = getDateTimeService().convertToSqlDate(deadlineDate);
            if(developmentProposal.getDeadlineDate().before(checkDeadLineDate)) {
                return TRUE;
            }
        }catch(Exception ex) {
            LOG.error(ex.getMessage());
        }
        return FALSE;
    }

    /**
     * This method is to check if the proposal is being routed for the first time.
     * FN_ROUTING_SEQ_RULE
     */
    public String routingSequenceRule(DevelopmentProposal developmentProposal) {
    	List<ActionRequest> actionRequests = developmentProposal.getProposalDocument().getDocumentHeader().getWorkflowDocument().getDocumentDetail().getActionRequests();
        return actionRequests.stream()
		 .filter(actionRequest -> actionRequest.getActionTaken() != null)
		 .anyMatch(actionRequest -> actionRequest.getActionTaken().getActionTaken().equals(ActionType.RETURN_TO_PREVIOUS)) ? FALSE : TRUE;
    }
    
    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }
    
    public QuestionnaireAnswerService getQuestionnaireAnswerService() {
		return questionnaireAnswerService;
	}

	public void setQuestionnaireAnswerService(
			QuestionnaireAnswerService questionnaireAnswerService) {
		this.questionnaireAnswerService = questionnaireAnswerService;
	}

    public UnitService getUnitService() {
        return unitService;
    }

    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

	public ProposalTypeService getProposalTypeService() {
		return proposalTypeService;
	}

	public void setProposalTypeService(ProposalTypeService proposalTypeService) {
		this.proposalTypeService = proposalTypeService;
	}

    
    
}
