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
package org.kuali.kra.iacuc.personnel;

import java.util.List;

import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.procedures.IacucProcedurePersonResponsible;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroup;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupBean;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupDetailBean;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.protocol.personnel.ProtocolPerson;
import org.kuali.kra.protocol.personnel.ProtocolPersonnelRuleBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonnelService;
import org.kuali.kra.rule.BusinessRuleInterface;

/**
 * This class...
 */
public class DeleteIacucProtocolPersonnelRule extends ProtocolPersonnelRuleBase implements BusinessRuleInterface<DeleteIacucProtocolPersonnelEvent> {

    @Override
    public boolean processRules(DeleteIacucProtocolPersonnelEvent event) {
        IacucProtocolDocument protocolDocument = (IacucProtocolDocument)event.getDocument();
        return isPersonDeleteValid(protocolDocument);
    }

    @Override
    public ProtocolPersonnelService getProtocolPersonnelServiceHook() {
        return (ProtocolPersonnelService)KraServiceLocator.getService("iacucProtocolPersonnelService");
    }
    
    /**
     * This method is to check whether person delete is valid. 
     * protocol person may be handling procedures. So block delete if person is handling
     * procedures to avoid dependency error.
     * @param protocolDocument
     * @return
     */
    private boolean isPersonDeleteValid(IacucProtocolDocument protocolDocument) {
        boolean personDeleteValid = true;
        List<ProtocolPerson> protocolPersons = protocolDocument.getIacucProtocol().getProtocolPersons();
        List<IacucProtocolStudyGroupBean> protocolStudyGroups = protocolDocument.getIacucProtocol().getIacucProtocolStudyGroups();
        for (ProtocolPerson protocolPerson : protocolPersons) {
            if(protocolPerson.isDelete()) {
                int personIndex = protocolPersons.indexOf(protocolPerson);
                String personId = protocolPerson.getPersonId();
                if(isPersonHandlingProcedure(personId, protocolStudyGroups)) {
                    reportError(formatErrorPropertyName(false, personIndex, ""), KeyConstants.IACUC_PROTOCOL_PERSON_DEPENDENCY_EXISTS);
                    personDeleteValid = false;
                }
            }
        }
        return personDeleteValid;
    }
    
    /**
     * This method is to check whether deleted person is handling procedures
     * @param personId
     * @param protocolStudyGroups
     * @return
     */
    private boolean isPersonHandlingProcedure(String personId, List<IacucProtocolStudyGroupBean> protocolStudyGroups) {
        boolean personHandlingProcedure = false;
        for(IacucProtocolStudyGroupBean studyGroupBean : protocolStudyGroups) {
            for(IacucProtocolStudyGroupDetailBean studyGroupDetailBean : studyGroupBean.getIacucProtocolStudyGroupDetailBeans()) {
                for(IacucProtocolStudyGroup studyGroup : studyGroupDetailBean.getIacucProtocolStudyGroups()) {
                    for(IacucProcedurePersonResponsible procedurePerson : studyGroup.getIacucProcedurePersonsResponsible()) {
                        if(procedurePerson.getPersonId().equals(personId)) {
                            personHandlingProcedure = true;
                            break;
                        }
                    }
                }
            }
        }
        return personHandlingProcedure;
    }

}
