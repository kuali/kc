/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.lookup;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.service.S2SService;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.web.format.Formatter;
import org.kuali.rice.kns.web.format.TimestampAMPMFormatter;
import org.kuali.rice.kns.web.struts.form.LookupForm;
import org.kuali.rice.kns.web.ui.Column;
import org.kuali.rice.kns.web.ui.ResultRow;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * This class implements a custom lookup for S2S Grants.gov Opportunity Lookup
 */
@Transactional
public class S2sOpportunityLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {

    private S2SService s2SService;
    private static final Logger LOG = Logger.getLogger(S2sOpportunityLookupableHelperServiceImpl.class);

    /**
     * 
     * @see org.kuali.core.lookup.KualiLookupableHelperServiceImpl#getSearchResults(java.util.Map) It calls the
     *      S2sService#searchOpportunity service to look up the opportunity
     */
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        LookupUtils.removeHiddenCriteriaFields(getBusinessObjectClass(), fieldValues);
        setBackLocation(fieldValues.get(KNSConstants.BACK_LOCATION));
        setDocFormKey(fieldValues.get(KNSConstants.DOC_FORM_KEY));
        setReferencesToRefresh(fieldValues.get(KNSConstants.REFERENCES_TO_REFRESH));
        GlobalVariables.getMessageList().add(Constants.GRANTS_GOV_LINK);
        List<S2sOpportunity> s2sOpportunity = new ArrayList<S2sOpportunity>();
        if (fieldValues != null
                && (fieldValues.get(Constants.CFDA_NUMBER) != null && !StringUtils.equals(fieldValues.get(Constants.CFDA_NUMBER)
                        .trim(), ""))
                || (fieldValues.get(Constants.OPPORTUNITY_ID) != null && !StringUtils.equals(fieldValues.get(
                        Constants.OPPORTUNITY_ID).trim(), ""))) {
            try {
                s2sOpportunity = s2SService.searchOpportunity(fieldValues.get(Constants.CFDA_NUMBER), fieldValues
                        .get(Constants.OPPORTUNITY_ID), "");
            }
            catch (S2SException e) {
                LOG.error(e.getMessage(), e);
                return new ArrayList<S2sOpportunity>();
            }
            if (s2sOpportunity != null) {
                return s2sOpportunity;
            }
            else {
                if (fieldValues.get(Constants.CFDA_NUMBER) != null
                        && !StringUtils.equals(fieldValues.get(Constants.CFDA_NUMBER).trim(), "")) {
                    GlobalVariables.getErrorMap().putError(Constants.CFDA_NUMBER, KeyConstants.ERROR_IF_CFDANUMBER_IS_INVALID);
                }
                if (fieldValues.get(Constants.OPPORTUNITY_ID) != null
                        && !StringUtils.equals(fieldValues.get(Constants.OPPORTUNITY_ID).trim(), "")) {
                    GlobalVariables.getErrorMap().putError(Constants.OPPORTUNITY_ID,
                            KeyConstants.ERROR_IF_OPPORTUNITY_ID_IS_INVALID);
                }
            }
            return new ArrayList<S2sOpportunity>();
        }
        else {
            GlobalVariables.getErrorMap().putError(Constants.NO_FIELD, KeyConstants.ERROR_IF_CFDANUMBER_AND_OPPORTUNITY_ID_IS_NULL);
            return s2sOpportunity;
        }
    }

    public S2SService getS2SService() {
        return s2SService;
    }

    public void setS2SService(S2SService service) {
        s2SService = service;
    }

    public Collection performLookup(LookupForm lookupForm, Collection resultTable, boolean bounded) {
        Collection displayList;
        displayList = super.performLookup(lookupForm, resultTable, bounded);
        ResultRow row;
        for (Iterator iter = resultTable.iterator(); iter.hasNext();) {
            row = (ResultRow) iter.next();
            List<Column> columns = row.getColumns();

            for (Iterator iterator = columns.iterator(); iterator.hasNext();) {
                Column col = (Column) iterator.next();

                if (StringUtils.equalsIgnoreCase(col.getColumnTitle(), "Instruction Page")
                        || StringUtils.equalsIgnoreCase(col.getColumnTitle(), "Schema URL")) {
                    col.setPropertyURL(col.getPropertyValue());
                }
            }
        }
        return displayList;
    }
    /**
     * As a workaround to format timestamp objects, overriding this method to handle timestamp formating.
     * @see org.kuali.rice.kns.lookup.AbstractLookupableHelperServiceImpl#getParameters(org.kuali.rice.kns.bo.BusinessObject, java.util.Map, java.lang.String, java.util.List)
     */
    protected Properties getParameters(BusinessObject bo, Map fieldConversions, String lookupImpl, List returnKeys) {
        Properties parameters = super.getParameters(bo, fieldConversions, lookupImpl, returnKeys);
        Iterator returnKeysIt = getReturnKeys().iterator();
        while (returnKeysIt.hasNext()) {
            String fieldNm = (String) returnKeysIt.next();
            Object fieldVal = ObjectUtils.getPropertyValue(bo, fieldNm);
            if (fieldVal instanceof Timestamp) {
                Formatter timestampFormatter = new TimestampAMPMFormatter();
                fieldVal = timestampFormatter.format(fieldVal); 
                if (fieldConversions.containsKey(fieldNm)) {
                    fieldNm = (String) fieldConversions.get(fieldNm);
                }
                parameters.setProperty(fieldNm, fieldVal.toString());
            }
        }
        return parameters;
    }
}
