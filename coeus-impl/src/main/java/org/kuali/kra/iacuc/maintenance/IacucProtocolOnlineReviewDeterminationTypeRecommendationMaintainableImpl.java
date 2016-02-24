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
package org.kuali.kra.iacuc.maintenance;


import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReviewDeterminationTypeRecommendation;
import org.kuali.kra.maintenance.KraMaintainableImpl;


public class IacucProtocolOnlineReviewDeterminationTypeRecommendationMaintainableImpl extends KraMaintainableImpl {

    private static final long serialVersionUID = 6613313050279041286L;
    
    //value should match org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReviewTypesNotDeterminationValuesFinder.DEFAULT_SELECTION
    private static final String DEFAULT_SELECTION = "select";
      
    @Override
    public void prepareForSave() {
        IacucProtocolOnlineReviewDeterminationTypeRecommendation reviewTypeDeterBO = (IacucProtocolOnlineReviewDeterminationTypeRecommendation) getBusinessObject();
        
        if (reviewTypeDeterBO.getNewProtocolReviewTypeCode() != null && !reviewTypeDeterBO.getNewProtocolReviewTypeCode().equalsIgnoreCase(DEFAULT_SELECTION)) {
            reviewTypeDeterBO.setIacucProtocolReviewTypeCode(reviewTypeDeterBO.getNewProtocolReviewTypeCode());
        }

        super.prepareForSave();
    }
}
