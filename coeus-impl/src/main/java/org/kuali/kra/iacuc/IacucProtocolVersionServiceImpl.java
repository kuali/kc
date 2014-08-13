/*
 * Copyright 2005-2014 The Kuali Foundation
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
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolVersionServiceImplBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.protocol.questionnaire.ProtocolModuleQuestionnaireBeanBase;


/**
 * ProtocolBase Version Service Implementation.
 */
public class IacucProtocolVersionServiceImpl extends ProtocolVersionServiceImplBase implements IacucProtocolVersionService{
    private IacucProtocolProcedureService iacucProtocolProcedureService;
    
    protected String getProtocolDocumentTypeHook() {
        return "IacucProtocolDocument";
    }
    
    protected ProtocolBase createProtocolNewVersionHook(ProtocolBase protocol) throws Exception {
        IacucProtocol iacucProtocol = (IacucProtocol)protocol;
        iacucProtocol = versioningService.createNewVersion(iacucProtocol);
        setNewProtocolId(iacucProtocol);
        initPersonId(iacucProtocol);
        getIacucProtocolProcedureService().resetAllProtocolStudyProcedures(iacucProtocol);
        return iacucProtocol;
    }

    protected ProtocolModuleQuestionnaireBeanBase getNewInstanceProtocolModuleQuestionnaireBeanHook(ProtocolBase protocol) {
        return new IacucProtocolModuleQuestionnaireBean((IacucProtocol) protocol);
    }

    @Override
    protected Class<? extends ProtocolBase> getProtocolBOClassHook() {
        return IacucProtocol.class;
    }

    @Override
    protected ProtocolDocumentBase createNewProtocolDocumentInstanceHook() {
        return new IacucProtocolDocument();
    }

    @Override
    protected String getProtocolSequenceIdHook() {
        return "SEQ_IACUC_PROTOCOL_ID";
    }

    protected String getProtocolPersonSequenceId() {
        return "SEQ_IACUC_PROTOCOL_ID";
    }
    
    public IacucProtocolProcedureService getIacucProtocolProcedureService() {
        return iacucProtocolProcedureService;
    }

    public void setIacucProtocolProcedureService(IacucProtocolProcedureService iacucProtocolProcedureService) {
        this.iacucProtocolProcedureService = iacucProtocolProcedureService;
    }

    /**
     * This method initializes the personId of the person
     * @param protocol
     */
    private void initPersonId(ProtocolBase protocol) {
        for (ProtocolPersonBase person : protocol.getProtocolPersons()) {
            Integer nextPersonId = getSequenceAccessorService().getNextAvailableSequenceNumber(getProtocolPersonSequenceId()).intValue();
            person.setProtocolPersonId(nextPersonId);
        }
    }
}
