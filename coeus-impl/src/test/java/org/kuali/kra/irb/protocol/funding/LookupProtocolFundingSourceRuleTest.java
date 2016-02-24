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
package org.kuali.kra.irb.protocol.funding;

import org.junit.Test;
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
