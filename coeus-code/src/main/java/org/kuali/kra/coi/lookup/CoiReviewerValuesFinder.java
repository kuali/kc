/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.coi.lookup;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.CoiReviewer;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CoiReviewerValuesFinder extends UifKeyValuesFinderBase {

    private BusinessObjectService businessObjectService;
    
    
    public BusinessObjectService getBusinessObjectService() {
        if(null == this.businessObjectService) {
            this.setBusinessObjectService(KcServiceLocator.getService(BusinessObjectService.class));
        }
        return this.businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();

        Collection<CoiReviewer> reviewerCodes = getBusinessObjectService().findAll(CoiReviewer.class);
        if (CollectionUtils.isNotEmpty(reviewerCodes)) {
            for (CoiReviewer reviewerCode : reviewerCodes) {
                keyValues.add(new ConcreteKeyValue(reviewerCode.getReviewerCode(), reviewerCode.getDescription()));
            }
        }
        
        return keyValues;
    }

}
