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
