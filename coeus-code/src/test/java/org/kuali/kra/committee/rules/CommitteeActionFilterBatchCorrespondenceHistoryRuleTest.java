/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.junit.Test;
import org.kuali.coeus.common.committee.impl.rule.event.CommitteeActionFilterBatchCorrespondenceHistoryEvent;
import org.kuali.coeus.common.committee.impl.rules.CommitteeActionFilterBatchCorrespondenceHistoryRule;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rules.TemplateRuleTest;

import java.sql.Date;

public class CommitteeActionFilterBatchCorrespondenceHistoryRuleTest {

    @Test
    public void testTrue() {
        
        new TemplateRuleTest<CommitteeActionFilterBatchCorrespondenceHistoryEvent, CommitteeActionFilterBatchCorrespondenceHistoryRule>() {

            @Override
            protected void prerequisite() {
                String batchCorrespondenceTypeCode = "1";
                Date startDate = Date.valueOf("2010-01-01");
                Date endDate = Date.valueOf("2010-12-31");
                
                event = new CommitteeActionFilterBatchCorrespondenceHistoryEvent(Constants.EMPTY_STRING, null, batchCorrespondenceTypeCode, startDate, endDate);
                rule = new CommitteeActionFilterBatchCorrespondenceHistoryRule();
                rule.setErrorReporter(new ErrorReporter());
                expectedReturnValue = true;
            }
        };
    }

    @Test
    public void testFalseMissingBatchCorrespondence() {
        
        new TemplateRuleTest<CommitteeActionFilterBatchCorrespondenceHistoryEvent, CommitteeActionFilterBatchCorrespondenceHistoryRule>() {

            @Override
            protected void prerequisite() {
                String batchCorrespondenceTypeCode = null;
                Date startDate = Date.valueOf("2010-01-01");
                Date endDate = Date.valueOf("2010-12-31");
                
                event = new CommitteeActionFilterBatchCorrespondenceHistoryEvent(Constants.EMPTY_STRING, null, batchCorrespondenceTypeCode, startDate, endDate);
                rule = new CommitteeActionFilterBatchCorrespondenceHistoryRule();
                rule.setErrorReporter(new ErrorReporter());
                expectedReturnValue = false;
            }
        };
    }

    @Test
    public void testFalseEndDateBeforeStartDate() {
        
        new TemplateRuleTest<CommitteeActionFilterBatchCorrespondenceHistoryEvent, CommitteeActionFilterBatchCorrespondenceHistoryRule>() {

            @Override
            protected void prerequisite() {
                String batchCorrespondenceTypeCode = "1";
                Date startDate = Date.valueOf("2010-12-31");
                Date endDate = Date.valueOf("2010-01-01");
                
                event = new CommitteeActionFilterBatchCorrespondenceHistoryEvent(Constants.EMPTY_STRING, null, batchCorrespondenceTypeCode, startDate, endDate);
                rule = new CommitteeActionFilterBatchCorrespondenceHistoryRule();
                rule.setErrorReporter(new ErrorReporter());
                expectedReturnValue = false;
            }
        };
    }

}
