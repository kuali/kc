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
package org.kuali.kra.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.bo.AbstractSpecialReview;
import org.kuali.kra.bo.AbstractSpecialReviewExemption;
import org.kuali.kra.document.SpecialReviewHandler;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rule.event.AddSpecialReviewEvent;
import org.kuali.kra.service.impl.SpecialReviewServiceImpl;
import org.kuali.kra.web.struts.form.SpecialReviewFormBase;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KualiRuleService;

/**
 * JUnit test class to test the SpecialReviewService methods
 */
@RunWith(JMock.class)
public class SpecialReviewServiceTest {

    private Mockery context = new JUnit4Mockery();
    private SpecialReviewServiceImpl srs = new SpecialReviewServiceImpl();
    /**
     * Test method for {@link org.kuali.kra.service.impl.SpecialReviewServiceImpl#addSpecialReview(org.kuali.kra.document.SpecialReviewHandler, org.kuali.kra.web.struts.form.SpecialReviewFormBase)}.
     */
    @Test
    public void testAddSpecialReview() {
        final SpecialReview sr = new SpecialReview();
        final SpecialReviewFormBase srf = getMockSpecialReviewForm();
        final SpecialReviewHandler srh = getMockSpecialReviewHandler();
        final List srList = new ArrayList();
        srList.add(sr);
        final KualiRuleService krs = context.mock(KualiRuleService.class);
        final AddSpecialReviewEvent addSpecialReviewEvent =new AddSpecialReviewEvent(Constants.EMPTY_STRING, 
                null, sr); 
        context.checking(new Expectations() {{
            one(srf).getNewSpecialReview();will(returnValue(sr));
            one(srf).getNewExemptionTypeCodes();will(returnValue(null));
            one(srf).getResearchDocument();will(returnValue(null));
            one(krs).applyRules(addSpecialReviewEvent); will(returnValue(true));
            one(srh).addSpecialReview(sr);
        }});
        srs.setKualiRuleService(krs);
        srs.addSpecialReview(srh, srf);
    }

    private SpecialReviewHandler getMockSpecialReviewHandler() {
        return context.mock(SpecialReviewHandler.class);
    }

    private SpecialReviewFormBase getMockSpecialReviewForm() {
        return context.mock(SpecialReviewFormBase.class);
    }

    /**
     * Test method for {@link org.kuali.kra.service.impl.SpecialReviewServiceImpl#deleteSpecialReview(org.kuali.kra.document.SpecialReviewHandler, int)}.
     */
    @Test
    public void testDeleteSpecialReview() {
        final SpecialReview sr = new SpecialReview();
        final SpecialReviewHandler srh = getMockSpecialReviewHandler();
        final List srList = new ArrayList();
        srList.add(sr);
        context.checking(new Expectations() {{
            one(srh).getSpecialReviews();will(returnValue(srList));
        }});
        srs.deleteSpecialReview(srh, 0);
        assertEquals(srList.size(), 0);
    }

    /**
     * Test method for {@link org.kuali.kra.service.impl.SpecialReviewServiceImpl#processBeforeSaveSpecialReview(org.kuali.kra.document.SpecialReviewHandler)}.
     */
    @Test
    public void testProcessBeforeSaveSpecialReview() {
        final SpecialReview sr = new SpecialReview();
        List specialReviewExemptions = new ArrayList();
        
        SpecialReviewExemption ex1 = sr.newSpecialReviewExemption("1");
        specialReviewExemptions.add(ex1);
        SpecialReviewExemption ex2 = sr.newSpecialReviewExemption("2");
        specialReviewExemptions.add(ex2);
        sr.setSpecialReviewExemptions(specialReviewExemptions);
        
        final SpecialReviewFormBase srf = getMockSpecialReviewForm();
        final SpecialReviewHandler srh = getMockSpecialReviewHandler();
        final List srList = new ArrayList();
        srList.add(sr);
        context.checking(new Expectations() {{
            allowing(srh).getSpecialReviews();will(returnValue(srList));
        }});
        sr.setExemptionTypeCodes(new String[]{"1","2","3"});
        srs.processBeforeSaveSpecialReview(srh);
        assertEquals(sr.getSpecialReviewExemptions().size(), 3);
        
        sr.setExemptionTypeCodes(new String[]{"1"});
        srs.processBeforeSaveSpecialReview(srh);
        assertEquals(sr.getSpecialReviewExemptions().size(), 1);
        
        
    }
    
    /**
     * If special review has only two exemptions, but four are found
     * in the database, the two unwanted exemptions must be deleted.
     */
    @Test
    public void testDeleteExemptions() {
        SpecialReview sr = new SpecialReview();
        
        String newExemptionTypeCodes[] = { "2", "4" };
        sr.setNewExemptionTypeCodes(newExemptionTypeCodes);
       
        final SpecialReviewHandler srh = new SpecialReviewMockHandler();
        srh.addSpecialReview(sr);
        
        final Map<String, Long> fieldValues = new HashMap<String, Long>();
        fieldValues.put("specialReviewId", sr.getSpecialReviewId());
        
        final Collection<AbstractSpecialReviewExemption> returnList = new ArrayList<AbstractSpecialReviewExemption>();
        final SpecialReviewExemption e1 = new SpecialReviewExemption();
        e1.setExemptionTypeCode("1");
        returnList.add(e1);
        final SpecialReviewExemption e2 = new SpecialReviewExemption();
        e2.setExemptionTypeCode("2");
        returnList.add(e2);
        final SpecialReviewExemption e3 = new SpecialReviewExemption();
        e3.setExemptionTypeCode("3");
        returnList.add(e3);
        final SpecialReviewExemption e4 = new SpecialReviewExemption();
        e4.setExemptionTypeCode("4");
        returnList.add(e4);
        
        final BusinessObjectService bos = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(bos).findMatching(SpecialReviewExemption.class, fieldValues); 
            will(returnValue(returnList));
            one(bos).delete(e1);
            one(bos).delete(e3);
        }});
        srs.setBusinessObjectService(bos);
        
        srs.deleteExemptions(srh, "specialReviewId", SpecialReviewExemption.class);
    }

    class SpecialReview extends AbstractSpecialReview<SpecialReviewExemption>{
        @Override
        public SpecialReviewExemption newSpecialReviewExemption(String exemptionTypeCode) {
            SpecialReviewExemption specialReviewExemption = new SpecialReviewExemption();
            specialReviewExemption.setExemptionTypeCode(exemptionTypeCode);
            return specialReviewExemption;
        }

        @Override
        public Long getSpecialReviewId() {
            return 1L;
        }
    }
    class SpecialReviewExemption extends AbstractSpecialReviewExemption{
        
    }
    class SpecialReviewMockHandler implements SpecialReviewHandler<SpecialReview>{
        List<SpecialReview> l = new ArrayList<SpecialReview>();
        public void addSpecialReview(SpecialReview specialReview) {
            l.add(specialReview);
        }

        public SpecialReview getSpecialReview(int index) {
            return l.get(index);
        }

        public List<SpecialReview> getSpecialReviews() {
            return l;
        }
        
    }
}
