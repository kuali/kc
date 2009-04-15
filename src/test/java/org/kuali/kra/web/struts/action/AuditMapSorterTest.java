/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.web.struts.action;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.util.AuditCluster;
import org.kuali.core.util.AuditError;

/**
 * Tests the {@link AuditMapSorter AuditMapSorter} class.
 */
public class AuditMapSorterTest {
    
    private Map<String, AuditCluster> fooBarErrorMap;
    private Mockery context = new JUnit4Mockery();
    
    @Before
    public void setupFooBarMap() {
        
        fooBarErrorMap = new HashMap<String, AuditCluster>();
        {
            AuditCluster a = new AuditCluster();
            List<AuditError> auditErrorList = new ArrayList<AuditError>();
            auditErrorList.add(new AuditError("fe", "fi", "fo", new String[] {"fum"}));
            auditErrorList.add(new AuditError("fee", "fii", "foo", new String[] {"fumm"}));
            a.setAuditErrorList(auditErrorList);
            
            fooBarErrorMap.put("foo", a); 
        }
        
        {
            AuditCluster a = new AuditCluster();
            List<AuditError> auditErrorList = new ArrayList<AuditError>();
            auditErrorList.add(new AuditError("fe", "fi", "fo", new String[] {"fum"}));
            auditErrorList.add(new AuditError("fee", "fii", "foo", new String[] {"fumm"}));
            a.setAuditErrorList(auditErrorList);
            
            fooBarErrorMap.put("bar", a); 
        }
    }
    
    /**
     * Test AuditMapSorter creation is null auditErrorsMap.
     */
    @Test(expected = NullPointerException.class)
    public void testNullAuditErrorsMap() {
        new AuditMapSorter(null);
    }
    
    
    /**
     * Test sort with null comparator map.
     */
    @Test(expected = NullPointerException.class)
    public void testNullCompMap() {
        new AuditMapSorter(new HashMap<String, AuditCluster>()).sort(null);
    }
    
    /**
     * Test sort with empty comparator map.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyCompMap() {
        new AuditMapSorter(new HashMap<String, AuditCluster>()).sort(new HashMap<String, Comparator<AuditError>>());
    }
    
    /**
     * Test null Comparator in comparator map.
     */
    @Test(expected = NullPointerException.class)
    public void testNullCompInMap() {
        Map<String, Comparator<AuditError>> m = new HashMap<String, Comparator<AuditError>>();
        m.put("foo", null);
        
        new AuditMapSorter(fooBarErrorMap).sort(m);
    }
    
    /**
     * Tests that sort happens with single entry in map.
     */
    @Test
    public void testSortHappensSingle() {
        Map<String, Comparator<AuditError>> compMap = new HashMap<String, Comparator<AuditError>>();
        final Comparator<AuditError> comp = this.context.mock(Comparator.class);
        compMap.put("bar", comp);
        
        this.fooBarErrorMap.remove("foo");
        
        //ensure that this test does what it says it does
        Assert.assertEquals(1, fooBarErrorMap.size());
        Assert.assertTrue("does not contain key bar", fooBarErrorMap.containsKey("bar"));
        
        context.checking(new Expectations() {
            {
                AuditError e0 = (AuditError) fooBarErrorMap.get("bar").getAuditErrorList().get(0);
                AuditError e1 = (AuditError) fooBarErrorMap.get("bar").getAuditErrorList().get(1);
                
                oneOf(comp).compare(e0, e1);
            }
        });
        
        new AuditMapSorter(fooBarErrorMap).sort(compMap);
        
        context.assertIsSatisfied();
    }
    
    /**
     * Tests that sort happens with multiple entries in map.
     */
    @Test
    public void testSortHappensMult() {
        Map<String, Comparator<AuditError>> compMap = new HashMap<String, Comparator<AuditError>>();
        final Comparator<AuditError> comp = this.context.mock(Comparator.class);
        compMap.put(".*", comp);
        
        //ensure that this test does what it says it does
        Assert.assertEquals(2, fooBarErrorMap.size());
        
        context.checking(new Expectations() {
            {
                AuditError e0 = (AuditError) fooBarErrorMap.get("bar").getAuditErrorList().get(0);
                AuditError e1 = (AuditError) fooBarErrorMap.get("bar").getAuditErrorList().get(1);
                
                oneOf(comp).compare(e0, e1);
                
                AuditError e3 = (AuditError) fooBarErrorMap.get("foo").getAuditErrorList().get(0);
                AuditError e4 = (AuditError) fooBarErrorMap.get("foo").getAuditErrorList().get(1);
                
                oneOf(comp).compare(e3, e4);
            }
        });
        
        new AuditMapSorter(fooBarErrorMap).sort(compMap);
        
        context.assertIsSatisfied();
    }
}
