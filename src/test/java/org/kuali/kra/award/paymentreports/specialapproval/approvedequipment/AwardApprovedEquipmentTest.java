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
package org.kuali.kra.award.paymentreports.specialapproval.approvedequipment;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.core.api.util.type.KualiDecimal;

/**
 * This class tests the AwardApprovedEquipment BO
 */
public class AwardApprovedEquipmentTest {

    private static final double DELTA = 0.001;
    
    @Test
    public void testGettingTotal() {
        final double AMOUNT1 = 100.00;
        final double AMOUNT2 = 200.00;
        final double AMOUNT3 = 300.00;
        Award award = new Award();
        List<AwardApprovedEquipment> items = new ArrayList<AwardApprovedEquipment>();
        items.add(createApprovedEquipmentItem(AMOUNT1));
        items.add(createApprovedEquipmentItem(AMOUNT2));
        items.add(createApprovedEquipmentItem(AMOUNT3));
        award.setApprovedEquipmentItems(items);
        Assert.assertEquals(AMOUNT1 + AMOUNT2 + AMOUNT3, award.getTotalApprovedEquipmentAmount().doubleValue(), DELTA);
    }
    
    private AwardApprovedEquipment createApprovedEquipmentItem(double amount) {
        AwardApprovedEquipment item = new AwardApprovedEquipment();
        item.setAmount(new KualiDecimal(amount));
        return item;
    }
    
}
