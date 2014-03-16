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
package org.kuali.kra.irb.protocol.participant;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.List;

/**
 * This class contains the rules to validate a <code>{@link ProtocolParticipant}</code>.
 * 
 * @author Kuali Research Administration Team (kc.dev@kuali.org)
 */
public class ProtocolParticipantRuleBase extends KcTransactionalDocumentRuleBase {
    
    private static final String DOT = ".";
    
    private static final String PARTICIPANT_TYPE_CODE_FIELD = "participantTypeCode";
    private DictionaryValidationService dictionaryValidationService;
    /**
     * ProcessDefinitionDefinitionDefinition the validation rules for an <code>{@link AddProtocolParticipantEvent}</code>
     * Participants with an invalid participant type code, duplicate participants (i.e. same
     * participant type code), or where the participant count is a non-positive number are not allowed.
     * 
     * @param addProtocolParticipantEvent
     * @return <code>true</code> if valid, <code>false</code> otherwise
     */
    public boolean processAddProtocolParticipantEvent(AddProtocolParticipantEvent addProtocolParticipantEvent) {
        boolean rulePassed = true;
        
        ProtocolParticipant newProtocolParticipant = addProtocolParticipantEvent.getNewProtocolParticipant();
        List<ProtocolParticipant> protocolParticipants = addProtocolParticipantEvent.getProtocolParticipants();
        String errorPath = addProtocolParticipantEvent.getErrorPathPrefix();
        getKnsDictionaryValidationService().validateBusinessObject(newProtocolParticipant);
        rulePassed &= GlobalVariables.getMessageMap().hasNoErrors();

        rulePassed &= validateUniqueParticipantType(newProtocolParticipant, protocolParticipants, errorPath);
        
        return rulePassed;
    }
    
    private boolean validateUniqueParticipantType(ProtocolParticipant newProtocolParticipant, List<ProtocolParticipant> protocolParticipants, 
            String errorPath) {
        
        boolean isValid = true;
        
        for (ProtocolParticipant protocolParticipant : protocolParticipants) {
            if (StringUtils.equals(protocolParticipant.getParticipantTypeCode(), newProtocolParticipant.getParticipantTypeCode())) {
                isValid = false;
                reportError(errorPath + DOT + PARTICIPANT_TYPE_CODE_FIELD, KeyConstants.ERROR_PROTOCOL_PARTICIPANT_TYPE_DUPLICATE);
                break;
            }
        }
        
        return isValid;
    }

    protected DictionaryValidationService getKnsDictionaryValidationService() {
        if (this.dictionaryValidationService == null) {
            this.dictionaryValidationService = KNSServiceLocator.getKNSDictionaryValidationService();
        }
        return this.dictionaryValidationService;
    }

}