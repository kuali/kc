/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.s2s.service.impl;

import gov.grants.apply.system.globalV10.HashValueDocument.HashValue;
import gov.grants.apply.system.headerV10.GrantSubmissionHeaderDocument.GrantSubmissionHeader;
import gov.grants.apply.system.metaGrantApplication.GrantApplicationDocument;
import gov.grants.apply.system.metaGrantApplication.GrantApplicationDocument.GrantApplication;
import gov.grants.apply.system.metaGrantApplication.GrantApplicationDocument.GrantApplication.Forms;
import gov.grants.apply.webservices.applicantintegrationservices_v1.ApplicationInformationType;
import gov.grants.apply.webservices.applicantintegrationservices_v1.GetApplicationListResponse;
import gov.grants.apply.webservices.applicantintegrationservices_v1.GetApplicationStatusDetailResponse;
import gov.grants.apply.webservices.applicantintegrationservices_v1.GetOpportunityListResponse;
import gov.grants.apply.webservices.applicantintegrationservices_v1.OpportunityInformationType;
import gov.grants.apply.webservices.applicantintegrationservices_v1.SubmitApplicationResponse;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;
//import org.apache.soap.util.mime.ByteArrayDataSource;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlValidationError;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.bo.S2sAppAttachments;
import org.kuali.kra.s2s.bo.S2sAppSubmission;
import org.kuali.kra.s2s.bo.S2sApplication;
import org.kuali.kra.s2s.bo.S2sOppForms;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.formmapping.FormMappingInfo;
import org.kuali.kra.s2s.formmapping.FormMappingLoader;
import org.kuali.kra.s2s.generator.S2SFormGenerator;
import org.kuali.kra.s2s.generator.S2SGeneratorNotFoundException;
import org.kuali.kra.s2s.generator.bo.AttachmentData;
import org.kuali.kra.s2s.service.GrantsGovConnectorService;
import org.kuali.kra.s2s.service.PrintService;
import org.kuali.kra.s2s.service.S2SFormGeneratorService;
import org.kuali.kra.s2s.service.S2SProposalValidatorService;
import org.kuali.kra.s2s.service.S2SService;
import org.kuali.kra.s2s.service.S2SUtilService;
//import org.kuali.kra.s2s.util.GrantApplicationHash;
import org.kuali.kra.s2s.util.GrantApplicationHash;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.kra.s2s.validator.OpportunitySchemaParser;
import org.kuali.kra.s2s.validator.S2SErrorHandler;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.GlobalVariables;
import org.w3c.dom.Node;

/**
 * 
 * This class is implementation of S2SService
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
// TODO: remove email tag from javadoc. new kra javadoc template does not have email
public class KRAS2SServiceImpl implements S2SService {
    private static final Logger LOG = Logger.getLogger(KRAS2SServiceImpl.class);
    private BusinessObjectService businessObjectService;
    private DateTimeService dateTimeService;
    private S2SFormGeneratorService s2SFormGeneratorService;
    private S2SProposalValidatorService s2SProposalValidatorService;
    private S2SUtilService s2SUtilService;
    private PrintService printService;
    private GrantsGovConnectorService grantsGovConnectorService;
    private static final String GRANTS_GOV_PREFIX = "/GrantApplication/Forms";
    private static final String GRANTS_GOV_STATUS = "ERROR";
    private static final String KEY_PROPOSAL_NUMBER = "proposalNumber";


    /**
     * 
     * This method is used to get the application status details.
     * 
     * @param ggTrackingId grants gov tracking id for the application.
     * @param proposalNumber Proposal number.
     * @throws S2SException
     * @see org.kuali.kra.s2s.service.S2SService#getStatusDetails(java.lang.String, java.lang.String)
     */
    public Object getStatusDetails(String ggTrackingId, String proposalNumber) throws S2SException {
        Object statusDetail = null;
        GetApplicationStatusDetailResponse applicationStatusDetailResponse;
        applicationStatusDetailResponse = grantsGovConnectorService.getApplicationStatusDetail(ggTrackingId, proposalNumber);
        if (applicationStatusDetailResponse != null) {
            statusDetail = applicationStatusDetailResponse.getDetailedStatus();
        }
        return statusDetail;
    }

    /**
     * 
     * This method returns the list of forms for a given opportunity
     * 
     * @param opportunity
     * @return {@link List}of {@link S2sOppForms} which are included in the given {@link S2sOpportunity}
     * @see org.kuali.kra.s2s.service.S2SService#parseOpportunityForms(org.kuali.kra.s2s.bo.S2sOpportunity)
     */
    public List<S2sOppForms> parseOpportunityForms(S2sOpportunity opportunity) {
        return new OpportunitySchemaParser().getForms(opportunity.getSchemaUrl());
    }

    /**
     * This method checks for the status of submission for the given {@link ProposalDevelopmentDocument} on Grants.gov
     * 
     * @param pdDoc for which status has to be checked
     * @return boolean, <code>true</code> if status has changed, false otherwise
     * @throws S2SException
     * @see org.kuali.kra.s2s.service.S2SService#refreshGrantsGov(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public boolean refreshGrantsGov(ProposalDevelopmentDocument pdDoc) throws S2SException {
        Map<String, String> opportunityMap = new HashMap<String, String>();
        opportunityMap.put(KEY_PROPOSAL_NUMBER, pdDoc.getProposalNumber());
        S2sOpportunity s2sOpportunity = (S2sOpportunity) businessObjectService.findByPrimaryKey(S2sOpportunity.class,
                opportunityMap);
        boolean success = false;
        GetApplicationListResponse applicationListResponse = grantsGovConnectorService.getApplicationList(s2sOpportunity
                .getOpportunityId(), s2sOpportunity.getCfdaNumber(), s2sOpportunity.getProposalNumber());
        List<S2sAppSubmission> appSubmissionList = pdDoc.getS2sAppSubmission();
        S2sAppSubmission appSubmission = null;
        int submissionNo = 0;
        for (S2sAppSubmission s2AppSubmission : appSubmissionList) {
            if (s2AppSubmission.getSubmissionNumber() > submissionNo) {
                appSubmission = s2AppSubmission;
                submissionNo = s2AppSubmission.getSubmissionNumber();
            }
        }
        saveGrantsGovStatus(pdDoc, appSubmission, applicationListResponse);
        success = true;
        return success;
    }

    /**
     * 
     * This method fetches the status from Grants Gov and saves in case status is modified
     * 
     * @param pdDoc {@link ProposalDevelopmentDocument}
     * @param appSubmission {@link S2sAppSubmission}
     * @param applicationListResponse {@link GetApplicationListResponse} response from Grants Gov
     * @throws S2SException
     */
    private void saveGrantsGovStatus(ProposalDevelopmentDocument pdDoc, S2sAppSubmission appSubmission,
            GetApplicationListResponse applicationListResponse) throws S2SException {
        boolean statusChanged = false;
        if (appSubmission != null) {
            if (applicationListResponse.getApplicationInformation() == null
                    || applicationListResponse.getApplicationInformation().size() == 0) {
                GetApplicationStatusDetailResponse response = grantsGovConnectorService.getApplicationStatusDetail(appSubmission
                        .getGgTrackingId(), pdDoc.getProposalNumber());
                if (response != null && response.getDetailedStatus() != null) {
                    String statusDetail = response.getDetailedStatus().toString();
                    statusChanged = !statusDetail.equalsIgnoreCase(appSubmission.getStatus());
                    appSubmission.setComments(statusDetail);
                    String statusStr = statusDetail.toUpperCase().indexOf(GRANTS_GOV_STATUS) == -1 ? statusDetail
                            : S2SConstants.STATUS_GRANTS_GOV_SUBMISSION_ERROR;
                    appSubmission.setStatus(statusStr);
                }
                else {
                    appSubmission.setComments(S2SConstants.STATUS_NO_RESPONSE_FROM_GRANTS_GOV);
                }
            }
            else {
                int appSize = applicationListResponse.getApplicationInformation().size();
                ApplicationInformationType ggApplication = applicationListResponse.getApplicationInformation().get(appSize - 1);
                if (ggApplication != null) {
                    statusChanged = !appSubmission.getStatus().equalsIgnoreCase(
                            ggApplication.getGrantsGovApplicationStatus().value());
                    appSubmission.setGgTrackingId(ggApplication.getGrantsGovTrackingNumber());
                    appSubmission.setReceivedDate(new Timestamp(ggApplication.getReceivedDateTime().toGregorianCalendar()
                            .getTimeInMillis()));
                    appSubmission.setStatus(ggApplication.getGrantsGovApplicationStatus().toString());
                    appSubmission.setLastModifiedDate(new Timestamp(ggApplication.getStatusDateTime().toGregorianCalendar()
                            .getTimeInMillis()));
                    appSubmission.setAgencyTrackingId(ggApplication.getAgencyTrackingNumber());
                    if (ggApplication.getAgencyTrackingNumber() != null && ggApplication.getAgencyTrackingNumber().length() > 0) {
                        appSubmission.setComments(S2SConstants.STATUS_AGENCY_TRACKING_NUMBER_ASSIGNED);
                    }
                    else {
                        appSubmission.setComments(ggApplication.getGrantsGovApplicationStatus().toString());
                    }
                }
            }
        }
        if (statusChanged) {
            businessObjectService.save(appSubmission);
        }
    }

    /**
     * This method is to find the list of available opportunities for a given cfda number, opportunity ID and competition ID.
     * 
     * @param cfdaNumber of the opportunity.
     * @param opportunityId parameter for the opportunity.
     * @param competitionId parameter for the opportunity.
     * @return List<S2sOpportunity> a list containing the available opportunities for the corresponding parameters.
     * @throws S2SException
     * @see org.kuali.kra.s2s.service.S2SService#searchOpportunity(java.lang.String, java.lang.String, java.lang.String)
     */
    public List<S2sOpportunity> searchOpportunity(String cfdaNumber, String opportunityId, String competitionId)
            throws S2SException {
        List<S2sOpportunity> s2sOpportunityList;
        s2sOpportunityList = convertToArrayList(grantsGovConnectorService.getOpportunityList(cfdaNumber, opportunityId,
                competitionId));
        return s2sOpportunityList;
    }

    /**
     * This method is used to submit forms to Grants.gov. It generates forms for a given {@link ProposalDevelopmentDocument},
     * validates and then submits the forms
     * 
     * @param pdDoc Proposal Development Document.
     * @return true if submitted false otherwise.
     * @throws S2SException
     * @see org.kuali.kra.s2s.service.S2SService#submitApplication(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public boolean submitApplication(ProposalDevelopmentDocument pdDoc) throws S2SException {
        boolean submissionStatus = false;
        Forms forms = Forms.Factory.newInstance();
        List<AttachmentData> attList = new ArrayList<AttachmentData>();
        if (generateAndValidateForms(forms, attList, pdDoc)) {
            GrantApplicationDocument grantApplicationDocument = getGrantApplicationDocument(pdDoc, forms);

            Map<String, DataHandler> attachments = new HashMap<String, DataHandler>();
            List<S2sAppAttachments> s2sAppAttachmentList = new ArrayList<S2sAppAttachments>();
            DataHandler attachmentFile;
            for (AttachmentData attachmentData : attList) {
                attachmentFile = new DataHandler(new ByteArrayDataSource(attachmentData.getContent(), attachmentData
                        .getContentType()));
                attachments.put(attachmentData.getContentId(), attachmentFile);
                S2sAppAttachments appAttachments = new S2sAppAttachments();
                appAttachments.setContentId(attachmentData.getContentId());
                s2sAppAttachmentList.add(appAttachments);
            }
            S2sAppSubmission appSubmission = new S2sAppSubmission();
            appSubmission.setStatus(S2SConstants.GRANTS_GOV_STATUS_MESSAGE);
            appSubmission.setComments(S2SConstants.GRANTS_GOV_COMMENTS_MESSAGE);
            SubmitApplicationResponse response = null;
            try {
                response = grantsGovConnectorService.submitApplication(grantApplicationDocument.xmlText(), attachments, pdDoc
                        .getProposalNumber());
            }
            catch (S2SException ex) {
                appSubmission.setStatus(S2SConstants.STATUS_GRANTS_GOV_SUBMISSION_ERROR);
                StringBuilder errMsg = new StringBuilder();
                errMsg.append(S2SConstants.GRANTS_GOV_SUBMISION_ERROR_MESSAGE);
                errMsg.append(ex.getMessage());
                appSubmission.setComments(errMsg.toString());
                businessObjectService.save(appSubmission);
                throw new S2SException(ex);
            }
            saveSubmissionDetails(pdDoc, appSubmission, response, grantApplicationDocument.xmlText(), s2sAppAttachmentList);
            submissionStatus = true;
        }
        return submissionStatus;
    }

    /**
     * 
     * This method populates values for {@link GrantApplicationDocument} for a given {@link ProposalDevelopmentDocument}
     * 
     * @param pdDoc {@link ProposalDevelopmentDocument}
     * @param forms {@link Forms} generated XML forms
     * @return {@link GrantApplicationDocument} populated with forms
     * @throws S2SException
     */
    private GrantApplicationDocument getGrantApplicationDocument(ProposalDevelopmentDocument pdDoc, Forms forms)
            throws S2SException {
        GrantApplicationDocument grantApplicationDocument = GrantApplicationDocument.Factory.newInstance();
        GrantApplication grantApplication = GrantApplication.Factory.newInstance();
        grantApplication.setForms(forms);
        GrantSubmissionHeader grantSubmissionHeader = GrantSubmissionHeader.Factory.newInstance();
        grantSubmissionHeader.setActivityTitle(pdDoc.getProgramAnnouncementTitle());
        grantSubmissionHeader.setOpportunityTitle(pdDoc.getProgramAnnouncementTitle());
        grantSubmissionHeader.setAgencyName(pdDoc.getSponsor().getSponsorName());
        grantSubmissionHeader.setCFDANumber(pdDoc.getCfdaNumber());
        S2sOpportunity s2sOpportunity = pdDoc.getS2sOpportunity();
        s2sOpportunity.refreshNonUpdateableReferences();
        if (s2sOpportunity.getCompetetionId() != null) {
            grantSubmissionHeader.setCompetitionID(s2sOpportunity.getCompetetionId());
        }
        grantSubmissionHeader.setOpportunityID(s2sOpportunity.getOpportunityId());
        grantSubmissionHeader.setSchemaVersion(S2SConstants.FORMVERSION_1_0);
        grantSubmissionHeader.setSubmissionTitle(s2sOpportunity.getProposalNumber());
        Calendar calClosingDate = Calendar.getInstance();
        calClosingDate.setTimeInMillis(s2sOpportunity.getClosingDate().getTime());
        grantSubmissionHeader.setClosingDate(calClosingDate);
        Calendar calOpeningDate = Calendar.getInstance();
        calOpeningDate.setTimeInMillis(s2sOpportunity.getOpeningDate().getTime());
        grantSubmissionHeader.setOpeningDate(calOpeningDate);
        String hashVal = GrantApplicationHash.computeGrantFormsHash(grantApplication.xmlText());
        HashValue hashValue = HashValue.Factory.newInstance();
        hashValue.setHashAlgorithm(S2SConstants.HASH_ALGORITHM);
        hashValue.setStringValue(hashVal);
        grantSubmissionHeader.setHashValue(hashValue);
        grantApplication.setGrantSubmissionHeader(grantSubmissionHeader);
        grantApplicationDocument.setGrantApplication(grantApplication);
        return grantApplicationDocument;
    }

    /**
     * 
     * This method saves the submission details after successful submission of proposal
     * 
     * @param pdDoc {@link ProposalDevelopmentDocument} that was submitted
     * @param appSubmission {@link S2sAppSubmission} submission details of proposal
     * @param response {@link SubmitApplicationResponse} submission response from grants gov
     * @param grantApplicationXml {@link String} XML content of submission
     * @param s2sAppAttachmentList {@link S2sAppAttachments} attachments included in submission
     */
    private void saveSubmissionDetails(ProposalDevelopmentDocument pdDoc, S2sAppSubmission appSubmission,
            SubmitApplicationResponse response, String grantApplicationXml, List<S2sAppAttachments> s2sAppAttachmentList) {
        if (response != null) {
            appSubmission.setGgTrackingId(response.getGrantsGovTrackingNumber());
            appSubmission.setReceivedDate(new Timestamp(response.getReceivedDateTime().toGregorianCalendar().getTimeInMillis()));
            S2sApplication application = new S2sApplication();
            application.setApplication(grantApplicationXml);
            application.setS2sAppAttachmentList(s2sAppAttachmentList);
            List<S2sApplication> s2sApplicationList = new ArrayList<S2sApplication>();
            s2sApplicationList.add(application);
            appSubmission.setS2sApplication(s2sApplicationList);
            appSubmission.setProposalNumber(pdDoc.getProposalNumber());
            List<S2sAppSubmission> appList = pdDoc.getS2sAppSubmission();
            int submissionNumber = 1;
            for (S2sAppSubmission submittedApplication : appList) {
                if (submittedApplication.getSubmissionNumber() >= submissionNumber) {
                    ++submissionNumber;
                }
            }
            appSubmission.setSubmissionNumber(submissionNumber);
            appList.add(appSubmission);
            appSubmission.setStatus(S2SConstants.GRANTS_GOV_SUBMISSION_MESSAGE);
            appSubmission.setComments(S2SConstants.GRANTS_GOV_PROCESSING_MESSAGE);
            pdDoc.setS2sAppSubmission(appList);
            businessObjectService.save(appSubmission);
        }
    }

    /**
     * 
     * This method is used to validate application before submission.
     * 
     * @param pdDoc Proposal Development Document.
     * @return boolean true if valid false otherwise.
     * @throws S2SException
     * @see org.kuali.kra.s2s.service.S2SService#validateApplication(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public boolean validateApplication(ProposalDevelopmentDocument pdDoc) throws S2SException {
        return generateAndValidateForms(null, null, pdDoc);
    }

    /**
     * 
     * This method is to generate and validate the generated forms.
     * 
     * @param forms Forms
     * @param attList List of attachments.
     * @param pdDoc Proposal Development Document.
     * @return validation result true if valid false otherwise.
     * @throws S2SException
     */
    private boolean generateAndValidateForms(Forms forms, List<AttachmentData> attList,
            ProposalDevelopmentDocument pdDoc) throws S2SException {
        boolean validationSucceeded = true;
        List<AuditError> errors = new ArrayList<AuditError>();
        FormMappingInfo info = null;
        S2SFormGenerator s2sFormGenerator = null;

        for (S2sOppForms oppForms : pdDoc.getS2sOppForms()) {
            if (!oppForms.getInclude()) {
                continue;
            }
            try {
                info = new FormMappingLoader().getFormInfo(oppForms.getOppNameSpace());
                s2sFormGenerator = s2SFormGeneratorService.getS2SGenerator(info.getNameSpace());
            }
            catch (S2SGeneratorNotFoundException e) {
                // Form generator not available
                continue;
            }

            XmlObject formObject = s2sFormGenerator.getFormObject(pdDoc);
            if (validateForm(formObject, errors, info.getFormName())) {
                if (forms != null && attList != null) {
                    setFormObject(forms, formObject);
                    attList.addAll(s2sFormGenerator.getAttachments());
                }
            }
            else {
                validationSucceeded = false;
            }
        }
        if (!validationSucceeded) {
            setValidationErrorMessage(errors);
        }
        return validationSucceeded;
    }

    /**
     * 
     * This method is to set the formObject to MetaGrants Forms Object. The xmlbeans
     * Schema compiled with xsd:any does not provide a direct method to add individual
     * forms to the Forms object. 
     * In this method, an XML Cursor is created from the existing Forms object and use
     * the moveXml method to add the form object to the Forms object.
     * 
     * @param forms Forms object to which the grants.gov form is added.
     * @param formObject xml object representing the grants.gov form.
     */
    private void setFormObject(Forms forms, XmlObject formObject) {
        // Create a cursor from the grants.gov form
        XmlCursor formCursor = formObject.newCursor();
        formCursor.toStartDoc();
        formCursor.toNextToken();

        // Create a cursor from the Forms object
        XmlCursor metaGrantCursor = forms.newCursor();
        metaGrantCursor.toNextToken();

        // Add the form to the Forms object.
        formCursor.moveXml(metaGrantCursor);
    }

    /**
     * 
     * This method is to put validation errors on UI
     * 
     * @param errors List of validation errors which has to be displayed on UI.
     */

    private void setValidationErrorMessage(List<AuditError> errors) {
        LOG.info("Error list size:" + errors.size() + errors.toString());
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        for (AuditError error : errors) {
            auditErrors.add(new AuditError(error.getErrorKey(), Constants.GRANTS_GOV_GENERIC_ERROR_KEY, error.getLink(),
                new String[] { error.getMessageKey() }));
        }
        if (!auditErrors.isEmpty()) {
            GlobalVariables.getAuditErrorMap().put("grantsGovAuditErrors",
                    new AuditCluster(Constants.GRANTS_GOV_OPPORTUNITY_PANEL, auditErrors, Constants.GRANTSGOV_ERRORS));
        }
    }

    /**
     * This method convert GetOpportunityListResponse to ArrayList<OpportunityInfo>
     * 
     * @param resList {GetOpportunityListResponse}
     * @return ArrayList<OpportunityInfo> containing all form information
     */

    private ArrayList<S2sOpportunity> convertToArrayList(GetOpportunityListResponse resList) {
        ArrayList<S2sOpportunity> convList = new ArrayList<S2sOpportunity>();
        if (resList == null || resList.getOpportunityInformation() == null) {
            return convList;
        }
        for (OpportunityInformationType opportunityInformationType : resList.getOpportunityInformation()) {
            convList.add(convert2S2sOpportunity(opportunityInformationType));
        }
        return convList;
    }

    /**
     * This method convert OpportunityInformationType to OpportunityInfo
     * 
     * @param oppInfoType {OpportunityInformationType}
     * @return OpportunityInfo containing Opportunity information corresponding to the OpportunityInformationType object.
     */
    private S2sOpportunity convert2S2sOpportunity(OpportunityInformationType oppInfoType) {
        S2sOpportunity s2Opportunity = new S2sOpportunity();
        s2Opportunity.setCfdaNumber(oppInfoType.getCFDANumber());
        s2Opportunity.setClosingDate(oppInfoType.getClosingDate() == null ? null : new java.sql.Timestamp(oppInfoType
                .getClosingDate().toGregorianCalendar().getTimeInMillis()));
        s2Opportunity.setCompetetionId(oppInfoType.getCompetitionID());
        s2Opportunity.setInstructionUrl(oppInfoType.getInstructionURL());
        s2Opportunity.setOpeningDate(oppInfoType.getOpeningDate() == null ? null : new java.sql.Timestamp(oppInfoType
                .getOpeningDate().toGregorianCalendar().getTimeInMillis()));
        s2Opportunity.setOpportunityId(oppInfoType.getOpportunityID());
        s2Opportunity.setOpportunityTitle(oppInfoType.getOpportunityTitle());
        s2Opportunity.setSchemaUrl(oppInfoType.getSchemaURL());
        return s2Opportunity;
    }

    /**
     * This method is to set businessObjectService
     * 
     * @param businessObjectService(BusinessObjectService)
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * 
     * Setter for {@link S2SFormGeneratorService}
     * 
     * @param s2SFormGeneratorService
     */
    public void setS2SFormGeneratorService(S2SFormGeneratorService s2SFormGeneratorService) {
        this.s2SFormGeneratorService = s2SFormGeneratorService;
    }

    /**
     * 
     * Setter for {@link S2SProposalValidatorService}
     * 
     * @param s2SProposalValidatorService
     */
    public void setS2SProposalValidatorService(S2SProposalValidatorService s2SProposalValidatorService) {
        this.s2SProposalValidatorService = s2SProposalValidatorService;
    }

    /**
     * Sets the s2sUtilService attribute value.
     * 
     * @param generatorUtilService The s2sUtilService to set.
     */
    public void setS2SUtilService(S2SUtilService s2SUtilService) {
        this.s2SUtilService = s2SUtilService;
    }

    /**
     * 
     * This method is used to print selected forms.
     * 
     * @param pdDoc Proposal Development Document.
     * @return AttachmentDataSource for the selected form.
     * @throws S2SException
     */
    public AttachmentDataSource printForm(ProposalDevelopmentDocument pdDoc) throws S2SException {
        return printService.printForm(pdDoc);
    }

    /**
     * Gets the printService attribute.
     * 
     * @return Returns the printService.
     */
    public PrintService getPrintService() {
        return printService;
    }

    /**
     * Sets the printService attribute value.
     * 
     * @param printService The printService to set.
     */
    public void setPrintService(PrintService printService) {
        this.printService = printService;
    }

    /**
     * 
     * This method is to validated the form.
     * 
     * @param formObject xml object of the form.
     * @param errors list of validation errors.
     * @param formName name of the form
     * @return validation result true if valid false otherwise.
     */
    private boolean validateForm(XmlObject formObject, List<AuditError> errors, String formName) {
        List<String> formErrors = new ArrayList<String>();
        boolean isFormValid = validateXml(formObject, formErrors, formName);
        if (!isFormValid) {
            for (String error : formErrors) {
                errors.add(S2SErrorHandler.getError(GRANTS_GOV_PREFIX + error));
            }
        }
        return isFormValid;
    }


    /**
     * 
     * This method receives an XMLObject and validates it against its schema and returns the validation result. It also receives a
     * list in which upon validation failure, populates it with XPaths of the error nodes
     * 
     * @param formObject XML document as {@link}XMLObject
     * @param errors List list of XPaths of the error nodes.
     * @return validation result true if valid false otherwise.
     */
    protected boolean validateXml(XmlObject formObject, List<String> errors, String formName) {
        XmlOptions validationOptions = new XmlOptions();
        ArrayList<XmlValidationError> validationErrors = new ArrayList<XmlValidationError>();
        validationOptions.setErrorListener(validationErrors);

        boolean isValid = formObject.validate(validationOptions);
        if (!isValid) {
            LOG.error("Errors occured during validation of XML from form generator" + validationErrors);
            Iterator<XmlValidationError> iter = validationErrors.iterator();
            while (iter.hasNext()) {
                XmlError error = iter.next();
                LOG.info("Validation error:" + error);
                Node node = error.getCursorLocation().getDomNode();
                String xPath = getXPath(node);
                errors.add(xPath);
            }
            LOG.info("Error XPaths:" + errors);
        }
        return isValid;
    }

    /**
     * 
     * This method receives a node, fetches its name, and recurses up to its parent node, until it reaches the document node, thus
     * creating the XPath of the node passed and returns it as String
     * 
     * @param node for which the Document node has to be found.
     * @return String which represents XPath of the node
     */
    private String getXPath(Node node) {
        String xPath = null;
        if (node.getNodeType() == Node.DOCUMENT_NODE) {
            xPath = "";
        }
        else {       
            //Use File.separator instead "/"
            xPath =  getXPath(node.getParentNode()) + "/" + node.getNodeName();
        }
        return xPath;
    }

    /**
     * Gets the s2SFormGeneratorService attribute.
     * 
     * @return Returns the s2SFormGeneratorService.
     */
    public S2SFormGeneratorService getS2SFormGeneratorService() {
        return s2SFormGeneratorService;
    }

    /**
     * Gets the s2SProposalValidatorService attribute.
     * 
     * @return Returns the s2SProposalValidatorService.
     */
    public S2SProposalValidatorService getS2SProposalValidatorService() {
        return s2SProposalValidatorService;
    }

    /**
     * Gets the s2sUtilService attribute.
     * 
     * @return Returns the s2sUtilService.
     */
    public S2SUtilService getS2sUtilService() {
        return s2SUtilService;
    }

    /**
     * Gets the businessObjectService attribute.
     * 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Gets the dateTimeService attribute.
     * 
     * @return Returns the dateTimeService.
     */
    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    /**
     * Sets the dateTimeService attribute value.
     * 
     * @param dateTimeService The dateTimeService to set.
     */
    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    /**
     * Sets the grantsGovConnectorService attribute value.
     * 
     * @param grantsGovConnectorService The grantsGovConnectorService to set.
     */
    public void setGrantsGovConnectorService(GrantsGovConnectorService grantsGovConnectorService) {
        this.grantsGovConnectorService = grantsGovConnectorService;
    }
}
