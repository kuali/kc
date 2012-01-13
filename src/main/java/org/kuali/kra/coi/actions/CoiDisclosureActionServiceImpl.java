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
import org.kuali.kra.coi.CoiUserRole;
import org.kuali.kra.coi.CoiDisclosureStatus;
import org.kuali.kra.coi.CoiDispositionStatus;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * 
 * This class implement methods defined in CoiDisclosureActionService.
 * This is mostly for disclosure actions page.
 */
public class CoiDisclosureActionServiceImpl implements CoiDisclosureActionService {

    private BusinessObjectService businessObjectService;

    /**
     * copy disc detailsn from previous master disclosure if it exists.
     * create a disclosure history methods.
     * set current disclosure flag for this approved disclosure, and reset it for previous mater disclosure
     * @see org.kuali.kra.coi.actions.CoiDisclosureActionService#approveDisclosure(org.kuali.kra.coi.CoiDisclosure, java.lang.String)
     */
    public void approveDisclosure(CoiDisclosure coiDisclosure, String coiDisclosureStatusCode) {

        CoiDisclosure masterCoiDisclosure = getMasterDisclosure(coiDisclosure.getCoiDisclosureNumber());
        List<KraPersistableBusinessObjectBase> disclosures = new ArrayList<KraPersistableBusinessObjectBase>();
        coiDisclosure.setDisclosureStatusCode(coiDisclosureStatusCode);
        coiDisclosure.setDisclosureDispositionCode(CoiDispositionStatus.APPROVED);
        disclosures.add(coiDisclosure);
        if (masterCoiDisclosure != null) {
            copyDisclosureDetails(masterCoiDisclosure, coiDisclosure);
            masterCoiDisclosure.setCurrentDisclosure(false);
//            coiDisclosure.setCurrentDisclosure(true);
            disclosures.add(masterCoiDisclosure);

        } 
            coiDisclosure.setCurrentDisclosure(true);
        
        disclosures.add(createDisclosureHistory(coiDisclosure));
        businessObjectService.save(disclosures);
    }
    
    public void addCoiUserRole(CoiDisclosure coiDisclosure, CoiUserRole coiUserRole) {
        coiDisclosure.getCoiUserRoles().add(coiUserRole);
        businessObjectService.save(coiDisclosure);
    }
    
    public void deleteCoiUserRole(CoiDisclosure coiDisclosure, int index) {
        if (index >= 0 && index < coiDisclosure.getCoiUserRoles().size()) {
            coiDisclosure.getCoiUserRoles().remove(index);
            
            businessObjectService.save(coiDisclosure);
        }
    }

    /*
     * retrieve current master disclosure
     */
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
    
    /*
     * copy disclosure details of current master disclosure to the disclosure that is bing approved
     */
    private void copyDisclosureDetails(CoiDisclosure masterCoiDisclosure, CoiDisclosure coiDisclosure) {
        // may also need to add note/attachment to new master disclosure
//        CoiDisclosure copiedDisclosure = (CoiDisclosure) ObjectUtils.deepCopy(masterCoiDisclosure);
        for (CoiDiscDetail coiDiscDetail : masterCoiDisclosure.getCoiDiscDetails()) {
            if (!isDisclosureDetailExist(coiDisclosure, coiDiscDetail)) {
                CoiDiscDetail copiedDiscDetail = (CoiDiscDetail) ObjectUtils.deepCopy(coiDiscDetail);
                copiedDiscDetail.setCopiedCoiDiscDetailId(copiedDiscDetail.getCoiDiscDetailId());
                copiedDiscDetail.setSequenceNumber(coiDisclosure.getSequenceNumber());
                copiedDiscDetail.setCoiDiscDetailId(null);
                if (copiedDiscDetail.getOriginalCoiDisclosureId() == null) {
                    copiedDiscDetail.setOriginalCoiDisclosureId(masterCoiDisclosure.getCoiDisclosureId());
                }
                coiDisclosure.getCoiDiscDetails().add(copiedDiscDetail);
            }
        }
    }

    /*
     * check if disclosure detail is exist in the disclosure being approved
     * if it is, then there is no need to copy over.
     */
    private boolean isDisclosureDetailExist(CoiDisclosure coiDisclosure,CoiDiscDetail coiDiscDetail) {
        boolean isExist = false;
        for (CoiDiscDetail discDetail : coiDisclosure.getCoiDiscDetails()) {
            if (StringUtils.equals(discDetail.getProjectType(), coiDiscDetail.getProjectType()) && StringUtils.equals(discDetail.getProjectIdFk(), coiDiscDetail.getProjectIdFk()) && discDetail.getPersonFinIntDisclosureId().equals(coiDiscDetail.getPersonFinIntDisclosureId())) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }
    
    /*
     * create a disclosure history record for the disclosure being approved
     */
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
