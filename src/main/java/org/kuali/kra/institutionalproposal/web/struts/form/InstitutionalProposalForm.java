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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.common.customattributes.CustomDataForm;
import org.kuali.kra.common.web.struts.form.ReportHelperBean;
import org.kuali.kra.common.web.struts.form.ReportHelperBeanContainer;
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
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalUnrecoveredFandABean;
import org.kuali.kra.institutionalproposal.specialreview.SpecialReviewHelper;
import org.kuali.kra.medusa.MedusaBean;
import org.kuali.kra.web.struts.form.Auditable;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;
import org.kuali.kra.web.struts.form.MultiLookupFormBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.util.ActionFormUtilMap;
import org.kuali.rice.krad.util.KRADConstants;

/**
 * This class...
 */
public class InstitutionalProposalForm extends KraTransactionalDocumentFormBase implements CustomDataForm, Auditable,
                                                                        MultiLookupFormBase, ReportHelperBeanContainer {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4564236415580911082L;
    private static final String CUSTOM_DATA_NAV_TO = "customData";

    private boolean auditActivated;
    
    private String lookupResultsSequenceNumber;
    private String lookupResultsBOClassName;
    
    private SpecialReviewHelper specialReviewHelper;
    private InstitutionalProposalCustomDataFormHelper institutionalProposalCustomDataFormHelper;
    private InstitutionalProposalNotepadBean institutionalProposalNotepadBean;
    private InstitutionalProposalCostShareBean institutionalProposalCostShareBean;
    private InstitutionalProposalUnrecoveredFandABean institutionalProposalUnrecoveredFandABean;
    private InstitutionalProposalProjectPersonnelBean projectPersonnelBean;
    private InstitutionalProposalCreditSplitBean institutionalProposalCreditSplitBean;
    private InstitutionalProposalUnitContactsBean unitContactsBean;
    private InstitutionalProposalCentralAdminContactsBean centralAdminContactsBean;
    private boolean cfdaLookupRequired;
    private MedusaBean medusaBean;
    private ReportHelperBean reportHelperBean;
    
    /* Populated from Proposal Log lookup for Proposal creation */
    private String proposalNumber;
    
    private transient String[] selectedAwardFundingProposals;
    
    private boolean viewFundingSource;
    private boolean docOpenedFromIPSearch;
    
    /**
     * 
     * Constructs a AwardForm.
     */
    public InstitutionalProposalForm() {
        super();
        initialize();
    }
    
    /** {@inheritDoc} */
    @Override
    protected String getDefaultDocumentTypeName() {
        return "InstitutionalProposalDocument";
    }
    
    /**
     * 
     * This method initialize all form variables
     */
    public void initialize() {
        specialReviewHelper = new SpecialReviewHelper(this);
        institutionalProposalCustomDataFormHelper = new InstitutionalProposalCustomDataFormHelper(this);
        institutionalProposalNotepadBean = new InstitutionalProposalNotepadBean(this);
        institutionalProposalCostShareBean = new InstitutionalProposalCostShareBean(this);
        institutionalProposalUnrecoveredFandABean = new InstitutionalProposalUnrecoveredFandABean(this);
        
        projectPersonnelBean = new InstitutionalProposalProjectPersonnelBean(this);
        institutionalProposalCreditSplitBean = new InstitutionalProposalCreditSplitBean(this);
        medusaBean = new MedusaBean();
        reportHelperBean = new ReportHelperBean(this);
        unitContactsBean = new InstitutionalProposalUnitContactsBean(this);
        centralAdminContactsBean = new InstitutionalProposalCentralAdminContactsBean(this);
        docOpenedFromIPSearch = false;
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
     * Gets the Special Review Helper.
     * @return the Special Review Helper
     */
    public SpecialReviewHelper getSpecialReviewHelper() {
        return specialReviewHelper;
    }

    /**
     * Sets the Special Review Helper.
     * @param specialReviewHelper the Special Review Helper
     */
    public void setSpecialReviewHelper(SpecialReviewHelper specialReviewHelper) {
        this.specialReviewHelper = specialReviewHelper;
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

    @Override
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
        getDocumentActions().put(KRADConstants.KUALI_ACTION_CAN_SAVE, KRADConstants.KUALI_DEFAULT_TRUE_VALUE);

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
        boolean displayEditButton;
        InstitutionalProposalDocument institutionalProposalDocument = (InstitutionalProposalDocument) getDocument();
        if (isDocOpenedFromIPSearch()) {
            displayEditButton = true;
        }else {
            displayEditButton = !isViewOnly();
        }
        displayEditButton &= getInstitutionalProposalDocument().getInstitutionalProposal().isActiveVersion();
        return displayEditButton;
      }
    
    public boolean isCfdaLookupRequired() {
        String integration = getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_AWARD, 
                Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.FIN_SYSTEM_INTEGRATION_ON_OFF_PARAMETER);
        cfdaLookupRequired = StringUtils.equals(integration, Constants.FIN_SYSTEM_INTEGRATION_ON) ? true : false;
        return cfdaLookupRequired;
    }

    protected ParameterService getParameterService() {
        return KraServiceLocator.getService(ParameterService.class);   
    }
    
    public boolean getViewFundingSource() {
        return viewFundingSource;
    }
    
    public void setViewFundingSource(boolean viewFundingSource) {
        this.viewFundingSource = viewFundingSource;
    }

    public boolean isDocOpenedFromIPSearch() {
        return docOpenedFromIPSearch;
    }
    public void setDocOpenedFromIPSearch(boolean docOpened) {
        docOpenedFromIPSearch = docOpened;
    }
    
    @Override
    public HeaderNavigation[] getHeaderNavigationTabs() {
        
        HeaderNavigation[] navigation = super.getHeaderNavigationTabs();
        
        List<HeaderNavigation> resultList = new ArrayList<HeaderNavigation>();
            //We have to copy the HeaderNavigation elements into a new collection as the 
            //List returned by DD is it's cached copy of the header navigation list.
        for (HeaderNavigation nav : navigation) {
            if (StringUtils.equals(nav.getHeaderTabNavigateTo(),CUSTOM_DATA_NAV_TO)) {
                boolean displayTab = !((InstitutionalProposalDocument)this.getDocument()).getCustomAttributeDocuments().isEmpty();
                nav.setDisabled(!displayTab);
                if (displayTab) {
                    resultList.add(nav);
                }
            } else {
                resultList.add(nav);
            }
        }
        
        HeaderNavigation[] result = new HeaderNavigation[resultList.size()];
        resultList.toArray(result);
        return result;
    }

}
