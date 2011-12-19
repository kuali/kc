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
package org.kuali.kra.coi.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.coi.CoiDiscDetail;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureHistory;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ObjectUtils;

public class CoiDisclosureActionServiceImpl implements CoiDisclosureActionService {

    private BusinessObjectService businessObjectService;

    public void approveDisclosure(CoiDisclosure coiDisclosure, String coiDisclosureStatusCode) {

        CoiDisclosure masterCoiDisclosure = getMasterDisclosure(coiDisclosure.getCoiDisclosureNumber());
        List<KraPersistableBusinessObjectBase> disclosures = new ArrayList<KraPersistableBusinessObjectBase>();
        coiDisclosure.setDisclosureStatusCode(coiDisclosureStatusCode);
        disclosures.add(coiDisclosure);
        if (masterCoiDisclosure != null) {
            copyDisclosureDetails(masterCoiDisclosure, coiDisclosure);
            masterCoiDisclosure.setCurrentDisclosure(false);
            coiDisclosure.setCurrentDisclosure(true);
            disclosures.add(masterCoiDisclosure);

        } else {
            coiDisclosure.setCurrentDisclosure(true);
        }
        disclosures.add(createDisclosureHistory(coiDisclosure));
        businessObjectService.save(disclosures);
    }
    
    private CoiDisclosure getMasterDisclosure(String coiDisclosureNumber) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("coiDisclosureNumber", coiDisclosureNumber);
        fieldValues.put("currentDisclosure", "Y");

        List<CoiDisclosure> disclosures = (List<CoiDisclosure>)businessObjectService.findMatching(CoiDisclosure.class, fieldValues);
        if (CollectionUtils.isNotEmpty(disclosures)) {
            return disclosures.get(0);
        } else {
            return null;
        }
    }
    
    private void copyDisclosureDetails(CoiDisclosure masterCoiDisclosure, CoiDisclosure coiDisclosure) {
        // may also need to add note/attachment to new master disclosure
        CoiDisclosure copiedDisclosure = (CoiDisclosure) ObjectUtils.deepCopy(masterCoiDisclosure);
        for (CoiDiscDetail coiDiscDetail : copiedDisclosure.getCoiDiscDetails()) {
            if (!isDisclosureDetailExist(coiDisclosure, coiDiscDetail)) {
                coiDiscDetail.setCopiedCoiDiscDetailId(coiDiscDetail.getCoiDiscDetailId());
                coiDiscDetail.setSequenceNumber(coiDisclosure.getSequenceNumber());
                coiDiscDetail.setCoiDiscDetailId(null);
                coiDisclosure.getCoiDiscDetails().add(coiDiscDetail);
            }
        }
    }

    private boolean isDisclosureDetailExist(CoiDisclosure coiDisclosure,CoiDiscDetail coiDiscDetail) {
        boolean isExist = false;
        for (CoiDiscDetail discDetail : coiDisclosure.getCoiDiscDetails()) {
            if (StringUtils.endsWith(discDetail.getProjectType(), coiDiscDetail.getProjectType()) && StringUtils.endsWith(discDetail.getProjectIdFk(), coiDiscDetail.getProjectIdFk())) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }
    
    private CoiDisclosureHistory createDisclosureHistory(CoiDisclosure coiDisclosure) {
        CoiDisclosureHistory coiDisclosureHistory = new CoiDisclosureHistory();
        coiDisclosureHistory.setCoiDisclosureId(coiDisclosure.getCoiDisclosureId());
        coiDisclosureHistory.setCoiDisclosureNumber(coiDisclosure.getCoiDisclosureNumber());
        coiDisclosureHistory.setSequenceNumber(coiDisclosure.getSequenceNumber());
        coiDisclosureHistory.setDisclosureDispositionStatus(coiDisclosure.getDisclosureDispositionCode());
        coiDisclosureHistory.setDisclosureStatus(coiDisclosure.getDisclosureStatusCode());
        return coiDisclosureHistory;

    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    

}
