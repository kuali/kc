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
package org.kuali.kra.committee.rules;

import org.junit.Test;
import org.kuali.coeus.common.committee.impl.rule.event.CommitteeActionFilterBatchCorrespondenceHistoryEvent;
import org.kuali.coeus.common.committee.impl.rules.CommitteeActionFilterBatchCorrespondenceHistoryRule;
import org.kuali.coeus.sys.impl.validation.ErrorReporterImpl;
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
                rule.setErrorReporter(new ErrorReporterImpl());
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
                rule.setErrorReporter(new ErrorReporterImpl());
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
                rule.setErrorReporter(new ErrorReporterImpl());
                expectedReturnValue = false;
            }
        };
    }

}
