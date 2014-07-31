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
package org.kuali.kra.irb.noteattachment;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.coeus.sys.impl.validation.ErrorReporterImpl;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.HashMap;

import static org.hamcrest.core.Is.is;

/**
 * Tests for {@link ProtocolAttachmentProtocolRuleHelper ProtocolAttachmentProtocolRuleHelper}.
 */
public class ProtocolAttachmentProtocolRuleHelperTest {

    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    
    @Before
    public void setupGlobalVars() {
        KNSGlobalVariables.setAuditErrorMap(new HashMap());
        GlobalVariables.setMessageMap(new MessageMap());
    }
    
    /**
     * Tests a valid status with blank code.
     */
    @Test
    public void validStatusBlankCode() {
        
        ProtocolAttachmentProtocolRuleHelper helper = new ProtocolAttachmentProtocolRuleHelper();
        helper.setErrorReporter(new ErrorReporterImpl());
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
        
        ProtocolAttachmentProtocolRuleHelper helper = new ProtocolAttachmentProtocolRuleHelper();
        helper.setErrorReporter(new ErrorReporterImpl());
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
        
        ProtocolAttachmentProtocolRuleHelper helper = new ProtocolAttachmentProtocolRuleHelper();
        helper.setErrorReporter(new ErrorReporterImpl());
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
        
        ProtocolAttachmentProtocolRuleHelper helper = new ProtocolAttachmentProtocolRuleHelper();
        helper.setErrorReporter(new ErrorReporterImpl());
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
        
        ProtocolAttachmentProtocolRuleHelper helper = new ProtocolAttachmentProtocolRuleHelper();
        helper.setErrorReporter(new ErrorReporterImpl());
        helper.resetPropertyPrefix("fooPrefix");
        
        final ProtocolAttachmentProtocol attachment = new ProtocolAttachmentProtocol();
        attachment.setStatus(new ProtocolAttachmentStatus("9", "a desc"));

        boolean valid = helper.validStatusForSubmission(attachment);
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should not be valid", valid, is(false));
    }
}
