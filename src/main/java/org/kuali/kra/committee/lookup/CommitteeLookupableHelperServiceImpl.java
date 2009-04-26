/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.committee.lookup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;

public class CommitteeLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {

    private static final String MEMBERSHIP_ROLE_CODE = "membershipRoleCode";
    private static final String MEMBERSHIP_NAME = "memberName";
    private static final String PERSON_NAME = "personName";
    private static final String RESEARCH_AREA_CODE = "researchAreaCode";

    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {

        return getUniqueList(super.getSearchResults(setupCritMaps(fieldValues)));
    }

    /*
     * set up search criteria value map.  Especially for collection entity fields.
     */
    private Map<String,String> setupCritMaps(Map<String, String> fieldValues) {

        Map <String,String> baseLookupFields = new HashMap<String,String>();
        for (Entry<String, String> entry : fieldValues.entrySet()) {
            if (entry.getKey().equals(MEMBERSHIP_NAME)) {
                baseLookupFields.put("committeeMemberships.personName",entry.getValue());
            } else if (entry.getKey().equals(MEMBERSHIP_ROLE_CODE)) {
                baseLookupFields.put("committeeMemberships.membershipRoles.membershipRoleCode",entry.getValue());
            } else if (entry.getKey().equals(RESEARCH_AREA_CODE)) {
                baseLookupFields.put("committeeResearchAreas.researchAreaCode",entry.getValue());
            } else {
                baseLookupFields.put(entry.getKey(),entry.getValue());                
            }
        }
        return baseLookupFields;
    }
    
    /**
     * Specifically, for drop down.
     * @see org.kuali.core.lookup.AbstractLookupableHelperServiceImpl#getRows()
     */
    @Override
    public List<Row> getRows() {
        List<Row> rows =  super.getRows();
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (field.getPropertyName().equals(RESEARCH_AREA_CODE)) {
                    super.updateLookupField(field,RESEARCH_AREA_CODE,"org.kuali.kra.bo.ResearchArea");
                } else if (field.getPropertyName().equals(MEMBERSHIP_NAME)) {
                    super.updateLookupField(field,PERSON_NAME,"org.kuali.kra.committee.bo.CommitteeMembership");
                }
            }
        }
        return rows;
    }

    /*
     * remove duplicates from the search results
     */
    private List<? extends BusinessObject> getUniqueList(List<? extends BusinessObject> searchResults) {

        // Maybe should use comparator to remove duplicates ?
        List <Committee> uniqueResults = new ArrayList <Committee>();
        List <String> committeeIds = new ArrayList <String>();
        if (CollectionUtils.isNotEmpty(searchResults)) {
            for (Committee committee : (List <Committee>)searchResults) {
                if (!committeeIds.contains(committee.getCommitteeId())) {
                    committee.getCommitteeChair();
                    uniqueResults.add(committee);
                    committeeIds.add(committee.getCommitteeId());
                }
            }
        }
        return uniqueResults;
    }

    
    protected String getHtmlAction() {
        return "committeeCommittee.do";
    }
    
    protected String getDocumentTypeName() {
        return "CommitteeDocument";
    }
    
    protected String getKeyFieldName() {
        return "committeeId";
    }

}
