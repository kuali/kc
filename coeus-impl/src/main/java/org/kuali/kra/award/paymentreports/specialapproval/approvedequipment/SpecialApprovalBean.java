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
