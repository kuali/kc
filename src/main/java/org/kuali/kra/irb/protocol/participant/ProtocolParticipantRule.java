/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * 
 * This class contains the rules to validate a <code>{@link ProtocolParticipant}</code>
 * 
 * @author Kuali Research Administration Team (kc.dev@kuali.org)
 */
public class ProtocolParticipantRule extends ResearchDocumentRuleBase implements AddProtocolParticipantRule {

    /**
     * 
     * Process the validation rules for an <code>{@link AddProtocolParticipantEvent}</code>
     * Participants with an invalid participant type code, duplicate participants (i.e. same
     * participant type code), or where the participant count is a non-positive number are not allowed.
     * 
     * @param addProtocolParticipantEvent
     * @return <code>true</code> if valid, <code>false</code> otherwise
     */
    public boolean processAddProtocolParticipantBusinessRules(AddProtocolParticipantEvent 
            addProtocolParticipantEvent) {
        final String PROPERTY_NAME_TYPE_CODE = Constants.PARTICIPANTS_PROPERTY_KEY +  ".participantTypeCode";
        final String PROPERTY_NAME_COUNT = Constants.PARTICIPANTS_PROPERTY_KEY + ".participantCount";
        boolean isValid = true;
        ProtocolParticipant protocolParticipant = addProtocolParticipantEvent.getProtocolParticipant();
        String participantTypeCode = protocolParticipant.getParticipantTypeCode(); 
        
        if (StringUtils.isBlank(participantTypeCode)) {
            isValid = false;
            reportError(PROPERTY_NAME_TYPE_CODE, KeyConstants.ERROR_PROTOCOL_PARTICIPANT_TYPE_NOT_SELECTED);
        } else if (!isValidParticipantTypeCode(participantTypeCode)) {
            isValid = false;
            reportError(PROPERTY_NAME_TYPE_CODE, KeyConstants.ERROR_PROTOCOL_PARTICIPANT_TYPE_INVALID);
        } else if (isDuplicate((ProtocolDocument) addProtocolParticipantEvent.getDocument(), 
                participantTypeCode)) {
            isValid = false;
            reportError(PROPERTY_NAME_TYPE_CODE, KeyConstants.ERROR_PROTOCOL_PARTICIPANT_TYPE_DUPLICATE);
        }

        Integer participantCount = addProtocolParticipantEvent.getProtocolParticipant().getParticipantCount();
        if ((participantCount != null) && (participantCount <= 0)) {
            isValid = false;
            reportError(PROPERTY_NAME_COUNT, KeyConstants.ERROR_PROTOCOL_PARTICIPANT_COUNT_INVALID);
        }

        return isValid;
    }
     
    /**
     * 
     * Validates the <code>{@link ParticipantTypeCode}</code>.  
     * 
     * @param participantTypeCode, the participant type code to validate.
     * @return <code>true</code> if valid, <code>false</code> otherwise
     */
    private boolean isValidParticipantTypeCode(String participantTypeCode) {
        boolean isValid = false;

        if (participantTypeCode != null) {
            // Query the database for a matching participant type code
            BusinessObjectService businessObjectService = 
                KraServiceLocator.getService(BusinessObjectService.class);
            Map<String,String> fieldValues = new HashMap<String,String>();
            fieldValues.put("participantTypeCode", participantTypeCode);
            if (businessObjectService.countMatching(ParticipantType.class, fieldValues) == 1) {
                isValid = true;
            }
        }
        return isValid;
    }

    /**
     * 
     * Check if the <code>{@link ParticipantTypeCode}</code> exists already as a 
     * <code>{@link ProtocolParticipant}</code>.
     * 
     * @param document, the protocol document to which the protocol participant is added
     * @param participantTypeCode, the participant type code of the protocol participant to be added
     * @return <code>true</code> if it is a duplicate, <code>false</code> otherwise
     */
    private boolean isDuplicate(ProtocolDocument document, String participantTypeCode) {
        List<ProtocolParticipant> protocolParticipants = document.getProtocol().getProtocolParticipants();
        for (ProtocolParticipant protocolParticipant : protocolParticipants) {
            if (protocolParticipant.getParticipantTypeCode().equals(participantTypeCode)) {
                return true;
            }
        }
        return false;        
    }
}
