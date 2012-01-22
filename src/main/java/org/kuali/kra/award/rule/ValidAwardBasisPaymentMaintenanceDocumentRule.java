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
package org.kuali.kra.award.rule;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.AwardBasisOfPayment;
import org.kuali.kra.award.home.AwardType;
import org.kuali.kra.award.home.ValidAwardBasisPayment;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.KraMaintenanceDocumentRuleBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.MessageMap;

/**
 * 
 * This class is check the uniqueness of award type code and basis of payment code.
 */
public class ValidAwardBasisPaymentMaintenanceDocumentRule extends KraMaintenanceDocumentRuleBase {

    /**
     * 
     * @see org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.rice.kns.document.MaintenanceDocument)
     */
    public boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        final ValidAwardBasisPayment validAwardBasisPayment = (ValidAwardBasisPayment) document.getNewMaintainableObject().getDataObject();

        if (!document.getNewMaintainableObject().getMaintenanceAction().equals(KRADConstants.MAINTENANCE_DELETE_ACTION)) {
            return validateUniqueueCodes(validAwardBasisPayment.getAwardType(), validAwardBasisPayment.getBasisOfPayment());
        } else {
            return true;
        }
    }

    /*
     * ensure the uniqueness of award type/basis of payment
     */
    private boolean validateUniqueueCodes(final AwardType awardType, final AwardBasisOfPayment basisOfPayment) {
        
        boolean valid = true;
        if (awardType != null && basisOfPayment != null &&StringUtils.isNotBlank(awardType.getAwardTypeCode().toString()) && StringUtils.isNotBlank(basisOfPayment.getBasisOfPaymentCode())) {
            final Map<String, String> pkMap = new HashMap<String, String>();
            pkMap.put("awardTypeCode", awardType.getAwardTypeCode().toString());
            pkMap.put("basisOfPaymentCode", basisOfPayment.getBasisOfPaymentCode());
            final int matchingCount = KraServiceLocator.getService(BusinessObjectService.class).countMatching(ValidAwardBasisPayment.class, pkMap);
            
            if (matchingCount > 0 ) {
                final MessageMap errorMap = GlobalVariables.getMessageMap();
                errorMap.putError("document.newMaintainableObject.awardTypeCode", KeyConstants.ERROR_AWARD_BASIS_EXIST,
                        new String[] {awardType.getDescription(), basisOfPayment.getDescription() });
                valid = false;
            }
        }
        return valid;
    }

}
