/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.propdev.impl.copy;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocValue;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.person.PropAwardPersonRole;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.NarrativeUserRights;
import org.kuali.coeus.propdev.impl.abstrct.ProposalAbstract;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.docperm.ProposalRoleTemplateService;
import org.kuali.coeus.propdev.impl.location.ProposalSite;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPersonUnit;
import org.kuali.coeus.propdev.impl.person.ProposalPersonYnq;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.coeus.propdev.impl.person.creditsplit.ProposalUnitCreditSplit;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.propdev.impl.questionnaire.ProposalDevelopmentModuleQuestionnaireBean;
import org.kuali.coeus.propdev.impl.s2s.*;
import org.kuali.coeus.propdev.impl.s2s.question.ProposalDevelopmentS2sModuleQuestionnaireBean;
import org.kuali.coeus.propdev.impl.state.ProposalState;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.bo.*;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.hierarchy.HierarchyStatusConstants;
import org.kuali.coeus.propdev.impl.person.KeyPersonnelService;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.bo.*;
import org.kuali.rice.krad.data.CopyOption;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.util.KRADPropertyConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * The Proposal Copy Service creates a new Proposal Development Document
 * based upon a current document and criteria specified by a user.
 * 
 * The service uses the following steps in order to copy a proposal:
 * <ol>
 * <li>The Document Service is used to create a new Proposal Development
 *     Document.  By having a new document, its initiator and timestamp
 *     are set correctly and all workflow information is in its initial
 *     state, e.g.  there are no adhoc routes.
 * </li>
 * <li>Most of the properties declared within the ProposalDevelopmentDocument
 *     are copied from the original document to the new document.  Some
 *     properties, such as <b>proposalNumber</b> are filtered out, i.e.
 *     they are not copied during this phase.<br><br>
 *     
 *     The copying of the properties is using eclipseLinks's built in copy functionality.
 * </li>
 * <li>The Document Overview properties are copied.  These are the
 *     description, explanation, and organization doc number fields
 *     on the document.  Since they belong to the base document class,
 *     they are not copied in step 2.
 * </li>
 * <li>The LeadUnit is set according to a user's selection.
 * </li>
 * <li>If the attachments are included, they are then copied.  This
 *     includes copying the contents of the attachment.
 * </li>
 * <li>If the budget is included, it is then copied.
 * </li>
 * <li>The document is saved to the database.
 * </li>
 * </ul>
 *
 * The <b>ProposalCopyCriteria</b> contains the user specified criteria, e.g. whether 
 * or not to copy attachments, etc.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@Component("proposalCopyService")
public class ProposalCopyServiceImpl implements ProposalCopyService {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalCopyServiceImpl.class);

    @Autowired
    @Qualifier("questionnaireAnswerService")
    private QuestionnaireAnswerService questionnaireAnswerService;
    
    @Autowired
    @Qualifier("kualiRuleService")
    private KualiRuleService kualiRuleService;
    
	@Autowired
    @Qualifier("unitService")
    private UnitService unitService;
    
	@Autowired
    @Qualifier("kcAuthorizationService")
	private KcAuthorizationService kcAuthorizationService;

	@Autowired
    @Qualifier("keyPersonnelService")
	private KeyPersonnelService keyPersonnelService;
	
	@Autowired
    @Qualifier("documentService")
	private DocumentService documentService;
	
	@Autowired
    @Qualifier("parameterService")
	private ParameterService parameterService;
	
	@Autowired
    @Qualifier("dateTimeService")
	private DateTimeService dateTimeService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Autowired
    @Qualifier("proposalRoleTemplateService")
    private ProposalRoleTemplateService proposalRoleTemplateService;


    /**
     * This method makes a copy of the DevelopmentProposal and sets it on a newly created doc and returns
     * the new doc.
     * @param doc the proposal development document to copy.
     * @param criteria the user-specified criteria that controls various copy operations.
     * @return
     * @throws Exception
     */
    public ProposalDevelopmentDocument copyProposal(ProposalDevelopmentDocument doc, ProposalCopyCriteria criteria) {
        String newDocNbr = null;
        ProposalDevelopmentDocument newDoc = null;
        LOG.info("STARTING PROPOSAL COPY");
        // check any business rules
        boolean rulePassed = getKualiRuleService().applyRules(new CopyProposalEvent(doc, criteria));
        
        if (rulePassed) {

            try {
                newDoc = createNewProposal(doc, criteria);


                copyProposal(doc, newDoc);

                // add the lead unit specified by user
                setLeadUnit(newDoc, criteria.getLeadUnitNumber());

                clearNarrativeUserRights(newDoc);

                copyNotes(doc, newDoc);

                List<ProposalAbstract>abstracts = getAbstracts(newDoc);

                if (criteria.getIncludeBudget()) {
                    removeBudgets(doc, newDoc, criteria.getBudgetVersions());
                    setBudgetVersionsToIncomplete(newDoc);
                }

                newDoc.getDevelopmentProposal().setS2sOpportunity(null);

                // all copied proposals should always be in progress.
                newDoc.getDevelopmentProposal().setProposalStateTypeCode(ProposalState.IN_PROGRESS);

                newDoc = (ProposalDevelopmentDocument) getDocumentService().saveDocument(newDoc);

                newDoc.getDevelopmentProposal().setProposalAbstracts(abstracts);

                modifyNewProposal(doc, newDoc, criteria);

                getDocumentService().saveDocument(newDoc);

                // Can't initialize authorization until a proposal is saved
                // and we have a new proposal number.
                initializeAuthorization(newDoc);

            } catch (Exception e) {
                    throw new RuntimeException("An error occured while trying to copy the proposal development document.", e);
            }

        }

        LOG.debug("New proposal generated...");
        return newDoc;
    }

    private void copyNotes(ProposalDevelopmentDocument doc, ProposalDevelopmentDocument newDoc) {
        List<Note> notes = doc.getNotes();
        List<Note> newNotes = new ArrayList<Note>();
        for (Note note : notes) {
            Note noteCopy = getDataObjectService().copyInstance(note, CopyOption.RESET_PK_FIELDS, CopyOption.RESET_VERSION_NUMBER, CopyOption.RESET_OBJECT_ID);
            // you should not need to do this since the version number should have been reset but for some reason it is not and an OLE is thrown if we do not explicitly set.
            noteCopy.setVersionNumber(0L);
            newNotes.add(noteCopy);
        }
        newDoc.setNotes(newNotes);
    }

    protected List<ProposalAbstract> getAbstracts(ProposalDevelopmentDocument newDoc) {
        List<ProposalAbstract> abstracts = newDoc.getDevelopmentProposal().getProposalAbstracts();
        newDoc.getDevelopmentProposal().setProposalAbstracts(null);
        return abstracts;
    }


    private void clearNarrativeUserRights(ProposalDevelopmentDocument newDoc) {
        for (Narrative narrative : newDoc.getDevelopmentProposal().getNarratives()) {
            narrative.setNarrativeUserRights(new ArrayList<NarrativeUserRights>());
        }

        for (Narrative narrative : newDoc.getDevelopmentProposal().getInstituteAttachments()) {
            narrative.setNarrativeUserRights(new ArrayList<NarrativeUserRights>());
        }
    }

    protected void copyQuestionnaire(ProposalDevelopmentDocument oldDoc, ProposalDevelopmentDocument newDoc) {
        ModuleQuestionnaireBean moduleQuestionnaireBean = new ProposalDevelopmentModuleQuestionnaireBean(oldDoc.getDevelopmentProposal(), true);
        ModuleQuestionnaireBean destModuleQuestionnaireBean = new ProposalDevelopmentModuleQuestionnaireBean(newDoc.getDevelopmentProposal(), false);
        getQuestionnaireAnswerService().copyAnswerHeaders(moduleQuestionnaireBean, destModuleQuestionnaireBean);

        //also copy the s2s questionnaires/answers too
        moduleQuestionnaireBean = new ProposalDevelopmentS2sModuleQuestionnaireBean(oldDoc.getDevelopmentProposal());
        destModuleQuestionnaireBean = new ProposalDevelopmentS2sModuleQuestionnaireBean(newDoc.getDevelopmentProposal());
        getQuestionnaireAnswerService().copyAnswerHeaders(moduleQuestionnaireBean, destModuleQuestionnaireBean);
    }


    protected void removeAttachments(ProposalDevelopmentDocument newDoc) {
        newDoc.getDevelopmentProposal().setNarratives(new ArrayList<Narrative>());
        newDoc.getDevelopmentProposal().setInstituteAttachments(new ArrayList<Narrative>());
        newDoc.getDevelopmentProposal().setProposalAbstracts(new ArrayList<ProposalAbstract>());
        newDoc.getDevelopmentProposal().setPropPersonBios(new ArrayList<ProposalPersonBiography>());
    }

    /**
     * Create a new proposal based upon a source proposal.  Only copy over the
     * properties necessary for the initial creation of the proposal.  This will
     * give us the proposal number to use when copying over the remainder of the
     * proposal.
     * @param srcDoc
     * @param criteria
     * @return
     * @throws Exception
     */
    protected ProposalDevelopmentDocument createNewProposal(ProposalDevelopmentDocument srcDoc, ProposalCopyCriteria criteria) throws Exception {
        
        ProposalDevelopmentDocument newDoc = (ProposalDevelopmentDocument) getDocumentService().getNewDocument(srcDoc.getClass());
        
        LOG.info("Copying Proposal ....");
        
        // Copy over the document overview properties.
        copyOverviewProperties(srcDoc, newDoc);
        
        copyRequiredProperties(srcDoc, newDoc);
        
        // Set lead unit.
        setLeadUnit(newDoc, criteria.getLeadUnitNumber());
        
        newDoc.getDocumentHeader().setDocumentTemplateNumber(srcDoc.getDocumentNumber());

        return newDoc;
    }
    
    /**
     * Copy over the required properties so we can do an initial save of the document
     * in order to obtain a proposal number.
     * @param oldDoc
     * @param newDoc
     */
    protected void copyRequiredProperties(ProposalDevelopmentDocument oldDoc, ProposalDevelopmentDocument newDoc) {
        DevelopmentProposal srcDevelopmentProposal = oldDoc.getDevelopmentProposal();
        DevelopmentProposal destDevelopmentProposal = newDoc.getDevelopmentProposal();
        
        newDoc.getDocumentHeader().setDocumentDescription(oldDoc.getDocumentHeader().getDocumentDescription());
        destDevelopmentProposal.setProposalTypeCode(srcDevelopmentProposal.getProposalTypeCode());
        destDevelopmentProposal.setActivityTypeCode(srcDevelopmentProposal.getActivityTypeCode());
        destDevelopmentProposal.setTitle(srcDevelopmentProposal.getTitle());
        destDevelopmentProposal.setSponsorCode(srcDevelopmentProposal.getSponsorCode());
        destDevelopmentProposal.setRequestedStartDateInitial(srcDevelopmentProposal.getRequestedStartDateInitial());
        destDevelopmentProposal.setRequestedEndDateInitial(srcDevelopmentProposal.getRequestedEndDateInitial());
        
        destDevelopmentProposal.getApplicantOrganization().setLocationName(srcDevelopmentProposal.getApplicantOrganization().getLocationName());
        destDevelopmentProposal.getApplicantOrganization().setSiteNumber(srcDevelopmentProposal.getApplicantOrganization().getSiteNumber());
        destDevelopmentProposal.getPerformingOrganization().setLocationName(srcDevelopmentProposal.getPerformingOrganization().getLocationName());
        destDevelopmentProposal.getPerformingOrganization().setSiteNumber(srcDevelopmentProposal.getPerformingOrganization().getSiteNumber());
        
        if (isProposalTypeRenewalRevisionContinuation(srcDevelopmentProposal.getProposalTypeCode())) {
            destDevelopmentProposal.setSponsorProposalNumber(srcDevelopmentProposal.getSponsorProposalNumber());
        }

        copyCustomDataFromDocument(oldDoc, newDoc);
    }

    /**
     * This method copies all custom data from one document to another.
     * @param src
     * @param dest
     */
    protected void copyCustomDataFromDocument(ProposalDevelopmentDocument src, ProposalDevelopmentDocument dest) {
        for (Map.Entry<String, CustomAttributeDocument> entry: src.getCustomAttributeDocuments().entrySet()) {
            // Find the attribute value
            CustomAttributeDocument customAttributeDocument = entry.getValue();
            if(customAttributeDocument.isActive()) {
                Map<String, Object> primaryKeys = new HashMap<String, Object>();
                primaryKeys.put(KRADPropertyConstants.DOCUMENT_NUMBER, src.getDocumentNumber());
                primaryKeys.put(Constants.CUSTOM_ATTRIBUTE_ID, customAttributeDocument.getId());
                List<CustomAttributeDocValue> customAttributeDocValues = (List<CustomAttributeDocValue>) getDataObjectService().findMatching(CustomAttributeDocValue.class,
                        QueryByCriteria.Builder.andAttributes(primaryKeys).build()).getResults();
                if (!CollectionUtils.isEmpty(customAttributeDocValues)) {
                    CustomAttributeDocValue customAttributeDocValue = customAttributeDocValues.get(0);

                    // Store a new CustomAttributeDocValue using the new document's document number
                    CustomAttributeDocValue newDocValue = new CustomAttributeDocValue();
                    newDocValue.setDocumentNumber(dest.getDocumentNumber());
                    newDocValue.setId(customAttributeDocument.getId().longValue());
                    newDocValue.setValue(customAttributeDocValue.getValue());
                    dest.getCustomDataList().add(newDocValue);
                    newDocValue.setValue(customAttributeDocValue == null ? customAttributeDocument.getCustomAttribute().getDefaultValue() : customAttributeDocValue.getValue());
                }
            }
        }
    }

    protected void fixS2sUserAttachedForms(ProposalDevelopmentDocument newDoc) {
        DevelopmentProposal developmentProposal = newDoc.getDevelopmentProposal();
        List<S2sUserAttachedForm> userAttachedForms = developmentProposal.getS2sUserAttachedForms();
        for (S2sUserAttachedForm s2sUserAttachedForm : userAttachedForms) {
            s2sUserAttachedForm.setProposalNumber(newDoc.getDevelopmentProposal().getProposalNumber());
            s2sUserAttachedForm.setDevelopmentProposal(developmentProposal);
            List<S2sUserAttachedFormAtt> attachments = s2sUserAttachedForm.getS2sUserAttachedFormAtts();
            for (S2sUserAttachedFormAtt s2sUserAttachedFormAtt : attachments) {
                s2sUserAttachedFormAtt.setProposalNumber(newDoc.getDevelopmentProposal().getProposalNumber());
            }
        }
    }

    /**
     * Copies over all Proposal Development Document properties.
     * Only the properties declared within the DevelopmentProposal
     * class are copied.  Properties from parent classes are not copied.
     *
     * @param oldDoc the source proposal development document, i.e. the original.
     * @param newDoc the destination proposal development document, i.e. the new document.
     * @throws Exception if the copy fails for any reason.
     */
    protected void copyProposal(ProposalDevelopmentDocument oldDoc, ProposalDevelopmentDocument newDoc)  throws Exception {

        //We need to copy DocumentNextValues to properly handle copied collections
        fixNextValues(oldDoc, newDoc);
        
        DevelopmentProposal copy = deepCopy(oldDoc.getDevelopmentProposal());

        newDoc.setDevelopmentProposal(copy);

        copy.setProposalDocument(newDoc);

    }

    protected DevelopmentProposal deepCopy(DevelopmentProposal src) throws Exception {
        return getDataObjectService().copyInstance(src, CopyOption.RESET_PK_FIELDS, CopyOption.RESET_VERSION_NUMBER, CopyOption.RESET_OBJECT_ID );
    }
    
    /**
     * The document next values must be the same in the new version as in
     * the old document.  Note that the next document values must be assigned
     * the document number of the new version.
     * @param oldDoc
     * @param newDoc
     */
    protected void fixNextValues(ProposalDevelopmentDocument oldDoc, ProposalDevelopmentDocument newDoc) {
        List<DocumentNextvalue> newNextValues = new ArrayList<DocumentNextvalue>();
        List<DocumentNextvalue> oldNextValues = oldDoc.getDocumentNextvalues();
        for (DocumentNextvalue oldNextValue : oldNextValues) {
            DocumentNextvalue newNextValue = new DocumentNextvalue();
            newNextValue.setPropertyName(oldNextValue.getPropertyName());
            newNextValue.setNextValue(oldNextValue.getNextValue());
            newNextValue.setDocumentKey(newDoc.getDocumentNumber());
            newNextValues.add(newNextValue);
        }
        newDoc.setDocumentNextvalues(newNextValues);
    }
    
    /**
     * Copies the document overview properties.  These properties are the
     * Description, Explanation, and Organization Document Number.
     * 
     * @param src the source proposal development document, i.e. the original.
     * @param dest the destination proposal development document, i.e. the new document.
     */
    protected void copyOverviewProperties(ProposalDevelopmentDocument src, ProposalDevelopmentDocument dest) {
        DocumentHeader srcHdr = src.getDocumentHeader();
        DocumentHeader destHdr = dest.getDocumentHeader();

        destHdr.setDocumentDescription(srcHdr.getDocumentDescription());
        destHdr.setExplanation(srcHdr.getExplanation());
        destHdr.setOrganizationDocumentNumber(srcHdr.getOrganizationDocumentNumber());
    }

    /**
     * Set the lead unit for the new proposal.
     * @param doc the new proposal development document
     * @param newLeadUnitNumber the new lead unit number
     */
    protected void setLeadUnit(ProposalDevelopmentDocument doc, String newLeadUnitNumber) {
        Unit newLeadUnit = getUnitService().getUnit(newLeadUnitNumber);
        doc.getDevelopmentProposal().setOwnedByUnitNumber(newLeadUnitNumber);
        doc.getDevelopmentProposal().setOwnedByUnit(newLeadUnit);
    }

    /**
     * Once the proposal is copied, we need to make changes based on the lead unit
     * and change other properties to make the proposal usable.
     * @param srcDoc
     * @param newDoc
     * @param criteria
     * @throws Exception
     */
    protected void modifyNewProposal(ProposalDevelopmentDocument srcDoc, ProposalDevelopmentDocument newDoc, ProposalCopyCriteria criteria) throws Exception {

         // Fixing the title
        newDoc.setDefaultDocumentDescription();

        cleanupHierarchy(newDoc);

        changeKeyPersonnelUnits(newDoc, srcDoc.getDevelopmentProposal().getOwnedByUnitNumber(), criteria.getLeadUnitNumber());


        if (!StringUtils.equals(srcDoc.getDevelopmentProposal().getUnitNumber(), newDoc.getDevelopmentProposal().getUnitNumber())) {
            changeOrganizationAndLocations(newDoc);
        }

        setPreviousGrantsGovTrackingId(srcDoc.getDevelopmentProposal().getProposalNumber(), newDoc);


        /* update timestamp and set the PK on abstracts because proposal copy clears the PK out, there is no anonymous access and
           proposalNUmber is not available before save, so have to get it now and add.
         */
        setPkAndNewUpdateTimestampOnAbstracts(newDoc);

        // handle user rights now since proposalNumber has been generated.
        if (!criteria.getIncludeAttachments()) {
            removeAttachments(newDoc);
        } else {
            modifyNarrativesStatus(srcDoc.getDevelopmentProposal(), newDoc.getDevelopmentProposal());
            modifyAttachmentPermissions(srcDoc.getDevelopmentProposal(), newDoc.getDevelopmentProposal());
        }

        if (criteria.getIncludeQuestionnaire()) {
            // handle questionnaire
            copyQuestionnaire(srcDoc, newDoc);

        }

        copyOpportunity(newDoc, srcDoc);

        fixS2sUserAttachedForms(newDoc);

    }

    /*
    The reset PK fields does not work well for composite PKs and this particular case, the mapping between S2SOpportunity and S2Sforms is not straightforward.
    Hence have to do the following for the PK to be handled right.
     */
    protected void copyOpportunity(ProposalDevelopmentDocument newDoc, ProposalDevelopmentDocument srcDoc) {
        if (srcDoc.getDevelopmentProposal().getS2sOpportunity() != null) {
            S2sOpportunity opportunity = getDataObjectService().copyInstance(srcDoc.getDevelopmentProposal().getS2sOpportunity(), CopyOption.RESET_PK_FIELDS, CopyOption.RESET_VERSION_NUMBER, CopyOption.RESET_OBJECT_ID);

            opportunity.setDevelopmentProposal(newDoc.getDevelopmentProposal());
            newDoc.getDevelopmentProposal().setS2sOpportunity(opportunity);

            for (S2sOppForms form : opportunity.getS2sOppForms()) {
                form.getS2sOppFormsId().setProposalNumber(newDoc.getDevelopmentProposal().getProposalNumber());
            }
        }
    }

    protected void modifyAttachmentPermissions(DevelopmentProposal oldProposal, DevelopmentProposal copiedProposal) {
        List<Narrative> narratives = copiedProposal.getNarratives();
        List<Narrative> oldNarratives = oldProposal.getNarratives();
        for (int narrativeNumber = 0; narrativeNumber < narratives.size(); narrativeNumber++) {
            setPersonNarrativePermission(globalVariableService.getUserSession().getPrincipalId(), narratives.get(narrativeNumber), oldNarratives.get(narrativeNumber), copiedProposal.getProposalNumber());
        }

        List<Narrative> instituteAttachments = copiedProposal.getInstituteAttachments();
        List<Narrative> oldInstituteAttachments = oldProposal.getInstituteAttachments();
        for (int narrativeNumber = 0; narrativeNumber < narratives.size(); narrativeNumber++) {
            setPersonNarrativePermission(globalVariableService.getUserSession().getPrincipalId(), narratives.get(narrativeNumber), oldNarratives.get(narrativeNumber), copiedProposal.getProposalNumber());
        }

        }


    protected void modifyNarrativesStatus(DevelopmentProposal oldProposal, DevelopmentProposal copiedProposal) {
        List<Narrative> narratives = copiedProposal.getNarratives();
        for (int narrativeNumber = 0; narrativeNumber < narratives.size(); narrativeNumber++) {
            if (StringUtils.equals(narratives.get(narrativeNumber).getNarrativeType().getNarrativeTypeGroup(), Constants.PROPOSAL_NARRATIVE_TYPE_GROUP_CODE))
                narratives.get(narrativeNumber).setModuleStatusCode(Constants.NARRATIVE_MODULE_STATUS_INCOMPLETE);
            else
                narratives.get(narrativeNumber).setModuleStatusCode(Constants.NARRATIVE_MODULE_STATUS_COMPLETE);
        }

    }

    protected void setPersonNarrativePermission(String personId, Narrative narrative, Narrative oldNarrative, String newProposalNumber) {
        List<NarrativeUserRights> newRights = narrative.getNarrativeUserRights();
        List<NarrativeUserRights> oldRights = oldNarrative.getNarrativeUserRights();

        //remove the user performing the copy from the narrative permissions in case they had permissions other than modify.
        //they will default to modify during the copy as they are the aggregator of the new document.
        for (int rightsNumber = 0; rightsNumber < newRights.size(); rightsNumber++) {
            if (oldRights.get(rightsNumber).getUserId() != personId) {
                newRights.get(rightsNumber).setModuleNumber(oldRights.get(rightsNumber).getModuleNumber());
                newRights.get(rightsNumber).setPersonName(oldRights.get(rightsNumber).getPersonName());
                newRights.get(rightsNumber).setUserId(oldRights.get(rightsNumber).getUserId());
                newRights.get(rightsNumber).setProposalNumber(newProposalNumber);
            }
        }
    }

    protected void setPkAndNewUpdateTimestampOnAbstracts(ProposalDevelopmentDocument newDoc) {
        for (ProposalAbstract curAbstract : newDoc.getDevelopmentProposal().getProposalAbstracts()) {
            curAbstract.setTimestampDisplay(new java.sql.Timestamp(new java.util.Date().getTime()));
            curAbstract.setAbstractTypeCode(curAbstract.getAbstractType().getCode());
            curAbstract.setProposalNumber(newDoc.getDevelopmentProposal().getProposalNumber());
        }
    }


    protected void setPreviousGrantsGovTrackingId(String oldProposalNumber, ProposalDevelopmentDocument newDoc) {
        Map<String, Object> keyMap = new HashMap<String, Object>();
        keyMap.put("proposalNumber", oldProposalNumber);
        List<S2sAppSubmission> s2sAppSubmissionProposalList = (List<S2sAppSubmission>) getDataObjectService().findMatching(S2sAppSubmission.class,
                QueryByCriteria.Builder.andAttributes(keyMap).build()).getResults();

        newDoc.getDevelopmentProposal().setS2sAppSubmission(new ArrayList<S2sAppSubmission>());
        for(S2sAppSubmission s2sAppSubmissionListValue:s2sAppSubmissionProposalList) {
            newDoc.getDevelopmentProposal().setPrevGrantsGovTrackingID(s2sAppSubmissionListValue.getGgTrackingId());
        }
    }

    protected void cleanupHierarchy(ProposalDevelopmentDocument newDoc) {
        // if proposal is parent, users want to be able to copy it and retain parent status so they can retain all data but still
        // link children, otherwise
        // this becomes an unlinked proposal and not all data syncs up to parent.
        if (!StringUtils.equals(newDoc.getDevelopmentProposal().getHierarchyStatus(), HierarchyStatusConstants.Parent.code()))
        {
            newDoc.getDevelopmentProposal().setHierarchyStatus(HierarchyStatusConstants.None.code());
        }
        newDoc.getDevelopmentProposal().setHierarchyParentProposalNumber(null);
        newDoc.getDevelopmentProposal().setHierarchyLastSyncHashCode(null);
    }

    /**
     * If the Lead Unit has changed in the previous {@link Document}, then this method corrects related
     * properties {@link Organization} and {@link org.kuali.coeus.propdev.impl.location.ProposalSite} instances
     *
     * @param doc {@link ProposalDevelopmentDocument} to fix
     */
    protected void changeOrganizationAndLocations(ProposalDevelopmentDocument doc) {
        DevelopmentProposal developmentProposal = doc.getDevelopmentProposal();
        
        Unit ownedByUnit = developmentProposal.getOwnedByUnit();
        if (ownedByUnit != null) {
            String unitOrganizationId = ownedByUnit.getOrganizationId();
            for (ProposalSite proposalSite: developmentProposal.getProposalSites()) {
                // set location name to default from Organization
                proposalSite.setOrganizationId(unitOrganizationId);
                proposalSite.refreshReferenceObject("organization");
                proposalSite.setLocationName(proposalSite.getOrganization().getOrganizationName());
                proposalSite.setRolodexId(proposalSite.getOrganization().getContactAddressId());
                proposalSite.refreshReferenceObject("rolodex");
                proposalSite.initializeDefaultCongressionalDistrict();
            }
        }
    }

    /**
     * Fix data related to Budget Versions.
     * @param doc the proposal development document
     */
    protected void setBudgetVersionsToIncomplete(ProposalDevelopmentDocument doc) {
        if (doc.getDevelopmentProposal().getBudgets().size() > 0) {
            String budgetStatusIncompleteCode = getParameterService().getParameterValueAsString(
                    Budget.class, Constants.BUDGET_STATUS_INCOMPLETE_CODE);
            
            doc.getDevelopmentProposal().setBudgetStatus(budgetStatusIncompleteCode);
        }
    }
    
    /**
     * Fix the Key Personnel.  This requires changing the lead unit for the PI
     * and the COIs to the new lead unit.  Also, if the PI's home unit is not in
     * the list, we must add it.
     * @param doc the proposal development document
     * @param oldLeadUnitNumber the old lead unit number
     * @param newLeadUnitNumber the new lead unit number
     * @throws Exception 
     */
    protected void changeKeyPersonnelUnits(ProposalDevelopmentDocument doc, String oldLeadUnitNumber, String newLeadUnitNumber) throws Exception {
       
        List<ProposalPerson> persons = doc.getDevelopmentProposal().getProposalPersons();
        for (ProposalPerson person : persons) {
            PropAwardPersonRole role = person.getRole();
            String roleId = role.getCode();
            
            if (StringUtils.equals(roleId, Constants.PRINCIPAL_INVESTIGATOR_ROLE)) {
                
                List<ProposalPersonUnit> proposalPersonUnits = person.getUnits();
                List<ProposalPersonUnit> newProposalPersonUnits = new ArrayList<ProposalPersonUnit>();
                // add credit splits for new units
                ProposalPersonUnit unit = createProposalPersonUnit(person, newLeadUnitNumber, true, false, proposalPersonUnits);
                newProposalPersonUnits.add(unit);
                
                String homeUnitNumber = person.getHomeUnit();
                if (!StringUtils.equals(newLeadUnitNumber, homeUnitNumber)) {
                    unit = createProposalPersonUnit(person, homeUnitNumber, false, true, proposalPersonUnits);
                    if (unit != null) {
                        newProposalPersonUnits.add(unit);
                    }
                }
                
                for (ProposalPersonUnit oldUnit : proposalPersonUnits) {
                    String oldUnitNumber = oldUnit.getUnitNumber();
                    if (!StringUtils.equals(newLeadUnitNumber, oldUnitNumber) &&
                        !StringUtils.equals(homeUnitNumber, oldUnitNumber) && 
                        !StringUtils.equals(oldLeadUnitNumber, oldUnitNumber)) {
                        
                        unit = createProposalPersonUnit(person, oldUnitNumber, false, true, proposalPersonUnits);
                        newProposalPersonUnits.add(unit);
                    }
                }
                
                person.setUnits(newProposalPersonUnits);  
            }
            
            for (ProposalPersonYnq ynq : person.getProposalPersonYnqs()) {
                ynq.setAnswer(null);
            }
        }

        getKeyPersonnelService().populateDocument(doc);
    }
    
    protected ProposalPersonUnit createProposalPersonUnit(ProposalPerson person, String unitNumber, boolean isLeadUnit, boolean isDeletable, List<ProposalPersonUnit> oldProposalPersonUnits) {
        ProposalPersonUnit proposalPersonUnit = getKeyPersonnelService().createProposalPersonUnit(unitNumber, person);
        if (proposalPersonUnit.getUnitNumber() == null) {
            return null;
        }
        proposalPersonUnit.setLeadUnit(isLeadUnit);
        proposalPersonUnit.setDelete(isDeletable);
        proposalPersonUnit.setVersionNumber(null);
        
        ProposalPersonUnit oldProposalPersonUnit = findProposalPersonUnit(unitNumber, oldProposalPersonUnits);
        if (oldProposalPersonUnit != null) {
            List<ProposalUnitCreditSplit> newUnitCreditSplits = new ArrayList<ProposalUnitCreditSplit>();
            List<ProposalUnitCreditSplit> oldUnitCreditSplits = oldProposalPersonUnit.getCreditSplits();
            for (ProposalUnitCreditSplit oldUnitCreditSplit : oldUnitCreditSplits) {
                ProposalUnitCreditSplit newUnitCreditSplit = getDataObjectService().copyInstance(oldUnitCreditSplit, CopyOption.RESET_OBJECT_ID, CopyOption.RESET_PK_FIELDS, CopyOption.RESET_VERSION_NUMBER);
                newUnitCreditSplits.add(newUnitCreditSplit);
            }
            proposalPersonUnit.setCreditSplits(newUnitCreditSplits);
        }
        proposalPersonUnit.setProposalPerson(person);
        return proposalPersonUnit;
    }
    
    protected ProposalPersonUnit findProposalPersonUnit(String unitNumber, List<ProposalPersonUnit> proposalPersonUnits) {
        for (ProposalPersonUnit proposalPersonUnit : proposalPersonUnits) {
            if (StringUtils.equals(unitNumber, proposalPersonUnit.getUnitNumber())) {
                return proposalPersonUnit;
            }
        }
        return null;
    }
    
    /**
     * Initialize the Authorizations for a new proposal.  The initiator/creator
     * is assigned the Aggregator role.
     * @param doc the proposal development document
     */
    protected void initializeAuthorization(ProposalDevelopmentDocument doc) {
        String userId = globalVariableService.getUserSession().getPrincipalId();
        if (!getKcAuthorizationService().hasDocumentLevelRole(userId, RoleConstants.AGGREGATOR, doc))
            getKcAuthorizationService().addDocumentLevelRole(userId, RoleConstants.AGGREGATOR, doc);

        // Add the users defined in the role templates for the proposal's lead unit
        getProposalRoleTemplateService().addUsers(doc);
    }

    /**
     * @param src the source proposal development document, i.e. the original.
     * @param dest the destination proposal development document, i.e. the new document.
     * @param budgetVersions
     */
    protected void removeBudgets(ProposalDevelopmentDocument src, ProposalDevelopmentDocument dest, String budgetVersions) throws Exception {
        if (budgetVersions.equals(ProposalCopyCriteria.BUDGET_FINAL_VERSION)) {
            ProposalDevelopmentBudgetExt finalBudgetVersion = (ProposalDevelopmentBudgetExt) src.getDevelopmentProposal().getFinalBudget();
            if (finalBudgetVersion != null) {
                ArrayList<ProposalDevelopmentBudgetExt> budgets = (ArrayList<ProposalDevelopmentBudgetExt>) src.getDevelopmentProposal().getBudgets();
                ProposalDevelopmentBudgetExt finalBudget = src.getDevelopmentProposal().getFinalBudget();
                for (int budgetVersionNumber = 0; budgetVersionNumber < budgets.size(); budgetVersionNumber++) {
                    if (budgets.get(budgetVersionNumber).getBudgetVersionNumber() != finalBudget.getBudgetVersionNumber()) {
                        budgets.remove(budgetVersionNumber);
                    }
                }
            }
        }
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

	protected DocumentService getDocumentService() {
		return documentService;
	}

	public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
	
	
    /**
     * Set the Key Personnel Service.  It is set via dependency injection.
     * 
     * @param keyPersonnelService the Key Personnel Service
     */
    public void setKeyPersonnelService(KeyPersonnelService keyPersonnelService) {
        this.keyPersonnelService = keyPersonnelService;
    }
    
    protected KeyPersonnelService getKeyPersonnelService() {
		return keyPersonnelService;
	}

    protected KcAuthorizationService getKcAuthorizationService() {
		return kcAuthorizationService;
	}

	public void setKcAuthorizationService(KcAuthorizationService kcAuthorizationService) {
		this.kcAuthorizationService = kcAuthorizationService;
	}

    /**
     * Get the Kuali Rule Service.
     * 
     * @return the Kuali Rule Service
     */
    protected KualiRuleService getKualiRuleService() {
        return kualiRuleService;
    }
    
    public void setKualiRuleService(KualiRuleService kualiRuleService) {
  		this.kualiRuleService = kualiRuleService;
  	}
    
    protected UnitService getUnitService() {
  		return unitService;
  	}

	public void setUnitService(UnitService unitService) {
  		this.unitService = unitService;
  	}

    /**
     * Gets the questionnaireAnswerService attribute. 
     * @return Returns the questionnaireAnswerService.
     */
    protected QuestionnaireAnswerService getQuestionnaireAnswerService() {
        return questionnaireAnswerService;
    }

    /**
     * Sets the questionnaireAnswerService attribute value.
     * @param questionnaireAnswerService The questionnaireAnswerService to set.
     */
    public void setQuestionnaireAnswerService(QuestionnaireAnswerService questionnaireAnswerService) {
        this.questionnaireAnswerService = questionnaireAnswerService;
    }

    /**
     * Is the Proposal Type set to Renewal, Revision, or a Continuation?
     * @param proposalTypeCode proposal type code
     * @return true or false
     */
    protected boolean isProposalTypeRenewalRevisionContinuation(String proposalTypeCode) {
        String proposalTypeCodeRenewal = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_RENEWAL);
        String proposalTypeCodeRevision = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_REVISION);
        String proposalTypeCodeContinuation = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_CONTINUATION);
         
        return !StringUtils.isEmpty(proposalTypeCode) &&
               (proposalTypeCode.equals(proposalTypeCodeRenewal) ||
                proposalTypeCode.equals(proposalTypeCodeRevision) ||
                proposalTypeCode.equals(proposalTypeCodeContinuation));
    }

    /**
     * Sets the ParameterService.
     * @param parameterService the parameter service. 
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    protected  ParameterService getParameterService() {
        return parameterService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public void setProposalRoleTemplateService(ProposalRoleTemplateService proposalRoleTemplateService) {
        this.proposalRoleTemplateService = proposalRoleTemplateService;
    }

    public ProposalRoleTemplateService getProposalRoleTemplateService() {
        return proposalRoleTemplateService;
    }


}
