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
package org.kuali.kra.irb.protocol.funding;

import java.util.ArrayList;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rules.TemplateRuleTest;

public class ProtocolFundingSourceRuleTest {
    
    private Mockery context = new JUnit4Mockery();

    
    private FundingSourceType fundingSponsorSourceType;
    private ProtocolFundingSource goodFundingSource;
    private ProtocolFundingSource badFundingSourceNoName;
    private ProtocolFundingSource badFundingSourceNoId;
    private ProtocolFundingSource badFundingSourceNoType;
    private ProtocolFundingSource badFundingSourceInvalidId;
    
    private ArrayList<ProtocolFundingSource> list = new ArrayList<ProtocolFundingSource>();
    private ArrayList<ProtocolFundingSource> duplicateList = new ArrayList<ProtocolFundingSource>();
    

    
    /**
     * Create the mock services and insert them into the protocol auth service.
     * 
     * @see org.kuali.kra.KraTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        fundingSponsorSourceType = new FundingSourceType();
        fundingSponsorSourceType.setFundingSourceTypeCode("1");
        fundingSponsorSourceType.setFundingSourceTypeFlag(true);
        fundingSponsorSourceType.setDescription("Sponsor");
        
        goodFundingSource = new ProtocolFundingSource("123", FundingSourceType.SPONSOR, "NAME", "TITLE");
        badFundingSourceNoId = new ProtocolFundingSource("", FundingSourceType.SPONSOR, "123", null);
        badFundingSourceNoName = new ProtocolFundingSource("123", FundingSourceType.SPONSOR, "", null);
        badFundingSourceNoType = new ProtocolFundingSource("123", null, "123", null);
        badFundingSourceInvalidId = new ProtocolFundingSource("100", FundingSourceType.SPONSOR, "COOLNESS", null);
        
        duplicateList.add(goodFundingSource);
 
    }
    
    @Test
    public void testSuccess() {    
        new  TemplateRuleTest<AddProtocolFundingSourceEvent, ProtocolFundingSourceRule> (){            
            @Override
            protected void prerequisite() {                  
                event = new AddProtocolFundingSourceEvent(Constants.EMPTY_STRING, null, goodFundingSource, list);
                rule = new ProtocolFundingSourceRule();
                rule.setProtocolFundingSourceService(getProtocolFundingSourceService());                
                expectedReturnValue = true;
            }
        };
    }
    
    @Test
    public void testDuplicate() {    
        new  TemplateRuleTest<AddProtocolFundingSourceEvent, ProtocolFundingSourceRule> (){            
            @Override
            protected void prerequisite() {                  
                event = new AddProtocolFundingSourceEvent(Constants.EMPTY_STRING, null, goodFundingSource, duplicateList);
                rule = new ProtocolFundingSourceRule();
                rule.setProtocolFundingSourceService(getProtocolFundingSourceService());                
                expectedReturnValue = false;
            }
        };
    }

    
    @Test
    public void testInvalidId() {    
        new  TemplateRuleTest<AddProtocolFundingSourceEvent, ProtocolFundingSourceRule> (){            
            @Override
            protected void prerequisite() {                  
                event = new AddProtocolFundingSourceEvent(Constants.EMPTY_STRING, null, badFundingSourceInvalidId, list);
                rule = new ProtocolFundingSourceRule();
                rule.setProtocolFundingSourceService(getProtocolFundingSourceService());                
                expectedReturnValue = false;
            }
        };
    }
    
    @Test
    public void testNoId() {    
        new  TemplateRuleTest<AddProtocolFundingSourceEvent, ProtocolFundingSourceRule> (){            
            @Override
            protected void prerequisite() {                  
                event = new AddProtocolFundingSourceEvent(Constants.EMPTY_STRING, null, badFundingSourceNoId, list);
                rule = new ProtocolFundingSourceRule();
                rule.setProtocolFundingSourceService(getProtocolFundingSourceService());                
                expectedReturnValue = false;
            }
        };
    }
    
    @Test
    public void testNoType() {    
        new  TemplateRuleTest<AddProtocolFundingSourceEvent, ProtocolFundingSourceRule> (){            
            @Override
            protected void prerequisite() {                  
                event = new AddProtocolFundingSourceEvent(Constants.EMPTY_STRING, null, badFundingSourceNoType, list);
                rule = new ProtocolFundingSourceRule();
                rule.setProtocolFundingSourceService(getProtocolFundingSourceService());                
                expectedReturnValue = false;
            }
        };
    }
    
    @Test
    public void testNoName() {    
        new  TemplateRuleTest<AddProtocolFundingSourceEvent, ProtocolFundingSourceRule> (){            
            @Override
            protected void prerequisite() {                  
                event = new AddProtocolFundingSourceEvent(Constants.EMPTY_STRING, null, badFundingSourceNoName, list);
                rule = new ProtocolFundingSourceRule();
                rule.setProtocolFundingSourceService(getProtocolFundingSourceService());                
                expectedReturnValue = false;
            }
        };
    }
    

    
    protected ProtocolFundingSourceService getProtocolFundingSourceService() {
        final ProtocolFundingSourceService service = context.mock(ProtocolFundingSourceService.class);
        context.checking(new Expectations() {{
            allowing(service).isValidIdForType(goodFundingSource); 
            will(returnValue(true));
            allowing(service).isValidIdForType(badFundingSourceNoId); 
            will(returnValue(true));
            allowing(service).isValidIdForType(badFundingSourceNoName); 
            will(returnValue(true));
            allowing(service).isValidIdForType(badFundingSourceNoType); 
            will(returnValue(true));
            
            allowing(service).isValidIdForType(badFundingSourceInvalidId); 
            will(returnValue(false));

            allowing(service).isEditable(fundingSponsorSourceType.getFundingSourceTypeCode());
            will(returnValue(true));

        }});
        return service;
    }

}
