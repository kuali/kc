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
package org.kuali.kra.iacuc;

import org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService;
import org.kuali.kra.iacuc.questionnaire.IacucProtocolModuleQuestionnaireBean;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.ProtocolDocument;
import org.kuali.kra.protocol.ProtocolVersionServiceImpl;
import org.kuali.kra.protocol.questionnaire.ProtocolModuleQuestionnaireBean;


/**
 * Protocol Version Service Implementation.
 */
public class IacucProtocolVersionServiceImpl extends ProtocolVersionServiceImpl implements IacucProtocolVersionService{
    private IacucProtocolProcedureService iacucProtocolProcedureService;
    
    protected String getProtocolDocumentTypeHook() {
        return "IacucProtocolDocument";
    }
    
    protected Protocol createProtocolNewVersionHook(Protocol protocol) throws Exception {
        IacucProtocol iacucProtocol = (IacucProtocol)protocol;
        iacucProtocol = versioningService.createNewVersion(iacucProtocol);
        getIacucProtocolProcedureService().resetProcedurePanel(iacucProtocol);
        return iacucProtocol;
    }

    protected ProtocolModuleQuestionnaireBean getNewInstanceProtocolModuleQuestionnaireBeanHook(Protocol protocol) {
        return new IacucProtocolModuleQuestionnaireBean((IacucProtocol) protocol);
    }

    @Override
    protected Class<? extends Protocol> getProtocolBOClassHook() {
        return IacucProtocol.class;
    }

    @Override
    protected ProtocolDocument createNewProtocolDocumentInstanceHook() {
        return new IacucProtocolDocument();
    }

    @Override
    protected String getProtocolSequenceIdHook() {
        return "SEQ_IACUC_PROTOCOL_ID";
    }

    public IacucProtocolProcedureService getIacucProtocolProcedureService() {
        return iacucProtocolProcedureService;
    }

    public void setIacucProtocolProcedureService(IacucProtocolProcedureService iacucProtocolProcedureService) {
        this.iacucProtocolProcedureService = iacucProtocolProcedureService;
    }

}
