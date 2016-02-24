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
        GlobalVariables.setAuditErrorMap(new HashMap());
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
