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
package org.kuali.kra.award.paymentreports.specialapproval.approvedequipment;

import java.io.Serializable;
import java.util.List;

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KualiRuleService;

/**
 * This class supports the Award Form / AwardPaymentReportsAndTermsAction classes for SpecialApproval
 */
public class SpecialApprovalBean implements Serializable {
    private static final long serialVersionUID = -6976882557080351302L;
    
    protected AwardForm form;
    protected transient KualiRuleService ruleService;
    private transient BusinessObjectService businessObjectService;
    
    protected SpecialApprovalBean(AwardForm form) {
        this.form = form;
    }

    /**
     * @param businessObjectService
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * @param ruleService
     */
    public void setRuleService(KualiRuleService ruleService) {
        this.ruleService = ruleService;
    }

    /**
     * @return
     */
    protected Award getAward() {
        return form.getAwardDocument().getAward();
    }

    /**
     * @return
     */
    protected AwardDocument getAwardDocument() {
        return form.getAwardDocument();
    }
    
    /**
     * @return
     */
    protected BusinessObjectService getBusinessObjectService() {
        if(businessObjectService == null) {
            businessObjectService = (BusinessObjectService) KraServiceLocator.getService("businessObjectService"); 
        }
        return businessObjectService;
    }

    /**
     * @return
     */
    protected KualiRuleService getRuleService() {
        if(ruleService == null) {
            ruleService = (KualiRuleService) KraServiceLocator.getService("kualiRuleService"); 
        }
        return ruleService;
    }

    /**
     * @param collection
     * @param deletedIndex
     */
    protected void removeCollectionItem(List<? extends KraPersistableBusinessObjectBase> collection, int deletedIndex) {
        if(deletedIndex >= 0 && deletedIndex < collection.size()) {
            collection.remove(deletedIndex);
        }
    }
}