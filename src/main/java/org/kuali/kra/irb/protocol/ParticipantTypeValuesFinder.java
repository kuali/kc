/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.protocol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.web.struts.form.ProtocolForm;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.KeyValuesService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/**
 * 
 * Finds the available set of supported Participant Types.  See
 * the method <code>getKeyValues()</code> for a full description.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ParticipantTypeValuesFinder extends KeyValuesBase {
    
    /**
     * Constructs the list of Protocol Participant Types.  Each entry
     * in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * type code and the "value" is the textual description that is viewed
     * by a user.  The list is obtained from the Participant Type database table
     * via the "keyValuesService".  The intent of this method is to provide 
     * a list which is viewed in a GUI.  As such, the first entry in the list 
     * is the generic &lt;"", "select:"&gt; option.
     * <p>
     * The list is also filtered based upon the participants currently in the
     * protocol document.  Users are not allowed to create two or more
     * participants with the same participant type.  Therefore, if an participant
     * type is already being used, it is removed from the list.  If the document
     * cannot be found, the entire list is returned (it is not filtered).
     * 
     * @return the list of &lt;key, value&gt; pairs of participant types.  The first entry
     * is always &lt;"", "select:"&gt;.
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyLabelPair> getKeyValues() {
        ProtocolDocument doc = getDocument();
        KeyValuesService keyValuesService = 
            (KeyValuesService) KraServiceLocator.getService("keyValuesService");
        Collection<ParticipantType> participantTypes = keyValuesService.findAll(ParticipantType.class);
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("", "select"));
        for (ParticipantType participantType : participantTypes) {
            if (!hasParticipant(doc, participantType)) {
                keyValues.add(new KeyLabelPair(participantType.getParticipantTypeCode(), 
                        participantType.getDescription()));
            }
        }
        return keyValues;
    }
    
    /**
     * Get the Protocol Document for the current session.  The
     * document is within the current form.
     * 
     * @return the current document or null if not found
     */
    private ProtocolDocument getDocument() {
        ProtocolDocument doc = null;
        ProtocolForm form = (ProtocolForm) GlobalVariables.getKualiForm();
        if (form != null) {
            doc = form.getDocument();
        }
        return doc;
    }
    
    /**
     * Does the document already have a participant using the given participant type?
     * 
     * @param doc, the Protocol Document.
     * @param participantType, the participant type to look for.
     * @return true if the participant type is found; otherwise false.
     */
    private boolean hasParticipant(ProtocolDocument doc, ParticipantType participantType) {
        if (doc != null) {
            List<ProtocolParticipant> protocolParticipants = doc.getProtocol().getProtocolParticipants();
            for (ProtocolParticipant protocolParticipant : protocolParticipants) {
                if (protocolParticipant.getParticipantTypeCode().
                        equals(participantType.getParticipantTypeCode())) {
                    return true;
                }
            }
        }
        return false;
    }
}
