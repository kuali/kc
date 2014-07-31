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
package org.kuali.kra.irb.protocol.funding;

import org.junit.Test;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.coeus.sys.impl.validation.ErrorReporterImpl;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.ProtocolEventBase;
import org.kuali.kra.rules.TemplateRuleTest;

public class LookupProtocolFundingSourceRuleTest {
    
    
    
    @Test
    public void testAward() {    
        new  TemplateRuleTest<LookupProtocolFundingSourceEvent, LookupProtocolFundingSourceRule> (){            
            @Override
            protected void prerequisite() {            
                event = new LookupProtocolFundingSourceEvent(Constants.EMPTY_STRING, null, FundingSourceType.AWARD, 
                        ProtocolEventBase.ErrorType.HARDERROR );
                rule = new LookupProtocolFundingSourceRule();
                rule.setErrorReporter(new ErrorReporterImpl());
                expectedReturnValue = true;
            }
        };
    }
    
    @Test
    public void testPropDev() {    
        new  TemplateRuleTest<LookupProtocolFundingSourceEvent, LookupProtocolFundingSourceRule> (){            
            @Override
            protected void prerequisite() {            
                event = new LookupProtocolFundingSourceEvent(Constants.EMPTY_STRING, null, FundingSourceType.PROPOSAL_DEVELOPMENT, 
                        ProtocolEventBase.ErrorType.HARDERROR );
                rule = new LookupProtocolFundingSourceRule();
                rule.setErrorReporter(new ErrorReporterImpl());
                expectedReturnValue = true;
            }
        };
    }
    @Test
    public void testUnit() {    
        new  TemplateRuleTest<LookupProtocolFundingSourceEvent, LookupProtocolFundingSourceRule> (){            
            @Override
            protected void prerequisite() {            
                event = new LookupProtocolFundingSourceEvent(Constants.EMPTY_STRING, null, FundingSourceType.UNIT, 
                        ProtocolEventBase.ErrorType.HARDERROR );
                rule = new LookupProtocolFundingSourceRule();
                rule.setErrorReporter(new ErrorReporterImpl());
                expectedReturnValue = true;
            }
        };
    }
    @Test
    public void testOther() {    
        new  TemplateRuleTest<LookupProtocolFundingSourceEvent, LookupProtocolFundingSourceRule> (){            
            @Override
            protected void prerequisite() {            
                event = new LookupProtocolFundingSourceEvent(Constants.EMPTY_STRING, null, FundingSourceType.OTHER, 
                        ProtocolEventBase.ErrorType.HARDERROR );
                rule = new LookupProtocolFundingSourceRule();
                rule.setErrorReporter(new ErrorReporterImpl());
                expectedReturnValue = false;
            }
        };
    }
    @Test
    public void testSponsor() {    
        new  TemplateRuleTest<LookupProtocolFundingSourceEvent, LookupProtocolFundingSourceRule> (){            
            @Override
            protected void prerequisite() {            
                event = new LookupProtocolFundingSourceEvent(Constants.EMPTY_STRING, null, FundingSourceType.SPONSOR, 
                        ProtocolEventBase.ErrorType.HARDERROR );
                rule = new LookupProtocolFundingSourceRule();
                rule.setErrorReporter(new ErrorReporterImpl());
                expectedReturnValue = true;
            }
        };
    }
    @Test
    public void  testInstProp() {    
        new  TemplateRuleTest<LookupProtocolFundingSourceEvent, LookupProtocolFundingSourceRule> (){            
            @Override
            protected void prerequisite() {            
                event = new LookupProtocolFundingSourceEvent(Constants.EMPTY_STRING, null, FundingSourceType.INSTITUTIONAL_PROPOSAL, 
                        ProtocolEventBase.ErrorType.HARDERROR );
                rule = new LookupProtocolFundingSourceRule();
                rule.setErrorReporter(new ErrorReporterImpl());
                expectedReturnValue = true;
            }
        };
    }
    

}
