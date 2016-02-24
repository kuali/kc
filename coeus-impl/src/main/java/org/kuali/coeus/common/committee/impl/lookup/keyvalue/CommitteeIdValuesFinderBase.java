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
package org.kuali.coeus.common.committee.impl.lookup.keyvalue;

import org.apache.commons.collections4.CollectionUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.sys.framework.keyvalue.KeyValueComparator;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceTemplateBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.sort;

/**
 * 
 * This class is to create key/values pair of active committees.
 */
public abstract class CommitteeIdValuesFinderBase extends UifKeyValuesFinderBase {
    

    private static final long serialVersionUID = -2721177236491755020L;
    
    private List<ProtocolCorrespondenceTemplateBase> correspondenceTemplates;
    private BusinessObjectService businessObjectService;
    private static final String COMMITTEE_TYPE_CODE = "committeeTypeCode";
    
    
    public BusinessObjectService getBusinessObjectService() {
        if(null == this.businessObjectService) {
            this.setBusinessObjectService(KcServiceLocator.getService(BusinessObjectService.class));
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
    public List<CommitteeBase> getActiveCommittees() {
        Map<String, String> criteria = new HashMap<String, String>();
        criteria.put(COMMITTEE_TYPE_CODE, getCommitteeTypeCodeHook());
        ArrayList<CommitteeBase> returnCommitteeList = new ArrayList<CommitteeBase>();

        Collection<? extends CommitteeBase> committees = this.getBusinessObjectService().findMatching(getCommitteeBOClassHook(), criteria);
        // sort and iterate through to get only the latest instances
        if (CollectionUtils.isNotEmpty(committees)) {
            List<String> committeeIds = new ArrayList<String>();
            // only the active ones
            Collections.sort((List<CommitteeBase>) committees, Collections.reverseOrder());
            for (CommitteeBase committee : committees) {
                if (!committeeIds.contains(committee.getCommitteeId())) {
                    returnCommitteeList.add(committee); 
                    committeeIds.add(committee.getCommitteeId());
                }
            }
        }
        
        return returnCommitteeList;
    }
    
    protected abstract Class<? extends CommitteeBase> getCommitteeBOClassHook();
    protected abstract String getCommitteeTypeCodeHook(); 

    /**
     * @return the list of &lt;key, value&gt; pairs of committees. The first entry is always &lt;"", "select:"&gt;.
     */
    @Override
    public List<KeyValue> getKeyValues() {

        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        // only the active ones
        Collection<CommitteeBase> committees = this.getActiveCommittees();
        if (CollectionUtils.isNotEmpty(committees)) {
            // get the exclusion list
            List<String> excludedCommitteeIds = getExcludedCommitteeIds();
            for (CommitteeBase committee : committees) {
                if (!excludedCommitteeIds.contains(committee.getCommitteeId())) {
                    keyValues.add(new ConcreteKeyValue(committee.getCommitteeId(), committee.getCommitteeName()));
                }
            }

            sort(keyValues, new KeyValueComparator());
        }

        keyValues.add(0, new ConcreteKeyValue("", "select"));
        
        return keyValues;
    }
    
    private List<String> getExcludedCommitteeIds() {
        List<String> committeeIds = new ArrayList<String>();

        if (CollectionUtils.isNotEmpty(correspondenceTemplates)) {
            for (ProtocolCorrespondenceTemplateBase correspondenceTemplate : correspondenceTemplates) {
                committeeIds.add(correspondenceTemplate.getCommitteeId());
            }
        }

        return committeeIds;
    }

    public List<ProtocolCorrespondenceTemplateBase> getCorrespondenceTemplates() {
        return correspondenceTemplates;
    }

    public void setCorrespondenceTemplates(List<ProtocolCorrespondenceTemplateBase> correspondenceTemplates) {
        this.correspondenceTemplates = correspondenceTemplates;
    }

}
