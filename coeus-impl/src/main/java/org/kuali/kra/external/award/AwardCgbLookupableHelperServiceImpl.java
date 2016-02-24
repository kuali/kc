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
package org.kuali.kra.external.award;

import java.util.List;
import java.util.Properties;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.lookup.AwardLookupableHelperServiceImpl;
import org.kuali.rice.kew.impl.document.search.DocumentSearchCriteriaBo;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.UrlFactory;

public class AwardCgbLookupableHelperServiceImpl extends AwardLookupableHelperServiceImpl {

	public static final String INVOICE_DOCUMENT_TYPE = "CINV";
	public static final String AWARD_INVOICE_PROPOSAL_NUMBER = "documentAttribute.proposalNumber";
	
    @Override
    @SuppressWarnings("unchecked")
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
    	List<HtmlData> result = super.getCustomActionUrls(businessObject, pkNames);
    	result.add(getInvoicesLookupUrl(businessObject));
    	return result;    	
    }
    
    /**
     * This method adds a link to the look up FOR the invoices associated with a given Award.
     *
     * @param bo
     * @return
     */
    protected AnchorHtmlData getInvoicesLookupUrl(BusinessObject bo) {
        Award award = (Award) bo;
        Properties params = new Properties();
        params.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, KRADConstants.SEARCH_METHOD);
        params.put(KRADConstants.DOC_FORM_KEY, "88888888");
        params.put(KRADConstants.HIDE_LOOKUP_RETURN_LINK, "false");
        params.put("documentTypeName", INVOICE_DOCUMENT_TYPE);
        params.put(AWARD_INVOICE_PROPOSAL_NUMBER, award.getAwardNumber());
        params.put(KRADConstants.RETURN_LOCATION_PARAMETER, "portal.do");
        params.put(KRADConstants.BUSINESS_OBJECT_CLASS_ATTRIBUTE, DocumentSearchCriteriaBo.class.getName());
        String url = UrlFactory.parameterizeUrl(KRADConstants.LOOKUP_ACTION, params);
        return new AnchorHtmlData(url, KRADConstants.SEARCH_METHOD, "View Invoices");
    }    
    
}
