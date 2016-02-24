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
package org.kuali.kra.iacuc.onlinereview;

import org.kuali.coeus.sys.framework.keyvalue.ExtendedPersistableBusinessObjectValuesFinder;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewType;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.service.KeyValuesService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IacucProtocolOnlineReviewTypesNotDeterminationValuesFinder extends ExtendedPersistableBusinessObjectValuesFinder {
        

    private static final long serialVersionUID = -4118821657613491616L;
    
    
    //value should match org.kuali.kra.maintenanceIacucProtocolOnlineReviewDeterminationTypeRecommendationMaintainableImpl.DEFAULT_SELECTION
    private static final String DEFAULT_SELECTION = "select"; 
    
    KeyValuesService keyValuesService = null;

    
    
    @Override
    public List<KeyValue> getKeyValues() {        
        Collection<IacucProtocolOnlineReviewDeterminationTypeRecommendation> reviewTypesUsedInDetermination = getKeyValuesService().findAll(IacucProtocolOnlineReviewDeterminationTypeRecommendation.class);
        Collection<IacucProtocolReviewType> allReviewTypes = getKeyValuesService().findAll(IacucProtocolReviewType.class);
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", DEFAULT_SELECTION));
        boolean found = false;
        
        for (IacucProtocolReviewType reviewType : allReviewTypes) {            
            for (IacucProtocolOnlineReviewDeterminationTypeRecommendation reviewTypesUsed : reviewTypesUsedInDetermination) {
                if (reviewType.getReviewTypeCode() != null && reviewTypesUsed.getIacucProtocolReviewTypeCode() != null && 
                    reviewType.getReviewTypeCode().equalsIgnoreCase(reviewTypesUsed.getIacucProtocolReviewTypeCode())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                String description = new String (reviewType.getReviewTypeCode() + " - " + reviewType.getDescription());
                keyValues.add(new ConcreteKeyValue(reviewType.getReviewTypeCode(), description));
            }
            found = false;
        }       
        
        return keyValues;
    }
    

    protected KeyValuesService getKeyValuesService() {
        if (keyValuesService == null) {
            keyValuesService =  KNSServiceLocator.getKeyValuesService();
        }
        return keyValuesService;
    }
    
}
