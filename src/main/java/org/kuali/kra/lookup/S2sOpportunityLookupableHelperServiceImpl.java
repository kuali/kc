/*
 * Copyright 2005-2010 The Kuali Foundation
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

import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.service.S2SService;
import org.kuali.rice.core.web.format.Formatter;
import org.kuali.rice.core.web.format.TimestampAMPMFormatter;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.LookupForm;
import org.kuali.rice.kns.web.ui.Column;
import org.kuali.rice.kns.web.ui.ResultRow;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * This class implements a custom lookup for S2S Grants.gov Opportunity Lookup
 */
@Transactional
public class S2sOpportunityLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {

    private S2SService s2SService;
    private static final Log LOG = LogFactory.getLog(S2sOpportunityLookupableHelperServiceImpl.class);

    /**
     * 
     * @see org.kuali.core.lookup.KualiLookupableHelperServiceImpl#getSearchResults(java.util.Map) It calls the
     *      S2sService#searchOpportunity service to look up the opportunity
     */
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        LookupUtils.removeHiddenCriteriaFields(getBusinessObjectClass(), fieldValues);
        setBackLocation(fieldValues.get(KRADConstants.BACK_LOCATION));
        setDocFormKey(fieldValues.get(KRADConstants.DOC_FORM_KEY));
        setReferencesToRefresh(fieldValues.get(KRADConstants.REFERENCES_TO_REFRESH));
        KNSGlobalVariables.getMessageList().add(Constants.GRANTS_GOV_LINK);
        List<S2sOpportunity> s2sOpportunity = new ArrayList<S2sOpportunity>();
        if (fieldValues != null
                && (fieldValues.get(Constants.CFDA_NUMBER) != null && !StringUtils.equals(fieldValues.get(Constants.CFDA_NUMBER)
                        .trim(), ""))
                || (fieldValues.get(Constants.OPPORTUNITY_ID) != null && !StringUtils.equals(fieldValues.get(
                        Constants.OPPORTUNITY_ID).trim(), ""))) {
            try {
                s2sOpportunity = s2SService.searchOpportunity(fieldValues.get(Constants.CFDA_NUMBER), fieldValues
                        .get(Constants.OPPORTUNITY_ID), "");
            }catch (S2SException e) {
                LOG.error(e.getMessage(), e);
                GlobalVariables.getMessageMap().putError(Constants.NO_FIELD, e.getErrorKey(),e.getMessage());
                return new ArrayList<S2sOpportunity>();
            }
            if (s2sOpportunity != null && s2sOpportunity.size()!=0) {
                return s2sOpportunity;
            }else if(fieldValues.get(Constants.CFDA_NUMBER) != null && !StringUtils.equals(fieldValues.get(Constants.CFDA_NUMBER)
                        .trim(), "") && fieldValues.get(Constants.OPPORTUNITY_ID) != null && !StringUtils.equals(fieldValues.get(
                        Constants.OPPORTUNITY_ID).trim(), "")){
                try{
                s2sOpportunity = s2SService.searchOpportunity(fieldValues.get(Constants.CFDA_NUMBER), "", "");
                }catch (S2SException e) {
                    LOG.error(e.getMessage(), e);
                    GlobalVariables.getMessageMap().putError(Constants.NO_FIELD, e.getErrorKey(),e.getMessage());
                    return new ArrayList<S2sOpportunity>();
                }
                if (s2sOpportunity != null) {
                    return s2sOpportunity;


                }
                else{
                    if (fieldValues.get(Constants.CFDA_NUMBER) != null
                            && !StringUtils.equals(fieldValues.get(Constants.CFDA_NUMBER).trim(), "")) {
                        GlobalVariables.getMessageMap().putError(Constants.CFDA_NUMBER, KeyConstants.ERROR_IF_CFDANUMBER_IS_INVALID);
                    }
                    if (fieldValues.get(Constants.OPPORTUNITY_ID) != null
                            && !StringUtils.equals(fieldValues.get(Constants.OPPORTUNITY_ID).trim(), "")) {
                        GlobalVariables.getMessageMap().putError(Constants.OPPORTUNITY_ID,
                                KeyConstants.ERROR_IF_OPPORTUNITY_ID_IS_INVALID);
                    }
                }
                return new ArrayList<S2sOpportunity>();
                }
             return new ArrayList<S2sOpportunity>();
        }
        else {
            GlobalVariables.getMessageMap().putError(Constants.NO_FIELD, KeyConstants.ERROR_IF_CFDANUMBER_AND_OPPORTUNITY_ID_IS_NULL);
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
            if(!lookupForm.getBackLocation().contains("proposalDevelopmentGrantsGov")){
            String cfdaNumber=columns.get(0).getPropertyValue();
            String closingDate=columns.get(1).getPropertyValue();
            String competetionId=columns.get(2).getPropertyValue();
            String instructionUrl=columns.get(3).getPropertyValue();
            String openingDate =columns.get(4).getPropertyValue();
            String oppurtunityId=columns.get(5).getPropertyValue();
            String oppurtunityTitle=columns.get(6).getPropertyValue();
            String schemaUrl=columns.get(7).getPropertyValue();
            
            String createProposalUrl=null;
            try {
                    String encodedUrl = URIUtil
                            .encodeQuery(lookupForm.getBackLocation()
                                    + "?channelTitle=CreateProposal&channelUrl=proposalDevelopmentProposal.do?methodToCall=docHandler&command=initiate&docTypeName=ProposalDevelopmentDocument"
                                    + "&createProposalFromGrantsGov=true"
                                    + "&document.developmentProposalList[0].s2sOpportunity.cfdaNumber=" + cfdaNumber
                                    + "&document.developmentProposalList[0].s2sOpportunity.opportunityId=" + oppurtunityId
                                    + "&document.developmentProposalList[0].s2sOpportunity.opportunityTitle=" + oppurtunityTitle
                                    + "&document.developmentProposalList[0].s2sOpportunity.closingDate=" + closingDate
                                    + "&document.developmentProposalList[0].s2sOpportunity.openingDate=" + openingDate
                                    + "&document.developmentProposalList[0].s2sOpportunity.instructionUrl=" + instructionUrl
                                    + "&document.developmentProposalList[0].s2sOpportunity.competetionId=" + competetionId
                                    + "&document.developmentProposalList[0].s2sOpportunity.schemaUrl=" + schemaUrl);
                    createProposalUrl = "<a href=" + encodedUrl + ">Create Proposal</a>";
                    row.setReturnUrl(createProposalUrl);
                }
                catch (URIException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
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
     * @see org.kuali.rice.kns.lookup.AbstractLookupableHelperServiceImpl#getParameters(org.kuali.rice.krad.bo.BusinessObject, java.util.Map, java.lang.String, java.util.List)
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