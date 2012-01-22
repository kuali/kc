/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.committee.bo.businessLogic;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeResearchArea;
import org.kuali.kra.committee.bo.CommitteeType;
import org.kuali.kra.committee.bo.businessLogic.impl.CommitteeResearchAreaBusinessLogicImpl;
import org.kuali.kra.committee.bo.businessLogic.impl.IrbCommitteeBusinessLogicImpl;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;

public class IrbCommitteeBusinessLogicTest {
    
    private static final String INACTIVE_RESEARCH_AREAS_PREFIX = "document.committeeList[0].committeeResearchAreas.inactive";
    private static final String SEPERATOR = ".";
    
    
    
    /**
     * This method tests the logic for validating that all research areas associated with a committee are active.
     * Specifically it tests 3 different cases: 
     *      1. IRB Committee has no research areas -- should give no error as rule is satisfied trivially.
     *      2. IRB Committee has research areas and all are active -- should give no error
     *      3. IRB Committee has research areas and some are inactive -- should give a single error with the error-property correctly encoding the
     *              indices of the inactive areas.
     *      4. COI Committee does not care about research areas being active or inactive.        
     */
    @Test
    public void validateCommitteeResearchAreas() throws Exception {
        Committee committee = new Committee();
        committee.setCommitteeTypeCode(CommitteeType.IRB_TYPE_CODE);
        
        
        IrbCommitteeBusinessLogicImpl irbCommitteeLogicInstance = new IrbCommitteeBusinessLogicImpl(committee, new CommitteeCollaboratorBusinessLogicFactoryGroup() {
            
            public CommitteeResearchAreaBusinessLogic getCommitteeReserachAreaBusinessLogic(CommitteeResearchArea businessObject) {
                return new CommitteeResearchAreaBusinessLogicImpl(businessObject, this);
            }

            @Override
            public void setCommitteeBusinessLogicFactory(CommitteeCollaboratorBusinessLogicFactory<Committee, CommitteeBusinessLogic> committeeBusinessLogicFactory) {
                // do nothing for this mock
            }

            @Override
            public void setCommitteeResearchAreaBusinessLogicFactory(CommitteeCollaboratorBusinessLogicFactory<CommitteeResearchArea, CommitteeResearchAreaBusinessLogic> committeeReserachAreaBusinessLogicFactory) {
                // do nothing for this mock
                
            }

            @Override
            public CommitteeBusinessLogic getCommitteeBusinessLogicFor(Committee businessObject) {
                // return null for this mock
                return null;
            }
        
        });
        
        
        // check case 1
        Assert.assertTrue(irbCommitteeLogicInstance.validateCommitteeResearchAreas());
        
        // check case 2
        CommitteeResearchArea dummyCRA0 = new CommitteeResearchArea();
        ResearchArea dummyRA0 = new ResearchArea();
        dummyRA0.setActive(true);
        dummyCRA0.setResearchAreas(dummyRA0);
        
        CommitteeResearchArea dummyCRA1 = new CommitteeResearchArea();
        ResearchArea dummyRA1 = new ResearchArea();
        dummyRA1.setActive(true);
        dummyCRA1.setResearchAreas(dummyRA1);
        
        CommitteeResearchArea dummyCRA2 = new CommitteeResearchArea();
        ResearchArea dummyRA2 = new ResearchArea();
        dummyRA2.setActive(true);
        dummyCRA2.setResearchAreas(dummyRA2);
        
        CommitteeResearchArea dummyCRA3 = new CommitteeResearchArea();
        ResearchArea dummyRA3 = new ResearchArea();
        dummyRA3.setActive(true);
        dummyCRA3.setResearchAreas(dummyRA3);
        
        ArrayList<CommitteeResearchArea> cras = new ArrayList<CommitteeResearchArea>();
        cras.add(dummyCRA0);
        cras.add(dummyCRA1);
        cras.add(dummyCRA2);
        cras.add(dummyCRA3);
        
        committee.setCommitteeResearchAreas(cras);
        
        Assert.assertTrue(committee.getCommitteeResearchAreas().get(0).getResearchAreas().isActive());
        Assert.assertTrue(committee.getCommitteeResearchAreas().get(1).getResearchAreas().isActive());
        Assert.assertTrue(committee.getCommitteeResearchAreas().get(2).getResearchAreas().isActive());
        Assert.assertTrue(committee.getCommitteeResearchAreas().get(3).getResearchAreas().isActive());
        
        Assert.assertTrue(irbCommitteeLogicInstance.validateCommitteeResearchAreas());
        
        // check case 3
        Assert.assertTrue(committee.getCommitteeResearchAreas().get(0).getResearchAreas().isActive());
        
        dummyRA1.setActive(false);
        Assert.assertFalse(committee.getCommitteeResearchAreas().get(1).getResearchAreas().isActive());
        
        Assert.assertTrue(committee.getCommitteeResearchAreas().get(2).getResearchAreas().isActive());
        
        dummyRA3.setActive(false);
        Assert.assertFalse(committee.getCommitteeResearchAreas().get(3).getResearchAreas().isActive());
        
        Assert.assertFalse(irbCommitteeLogicInstance.validateCommitteeResearchAreas());
        String errorPropertyKey = INACTIVE_RESEARCH_AREAS_PREFIX + SEPERATOR + "1.3.";
        assertError(errorPropertyKey, KeyConstants.ERROR_COMMITTEE_RESEARCH_AREA_INACTIVE);
    }
    
    
    
    /**
     * Assert an error.  The Error Map should have one error with the given
     * property key and error key.
     * @param propertyKey
     * @param errorKey
     */
    protected void assertError(String propertyKey, String errorKey) {
        List<ErrorMessage> errors = GlobalVariables.getMessageMap().getMessages(propertyKey);
        Assert.assertNotNull(errors);
        Assert.assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        Assert.assertNotNull(message);
        Assert.assertEquals(message.getErrorKey(), errorKey);
    }

}
