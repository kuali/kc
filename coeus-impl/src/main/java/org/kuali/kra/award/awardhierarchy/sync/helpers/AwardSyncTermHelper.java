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
package org.kuali.kra.award.awardhierarchy.sync.helpers;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardSponsorTerm;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

/**
 * Award Hierarchy Sync Helper for Terms.
 */
public class AwardSyncTermHelper extends AwardSyncHelperBase {
    
    @SuppressWarnings("unchecked")
    @Override
    public void applySyncChange(Award award, AwardSyncChange change) 
        throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, 
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
