/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
