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
package org.kuali.kra.award.awardhierarchy.sync.helpers;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

/**
 * Award Hierarchy Sync Helper for the Award itself.
 */
public class AwardSyncAwardHelper extends AwardSyncHelperBase {
    
    public static final String SPONSOR_CODE_ATTR = "sponsorCode";
    public static final String STATUS_CODE_ATTR = "statusCode";
    
    @Override
    public void applySyncChange(Award award, AwardSyncChange change) throws NoSuchFieldException {
        if (StringUtils.equals(change.getSyncType(), AwardSyncType.ADD_SYNC.getSyncValue())) {
            if (StringUtils.equalsIgnoreCase(change.getAttrName(), SPONSOR_CODE_ATTR)) {
                award.setSponsorCode((String) change.getXmlExport().getValues().get(change.getAttrName()));
            } else if (StringUtils.equalsIgnoreCase(change.getAttrName(), STATUS_CODE_ATTR)) {
                award.setStatusCode((Integer) change.getXmlExport().getValues().get(change.getAttrName()));
            } else {
                throw new NoSuchFieldException();
            }
        }
    }
    
    @Override
    protected String getObjectDesc(PersistableBusinessObject syncableObject, String attrName) {
        if (StringUtils.equalsIgnoreCase(attrName, SPONSOR_CODE_ATTR)) {
            return "Sponsor";
        } else if (StringUtils.equalsIgnoreCase(attrName, STATUS_CODE_ATTR)) {
            return "Award Status";
        } else {
            return null;
        }
    }
    
    @Override
    protected String getDataDesc(PersistableBusinessObject syncableObject, String attrName) {
        Award award = (Award)syncableObject;
        if (StringUtils.equalsIgnoreCase(attrName, SPONSOR_CODE_ATTR)) {
            return award.getSponsorCode() + " : " + award.getSponsorName();
        } else if (StringUtils.equalsIgnoreCase(attrName, STATUS_CODE_ATTR)) {
            award.refreshReferenceObject("awardStatus");
            return award.getAwardStatus().getDescription();
        } else {
            return null;
        }        
    }    
}
