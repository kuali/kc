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
package org.kuali.kra.irb.protocol.participant;

import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.KeyValuesService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 
 * Finds the available set of supported Participant Types.  See
 * the method <code>getKeyValues()</code> for a full description.
 * 
 * @author Kuali Research Administration Team (kc.dev@kuali.org)
 */
public class ParticipantTypeValuesFinder extends FormViewAwareUifKeyValuesFinderBase {
    

    private static final long serialVersionUID = 6315943281880005921L;

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
     */
    @Override
    public List<KeyValue> getKeyValues() {
        ProtocolDocument doc = (ProtocolDocument) getDocument();
        KeyValuesService keyValuesService = 
            (KeyValuesService) KcServiceLocator.getService("keyValuesService");
        Collection<ParticipantType> participantTypes = keyValuesService.findAll(ParticipantType.class);
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));
        for (ParticipantType participantType : participantTypes) {
            if (!hasParticipant(doc, participantType)) {
                keyValues.add(new ConcreteKeyValue(participantType.getParticipantTypeCode(), 
                        participantType.getDescription()));
            }
        }
        return keyValues;
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
            List<ProtocolParticipant> protocolParticipants = ((Protocol) doc.getProtocol()).getProtocolParticipants();
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
