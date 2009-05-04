/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.noteattachment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesFinder;
import org.kuali.rice.kns.web.ui.KeyLabelPair;
import static org.hamcrest.core.Is.*;

/**
 * Tests for {@link ProtocolAttachmentTypeByGroupValuesFinder ProtocolAttachmentTypeByGroupValuesFinder}.
 */
public class ProtocolAttachmentTypeByGroupValuesFinderTest {
    
    private Mockery context = new JUnit4Mockery();
    
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
        final List<KeyLabelPair> kv = new ArrayList<KeyLabelPair>();
        
        this.context.checking(new Expectations() {
            {
                oneOf(usedFinder).getKeyValues();
                will(returnValue(kv));
            }
        });
        List<KeyLabelPair> gotKv = finder.getKeyValues();
        
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat("Should be equal", gotKv, is(kv));
    }
    
    /** tests that this class works correctly when the filter types are set. */
    @Test
    public void getTypeWithFilterTypes() {
        final KeyValuesFinder usedFinder = this.context.mock(KeyValuesFinder.class);
        
        ProtocolAttachmentTypeByGroupValuesFinder finder = new ProtocolAttachmentTypeByGroupValuesFinder() {
            @Override
            KeyValuesFinder createKeyValuesFinder() {
                return usedFinder;
            }    
        };
        finder.setGroupCode("1");
        
        ProtocolAttachmentBase pa = new ProtocolAttachmentProtocol();
        ProtocolAttachmentType type = new ProtocolAttachmentType();
        type.setCode("1");
        pa.setType(type);
        pa.setType(new ProtocolAttachmentType("1", "a desc"));
        Collection<ProtocolAttachmentBase> filterTypes = Collections.singletonList(pa);
        finder.setFilterTypes(filterTypes);
        
        final List<KeyLabelPair> kv = new ArrayList<KeyLabelPair>();
        KeyLabelPair kl1 = new KeyLabelPair("1", "a desc");
        KeyLabelPair kl2 = new KeyLabelPair("2", "a desc");
        kv.add(kl1);
        kv.add(kl2);
        
        this.context.checking(new Expectations() {
            {
                oneOf(usedFinder).getKeyValues();
                will(returnValue(kv));
            }
        });
        List<KeyLabelPair> gotKv = finder.getKeyValues();
        
        
        this.context.assertIsSatisfied();
        
        for (KeyLabelPair aKv : gotKv) {
            if ("1".equals(aKv.getKey())) {
                Assert.fail("contains a type that should be filtered");
            }
        }
        
        Assert.assertThat("Should be equal", gotKv.size(), is(1));
    }
}

