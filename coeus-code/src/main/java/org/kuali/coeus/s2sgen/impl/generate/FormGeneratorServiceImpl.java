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
package org.kuali.coeus.s2sgen.impl.generate;

import gov.grants.apply.system.globalV10.HashValueDocument;
import gov.grants.apply.system.headerV10.GrantSubmissionHeaderDocument;
import gov.grants.apply.system.metaGrantApplication.GrantApplicationDocument;
import gov.grants.apply.system.metaGrantApplication.GrantApplicationDocument.GrantApplication.Forms;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;
import org.kuali.coeus.propdev.api.s2s.*;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.api.core.InfastructureConstants;
import org.kuali.coeus.s2sgen.api.core.S2SException;

import org.kuali.coeus.propdev.api.attachment.NarrativeService;
import org.kuali.coeus.s2sgen.api.generate.*;
import org.kuali.coeus.s2sgen.api.hash.GrantApplicationHashService;
import org.kuali.coeus.s2sgen.impl.datetime.S2SDateTimeService;
import org.kuali.coeus.s2sgen.impl.validate.S2SValidatorService;
import org.kuali.coeus.s2sgen.api.core.AuditError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;
import java.util.*;

@Component("formGeneratorService")
public class FormGeneratorServiceImpl implements FormGeneratorService {
	private static final Log LOG = LogFactory.getLog(FormGeneratorServiceImpl.class);

    @Autowired
    @Qualifier("s2SFormGeneratorRetrievalService")
	private S2SFormGeneratorRetrievalService s2SFormGeneratorService;

    @Autowired
    @Qualifier("s2SValidatorService")
    private S2SValidatorService s2SValidatorService;

    @Autowired
    @Qualifier("narrativeService")
    private NarrativeService narrativeService;

    @Autowired
    @Qualifier("formMappingService")
    private FormMappingService formMappingService;

    @Autowired
    @Qualifier("s2SDateTimeService")
    private S2SDateTimeService s2SDateTimeService;

    @Autowired
    @Qualifier("grantApplicationHashService")
    private GrantApplicationHashService grantApplicationHashService;

	/**
	 * 
	 * This method is used to validate application before submission.
	 * 
	 * @param pdDoc
	 *            Proposal Development Document.
	 * @throws S2SException
	 */
    @Override
	public FormValidationResult validateForms(
            Object pdDoc)
			throws S2SException {

        if (pdDoc == null) {
            throw new IllegalArgumentException("pdDoc is null");
        }

        ProposalDevelopmentDocumentContract pdDocContract = (ProposalDevelopmentDocumentContract) pdDoc;

        return generateAndValidateForms(null, null,
                pdDocContract);
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
	 * @throws S2SException
	 */
	protected FormGenerationResult generateAndValidateForms(Forms forms,
			List<AttachmentData> attList, ProposalDevelopmentDocumentContract pdDoc) throws S2SException {
		boolean validationSucceeded = true;
		DevelopmentProposalContract developmentProposal = pdDoc.getDevelopmentProposal();
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
            FormMappingInfo info = formMappingService.getFormInfo(opportunityForm.getOppNameSpace(),developmentProposal.getProposalNumber());
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
        FormGenerationResult result = new FormGenerationResult();
        result.setValid(validationSucceeded);
        result.setErrors(auditErrors);
        result.setAttachments(attList);
		return result;
	}

    @Override
    public FormGenerationResult generateAndValidateForms(Object pdDoc) throws S2SException {

        if (pdDoc == null) {
            throw new IllegalArgumentException("pdDoc is null");
        }

        ProposalDevelopmentDocumentContract pdDocContract = (ProposalDevelopmentDocumentContract) pdDoc;

        GrantApplicationDocument.GrantApplication.Forms forms = GrantApplicationDocument.GrantApplication.Forms.Factory.newInstance();
        List<AttachmentData> attList = new ArrayList<AttachmentData>();
        final FormGenerationResult result = generateAndValidateForms(forms, attList, pdDocContract);
        if (result.isValid()) {
            String applicationXml = getGrantApplicationDocument(pdDocContract,forms);
            result.setApplicationXml(applicationXml);
        }
        return result;
    }

    /**
     *
     * This method populates values for {@link GrantApplicationDocument} for a
     * given {@link ProposalDevelopmentDocumentContract}
     *
     * @param pdDoc
     *            {@link ProposalDevelopmentDocumentContract}
     * @param forms
     *            {@link gov.grants.apply.system.metaGrantApplication.GrantApplicationDocument.GrantApplication.Forms} generated XML forms
     * @return {@link GrantApplicationDocument} populated with forms
     * @throws S2SException
     */
    protected String getGrantApplicationDocument(
            ProposalDevelopmentDocumentContract pdDoc, GrantApplicationDocument.GrantApplication.Forms forms) throws S2SException {
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
        grantSubmissionHeader.setSchemaVersion(FormVersion.v1_0.getVersion());
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
        String hashVal = grantApplicationHashService.computeGrantFormsHash(applicationXml);

        HashValueDocument.HashValue hashValue = HashValueDocument.HashValue.Factory.newInstance();
        hashValue.setHashAlgorithm(InfastructureConstants.HASH_ALGORITHM);
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
        String applicationXml = s2SDateTimeService.removeTimezoneFactor(applicationXmlText);
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
	 * Setter for {@link S2SFormGeneratorRetrievalService}
	 * 
	 * @param s2SFormGeneratorService
	 */
	public void setS2SFormGeneratorService(
			S2SFormGeneratorRetrievalService s2SFormGeneratorService) {
		this.s2SFormGeneratorService = s2SFormGeneratorService;
	}

	/**
	 * Gets the s2SFormGeneratorService attribute.
	 * 
	 * @return Returns the s2SFormGeneratorService.
	 */
	public S2SFormGeneratorRetrievalService getS2SFormGeneratorService() {
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

    public S2SDateTimeService getS2SDateTimeService() {
        return s2SDateTimeService;
    }

    public void setS2SDateTimeService(S2SDateTimeService s2SUtilService) {
        this.s2SDateTimeService = s2SDateTimeService;
    }

    public FormMappingService getFormMappingService() {
        return formMappingService;
    }

    public void setFormMappingService(FormMappingService formMappingService) {
        this.formMappingService = formMappingService;
    }

    public GrantApplicationHashService getGrantApplicationHashService() {
        return grantApplicationHashService;
    }

    public void setGrantApplicationHashService(GrantApplicationHashService grantApplicationHashService) {
        this.grantApplicationHashService = grantApplicationHashService;
    }
}
