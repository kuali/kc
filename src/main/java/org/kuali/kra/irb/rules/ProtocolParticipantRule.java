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
package org.kuali.kra.irb.rules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.bo.ParticipantType;
import org.kuali.kra.irb.bo.ProtocolParticipant;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.rule.AddProtocolParticipantRule;
import org.kuali.kra.irb.rule.event.AddProtocolParticipantEvent;
import org.kuali.kra.irb.rule.event.AddProtocolReferenceEvent;

public class ProtocolParticipantRule extends ProtocolDocumentRule implements AddProtocolParticipantRule {

    /**
     * Don't allow participants with an invalid participant type code, duplicate participants (i.e.
     * same participant type code), or where the participant count in a non-positive number.
     * 
     * @see org.kuali.kra.irb.rule.ParticipantRule#processAddParticipantBusinessRules(org.kuali.kra.irb.document.ProtocolDocument, org.kuali.kra.irb.bo.ProtocolParticipant)
     */
    public boolean processAddProtocolParticipantBusinessRules(AddProtocolParticipantEvent addProtocolParticipantEvent) {
        boolean isValid = true;
        String participantTypeCode = addProtocolParticipantEvent.getProtocolParticipant().getParticipantTypeCode();
        
        if (StringUtils.isBlank(participantTypeCode)) {
            isValid = false;
            reportError(Constants.PARTICIPANTS_PROPERTY_KEY + ".participantTypeCode", KeyConstants.ERROR_PROTOCOL_PARTICIPANT_TYPE_NOT_SELECTED, "Description");
        }
        else if (isInvalid(participantTypeCode)) {
            isValid = false;
            reportError(Constants.PARTICIPANTS_PROPERTY_KEY + ".participantTypeCode", KeyConstants.ERROR_PROTOCOL_PARTICIPANT_TYPE_INVALID, "Description");
        }
        else if (isDuplicate((ProtocolDocument)addProtocolParticipantEvent.getDocument(), participantTypeCode)) {
            isValid = false;
            reportError(Constants.PARTICIPANTS_PROPERTY_KEY + ".participantTypeCode", KeyConstants.ERROR_PROTOCOL_PARTICIPANT_TYPE_DUPLICATE, "Description");
        }

        Integer participantCount = addProtocolParticipantEvent.getProtocolParticipant().getParticipantCount();
        if ((participantCount != null) && (participantCount <= 0)) {
            isValid = false;
            reportError(Constants.PARTICIPANTS_PROPERTY_KEY + ".participantCount", KeyConstants.ERROR_PROTOCOL_PARTICIPANT_COUNT_INVALID, "Count");
        }

        return isValid;
    }
    
 
    /**
     * Is this an invalid participant type code?  Query the database for a matching participant
     * type code.  If found, it is valid; otherwise it is invalid.
     * 
     * @param participantTypeCode, the participant type code to test against.
     * @return true if invalid; false if valid
     */
    private boolean isInvalid(String participantTypeCode) {
        if (participantTypeCode != null) {
            BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
            Map<String,String> fieldValues = new HashMap<String,String>();
            fieldValues.put("participantTypeCode", participantTypeCode);
            if (businessObjectService.countMatching(ParticipantType.class, fieldValues) == 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Is this a duplicate participant?  Participants must have a unique participant type code.
     * If a participant already exists with the same participant type code, do not allow
     * the next participant.
     * 
     * @param document, the protocol document
     * @param participantTypeCode, the participant type code to compare against
     * @return true if it is a duplicate; otherwise false
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
