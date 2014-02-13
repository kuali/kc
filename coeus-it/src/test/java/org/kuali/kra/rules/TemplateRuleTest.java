/*
 * Copyright 2005-2013 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.rules;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.rice.kns.util.KNSGlobalVariables;
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
        KNSGlobalVariables.setAuditErrorMap(new HashMap());  
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
