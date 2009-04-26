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
package org.kuali.kra.irb.noteattachment;

import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.test.ProtocolTestUtil;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Tests the {@link ProtocolAttachmentBaseRuleHelper ProtocolAttachmentBaseRuleHelper} class.
 */
public class ProtocolAttachmentBaseRuleHelperTest {

    private Mockery context = new JUnit4Mockery();

    @Before
    public void setupGlobalVars() {
        GlobalVariables.setAuditErrorMap(new HashMap());
        GlobalVariables.setErrorMap(new ErrorMap());
    }
    
    /**
     * Tests conditions related to a description being empty but valid.
     */
    @Test
    public void validDescriptionEmpty() {
        final ProtocolAttachmentService paService = this.context.mock(ProtocolAttachmentService.class); 
        final DictionaryValidationService ddService = this.context.mock(DictionaryValidationService.class);
        
        ProtocolAttachmentBaseRuleHelper helper = new ProtocolAttachmentBaseRuleHelper(paService, ddService);
        
        ProtocolAttachmentBase attachment = new ProtocolAttachmentProtocol();
        attachment.setType(new ProtocolAttachmentType("3", "a desc"));
        
        boolean valid = helper.validDescription(attachment);
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should be valid", valid, is(true));
    }

    /**
     * Tests conditions related to a description being non-empty and valid.
     */
    @Test
    public void validDescriptionNotEmpty() {
        final ProtocolAttachmentService paService = this.context.mock(ProtocolAttachmentService.class); 
        final DictionaryValidationService ddService = this.context.mock(DictionaryValidationService.class);
        
        ProtocolAttachmentBaseRuleHelper helper = new ProtocolAttachmentBaseRuleHelper(paService, ddService);
        
        ProtocolAttachmentBase attachment = new ProtocolAttachmentProtocol();
        attachment.setType(new ProtocolAttachmentType("3", "a desc"));
        attachment.setDescription("a desc");
        
        boolean valid = helper.validDescription(attachment);
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should be valid", valid, is(true));
    }
    
    /**
     * Tests conditions related to a description being empty and invalid.
     */
    @Test
    public void invalidDescription() {
        final ProtocolAttachmentService paService = this.context.mock(ProtocolAttachmentService.class); 
        final DictionaryValidationService ddService = this.context.mock(DictionaryValidationService.class);
        
        ProtocolAttachmentBaseRuleHelper helper = new ProtocolAttachmentBaseRuleHelper(paService, ddService);
        
        ProtocolAttachmentBase attachment = new ProtocolAttachmentProtocol();
        attachment.setType(new ProtocolAttachmentType("11", "a desc"));
        
        this.context.checking(new Expectations() {{
            ProtocolAttachmentType type = new ProtocolAttachmentType();
            type.setCode("11");
            type.setDescription("not Other");
            
            one(paService).getTypeFromCode("11");
            will(returnValue(type));
        }});
        
        boolean valid = helper.validDescription(attachment);
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should be not valid", valid, is(false));
    }
    
    /**
     * Tests a valid attachment according to the DD validation.
     */
    @Test
    public void validAttachmentAttachmentProtocolDD() {
        final ProtocolAttachmentService paService = this.context.mock(ProtocolAttachmentService.class); 
        final DictionaryValidationService ddService = this.context.mock(DictionaryValidationService.class);
        
        ProtocolAttachmentBaseRuleHelper helper = new ProtocolAttachmentBaseRuleHelper(paService, ddService);
        helper.resetPropertyPrefix("fooPrefix");
        
        final ProtocolAttachmentProtocol attachment = new ProtocolAttachmentProtocol();
        attachment.setType(new ProtocolAttachmentType("11", "a desc"));
        attachment.setAttachmentVersionNumber(1);
        attachment.setDocumentId(1);
        Protocol protocol = ProtocolTestUtil.getProtocol(this.context);
        protocol.setProtocolId(1L);
        attachment.setProtocol(protocol);
        
        this.context.checking(new Expectations() {{         
            one(ddService).isBusinessObjectValid(attachment, "fooPrefix");
            will(returnValue(true));
        }});
        
        boolean valid = helper.validAgainstDictionary(attachment);
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should be valid", valid, is(true));
    }
    
    /**
     * Tests a valid type for group with blank code.
     */
    @Test
    public void validTypeForGroupBlankCode() {
        final ProtocolAttachmentService paService = this.context.mock(ProtocolAttachmentService.class); 
        final DictionaryValidationService ddService = this.context.mock(DictionaryValidationService.class);
        
        ProtocolAttachmentBaseRuleHelper helper = new ProtocolAttachmentBaseRuleHelper(paService, ddService);
        helper.resetPropertyPrefix("fooPrefix");
        
        final ProtocolAttachmentProtocol attachment = new ProtocolAttachmentProtocol();
        attachment.setType(new ProtocolAttachmentType(null, "a desc"));
                
        boolean valid = helper.validTypeForGroup(attachment);
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should be valid", valid, is(true));
    }
    
    /**
     * Tests a valid type for group with valid type code and type found.
     */
    @Test
    public void validTypeForGroupTypeFound() {
        final ProtocolAttachmentService paService = this.context.mock(ProtocolAttachmentService.class); 
        final DictionaryValidationService ddService = this.context.mock(DictionaryValidationService.class);
        
        ProtocolAttachmentBaseRuleHelper helper = new ProtocolAttachmentBaseRuleHelper(paService, ddService);
        helper.resetPropertyPrefix("fooPrefix");
        
        final ProtocolAttachmentProtocol attachment = new ProtocolAttachmentProtocol();
        attachment.setType(new ProtocolAttachmentType("11", "a desc"));
        
        this.context.checking(new Expectations() {{         
            Collection<ProtocolAttachmentType> types = new ArrayList<ProtocolAttachmentType>();
            ProtocolAttachmentType aType = new ProtocolAttachmentType();
            aType.setCode("11");
            types.add(aType);
            
            one(paService).getTypesForGroup(attachment.getGroupCode());
            will(returnValue(types));
        }});
        
        boolean valid = helper.validTypeForGroup(attachment);
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should be valid", valid, is(true));
    }
    
    /**
     * Tests invalid type for group with valid type code and type found but not for the given group.
     */
    @Test
    public void invalidTypeForGroupTypeFound() {
        final ProtocolAttachmentService paService = this.context.mock(ProtocolAttachmentService.class); 
        final DictionaryValidationService ddService = this.context.mock(DictionaryValidationService.class);
        
        ProtocolAttachmentBaseRuleHelper helper = new ProtocolAttachmentBaseRuleHelper(paService, ddService);
        helper.resetPropertyPrefix("fooPrefix");
        
        final ProtocolAttachmentProtocol attachment = new ProtocolAttachmentProtocol();
        attachment.setType(new ProtocolAttachmentType("11", "a desc"));
        
        this.context.checking(new Expectations() {{         
            
            Collection<ProtocolAttachmentType> types = new ArrayList<ProtocolAttachmentType>();
            {
                ProtocolAttachmentType aType = new ProtocolAttachmentType();
                aType.setCode("12");
                types.add(aType);
            }
            one(paService).getTypesForGroup(attachment.getGroupCode());
            will(returnValue(types));
            
            ProtocolAttachmentType aType2 = new ProtocolAttachmentType();
            aType2.setCode("11");
            aType2.setDescription("a desc");
            
            one(paService).getTypeFromCode("11");
            will(returnValue(aType2));
        }});
        
        boolean valid = helper.validTypeForGroup(attachment);
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should be valid", valid, is(false));
    }
    
    /**
     * Tests invalid type for group with valid type code and type NOT found.
     */
    @Test
    public void invalidTypeForGroupTypeNotFound() {
        final ProtocolAttachmentService paService = this.context.mock(ProtocolAttachmentService.class); 
        final DictionaryValidationService ddService = this.context.mock(DictionaryValidationService.class);
        
        ProtocolAttachmentBaseRuleHelper helper = new ProtocolAttachmentBaseRuleHelper(paService, ddService);
        helper.resetPropertyPrefix("fooPrefix");
        
        final ProtocolAttachmentProtocol attachment = new ProtocolAttachmentProtocol();
        attachment.setType(new ProtocolAttachmentType("11", "a desc"));
        
        this.context.checking(new Expectations() {{         
            Collection<ProtocolAttachmentType> types = new ArrayList<ProtocolAttachmentType>();
            ProtocolAttachmentType aType = new ProtocolAttachmentType();
            aType.setCode("12");
            types.add(aType);
            
            one(paService).getTypesForGroup(attachment.getGroupCode());
            will(returnValue(types));
            
            one(paService).getTypeFromCode("11");
            will(returnValue(null));
        }});
        
        boolean valid = helper.validTypeForGroup(attachment);
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should be valid", valid, is(false));
    }
    
    /**
     * Tests a invalid attachment according to the DD validation.
     */
    @Test
    public void invalidAttachmentDD() {
        final ProtocolAttachmentService paService = this.context.mock(ProtocolAttachmentService.class); 
        final DictionaryValidationService ddService = this.context.mock(DictionaryValidationService.class);
        
        ProtocolAttachmentBaseRuleHelper helper = new ProtocolAttachmentBaseRuleHelper(paService, ddService);
        helper.resetPropertyPrefix("fooPrefix");
        
        final ProtocolAttachmentProtocol attachment = new ProtocolAttachmentProtocol();
        attachment.setType(new ProtocolAttachmentType("11", "a desc"));
        attachment.setAttachmentVersionNumber(1);
        attachment.setDocumentId(1);
        attachment.setProtocol(null);
        
        this.context.checking(new Expectations() {{         
            one(ddService).isBusinessObjectValid(attachment, "fooPrefix");
            will(returnValue(false));
        }});
        
        boolean valid = helper.validAgainstDictionary(attachment);
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should not be valid", valid, is(false));
    }
    
}
