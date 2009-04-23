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
import java.util.HashMap;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.test.ProtocolTestUtil;

/**
 * Tests for {@link ProtocolAttachmentPersonnelRuleHelper ProtocolAttachmentPersonnelRuleHelper}.
 */
public class ProtocolAttachmentPersonnelRuleHelperTest {
    
    private Mockery context = new JUnit4Mockery();
    
    @Before
    public void setupGlobalVars() {
        GlobalVariables.setAuditErrorMap(new HashMap());
        GlobalVariables.setErrorMap(new ErrorMap());
    }
    
    @Test @Ignore public void wip() {}
    
//    /**
//     * Tests an invalid person/type combination.
//     */
//    @Test
//    public void duplicateTypePersonInvalid() {
//        ProtocolAttachmentPersonnel attachmentToValidate = this.getAttachmentToValidate();
//               
//        Protocol protocol = ProtocolTestUtil.getProtocol(this.context);
//        
//        List<ProtocolAttachmentPersonnel> attachments = new ArrayList<ProtocolAttachmentPersonnel>();
//        ProtocolAttachmentPersonnel attachment1 = new ProtocolAttachmentPersonnel();
//        attachment1.setId(2L);
//        attachment1.setTypeCode("1");
//        attachment1.setPersonId(1);
//        attachments.add(attachment1);
//        
//        protocol.setAttachmentPersonnels(attachments);
//               
//        ProtocolAttachmentService service = this.context.mock(ProtocolAttachmentService.class);
//        ProtocolAttachmentPersonnelRuleHelper helper = new ProtocolAttachmentPersonnelRuleHelper(service);
//        
//        boolean valid = helper.duplicateTypePerson(attachmentToValidate, protocol);
//        
//        this.context.assertIsSatisfied();
//        
//        Assert.assertThat("Should not be valid", valid, is(false));
//    }
//    
//    /**
//     * Tests an valid person/type combination with different type.
//     */
//    @Test
//    public void duplicateTypePersonValidDiffType() {
//        ProtocolAttachmentPersonnel attachmentToValidate = this.getAttachmentToValidate();
//        
//        Protocol protocol = ProtocolTestUtil.getProtocol(this.context);
//        
//        List<ProtocolAttachmentPersonnel> attachments = new ArrayList<ProtocolAttachmentPersonnel>();
//        ProtocolAttachmentPersonnel attachment1 = new ProtocolAttachmentPersonnel();
//        attachment1.setId(2L);
//        attachment1.setTypeCode("2");
//        attachment1.setPersonId(1);
//        attachments.add(attachment1);
//        
//        protocol.setAttachmentPersonnels(attachments);
//        
//        
//        ProtocolAttachmentService service = this.context.mock(ProtocolAttachmentService.class);
//        ProtocolAttachmentPersonnelRuleHelper helper = new ProtocolAttachmentPersonnelRuleHelper(service);
//        
//        boolean valid = helper.duplicateTypePerson(attachmentToValidate, protocol);
//        
//        this.context.assertIsSatisfied();
//        
//        Assert.assertThat("Should be valid", valid, is(true));
//    }
//    
//    /**
//     * Tests an valid person/type combination with different person.
//     */
//    @Test
//    public void duplicateTypePersonValidDiffPerson() {
//        ProtocolAttachmentPersonnel attachmentToValidate = this.getAttachmentToValidate();
//        
//        Protocol protocol = ProtocolTestUtil.getProtocol(this.context);
//        
//        List<ProtocolAttachmentPersonnel> attachments = new ArrayList<ProtocolAttachmentPersonnel>();
//        ProtocolAttachmentPersonnel attachment1 = new ProtocolAttachmentPersonnel();
//        attachment1.setId(2L);
//        attachment1.setTypeCode("1");
//        attachment1.setPersonId(2);
//        attachments.add(attachment1);
//        
//        protocol.setAttachmentPersonnels(attachments);
//        
//        ProtocolAttachmentService service = this.context.mock(ProtocolAttachmentService.class);
//        ProtocolAttachmentPersonnelRuleHelper helper = new ProtocolAttachmentPersonnelRuleHelper(service);
//        
//        boolean valid = helper.duplicateTypePerson(attachmentToValidate, protocol);
//        
//        this.context.assertIsSatisfied();
//        
//        Assert.assertThat("Should be valid", valid, is(true));
//    }
//    
//    /**
//     * Tests an valid person/type combination where the attachment contains the same id as an attachment in the Protocol.
//     */
//    @Test
//    public void duplicateTypePersonValidSameAttachment() {
//        ProtocolAttachmentPersonnel attachmentToValidate = this.getAttachmentToValidate();
//        
//        Protocol protocol = ProtocolTestUtil.getProtocol(this.context);
//        
//        List<ProtocolAttachmentPersonnel> attachments = new ArrayList<ProtocolAttachmentPersonnel>();
//        ProtocolAttachmentPersonnel attachment1 = new ProtocolAttachmentPersonnel();
//        attachment1.setId(1L);
//        attachment1.setTypeCode("1");
//        attachment1.setPersonId(1);
//        attachments.add(attachment1);
//        
//        protocol.setAttachmentPersonnels(attachments);
//        
//        ProtocolAttachmentService service = this.context.mock(ProtocolAttachmentService.class);
//        ProtocolAttachmentPersonnelRuleHelper helper = new ProtocolAttachmentPersonnelRuleHelper(service);
//        
//        boolean valid = helper.duplicateTypePerson(attachmentToValidate, protocol);
//        
//        this.context.assertIsSatisfied();
//        
//        Assert.assertThat("Should be valid", valid, is(true));
//    }
//    
//    /**
//     * Tests an available person for an attachment.
//     */
//    @Test
//    public void personAvailable() {
//        ProtocolAttachmentPersonnel attachmentToValidate = this.getAttachmentToValidate();
//        
//        Protocol protocol = ProtocolTestUtil.getProtocol(this.context);
//        
//        ProtocolPerson person1 = new ProtocolPerson();
//        person1.setProtocolPersonId(1);
//        protocol.getProtocolPersons().add(person1);
//
//        ProtocolAttachmentService service = this.context.mock(ProtocolAttachmentService.class);
//        ProtocolAttachmentPersonnelRuleHelper helper = new ProtocolAttachmentPersonnelRuleHelper(service);
//        
//        boolean valid = helper.availablePerson(attachmentToValidate, protocol);
//        
//        this.context.assertIsSatisfied();
//        
//        Assert.assertThat("Should be valid", valid, is(true));
//    }
//    
//    /**
//     * Tests a not available person for an attachment.
//     */
//    @Test
//    public void personNotAvailable() {
//        ProtocolAttachmentPersonnel attachmentToValidate = this.getAttachmentToValidate();
//        
//        Protocol protocol = ProtocolTestUtil.getProtocol(this.context);
//        
//        List<ProtocolPerson> persons = new ArrayList<ProtocolPerson>();
//        ProtocolPerson person1 = new ProtocolPerson();
//        person1.setProtocolPersonId(2);
//        protocol.getProtocolPersons().add(person1);
//        
//        final ProtocolAttachmentService service = this.context.mock(ProtocolAttachmentService.class);
//        ProtocolAttachmentPersonnelRuleHelper helper = new ProtocolAttachmentPersonnelRuleHelper(service);
//        
//        this.context.checking(new Expectations() {{
//            oneOf(service).getPerson(1);
//            ProtocolPerson person = new ProtocolPerson();
//            person.setPersonId("1");
//            person.setPersonName("W. Axl Rose");
//            will(returnValue(person));
//        }});
//        
//        boolean valid = helper.availablePerson(attachmentToValidate, protocol);
//        
//        this.context.assertIsSatisfied();
//        
//        Assert.assertThat("Should not be valid", valid, is(false));
//    }
//    
//    /**
//     * Gets a ProtocolAttachmentPersonnel to validate.
//     * @return a ProtocolAttachmentPersonnel to validate.
//     */
//    private ProtocolAttachmentPersonnel getAttachmentToValidate() {
//        ProtocolAttachmentPersonnel attachment = new ProtocolAttachmentPersonnel();
//        attachment.setId(1L);
//        attachment.setTypeCode("1");
//        attachment.setPersonId(1);
//        
//        return attachment;
//    }
}
