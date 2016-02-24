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
package org.kuali.kra.award.paymentreports.specialapproval.approvedequipment;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.kra.award.home.Award;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.ArrayList;
import java.util.List;

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
        item.setAmount(new ScaleTwoDecimal(amount));
        return item;
    }
    
}
