/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.rules;

import java.sql.Date;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.ErrorMessage;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.TypedArrayList;
import org.kuali.kra.bo.SpecialReview;
import org.kuali.kra.bo.SpecialReviewApprovalType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalSpecialReviewEvent;

public class ProposalDevelopmentProposalSpecialReviewRuleTest extends ProposalDevelopmentRuleTestBase {

    private static final String NEW_PROPOSAL_SPECIAL_REVIEW = "newPropSpecialReview";
    private ProposalDevelopmentProposalSpecialReviewRule rule = null;
    private DateFormat dateFormat;
    private List<SpecialReviewApprovalType> approvalTypeCodes;
    private List<SpecialReview> specialReviewCodes;
    private BusinessObjectService bos;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ProposalDevelopmentProposalSpecialReviewRule();
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

        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
 
        ProposalSpecialReview newProposalSpecialReview = new ProposalSpecialReview();
        newProposalSpecialReview.setApprovalTypeCode(approvalTypeCodes.get(0).getApprovalTypeCode());
        newProposalSpecialReview.setSpecialReviewCode(specialReviewCodes.get(0).getSpecialReviewCode());
        newProposalSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newProposalSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddProposalSpecialReviewEvent addProposalSpecialReviewEvent = new AddProposalSpecialReviewEvent(Constants.EMPTY_STRING, document,
            newProposalSpecialReview);
        assertTrue(rule.processAddProposalSpecialReviewBusinessRules(addProposalSpecialReviewEvent));
    }

    /**
     * Test adding an proposal special with unspecified approval status . This corresponds to a empty string type code, i.e. the user
     * didn't select approval status.
     * 
     * @throws Exception
     */
    @Test
    public void testUnspecifiedApprovalTypeCode() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();

        ProposalSpecialReview newProposalSpecialReview = new ProposalSpecialReview();
        //newProposalSpecialReview.setApprovalTypeCode(approvalTypeCodes.get(0).getApprovalTypeCode());
        newProposalSpecialReview.setSpecialReviewCode(specialReviewCodes.get(0).getSpecialReviewCode());
        newProposalSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newProposalSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddProposalSpecialReviewEvent addProposalSpecialReviewEvent = new AddProposalSpecialReviewEvent(Constants.EMPTY_STRING, document,
            newProposalSpecialReview);
        assertFalse(rule.processAddProposalSpecialReviewBusinessRules(addProposalSpecialReviewEvent));

        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages(NEW_PROPOSAL_SPECIAL_REVIEW + ".approvalTypeCode");
        assertTrue(errors.size() == 1);

        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_REQUIRED_SELECT_APPROVAL_STATUS);
    }

    /**
     * Test adding an proposal special with unspecified special review code . This corresponds to a empty string type code, i.e. the user
     * didn't select special review code.
     * 
     * @throws Exception
     */
    @Test
    public void testUnspecifiedSpecialReviewCode() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();

        ProposalSpecialReview newProposalSpecialReview = new ProposalSpecialReview();
        newProposalSpecialReview.setApprovalTypeCode(approvalTypeCodes.get(0).getApprovalTypeCode());
        //newProposalSpecialReview.setSpecialReviewCode(specialReviewCodes.get(0).getSpecialReviewCode());
        newProposalSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newProposalSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddProposalSpecialReviewEvent addProposalSpecialReviewEvent = new AddProposalSpecialReviewEvent(Constants.EMPTY_STRING, document,
            newProposalSpecialReview);
        assertFalse(rule.processAddProposalSpecialReviewBusinessRules(addProposalSpecialReviewEvent));

        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages(NEW_PROPOSAL_SPECIAL_REVIEW + ".specialReviewCode");
        assertTrue(errors.size() == 1);

        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_REQUIRED_SELECT_SPECIAL_REVIEW_CODE);
    }

    /**
     * Test adding an proposal special with approval data before application date. 
     * 
     * @throws Exception
     */
    @Test
    public void testApprovalDateBeforeApplicationDate() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();

        ProposalSpecialReview newProposalSpecialReview = new ProposalSpecialReview();
        newProposalSpecialReview.setApprovalTypeCode(approvalTypeCodes.get(0).getApprovalTypeCode());
        newProposalSpecialReview.setSpecialReviewCode(specialReviewCodes.get(0).getSpecialReviewCode());
        // 08/01/2008 > 08/01/2007
        newProposalSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2008").getTime()));
        newProposalSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddProposalSpecialReviewEvent addProposalSpecialReviewEvent = new AddProposalSpecialReviewEvent(Constants.EMPTY_STRING, document,
            newProposalSpecialReview);
        assertFalse(rule.processAddProposalSpecialReviewBusinessRules(addProposalSpecialReviewEvent));

        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages(NEW_PROPOSAL_SPECIAL_REVIEW + ".approvalDate");
        assertTrue(errors.size() == 1);

        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_APPROVAL_DATE_BEFORE_APPLICATION_DATE_SPECIALREVIEW);
    }

}
