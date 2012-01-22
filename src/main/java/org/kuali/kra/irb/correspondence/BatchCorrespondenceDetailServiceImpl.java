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
package org.kuali.kra.irb.correspondence;

import java.util.List;

import org.kuali.rice.krad.service.BusinessObjectService;

public class BatchCorrespondenceDetailServiceImpl implements BatchCorrespondenceDetailService {

    private static final String REFERENCE_PROTOCOL_CORRESPONDENCE_TYPE = "protocolCorrespondenceType";

    BusinessObjectService businessObjectService;

    /**
     * 
     * @see org.kuali.kra.irb.correspondence.BatchCorrespondenceDetailService#addBatchCorrespondenceDetail(
     * org.kuali.kra.irb.correspondence.BatchCorrespondence, org.kuali.kra.irb.correspondence.BatchCorrespondenceDetail)
     */
    public void addBatchCorrespondenceDetail(BatchCorrespondence batchCorrespondence,
            BatchCorrespondenceDetail newBatchCorrespondenceDetail) {

        newBatchCorrespondenceDetail.setBatchCorrespondenceTypeCode(batchCorrespondence.getBatchCorrespondenceTypeCode());
        newBatchCorrespondenceDetail.refreshReferenceObject(REFERENCE_PROTOCOL_CORRESPONDENCE_TYPE);

        batchCorrespondence.getBatchCorrespondenceDetails().add(newBatchCorrespondenceDetail);
    }

    /**
     * 
     * @see org.kuali.kra.irb.correspondence.BatchCorrespondenceDetailService#saveBatchCorrespondenceDetails(
     * org.kuali.kra.irb.correspondence.BatchCorrespondence, java.util.List)
     */
    public void saveBatchCorrespondenceDetails(BatchCorrespondence batchCorrespondence, List<BatchCorrespondenceDetail> deletedBos) {
        if (!deletedBos.isEmpty()) {
            businessObjectService.delete(deletedBos);
        }
        
        businessObjectService.save(batchCorrespondence);
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
