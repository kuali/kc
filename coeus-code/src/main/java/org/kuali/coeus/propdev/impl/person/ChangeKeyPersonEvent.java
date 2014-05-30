/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.person;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.rules.rule.BusinessRule;
import org.kuali.rice.krad.rules.rule.event.DocumentEvent;


/**
 * Event class for actions that trigger modification of a <code>{@link ProposalPerson}</code> added to a <code>{@link ProposalDevelopmentDocument}</code>
 * 
 */
public class ChangeKeyPersonEvent extends KeyPersonEventBase implements DocumentEvent {
    
    private BusinessObject source;
    private int index;

    public ChangeKeyPersonEvent(ProposalDevelopmentDocument document, ProposalPerson person, BusinessObject source, int index) {
        this("", document, person, source ,index);
    }

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

    @Override
    public Class<ChangeKeyPersonRule> getRuleInterfaceClass() {
        return ChangeKeyPersonRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        //GlobalVariables.getMessageMap().addToErrorPath(getErrorPathPrefix());
        boolean retval = ((ChangeKeyPersonRule) rule).processChangeKeyPersonBusinessRules(getProposalPerson(), getSource(),index);
       // GlobalVariables.getMessageMap().removeFromErrorPath(getErrorPathPrefix());
        
        return retval;
    }
}
