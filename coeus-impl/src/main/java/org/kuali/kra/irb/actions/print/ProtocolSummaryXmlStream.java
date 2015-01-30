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
package org.kuali.kra.irb.actions.print;

import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.sponsor.SponsorService;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocValue;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.permissions.impl.web.bean.AssignedRole;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewModule;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewal;
import org.kuali.kra.irb.actions.notification.ProtocolNotificationTemplateAuthorizationService;
import org.kuali.kra.irb.actions.risklevel.ProtocolRiskLevel;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentProtocol;
import org.kuali.kra.irb.noteattachment.ProtocolNotepad;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.personnel.ProtocolPersonRole;
import org.kuali.kra.irb.personnel.ProtocolUnit;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSource;
import org.kuali.kra.irb.protocol.location.ProtocolLocation;
import org.kuali.kra.irb.protocol.participant.ProtocolParticipant;
import org.kuali.kra.irb.protocol.reference.ProtocolReference;
import org.kuali.kra.irb.protocol.research.ProtocolResearchArea;
import org.kuali.kra.irb.specialreview.ProtocolSpecialReview;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.kra.protocol.actions.print.ProtocolSummaryPrintOptions;
import org.kuali.kra.protocol.actions.print.ProtocolSummaryXmlStreamBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.KRADPropertyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.w3.x2001.protocolSummarySchema.*;
import org.w3.x2001.protocolSummarySchema.ProtocolSummaryDocument.ProtocolSummary;
import org.w3.x2001.protocolSummarySchema.ProtocolSummaryDocument.ProtocolSummary.PrintRequirement;

import java.sql.Date;
import java.util.*;


/**
 * This class is to generate Protocol Summary Xml file
 */
public class ProtocolSummaryXmlStream extends ProtocolSummaryXmlStreamBase {
    private static final String OTHER = "9";
    private static final String SCHOOL_NAME = "SCHOOL_NAME";
    private static final String SCHOOL_ACRONYM = "SCHOOL_ACRONYM";
    
    private SponsorService sponsorService;
    private UnitService unitService;
    private BusinessObjectService businessObjectService;
    private InstitutionalProposalService institutionalProposalService;
    private AwardService awardService;
    
    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;


    @Override
    public Map<String, XmlObject> generateXmlStream(KcPersistableBusinessObjectBase printableBusinessObject,
            Map<String, Object> reportParameters) {
        Protocol protocol = (Protocol) printableBusinessObject;
        ProtocolSummaryDocument protocolSummaryDocument = ProtocolSummaryDocument.Factory.newInstance();
        protocolSummaryDocument.setProtocolSummary(getProtocolSummary(protocol, reportParameters));
        Map<String, XmlObject> map = new HashMap<String,XmlObject>();
        map.put("PrtocolSummary", protocolSummaryDocument);
        
        return map; 
    }
    
    private String getOptionString(boolean printOption){
        return printOption?"1":"0";
    }
    public ProtocolSummary getProtocolSummary(KcPersistableBusinessObjectBase printableBusinessObject,
            Map<String, Object> htData) {
        Protocol protocol = (Protocol) printableBusinessObject;
        ProtocolSummary protocolSummary = ProtocolSummary.Factory.newInstance();
        PrintRequirement  printRequirementType = protocolSummary.addNewPrintRequirement();
        protocolSummary.setPrintRequirement(printRequirementType);
        if(htData!=null){
            ProtocolSummaryPrintOptions summaryOptions = (ProtocolSummaryPrintOptions)htData.get(ProtocolSummaryPrintOptions.class);
            printRequirementType.setActionsRequired(getOptionString(summaryOptions.isActions()));
            printRequirementType.setAmendRenewModulesRequired(getOptionString(summaryOptions.isAmmendmentRenewalSummary()));
            printRequirementType.setAmendRenewSRequired(getOptionString(summaryOptions.isAmendmentRenewalHistory()));
            printRequirementType.setDocumentsRequired(getOptionString(summaryOptions.isDocuments()));
            printRequirementType.setResearchAreasRequired(getOptionString(summaryOptions.isAreaOfResearch()) );
            printRequirementType.setCorrespondentsRequired(getOptionString(summaryOptions.isCorrespondents()));
            printRequirementType.setFundingSourcesRequired(getOptionString(summaryOptions.isFundingSource()));
            printRequirementType.setInvestigatorsRequired(getOptionString(summaryOptions.isInvestigator()) );
            printRequirementType.setNotesRequired(getOptionString(summaryOptions.isNotes()));
            printRequirementType.setOrganizationRequired(getOptionString(summaryOptions.isOrganizaition()));
            printRequirementType.setOtherDataRequired(getOptionString(summaryOptions.isOtherData()));
            printRequirementType.setProtocolDetailsRequired(getOptionString(summaryOptions.isProtocolDetails()));
            printRequirementType.setReferencesRequired(getOptionString(summaryOptions.isReferences()));
            printRequirementType.setRiskLevelsRequired(getOptionString(summaryOptions.isRiskLevel()));
            printRequirementType.setUserRolesRequired(getOptionString(summaryOptions.isRoles()));
            printRequirementType.setSpecialReviewRequired(getOptionString(summaryOptions.isSpecialReview()));
            printRequirementType.setKeyPersonsRequired(getOptionString(summaryOptions.isStudyPersonnels()));
            printRequirementType.setSubjectsRequired(getOptionString(summaryOptions.isSubjects()));
        }
        printRequirementType.setCurrentDate(getDateTimeService().getCurrentCalendar());
        setProtocolDetails(protocolSummary,protocol);
        setProtocolPersons(protocolSummary,protocol);
        
        setProtocolLocations(protocolSummary,protocol);
        setProtocolResearchAreas(protocolSummary,protocol);
        setProtocolFundingResources(protocolSummary,protocol);
        
        setProtocolActions(protocolSummary,protocol);
        
        setProtocolVulnerableSubjects(protocolSummary,protocol);
        setProtocolSpecialReviewes(protocolSummary,protocol);
        setProtocolRiskLevels(protocolSummary,protocol);
        setProtocolNotes(protocolSummary,protocol);
        
        setProtocolAmendmentRenewals(protocolSummary,protocol);
        setProtocolOtherData(protocolSummary,protocol);
        setProtocolReferences(protocolSummary,protocol);
        setProtocolUserRoles(protocolSummary,protocol);
        setProtocolDocuments(protocolSummary,protocol);
        
        setSchoolInfo(protocolSummary,protocol);
        return protocolSummary;
    }
    private void setSchoolInfo(ProtocolSummary protocolSummary, Protocol protoInfoBean) {
          String schoolName = getProposalParameterValue(SCHOOL_NAME);
          String schoolAcronym = getProposalParameterValue(SCHOOL_ACRONYM);
          SchoolInfoType schoolInfoType = protocolSummary.addNewSchoolInfo();
          schoolInfoType.setSchoolName(schoolName);
          schoolInfoType.setAcronym(schoolAcronym);
          protocolSummary.setSchoolInfo(schoolInfoType);
    }
    private String getProposalParameterValue(String param) {
        ParameterService parameterService = KcServiceLocator.getService(ParameterService.class);
        return parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, param);
    }

    private void setProtocolDocuments(ProtocolSummary protocolSummary, Protocol protocol) {
        ProtocolDocumentsType protocolDocumentsType = protocolSummary.addNewProtocolDocuments();
        protocolDocumentsType.setProtocolNumber(protocol.getProtocolNumber());
        protocolDocumentsType.setSequenceNumber(protocol.getSequenceNumber());
        List<ProtocolAttachmentProtocol> protocolAttachments = (List)protocol.getActiveAttachmentProtocols();
        for (ProtocolAttachmentProtocol protocolAttachmentProtocol : protocolAttachments) {
            if(protocolAttachmentProtocol.getTypeCode().equals(OTHER)){
                ProtocolOtherDocumentsType protocolOtherDocumentsType = protocolDocumentsType.addNewProtocolOtherDocuments();
                protocolOtherDocumentsType.setDescription(protocolAttachmentProtocol.getDescription());
                protocolOtherDocumentsType.setDocumentId(protocolAttachmentProtocol.getDocumentId());
                protocolOtherDocumentsType.setDocumentTypeCode(Integer.parseInt(protocolAttachmentProtocol.getTypeCode()));
                protocolOtherDocumentsType.setDocumentTypeDesc(protocolAttachmentProtocol.getType().getDescription());
                protocolOtherDocumentsType.setFileName(protocolAttachmentProtocol.getFile().getName());
                if (protocolAttachmentProtocol.getUpdateTimestamp() !=null) {
                    protocolOtherDocumentsType.setUpdateTimestamp(getDateTimeService().getCalendar(protocolAttachmentProtocol.getUpdateTimestamp()));
                }
                protocolOtherDocumentsType.setUpdateUser(protocolAttachmentProtocol.getUpdateUser());

            }else{
                ProtocolDocumentType protocolDocumentType = protocolDocumentsType.addNewProtocolDocument();
                protocolDocumentType.setDescription(protocolAttachmentProtocol.getDescription());
                protocolDocumentType.setDocumentTypeCode(Integer.parseInt(protocolAttachmentProtocol.getTypeCode()));
                protocolDocumentType.setDocumentTypeGroup(protocolAttachmentProtocol.getType().getDescription());
                if (protocolAttachmentProtocol.getUpdateTimestamp() !=null) {
                    protocolDocumentType.setUpdateTimestamp(getDateTimeService().getCalendar(protocolAttachmentProtocol.getUpdateTimestamp()));
                }
                protocolDocumentType.setUpdateUser(protocolAttachmentProtocol.getUpdateUser());
            }
        }
    }
    private void setProtocolUserRoles(ProtocolSummary protocolSummary, Protocol protocol) {
        ProtocolPrintPermissionUtils protocolPrintPermissionUtils = new ProtocolPrintPermissionUtils(RoleConstants.PROTOCOL_ROLE_TYPE);
        protocolPrintPermissionUtils.setProtocol(protocol);
        List<AssignedRole> assignedRoles = protocolPrintPermissionUtils.getAssignedRoles();
        for (AssignedRole userRolesInfoBean : assignedRoles) {
            ProtocolRolesType protocolRolesType = protocolSummary.addNewProtocolUserRoles();
            protocolRolesType.setRoleName(userRolesInfoBean.getRole().getName());
            List<String> vecUsers = userRolesInfoBean.getUserNames();
            for (String userName : vecUsers) {
                ProtocolUserRolesType protocolUserRolesType = protocolRolesType.addNewUserRoles();
                protocolUserRolesType.setUserName(userName);
                protocolUserRolesType.setPersonName(userName);
            }
        }
    }
    private void setProtocolReferences(ProtocolSummary protocolSummary, Protocol protocol) {
        List<ProtocolReference> protocolReferences = (List) protocol.getProtocolReferences();
        for (ProtocolReference protocolReferencesBean : protocolReferences) {
            protocolReferencesBean.refreshNonUpdateableReferences();
            ProtocolReferencesType protocolReferencesType = protocolSummary.addNewProtocolReferences();
            protocolReferencesType.setApplicationDate(convertDateToCalendar(protocolReferencesBean.getApplicationDate()));
            protocolReferencesType.setApprovalDate(convertDateToCalendar(protocolReferencesBean.getApprovalDate()));
            protocolReferencesType.setComments(protocolReferencesBean.getComments());
            protocolReferencesType.setProtocolNumber(protocolReferencesBean.getProtocolNumber());
            protocolReferencesType.setProtocolReferenceNumber(protocolReferencesBean.getProtocolReferenceNumber());
            if(protocolReferencesBean.getProtocolReferenceType()!=null){
                protocolReferencesType.setProtocolReferenceTypeCode(protocolReferencesBean.getProtocolReferenceTypeCode());
                protocolReferencesType.setProtocolReferenceTypeDesc(protocolReferencesBean.getProtocolReferenceType().getDescription());
            }
            protocolReferencesType.setReferenceKey(protocolReferencesBean.getReferenceKey());
            protocolReferencesType.setSequenceNumber(protocolReferencesBean.getSequenceNumber());
        }
    }
    
    
    public void prepareView(ProtocolDocument protocolDocument) {
       
        Map<String, CustomAttributeDocument> customAttributeDocuments = protocolDocument.getCustomAttributeDocuments();
        String documentNumber = protocolDocument.getDocumentNumber();
        for(Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry:customAttributeDocuments.entrySet()) {
            CustomAttributeDocument customAttributeDocument = customAttributeDocumentEntry.getValue();
            Map<String, Object> primaryKeys = new HashMap<String, Object>();
            primaryKeys.put(KRADPropertyConstants.DOCUMENT_NUMBER, documentNumber);
            primaryKeys.put(Constants.CUSTOM_ATTRIBUTE_ID, customAttributeDocument.getId());

            CustomAttributeDocValue customAttributeDocValue = (CustomAttributeDocValue) KcServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(CustomAttributeDocValue.class, primaryKeys);
            if (customAttributeDocValue != null) {
                customAttributeDocument.getCustomAttribute().setValue(customAttributeDocValue.getValue());
            }

        }
    }

    
    private void setProtocolOtherData(ProtocolSummary protocolSummary, Protocol protocol) {
        ProtocolDocument protocolDocument = (ProtocolDocument) protocol.getProtocolDocument();
        prepareView(protocolDocument);
        Map<String,CustomAttributeDocument> customAttributes = protocolDocument.getCustomAttributeDocuments();
        Iterator<String> customAttributesKeyIt = customAttributes.keySet().iterator();
        while (customAttributesKeyIt.hasNext()) {
            String attributeKey = (String) customAttributesKeyIt.next();
            CustomAttributeDocument attributeDocument = customAttributes.get(attributeKey);
            String value = (String)attributeDocument.getCustomAttribute().getValue();
            ProtocolOtherDataType protocolOtherDataType = protocolSummary.addNewProtocolOthersData();
            protocolOtherDataType.setColumnName(attributeKey);
            protocolOtherDataType.setColumnValue(value);
            protocolOtherDataType.setProtocolNumber(protocol.getProtocolNumber());
            protocolOtherDataType.setSequenceNumber(protocol.getSequenceNumber());
            protocolOtherDataType.setUpdateUser(customAttributes.get(attributeKey).getUpdateUser());
        }
        
    }
    private void setProtocolAmendmentRenewals(ProtocolSummary protocolSummary, Protocol protocol) {
        String versionNo = "",moduleType = "";
        String amendType = "Amendment";
        String renewalType = "Renewal";
        String protocolNo = null;
        List<ProtocolAmendRenewal> protocolAmendmentRenewals = (List) protocol.getProtocolAmendRenewals();
        for (ProtocolAmendRenewal protocolAmendRenewalBean : protocolAmendmentRenewals) {
            ProtoAmendRenewalType protoAmendRenewalType = protocolSummary.addNewProtocolAmenRenewal();
            protoAmendRenewalType.setDateCreated(convertDateToCalendar(protocolAmendRenewalBean.getDateCreated()));
            protoAmendRenewalType.setProtoAmendRenNumber(protocolAmendRenewalBean.getProtoAmendRenNumber());
            protoAmendRenewalType.setProtocolNumber(protocolAmendRenewalBean.getProtocolNumber());
            protoAmendRenewalType.setSequenceNumber(protocolAmendRenewalBean.getSequenceNumber());
            protoAmendRenewalType.setSummary(protocolAmendRenewalBean.getSummary());
            protoAmendRenewalType.setUpdateUser(protocolAmendRenewalBean.getUpdateUser());
            protocolNo=protocolAmendRenewalBean.getProtoAmendRenNumber();
            if(protocolNo!=null && protocolNo.length() >= 14 ) {
                versionNo = protocolNo.substring(11);
                if( protocolNo.indexOf( 'A' ) != -1 ) {
                    moduleType = amendType;
                }else if( protocolNo.indexOf( 'R' ) != -1 ) {
                    moduleType = renewalType;
                }
            }
            if(moduleType!=null && moduleType.length()>0){
                protoAmendRenewalType.setType(moduleType);
            }
            if(versionNo!=null && versionNo.length()>0){
                protoAmendRenewalType.setVersion(versionNo);
            }
            if(protocolAmendRenewalBean.getProtocol()!=null && protocolAmendRenewalBean.getProtocol().getProtocolStatus()!=null){
                protoAmendRenewalType.setProtocolStatusDesc(protocolAmendRenewalBean.getProtocol().getProtocolStatus().getDescription());
                protoAmendRenewalType.setProtocolStatusCode(Integer.parseInt(protocolAmendRenewalBean.getProtocol().getProtocolStatusCode()));
            }
            List<ProtocolAmendRenewModule> vecModuleData  = (List) protocolAmendRenewalBean.getModules();
            for (ProtocolAmendRenewModule amendRenewModuleBean : vecModuleData) {
                ProtocolModulesType protocolModulesType =  protoAmendRenewalType.addNewProtocolModules();
                if(amendRenewModuleBean.getProtocolModule()!=null){
                    protocolModulesType.setProtocolModuleCode(amendRenewModuleBean.getProtocolModule().getProtocolModuleCode());
                    protocolModulesType.setDescription(amendRenewModuleBean.getProtocolModule().getDescription());
                }
                protocolModulesType.setUpdateUser(amendRenewModuleBean.getUpdateUser());
            }
        }
    }
    private void setProtocolNotes(ProtocolSummary protocolSummary, Protocol protocol) {
        List<ProtocolNotepad> protocolNotes = (List) protocol.getNotepads();
        boolean isProtocolPerson = KcServiceLocator.getService(ProtocolActionService.class).isProtocolPersonnel(protocol);
        boolean hasPermission = KcServiceLocator.getService(ProtocolNotificationTemplateAuthorizationService.class).hasPermission(
                PermissionConstants.VIEW_RESTRICTED_NOTES);
        for (ProtocolNotepad protocolNotepad : protocolNotes) {
            boolean restrictedView = protocolNotepad.getRestrictedView();
            if (!(isProtocolPerson) && (hasPermission) || (!restrictedView)) {
                ProtocolNotesType protocolNotesType = protocolSummary.addNewProtocolNotes();
                protocolNotesType.setComments(protocolNotepad.getComments());
                protocolNotesType.setEntryNumber(protocolNotepad.getEntryNumber());
                protocolNotesType.setProtocolNumber(protocolNotepad.getProtocolNumber());
                protocolNotesType.setSequenceNumber(protocolNotepad.getSequenceNumber());
                protocolNotesType.setUpdateUser(protocolNotepad.getUpdateUser());
                if (protocolNotepad.getUpdateTimestamp()!=null) {
                    protocolNotesType.setUpdateTimestamp(getDateTimeService().getCalendar(protocolNotepad.getUpdateTimestamp()));
                }
            }
        }
        
    }
    private Calendar convertDateToCalendar(Date date){
        return date==null?null:getDateTimeService().getCalendar(date);
    }
    private void setProtocolRiskLevels(ProtocolSummary protocolSummary, Protocol protocol) {
        List<ProtocolRiskLevel> protocolRiskLevels = protocol.getProtocolRiskLevels();
        for (ProtocolRiskLevel protocolRiskLevelBean : protocolRiskLevels) {
            protocolRiskLevelBean.refreshNonUpdateableReferences();
            ProtocolRiskLevelsType protocolRiskLevelsType = protocolSummary.addNewProtocolRiskLevels();
            protocolRiskLevelsType.setComments(protocolRiskLevelBean.getComments());
            protocolRiskLevelsType.setDateAssigned(convertDateToCalendar(protocolRiskLevelBean.getDateAssigned()));
            protocolRiskLevelsType.setDateUpdated(convertDateToCalendar(protocolRiskLevelBean.getDateInactivated()));
            protocolRiskLevelsType.setProtocolNumber(protocolRiskLevelBean.getProtocolNumber());
            if(protocolRiskLevelBean.getRiskLevelCode()!=null){
                protocolRiskLevelsType.setRiskLevelCode(Integer.parseInt(protocolRiskLevelBean.getRiskLevelCode()));
                protocolRiskLevelsType.setRiskLevelDesc(protocolRiskLevelBean.getRiskLevel().getDescription());
            }
            protocolRiskLevelsType.setSequenceNumber(protocolRiskLevelBean.getSequenceNumber());
            protocolRiskLevelsType.setStatus(protocolRiskLevelBean.getStatus());
            protocolRiskLevelsType.setUpdateUser(protocolRiskLevelBean.getUpdateUser());
        }
    }
    private void setProtocolSpecialReviewes(ProtocolSummary protocolSummary, Protocol protocol) {
        List<ProtocolSpecialReview> protocolSpecialReviews = (List) protocol.getSpecialReviews();
        for (ProtocolSpecialReview specialReview : protocolSpecialReviews) {
            specialReview.refreshNonUpdateableReferences();
            ProtocolSpecialReviewType protocolSpecialReviewType = protocolSummary.addNewProtocolSpecialReview();
            if (specialReview.getApplicationDate()!=null) {
                protocolSpecialReviewType.setApplicationDate(getDateTimeService().getCalendar(specialReview.getApplicationDate()));
            }
            if (specialReview.getApprovalDate()!=null) {
                protocolSpecialReviewType.setApprovalDate(getDateTimeService().getCalendar(specialReview.getApprovalDate()));
            }
            if(specialReview.getApprovalTypeCode()!=null){
                protocolSpecialReviewType.setApprovalTypeCode(Integer.parseInt(specialReview.getApprovalTypeCode()));
                protocolSpecialReviewType.setApprovalTypeDesc(specialReview.getApprovalType().getDescription());
            }
            protocolSpecialReviewType.setComments(specialReview.getComments());
            protocolSpecialReviewType.setProtocolNumber(specialReview.getProtocolNumber());
            protocolSpecialReviewType.setSequenceNumber(specialReview.getSequenceNumber());
            protocolSpecialReviewType.setSpRevProtocolNumber(specialReview.getProtocolNumber());
            if(specialReview.getSpecialReviewTypeCode()!=null){
                protocolSpecialReviewType.setSpecialReviewCode(Integer.parseInt(specialReview.getSpecialReviewTypeCode()));
                protocolSpecialReviewType.setSpecialReviewDesc(specialReview.getSpecialReviewType().getDescription());
            }
            protocolSpecialReviewType.setSpecialReviewNumber(specialReview.getSpecialReviewNumber());
            protocolSpecialReviewType.setUpdateUser(specialReview.getUpdateUser());
        }
    }
    private void setProtocolVulnerableSubjects(ProtocolSummary protocolSummary, Protocol protocol) {
        List<ProtocolParticipant> protocolParticipants = protocol.getProtocolParticipants();
        for (ProtocolParticipant vulnerableSubListsBean : protocolParticipants) {
            ProtocolSubjectsType protocolSubjectsType = protocolSummary.addNewProtocolSubjects();
            protocolSubjectsType.setProtocolNumber(vulnerableSubListsBean.getProtocolNumber());
            protocolSubjectsType.setSequenceNumber(vulnerableSubListsBean.getSequenceNumber());
            if(vulnerableSubListsBean.getParticipantCount()!=null){
                protocolSubjectsType.setSubjectCount(vulnerableSubListsBean.getParticipantCount().intValue());
            }
            if(vulnerableSubListsBean.getParticipantType()!=null){
                protocolSubjectsType.setVulnerableSubjectTypeCode(Integer.parseInt(vulnerableSubListsBean.getParticipantTypeCode()));
                protocolSubjectsType.setVulnerableSubjectTypeDesc(vulnerableSubListsBean.getParticipantType().getDescription());
            }
            protocolSubjectsType.setUpdateUser(vulnerableSubListsBean.getUpdateUser());
        }

    }
    private void setProtocolActions(ProtocolSummary protocolSummary, Protocol protocol) {
        List<ProtocolAction> protocolAction = (List) protocol.getProtocolActions();
        for (ProtocolAction protocolActionsBean : protocolAction) {
            ProtocolActionsType protocolActionsType = protocolSummary.addNewProtocolActions();
            protocolActionsType.setActionId(protocolActionsBean.getActionId());
            if (protocolActionsBean.getActionDate()!=null) {
                protocolActionsType.setActionDate(getDateTimeService().getCalendar(protocolActionsBean.getActionDate()));
            }
            protocolActionsType.setComments(protocolActionsBean.getComments());
            if(protocolActionsBean.getProtocolActionTypeCode()!=null){
                protocolActionsType.setProtocolActionTypeCode(Integer.parseInt(protocolActionsBean.getProtocolActionTypeCode()));
                protocolActionsType.setProtocolActionTypeDesc(protocolActionsBean.getProtocolActionType().getDescription());
            }
            protocolActionsType.setProtocolNumber(protocolActionsBean.getProtocolNumber());
            protocolActionsType.setSequenceNumber(protocolActionsBean.getSequenceNumber());
            if(protocolActionsBean.getSubmissionNumber()!=null){
                protocolActionsType.setSubmissionNumber(protocolActionsBean.getSubmissionNumber());
            }
            protocolActionsType.setUpdateUser(protocolActionsBean.getUpdateUser());
            if (protocol.getApprovalDate()!=null) {
                protocolActionsType.setApprovalDate(convertDateToCalendar(protocol.getApprovalDate()));
            }
        }
        
    }
    private void setProtocolFundingResources(ProtocolSummary protocolSummary, Protocol protocol) {
        List<ProtocolFundingSource> protocolFundngSources = (List) protocol.getProtocolFundingSources();
        for (ProtocolFundingSource fundingSourceBean : protocolFundngSources) {
            ProtocolFundingSourceType protocolFundingSourceType = protocolSummary.addNewProtocolFundingSources();
            protocolFundingSourceType.setFundingSource(fundingSourceBean.getFundingSourceNumber());
            protocolFundingSourceType.setFundingSourceTypeCode(Integer.valueOf(fundingSourceBean.getFundingSourceTypeCode()));
            String title = getFundingSourceNameOrTitle(fundingSourceBean);
            if (title != null) {
                protocolFundingSourceType.setTitle(title);
            }
            if(fundingSourceBean.getFundingSourceType()!=null){
                protocolFundingSourceType.setFundingSourceTypeDesc(fundingSourceBean.getFundingSourceType().getDescription());
            }
            protocolFundingSourceType.setProtocolNumber(fundingSourceBean.getProtocolNumber());
            protocolFundingSourceType.setSequenceNumber(fundingSourceBean.getSequenceNumber());
            protocolFundingSourceType.setUpdateUser(fundingSourceBean.getUpdateUser());
        }
    }
    
    private String getFundingSourceNameOrTitle(ProtocolFundingSource fundingSourceBean) {
        String title = null;
        
        String fundingSourceTypeCode = fundingSourceBean.getFundingSourceTypeCode();
        if (FundingSourceType.SPONSOR.equals(fundingSourceTypeCode)) {
            title = getSponsorName(fundingSourceBean.getFundingSourceNumber());
        } else if (FundingSourceType.UNIT.equals(fundingSourceTypeCode)) {
            title = getUnitName(fundingSourceBean.getFundingSourceNumber());
        } else if (FundingSourceType.PROPOSAL_DEVELOPMENT.equals(fundingSourceTypeCode)) {
            title = getDevelopmentProposalTitle(fundingSourceBean.getFundingSourceNumber());
        } else if (FundingSourceType.INSTITUTIONAL_PROPOSAL.equals(fundingSourceTypeCode)) {
            title = getInstitutionalProposalTitle(fundingSourceBean.getFundingSourceNumber());
        } else if (FundingSourceType.AWARD.equals(fundingSourceTypeCode)) {
            title = getAwardTitle(fundingSourceBean.getFundingSourceNumber());
        }

        return title;
    }
    
    private String getSponsorName(String fundingSourceNumber) {
        return getSponsorService().getSponsorName(fundingSourceNumber);
    }
    
    private String getUnitName(String fundingSourceNumber) {
        return getUnitService().getUnitName(fundingSourceNumber);
    }
    
    private String getDevelopmentProposalTitle(String fundingSourceNumber) {
        DevelopmentProposal developmentProposal = getDataObjectService().find(DevelopmentProposal.class, fundingSourceNumber);
        
        return developmentProposal == null ? null : developmentProposal.getTitle();
    }
    
    private String getInstitutionalProposalTitle(String fundingSourceNumber) {
        InstitutionalProposal institutionalProposal = getInstitutionalProposalService().getActiveInstitutionalProposalVersion(fundingSourceNumber);
        
        if (institutionalProposal == null) {
            institutionalProposal = getInstitutionalProposalService().getPendingInstitutionalProposalVersion(fundingSourceNumber);
        }

        return institutionalProposal == null ? null : institutionalProposal.getTitle();
    }
    
    private String getAwardTitle(String fundingSourceNumber) {
        Award award = null;
        
        List<Award> awards = getAwardService().findAwardsForAwardNumber(fundingSourceNumber);
        
        if (!awards.isEmpty()) {
            award = awards.get(awards.size() - 1);
        }
        
        return award == null ? null : award.getTitle();
    }

    private void setProtocolResearchAreas(ProtocolSummary protocolSummary, Protocol protocol) {
        List<ProtocolResearchArea> vecResearchAreas = (List) protocol.getProtocolResearchAreas();
        for (ProtocolResearchArea reasearchAreasBean : vecResearchAreas) {
            ProtocolResearchAreasType protocolResearchAreasType = protocolSummary.addNewProtocolResearchAreas();
            protocolResearchAreasType.setProtocolNumber(reasearchAreasBean.getProtocolNumber());
            protocolResearchAreasType.setResearchAreaCode(reasearchAreasBean.getResearchAreaCode());
            protocolResearchAreasType.setResearchAreaDesc(reasearchAreasBean.getResearchAreas().getDescription());
            protocolResearchAreasType.setSequenceNumber(reasearchAreasBean.getSequenceNumber());
            protocolResearchAreasType.setUpdateUser(reasearchAreasBean.getUpdateUser());
        }
        
    }
    private void setProtocolLocations(ProtocolSummary protocolSummary, Protocol protocol) {
        List<ProtocolLocation> protocolLocationList = (List) protocol.getProtocolLocations();
        for (ProtocolLocation protocolLocationListBean : protocolLocationList) {
            protocolLocationListBean.refreshNonUpdateableReferences();
            ProtocolLocationType protocolLocationType = protocolSummary.addNewProtocolOrganization();
            protocolLocationType.setOrganizationId(protocolLocationListBean.getOrganizationId());
            if(protocolLocationListBean.getProtocolOrganizationType()!=null){
                protocolLocationType.setProtocolOrgTypeCode(Integer.parseInt(protocolLocationListBean.getProtocolOrganizationTypeCode()));
                protocolLocationType.setProtocolOrgTypeDesc(protocolLocationListBean.getProtocolOrganizationType().getDescription());
            }
            if(protocolLocationListBean.getOrganization()!=null){
                protocolLocationType.setOrgName(protocolLocationListBean.getOrganization().getOrganizationName());
            }
            protocolLocationType.setAddress(getAddress(protocolLocationListBean));
            protocolLocationType.setRolodexId(protocolLocationListBean.getRolodexId());
        }
        
    }
    private String getAddress(ProtocolLocation protocolLocationListBean) {
        protocolLocationListBean.refreshNonUpdateableReferences();
        StringBuffer strBffr = new StringBuffer();
        if(protocolLocationListBean.getOrganization()!=null){
            strBffr.append(protocolLocationListBean.getOrganization().getOrganizationName());
            strBffr.append(" ");
        }
        Rolodex address = protocolLocationListBean.getRolodex();
        if(address!=null){
            strBffr.append(address.getAddressLine1());
            strBffr.append(" ");
            strBffr.append(address.getAddressLine2());
            strBffr.append(" ");
            strBffr.append(address.getAddressLine3());
            strBffr.append(" ");
            strBffr.append(address.getCity());
            strBffr.append(" ");
            strBffr.append(address.getCounty());
            strBffr.append(" ");
            strBffr.append(address.getState());
            strBffr.append(" ");
            strBffr.append(address.getPostalCode());
            strBffr.append(" ");
            strBffr.append(address.getCountryCode());
            strBffr.append(" ");
        }
        return strBffr.toString();
    }
    private void setProtocolPersons(ProtocolSummary protocolSummary, Protocol protocol) {
        List<ProtocolPerson> vecInvestigator = (List) protocol.getProtocolPersons();
        for (ProtocolPerson protocolPerson : vecInvestigator) {
            protocolPerson.refreshNonUpdateableReferences();
            if (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRole.ROLE_PRINCIPAL_INVESTIGATOR)
                    || protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRole.ROLE_CO_INVESTIGATOR)) {
                ProtocolInvestigatorType protocolInvestigatorType = protocolSummary.addNewProtocolInvestigators();
                protocolInvestigatorType.setPersonId(protocolPerson.getPersonId());
                protocolInvestigatorType.setPersonName(protocolPerson.getPersonName());
                if(protocolPerson.getAffiliationType()!=null){
                    protocolInvestigatorType.setAffiliationTypeCode(protocolPerson.getAffiliationType().getAffiliationTypeCode());
                    protocolInvestigatorType.setAffiliationTypeDesc(protocolPerson.getAffiliationType().getDescription());
                }
                protocolInvestigatorType.setNonEmployeeFlag( ( protocolPerson.isNonEmployee()  ? "Y" : "N" ) );
                protocolInvestigatorType.setPrincipalInvestigatorFlag((protocolPerson.isPrincipalInvestigator() ? "Y" : "N"));
                protocolInvestigatorType.setProtocolNumber(protocolPerson.getProtocolNumber());
                protocolInvestigatorType.setSequenceNumber(protocolPerson.getSequenceNumber());
                protocolInvestigatorType.setUpdateUser(protocolPerson.getUpdateUser());
                protocolInvestigatorType.setTrainingFlag( ( protocolPerson.isTrained()  ? "Y" : "N" ) );
                List<ProtocolUnit> vecInvUnits =  (List) protocolPerson.getProtocolUnits();
                for (ProtocolUnit protocolUnit : vecInvUnits) {
                        ProtocolUnitsType protocolUnitsType = protocolInvestigatorType.addNewProtocolUnits();
                        protocolUnitsType.setLeadUnitFlag((protocolUnit.getLeadUnitFlag() ? "Y" : "N"));
                        protocolUnitsType.setPersonId(protocolUnit.getPersonId());
                        protocolUnitsType.setUnitNumber(protocolUnit.getUnitNumber());
                        protocolUnitsType.setUnitName(protocolUnit.getUnitName());
                        protocolUnitsType.setProtocolNumber(protocolUnit.getProtocolNumber());
                        protocolUnitsType.setSequenceNumber(protocolUnit.getSequenceNumber());
                }
            }else if (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRole.ROLE_STUDY_PERSONNEL)) {
                ProtocolKeyPersonsType protocolKeyPersonsType = protocolSummary.addNewProtocolKeyPersons();
                protocolKeyPersonsType.setPersonId(protocolPerson.getPersonId());
                protocolKeyPersonsType.setPersonName(protocolPerson.getPersonName());
                protocolKeyPersonsType.setAffiliationTypeCode(protocolPerson.getAffiliationType().getAffiliationTypeCode());
                protocolKeyPersonsType.setNonEmployeeFlag( ( protocolPerson.isNonEmployee() ? "Y" : "N" ) );
                protocolKeyPersonsType.setProtocolNumber(protocolPerson.getProtocolNumber());
                protocolKeyPersonsType.setSequenceNumber(protocolPerson.getSequenceNumber());
                protocolKeyPersonsType.setUpdateUser(protocolPerson.getUpdateUser());
                if(protocolPerson.getProtocolPersonRole()!=null){
                    protocolKeyPersonsType.setPersonRole(protocolPerson.getProtocolPersonRole().getDescription());
                }
                protocolKeyPersonsType.setTrainingFlag( ( protocolPerson.isTrained()  ? "Y" : "N" ) );
                protocolKeyPersonsType.setAffiliationTypeCode(protocolPerson.getAffiliationType().getAffiliationTypeCode());
                if(protocolPerson.getAffiliationType()!=null){
                    protocolKeyPersonsType.setAffiliationTypeDesc(protocolPerson.getAffiliationType().getDescription());
                }
            }
            else if (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRole.ROLE_CORRESPONDENT_CRC)
                    || (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRole.ROLE_CORRESPONDENT_ADMINISTRATOR))) {
                ProtocolCorrespondentType protocolCorrespondentType = protocolSummary.addNewProtocolCorrespondents();
                protocolCorrespondentType.setCorrespondentTypeCode(Integer.parseInt(protocolPerson.getProtocolPersonRoleId()));
                protocolCorrespondentType.setCorrespondentTypeDesc(protocolPerson.getProtocolPersonRole().getDescription());
                protocolCorrespondentType.setComments(protocolPerson.getComments());
                protocolCorrespondentType.setNonEmployeeFlag(( protocolPerson.isNonEmployee()  ? "Y" : "N" ));
                protocolCorrespondentType.setPersonId(protocolPerson.getPersonId());
                protocolCorrespondentType.setPersonName(protocolPerson.getPersonName());
                protocolCorrespondentType.setProtocolNumber(protocolPerson.getProtocolNumber());
                protocolCorrespondentType.setSequenceNumber(protocolPerson.getSequenceNumber());
                protocolCorrespondentType.setUpdateUser(protocolPerson.getUpdateUser());
            }
        }
    }
    private void setProtocolDetails(ProtocolSummary protocolSummary, Protocol protocol) {
        ProtocolDetailsType protocolDetailsType = null;
        if( protocol !=null ){
            protocolDetailsType = protocolSummary.addNewProtocolDetails();
            
            if (protocol.getSubmissionDate()!=null) {
                protocolDetailsType.setApplicationDate(getDateTimeService().getCalendar(protocol.getSubmissionDate()));
            }
            if (protocol.getApprovalDate()!=null) {
                protocolDetailsType.setApprovalDate(getDateTimeService().getCalendar(protocol.getApprovalDate()));
            }
            if (protocol.getInitialSubmissionDate()!=null) {
                protocolDetailsType.setCreateTimestamp(getDateTimeService().getCalendar(protocol.getUpdateTimestamp()));
            }
            
            protocolDetailsType.setDescription(protocol.getDescription());
            if (protocol.getExpirationDate()!=null) {
                protocolDetailsType.setExpirationDate(getDateTimeService().getCalendar(protocol.getExpirationDate()));
            }
            
            protocolDetailsType.setFdaApplicationNumber(protocol.getFdaApplicationNumber());
            if(protocol.getProtocolSubmission()!=null){
                protocolDetailsType.setIsBillable((protocol.getProtocolSubmission().isBillable()  ? "Y" : "N" ) );
            }
            if (protocol.getLastApprovalDate()!=null) {
                protocolDetailsType.setLastApprovalDate(getDateTimeService().getCalendar(protocol.getLastApprovalDate()));
            }
            
            protocolDetailsType.setProtocolNumber(protocol.getProtocolNumber());
            if(protocol.getProtocolStatusCode()!=null){
                protocolDetailsType.setProtocolStatusCode(Integer.parseInt(protocol.getProtocolStatusCode()));
                protocolDetailsType.setProtocolStatusDesc(protocol.getProtocolStatus().getDescription());
            }
            protocolDetailsType.setProtocolStatusDesc(protocol.getProtocolStatus().getDescription());
            if(protocol.getProtocolTypeCode()!=null){
                protocolDetailsType.setProtocolTypeCode(Integer.parseInt(protocol.getProtocolTypeCode()));
                protocolDetailsType.setProtocolTypeDesc(protocol.getProtocolType().getDescription());
            }
            setProtocolInvestigator(protocol, protocolDetailsType);
            protocolDetailsType.setReferenceNumber1(protocol.getReferenceNumber1());
            protocolDetailsType.setReferenceNumber2(protocol.getReferenceNumber2());
            protocolDetailsType.setSequenceNumber(protocol.getSequenceNumber());
            protocolDetailsType.setTitle(protocol.getTitle());
            protocolDetailsType.setUpdateUser(protocol.getUpdateUser());
        }
    }
    
    private void setProtocolInvestigator(Protocol protocol, ProtocolDetailsType protocolDetailsType) {
        List<ProtocolPerson> vecInvestigator = (List) protocol.getProtocolPersons();
        for (ProtocolPerson protocolPerson : vecInvestigator) {
            protocolPerson.refreshNonUpdateableReferences();
            if (protocolPerson.getProtocolPersonRoleId().equals(ProtocolPersonRole.ROLE_PRINCIPAL_INVESTIGATOR)) {
                protocolDetailsType.setInvestigator(protocolPerson.getPersonName());
            }
        }        
    }

    public SponsorService getSponsorService() {
        if (sponsorService == null) {
            sponsorService = KcServiceLocator.getService(SponsorService.class);
        }
        return sponsorService;
    }

    public void setSponsorService(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }

    public UnitService getUnitService() {
        if (unitService == null) {
            unitService = KcServiceLocator.getService(UnitService.class);
        }
        return unitService;
    }

    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }

    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public InstitutionalProposalService getInstitutionalProposalService() {
        if (institutionalProposalService == null) {
            institutionalProposalService = KcServiceLocator.getService(InstitutionalProposalService.class);
        }
        return institutionalProposalService;
    }

    public void setInstitutionalProposalService(InstitutionalProposalService institutionalProposalService) {
        this.institutionalProposalService = institutionalProposalService;
    }
 
    public AwardService getAwardService() {
        if (awardService == null) {
            awardService = KcServiceLocator.getService(AwardService.class);
        }
        return awardService;
    }

    public void setAwardService(AwardService awardService) {
        this.awardService = awardService;
    }

	public DataObjectService getDataObjectService() {
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}

}
