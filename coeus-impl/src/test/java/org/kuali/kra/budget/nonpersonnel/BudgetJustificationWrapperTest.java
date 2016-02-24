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
package org.kuali.kra.budget.nonpersonnel;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetJustificationWrapper;

import java.sql.Date;

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
