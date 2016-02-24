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
