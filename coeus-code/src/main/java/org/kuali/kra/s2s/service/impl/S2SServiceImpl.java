/*
 * Copyright 2005-2014 The Kuali Foundation.
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

import gov.grants.apply.system.globalV10.HashValueDocument;
import gov.grants.apply.system.headerV10.GrantSubmissionHeaderDocument;
import gov.grants.apply.system.metaGrantApplication.GrantApplicationDocument;
import gov.grants.apply.system.metaGrantApplication.GrantApplicationDocument.GrantApplication.Forms;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.propdev.api.s2s.*;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.kra.s2s.S2SException;

import org.kuali.coeus.propdev.api.attachment.NarrativeService;
import org.kuali.kra.s2s.formmapping.FormMappingInfo;
import org.kuali.kra.s2s.formmapping.FormMappingService;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.generator.bo.AttachmentData;
import org.kuali.kra.s2s.service.*;
import org.kuali.kra.s2s.util.AuditError;
import org.kuali.kra.s2s.util.GrantApplicationHash;
import org.kuali.kra.s2s.util.S2SConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;
import java.util.*;

@Component("s2SService")
public class S2SServiceImpl implements S2SService {
	private static final Log LOG = LogFactory.getLog(S2SServiceImpl.class);

    @Autowired
    @Qualifier("s2SFormGeneratorService")
	private S2SFormGeneratorService s2SFormGeneratorService;

    @Autowired
    @Qualifier("s2SValidatorService")
    private S2SValidatorService s2SValidatorService;

    @Autowired
    @Qualifier("narrativeService")
    private NarrativeService narrativeService;

    @Autowired
    @Qualifier("s2SUtilService")
    private S2SUtilService s2SUtilService;

    @Autowired
    @Qualifier("formMappingService")
    private FormMappingService formMappingService;

	/**
	 * 
	 * This method is used to validate application before submission.
	 * 
	 * @param proposalDevelopmentDocument
	 *            Proposal Development Document.
	 * @return boolean true if valid false otherwise.
	 * @throws S2SException
	 * @see org.kuali.kra.s2s.service.S2SService#validateApplication(org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument)
	 */
	public FormActionResult validateApplication(
			ProposalDevelopmentDocument proposalDevelopmentDocument)
			throws S2SException {
		return generateAndValidateForms(null, null,
				proposalDevelopmentDocument);
	}

	/**
	 * 
	 * This method is to generate and validate the generated forms.
	 * 
	 * @param forms
	 *            Forms
	 * @param attList
	 *            List of attachments.
	 * @param pdDoc
	 *            Proposal Development Document.
	 * @return validation result true if valid false otherwise.
	 * @throws S2SException
	 */
	public FormActionResult generateAndValidateForms(Forms forms,
			List<AttachmentData> attList, ProposalDevelopmentDocument pdDoc) throws S2SException {
		boolean validationSucceeded = true;
		DevelopmentProposal developmentProposal = pdDoc.getDevelopmentProposal();
		List<? extends S2sOppFormsContract> opportunityForms = developmentProposal.getS2sOppForms();

		if (attList == null) {
		    attList = new ArrayList<AttachmentData>();
		}

        List<AuditError> auditErrors = new ArrayList<AuditError>();
        getNarrativeService().deleteSystemGeneratedNarratives(pdDoc.getDevelopmentProposal().getNarratives());
		for (S2sOppFormsContract opportunityForm : opportunityForms) {
			if (!opportunityForm.getInclude()) {
				continue;
			}
			List<AttachmentData> formAttList = new ArrayList<AttachmentData>();
			S2SBaseFormGenerator s2sFormGenerator = null;
            FormMappingInfo info = formMappingService.getFormInfo(developmentProposal.getProposalNumber(),opportunityForm.getOppNameSpace());
            if(info==null) continue;
			String namespace = info.getNameSpace();
            s2sFormGenerator = (S2SBaseFormGenerator)s2SFormGeneratorService.getS2SGenerator(developmentProposal.getProposalNumber(),namespace);
		    s2sFormGenerator.setAuditErrors(auditErrors);
		    s2sFormGenerator.setAttachments(formAttList);
		    s2sFormGenerator.setNamespace(info.getNameSpace());
			try {
				XmlObject formObject = s2sFormGenerator.getFormObject(pdDoc);
				if (s2SValidatorService.validate(formObject, auditErrors)) {
					if (forms != null && attList != null) {
						setFormObject(forms, formObject);
					}
				} else {
					validationSucceeded = false;
				}
				attList.addAll(formAttList);
			} catch (Exception ex) {
				LOG.error(
						"Unknown error from " + opportunityForm.getFormName(),
						ex);
				throw new S2SException("Could not generate form for "
						+ opportunityForm.getFormName(), ex);
			}
		}
        FormActionResult result = new FormActionResult();
        result.setValid(validationSucceeded);
        result.setErrors(auditErrors);
        result.setAttachments(attList);
		return result;
	}

    @Override
    public FormActionResult generateAndValidateForms(ProposalDevelopmentDocument pdDoc) throws S2SException {

        GrantApplicationDocument.GrantApplication.Forms forms = GrantApplicationDocument.GrantApplication.Forms.Factory.newInstance();
        List<AttachmentData> attList = new ArrayList<AttachmentData>();
        final FormActionResult result = generateAndValidateForms(forms, attList, pdDoc);
        if (result.isValid()) {
            String applicationXml = getGrantApplicationDocument(pdDoc,forms);
            result.setApplicationXml(applicationXml);
        }
        return result;
    }

    /**
     *
     * This method populates values for {@link GrantApplicationDocument} for a
     * given {@link ProposalDevelopmentDocument}
     *
     * @param pdDoc
     *            {@link ProposalDevelopmentDocument}
     * @param forms
     *            {@link gov.grants.apply.system.metaGrantApplication.GrantApplicationDocument.GrantApplication.Forms} generated XML forms
     * @return {@link GrantApplicationDocument} populated with forms
     * @throws S2SException
     */
    protected String getGrantApplicationDocument(
            ProposalDevelopmentDocument pdDoc, GrantApplicationDocument.GrantApplication.Forms forms) throws S2SException {
        GrantApplicationDocument grantApplicationDocument = GrantApplicationDocument.Factory.newInstance();
        GrantApplicationDocument.GrantApplication grantApplication = GrantApplicationDocument.GrantApplication.Factory.newInstance();
        grantApplication.setForms(forms);
        GrantSubmissionHeaderDocument.GrantSubmissionHeader grantSubmissionHeader = GrantSubmissionHeaderDocument.GrantSubmissionHeader.Factory.newInstance();
        grantSubmissionHeader.setActivityTitle(pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle());
        grantSubmissionHeader.setOpportunityTitle(pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle());
        grantSubmissionHeader.setAgencyName(pdDoc.getDevelopmentProposal().getSponsor().getSponsorName());
        if(pdDoc.getDevelopmentProposal().getCfdaNumber()!=null){
            grantSubmissionHeader.setCFDANumber(pdDoc.getDevelopmentProposal().getCfdaNumber());
        }
        S2sOpportunityContract s2sOpportunity = pdDoc.getDevelopmentProposal().getS2sOpportunity();
        if (s2sOpportunity.getCompetetionId() != null) {
            grantSubmissionHeader.setCompetitionID(s2sOpportunity.getCompetetionId());
        }
        grantSubmissionHeader.setOpportunityID(s2sOpportunity.getOpportunityId());
        grantSubmissionHeader.setSchemaVersion(S2SConstants.FORMVERSION_1_0);
        grantSubmissionHeader.setSubmissionTitle(s2sOpportunity.getProposalNumber());

        // set closing date unless null
        Calendar closingDate = s2sOpportunity.getClosingDate();
        if (closingDate != null) {
            grantSubmissionHeader.setClosingDate(closingDate);
        }

        // set opening date unless null
        Calendar openingDate = s2sOpportunity.getOpeningDate();
        if (openingDate != null) {
            grantSubmissionHeader.setOpeningDate(openingDate);
        }
        String applicationXml = getXmlFromDocument(grantApplication);
        String hashVal = GrantApplicationHash.computeGrantFormsHash(applicationXml);

        HashValueDocument.HashValue hashValue = HashValueDocument.HashValue.Factory.newInstance();
        hashValue.setHashAlgorithm(S2SConstants.HASH_ALGORITHM);
        hashValue.setStringValue(hashVal);
        grantSubmissionHeader.setHashValue(hashValue);
        grantApplication.setGrantSubmissionHeader(grantSubmissionHeader);
        grantApplicationDocument.setGrantApplication(grantApplication);
        String schemaUrl = s2sOpportunity.getSchemaUrl();

        XmlCursor cursor = grantApplicationDocument.newCursor();

        cursor.toStartDoc();
        if (cursor.toFirstChild()){
            String defaultNameSpace = cursor.getName().getNamespaceURI();
            String schemaLocation = defaultNameSpace+ " "+schemaUrl;
            cursor.setAttributeText(new QName("http://www.w3.org/2001/XMLSchema-instance","schemaLocation"), schemaLocation);
        }

        return getXmlFromDocument(grantApplicationDocument);
    }

    private String getXmlFromDocument(XmlObject grantApplicationDocument) {
        String applicationXmlText = grantApplicationDocument
                .xmlText(s2SFormGeneratorService.getXmlOptionsPrefixes());
        String applicationXml = s2SUtilService.removeTimezoneFactor(applicationXmlText);
        return applicationXml;
    }



    /**
	 * 
	 * This method is to set the formObject to MetaGrants Forms Object. The
	 * xmlbeans Schema compiled with xsd:any does not provide a direct method to
	 * add individual forms to the Forms object. In this method, an XML Cursor
	 * is created from the existing Forms object and use the moveXml method to
	 * add the form object to the Forms object.
	 * 
	 * @param forms
	 *            Forms object to which the grants.gov form is added.
	 * @param formObject
	 *            xml object representing the grants.gov form.
	 */
	protected void setFormObject(Forms forms, XmlObject formObject) {
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
	 * Setter for {@link S2SFormGeneratorService}
	 * 
	 * @param s2SFormGeneratorService
	 */
	public void setS2SFormGeneratorService(
			S2SFormGeneratorService s2SFormGeneratorService) {
		this.s2SFormGeneratorService = s2SFormGeneratorService;
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
	 * Gets the s2SValidatorService attribute.
	 * 
	 * @return Returns the s2SValidatorService.
	 */
	public S2SValidatorService getS2SValidatorService() {
		return s2SValidatorService;
	}

	/**
	 * Sets the s2SValidatorService attribute value.
	 * 
	 * @param validatorService
	 *            The s2SValidatorService to set.
	 */
	public void setS2SValidatorService(S2SValidatorService validatorService) {
		s2SValidatorService = validatorService;
	}

    public NarrativeService getNarrativeService() {
        return narrativeService;
    }

    public void setNarrativeService(NarrativeService narrativeService) {
        this.narrativeService = narrativeService;
    }

    public S2SUtilService getS2SUtilService() {
        return s2SUtilService;
    }

    public void setS2SUtilService(S2SUtilService s2SUtilService) {
        this.s2SUtilService = s2SUtilService;
    }

    public FormMappingService getFormMappingService() {
        return formMappingService;
    }

    public void setFormMappingService(FormMappingService formMappingService) {
        this.formMappingService = formMappingService;
    }


}
