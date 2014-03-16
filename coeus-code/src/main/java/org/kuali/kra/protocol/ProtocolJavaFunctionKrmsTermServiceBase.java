/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.protocol;

import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.kra.krms.service.impl.KcKrmsJavaFunctionTermServiceBase;
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

    public KcPersonService getKcPersonService() {
        return kcPersonService;
    }
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }
}
