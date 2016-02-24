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
import org.junit.Test;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesFinder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;

/**
 * Tests for {@link ProtocolAttachmentTypeByGroupValuesFinder ProtocolAttachmentTypeByGroupValuesFinder}.
 */
public class ProtocolAttachmentTypeByGroupValuesFinderTest {
    
    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    
    @Test(expected=IllegalStateException.class)
    public void getTypeInvalidGroupCode() {
        ProtocolAttachmentTypeByGroupValuesFinder finder = new ProtocolAttachmentTypeByGroupValuesFinder();
        finder.getKeyValues();
    }
    
    /** tests that this class works correctly when the filter types are not set.  filter types are optional. */
    @Test
    public void getTypeNullFilterTypes() {
        final KeyValuesFinder usedFinder = this.context.mock(KeyValuesFinder.class);
        
        ProtocolAttachmentTypeByGroupValuesFinder finder = new ProtocolAttachmentTypeByGroupValuesFinder() {
            @Override
            KeyValuesFinder createKeyValuesFinder() {
                return usedFinder;
            }    
        };
        finder.setGroupCode("1");
        finder.setFilterTypes(null);
        final List<KeyValue> kv = new ArrayList<KeyValue>();
        
        this.context.checking(new Expectations() {
            {
                oneOf(usedFinder).getKeyValues();
                will(returnValue(kv));
            }
        });
        List<KeyValue> gotKv = finder.getKeyValues();
        
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should be equal", gotKv, is(kv));
    }
    
    /** tests that this class works correctly when the filter types are set. */
    @Test
    public void getTypeWithFilterTypes() {
        final KeyValuesFinder usedFinder = this.context.mock(KeyValuesFinder.class);
        
        ProtocolAttachmentTypeByGroupValuesFinder<ProtocolAttachmentProtocol> finder
            = new ProtocolAttachmentTypeByGroupValuesFinder<ProtocolAttachmentProtocol>() {
            @Override
            KeyValuesFinder createKeyValuesFinder() {
                return usedFinder;
            }    
        };
        finder.setGroupCode("1");
        
        ProtocolAttachmentProtocol pa = new ProtocolAttachmentProtocol();
        ProtocolAttachmentType type = new ProtocolAttachmentType();
        type.setCode("1");
        pa.setType(type);
        pa.setType(new ProtocolAttachmentType("1", "a desc"));
        Collection<ProtocolAttachmentProtocol> filterTypes = Collections.singletonList(pa);
        finder.setFilterTypes(filterTypes);
        
        final List<KeyValue> kv = new ArrayList<KeyValue>();
        KeyValue kl1 = new ConcreteKeyValue("1", "a desc");
        KeyValue kl2 = new ConcreteKeyValue("2", "a desc");
        kv.add(kl1);
        kv.add(kl2);
        
        this.context.checking(new Expectations() {
            {
                oneOf(usedFinder).getKeyValues();
                will(returnValue(kv));
            }
        });
        List<KeyValue> gotKv = finder.getKeyValues();
        
        
        this.context.assertIsSatisfied();
        
        for (KeyValue aKv : gotKv) {
            if ("1".equals(aKv.getKey())) {
                Assert.fail("contains a type that should be filtered");
            }
        }
        
        Assert.assertThat("Should be equal", gotKv.size(), is(1));
    }
}

