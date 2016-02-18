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
package org.kuali.kra.protocol;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.impl.krms.KcKrmsJavaFunctionTermServiceBase;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.ProtocolActionTypeBase;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewModuleBase;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewalBase;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolModuleBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionTypeBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentProtocolBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;
import org.kuali.kra.protocol.protocol.location.ProtocolLocationBase;
import org.kuali.kra.protocol.protocol.research.ProtocolResearchAreaBase;

import java.util.List;

public abstract class ProtocolJavaFunctionKrmsTermServiceBase extends KcKrmsJavaFunctionTermServiceBase implements ProtocolJavaFunctionKrmsTermService {

    private static final int NON_FACULTY = 2;
    private KcPersonService kcPersonService;
    private UnitService unitService;

    public abstract String getRenewalActionTypeCode();
    public abstract String getProtocolPersonnelModuleTypeCode();
    public abstract String getProtocolOrganizationModuleTypeCode();
    public abstract String getNotifySubmissionTypeCode();

    @Override
    public Boolean allProtocols() {
        return Boolean.TRUE;
    }

    @Override
    public Boolean isProtocolAmendment(ProtocolBase protocol) {
        return protocol.isAmendment();
    }

    @Override
    public Boolean isProtocolRenewal(ProtocolBase  protocol) {
        boolean result = false;
        if(protocol.isRenewal()){
            List<ProtocolActionBase> actions = protocol.getProtocolActions();
            for (ProtocolActionBase protocolActionBase : actions) {
                ProtocolActionTypeBase protocolActionType = protocolActionBase.getProtocolActionType();
                if(protocolActionType.getProtocolActionTypeCode().equals(getRenewalActionTypeCode())){
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public Boolean isProtocolLeadUnitBelow(ProtocolBase protocol,String unitNumber) {
        boolean result = false;
        String leadUnit = protocol.getLeadUnitNumber();
        List<Unit> unitHierachy = getUnitService().getUnitHierarchyForUnit(unitNumber);
        for (Unit unit : unitHierachy) {
            if(unit.getUnitNumber().equals(leadUnit)){
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Boolean isPINotFaculty(ProtocolBase protocol) {
        return new Integer(NON_FACULTY).equals(protocol.getPrincipalInvestigator().getAffiliationTypeCode());
    }

    @Override
    public Boolean isSpecialReviewExists(ProtocolBase protocol) {
        return !protocol.getSpecialReviews().isEmpty();
    }

    @Override
    public Boolean hasProtocolContainsOrganization(ProtocolBase protocol, String organizationId) {
        boolean result = false;
        List<ProtocolLocationBase> locations = protocol.getProtocolLocations();
        for (ProtocolLocationBase protocolLocation : locations) {
            if(organizationId!=null && organizationId.equals(protocolLocation.getOrganizationId())){
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Boolean hasAllPersonsCompletedTraining(ProtocolBase protocol) {
        //Cant find appropriate coeus implementation
        throw new RuntimeException("Not implemented");
        
    }

    @Override
    public Boolean hasFundingSourceContainsSponsor(ProtocolBase protocol, String sponsor) {
        boolean result = false;
        List<ProtocolFundingSourceBase> fundingSources = protocol.getProtocolFundingSources();
        for (ProtocolFundingSourceBase protocolFundingSource : fundingSources) {
            if(protocolFundingSource.getFundingSourceTypeCode().equals("1") && protocolFundingSource.getFundingSourceNumber().equals(sponsor)){
                result = true;
            }
            
        }
        return result;
    }

    @Override
    public Boolean hasFundingSourceContainsUnit(ProtocolBase protocol, String unitNumber) {
        
        boolean result = false;
        List<ProtocolFundingSourceBase> fundingSources = protocol.getProtocolFundingSources();
        for (ProtocolFundingSourceBase protocolFundingSource : fundingSources) {
            if(protocolFundingSource.getFundingSourceTypeCode().equals("2") && protocolFundingSource.getFundingSourceNumber().equals(unitNumber)){
                result = true;
            }
            
        }
        return result;
    }

    @Override
    public Boolean hasProtocolContainsAreaOfResearch(ProtocolBase protocol, String areaOfResearchCode) {
        boolean result = false;
        List<ProtocolResearchAreaBase> researchAreas = protocol.getProtocolResearchAreas();
        for (ProtocolResearchAreaBase protocolResearchArea : researchAreas) {
            if(protocolResearchArea.getResearchAreaCode().equals(areaOfResearchCode)){
                result = true;
                break;
            }
        }
        return result;

    }
    protected abstract ProtocolBase getActiveProtocol(String protocolNumber);

    @Override
    public Boolean isPiChangedInAmendmentOrRenewal(ProtocolBase protocol) {
        boolean result = false;
        if(protocol.isAmendment() || protocol.isRenewal()){
            List<ProtocolAmendRenewalBase> amendmentRenewalModules = protocol.getProtocolAmendRenewals();
            for (ProtocolAmendRenewalBase protocolAmendRenewalBase : amendmentRenewalModules) {
                List<ProtocolAmendRenewModuleBase> modules = protocolAmendRenewalBase.getModules();
                for (ProtocolAmendRenewModuleBase protocolAmendRenewModule : modules) {
                    ProtocolModuleBase protocolModule = protocolAmendRenewModule.getProtocolModule();
                    if(protocolAmendRenewModule.getProtocolModuleTypeCode().equals(getProtocolPersonnelModuleTypeCode())){
                        String originalProtocolNumber = protocol.getAmendedProtocolNumber();
                        ProtocolBase originalProtocol = getActiveProtocol(originalProtocolNumber);
                        if(!protocol.getPrincipalInvestigator().getPersonId().equals(
                                    originalProtocol.getPrincipalInvestigator().getPersonId())){
                            result = true;
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Boolean isOrganizationChangedInAmendmentOrRenewal(ProtocolBase protocol) {
        boolean result = false;
        if(protocol.isAmendment() || protocol.isRenewal()){
            String originalProtocolNumber = protocol.getAmendedProtocolNumber();
            ProtocolBase originalProtocol = getActiveProtocol(originalProtocolNumber);
            List<ProtocolAmendRenewalBase> amendmentRenewalModules = protocol.getProtocolAmendRenewals();
            for (ProtocolAmendRenewalBase protocolAmendRenewalBase : amendmentRenewalModules) {
                List<ProtocolAmendRenewModuleBase> modules = protocolAmendRenewalBase.getModules();
                for (ProtocolAmendRenewModuleBase protocolAmendRenewModule : modules) {
                    ProtocolModuleBase protocolModule = protocolAmendRenewModule.getProtocolModule();
                    if(protocolAmendRenewModule.getProtocolModuleTypeCode().equals(getProtocolOrganizationModuleTypeCode())){
                        List<ProtocolLocationBase> locations = protocol.getProtocolLocations();
                        if(hasLocaltionsChanged(locations,originalProtocol.getProtocolLocations())){
                            result = true;
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    private boolean hasLocaltionsChanged(List<ProtocolLocationBase> amendedLocations, List<ProtocolLocationBase> originalLocations) {
        boolean result = false; 
        if(amendedLocations.size()==originalLocations.size()){
            for (ProtocolLocationBase amendedLocation : amendedLocations) {
                boolean exists = false;
                String amendedOrgId = amendedLocation.getOrganizationId();
                String amdededOrgTypeCode = amendedLocation.getProtocolOrganizationTypeCode();
                for (ProtocolLocationBase originalLocation : originalLocations) {
                    if(originalLocation.getOrganizationId().equals(amendedOrgId) && 
                            originalLocation.getProtocolOrganizationTypeCode().equals(amdededOrgTypeCode)){
                        exists = true;
                        break;
                    }
                }
                if(!exists){
                    result = true;
                    break;
                }
            }
        }else{
            result = true;
        }
        return result;
    }
    @Override
    public Boolean isSubmitUserProtocolPi(ProtocolBase protocol,String submitUserId) {
        boolean result = false; 
        ProtocolPersonBase pi = protocol.getPrincipalInvestigator();
        if(pi.getUserName()!=null && pi.getUserName().equals(submitUserId)){
            result = true;
        }
        return result;
    }

    @Override
    public Boolean isLeadUnitProtocolCampus(ProtocolBase protocol, String campusCode) {
        String leadUnit = protocol.getLeadUnitNumber();
        boolean result = false;
        if(leadUnit!=null && leadUnit.length()>3 && leadUnit.indexOf(campusCode)!=-1){
            result = true;
        }
        return result;
    }

    @Override
    public Boolean isProtocolRenewalWithAmendment(ProtocolBase protocol) {
        boolean result = !protocol.isRenewalWithoutAmendment();
        return result;
    }
    @Override
    public Boolean hasProtocolContainsDocumentType(ProtocolBase protocol, String documentTypeCode) {
        boolean result = false;
        List<ProtocolAttachmentProtocolBase> attachments = protocol.getAttachmentProtocols();
        for (ProtocolAttachmentProtocolBase protocolAttachmentProtocol : attachments) {
            if(protocolAttachmentProtocol.getTypeCode().equals(documentTypeCode)){
                result = true;
                break;
            }
        }
        return result;
    }


    @Override
    public Boolean isProtocolInSubmission(ProtocolBase protocol, String submissionTypeCode) {
        ProtocolSubmissionBase submission = protocol.getProtocolSubmission();
        boolean result = submission.getSubmissionTypeCode().equals(submissionTypeCode);
        return result;
    }

    @Override
    public Boolean hasProtocolContainsNotifySubmissionQualifierType(ProtocolBase protocol,Integer submissionNumber,String submissionQualifierTypeCode) {
        boolean result = false;
        List<ProtocolSubmissionBase> submissions = protocol.getProtocolSubmissions();
        for (ProtocolSubmissionBase protocolSubmission : submissions) {
            ProtocolSubmissionTypeBase submissionType = protocolSubmission.getProtocolSubmissionType();
            if(submissionType.getSubmissionTypeCode().equals(getNotifySubmissionTypeCode()) && 
                            protocolSubmission.getSubmissionTypeQualifierCode().equals(submissionQualifierTypeCode)){
                result=true;
            }
        }
        return result;
    }

    
    @Override
    public Boolean hasSubmissionType(ProtocolBase protocol, Integer submissionNumber, String submissionTypeCode) {
        boolean result = false;
        List<ProtocolSubmissionBase> submissions = protocol.getProtocolSubmissions();
        for (ProtocolSubmissionBase protocolSubmission : submissions) {
            ProtocolSubmissionTypeBase submissionType = protocolSubmission.getProtocolSubmissionType();
            if(submissionType!=null && submissionType.getSubmissionTypeCode().equals(submissionTypeCode)){
                result=true;
            }
        }
        return result;
    }

    public Boolean hasProtocolContainsAmendRenewModule(ProtocolBase protocol, String moduleName) {
        if (protocol.isAmendment() || protocol.isRenewalWithAmendment()) {
            return  protocol.getProtocolAmendRenewal().getModules().stream().anyMatch(module ->
                    StringUtils.equals(module.getProtocolModuleTypeCode(), moduleName) || doesProtocolModuleDescriptionMatch(moduleName, module));
        }
        return false;
    }

    protected boolean doesProtocolModuleDescriptionMatch(String moduleName, ProtocolAmendRenewModuleBase module) {
        if (module.getProtocolModule() == null) {
            module.refreshReferenceObject("protocolModule");
        }
        return module.getProtocolModule() != null && StringUtils.equals(module.getProtocolModule().getDescription(), moduleName);
    }

    public Boolean hasProtocolContainsSponsorType(ProtocolBase protocol, String sponsorTypeCode) {
        return protocol.getProtocolFundingSources().stream().anyMatch(fundingSource ->
                                                                            isFundingSourceSponsor(fundingSource) &&
                                                                                    isSponsorTypeMatch(sponsorTypeCode, fundingSource)
                                                                    );
    }

    protected boolean isSponsorTypeMatch(String sponsorTypeCode, ProtocolFundingSourceBase fundingSource) {
        Sponsor sponsor = getSponsorByFundingSourceNumber(fundingSource);
        return (sponsor != null && StringUtils.equals(sponsor.getSponsorTypeCode(), sponsorTypeCode)) ||
                (sponsor != null && sponsor.getSponsorType() != null && StringUtils.equals(sponsor.getSponsorType().getDescription(), sponsorTypeCode));
    }

    protected boolean isFundingSourceSponsor(ProtocolFundingSourceBase fundingSource) {
        return StringUtils.equals(fundingSource.getFundingSourceTypeCode(), FundingSourceType.SPONSOR);
    }

    protected Sponsor getSponsorByFundingSourceNumber(ProtocolFundingSourceBase fundingSource) {
        return getBusinessObjectService().findBySinglePrimaryKey(Sponsor.class, fundingSource.getFundingSourceNumber());
    }

    public Boolean hasBaseProtocolHasLastApprovalDate(ProtocolBase protocol) {
        ProtocolBase parentProtocol = protocol;
        if (!protocol.isNew()) {
            ProtocolBase potentialParentProtocol = getActiveProtocol(protocol.getAmendedProtocolNumber());
            if (potentialParentProtocol != null) {
                parentProtocol = potentialParentProtocol;
            }
        }
        return parentProtocol.getLastApprovalDate() != null;
    }

    public KcPersonService getKcPersonService() {
        return kcPersonService;
    }
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public UnitService getUnitService() {
        return unitService;
    }

    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }
}
