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
package org.kuali.kra.award.bo;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.paymentreports.specialapproval.approvedequipment.AwardApprovedEquipment;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * 
 * This class tests methods in Award.java class
 */
public class AwardTest { 
    private static final double DELTA = 0.001;

    private static final int AWARD_ATTRIBUTES_COUNT = 47;
    
    private Award awardBo;
    
    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        awardBo = new Award();
    }

    /**
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        awardBo = null;
    }
    
    /**
     * 
     * This method tests that total attributes of Award Business Object 
     * @throws Exception
     */
    @Test
    public void testAwardBoAttributesCount() throws Exception {              
        Assert.assertEquals(AWARD_ATTRIBUTES_COUNT, awardBo.toStringMapper().size());
    }
    
    @Test
    public void testCalculatingValuableItemTotal() {
        final double AMOUNT1 = 100.00;
        final double AMOUNT2 = 200.00;
        final double AMOUNT3 = 300.00;
        Award award = new Award();
        List<AwardApprovedEquipment> items = new ArrayList<AwardApprovedEquipment>();
        items.add(createValuableItem(AMOUNT1));
        items.add(createValuableItem(AMOUNT2));
        items.add(createValuableItem(AMOUNT3));
        award.setApprovedEquipmentItems(items);
        Assert.assertEquals(AMOUNT1 + AMOUNT2 + AMOUNT3, award.getTotalAmount(items).doubleValue(), DELTA);
    }
    
    private AwardApprovedEquipment createValuableItem(double amount) {
        AwardApprovedEquipment item = new AwardApprovedEquipment();
        item.setAmount(new KualiDecimal(amount));
        return item;
    }
}
