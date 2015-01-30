/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.bo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.sponsor.term.SponsorTerm;

/**
 * This class tests methods in SponsorTerm.java
 */
public class SponsorTermTest {
    
private static final int SPONSOR_TERM_ATTRIBUTES_COUNT = 6;
    
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
        Assert.assertEquals(SPONSOR_TERM_ATTRIBUTES_COUNT, sponsorTermBo.getClass().getDeclaredFields().length);
    }

}
