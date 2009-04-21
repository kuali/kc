/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.rules;

import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.rule.event.LookupProtocolFundingSourceEvent;
import org.kuali.kra.irb.rule.event.ProtocolEventBase;
import org.kuali.kra.irb.service.impl.ProtocolFundingSourceServiceImpl.FundingSourceLookup;
import org.kuali.kra.rules.TemplateRuleTest;

public class LookupProtocolFundingSourceRuleTest {
    
    
    
    @Test
    public void testAward() {    
        new  TemplateRuleTest<LookupProtocolFundingSourceEvent, LookupProtocolFundingSourceRule> (){            
            @Override
            protected void prerequisite() {            
                event = new LookupProtocolFundingSourceEvent(Constants.EMPTY_STRING, null, FundingSourceLookup.AWARD.getLookupName(), ProtocolEventBase.ErrorType.HARDERROR );
                rule = new LookupProtocolFundingSourceRule();
                expectedReturnValue = true;
            }
        };
    }
    
    @Test
    public void testPropDev() {    
        new  TemplateRuleTest<LookupProtocolFundingSourceEvent, LookupProtocolFundingSourceRule> (){            
            @Override
            protected void prerequisite() {            
                event = new LookupProtocolFundingSourceEvent(Constants.EMPTY_STRING, null, FundingSourceLookup.PROPOSAL_DEVELOPMENT.getLookupName(), ProtocolEventBase.ErrorType.HARDERROR );
                rule = new LookupProtocolFundingSourceRule();
                expectedReturnValue = true;
            }
        };
    }
    @Test
    public void testUnit() {    
        new  TemplateRuleTest<LookupProtocolFundingSourceEvent, LookupProtocolFundingSourceRule> (){            
            @Override
            protected void prerequisite() {            
                event = new LookupProtocolFundingSourceEvent(Constants.EMPTY_STRING, null, FundingSourceLookup.UNIT.getLookupName(), ProtocolEventBase.ErrorType.HARDERROR );
                rule = new LookupProtocolFundingSourceRule();
                expectedReturnValue = true;
            }
        };
    }
    @Test
    public void testOther() {    
        new  TemplateRuleTest<LookupProtocolFundingSourceEvent, LookupProtocolFundingSourceRule> (){            
            @Override
            protected void prerequisite() {            
                event = new LookupProtocolFundingSourceEvent(Constants.EMPTY_STRING, null, FundingSourceLookup.OTHER.getLookupName(), ProtocolEventBase.ErrorType.HARDERROR );
                rule = new LookupProtocolFundingSourceRule();
                expectedReturnValue = false;
            }
        };
    }
    @Test
    public void testSponsor() {    
        new  TemplateRuleTest<LookupProtocolFundingSourceEvent, LookupProtocolFundingSourceRule> (){            
            @Override
            protected void prerequisite() {            
                event = new LookupProtocolFundingSourceEvent(Constants.EMPTY_STRING, null, FundingSourceLookup.SPONSOR.getLookupName(), ProtocolEventBase.ErrorType.HARDERROR );
                rule = new LookupProtocolFundingSourceRule();
                expectedReturnValue = true;
            }
        };
    }
    @Test
    public void  testInstProp() {    
        new  TemplateRuleTest<LookupProtocolFundingSourceEvent, LookupProtocolFundingSourceRule> (){            
            @Override
            protected void prerequisite() {            
                event = new LookupProtocolFundingSourceEvent(Constants.EMPTY_STRING, null, FundingSourceLookup.INSTITUTE_PROPOSAL.getLookupName(), ProtocolEventBase.ErrorType.HARDERROR );
                rule = new LookupProtocolFundingSourceRule();
                expectedReturnValue = false;
            }
        };
    }
    

}
