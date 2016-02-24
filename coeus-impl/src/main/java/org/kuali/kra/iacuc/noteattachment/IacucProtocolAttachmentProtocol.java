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
package org.kuali.kra.iacuc.noteattachment;

import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentProtocolBase;

public class IacucProtocolAttachmentProtocol extends ProtocolAttachmentProtocolBase {

    private static final long serialVersionUID = 4879429021874546070L;
    private static final String GROUP_CODE = "1";
    
    public IacucProtocolAttachmentProtocol(ProtocolBase protocol) {
        super(protocol);
    }

    public IacucProtocolAttachmentProtocol() {
        super();
    }
    
    @Override
    public String getGroupCode() {
        return GROUP_CODE;
    }

    @Override
    public String getAttachmentDescription() {
        return "ProtocolBase Attachment";
    }

    @Override
    public boolean isDraft() {
        return IacucProtocolAttachmentStatus.DRAFT.equals(getDocumentStatusCode());
    }

    @Override
    public void setDraft() {
        setDocumentStatusCode(IacucProtocolAttachmentStatus.DRAFT);
    }

    @Override
    public boolean isFinal() {
        return IacucProtocolAttachmentStatus.FINALIZED.equals(getDocumentStatusCode());
    }

    @Override
    public void setFinal() {
        setDocumentStatusCode(IacucProtocolAttachmentStatus.FINALIZED);
    }

    @Override
    public boolean isDeleted() {
        return IacucProtocolAttachmentStatus.DELETED.equals(getDocumentStatusCode());
    }

    @Override
    public void setDeleted() {
        setDocumentStatusCode(IacucProtocolAttachmentStatus.DELETED);
    }

}
