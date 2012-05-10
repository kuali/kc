/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.iacuc;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.iacuc.actions.IacucActionHelper;
import org.kuali.kra.iacuc.customdata.IacucProtocolCustomDataHelper;
import org.kuali.kra.iacuc.permission.IacucPermissionsHelper;
import org.kuali.kra.iacuc.personnel.IacucPersonnelHelper;
import org.kuali.kra.iacuc.protocol.IacucProtocolHelper;
import org.kuali.kra.iacuc.protocol.reference.IacucProtocolReferenceBean;
import org.kuali.kra.iacuc.questionnaire.IacucProtocolQuestionnaireHelper;
import org.kuali.kra.iacuc.specialreview.IacucProtocolSpecialReviewHelper;
import org.kuali.kra.iacuc.species.IacucProtocolSpeciesHelper;
import org.kuali.kra.iacuc.species.exception.IacucProtocolExceptionHelper;
import org.kuali.kra.iacuc.threers.IacucAlternateSearchHelper;
import org.kuali.kra.protocol.ProtocolForm;
import org.kuali.kra.protocol.actions.ProtocolStatus;
import org.kuali.kra.protocol.protocol.ProtocolHelper;
import org.kuali.kra.protocol.protocol.reference.ProtocolReferenceBean;
import org.kuali.kra.protocol.questionnaire.QuestionnaireHelper;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.util.ActionFormUtilMap;
import org.kuali.rice.kns.web.ui.HeaderField;

/**
 * This class...
 */
public class IacucProtocolForm extends ProtocolForm {
    
    private static final long serialVersionUID = -535557943052220820L;
    private IacucProtocolSpeciesHelper iacucProtocolSpeciesHelper;
    private IacucAlternateSearchHelper iacucAlternateSearchHelper;
    private IacucProtocolExceptionHelper iacucProtocolExceptionHelper;
    

    public IacucProtocolForm() throws Exception {
        super();
        initializeIacucProtocolHelpers();
        initializeIacucProtocolSpecies();
        initializeIacucAlternateSearchHelper();
        initializeIacucProtocolException();
    }

    public void initializeIacucProtocolHelpers() throws Exception {
        setActionHelper(new IacucActionHelper(this));
        setProtocolCustomDataHelper(new IacucProtocolCustomDataHelper(this));
        setProtocolSpecialReviewHelper(new IacucProtocolSpecialReviewHelper(this));
        setQuestionnaireHelper(new IacucProtocolQuestionnaireHelper(this));
    }
    
    public void initializeIacucProtocolSpecies() throws Exception {
        setIacucProtocolSpeciesHelper(new IacucProtocolSpeciesHelper(this));
    }
    
    public void initializeIacucProtocolException() throws Exception {
        setIacucProtocolExceptionHelper(new IacucProtocolExceptionHelper(this));
    }

    protected void initializeIacucAlternateSearchHelper() throws Exception {
        setIacucAlternateSearchHelper(new IacucAlternateSearchHelper(this));
    }
    
    @Override
    public String getActionName() {
        return "iacucProtocol";
    }

    /** {@inheritDoc} */
    @Override
    protected String getDefaultDocumentTypeName() {
        return "IacucProtocolDocument";
    }


    /**
     * Gets a {@link IacucProtocolDocument ProtocolDocument}.
     * @return {@link IacucProtocolDocument ProtocolDocument}
     */
    public IacucProtocolDocument getIacucProtocolDocument() {
        return (IacucProtocolDocument) super.getProtocolDocument();
    }


    @Override
    protected String getLockRegion() {
        // TODO Auto-generated method stub
        return KraAuthorizationConstants.LOCK_DESCRIPTOR_IACUC_PROTOCOL;
    }

    public IacucProtocolHelper getProtocolHelper() {
        return (IacucProtocolHelper)super.getProtocolHelper();
    }

    @Override
    protected ProtocolHelper createNewProtocolHelperInstanceHook(ProtocolForm protocolForm) {
        return new IacucProtocolHelper((IacucProtocolForm) protocolForm);
    }
    
    
    public IacucPermissionsHelper getPermissionsHelper(ProtocolForm protocolForm) {
        return (IacucPermissionsHelper)super.getPermissionsHelper();
    }
    
    @Override
    protected IacucPermissionsHelper createNewPermissionsHelperInstanceHook(ProtocolForm protocolForm) {
        return new IacucPermissionsHelper((IacucProtocolForm) protocolForm);
    }
    
    public IacucPersonnelHelper getPersonnelHelper(ProtocolForm protocolForm) {
        return (IacucPersonnelHelper)super.getPersonnelHelper();
    }
    
    @Override
    protected IacucPersonnelHelper createNewPersonnelHelperInstanceHook(ProtocolForm protocolForm) {
        return new IacucPersonnelHelper((IacucProtocolForm)protocolForm);
    }
    
    protected QuestionnaireHelper createNewQuestionnaireHelper(ProtocolForm form) {
        return new IacucProtocolQuestionnaireHelper(form);
    }

    protected IacucActionHelper createNewActionHelper(ProtocolForm protocolForm) throws Exception {
        return new IacucActionHelper(protocolForm);
    }

    @Override
    public String getModuleCode() {
        return CoeusModule.IACUC_PROTOCOL_MODULE_CODE;
    }

    public IacucProtocolSpeciesHelper getIacucProtocolSpeciesHelper() {
        return iacucProtocolSpeciesHelper;
    }

    public void setIacucProtocolSpeciesHelper(IacucProtocolSpeciesHelper iacucProtocolSpeciesHelper) {
        this.iacucProtocolSpeciesHelper = iacucProtocolSpeciesHelper;
    }

    public IacucAlternateSearchHelper getIacucAlternateSearchHelper() {
        return iacucAlternateSearchHelper;
    }

    public void setIacucAlternateSearchHelper(IacucAlternateSearchHelper iacucAlternateSearchHelper) {
        this.iacucAlternateSearchHelper = iacucAlternateSearchHelper;
    }

    @Override
    protected ProtocolReferenceBean createNewProtocolReferenceBeanInstance() {
        return new IacucProtocolReferenceBean();
    }

    public IacucProtocolExceptionHelper getIacucProtocolExceptionHelper() {
        return iacucProtocolExceptionHelper;
    }

    public void setIacucProtocolExceptionHelper(IacucProtocolExceptionHelper iacucProtocolExceptionHelper) {
        this.iacucProtocolExceptionHelper = iacucProtocolExceptionHelper;
    }
    
    @Override
    public void populate(HttpServletRequest request) { 
        super.populate(request);
        
        // Temporary hack for KRACOEUS-489
        if (getActionFormUtilMap() instanceof ActionFormUtilMap) {
            ((ActionFormUtilMap) getActionFormUtilMap()).clear();
        }
    }
    
    @Override
    public void populateHeaderFields(WorkflowDocument workflowDocument) {
        super.populateHeaderFields(workflowDocument);
        IacucProtocolDocument pd = getIacucProtocolDocument();
        
        HeaderField documentNumber = getDocInfo().get(0);
        documentNumber.setDdAttributeEntryName("DataDictionary.IacucProtocolDocument.attributes.documentNumber");
        
        ProtocolStatus protocolStatus = (pd == null) ? null : pd.getIacucProtocol().getProtocolStatus();
        HeaderField docStatus = new HeaderField("DataDictionary.AttributeReferenceDummy.attributes.workflowDocumentStatus", protocolStatus == null? "" : protocolStatus.getDescription());
        getDocInfo().set(1, docStatus);
        
        String lastUpdatedDateStr = null;
        if(pd != null && pd.getUpdateTimestamp() != null) {
            lastUpdatedDateStr = getFormattedDateTime(pd.getUpdateTimestamp());
        }
        
        if(getDocInfo().size() > 2) {
            HeaderField initiatorField = getDocInfo().get(2);
            String modifiedInitiatorFieldStr = initiatorField.getDisplayValue();
            if(StringUtils.isNotBlank(lastUpdatedDateStr)) {
                modifiedInitiatorFieldStr = modifiedInitiatorFieldStr + " : " + lastUpdatedDateStr;
            }
            getDocInfo().set(2, new HeaderField("DataDictionary.IacucProtocol.attributes.initiatorLastUpdated", modifiedInitiatorFieldStr));
        }
        
        String protocolSubmissionStatusStr = null;
        if(pd != null && pd.getIacucProtocol() != null && pd.getIacucProtocol().getProtocolSubmission() != null) {
            pd.getIacucProtocol().getProtocolSubmission().refreshReferenceObject("submissionStatusCode");
            protocolSubmissionStatusStr = pd.getIacucProtocol().getProtocolSubmission().getSubmissionStatus().getDescription();
        }
        HeaderField protocolSubmissionStatus = new HeaderField("DataDictionary.IacucProtocol.attributes.protocolSubmissionStatus", protocolSubmissionStatusStr);
        getDocInfo().set(3, protocolSubmissionStatus);
        
        getDocInfo().add(new HeaderField("DataDictionary.IacucProtocol.attributes.protocolNumber", (pd == null) ? null : pd.getIacucProtocol().getProtocolNumber()));

        String expirationDateStr = null;
        if(pd != null && pd.getProtocol().getExpirationDate() != null) {
            expirationDateStr = getFormattedDate(pd.getIacucProtocol().getExpirationDate()); 
        }
        
        HeaderField expirationDate = new HeaderField("DataDictionary.IacucProtocol.attributes.expirationDate", expirationDateStr);
        getDocInfo().add(expirationDate);
    }
    
}
