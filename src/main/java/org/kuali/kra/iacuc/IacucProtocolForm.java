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

import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.iacuc.customdata.IacucProtocolCustomDataHelper;
import org.kuali.kra.iacuc.permission.IacucPermissionsHelper;
import org.kuali.kra.iacuc.personnel.IacucPersonnelHelper;
import org.kuali.kra.iacuc.protocol.IacucProtocolHelper;
import org.kuali.kra.iacuc.questionnaire.IacucProtocolQuestionnaireHelper;
import org.kuali.kra.iacuc.specialreview.IacucProtocolSpecialReviewHelper;
import org.kuali.kra.iacuc.species.IacucProtocolSpeciesHelper;
import org.kuali.kra.protocol.ProtocolForm;
import org.kuali.kra.protocol.protocol.ProtocolHelper;
import org.kuali.kra.protocol.questionnaire.QuestionnaireHelper;

/**
 * This class...
 */
public class IacucProtocolForm extends ProtocolForm {
    
    private static final long serialVersionUID = -535557943052220820L;
    private IacucProtocolSpeciesHelper iacucProtocolSpeciesHelper;
    

    public IacucProtocolForm() throws Exception {
        super();
        setProtocolCustomDataHelper(new IacucProtocolCustomDataHelper(this));
        setProtocolSpecialReviewHelper(new IacucProtocolSpecialReviewHelper(this));
        initializeIacucProtocolSpecies();
    }

    public void initializeIacucProtocolSpecies() throws Exception {
        setIacucProtocolSpeciesHelper(new IacucProtocolSpeciesHelper(this));
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
}
