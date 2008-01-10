/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.kra.proposaldevelopment.web.struts.form;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.kuali.core.bo.Parameter;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DataDictionaryService;
import org.kuali.core.util.ActionFormUtilMap;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.web.struts.form.KualiTransactionalDocumentFormBase;
import org.kuali.kra.bo.PersonEditableField;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.CreditSplit;
import org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.PropScienceKeyword;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria;
import org.kuali.kra.proposaldevelopment.bo.ProposalLocation;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonDegree;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;

/**
 * This class...
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentForm extends KualiTransactionalDocumentFormBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDevelopmentForm.class);
    private String primeSponsorName;
    private ProposalLocation newPropLocation;
    private ProposalSpecialReview newPropSpecialReview;
    private ProposalPerson newProposalPerson;
    private List<ProposalPersonDegree> newProposalPersonDegree;
    private List<Unit> newProposalPersonUnit;
    private String newRolodexId;
    private String newPersonId;
    private Narrative newNarrative;
    private FormFile narrativeFile;
    private Map personEditableFields;
    private boolean showMaintenanceLinks;
    private ProposalAbstract newProposalAbstract;
    private ProposalPersonBiography newPropPersonBio;
    private Narrative newInstituteAttachment;
    private boolean auditActivated;
    private ProposalCopyCriteria copyCriteria;
    private Map<String, Parameter> proposalDevelopmentParameters;
    private Integer answerYesNo;
    private Integer answerYesNoNA;

    /**
     * Used to indicate which result set we're using when refreshing/returning from a multi-value lookup
     */
    private String lookupResultsSequenceNumber;
    /**
     * The type of result returned by the multi-value lookup
     *
     * TODO: to be persisted in the lookup results service instead? See https://test.kuali.org/confluence/display/KULRNE/Using+multiple+value+lookups
     */
    private String lookupResultsBOClassName;

    public ProposalDevelopmentForm() {
        super();
        this.setDocument(new ProposalDevelopmentDocument());
        initialize();
    }

    /**
     *
     * This method initialize all form variables
     */
    public void initialize() {
        setNewPropLocation(new ProposalLocation());
        setNewPropSpecialReview(new ProposalSpecialReview());
        setNewNarrative(new Narrative());
        setNewProposalPerson(new ProposalPerson());
        setNewProposalPersonDegree(new ArrayList<ProposalPersonDegree>());
        setNewProposalPersonUnit(new ArrayList<Unit>());
        setNewProposalAbstract(new ProposalAbstract());
        setCopyCriteria(new ProposalCopyCriteria());
        DataDictionaryService dataDictionaryService = (DataDictionaryService) KraServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
        this.setHeaderNavigationTabs((dataDictionaryService.getDataDictionary().getDocumentEntry(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument.class.getName())).getHeaderTabNavigation());
        proposalDevelopmentParameters = new HashMap<String, Parameter>();
    }


    public ProposalDevelopmentDocument getProposalDevelopmentDocument() {
        return (ProposalDevelopmentDocument) this.getDocument();
    }

    @Override
    public void populate(HttpServletRequest request) {
        super.populate(request);
        ProposalDevelopmentDocument proposalDevelopmentDocument=getProposalDevelopmentDocument();

        proposalDevelopmentDocument.refreshReferenceObject("sponsor");

		// Temporary hack for KRACOEUS-489
        if (getActionFormUtilMap() instanceof ActionFormUtilMap) {
            ((ActionFormUtilMap) getActionFormUtilMap()).setCacheValueFinderResults(false);
        }

    }


    public ProposalLocation getNewPropLocation() {
        return newPropLocation;
    }


    public void setNewPropLocation(ProposalLocation newPropLocation) {
        this.newPropLocation = newPropLocation;
    }


    public ProposalSpecialReview getNewPropSpecialReview() {
        return newPropSpecialReview;
    }


    public void setNewPropSpecialReview(ProposalSpecialReview newPropSpecialReview) {
        this.newPropSpecialReview = newPropSpecialReview;
    }

    /**
     * Gets the new proposal abstract.  This is the abstract filled
     * in by the user on the form before pressing the add button. The
     * abstract can be invalid if the user has not specified an abstract type.
     *
     * @return the new proposal abstract
     */
    public ProposalAbstract getNewProposalAbstract() {
        return newProposalAbstract;
    }

    /**
     * Sets the new proposal abstract.  This is the abstract that will be
     * shown to the user on the form.
     *
     * @param newProposalAbstract
     */
    public void setNewProposalAbstract(ProposalAbstract newProposalAbstract) {
        this.newProposalAbstract = newProposalAbstract;
    }

    /* Reset method
     * @param mapping
     * @param request
     * reset check box values in keyword panel and properties that much be read on each request.
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.setCopyCriteria(new ProposalCopyCriteria());
       // following reset the tab stats and will load as default when it returns from lookup.
       // TODO : Do we really need this?
       // implemented headerTab in KraTransactionalDocumentActionBase
       //     this.setTabStates(new HashMap<String, String>());
        this.setCurrentTabIndex(0);


        ProposalDevelopmentDocument proposalDevelopmentDocument = this.getProposalDevelopmentDocument();
        List<PropScienceKeyword> keywords = proposalDevelopmentDocument.getPropScienceKeywords();
        for(int i=0; i<keywords.size(); i++) {
            PropScienceKeyword propScienceKeyword = (PropScienceKeyword)keywords.get(i);
            propScienceKeyword.setSelectKeyword(false);
        }
    }


    /**
     * Sets the primeSponsorName attribute value.
     * @param primeSponsorName The primeSponsorName to set.
     */
    public void setPrimeSponsorName(String primeSponsorName) {
        this.primeSponsorName = primeSponsorName;
    }


    /**
     * Gets the primeSponsorName attribute.
     * @return Returns the primeSponsorName.
     */
    public String getPrimeSponsorName() {
        return primeSponsorName;
    }

    /**
     * Gets the value of newPersonId
     *
     * @return the value of newPersonId
     */
    public String getNewPersonId() {
        return this.newPersonId;
    }

    /**
     * Sets the value of newPersonId
     *
     * @param argNewPersonId Value to assign to this.newPersonId
     */
    public void setNewPersonId(String argNewPersonId) {
        this.newPersonId = argNewPersonId;
    }

    public String getLookupResultsSequenceNumber() {
        return lookupResultsSequenceNumber;
    }

    public void setLookupResultsSequenceNumber(String lookupResultsSequenceNumber) {
        this.lookupResultsSequenceNumber = lookupResultsSequenceNumber;
    }


    /**
     * Gets the value of newProposalPerson
     *
     * @return the value of newProposalPerson
     */
    public ProposalPerson getNewProposalPerson() {
        return this.newProposalPerson;
    }

    /**
     * Sets the value of newProposalPerson
     *
     * @param argNewProposalPerson Value to assign to this.newProposalPerson
     */
    public void setNewProposalPerson(ProposalPerson argNewProposalPerson) {
        this.newProposalPerson = argNewProposalPerson;
    }

    /**
     * Gets the value of newProposalPersonUnit
     *
     * @return the value of newProposalPersonUnit
     */
    public List<Unit> getNewProposalPersonUnit() {
        if (this.getProposalDevelopmentDocument().getProposalPersons().size() > this.newProposalPersonUnit.size()) {
            this.newProposalPersonUnit.add(this.newProposalPersonUnit.size(), new Unit());
        }
        return this.newProposalPersonUnit;
    }

    /**
     * Sets the value of newProposalPersonUnit
     *
     * @param argUnit Value to assign to this.newProposalPersonUnit
     */
    public void setNewProposalPersonUnit(List<Unit> argUnit) {
        this.newProposalPersonUnit = argUnit;
    }

    /**
     * Gets the value of newProposalPersonDegree
     *
     * @return the value of newProposalPersonDegree
     */
    public List<ProposalPersonDegree> getNewProposalPersonDegree() {

        if (this.getProposalDevelopmentDocument().getProposalPersons().size() > this.newProposalPersonDegree.size()) {
            this.newProposalPersonDegree.add(this.newProposalPersonDegree.size(),new ProposalPersonDegree());
        }
        return this.newProposalPersonDegree;
    }

    /**
     * Sets the value of newProposalPersonDegree
     *
     * @param argDegree Value to assign to this.newProposalPersonDegree
     */
    public void setNewProposalPersonDegree(List<ProposalPersonDegree> argDegree) {
        this.newProposalPersonDegree = argDegree;
    }

    /**
     * Gets the value of newRolodexId
     *
     * @return the value of newRolodexId
     */
    public String getNewRolodexId() {
        return this.newRolodexId;
    }


    /**
     * Sets the value of newRolodexId
     *
     * @param argNewRolodexId Value to assign to this.newRolodexId
     */
    public void setNewRolodexId(String argNewRolodexId) {
        this.newRolodexId = argNewRolodexId;
    }

    public String getLookupResultsBOClassName() {
        return lookupResultsBOClassName;
    }

    public void setLookupResultsBOClassName(String lookupResultsBOClassName) {
        this.lookupResultsBOClassName = lookupResultsBOClassName;
    }

    /**
     * Gets the newNarrative attribute.
     * @return Returns the newNarrative.
     */
    public Narrative getNewNarrative() {
        return newNarrative;
    }


    /**
     * Sets the newNarrative attribute value.
     * @param newNarrative The newNarrative to set.
     */
    public void setNewNarrative(Narrative newNarrative) {
        this.newNarrative = newNarrative;
    }


    public FormFile getNarrativeFile() {
        return narrativeFile;
    }


    public void setNarrativeFile(FormFile narrativeFile) {
        this.narrativeFile = narrativeFile;
    }

    public boolean isShowMaintenanceLinks(){
        return showMaintenanceLinks;
    }

    public void setShowMaintenanceLinks(boolean showMaintenanceLinks) {
        this.showMaintenanceLinks = showMaintenanceLinks;
    }

    private BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    /**
     * Creates the list of <code>{@link PersonEditableField}</code> field names.
     */
    public void populatePersonEditableFields() {
        LOG.debug("Adding PersonEditableFields");

        setPersonEditableFields(new HashMap());

        Collection<PersonEditableField> fields = getBusinessObjectService().findAll(PersonEditableField.class);
        for (PersonEditableField field : fields) {
            LOG.debug("found field " + field.getFieldName());
            getPersonEditableFields().put(field.getFieldName(), new Boolean(field.isActive()));
        }
    }

    /**
     * Write access to <code>{@link Map}</code> containing persisted <code>{@link PersonEditableField}</code> BO instances.
     *
     * @param fields
     */
    public void setPersonEditableFields(Map fields) {
        personEditableFields = fields;
    }

    /**
     * Get persisted <code>{@link PersonEditableField}</code> BO instances as a <code>{@link Map}</code>. If the <code>{@link Map}</code> containing them is
     *  <code>null</code>, then it gets populated here.
     *
     * @return Map containing person editable fields
     */
    public Map getPersonEditableFields() {
        if (personEditableFields == null) {
            populatePersonEditableFields();
        }
        return personEditableFields;
    }

    public Map getCreditSplitTotals() {
        return getKeyPersonnelService().calculateCreditSplitTotals(getProposalDevelopmentDocument());
    }


    public ProposalPersonBiography getNewPropPersonBio() {
        return newPropPersonBio;
    }


    public void setNewPropPersonBio(ProposalPersonBiography newPropPersonBio) {
        this.newPropPersonBio = newPropPersonBio;
    }


    public Narrative getNewInstituteAttachment() {
        return newInstituteAttachment;
    }


    public void setNewInstituteAttachment(Narrative newInstituteAttachment) {
        this.newInstituteAttachment = newInstituteAttachment;
    }


    /**
     * Sets the auditActivated attribute value.
     * @param auditActivated The auditActivated to set.
     */
    public void setAuditActivated(boolean auditActivated) {
        this.auditActivated = auditActivated;
    }

    /**
     * Gets the auditActivated attribute.
     * @return Returns the auditActivated.
     */
    public boolean isAuditActivated() {
        return auditActivated;
    }

    private KeyPersonnelService getKeyPersonnelService() {
        return getService(KeyPersonnelService.class);
    }

    /**
     * Gets the Copy Criteria for copying a proposal development document.
     * The criteria is user-specified and controls the operation of the
     * copy.
     *
     * @return the proposal copy criteria
     */
    public ProposalCopyCriteria getCopyCriteria() {
        return copyCriteria;
    }

    /**
     * Sets the Copy Criteria for copying a proposal development document.
     * The criteria is user-specified and controls the operation of the
     * copy.
     *
     * @param copyCriteria the new proposal copy criteria
     */
    public void setCopyCriteria(ProposalCopyCriteria copyCriteria) {
        this.copyCriteria = copyCriteria;
    }

    /**
     * Determines if attachments can be copied.
     *
     * @return true if copying attachments is disabled; otherwise false.
     */
    public boolean getIsCopyAttachmentsDisabled() {
        ProposalDevelopmentDocument doc = this.getProposalDevelopmentDocument();
        return !(doc.getNarratives().size() > 0 ||
            doc.getInstituteAttachments().size() > 0 ||
            doc.getPropPersonBios().size() > 0);
    }

    /**
     * This method...
     *
     * @return true if copying budget(s) is disabled; otherwise false.
     */
    public boolean getIsCopyBudgetDisabled() {
        return true;
    }


    /**
     * Sets the proposalDevelopmentParameters attribute value.
     * @param proposalDevelopmentParameters The proposalDevelopmentParameters to set.
     */
    public void setProposalDevelopmentParameters(Map<String, Parameter> proposalDevelopmentParameters) {
        this.proposalDevelopmentParameters = proposalDevelopmentParameters;
    }


    /**
     * Gets the proposalDevelopmentParameters attribute.
     * @return Returns the proposalDevelopmentParameters.
     */
    public Map<String, Parameter> getProposalDevelopmentParameters() {
        return proposalDevelopmentParameters;
    }

    public Integer getAnswerYesNo() {
        return Constants.ANSWER_YES_NO;
    }


    public Integer getAnswerYesNoNA() {
        return Constants.ANSWER_YES_NO_NA;
    }

}
