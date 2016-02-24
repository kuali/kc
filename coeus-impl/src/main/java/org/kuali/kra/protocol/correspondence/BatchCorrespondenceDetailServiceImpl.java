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
package org.kuali.kra.protocol.correspondence;

import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.List;

public class BatchCorrespondenceDetailServiceImpl implements BatchCorrespondenceDetailService {

    private static final String REFERENCE_PROTOCOL_CORRESPONDENCE_TYPE = "protocolCorrespondenceType";

    BusinessObjectService businessObjectService;

    /**
     * 
     * @see org.kuali.kra.protocol.correspondence.BatchCorrespondenceDetailService#addBatchCorrespondenceDetail(
     * org.kuali.kra.protocol.correspondence.BatchCorrespondenceBase, org.kuali.kra.protocol.correspondence.BatchCorrespondenceDetailBase)
     */
    public void addBatchCorrespondenceDetail(BatchCorrespondenceBase batchCorrespondence,
            BatchCorrespondenceDetailBase newBatchCorrespondenceDetail) {

        newBatchCorrespondenceDetail.setBatchCorrespondenceTypeCode(batchCorrespondence.getBatchCorrespondenceTypeCode());
        newBatchCorrespondenceDetail.refreshReferenceObject(REFERENCE_PROTOCOL_CORRESPONDENCE_TYPE);

        batchCorrespondence.getBatchCorrespondenceDetails().add(newBatchCorrespondenceDetail);
    }

    /**
     * 
     * @see org.kuali.kra.protocol.correspondence.BatchCorrespondenceDetailService#saveBatchCorrespondenceDetails(
     * org.kuali.kra.protocol.correspondence.BatchCorrespondenceBase, java.util.List)
     */
    public void saveBatchCorrespondenceDetails(BatchCorrespondenceBase batchCorrespondence, List<BatchCorrespondenceDetailBase> deletedBos) {
        if (!deletedBos.isEmpty()) {
            businessObjectService.delete(deletedBos);
        }
        
        businessObjectService.save(batchCorrespondence);
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
