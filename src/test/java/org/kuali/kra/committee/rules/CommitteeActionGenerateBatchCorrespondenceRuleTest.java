/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.committee.rules;

import java.sql.Date;

import org.junit.Test;
import org.kuali.kra.committee.rule.event.CommitteeActionGenerateBatchCorrespondenceEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rules.TemplateRuleTest;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

public class CommitteeActionGenerateBatchCorrespondenceRuleTest extends KcUnitTestBase {
    
    @Test
    public void testTrue() {
        
        new TemplateRuleTest<CommitteeActionGenerateBatchCorrespondenceEvent, CommitteeActionGenerateBatchCorrespondenceRule>() {

            @Override
            protected void prerequisite() {
                String batchCorrespondenceTypeCode = "1";
                Date startDate = Date.valueOf("2010-01-01");
                Date endDate = Date.valueOf("2010-12-31");
                String committeeId = "Committee1";
                
                event = new CommitteeActionGenerateBatchCorrespondenceEvent(Constants.EMPTY_STRING, null, batchCorrespondenceTypeCode, startDate, endDate, committeeId);
                rule = new CommitteeActionGenerateBatchCorrespondenceRule();
                expectedReturnValue = true;
            }
        };
    }

    @Test
    public void testFalseMissingBatchCorrespondence() {
        
        new TemplateRuleTest<CommitteeActionGenerateBatchCorrespondenceEvent, CommitteeActionGenerateBatchCorrespondenceRule>() {

            @Override
            protected void prerequisite() {
                String batchCorrespondenceTypeCode = null;
                Date startDate = Date.valueOf("2010-01-01");
                Date endDate = Date.valueOf("2010-12-31");
                String committeeId = "Committee1";
                
                event = new CommitteeActionGenerateBatchCorrespondenceEvent(Constants.EMPTY_STRING, null, batchCorrespondenceTypeCode, startDate, endDate, committeeId);
                rule = new CommitteeActionGenerateBatchCorrespondenceRule();
                expectedReturnValue = false;
            }
        };
    }

    @Test
    public void testFalseMissingStartDate() {
        
        new TemplateRuleTest<CommitteeActionGenerateBatchCorrespondenceEvent, CommitteeActionGenerateBatchCorrespondenceRule>() {

            @Override
            protected void prerequisite() {
                String batchCorrespondenceTypeCode = "1";
                Date startDate = null;
                Date endDate = Date.valueOf("2010-12-31");
                String committeeId = "Committee1";
                
                event = new CommitteeActionGenerateBatchCorrespondenceEvent(Constants.EMPTY_STRING, null, batchCorrespondenceTypeCode, startDate, endDate, committeeId);
                rule = new CommitteeActionGenerateBatchCorrespondenceRule();
                expectedReturnValue = false;
            }
        };
    }

    @Test
    public void testFalseMissingEndDate() {
        
        new TemplateRuleTest<CommitteeActionGenerateBatchCorrespondenceEvent, CommitteeActionGenerateBatchCorrespondenceRule>() {

            @Override
            protected void prerequisite() {
                String batchCorrespondenceTypeCode = "1";
                Date startDate = Date.valueOf("2010-01-01");
                Date endDate = null;
                String committeeId = "Committee1";
                
                event = new CommitteeActionGenerateBatchCorrespondenceEvent(Constants.EMPTY_STRING, null, batchCorrespondenceTypeCode, startDate, endDate, committeeId);
                rule = new CommitteeActionGenerateBatchCorrespondenceRule();
                expectedReturnValue = false;
            }
        };
    }

    @Test
    public void testFalseEndDateBeforeStartDate() {
        
        new TemplateRuleTest<CommitteeActionGenerateBatchCorrespondenceEvent, CommitteeActionGenerateBatchCorrespondenceRule>() {

            @Override
            protected void prerequisite() {
                String batchCorrespondenceTypeCode = "1";
                Date startDate = Date.valueOf("2010-12-31");
                Date endDate = Date.valueOf("2010-01-01");
                String committeeId = "Committee1";
                
                event = new CommitteeActionGenerateBatchCorrespondenceEvent(Constants.EMPTY_STRING, null, batchCorrespondenceTypeCode, startDate, endDate, committeeId);
                rule = new CommitteeActionGenerateBatchCorrespondenceRule();
                expectedReturnValue = false;
            }
        };
    }

}
