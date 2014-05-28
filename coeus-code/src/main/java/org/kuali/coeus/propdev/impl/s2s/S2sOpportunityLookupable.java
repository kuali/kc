package org.kuali.coeus.propdev.impl.s2s;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.service.S2SService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.lookup.LookupForm;
import org.kuali.rice.krad.lookup.LookupView;
import org.kuali.rice.krad.lookup.LookupableImpl;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.UrlFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/*
   todo in xml from s2sOpportunityLookupableHelperService :
   format urls as href
   conditionally display return link
   create link

   GlobalVariables.getMessageMap().putInfo(KRADConstants.GLOBAL_INFO, Constants.GRANTS_GOV_LINK);
 */

public class S2sOpportunityLookupable extends LookupableImpl {

    private static final Log LOG = LogFactory.getLog(S2sOpportunityLookupable.class);

    private S2SService s2SService;
    private DateTimeService dateTimeService;
    
    @Override
    public List<?> performSearch(LookupForm form, Map<String, String> searchCriteria, boolean unbounded) {

        final String providerCode = searchCriteria.get(Constants.PROVIDER_CODE);
        final String cfdaNumber = searchCriteria.get(Constants.CFDA_NUMBER);
        final String opportunityId = searchCriteria.get(Constants.OPPORTUNITY_ID);
        HashMap<String, String> mp = new HashMap<String, String>();
        mp.put("opportunityId", "newS2sOpportunity.opportunityId");
        mp.put("opportunityTitle", "newS2sOpportunity.opportunityTitle");
        mp.put("openingDate", "newS2sOpportunity.openingDate");
        mp.put("revisionCode", "newS2sOpportunity.revisionCode");
        mp.put("schemaUrl", "newS2sOpportunity.schemaUrl");
        mp.put("closingDate", "newS2sOpportunity.closingDate"); 
        mp.put("instructionUrl", "newS2sOpportunity.instructionUrl");
        mp.put("s2sSubmissionTypeCode", "newS2sOpportunity.s2sSubmissionTypeCode");
        mp.put("competetionId", "newS2sOpportunity.competetionId");        
        mp.put("providerCode", "newS2sOpportunity.providerCode");
        mp.put("cfdaNumber", "newS2sOpportunity.cfdaNumber");            
        form.setFieldConversions(mp);
        if (StringUtils.isBlank(providerCode)) {
            GlobalVariables.getMessageMap().putError(Constants.PROVIDER_CODE, KeyConstants.ERROR_S2S_PROVIDER_INVALID);
            return Collections.emptyList();
        }

        List<S2sOpportunity> s2sOpportunity = new ArrayList<S2sOpportunity>();
        if (StringUtils.isNotBlank(cfdaNumber) || StringUtils.isNotBlank(opportunityId)) {
            try {
                s2sOpportunity = getS2SService().searchOpportunity(providerCode, cfdaNumber, opportunityId, "");
            }catch (S2SException e) {
                LOG.error(e.getMessage(), e);
                GlobalVariables.getMessageMap().putError(Constants.NO_FIELD, e.getErrorKey(),e.getMessage());
                return Collections.emptyList();
            }
            if (s2sOpportunity != null && !s2sOpportunity.isEmpty()) {
                return s2sOpportunity;
            } else if (StringUtils.isNotBlank(cfdaNumber) && StringUtils.isNotBlank(opportunityId)) {
                try{
                    s2sOpportunity = getS2SService().searchOpportunity(providerCode, cfdaNumber, "", "");
                }catch (S2SException e) {
                    LOG.error(e.getMessage(), e);
                    GlobalVariables.getMessageMap().putError(Constants.NO_FIELD, e.getErrorKey(),e.getMessage());
                    return Collections.emptyList();
                }
                if (s2sOpportunity != null) {
                    return s2sOpportunity;
                } else {
                    if (StringUtils.isNotBlank(cfdaNumber)) {
                        GlobalVariables.getMessageMap().putError(Constants.CFDA_NUMBER, KeyConstants.ERROR_IF_CFDANUMBER_IS_INVALID);
                    }
                    if (StringUtils.isNotBlank(opportunityId)) {
                        GlobalVariables.getMessageMap().putError(Constants.OPPORTUNITY_ID,
                                KeyConstants.ERROR_IF_OPPORTUNITY_ID_IS_INVALID);
                    }
                }
                return Collections.emptyList();
            }
            return Collections.emptyList();
        }
        else {
            GlobalVariables.getMessageMap().putError(Constants.NO_FIELD, KeyConstants.ERROR_IF_CFDANUMBER_AND_OPPORTUNITY_ID_IS_NULL);
            return s2sOpportunity;
        }
    }

    public S2SService getS2SService() {
        if (s2SService == null) {
            s2SService = KcServiceLocator.getService(S2SService.class);
        }

        return s2SService;
    }
    
    @Override
    protected String getReturnUrl(LookupView lookupView, LookupForm lookupForm, Object dataObject) {
    	List<S2sOpportunity> s2sOpportunityList  = (List<S2sOpportunity>) lookupForm.getLookupResults();
    	 Properties props = getReturnUrlParameters(lookupView, lookupForm, dataObject);
    	if (s2sOpportunityList!= null){
            for (int i=0; i<s2sOpportunityList.size(); i++ ){   
           	 	S2sOpportunity opp = s2sOpportunityList.get(i);
           	 	props.put("newS2sOpportunity.cfdaNumber", opp.getCfdaNumber() != null ? opp.getCfdaNumber() : "");
           	 	props.put("newS2sOpportunity.opportunityId", opp.getOpportunityId() != null ? opp.getOpportunityId() : "");
           	 	props.put("newS2sOpportunity.opportunityTitle", opp.getOpportunityTitle() != null ? opp.getOpportunityTitle() : "");
           	 	props.put("newS2sOpportunity.closingDate", opp.getClosingDate() != null ? getDateTimeService().toDateTimeString(opp.getClosingDate().getTime()) : "");
           	 	props.put("newS2sOpportunity.openingDate", opp.getOpeningDate() != null ? getDateTimeService().toDateTimeString(opp.getOpeningDate().getTime()) : "");
           	 	props.put("newS2sOpportunity.instructionUrl", opp.getInstructionUrl() != null ? opp.getInstructionUrl() : "");
           	 	props.put("newS2sOpportunity.competetionId", opp.getCompetetionId() != null ? opp.getCompetetionId() : "");
           	 	props.put("newS2sOpportunity.schemaUrl", opp.getSchemaUrl() != null ? opp.getSchemaUrl() : "");
           	 	props.put("newS2sOpportunity.providerCode", opp.getProviderCode());
            }
    	}
        String href = "";
        if (StringUtils.isNotBlank(lookupForm.getReturnLocation())) {
            href = UrlFactory.parameterizeUrl(lookupForm.getReturnLocation(), props);
        }
        return href;
    }
    
    protected DateTimeService getDateTimeService() {
        if (dateTimeService == null) {
            dateTimeService = KcServiceLocator.getService(DateTimeService.class);
        }
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }
}
