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
package org.kuali.kra.committee.lookup.keyvalue;

import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplate;
import org.kuali.kra.lookup.keyvalue.KeyLabelPairComparator;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.core.util.KeyLabelPair;

import java.util.Collections;

/**
 * 
 * This class is to create key/values pair of active committees.
 */
public class CommitteeIdValuesFinder extends KeyValuesBase {
    
    private List<ProtocolCorrespondenceTemplate> correspondenceTemplates;
    private BusinessObjectService businessObjectService;
    
    
    public BusinessObjectService getBusinessObjectService() {
        if(null == this.businessObjectService) {
            this.setBusinessObjectService(KraServiceLocator.getService(BusinessObjectService.class));
        }
        return this.businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * This method will return the list of all highest-sequence number committee instances.
     * Will always return non-null (but possibly empty) collection.
     */
    protected List<Committee> getActiveCommittees() {
        ArrayList<Committee> returnCommitteeList = new ArrayList<Committee>();
        
        Collection<Committee> committees = this.getBusinessObjectService().findAll(Committee.class);
        // sort and iterate through to get only the latest instances
        if (CollectionUtils.isNotEmpty(committees)) {
            List<String> committeeIds = new ArrayList<String>();
            // only the active ones
            Collections.sort((List<Committee>) committees, Collections.reverseOrder());
            for (Committee committee : committees) {
                if (!committeeIds.contains(committee.getCommitteeId())) {
                    returnCommitteeList.add(committee); 
                    committeeIds.add(committee.getCommitteeId());
                }
            }
        }
        
        return returnCommitteeList;
    }

    /**
     * @return the list of &lt;key, value&gt; pairs of committees. The first entry is always &lt;"", "select:"&gt;.
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @SuppressWarnings("unchecked")
    public List<KeyLabelPair> getKeyValues() {

        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        // only the active ones
        Collection<Committee> committees = this.getActiveCommittees();
        if (CollectionUtils.isNotEmpty(committees)) {
            // get the exclusion list
            List<String> excludedCommitteeIds = getExcludedCommitteeIds();
            for (Committee committee : committees) {
                if (!excludedCommitteeIds.contains(committee.getCommitteeId())) {
                    keyValues.add(new KeyLabelPair(committee.getCommitteeId(), committee.getCommitteeName()));
                }
            }

            sort(keyValues, new KeyLabelPairComparator());
        }

        keyValues.add(0, new KeyLabelPair("", "select"));
        
        return keyValues;
    }
    
    private List<String> getExcludedCommitteeIds() {
        List<String> committeeIds = new ArrayList<String>();

        if (CollectionUtils.isNotEmpty(correspondenceTemplates)) {
            for (ProtocolCorrespondenceTemplate correspondenceTemplate : correspondenceTemplates) {
                committeeIds.add(correspondenceTemplate.getCommitteeId());
            }
        }

        return committeeIds;
    }

    public List<ProtocolCorrespondenceTemplate> getCorrespondenceTemplates() {
        return correspondenceTemplates;
    }

    public void setCorrespondenceTemplates(List<ProtocolCorrespondenceTemplate> correspondenceTemplates) {
        this.correspondenceTemplates = correspondenceTemplates;
    }

}
