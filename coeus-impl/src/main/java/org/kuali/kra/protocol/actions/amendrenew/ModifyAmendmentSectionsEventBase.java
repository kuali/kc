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
package org.kuali.kra.protocol.actions.amendrenew;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.protocol.ProtocolDocumentBase;

/**
 * When amendment sections are modified, this event is generated.
 */
@SuppressWarnings("unchecked")
public abstract class ModifyAmendmentSectionsEventBase extends KcDocumentEventBaseExtension {

    private ProtocolAmendmentBean amendmentBean;
    private String propertyName;
    private boolean amendment;

    public ModifyAmendmentSectionsEventBase(ProtocolDocumentBase document, String propertyName, ProtocolAmendmentBean amendmentBean) {
        super("Modify Amendment Sections", "", document);
        this.propertyName = propertyName;
        this.amendmentBean = amendmentBean;
        this.amendment = getProtocolDocument().getProtocol().isAmendment();
    }
    
    public ProtocolDocumentBase getProtocolDocument() {
        return (ProtocolDocumentBase) getDocument();
    }
    
    public String getPropertyName() {
        return propertyName;
    }
    
    public ProtocolAmendmentBean getAmendmentBean() {
        return amendmentBean;
    }
    
    public boolean isAmendment() {
        return amendment;
    }

    @Override
    public abstract KcBusinessRule getRule();

}
