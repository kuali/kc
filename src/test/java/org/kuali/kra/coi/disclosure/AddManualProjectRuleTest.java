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
package org.kuali.kra.coi.disclosure;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureEventType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rules.TemplateRuleTest;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

public class AddManualProjectRuleTest extends KcUnitTestBase {

    @Test
    public void testAddProposalEventOK() {
        new TemplateRuleTest<AddManualProjectEvent, AddManualProjectRule>() {
            @Override
            protected void prerequisite() {
                CoiDisclProject coiDisclProject = new CoiDisclProject() ;
                
                coiDisclProject.setShortTextField1("test id");
                coiDisclProject.setDisclosureEventType(CoiDisclosureEventType.MANUAL_DEVELOPMENT_PROPOSAL);
                coiDisclProject.setLongTextField1("test title");
                coiDisclProject.setShortTextField2("1");
                coiDisclProject.setLongTextField3("test sponsor");

                coiDisclProject.setDateField1(new java.sql.Date(new Date().getTime()));
                CoiDisclosure coiDisclosure = new CoiDisclosure();
                coiDisclosure.setCoiDisclProjects(new ArrayList<CoiDisclProject>());
                coiDisclProject.setCoiDisclosure(coiDisclosure);
                Date dt = DateUtils.addDays(new Date(), 10);
                coiDisclProject.setDateField2(new java.sql.Date(dt.getTime()));
                event = new AddManualProjectEvent(Constants.EMPTY_STRING, coiDisclProject);
                rule = new AddManualProjectRule();
                expectedReturnValue = true;
            }
        };
    }

/*
    @Test
    public void testAddProposalEventProjectIdRequired() {
        new TemplateRuleTest<AddManualProjectEvent, AddManualProjectRule>() {
            @Override
            protected void prerequisite() {
                CoiDisclProject coiDisclProject = new CoiDisclProject() ;
                
        //        coiDisclProject.setShortTextField1("test id");
                coiDisclProject.setDisclosureEventType(CoiDisclosureEventType.MANUAL_DEVELOPMENT_PROPOSAL);
                coiDisclProject.setLongTextField1("test title");
                coiDisclProject.setShortTextField2("1");
                coiDisclProject.setLongTextField3("test sponsor");

                coiDisclProject.setDateField1(new java.sql.Date(new Date().getTime()));
                CoiDisclosure coiDisclosure = new CoiDisclosure();
                coiDisclosure.setCoiDisclProjects(new ArrayList<CoiDisclProject>());
                coiDisclProject.setCoiDisclosure(coiDisclosure);
                Date dt = DateUtils.addDays(new Date(), 10);
                coiDisclProject.setDateField2(new java.sql.Date(dt.getTime()));
                event = new AddManualProjectEvent(Constants.EMPTY_STRING, coiDisclProject);
                rule = new AddManualProjectRule();
                expectedReturnValue = false;
            }
        };
    }

    @Test
    public void testAddProposalEventEndDateBeforeStartDate() {
        new TemplateRuleTest<AddManualProjectEvent, AddManualProjectRule>() {
            @Override
            protected void prerequisite() {
                CoiDisclProject coiDisclProject = new CoiDisclProject() ;
                
                coiDisclProject.setShortTextField1("test id");
                coiDisclProject.setDisclosureEventType(CoiDisclosureEventType.MANUAL_DEVELOPMENT_PROPOSAL);
                coiDisclProject.setLongTextField1("test title");
                coiDisclProject.setShortTextField2("1");
                coiDisclProject.setLongTextField3("test sponsor");

                coiDisclProject.setDateField1(new java.sql.Date(new Date().getTime()));
                CoiDisclosure coiDisclosure = new CoiDisclosure();
                coiDisclosure.setCoiDisclProjects(new ArrayList<CoiDisclProject>());
                coiDisclProject.setCoiDisclosure(coiDisclosure);
                Date dt = DateUtils.addDays(new Date(), -10);
                coiDisclProject.setDateField2(new java.sql.Date(dt.getTime()));
                event = new AddManualProjectEvent(Constants.EMPTY_STRING, coiDisclProject);
                rule = new AddManualProjectRule();
                expectedReturnValue = false;
            }
        };
    }
*/
    @Test
    public void testAddAwardEventOK() {
        new TemplateRuleTest<AddManualProjectEvent, AddManualProjectRule>() {
            @Override
            protected void prerequisite() {
                CoiDisclProject coiDisclProject = new CoiDisclProject() ;
                
                coiDisclProject.setShortTextField1("test id");
                coiDisclProject.setDisclosureEventType(CoiDisclosureEventType.MANUAL_AWARD);
                coiDisclProject.setLongTextField1("test title");
                coiDisclProject.setDateField1(new java.sql.Date(new Date().getTime()));
                CoiDisclosure coiDisclosure = new CoiDisclosure();
                coiDisclosure.setCoiDisclProjects(new ArrayList<CoiDisclProject>());
                coiDisclProject.setCoiDisclosure(coiDisclosure);
                event = new AddManualProjectEvent(Constants.EMPTY_STRING, coiDisclProject);
                rule = new AddManualProjectRule();
                expectedReturnValue = true;
            }
        };
    }

    @Test
    public void testAddProtocolEventOK() {
        new TemplateRuleTest<AddManualProjectEvent, AddManualProjectRule>() {
            @Override
            protected void prerequisite() {
                CoiDisclProject coiDisclProject = new CoiDisclProject() ;
                
                coiDisclProject.setShortTextField1("test id");
                coiDisclProject.setDisclosureEventType(CoiDisclosureEventType.MANUAL_IRB_PROTOCOL);
                coiDisclProject.setLongTextField1("test title");
                coiDisclProject.setShortTextField2("1");
                CoiDisclosure coiDisclosure = new CoiDisclosure();
                coiDisclosure.setCoiDisclProjects(new ArrayList<CoiDisclProject>());
                coiDisclProject.setCoiDisclosure(coiDisclosure);
                event = new AddManualProjectEvent(Constants.EMPTY_STRING, coiDisclProject);
                rule = new AddManualProjectRule();
                expectedReturnValue = true;
            }
        };
    }



}
