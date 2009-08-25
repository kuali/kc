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
package org.kuali.kra.award.awardhierarchy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.service.BusinessObjectServiceAdapter;
import org.kuali.rice.kns.bo.PersistableBusinessObject;

public class AwardHierarchyServiceImplTest {
    AwardHierarchyServiceImpl service;
    
    private AwardHierarchyTestHelper helper;
    
    @Before
    public void setUp() {
        service = new AwardHierarchyServiceImpl();
        service.setBusinessObjectService(new MockBusinessObjectService());
        helper = new AwardHierarchyTestHelper(service);
    }
    
    @After
    public void tearDown() {
        helper = null;
        service = null;        
    }

    @Test
    public void testCreatingRootNode() {
        helper.testCreatingRootNode();
    }
    
    @Test
    public void testCreatingHierarchy() {
        helper.testCreatingHierarchy();
    }
    
    @Test
    public void testCreatingRootNode_NullAwardNumber() {
        helper.testCreatingRootNode_NullAwardNumber();
    }
    
    private class MockBusinessObjectService extends BusinessObjectServiceAdapter {
        Map<String, AwardHierarchy> awardHierarchyMap = new TreeMap<String, AwardHierarchy>();

        @SuppressWarnings("unchecked")
        @Override
        public PersistableBusinessObject findByPrimaryKey(Class klass, Map identifiers) {
            String awardNumber = (String) identifiers.get("awardNumber");
            return awardNumber != null ? awardHierarchyMap.get(awardNumber) : null;
        }

        @Override
        public void save(PersistableBusinessObject bo) {
            AwardHierarchy awardHierarchy = (AwardHierarchy) bo;
            awardHierarchyMap.put(awardHierarchy.getAwardNumber(), awardHierarchy);
        }

        /**
         * @see org.kuali.kra.service.BusinessObjectServiceAdapter#findMatchingOrderBy(java.lang.Class, java.util.Map, java.lang.String, boolean)
         */
        @SuppressWarnings("unchecked")
        @Override
        public Collection findMatchingOrderBy(Class klass, Map fieldValues, String sortField, boolean sortAscending) {
            String parentAwardNumber = (String) fieldValues.get("parentAwardNumber");
            Map<String, AwardHierarchy> matching = new TreeMap<String, AwardHierarchy>();
            for(AwardHierarchy node: awardHierarchyMap.values()) {
                if(node.getParentAwardNumber().equals(parentAwardNumber)) {
                    matching.put(node.getAwardNumber(), node);
                }
            }
            List<AwardHierarchy> list = new ArrayList<AwardHierarchy>();
            for(String awardNumber: matching.keySet()) {
                list.add(matching.get(awardNumber));
            }
            return list;
        }
    }
}