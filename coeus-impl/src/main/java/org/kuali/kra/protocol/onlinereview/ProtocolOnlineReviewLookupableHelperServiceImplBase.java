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
package org.kuali.kra.protocol.onlinereview;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.kra.protocol.onlinereview.lookup.ProtocolOnlineReviewLookupConstants;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.UrlFactory;

import java.text.ParseException;
import java.util.*;

public abstract class ProtocolOnlineReviewLookupableHelperServiceImplBase extends KraLookupableHelperServiceImpl {


    private static final long serialVersionUID = 7269604308213091097L;


    private DictionaryValidationService dictionaryValidationService;
    
    //field names
    private static final String REVIEWER_EMPLOYEE="lookupReviewerPersonId";
    private static final String REVIEWER_NONEMPLOYEE="lookupReviewerRolodexId";
    private static final String LOOKUP_PROTOCOL_ONLINE_REVIEW_STATUS_CODES="lookupProtocolOnlineReviewStatusCode";
    private static final String PROTOCOL_NUMBER="lookupProtocol.protocolNumber";
    private static final String PROTOCOL_REVIEW_DUE_DATE="lookupDateDue";
    //translateed field names to object graph
    private static final String OBJ_PROTOCOLREVIEWER_REVIEWER_EMPLOYEE="protocolReviewer.personId";
    private static final String OBJ_PROTOCOLREVIEWER_NONEMPLOYEE="protocolReviewer.personId";
    private static final String OBJ_PROTOCOL_PROTOCOL_NUMBER="protocol.protocolNumber";
    private static final String OBJ_PROTOCOL_ONLINE_REVIEW_STATUS_CODE="protocolOnlineReviewStatusCode";
    private static final String OBJ_PROTOCOL_REVIEW_DUE_DATE="protocol.dateDue";    
    
    @Override
    protected abstract String getDocumentTypeName();

    @Override
    protected abstract String getHtmlAction();

    @Override
    protected String getKeyFieldName() {
        return "protocolOnlineReviewId";
    }

   /**
    * 
    * This method returns an instance of a DictionaryValidationService implementation
    * @return
    */
   public DictionaryValidationService getDictionaryValidationService() {
       if (dictionaryValidationService == null) {
           dictionaryValidationService =  KNSServiceLocator.getKNSDictionaryValidationService();
       }
       return dictionaryValidationService;
   }

   protected boolean validateDate(String dateFieldName, String dateFieldValue) {
       try{
           CoreApiServiceLocator.getDateTimeService().convertToSqlTimestamp(dateFieldValue);
           return true;
       } catch (ParseException e) {
           GlobalVariables.getMessageMap().putError(dateFieldName, KeyConstants.ERROR_PROTOCOL_SEARCH_INVALID_DATE);
           return false;
       } catch (Exception e) {
           LOG.error(e.getMessage(), e);
           GlobalVariables.getMessageMap().putError(dateFieldName, KeyConstants.ERROR_PROTOCOL_SEARCH_INVALID_DATE);
           return false;
       }
   }

   @Override
   public void validateSearchParameters(Map fieldValues) {
       Map<String,String> fvalues = (Map<String,String>)fieldValues;
       super.validateSearchParameters(fieldValues);
       Set<String> keys = fieldValues.keySet();
       for (String key : keys) {
           String value = fieldValues.get(key).toString();
           if (key.toUpperCase().indexOf("DATE") > 0) {
               //we have a date, now we need to weed out the calculated params that have '..' or '>=' or '<='
               if(value.indexOf("..") == -1 && value.indexOf(">=") == -1 && value.indexOf("<=") == -1) {
                   if(StringUtils.isNotBlank(value)) {
                       validateDate(key, value);
                   }
               }
           }
       }
       

       if (StringUtils.isNotBlank(fvalues.get(REVIEWER_NONEMPLOYEE)) && StringUtils.isNotBlank(fvalues.get(REVIEWER_EMPLOYEE))) {
           //we can only search for one at a time.
           GlobalVariables.getMessageMap().putError(ProtocolOnlineReviewLookupConstants.Property.REVIEWER_NONEMPLOYEE, KeyConstants.ERROR_PROTOCOL_ONLINE_REVIEW_INVALID_ONE_PERSON_ONLY);
           
       } 
       
   }
  
   @Override
   public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
       validateSearchParameters(fieldValues);
       List<ProtocolOnlineReviewBase> results;

       if (StringUtils.isNotBlank(fieldValues.get(REVIEWER_EMPLOYEE))) {
           fieldValues.put(OBJ_PROTOCOLREVIEWER_REVIEWER_EMPLOYEE, fieldValues.get(REVIEWER_EMPLOYEE));
       } else if (StringUtils.isNotBlank(fieldValues.get(REVIEWER_NONEMPLOYEE))) {
           fieldValues.put(OBJ_PROTOCOLREVIEWER_NONEMPLOYEE, fieldValues.get(REVIEWER_NONEMPLOYEE));
       }
       
       if (StringUtils.isNotBlank(fieldValues.get(PROTOCOL_REVIEW_DUE_DATE))) {
           fieldValues.put(OBJ_PROTOCOL_REVIEW_DUE_DATE, fieldValues.get(PROTOCOL_REVIEW_DUE_DATE));
       }
       
       if (StringUtils.isNotBlank(fieldValues.get(PROTOCOL_NUMBER))) {
           fieldValues.put( OBJ_PROTOCOL_PROTOCOL_NUMBER, fieldValues.get(PROTOCOL_NUMBER));
       }
       
       if (StringUtils.isNotBlank(fieldValues.get(LOOKUP_PROTOCOL_ONLINE_REVIEW_STATUS_CODES))) {
           fieldValues.put(OBJ_PROTOCOL_ONLINE_REVIEW_STATUS_CODE, fieldValues.get(LOOKUP_PROTOCOL_ONLINE_REVIEW_STATUS_CODES));
       }
       
       fieldValues.remove(PROTOCOL_REVIEW_DUE_DATE);
       fieldValues.remove(REVIEWER_NONEMPLOYEE);
       fieldValues.remove(REVIEWER_EMPLOYEE);
       fieldValues.remove(PROTOCOL_NUMBER);
       fieldValues.remove(LOOKUP_PROTOCOL_ONLINE_REVIEW_STATUS_CODES);

       super.setBackLocationDocFormKey(fieldValues);
       results = (List<ProtocolOnlineReviewBase>)super.getSearchResults(fieldValues);
       return filterResults(results);
   }

   protected abstract List<ProtocolOnlineReviewBase> filterResults(List<ProtocolOnlineReviewBase> results);
   
   protected abstract String getProtocolSubmissionApprovedStatusCodeHook();

  
    @Override
    protected void addEditHtmlData(List<HtmlData> htmlDataList, BusinessObject businessObject) {
        ProtocolOnlineReviewBase protocolOnlineReview = (ProtocolOnlineReviewBase) businessObject;
        
        if (getProtocolOLRSavedStatusCodeHook().equals(protocolOnlineReview.getProtocolOnlineReviewStatusCode())) {
            htmlDataList.add(getEditLinkForDocument(protocolOnlineReview.getProtocolOnlineReviewDocument()));
        } else {
            htmlDataList.add(getViewLink(protocolOnlineReview.getProtocolOnlineReviewDocument().getDocumentNumber()));
        }
    }
    
    protected abstract String getProtocolOLRSavedStatusCodeHook();

    @Override
    protected AnchorHtmlData getViewLink(String documentNumber) {
        Properties parameters = getLinkProperties(documentNumber);
        parameters.put("viewDocument", "true");
        String displayText = "view";
        
        return getAnchorHtmlData(parameters, displayText);
    }
    
    private AnchorHtmlData getEditLinkForDocument(Document document) {
        Properties parameters = getLinkProperties(document.getDocumentNumber());
        String displayText = KRADConstants.MAINTENANCE_EDIT_METHOD_TO_CALL;
        
        return getAnchorHtmlData(parameters, displayText);
    }
    
    private Properties getLinkProperties(String documentNumber) {
        Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, "redirectToProtocolFromReview");
        parameters.put(KRADConstants.PARAMETER_COMMAND, "displayDocSearchView");
        parameters.put(KRADConstants.DOCUMENT_TYPE_NAME, getDocumentTypeName());
        parameters.put("docId", documentNumber);
        return parameters;
    }
    
    private AnchorHtmlData getAnchorHtmlData(Properties parameters, String displayText) {
        String href  = UrlFactory.parameterizeUrl("../" + getHtmlAction(), parameters);
        return new AnchorHtmlData(href, KRADConstants.DOC_HANDLER_METHOD, displayText);
    }

}
