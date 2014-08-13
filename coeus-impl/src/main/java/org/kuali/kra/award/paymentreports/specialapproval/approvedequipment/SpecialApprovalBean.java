/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;

import java.io.Serializable;
import java.util.List;

/**
 * This class supports the Award Form / AwardPaymentReportsAndTermsAction classes for SpecialApproval
 */
public class SpecialApprovalBean implements Serializable {
    private static final long serialVersionUID = -6976882557080351302L;
    
    protected AwardForm form;

    protected SpecialApprovalBean(AwardForm form) {
        this.form = form;
    }


    protected Award getAward() {
        return form.getAwardDocument().getAward();
    }


    protected AwardDocument getAwardDocument() {
        return form.getAwardDocument();
    }


    /**
     * @param collection
     * @param deletedIndex
     */
    protected void removeCollectionItem(List<? extends KcPersistableBusinessObjectBase> collection, int deletedIndex) {
        if(deletedIndex >= 0 && deletedIndex < collection.size()) {
            collection.remove(deletedIndex);
        }
    }
}
