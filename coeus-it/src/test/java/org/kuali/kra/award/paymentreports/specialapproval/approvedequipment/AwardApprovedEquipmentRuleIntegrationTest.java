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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.home.Award;
import org.kuali.coeus.sys.framework.validation.SoftError;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * This class tests AwardApprovedEquipmentRuleImpl behavior
 */
public class AwardApprovedEquipmentRuleIntegrationTest extends KcIntegrationTestBase {
    private static final double ONE_PENNY = 0.01;
    private static final double FEDERAL_MIN_AMOUNT = 100.00;
    private static final double INSTITUTE_MIN_AMOUNT = 50.00;
    private static final String MIN_AMOUNT_TEXT = "50.00";
    private static final String WIDGET = "Widget";
    private static final String MODEL = "Model 1001";
    private static final String VENDOR = "WidgetVendor";
    private static final String ERROR_KEY = AwardApprovedEquipmentRuleImpl.APPROVED_EQUIPMENT_ITEMS_LIST_ERROR_KEY;
    
    private AwardApprovedEquipmentRuleImpl approvedEquipmentRule;
    private Award award;
    private MinimumCapitalizationInfo minimumCapitalizationInfo;
    
    @Before
    public void setUp() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        this.approvedEquipmentRule = new AwardApprovedEquipmentRuleImpl();
        String requirement = EquipmentCapitalizationMinimumLoader.INSTITUTION_REQUIREMENT;
        minimumCapitalizationInfo = new MinimumCapitalizationInfo(requirement, INSTITUTE_MIN_AMOUNT, FEDERAL_MIN_AMOUNT, INSTITUTE_MIN_AMOUNT);
        award = new Award();
        award.setAwardId(1L);
        award.setAwardNumber("X1000");
        award.setSequenceNumber(1);
        KNSGlobalVariables.setKualiForm(new AwardForm());
    }
    
    @After
    public void tearDown() throws Exception {
        minimumCapitalizationInfo = null;
        award = null;
        approvedEquipmentRule = null;
        GlobalVariables.setUserSession(null);
    }
    
    @Test
    public void testValidatingAmount() throws Exception {
        AwardApprovedEquipment equipmentItem = createEquipmentItem(VENDOR, MODEL, WIDGET, -1.00);
        approvedEquipmentRule.isAmountValid(ERROR_KEY, equipmentItem, minimumCapitalizationInfo);
        Assert.assertEquals(1, getSoftErrors().size());
        
        equipmentItem.setAmount(0.00);
        approvedEquipmentRule.isAmountValid(ERROR_KEY, equipmentItem, minimumCapitalizationInfo);
        Assert.assertEquals(1, getSoftErrors().size());
        
        equipmentItem.setAmount(INSTITUTE_MIN_AMOUNT - ONE_PENNY);
        approvedEquipmentRule.isAmountValid(ERROR_KEY, equipmentItem, minimumCapitalizationInfo);
        List<SoftError> errors = getSoftErrors();
        Assert.assertEquals(1, errors.size());
        Assert.assertEquals(MIN_AMOUNT_TEXT, errors.get(0).getErrorParms()[0]);
        
        equipmentItem.setAmount(INSTITUTE_MIN_AMOUNT);
        approvedEquipmentRule.isAmountValid(ERROR_KEY, equipmentItem, minimumCapitalizationInfo);
        Assert.assertEquals(1, getSoftErrors().size());
        
        equipmentItem.setAmount(FEDERAL_MIN_AMOUNT);
        approvedEquipmentRule.isAmountValid(ERROR_KEY, equipmentItem, minimumCapitalizationInfo);
        Assert.assertEquals(0, getSoftErrors().size());
    }

    private List<SoftError> getSoftErrors() {
        Map<String, Collection<SoftError>> softMessageMap = ((AwardForm) KNSGlobalVariables.getKualiForm()).getSoftErrors();
        Collection<SoftError> errors = softMessageMap != null ? softMessageMap.get(ERROR_KEY) : null;
        return errors != null ? new ArrayList<SoftError>(errors) : new ArrayList<SoftError>();
    }
    
    private AwardApprovedEquipment createEquipmentItem(String vendor, String model, 
                                                        String item, double value) {
        return new AwardApprovedEquipment(vendor, model, item, value);
    }
    
}
