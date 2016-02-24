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
package org.kuali.coeus.sys.framework.controller.interceptor;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;

import java.util.*;

/**
 * Tests the {@link AuditMapSorter} class.
 */
public class AuditMapSorterTest {
    
    private Map<String, AuditCluster> fooBarErrorMap;
    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    
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
                
                allowing(comp).compare(e0, e1);
                allowing(comp).compare(e1, e0);
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

                allowing(comp).compare(e0, e1);
                allowing(comp).compare(e1, e0);
                
                AuditError e3 = (AuditError) fooBarErrorMap.get("foo").getAuditErrorList().get(0);
                AuditError e4 = (AuditError) fooBarErrorMap.get("foo").getAuditErrorList().get(1);

                allowing(comp).compare(e3, e4);
                allowing(comp).compare(e4, e3);
            }
        });
        
        new AuditMapSorter(fooBarErrorMap).sort(compMap);
        
        context.assertIsSatisfied();
    }
}
