/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.iacuc.onlinereview;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.coeus.sys.framework.keyvalue.ExtendedPersistableBusinessObjectValuesFinder;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewType;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.service.KeyValuesService;


/**
 * Assembles the IACUC Protocol Review Type Determination Values to display in the drop-down menu when
 * performing an online review. 
 */
public class IacucProtocolOnlineReviewDeterminationTypeRecommendationValuesFinder extends ExtendedPersistableBusinessObjectValuesFinder {


    private static final long serialVersionUID = 7071063929463359324L;

    private static final String DEFAULT_SELECTION = "select"; 
    
    KeyValuesService keyValuesService = null;
        
    @Override
    public List<KeyValue> getKeyValues() {        
        Collection<IacucProtocolOnlineReviewDeterminationTypeRecommendation> reviewTypesUsedInDetermination = getKeyValuesService().findAll(IacucProtocolOnlineReviewDeterminationTypeRecommendation.class);
        Collection<IacucProtocolReviewType> allReviewTypes = getKeyValuesService().findAll(IacucProtocolReviewType.class);
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", DEFAULT_SELECTION));
        boolean found = false;
        
        for (IacucProtocolOnlineReviewDeterminationTypeRecommendation reviewTypesUsed : reviewTypesUsedInDetermination) {
            for (IacucProtocolReviewType reviewType : allReviewTypes) {                        
                if (reviewTypesUsed.getIacucProtocolReviewTypeCode() != null && reviewType.getReviewTypeCode() != null && 
                        reviewTypesUsed.getIacucProtocolReviewTypeCode().equalsIgnoreCase(reviewType.getReviewTypeCode())) {
                    found = true;
                    break;
                }
            }
            if (found) {
                keyValues.add(new ConcreteKeyValue(reviewTypesUsed.getIacucProtocolReviewTypeCode(), reviewTypesUsed.getIacucProtocolReviewType().getDescription()));
            }
            found = false;
        }       
        
        return keyValues;
    }
    

    protected KeyValuesService getKeyValuesService() {
        if (keyValuesService == null) {
            keyValuesService =  KcServiceLocator.getService(KeyValuesService.class);
        }
        return keyValuesService;
    }
}
