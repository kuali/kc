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
package org.kuali.kra.iacuc.actions.request;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.iacuc.IacucProtocolDocument;

/**
 * This event is generated whenever a user makes a request to 
 * close the protocol, suspend it, close enrollment, re-open enrollment,
 * or request data analysis.
 */
@SuppressWarnings("unchecked")
public class IacucProtocolRequestEvent<T extends KcBusinessRule> extends KcDocumentEventBaseExtension {

    private IacucProtocolRequestBean requestBean;
    private String propertyKey;

    public IacucProtocolRequestEvent(IacucProtocolDocument document, String propertyKey, IacucProtocolRequestBean requestBean) {
        super("Iacuc Protocol Request", "", document);
        this.propertyKey = propertyKey;
        this.requestBean = requestBean;
    }
    
    public IacucProtocolDocument getProtocolDocument() {
        return (IacucProtocolDocument) getDocument();
    }
    
    public String getPropertyKey() {
        return propertyKey;
    }
    
    public IacucProtocolRequestBean getRequestBean() {
        return requestBean;
    }

    @Override
    public KcBusinessRule getRule() {
        return new IacucProtocolRequestRule();
    }
}
