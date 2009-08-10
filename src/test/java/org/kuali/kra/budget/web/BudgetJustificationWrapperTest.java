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
package org.kuali.kra.budget.web;

import java.sql.Date;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.kra.budget.nonpersonnel.BudgetJustificationWrapper;

public class BudgetJustificationWrapperTest {
    private static final String JUSTIFICATION_TEXT_WITH_XML_MARKUP = "A justification with <xml> markup";
    private static final Date TEST_DATE = new Date(System.currentTimeMillis());
    
    @Test
    public void testParsingBudgetJustification() throws Exception {
        BudgetJustificationWrapper budgetDocumentWrapper = new BudgetJustificationWrapper(TEST_DATE, "testUser", JUSTIFICATION_TEXT_WITH_XML_MARKUP);
        String xmlSerialization = budgetDocumentWrapper.toString();
        
        BudgetJustificationWrapper testBudgetDocumentWrapper = new BudgetJustificationWrapper(xmlSerialization);
        Assert.assertEquals(JUSTIFICATION_TEXT_WITH_XML_MARKUP, testBudgetDocumentWrapper.getJustificationText());
        
        
        Assert.assertEquals(xmlSerialization, testBudgetDocumentWrapper.toString());
    }
}
