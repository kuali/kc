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

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardSponsorTerm;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

/**
 * Award Hierarchy Sync Helper for Terms.
 */
public class AwardSyncTermHelper extends AwardSyncHelperBase {
    
    @SuppressWarnings("unchecked")
    @Override
    public void applySyncChange(Award award, AwardSyncChange change) 
        throws NoSuchFieldException, IntrospectionException, IllegalAccessException, InvocationTargetException, 
        ClassNotFoundException, NoSuchMethodException, InstantiationException {
        Collection awardTerms = award.getAwardSponsorTerms();
        AwardSponsorTerm term = (AwardSponsorTerm) getAwardSyncUtilityService().findMatchingBo(awardTerms, change.getXmlExport().getKeys());
        if (StringUtils.equals(change.getSyncType(), AwardSyncType.ADD_SYNC.getSyncValue())) {
            if (term != null) {
                this.setValuesOnSyncable(term, change.getXmlExport().getValues(), change);
            } else {
                term = new AwardSponsorTerm();
                setValuesOnSyncable(term, change.getXmlExport().getKeys(), change);
                setValuesOnSyncable(term, change.getXmlExport().getValues(), change);
                award.add(term);
            }
            //sponsorTerm might be null otherwise and this will generate an error during validation
            term.refreshReferenceObject("sponsorTerm");
        } else {
            if (term != null) {
                awardTerms.remove(term);
            }
        }
    }
    
    @Override
    protected String getObjectDesc(PersistableBusinessObject syncableObject, String attrName) {
        return "Term";
    }
    
    @Override
    protected String getDataDesc(PersistableBusinessObject syncableObject, String attrName) {
        AwardSponsorTerm term = (AwardSponsorTerm) syncableObject;
        return term.getSponsorTerm().getSponsorTermType().getDescription() + " : " + term.getSponsorTerm().getDescription();
    }     
}
