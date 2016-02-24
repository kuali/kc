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
package org.kuali.kra.award.rule;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.award.rule.event.AwardCommentsRuleEvent;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.List;
import static org.junit.Assert.*;
public class AwardCommentsRuleTest extends KcIntegrationTestBase {
    private static final String ERROR_PATH_PREFIX = "document.awards[0]";
    private static final String VALID_COMMENT = "This comment should pass validation";
    private static final int MAX_COMMENT_LENGTH = 5000;
    
    private AwardCommentsRule awardCommentsRule;
    
    @Before
    public void setUp() throws Exception {
        GlobalVariables.setMessageMap(new MessageMap());
        awardCommentsRule = new AwardCommentsRuleImpl();
    }
    
    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testProcessAwardCommentsBusinessRules() throws Exception {
        AwardDocument document = new AwardDocument();   // can't use JMock for an AwardDocument b/c toString() is final
        Award award = document.getAward();
        AwardComment awardComment = null;
        
        // an award with no comments should pass validation
        assertNoErrors(document);

        // try adding one AwardComment
        awardComment = award.getAwardPaymentAndInvoiceRequirementsComments();
        assertEquals(1, award.getAwardComments().size());
        assertNotNull(awardComment);
        assertEquals("", awardComment.getComments());
        assertNoErrors(document);
        
        // try adding another one
        awardComment = award.getAwardFandaRateComment();
        assertEquals(2, award.getAwardComments().size());
        assertNotNull(awardComment);
        assertEquals("", awardComment.getComments());
        assertNoErrors(document);

        // set a valid comment
        awardComment.setComments(VALID_COMMENT);
        assertEquals(VALID_COMMENT, awardComment.getComments());
        assertNoErrors(document);
        
        // set another valid comment
        String validLongComment = generateComment(MAX_COMMENT_LENGTH);
        awardComment.setComments(validLongComment);
        assertEquals(validLongComment, awardComment.getComments());
        assertNoErrors(document);
        
        // set an invalid comment
        String invalidLongComment = generateComment(MAX_COMMENT_LENGTH + 1);
        awardComment.setComments(invalidLongComment);
        assertEquals(invalidLongComment, awardComment.getComments());
        String propertyKey = ERROR_PATH_PREFIX + "." + "awardFandaRateComment.comments";
        assertOneError(document, propertyKey, KeyConstants.ERROR_MAXLENGTH);
    }
    
    private void assertNoErrors(AwardDocument document) {
        AwardCommentsRuleEvent ruleEvent = new AwardCommentsRuleEvent(ERROR_PATH_PREFIX, document);
        boolean result = awardCommentsRule.processAwardCommentsBusinessRules(ruleEvent);
        assertTrue(result);
        assertEquals(0, GlobalVariables.getMessageMap().getErrorCount());
    }
    
    private void assertOneError(AwardDocument document, String propertyKey, String expectedErrorKey) {
        AwardCommentsRuleEvent ruleEvent = new AwardCommentsRuleEvent(ERROR_PATH_PREFIX, document);
        boolean result = awardCommentsRule.processAwardCommentsBusinessRules(ruleEvent);
        assertFalse(result);
        assertEquals(1, GlobalVariables.getMessageMap().getErrorCount());
        
        List errorMessages = GlobalVariables.getMessageMap().getErrorMessagesForProperty(propertyKey);
        assertNotNull(errorMessages);
        assertEquals(1, errorMessages.size());
        String actualErrorKey = ((ErrorMessage)errorMessages.get(0)).getErrorKey();
        assertEquals(expectedErrorKey, actualErrorKey);
    }
    
    private String generateComment(int length) {
        StringBuffer comment = new StringBuffer();
        for (int i=0; i<length; i++) {
            comment = comment.append('x');
        }
        return comment.toString();
    }
}
