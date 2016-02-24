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
import org.kuali.kra.committee.rule.event.CommitteeActionGenerateBatchCorrespondenceEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rules.TemplateRuleTest;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import java.sql.Date;

public class CommitteeActionGenerateBatchCorrespondenceRuleTest extends KcIntegrationTestBase {
    
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
