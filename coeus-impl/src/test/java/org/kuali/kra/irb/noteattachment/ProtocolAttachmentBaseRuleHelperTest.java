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
package org.kuali.kra.irb.noteattachment;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.impl.validation.ErrorReporterImpl;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.test.ProtocolTestUtil;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import static org.hamcrest.core.Is.is;

/**
 * Tests the {@link ProtocolAttachmentBaseRuleHelper ProtocolAttachmentBaseRuleHelper} class.
 */
public class ProtocolAttachmentBaseRuleHelperTest {

    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};

    @Before
    public void setupGlobalVars() {
        GlobalVariables.setAuditErrorMap(new HashMap());
        GlobalVariables.setMessageMap(new MessageMap());
    }
    
    /**
     * Tests conditions related to a description being empty but valid.
     */
    @Test
    public void validDescriptionEmpty() {
        final ProtocolAttachmentService paService = this.context.mock(ProtocolAttachmentService.class); 
        final DictionaryValidationService ddService = this.context.mock(DictionaryValidationService.class);
        
        ProtocolAttachmentBaseRuleHelper helper = new ProtocolAttachmentBaseRuleHelper(paService, ddService);
        helper.setErrorReporter(new ErrorReporterImpl());
        ProtocolAttachmentProtocol attachment = new ProtocolAttachmentProtocol();
        attachment.setType(new ProtocolAttachmentType("3", "a desc"));
        
        boolean valid = helper.validDescriptionWhenRequired(attachment);
        
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
        helper.setErrorReporter(new ErrorReporterImpl());
        ProtocolAttachmentProtocol attachment = new ProtocolAttachmentProtocol();
        attachment.setType(new ProtocolAttachmentType("3", "a desc"));
        attachment.setDescription("a desc");
        
        boolean valid = helper.validDescriptionWhenRequired(attachment);
        
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
        helper.setErrorReporter(new ErrorReporterImpl());
        ProtocolAttachmentProtocol attachment = new ProtocolAttachmentProtocol();
        attachment.setType(new ProtocolAttachmentType("9", "a desc"));
        
        this.context.checking(new Expectations() {{
            ProtocolAttachmentType type = new ProtocolAttachmentType();
            type.setCode("9");
            type.setDescription("not Other");
            
            one(paService).getTypeFromCode("9");
            will(returnValue(type));
        }});
        
        boolean valid = helper.validDescriptionWhenRequired(attachment);
        
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
        helper.setErrorReporter(new ErrorReporterImpl());
        helper.resetPropertyPrefix("fooPrefix");
        
        final ProtocolAttachmentProtocol attachment = new ProtocolAttachmentProtocol();
        attachment.setType(new ProtocolAttachmentType("9", "a desc"));
        attachment.setSequenceNumber(1);
        attachment.setDocumentId(1);
        Protocol protocol = ProtocolTestUtil.getProtocol(this.context);
        protocol.setProtocolId(1L);
        
        this.context.checking(new Expectations() {{         
            one(ddService).isBusinessObjectValid(attachment, "fooPrefix");
            will(returnValue(true));
        }});
        
        boolean valid = helper.validPrimitiveFields(attachment);
        
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
        helper.setErrorReporter(new ErrorReporterImpl());
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
        helper.setErrorReporter(new ErrorReporterImpl());
        helper.resetPropertyPrefix("fooPrefix");
        
        final ProtocolAttachmentProtocol attachment = new ProtocolAttachmentProtocol();
        attachment.setType(new ProtocolAttachmentType("9", "a desc"));
        
        this.context.checking(new Expectations() {{         
            Collection<ProtocolAttachmentType> types = new ArrayList<ProtocolAttachmentType>();
            ProtocolAttachmentType aType = new ProtocolAttachmentType();
            aType.setCode("9");
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
        helper.setErrorReporter(new ErrorReporterImpl());
        helper.resetPropertyPrefix("fooPrefix");
        
        final ProtocolAttachmentProtocol attachment = new ProtocolAttachmentProtocol();
        attachment.setType(new ProtocolAttachmentType("9", "a desc"));
        
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
            aType2.setCode("9");
            aType2.setDescription("a desc");
            
            one(paService).getTypeFromCode("9");
            will(returnValue(aType2));
        }});
        helper.setErrorReporter(new ErrorReporterImpl());
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
        helper.setErrorReporter(new ErrorReporterImpl());
        helper.resetPropertyPrefix("fooPrefix");
        
        final ProtocolAttachmentProtocol attachment = new ProtocolAttachmentProtocol();
        attachment.setType(new ProtocolAttachmentType("9", "a desc"));
        
        this.context.checking(new Expectations() {{         
            Collection<ProtocolAttachmentType> types = new ArrayList<ProtocolAttachmentType>();
            ProtocolAttachmentType aType = new ProtocolAttachmentType();
            aType.setCode("12");
            types.add(aType);
            
            one(paService).getTypesForGroup(attachment.getGroupCode());
            will(returnValue(types));
            
            one(paService).getTypeFromCode("9");
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
        helper.setErrorReporter(new ErrorReporterImpl());
        helper.resetPropertyPrefix("fooPrefix");
        
        final ProtocolAttachmentProtocol attachment = new ProtocolAttachmentProtocol();
        attachment.setType(new ProtocolAttachmentType("9", "a desc"));
        attachment.setSequenceNumber(1);
        attachment.setDocumentId(1);
        
        this.context.checking(new Expectations() {{         
            one(ddService).isBusinessObjectValid(attachment, "fooPrefix");
            will(returnValue(false));
        }});
        
        boolean valid = helper.validPrimitiveFields(attachment);
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should not be valid", valid, is(false));
    }
    
}
