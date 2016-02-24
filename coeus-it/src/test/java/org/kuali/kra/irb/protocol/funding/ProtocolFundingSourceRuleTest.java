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

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rules.TemplateRuleTest;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import java.util.ArrayList;

public class ProtocolFundingSourceRuleTest extends KcIntegrationTestBase {
    
    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};

    
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
        
        goodFundingSource = new ProtocolFundingSource("000108", FundingSourceType.SPONSOR, "NAME", "TITLE");
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
