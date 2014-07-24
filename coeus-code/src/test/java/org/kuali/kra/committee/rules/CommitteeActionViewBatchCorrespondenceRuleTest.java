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
import org.kuali.coeus.common.committee.impl.bo.CommitteeBatchCorrespondenceBase;
import org.kuali.coeus.common.committee.impl.rule.event.CommitteeActionViewBatchCorrespondenceEvent;
import org.kuali.coeus.common.committee.impl.rules.CommitteeActionViewBatchCorrespondenceRule;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.kra.committee.bo.CommitteeBatchCorrespondence;
import org.kuali.kra.committee.bo.CommitteeBatchCorrespondenceDetail;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rules.TemplateRuleTest;

import java.util.ArrayList;
import java.util.List;

public class CommitteeActionViewBatchCorrespondenceRuleTest {
    
    @Test
    public void testTrue() {
        
        new TemplateRuleTest<CommitteeActionViewBatchCorrespondenceEvent, CommitteeActionViewBatchCorrespondenceRule>() {

            @Override
            protected void prerequisite() {
                CommitteeBatchCorrespondenceDetail committeeBatchCorrespondenceDetail = new CommitteeBatchCorrespondenceDetail();
                committeeBatchCorrespondenceDetail.setSelected(true);
                CommitteeBatchCorrespondence committeeBatchCorrespondence = new CommitteeBatchCorrespondence();
                committeeBatchCorrespondence.getCommitteeBatchCorrespondenceDetails().add(committeeBatchCorrespondenceDetail);
                List<CommitteeBatchCorrespondenceBase> committeeBatchCorrespondences = new ArrayList<CommitteeBatchCorrespondenceBase>();
                committeeBatchCorrespondences.add(committeeBatchCorrespondence);
                
                event = new CommitteeActionViewBatchCorrespondenceEvent(Constants.EMPTY_STRING, null, committeeBatchCorrespondences, false);
                rule = new CommitteeActionViewBatchCorrespondenceRule();
                rule.setErrorReporter(new ErrorReporter());
                expectedReturnValue = true;
            }
        };
    }

    @Test
    public void testFalse() {
        
        new TemplateRuleTest<CommitteeActionViewBatchCorrespondenceEvent, CommitteeActionViewBatchCorrespondenceRule>() {

            @Override
            protected void prerequisite() {
                List<CommitteeBatchCorrespondenceBase> committeeBatchCorrespondences = new ArrayList<CommitteeBatchCorrespondenceBase>();
                event = new CommitteeActionViewBatchCorrespondenceEvent(Constants.EMPTY_STRING, null, committeeBatchCorrespondences, false);
                rule = new CommitteeActionViewBatchCorrespondenceRule();
                rule.setErrorReporter(new ErrorReporter());
                expectedReturnValue = false;
            }
        };
    }

}
