/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.rule.event;

import org.kuali.kra.logging.TraceLogProxyFactory;
import org.kuali.kra.logging.Traceable;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.ChangeKeyPersonRule;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.rule.BusinessRule;
import org.kuali.rice.kns.rule.event.KualiDocumentEvent;


/**
 * Event class for actions that trigger modification of a <code>{@link ProposalPerson}</code> added to a <code>{@link ProposalDocument}</code>
 * 
 */
public class ChangeKeyPersonEvent extends KeyPersonEventBase implements KualiDocumentEvent, Traceable<ChangeKeyPersonEvent> {
    
    private BusinessObject source;
    private int index;
        
    /**
     * Default Constructor
     * 
     * @param document
     * @param person
     * @param source business object
     */
    public ChangeKeyPersonEvent(ProposalDevelopmentDocument document, ProposalPerson person, BusinessObject source, int index) {
        this("", document, person, source ,index);
    }

    /**
     * Default Constructor
     * 
     * @param document
     * @param person
     * @param source business object 
     */
    public ChangeKeyPersonEvent(String errorPathPrefix, ProposalDevelopmentDocument document, ProposalPerson person, BusinessObject source,int index) {
        super("add BusinessObject to person " + person, errorPathPrefix, document, person);
        setSource(source);
        this.index=index;
       
    }

    /**
     * Read access to source
     * 
     * @return source of the event
     */
    public BusinessObject getSource() {
        return source;
    }

    /**
     * Write access to source
     * 
     * @param source
     */
    public void setSource(BusinessObject source) {
        this.source = source;
    }

    /**
     * @see org.kuali.rice.kns.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class<?> getRuleInterfaceClass() {
        return ChangeKeyPersonRule.class;
    }

    /**
     * @see org.kuali.rice.kns.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.rice.kns.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        //GlobalVariables.getErrorMap().addToErrorPath(getErrorPathPrefix());
        boolean retval = ((ChangeKeyPersonRule) rule).processChangeKeyPersonBusinessRules(getProposalPerson(), getSource(),index);
       // GlobalVariables.getErrorMap().removeFromErrorPath(getErrorPathPrefix());
        
        return retval;
    }
    
    /**
     * 
     * @see org.kuali.kra.logging.Traceable#getProxy(java.lang.Object)
     */
    public ChangeKeyPersonEvent getProxy(ChangeKeyPersonEvent archetype) {
        if (archetype == null) {
            archetype = this;
        }
        return TraceLogProxyFactory.getProxyFor(archetype);
    }
}
