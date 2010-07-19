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
package org.kuali.kra.irb.actions.decision;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

/*
@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_status.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ORG_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_PERSON_ROLES.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE_QUALIFIER.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_review_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_REVIEWER_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_committee_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ATTACHMENT_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ATTACHMENT_STATUS.sql", delimiter = ";")
}))*/
public class CommitteeDecisionServiceTest extends KcUnitTestBase {
    
    CommitteeDecisionService committeeDecisionService;
    //BusinessObjectService businessObjectService;
    Protocol protocol;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        //committeeDecisionService = KraServiceLocator.getService(CommitteeDecisionService.class);
        committeeDecisionService = KraServiceLocator.getService("protocolCommitteeDecisionService");
        protocol = ProtocolFactory.createProtocolDocument("123456").getProtocol();
        protocol.setProtocolId(new Long(1234));
    }

    @After
    public void tearDown() throws Exception {
        committeeDecisionService = null;
        super.tearDown();
    }

    @Test
    public void testSetCommitteeDecision() throws Exception {
        CommitteeDecision committeeDecision = new CommitteeDecision();
        committeeDecision.setAbstainCount(new Integer(0));
        committeeDecision.setMotion(MotionValuesFinder.APPROVE);
        committeeDecision.setNoCount(new Integer(0));
        committeeDecision.setProtocolId(protocol.getProtocolId());
        committeeDecision.setVotingComments("just some dumb comments");
        committeeDecision.setYesCount(new Integer(2));
        committeeDecisionService.setCommitteeDecision(protocol, committeeDecision);
    }

}
