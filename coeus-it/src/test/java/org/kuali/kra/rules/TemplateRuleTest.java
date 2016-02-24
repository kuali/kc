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
package org.kuali.kra.rules;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("unchecked")
public abstract class TemplateRuleTest<E extends KcDocumentEventBaseExtension, R extends KcBusinessRule> {
    
    protected E event;
     
    protected R rule;
    
    protected boolean expectedReturnValue;

    public TemplateRuleTest() {
        GlobalVariables.setMessageMap(new MessageMap());
        GlobalVariables.setAuditErrorMap(new HashMap());  
        prerequisite(); 
        assertEquals(expectedReturnValue, rule.processRules(event));
        checkRuleAssertions();
    }
    

    protected abstract void prerequisite();

    public void checkRuleAssertions() {
    ;
    }
    
    protected MessageMap getErrorMap() {
        return GlobalVariables.getMessageMap();
    }
}
