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
package org.kuali.kra.irb.protocol.funding;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.protocol.funding.AddProtocolFundingSourceEvent;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSource;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSourceRule;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSourceService;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSourceServiceImpl.FundingSourceLookup;
import org.kuali.kra.rules.TemplateRuleTest;

public class AddProtocolFundingSourceTest {
    
 //   ProtocolFundingSource fundingSrc = addProtocolFundingSourceEvent.getFundingSource();
    
    private Mockery context = new JUnit4Mockery();

    private final String goodFundingSourceId = "000100";
    private final String goodFundingSourceName = "Air Force";
    private final String badFundingSourceId = "goober99";
    private final String badFundingSourceName = "";
    
    private ProtocolFundingSource fundingSource;
    private ProtocolFundingSource badFundingSource;
    private List<ProtocolFundingSource> protocolFundingSources;
    private List<ProtocolFundingSource> emptyProtocolFundingSources;
    private ProtocolDocument doc = null;

    
    /**
     * Create the mock services and insert them into the protocol auth service.
     * 
     * @see org.kuali.kra.KraTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        FundingSourceType fsType = new FundingSourceType();
        fsType.setFundingSourceTypeCode(FundingSourceLookup.SPONSOR.getFundingTypeCode());
        fsType.setDescription(FundingSourceLookup.SPONSOR.getName());
        fundingSource = new ProtocolFundingSource(goodFundingSourceId, fsType, goodFundingSourceName, "" );
        protocolFundingSources = new ArrayList<ProtocolFundingSource>();
        protocolFundingSources.add(fundingSource);
        
        emptyProtocolFundingSources = new ArrayList<ProtocolFundingSource>();
        
        badFundingSource= new ProtocolFundingSource(badFundingSourceId, fsType, badFundingSourceName, "" );

    }  
    
    @Test
    public void testSimple() {    
        new  TemplateRuleTest<AddProtocolFundingSourceEvent, ProtocolFundingSourceRule> (){            
            @Override
            protected void prerequisite() {        
                event = new AddProtocolFundingSourceEvent(Constants.EMPTY_STRING, doc, fundingSource, emptyProtocolFundingSources);
                rule = new ProtocolFundingSourceRule();
                rule.setBusinessObjectService(null);
                rule.setProtocolFundingSourceService(getProtocolFundingSourceService());
                expectedReturnValue = true;
            }
        };
    }

    @Test
    public void testDuplicate() { 
        TemplateRuleTest<AddProtocolFundingSourceEvent, ProtocolFundingSourceRule>  theTest = 
            new  TemplateRuleTest<AddProtocolFundingSourceEvent, ProtocolFundingSourceRule> (){            
                @Override
                protected void prerequisite() {        
                    event = new AddProtocolFundingSourceEvent(Constants.EMPTY_STRING, doc, fundingSource, protocolFundingSources);
                    rule = new ProtocolFundingSourceRule();
                    rule.setBusinessObjectService(null);
                    rule.setProtocolFundingSourceService(getProtocolFundingSourceService());
                    expectedReturnValue = false;
                }                
                @Override
                public void checkRuleAssertions() {
                    Assert.assertTrue(getErrorMap().containsMessageKey(KeyConstants.ERROR_PROTOCOL_FUNDING_DUPLICATE));
                }
            };
            theTest.checkRuleAssertions();            
    }
    
    @Test
    public void testNullFundingSource() { 
        TemplateRuleTest<AddProtocolFundingSourceEvent, ProtocolFundingSourceRule>  theTest = 
            new  TemplateRuleTest<AddProtocolFundingSourceEvent, ProtocolFundingSourceRule> (){            
                @Override
                protected void prerequisite() {        
                    event = new AddProtocolFundingSourceEvent(Constants.EMPTY_STRING, doc, null, protocolFundingSources);
                    rule = new ProtocolFundingSourceRule();
                    rule.setBusinessObjectService(null);
                    rule.setProtocolFundingSourceService(getProtocolFundingSourceService());
                    expectedReturnValue = false;
                }                
                @Override
                public void checkRuleAssertions() {
                    Assert.assertTrue(getErrorMap().containsMessageKey(KeyConstants.ERROR_PROTOCOL_FUNDING_TYPE_NOT_FOUND));
                }
            };
            theTest.checkRuleAssertions();            
    }

    
    @Test
    public void testBlankIDFundingSource() { 
        TemplateRuleTest<AddProtocolFundingSourceEvent, ProtocolFundingSourceRule>  theTest = 
            new  TemplateRuleTest<AddProtocolFundingSourceEvent, ProtocolFundingSourceRule> (){            
                @Override
                protected void prerequisite() {        
                    fundingSource.setFundingSource("");
                    event = new AddProtocolFundingSourceEvent(Constants.EMPTY_STRING, doc, fundingSource, protocolFundingSources);
                    rule = new ProtocolFundingSourceRule();
                    rule.setBusinessObjectService(null);
                    rule.setProtocolFundingSourceService(getProtocolFundingSourceService());
                    expectedReturnValue = false;
                }                
                @Override
                public void checkRuleAssertions() {
                    Assert.assertTrue(getErrorMap().containsMessageKey(KeyConstants.ERROR_PROTOCOL_FUNDING_ID_NOT_FOUND));
                }
            };
            theTest.checkRuleAssertions();            
    }
    
    @Test
    public void testBlankNameFundingSource() { 
        TemplateRuleTest<AddProtocolFundingSourceEvent, ProtocolFundingSourceRule>  theTest = 
            new  TemplateRuleTest<AddProtocolFundingSourceEvent, ProtocolFundingSourceRule> (){            
                @Override
                protected void prerequisite() {        
                    fundingSource.setFundingSourceName("");
                    event = new AddProtocolFundingSourceEvent(Constants.EMPTY_STRING, doc, fundingSource, protocolFundingSources);
                    rule = new ProtocolFundingSourceRule();
                    rule.setBusinessObjectService(null);
                    rule.setProtocolFundingSourceService(getProtocolFundingSourceService());
                    expectedReturnValue = false;
                }                
                @Override
                public void checkRuleAssertions() {
                    Assert.assertTrue(getErrorMap().containsMessageKey(KeyConstants.ERROR_PROTOCOL_FUNDING_NAME_NOT_FOUND));
                }
            };
            theTest.checkRuleAssertions();            
    }
    
    @Test
    public void testNotFoundNameFundingSource() { 
        TemplateRuleTest<AddProtocolFundingSourceEvent, ProtocolFundingSourceRule>  theTest = 
            new  TemplateRuleTest<AddProtocolFundingSourceEvent, ProtocolFundingSourceRule> (){            
                @Override
                protected void prerequisite() {        
                    fundingSource.setFundingSourceName("not found");
                    event = new AddProtocolFundingSourceEvent(Constants.EMPTY_STRING, doc, fundingSource, protocolFundingSources);
                    rule = new ProtocolFundingSourceRule();
                    rule.setBusinessObjectService(null);
                    rule.setProtocolFundingSourceService(getProtocolFundingSourceService());
                    expectedReturnValue = false;
                }                
                @Override
                public void checkRuleAssertions() {
                    Assert.assertTrue(getErrorMap().containsMessageKey(KeyConstants.ERROR_PROTOCOL_FUNDING_NAME_NOT_FOUND));
                }
            };
            theTest.checkRuleAssertions();            
    }

    @Test
    public void testNullTypeFundingSource() { 
        TemplateRuleTest<AddProtocolFundingSourceEvent, ProtocolFundingSourceRule>  theTest = 
            new  TemplateRuleTest<AddProtocolFundingSourceEvent, ProtocolFundingSourceRule> (){            
                @Override
                protected void prerequisite() {        
                    fundingSource.setFundingSourceType(null);
                    event = new AddProtocolFundingSourceEvent(Constants.EMPTY_STRING, doc, fundingSource, protocolFundingSources);
                    rule = new ProtocolFundingSourceRule();
                    rule.setBusinessObjectService(null);
                    rule.setProtocolFundingSourceService(getProtocolFundingSourceService());
                    expectedReturnValue = false;
                }                
                @Override
                public void checkRuleAssertions() {
                    Assert.assertTrue(getErrorMap().containsMessageKey(KeyConstants.ERROR_PROTOCOL_FUNDING_TYPE_NOT_FOUND));
                }
            };
            theTest.checkRuleAssertions();            
    }
    
    @Test
    public void testInvalidIdTypeFundingSource() { 
        TemplateRuleTest<AddProtocolFundingSourceEvent, ProtocolFundingSourceRule>  theTest = 
            new  TemplateRuleTest<AddProtocolFundingSourceEvent, ProtocolFundingSourceRule> (){            
                @Override
                protected void prerequisite() {        
                    event = new AddProtocolFundingSourceEvent(Constants.EMPTY_STRING, doc, badFundingSource, protocolFundingSources);
                    rule = new ProtocolFundingSourceRule();
                    rule.setBusinessObjectService(null);
                    rule.setProtocolFundingSourceService(getProtocolFundingSourceService());
                    expectedReturnValue = false;
                }                
                @Override
                public void checkRuleAssertions() {
                    Assert.assertTrue(getErrorMap().containsMessageKey(KeyConstants.ERROR_PROTOCOL_FUNDING_ID_INVALID_FOR_TYPE));
                }
            };
            theTest.checkRuleAssertions();            
    }
    
    protected ProtocolFundingSourceService getProtocolFundingSourceService() {
        final ProtocolFundingSourceService protocolFundingSourceService = context.mock(ProtocolFundingSourceService.class);
        context.checking(new Expectations() {{
          allowing(protocolFundingSourceService).isValidIdForType(fundingSource); 
          will(returnValue(true));
          one(protocolFundingSourceService).isValidIdForType(badFundingSource); 
          will(returnValue(false));
        }});
        return protocolFundingSourceService;
    }
    
}
