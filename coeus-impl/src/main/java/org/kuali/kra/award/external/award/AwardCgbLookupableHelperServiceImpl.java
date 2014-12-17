package org.kuali.kra.award.external.award;

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
