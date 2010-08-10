/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal.web.struts.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.award.contacts.AwardCentralAdminContactsBean;
import org.kuali.kra.award.contacts.AwardUnitContactsBean;
import org.kuali.kra.bo.AbstractSpecialReview;
import org.kuali.kra.common.customattributes.CustomDataForm;
import org.kuali.kra.common.web.struts.form.ReportHelperBean;
import org.kuali.kra.common.web.struts.form.ReportHelperBeanContainer;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalCentralAdminContactsBean;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalCreditSplitBean;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalProjectPersonnelBean;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalUnitContactsBean;
import org.kuali.kra.institutionalproposal.customdata.InstitutionalProposalCustomDataFormHelper;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalCostShareBean;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalNotepadBean;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalSpecialReview;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalSpecialReviewExemption;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalUnrecoveredFandABean;
import org.kuali.kra.medusa.MedusaBean;
import org.kuali.kra.web.struts.form.Auditable;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;
import org.kuali.kra.web.struts.form.MultiLookupFormBase;
import org.kuali.kra.web.struts.form.SpecialReviewFormBase;
import org.kuali.rice.kns.datadictionary.DocumentEntry;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.util.ActionFormUtilMap;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * This class...
 */
public class InstitutionalProposalForm extends KraTransactionalDocumentFormBase implements CustomDataForm, Auditable,
                                                                        MultiLookupFormBase, ReportHelperBeanContainer,
                                                                        SpecialReviewFormBase<InstitutionalProposalSpecialReviewExemption>{

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4564236415580911082L;
    
    private boolean auditActivated;
    
    private String lookupResultsSequenceNumber;
    private String lookupResultsBOClassName;
    
    private String[] newExemptionTypeCodes;
    private List<InstitutionalProposalSpecialReviewExemption> newSpecialReviewExemptions;
    private InstitutionalProposalSpecialReview newInstitutionalProposalSpecialReview;
    
    private InstitutionalProposalCustomDataFormHelper institutionalProposalCustomDataFormHelper;
    private InstitutionalProposalNotepadBean institutionalProposalNotepadBean;
    private InstitutionalProposalCostShareBean institutionalProposalCostShareBean;
    private InstitutionalProposalUnrecoveredFandABean institutionalProposalUnrecoveredFandABean;
    private InstitutionalProposalProjectPersonnelBean projectPersonnelBean;
    private InstitutionalProposalCreditSplitBean institutionalProposalCreditSplitBean;
    private InstitutionalProposalUnitContactsBean unitContactsBean;
    private InstitutionalProposalCentralAdminContactsBean centralAdminContactsBean;
    
    private MedusaBean medusaBean;
    private ReportHelperBean reportHelperBean;
    
    /* Populated from Proposal Log lookup for Proposal creation */
    private String proposalNumber;
    
    private transient String[] selectedAwardFundingProposals;
    
    /**
     * 
     * Constructs a AwardForm.
     */
    public InstitutionalProposalForm() {
        super();        
        initializeHeaderNavigationTabs();
        this.setDocument(new InstitutionalProposalDocument());
        initialize();
    }
    
    /**
     * 
     * This method initialize all form variables
     */
    public void initialize() {
        institutionalProposalCustomDataFormHelper = new InstitutionalProposalCustomDataFormHelper(this);
        institutionalProposalNotepadBean = new InstitutionalProposalNotepadBean(this);
        institutionalProposalCostShareBean = new InstitutionalProposalCostShareBean(this);
        institutionalProposalUnrecoveredFandABean = new InstitutionalProposalUnrecoveredFandABean(this);
        
        newInstitutionalProposalSpecialReview = new InstitutionalProposalSpecialReview();
        newSpecialReviewExemptions = new ArrayList<InstitutionalProposalSpecialReviewExemption>();
        projectPersonnelBean = new InstitutionalProposalProjectPersonnelBean(this);
        institutionalProposalCreditSplitBean = new InstitutionalProposalCreditSplitBean(this);
        medusaBean = new MedusaBean();
        reportHelperBean = new ReportHelperBean(this);
        unitContactsBean = new InstitutionalProposalUnitContactsBean(this);
        centralAdminContactsBean = new InstitutionalProposalCentralAdminContactsBean(this);
    }
    
    /**
     * 
     * This method returns the AwardDocument object.
     * @return
     */
    public InstitutionalProposalDocument getInstitutionalProposalDocument() {
        return (InstitutionalProposalDocument) super.getDocument();
    }
    
    /**
     * This method returns a string representation of the document type
     * @return
     */
    public String getDocumentTypeName() {
        return "InstitutionalProposalDocument";
    }
    
    /**
     * 
     * This method initializes the loads the header navigation tabs.
     */
    protected void initializeHeaderNavigationTabs(){
        DataDictionaryService dataDictionaryService = getDataDictionaryService();
        DocumentEntry docEntry = dataDictionaryService.getDataDictionary().getDocumentEntry(
                org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument.class.getName());
        List<HeaderNavigation> navList = docEntry.getHeaderNavigationList();
        HeaderNavigation[] list = new HeaderNavigation[navList.size()];
        navList.toArray(list);
        super.setHeaderNavigationTabs(list); 
    }
    
    /**
     * Gets the institutionalProposalCustomDataFormHelper attribute. 
     * @return Returns the institutionalProposalCustomDataFormHelper.
     */
    public InstitutionalProposalCustomDataFormHelper getCustomDataHelper() {
        return institutionalProposalCustomDataFormHelper;
    }
    
    /**
     * Gets the institutionalProposalCustomDataFormHelper attribute. 
     * @return Returns the institutionalProposalCustomDataFormHelper.
     */
    public InstitutionalProposalCustomDataFormHelper getInstitutionalProposalCustomDataFormHelper() {
        return institutionalProposalCustomDataFormHelper;
    }

    /**
     * Sets the institutionalProposalCustomDataFormHelper attribute value.
     * @param institutionalProposalCustomDataFormHelper The institutionalProposalCustomDataFormHelper to set.
     */
    public void setInstitutionalProposalCustomDataFormHelper(
            InstitutionalProposalCustomDataFormHelper institutionalProposalCustomDataFormHelper) {
        this.institutionalProposalCustomDataFormHelper = institutionalProposalCustomDataFormHelper;
    }
    
    /**
     * Gets the institutionalProposalNotepadBean attribute. 
     * @return Returns the institutionalProposalNotepadBean.
     */
    public InstitutionalProposalNotepadBean getInstitutionalProposalNotepadBean() {
        return institutionalProposalNotepadBean;
    }

    /**
     * Sets the institutionalProposalNotepadBean attribute value.
     * @param institutionalProposalNotepadBean The institutionalProposalNotepadBean to set.
     */
    public void setInstitutionalProposalNotepadBean(InstitutionalProposalNotepadBean institutionalProposalNotepadBean) {
        this.institutionalProposalNotepadBean = institutionalProposalNotepadBean;
    }
    
    


    /**
     * Gets the projectPersonnelBean attribute. 
     * @return Returns the projectPersonnelBean.
     */
    public InstitutionalProposalProjectPersonnelBean getProjectPersonnelBean() {
        return projectPersonnelBean;
    }

    /**
     * Sets the projectPersonnelBean attribute value.
     * @param projectPersonnelBean The projectPersonnelBean to set.
     */
    public void setProjectPersonnelBean(InstitutionalProposalProjectPersonnelBean projectPersonnelBean) {
        this.projectPersonnelBean = projectPersonnelBean;
    }
    
    public InstitutionalProposalUnitContactsBean getUnitContactsBean() {
        return unitContactsBean;
    }
    
    /**
     * @return
     */
    public InstitutionalProposalCentralAdminContactsBean getCentralAdminContactsBean() {
        return centralAdminContactsBean;
    }
    

    /**
     * Gets the institutionalProposalCreditSplitBean attribute. 
     * @return Returns the institutionalProposalCreditSplitBean.
     */
    public InstitutionalProposalCreditSplitBean getInstitutionalProposalCreditSplitBean() {
        return institutionalProposalCreditSplitBean;
    }

    /**
     * Sets the institutionalProposalCreditSplitBean attribute value.
     * @param institutionalProposalCreditSplitBean The institutionalProposalCreditSplitBean to set.
     */
    public void setInstitutionalProposalCreditSplitBean(InstitutionalProposalCreditSplitBean institutionalProposalCreditSplitBean) {
        this.institutionalProposalCreditSplitBean = institutionalProposalCreditSplitBean;
    }

    /**
     * Gets the institutionalProposalCostShareBean attribute. 
     * @return Returns the institutionalProposalCostShareBean.
     */
    public InstitutionalProposalCostShareBean getInstitutionalProposalCostShareBean() {
        return institutionalProposalCostShareBean;
    }

    /**
     * Sets the institutionalProposalCostShareBean attribute value.
     * @param institutionalProposalCostShareBean The institutionalProposalCostShareBean to set.
     */
    public void setInstitutionalProposalCostShareBean(InstitutionalProposalCostShareBean institutionalProposalCostShareBean) {
        this.institutionalProposalCostShareBean = institutionalProposalCostShareBean;
    }

    /**
     * Gets the institutionalProposalUnrecoveredFandABean attribute. 
     * @return Returns the institutionalProposalUnrecoveredFandABean.
     */
    public InstitutionalProposalUnrecoveredFandABean getInstitutionalProposalUnrecoveredFandABean() {
        return institutionalProposalUnrecoveredFandABean;
    }

    /**
     * Sets the institutionalProposalUnrecoveredFandABean attribute value.
     * @param institutionalProposalUnrecoveredFandABean The institutionalProposalUnrecoveredFandABean to set.
     */
    public void setInstitutionalProposalUnrecoveredFandABean(
            InstitutionalProposalUnrecoveredFandABean institutionalProposalUnrecoveredFandABean) {
        this.institutionalProposalUnrecoveredFandABean = institutionalProposalUnrecoveredFandABean;
    }

    /**
     * Gets the newInstitutionalProposalSpecialReview attribute. 
     * @return Returns the newInstitutionalProposalSpecialReview.
     */
    public InstitutionalProposalSpecialReview getNewInstitutionalProposalSpecialReview() {
        return newInstitutionalProposalSpecialReview;
    }

    /**
     * Sets the newInstitutionalProposalSpecialReview attribute value.
     * @param newInstitutionalProposalSpecialReview The newInstitutionalProposalSpecialReview to set.
     */
    public void setNewInstitutionalProposalSpecialReview(InstitutionalProposalSpecialReview newInstitutionalProposalSpecialReview) {
        this.newInstitutionalProposalSpecialReview = newInstitutionalProposalSpecialReview;
    }

    /**
     * Sets the newExemptionTypeCodes attribute value.
     * @param newExemptionTypeCodes The newExemptionTypeCodes to set.
     */
    public void setNewExemptionTypeCodes(String[] newExemptionTypeCodes) {
        this.newExemptionTypeCodes = newExemptionTypeCodes;
    }

    /**
     * Sets the newSpecialReviewExemptions attribute value.
     * @param newSpecialReviewExemptions The newSpecialReviewExemptions to set.
     */
    public void setNewSpecialReviewExemptions(List<InstitutionalProposalSpecialReviewExemption> newSpecialReviewExemptions) {
        this.newSpecialReviewExemptions = newSpecialReviewExemptions;
    }

    public String getActionName() {
        return "institutionalProposal";
    }
    
    /**
     * @return The selected lead unit
     */
    public String getSelectedLeadUnit() {
        return projectPersonnelBean.getSelectedLeadUnit();
    }
    
    /**
     * @param unitName
     */
    public void setSelectedLeadUnit(String unitName) {
        projectPersonnelBean.setSelectedLeadUnit(unitName);
    }

    /**
     * 
     * This method is a wrapper method for getting DataDictionary Service using the Service Locator.
     * @return
     */
    protected DataDictionaryService getDataDictionaryService(){
        return (DataDictionaryService) KraServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
    }
    
    /**
     * @see org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase#getLockRegion()
     */
    @Override
    protected String getLockRegion() {
        return KraAuthorizationConstants.LOCK_DESCRIPTOR_INSTITUTIONAL_PROPOSAL;
    }

    /**
     * @see org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase#setSaveDocumentControl(java.util.Map)
     */
    @Override
    protected void setSaveDocumentControl(Map editMode) {
        getDocumentActions().put(KNSConstants.KUALI_ACTION_CAN_SAVE, KNSConstants.KUALI_DEFAULT_TRUE_VALUE);

    }
    
    /** {@inheritDoc} */
    public boolean isAuditActivated() {
        return this.auditActivated;
    }

    /** {@inheritDoc} */
    public void setAuditActivated(boolean auditActivated) {
        this.auditActivated = auditActivated;
    }

    /**
     * @see org.kuali.kra.web.struts.form.SpecialReviewFormBase#getNewExemptionTypeCodes()
     */
    public String[] getNewExemptionTypeCodes() {
        return newExemptionTypeCodes;
    }

    /**
     * @see org.kuali.kra.web.struts.form.SpecialReviewFormBase#getNewSpecialReview()
     */
    public AbstractSpecialReview<InstitutionalProposalSpecialReviewExemption> getNewSpecialReview() {
        return newInstitutionalProposalSpecialReview;
    }

    /**
     * @see org.kuali.kra.web.struts.form.SpecialReviewFormBase#getNewSpecialReviewExemptions()
     */
    public List<InstitutionalProposalSpecialReviewExemption> getNewSpecialReviewExemptions() {
        return newSpecialReviewExemptions;
    }

    /**
     * @see org.kuali.kra.web.struts.form.SpecialReviewFormBase#getResearchDocument()
     */
    public ResearchDocumentBase getResearchDocument() {
        return getInstitutionalProposalDocument();
    }

    /**
     * Gets the lookupResultsSequenceNumber attribute. 
     * @return Returns the lookupResultsSequenceNumber.
     */
    public String getLookupResultsSequenceNumber() {
        return lookupResultsSequenceNumber;
    }

    /**
     * Sets the lookupResultsSequenceNumber attribute value.
     * @param lookupResultsSequenceNumber The lookupResultsSequenceNumber to set.
     */
    public void setLookupResultsSequenceNumber(String lookupResultsSequenceNumber) {
        this.lookupResultsSequenceNumber = lookupResultsSequenceNumber;
    }

    /**
     * Gets the lookupResultsBOClassName attribute. 
     * @return Returns the lookupResultsBOClassName.
     */
    public String getLookupResultsBOClassName() {
        return lookupResultsBOClassName;
    }

    /**
     * Sets the lookupResultsBOClassName attribute value.
     * @param lookupResultsBOClassName The lookupResultsBOClassName to set.
     */
    public void setLookupResultsBOClassName(String lookupResultsBOClassName) {
        this.lookupResultsBOClassName = lookupResultsBOClassName;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    /**
     * Gets the medusaBean attribute. 
     * @return Returns the medusaBean.
     */
    public MedusaBean getMedusaBean() {
        return medusaBean;
    }

    /**
     * Sets the medusaBean attribute value.
     * @param medusaBean The medusaBean to set.
     */
    public void setMedusaBean(MedusaBean medusaBean) {
        this.medusaBean = medusaBean;
    }
    
    public String getValueFinderResultDoNotCache(){
        if (this.getActionFormUtilMap() instanceof ActionFormUtilMap) {
            ((ActionFormUtilMap) this.getActionFormUtilMap()).setCacheValueFinderResults(false);
        }
        return "";
    }
    
    public String getValueFinderResultCache(){
        if (this.getActionFormUtilMap() instanceof ActionFormUtilMap) {
            ((ActionFormUtilMap) this.getActionFormUtilMap()).setCacheValueFinderResults(true);
        }
        return "";
    }

    /**
     *
     * @return
     */
    public ReportHelperBean getReportHelperBean() {
        return reportHelperBean;
    }

    public String[] getSelectedAwardFundingProposals() {
        return selectedAwardFundingProposals;
    }

    public void setSelectedAwardFundingProposals(String[] selectedAwardFundingProposals) {
        this.selectedAwardFundingProposals = selectedAwardFundingProposals;
    }
    
    public boolean getDisplayEditButton() {
        return this.getInstitutionalProposalDocument().getInstitutionalProposal().isActiveVersion();
    }
    
}
