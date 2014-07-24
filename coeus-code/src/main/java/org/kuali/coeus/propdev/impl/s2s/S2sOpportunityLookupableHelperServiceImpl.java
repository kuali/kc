/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.s2s;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.propdev.impl.s2s.connect.S2sCommunicationException;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.lookup.KcKualiLookupableHelperServiceImpl;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.web.format.Formatter;
import org.kuali.rice.core.web.format.TimestampAMPMFormatter;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kns.document.authorization.BusinessObjectRestrictions;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.LookupForm;
import org.kuali.rice.kns.web.ui.Column;
import org.kuali.rice.kns.web.ui.ResultRow;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;
import org.kuali.rice.krad.util.UrlFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

/**
 * 
 * This class implements a custom lookup for S2S Grants.gov Opportunity Lookup
 */
@Transactional
@Component("s2sOpportunityLookupableHelperService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S2sOpportunityLookupableHelperServiceImpl extends KcKualiLookupableHelperServiceImpl {

    private static final Log LOG = LogFactory.getLog(S2sOpportunityLookupableHelperServiceImpl.class);

    @Autowired
    @Qualifier("s2sSubmissionService")
    private S2sSubmissionService s2sSubmissionService;

    @Autowired
    @Qualifier("dateTimeService")
    private DateTimeService dateTimeService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        LookupUtils.removeHiddenCriteriaFields(getBusinessObjectClass(), fieldValues);
        setBackLocation(fieldValues.get(KRADConstants.BACK_LOCATION));
        setDocFormKey(fieldValues.get(KRADConstants.DOC_FORM_KEY));
        setReferencesToRefresh(fieldValues.get(KRADConstants.REFERENCES_TO_REFRESH));
        KNSGlobalVariables.getMessageList().add(Constants.GRANTS_GOV_LINK);
        List<S2sOpportunity> s2sOpportunity = new ArrayList<S2sOpportunity>();
        String providerCode = fieldValues.get(Constants.PROVIDER_CODE);
        String cfdaNumber = fieldValues.get(Constants.CFDA_NUMBER);
        String opportunityId = fieldValues.get(Constants.OPPORTUNITY_ID);
        if (StringUtils.isBlank(providerCode)) {
            globalVariableService.getMessageMap().putError(Constants.PROVIDER_CODE, KeyConstants.ERROR_S2S_PROVIDER_INVALID);
            return new ArrayList<S2sOpportunity>();
        }
        if (StringUtils.isNotBlank(cfdaNumber) || StringUtils.isNotBlank(opportunityId)) {
            try {
                s2sOpportunity = s2sSubmissionService.searchOpportunity(providerCode, cfdaNumber, opportunityId, "");
            }catch (S2sCommunicationException e) {
                LOG.error(e.getMessage(), e);
                globalVariableService.getMessageMap().putError(Constants.NO_FIELD, e.getErrorKey(),e.getMessage());
                return new ArrayList<S2sOpportunity>();
            }
            if (s2sOpportunity != null && s2sOpportunity.size()!=0) {
                return s2sOpportunity;
            } else if (StringUtils.isNotBlank(cfdaNumber) && StringUtils.isNotBlank(opportunityId)) {
                try{
                    s2sOpportunity = s2sSubmissionService.searchOpportunity(providerCode, cfdaNumber, "", "");
                }catch (S2sCommunicationException e) {
                    LOG.error(e.getMessage(), e);
                    globalVariableService.getMessageMap().putError(Constants.NO_FIELD, e.getErrorKey(),e.getMessage());
                    return new ArrayList<S2sOpportunity>();
                }
                if (s2sOpportunity != null) {
                    return s2sOpportunity;
                } else{
                    if (StringUtils.isNotBlank(cfdaNumber)) {
                        globalVariableService.getMessageMap().putError(Constants.CFDA_NUMBER, KeyConstants.ERROR_IF_CFDANUMBER_IS_INVALID);
                    }
                    if (StringUtils.isNotBlank(opportunityId)) {
                        globalVariableService.getMessageMap().putError(Constants.OPPORTUNITY_ID,
                                KeyConstants.ERROR_IF_OPPORTUNITY_ID_IS_INVALID);
                    }
                }
                return new ArrayList<S2sOpportunity>();
                }
             return new ArrayList<S2sOpportunity>();
        }
        else {
            globalVariableService.getMessageMap().putError(Constants.NO_FIELD, KeyConstants.ERROR_IF_CFDANUMBER_AND_OPPORTUNITY_ID_IS_NULL);
            return s2sOpportunity;
        }
    }

    public Collection performLookup(LookupForm lookupForm, Collection resultTable, boolean bounded) {
        Collection displayList = super.performLookup(lookupForm, resultTable, bounded);
        for (Iterator iter = resultTable.iterator(); iter.hasNext();) {
            ResultRow row = (ResultRow) iter.next();
 
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
    
    public HtmlData getReturnUrl(BusinessObject businessObject, LookupForm lookupForm, List returnKeys, BusinessObjectRestrictions businessObjectRestrictions) {
        boolean showReturnLink = lookupForm.getBackLocation().contains("proposalDevelopmentGrantsGov");
        if (showReturnLink) {
            return super.getReturnUrl(businessObject, lookupForm, returnKeys, businessObjectRestrictions);
        } else {
            return getCreateLink((S2sOpportunity) businessObject);
        }
    }
    
    protected AnchorHtmlData getCreateLink(S2sOpportunity opp) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText("Create Proposal");
        Properties parameters = new Properties();
        parameters.put("createProposalFromGrantsGov", "true");
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, KRADConstants.DOC_HANDLER_METHOD);
        parameters.put(KRADConstants.PARAMETER_COMMAND, KewApiConstants.INITIATE_COMMAND);
        parameters.put(KRADConstants.DOCUMENT_TYPE_NAME, "ProposalDevelopmentDocument");
        parameters.put("newS2sOpportunity.cfdaNumber", opp.getCfdaNumber() != null ? opp.getCfdaNumber() : "");
        parameters.put("newS2sOpportunity.opportunityId", opp.getOpportunityId() != null ? opp.getOpportunityId() : "");
        parameters.put("newS2sOpportunity.opportunityTitle", opp.getOpportunityTitle() != null ? opp.getOpportunityTitle() : "");
        parameters.put("newS2sOpportunity.closingDate", opp.getClosingDate() != null ? getDateTimeService().toDateTimeString(opp.getClosingDate().getTime()) : "");
        parameters.put("newS2sOpportunity.openingDate", opp.getOpeningDate() != null ? getDateTimeService().toDateTimeString(opp.getOpeningDate().getTime()) : "");
        parameters.put("newS2sOpportunity.instructionUrl", opp.getInstructionUrl() != null ? opp.getInstructionUrl() : "");
        parameters.put("newS2sOpportunity.competetionId", opp.getCompetetionId() != null ? opp.getCompetetionId() : "");
        parameters.put("newS2sOpportunity.schemaUrl", opp.getSchemaUrl() != null ? opp.getSchemaUrl() : "");
        parameters.put("newS2sOpportunity.providerCode", opp.getProviderCode());
        String href  = UrlFactory.parameterizeUrl("../proposalDevelopmentProposal.do", parameters);
        htmlData.setHref(href);
        return htmlData;  
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

    protected DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    public S2sSubmissionService getS2sSubmissionService() {
        return s2sSubmissionService;
    }

    public void setS2sSubmissionService(S2sSubmissionService s2sSubmissionService) {
        this.s2sSubmissionService = s2sSubmissionService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }
}