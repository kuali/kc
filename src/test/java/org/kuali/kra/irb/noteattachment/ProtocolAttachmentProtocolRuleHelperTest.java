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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.test.ProtocolTestUtil;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import static org.hamcrest.core.Is.*;

/**
 * Tests for {@link ProtocolAttachmentProtocolRuleHelper ProtocolAttachmentProtocolRuleHelper}.
 */
public class ProtocolAttachmentProtocolRuleHelperTest {

    private Mockery context = new JUnit4Mockery();
    
    @Before
    public void setupGlobalVars() {
        GlobalVariables.setAuditErrorMap(new HashMap());
        GlobalVariables.setErrorMap(new ErrorMap());
    }
    
    /**
     * Tests a duplicate type.
     */
    @Test
    public void duplicateTypeInvalid() {
        ProtocolAttachmentProtocol attachmentToValidate = this.getAttachmentToValidate();
               
        Protocol protocol = ProtocolTestUtil.getProtocol(this.context);
        
        List<ProtocolAttachmentProtocol> attachments = new ArrayList<ProtocolAttachmentProtocol>();
        ProtocolAttachmentProtocol attachment1 = new ProtocolAttachmentProtocol();
        attachment1.setId(2L);
        attachment1.setType(new ProtocolAttachmentType("1", "a desc"));
        attachments.add(attachment1);
        
        protocol.setAttachmentProtocols(attachments);
               
        ProtocolAttachmentService service = this.context.mock(ProtocolAttachmentService.class);
        ProtocolAttachmentProtocolRuleHelper helper = new ProtocolAttachmentProtocolRuleHelper(service);
        
        boolean valid = helper.duplicateType(attachmentToValidate, protocol);
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should not be valid", valid, is(false));
    }
    
    /**
     * Tests a unique type is valid.
     */
    @Test
    public void duplicateTypeValidDiffType() {
        ProtocolAttachmentProtocol attachmentToValidate = this.getAttachmentToValidate();
        
        Protocol protocol = ProtocolTestUtil.getProtocol(this.context);
        
        List<ProtocolAttachmentProtocol> attachments = new ArrayList<ProtocolAttachmentProtocol>();
        ProtocolAttachmentProtocol attachment1 = new ProtocolAttachmentProtocol();
        attachment1.setId(2L);
        attachment1.setType(new ProtocolAttachmentType("2", "a desc"));
        attachments.add(attachment1);
        
        protocol.setAttachmentProtocols(attachments);
               
        ProtocolAttachmentService service = this.context.mock(ProtocolAttachmentService.class);
        ProtocolAttachmentProtocolRuleHelper helper = new ProtocolAttachmentProtocolRuleHelper(service);
        
        boolean valid = helper.duplicateType(attachmentToValidate, protocol);
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should be valid", valid, is(true));
    }
    
    /**
     * Tests same type, same attachment is valid.
     */
    @Test
    public void duplicateTypeValidSameAttachment() {
        ProtocolAttachmentProtocol attachmentToValidate = this.getAttachmentToValidate();
        
        Protocol protocol = ProtocolTestUtil.getProtocol(this.context);
        
        List<ProtocolAttachmentProtocol> attachments = new ArrayList<ProtocolAttachmentProtocol>();
        ProtocolAttachmentProtocol attachment1 = new ProtocolAttachmentProtocol();
        attachment1.setId(1L);
        attachment1.setType(new ProtocolAttachmentType("1", "a desc"));
        attachments.add(attachment1);
        
        protocol.setAttachmentProtocols(attachments);
               
        ProtocolAttachmentService service = this.context.mock(ProtocolAttachmentService.class);
        ProtocolAttachmentProtocolRuleHelper helper = new ProtocolAttachmentProtocolRuleHelper(service);
        
        boolean valid = helper.duplicateType(attachmentToValidate, protocol);
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should be valid", valid, is(true));
    }
    
    /**
     * Tests a valid status with blank code.
     */
    @Test
    public void validStatusBlankCode() {
        final ProtocolAttachmentService paService = this.context.mock(ProtocolAttachmentService.class); 
        
        ProtocolAttachmentProtocolRuleHelper helper = new ProtocolAttachmentProtocolRuleHelper(paService);
        helper.resetPropertyPrefix("fooPrefix");
        
        final ProtocolAttachmentProtocol attachment = new ProtocolAttachmentProtocol();
        attachment.setStatus(null);
                
        boolean valid = helper.validStatus(attachment);
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should be valid", valid, is(true));
    }
    
    /**
     * Tests a valid status with valid code.
     */
    @Test
    public void validStatus() {
        final ProtocolAttachmentService paService = this.context.mock(ProtocolAttachmentService.class); 
        
        ProtocolAttachmentProtocolRuleHelper helper = new ProtocolAttachmentProtocolRuleHelper(paService);
        helper.resetPropertyPrefix("fooPrefix");
        
        final ProtocolAttachmentProtocol attachment = new ProtocolAttachmentProtocol();
        attachment.setStatus(new ProtocolAttachmentStatus("1", "a desc"));
        
        this.context.checking(new Expectations() {{
        }});
        
        boolean valid = helper.validStatus(attachment);
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should be valid", valid, is(true));
    }
    
    /**
     * Tests a invalid status for submission with null.
     */
    @Test
    public void invalidStatusValidCodeForSubmissionNull() {
        final ProtocolAttachmentService paService = this.context.mock(ProtocolAttachmentService.class); 
        
        ProtocolAttachmentProtocolRuleHelper helper = new ProtocolAttachmentProtocolRuleHelper(paService);
        helper.resetPropertyPrefix("fooPrefix");
        
        final ProtocolAttachmentProtocol attachment = new ProtocolAttachmentProtocol();
        attachment.setStatus(null);
               
        boolean valid = helper.validStatusForSubmission(attachment);
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should not be valid", valid, is(false));
    }
    
    /**
     * Tests a invalid status for submission with valid code.
     */
    @Test
    public void invalidStatusValidCodeForSubmissionValidCode() {
        final ProtocolAttachmentService paService = this.context.mock(ProtocolAttachmentService.class); 
        
        ProtocolAttachmentProtocolRuleHelper helper = new ProtocolAttachmentProtocolRuleHelper(paService);
        helper.resetPropertyPrefix("fooPrefix");
        
        final ProtocolAttachmentProtocol attachment = new ProtocolAttachmentProtocol();
        attachment.setStatus(new ProtocolAttachmentStatus("1", "a desc"));
        
        boolean valid = helper.validStatusForSubmission(attachment);
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should not be valid", valid, is(false));
    }
    
    /**
     * Tests a valid status for submission with valid code.
     */
    @Test
    public void validStatusValidCodeForSubmission() {
        final ProtocolAttachmentService paService = this.context.mock(ProtocolAttachmentService.class); 
        
        ProtocolAttachmentProtocolRuleHelper helper = new ProtocolAttachmentProtocolRuleHelper(paService);
        helper.resetPropertyPrefix("fooPrefix");
        
        final ProtocolAttachmentProtocol attachment = new ProtocolAttachmentProtocol();
        attachment.setStatus(new ProtocolAttachmentStatus("9", "a desc"));

        boolean valid = helper.validStatusForSubmission(attachment);
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should not be valid", valid, is(false));
    }
    
    /**
     * Gets a ProtocolAttachmentProtocol to validate.
     * @return a ProtocolAttachmentProtocol to validate.
     */
    private ProtocolAttachmentProtocol getAttachmentToValidate() {
        ProtocolAttachmentProtocol attachment = new ProtocolAttachmentProtocol();
        attachment.setId(1L);
        attachment.setType(new ProtocolAttachmentType("1", "a desc"));
        
        return attachment;
    }
}
