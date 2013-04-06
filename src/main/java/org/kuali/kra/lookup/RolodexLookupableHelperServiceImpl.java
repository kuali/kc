/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.lookup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.Rolodex;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.KRADConstants;

public class RolodexLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl{

    private static final String IS_SPONSOR_ADDRESS = "isSponsorAddress";
    private static final String SPONSOR_NAME = "sponsor.sponsorName";

    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {

        setBackLocation(fieldValues.get(KRADConstants.BACK_LOCATION));
        setDocFormKey(fieldValues.get(KRADConstants.DOC_FORM_KEY));
        setReferencesToRefresh(fieldValues.get(KRADConstants.REFERENCES_TO_REFRESH));
        String isAddressFlagValue = fieldValues.get(IS_SPONSOR_ADDRESS);
        fieldValues.remove(IS_SPONSOR_ADDRESS);
        fieldValues.remove(SPONSOR_NAME);
        List<Rolodex> unboundedResults =
            (List<Rolodex>) super.getSearchResultsUnbounded(fieldValues);
        List<Rolodex> returnResults = new ArrayList<Rolodex>();
        try {
            returnResults = filterForRolodex(unboundedResults);
        } catch (WorkflowException e) {
            e.printStackTrace();
        }
        return returnResults;
    }

    protected List<Rolodex> filterForRolodex(List<Rolodex> collectionByQuery) throws WorkflowException{
        List<Rolodex> filterRolodexList = new ArrayList<Rolodex>();
            for (Rolodex rolodex : collectionByQuery) {
                filterRolodexList.add(rolodex);
            }
      
        return filterRolodexList;
    }
}

