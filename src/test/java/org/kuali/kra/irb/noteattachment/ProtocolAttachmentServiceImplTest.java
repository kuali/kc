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
import java.util.Collections;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.irb.personnel.ProtocolPerson;

/**
 * Tests for {@link ProtocolAttachmentServiceImpl ProtocolAttachmentServiceImpl}.
 */
public class ProtocolAttachmentServiceImplTest {
    
    private Mockery context = new JUnit4Mockery();
    
    /** tests calling getStatusFromCode with null arg. */
    @Test(expected=IllegalArgumentException.class)
    public void getStatusFromCodeNullCode() {
        final BusinessObjectService boService = this.context.mock(BusinessObjectService.class);
        ProtocolAttachmentServiceImpl paService = new ProtocolAttachmentServiceImpl(boService);
        paService.getStatusFromCode(null);
        
        this.context.assertIsSatisfied();
    }
    
    /** tests calling getTypeFromCode with null arg. */
    @Test(expected=IllegalArgumentException.class)
    public void getTypeFromCodeNullCode() {
        final BusinessObjectService boService = this.context.mock(BusinessObjectService.class);
        ProtocolAttachmentServiceImpl paService = new ProtocolAttachmentServiceImpl(boService);
        paService.getTypeFromCode(null);
        
        this.context.assertIsSatisfied();
    }
    
    /** tests calling getTypeFromCode with valid arg. */
    @Test
    public void getStatusFromCodeGoodCode() {
        final BusinessObjectService boService = this.context.mock(BusinessObjectService.class);
        ProtocolAttachmentServiceImpl paService = new ProtocolAttachmentServiceImpl(boService);
        final ProtocolAttachmentStatus status = new ProtocolAttachmentStatus();
        status.setCode("1");
        status.setDescription("a desc");
        
        this.context.checking(new Expectations() {
            {
                oneOf(boService).findByPrimaryKey(ProtocolAttachmentStatus.class, Collections.singletonMap("code", "1"));
                will(returnValue(status));
            }
        });
        
        final ProtocolAttachmentStatus gotStatus = paService.getStatusFromCode("1");
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should be equal", gotStatus, is(status));
    }
    
    /** tests calling getTypeFromCode with valid arg. */
    @Test
    public void getTypeFromCodeGoodCode() {
        final BusinessObjectService boService = this.context.mock(BusinessObjectService.class);
        ProtocolAttachmentServiceImpl paService = new ProtocolAttachmentServiceImpl(boService);
        final ProtocolAttachmentType type = new ProtocolAttachmentType();
        type.setCode("1");
        type.setDescription("a desc");
        
        this.context.checking(new Expectations() {
            {
                oneOf(boService).findByPrimaryKey(ProtocolAttachmentType.class, Collections.singletonMap("code", "1"));
                will(returnValue(type));
            }
        });
        
        final ProtocolAttachmentType gotType = paService.getTypeFromCode("1");
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should be equal", gotType, is(type));
    }
    
    /** tests calling getTypesForGroup with null arg. */
    @Test(expected=IllegalArgumentException.class)
    public void getTypesForGroupNullCode() {
        final BusinessObjectService boService = this.context.mock(BusinessObjectService.class);
        ProtocolAttachmentServiceImpl paService = new ProtocolAttachmentServiceImpl(boService);
        paService.getTypesForGroup(null);
        
        this.context.assertIsSatisfied();
    }
    
    /** tests calling getTypesForGroup with valid arg where the group is found. */
    @Test
    public void getTypesForGroupFound() {
        final BusinessObjectService boService = this.context.mock(BusinessObjectService.class);
        ProtocolAttachmentServiceImpl paService = new ProtocolAttachmentServiceImpl(boService);
        final ProtocolAttachmentType type = new ProtocolAttachmentType();
        type.setCode("1");
        type.setDescription("a desc");
        final Collection<ProtocolAttachmentType> types = Collections.singletonList(type);
        
        this.context.checking(new Expectations() {
            {
                ProtocolAttachmentTypeGroup typeGroup = new ProtocolAttachmentTypeGroup();
                typeGroup.setType(type);
                typeGroup.setTypeCode("1");
                typeGroup.setGroupCode("1");
                oneOf(boService).findMatching(ProtocolAttachmentTypeGroup.class, Collections.singletonMap("groupCode", "1"));
                will(returnValue(Collections.singletonList(typeGroup)));
            }
        });
        
        final Collection<ProtocolAttachmentType> gotTypes = paService.getTypesForGroup("1");
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should be equal", gotTypes, is(types));
    }
    
    /** tests calling getTypesForGroup with valid arg where the group is not found. */
    @Test
    public void getTypesForGroupNotFound() {
        final BusinessObjectService boService = this.context.mock(BusinessObjectService.class);
        ProtocolAttachmentServiceImpl paService = new ProtocolAttachmentServiceImpl(boService);

        final Collection<ProtocolAttachmentType> types = new ArrayList<ProtocolAttachmentType>();
        
        this.context.checking(new Expectations() {
            {
                oneOf(boService).findMatching(ProtocolAttachmentTypeGroup.class, Collections.singletonMap("groupCode", "1"));
                will(returnValue(null));
            }
        });
        
        final Collection<ProtocolAttachmentType> gotTypes = paService.getTypesForGroup("1");
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should be equal", gotTypes, is(types));
    }
    
    /** tests calling getPerson with null arg. */
    @Test(expected=IllegalArgumentException.class)
    public void getPersonNullId() {
        final BusinessObjectService boService = this.context.mock(BusinessObjectService.class);
        ProtocolAttachmentServiceImpl paService = new ProtocolAttachmentServiceImpl(boService);
        paService.getPerson(null);
        
        this.context.assertIsSatisfied();
    }
    
    /** tests calling getPerson with valid arg. */
    @Test
    public void getPersonGoodId() {
        final BusinessObjectService boService = this.context.mock(BusinessObjectService.class);
        ProtocolAttachmentServiceImpl paService = new ProtocolAttachmentServiceImpl(boService);
        final ProtocolPerson person = new ProtocolPerson();
        person.setProtocolPersonId(1);
        person.setPersonName("Foo bar");
        
        this.context.checking(new Expectations() {
            {
                oneOf(boService).findByPrimaryKey(ProtocolPerson.class, Collections.singletonMap("protocolPersonId", 1));
                will(returnValue(person));
            }
        });
        
        final ProtocolPerson gotPerson = paService.getPerson(1);
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should be equal", gotPerson, is(person));
    }
    
    /**
     * placeholder method for testing saves.
     */
    @Test @Ignore("save methods will probably change - not testing yet")
    public void saveAttachment() {
        //nothing to do yet...
    }
}
