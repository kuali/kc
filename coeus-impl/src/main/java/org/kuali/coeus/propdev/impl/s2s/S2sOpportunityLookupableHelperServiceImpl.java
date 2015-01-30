/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.s2s;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.lookup.KcKualiLookupableHelperServiceImpl;
import org.kuali.kra.infrastructure.Constants;
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

    @Autowired
    @Qualifier("s2sOpportunityLookupKradKnsHelperService")
    private S2sOpportunityLookupKradKnsHelperService s2sOpportunityLookupKradKnsHelperService;

    @Autowired
    @Qualifier("dateTimeService")
    private DateTimeService dateTimeService;

    public List<? extends BusinessObject> getSearchResults(Map<String, String> searchCriteria) {
        LookupUtils.removeHiddenCriteriaFields(getBusinessObjectClass(), searchCriteria);
        setBackLocation(searchCriteria.get(KRADConstants.BACK_LOCATION));
        setDocFormKey(searchCriteria.get(KRADConstants.DOC_FORM_KEY));
        setReferencesToRefresh(searchCriteria.get(KRADConstants.REFERENCES_TO_REFRESH));
        KNSGlobalVariables.getMessageList().add(Constants.GRANTS_GOV_LINK);

        final String providerCode = searchCriteria.get(Constants.PROVIDER_CODE);
        final String cfdaNumber = searchCriteria.get(Constants.CFDA_NUMBER);
        final String opportunityId = searchCriteria.get(Constants.OPPORTUNITY_ID);

        return s2sOpportunityLookupKradKnsHelperService.performSearch(providerCode, cfdaNumber, opportunityId);
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

    public S2sOpportunityLookupKradKnsHelperService getS2sOpportunityLookupKradKnsHelperService() {
        return s2sOpportunityLookupKradKnsHelperService;
    }

    public void setS2sOpportunityLookupKradKnsHelperService(S2sOpportunityLookupKradKnsHelperService s2sOpportunityLookupKradKnsHelperService) {
        this.s2sOpportunityLookupKradKnsHelperService = s2sOpportunityLookupKradKnsHelperService;
    }
}
