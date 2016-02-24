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
package org.kuali.kra.iacuc.actions.noreview;

import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.actions.noreview.ExecuteProtocolReviewNotRequiredRule;
import org.kuali.rice.krad.rules.rule.BusinessRule;


public class IacucProtocolReviewNotRequiredEvent extends KcDocumentEventBase {
    
    private IacucProtocolReviewNotRequiredBean actionBean;
    
    /**
     * 
     * Constructs a ProtocolReviewNotRequiredEvent.java.
     * @param document
     * @param actionBean
     */
    public IacucProtocolReviewNotRequiredEvent(ProtocolDocumentBase document, IacucProtocolReviewNotRequiredBean actionBean) {
        super("review not required for document " + getDocumentId(document), "", document);
        this.actionBean = actionBean;
        logEvent();
    }

    @Override
    public Class getRuleInterfaceClass() {
        return ExecuteProtocolReviewNotRequiredRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((ExecuteProtocolReviewNotRequiredRule) rule).processReviewNotRequiredRule((ProtocolDocumentBase) getDocument(), actionBean);
    }

    @Override
    protected void logEvent() {

        
    }

}
