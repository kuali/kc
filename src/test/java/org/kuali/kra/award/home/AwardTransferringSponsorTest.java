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
package org.kuali.kra.award.home;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for AwardTransferringSponsor.java BO
 * 
 * @author Kuali Coeus development team (kc.dev@kuali.org)
 */
public class AwardTransferringSponsorTest {
    
    private static final int AWARD_TRANSFERRING_SPONSOR_ATTRIBUTES_COUNT = 4;
    
    private AwardTransferringSponsor awardTransferringSponsor;
    private Award award = new Award();
    
    /**
    * @throws Exception
    */
   @Before
   public void setUp() throws Exception {
       awardTransferringSponsor = new AwardTransferringSponsor();
       awardTransferringSponsor.setAward(award);
   }

   /**
    *
    * @throws Exception
    */
   @After
   public void tearDown() throws Exception {
       awardTransferringSponsor = null;
   }
   
   /**
    * 
    * This method tests that total attributes of Award Business Object 
    * @throws Exception
    */
   @Test
   public void testAwardCostShareBoAttributesCount() throws Exception {              
       Assert.assertEquals(AWARD_TRANSFERRING_SPONSOR_ATTRIBUTES_COUNT, 
               awardTransferringSponsor.getClass().getFields().length);
   }

}
