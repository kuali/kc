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
package org.kuali.kra.protocol.noteattachment;

/**
 * Implementation of {@link AddProtocolAttachmentProtocolRule AddProtocolAttachmentProtocolRule}.
 * @see AddProtocolAttachmentProtocolRule for details
 */
public abstract class AddProtocolAttachmentProtocolRuleImplBase implements AddProtocolAttachmentProtocolRule {

    protected ProtocolAttachmentBaseRuleHelper baseHelper;
    protected ProtocolAttachmentProtocolRuleHelperBase protocolHelper;

    @Override
    public boolean processAddProtocolAttachmentProtocolRules(AddProtocolAttachmentProtocolEvent event) {      
        
        final ProtocolAttachmentProtocolBase newAttachmentProtocol = event.getNewAttachmentProtocol();
        
        boolean valid = this.baseHelper.validPrimitiveFields(newAttachmentProtocol);
        valid &= this.baseHelper.validTypeForGroup(newAttachmentProtocol);
        valid &= this.baseHelper.validDescriptionWhenRequired(newAttachmentProtocol);
        valid &= this.protocolHelper.validStatus(newAttachmentProtocol);
        valid &= this.baseHelper.validFile(newAttachmentProtocol);
        
        return valid;
    }
}
