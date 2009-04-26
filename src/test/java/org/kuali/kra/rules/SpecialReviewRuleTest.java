/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.rules;

import java.sql.Date;
import java.text.DateFormat;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.bo.AbstractSpecialReview;
import org.kuali.kra.bo.AbstractSpecialReviewExemption;
import org.kuali.kra.bo.SpecialReview;
import org.kuali.kra.bo.SpecialReviewApprovalType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rule.SpecialReviewRule;
import org.kuali.kra.rule.event.AddSpecialReviewEvent;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ErrorMessage;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.TypedArrayList;

public class SpecialReviewRuleTest extends KraTestBase {

    private static final String NEW_SPECIAL_REVIEW = "newSpecialReview";
    private SpecialReviewRule rule = null;
    private DateFormat dateFormat;
    private List<SpecialReviewApprovalType> approvalTypeCodes;
    private List<SpecialReview> specialReviewCodes;
    private BusinessObjectService bos;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new SpecialReviewRulesImpl();
        dateFormat = DateFormat.getDateInstance();
        bos = KraServiceLocator.getService(BusinessObjectService.class);
        approvalTypeCodes = (List)bos.findAll(SpecialReviewApprovalType.class);
        specialReviewCodes = (List)bos.findAll(SpecialReview.class);
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        dateFormat = null;
        bos = null;
        approvalTypeCodes=null;
        specialReviewCodes=null;
        super.tearDown();
    }

    /** 
     * 
     * This method approval type and special review codes are OK before do the real rule test
     */
    @Test
    public void checkCodes() {
        assertNotNull(approvalTypeCodes);
        assertNotNull(specialReviewCodes);
        assertTrue(approvalTypeCodes.size()>1);
        assertTrue(specialReviewCodes.size()>1);
    }


    /**
     * Test a good case.
     * 
     * @throws Exception
     */
    @Test
    public void testOK() throws Exception {

        Document document = getSomeDocument();
 
        AbstractSpecialReview newSpecialReview = new MockSpecialReview();
        newSpecialReview.setApprovalTypeCode(approvalTypeCodes.get(0).getApprovalTypeCode());
        newSpecialReview.setSpecialReviewCode(specialReviewCodes.get(0).getSpecialReviewCode());
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent addSpecialReviewEvent = new AddSpecialReviewEvent(Constants.EMPTY_STRING, document,newSpecialReview);
        assertTrue(rule.processAddSpecialReviewEvent(addSpecialReviewEvent));
    }

    /**
     * Test adding an proposal special with unspecified approval status . This corresponds to a empty string type code, i.e. the user
     * didn't select approval status.
     * 
     * @throws Exception
     */
    @Test
    public void testUnspecifiedApprovalTypeCode() throws Exception {
        Document document = getSomeDocument();

        AbstractSpecialReview newSpecialReview = new MockSpecialReview();
        //newProposalSpecialReview.setApprovalTypeCode(approvalTypeCodes.get(0).getApprovalTypeCode());
        newSpecialReview.setSpecialReviewCode(specialReviewCodes.get(0).getSpecialReviewCode());
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent addSpecialReviewEvent = new AddSpecialReviewEvent(Constants.EMPTY_STRING, document,
                newSpecialReview);
        assertFalse(rule.processAddSpecialReviewEvent(addSpecialReviewEvent));

        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages(NEW_SPECIAL_REVIEW + ".approvalTypeCode");
        assertTrue(errors.size() == 1);

        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_REQUIRED_SELECT_APPROVAL_STATUS);
    }

    private Document getSomeDocument() throws WorkflowException, Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        Document document = getDocumentService().getNewDocument(AwardDocument.class);
        return document;
    }

    /**
     * Test adding an  special with unspecified special review code . This corresponds to a empty string type code, i.e. the user
     * didn't select special review code.
     * 
     * @throws Exception
     */
    @Test
    public void testUnspecifiedSpecialReviewCode() throws Exception {
        Document document = getSomeDocument();

        AbstractSpecialReview newSpecialReview = new MockSpecialReview();
        newSpecialReview.setApprovalTypeCode(approvalTypeCodes.get(0).getApprovalTypeCode());
        //newProposalSpecialReview.setSpecialReviewCode(specialReviewCodes.get(0).getSpecialReviewCode());
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent addSpecialReviewEvent = new AddSpecialReviewEvent(Constants.EMPTY_STRING, document,
            newSpecialReview);
        assertFalse(rule.processAddSpecialReviewEvent(addSpecialReviewEvent));

        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages(NEW_SPECIAL_REVIEW + ".specialReviewCode");
        assertTrue(errors.size() == 1);

        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_REQUIRED_SELECT_SPECIAL_REVIEW_CODE);
    }

    /**
     * Test adding an  special with approval data before application date. 
     * 
     * @throws Exception
     */
    @Test
    public void testApprovalDateBeforeApplicationDate() throws Exception {
        Document document = getSomeDocument();

        AbstractSpecialReview newSpecialReview = new MockSpecialReview();
        newSpecialReview.setApprovalTypeCode(approvalTypeCodes.get(0).getApprovalTypeCode());
        newSpecialReview.setSpecialReviewCode(specialReviewCodes.get(0).getSpecialReviewCode());
        // 08/01/2008 > 08/01/2007
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2008").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<MockSpecialReview> addProposalSpecialReviewEvent = new AddSpecialReviewEvent(Constants.EMPTY_STRING, document,
            newSpecialReview);
        assertFalse(rule.processAddSpecialReviewEvent(addProposalSpecialReviewEvent));

        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages(NEW_SPECIAL_REVIEW + ".approvalDate");
        assertTrue(errors.size() == 1);

        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_APPROVAL_DATE_BEFORE_APPLICATION_DATE_SPECIALREVIEW);
    }
    
}
class MockSpecialReview extends AbstractSpecialReview<MockSpecialReviewExemption>{
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6160571834897677669L;

    @Override
    public MockSpecialReviewExemption newSpecialReviewExemption(String exemptionTypeCode) {
        return new MockSpecialReviewExemption();
    }

    @Override
    public Long getSpecialReviewId() {
        return null;
    }
}
class MockSpecialReviewExemption extends AbstractSpecialReviewExemption{

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8085176837230519594L;
    
}

