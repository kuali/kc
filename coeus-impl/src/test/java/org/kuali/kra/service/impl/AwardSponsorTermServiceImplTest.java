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
package org.kuali.kra.service.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.sponsor.term.SponsorTerm;
import org.kuali.kra.award.service.impl.AwardSponsorTermServiceImpl;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.ArrayList;
import java.util.List;

/**
 * This class tests AwardSponsorTermService methods.
 */
public class AwardSponsorTermServiceImplTest extends AwardSponsorTermServiceImpl {

    private static final String ONE = "1";
    private static final String TWO= "2";

    private static final String TEST_STRING_ONE = "test1";
    private static final String TEST_STRING_TWO = "test2";
    
    AwardSponsorTermServiceImpl awardSponsorTermServiceImpl;
    List<KeyValue> keyValueList;
    List<SponsorTerm> sponsorTerms;
    
    @Before
    public void setUp() throws Exception {
        awardSponsorTermServiceImpl = new AwardSponsorTermServiceImpl();
        keyValueList = new ArrayList<KeyValue>();
        keyValueList.add(new ConcreteKeyValue(ONE, TEST_STRING_ONE));
        keyValueList.add(new ConcreteKeyValue(TWO, TEST_STRING_TWO));
    }

    @After
    public void tearDown() throws Exception {
        awardSponsorTermServiceImpl = null;
        keyValueList = null;
    }

    @Test
    public final void testAddEmptyNewSponsorTerms() {
        sponsorTerms = awardSponsorTermServiceImpl.getEmptyNewSponsorTerms(keyValueList);
        Assert.assertEquals(Integer.parseInt(TWO), sponsorTerms.size());
    }
}
