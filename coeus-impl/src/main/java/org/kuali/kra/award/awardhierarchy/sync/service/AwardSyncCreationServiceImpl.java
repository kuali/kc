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
package org.kuali.kra.award.awardhierarchy.sync.service;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncPendingChangeBean;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport;
import org.kuali.kra.award.awardhierarchy.sync.helpers.AwardSyncHelper;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.XmlObjectSerializerService;

import java.lang.reflect.InvocationTargetException;
import java.util.ListIterator;
import java.util.Map;

/**
 * Class to build award hierarchy descendant sync objects.
 */
public class AwardSyncCreationServiceImpl implements AwardSyncCreationService {

    private AwardSyncHelpersService awardSyncHelpersService;
    private XmlObjectSerializerService xmlSerializerService;
    private BusinessObjectService businessObjectService;
    
    @Override
    public AwardSyncChange createAwardSyncChange(AwardSyncPendingChangeBean pendingChange) 
        throws NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        AwardSyncChange change = 
            getSyncHelper(pendingChange.getObject().getClass().getCanonicalName()).createAwardSyncChange(pendingChange.getSyncType(), 
                    pendingChange.getObject(), pendingChange.getAwardAttr(), pendingChange.getAttrName());
        change.setXml(getXmlSerializerService().toXml(change.getXmlExport()));
        return change;
    }
    
    @Override
    public void addAwardSyncChange(Award award, AwardSyncPendingChangeBean pendingChange) throws Exception {
        AwardSyncChange syncChange = createAwardSyncChange(pendingChange);
        addAwardSyncChange(award, syncChange);
    }
    
    @Override
    public void addAwardSyncChange(Award award, AwardSyncChange syncChange) {
        ListIterator<AwardSyncChange> iter = award.getSyncChanges().listIterator();
        while (iter.hasNext()) {
            AwardSyncChange origChange = iter.next();
            if (changeOnSameObject(origChange, syncChange)) {
                getBusinessObjectService().delete(origChange);
                iter.remove();
            }
        }
        syncChange.setAwardId(award.getAwardId());
        award.getSyncChanges().add(syncChange); 
    }
    
    @Override
    public AwardSyncXmlExport getXmlExport(AwardSyncChange change) {
        return (AwardSyncXmlExport) getXmlSerializerService().fromXml(change.getXml());
    }  
    
    /**
     * Compares change1 and change2 for equality.
     * @param change1
     * @param change2
     * @return
     */
    protected boolean changeOnSameObject(AwardSyncChange change1, AwardSyncChange change2) {
        return StringUtils.equals(change1.getClassName(), change2.getClassName())
                && StringUtils.equals(change1.getAttrName(), change2.getAttrName())
                && StringUtils.equals(change1.getSyncType(), change2.getSyncType())
                && sameObject(getXmlExport(change1), getXmlExport(change2));
    }

    /**
     * The change is on the same object is all the AwardSyncXmlExport keys in the graph
     * that are part of the object key are equal.
     * @param change1
     * @param change2
     * @return
     */
    protected boolean sameObject(AwardSyncXmlExport change1, AwardSyncXmlExport change2) {
        if (StringUtils.equals(change1.getClassName(), change2.getClassName())
                && ObjectUtils.equals(change1.getKeys(), change2.getKeys())) {
            boolean result = true;
            for (Map.Entry<String, Object> entry : change1.getValues().entrySet()) {
                if (entry.getValue() instanceof AwardSyncXmlExport
                        && ((AwardSyncXmlExport) entry.getValue()).isPartOfObjectKey()) {
                    if (!(change2.getValues().get(entry.getKey()) instanceof AwardSyncXmlExport)
                            || !sameObject((AwardSyncXmlExport) entry.getValue(), 
                                (AwardSyncXmlExport) change2.getValues().get(entry.getKey()))) {
                        result = false;
                    }
                }
            }
            return result;
        } else {
            return false;
        }
    }
    
    /**
     * Get the helper for the class name from {@link AwardSyncHelpersService}.
     * @param className
     * @return
     */
    protected AwardSyncHelper getSyncHelper(String className) {
        return getAwardSyncHelpersService().getSyncHelper(className);
    }
    

    protected AwardSyncHelpersService getAwardSyncHelpersService() {
        return awardSyncHelpersService;
    }

    public void setAwardSyncHelpersService(AwardSyncHelpersService awardSyncHelpersService) {
        this.awardSyncHelpersService = awardSyncHelpersService;
    }

    protected XmlObjectSerializerService getXmlSerializerService() {
        return xmlSerializerService;
    }

    public void setXmlSerializerService(XmlObjectSerializerService xmlSerializerService) {
        this.xmlSerializerService = xmlSerializerService;
    }

    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
