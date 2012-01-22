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
package org.kuali.kra.bo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests methods in SponsorTerm.java
 */
public class SponsorTermTest {
    
private static final int SPONSOR_TERM_ATTRIBUTES_COUNT = 4;
    
    private SponsorTerm sponsorTermBo;
    
    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        sponsorTermBo = new SponsorTerm();
    }

    /**
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        sponsorTermBo = null;
    }
    
    /**
     * 
     * This method tests that total attributes of Award Business Object 
     * @throws Exception
     */
    @Test
    public void testCommentTypeBoAttributesCount() throws Exception {              
        Assert.assertEquals(SPONSOR_TERM_ATTRIBUTES_COUNT, sponsorTermBo.getClass().getFields().length);
    }

}
