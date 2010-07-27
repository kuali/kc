/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;

/**
 * 
 * This class handles searching for protocoles.
 */
public class ProtocolLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {    

    private static final String RESEARCH_AREA_CLASS_PATH = ResearchArea.class.getName();
    private static final String ORGANIZATION_CLASS_PATH = Organization.class.getName();
    private DictionaryValidationService dictionaryValidationService;
    private ProtocolDao protocolDao;
    private KcPersonService kcPersonService;
    private KraAuthorizationService kraAuthorizationService;

    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        validateSearchParameters(fieldValues);
        // need to set backlocation & docformkey here. Otherwise, they are empty
        super.setBackLocationDocFormKey(fieldValues);        
        return protocolDao.getProtocols(fieldValues);
    }
    
    @Override
    public void validateSearchParameters(Map fieldValues) {
        //super.validateSearchParameters(fieldValues);
        Set<String> keys = fieldValues.keySet();
        for (String key : keys) {
            String value = fieldValues.get(key).toString();
            if (key.toUpperCase().indexOf("DATE") > 0) {
                //we have a date, now we need to weed out the calculated params that have '..' or '>=' or '<='
                if(value.indexOf("..") == -1 && value.indexOf(">=") == -1 && value.indexOf("<=") == -1) {
                    boolean valid = validateDate(key, value);
                    System.err.println("key:'" + key + "'  value:'" + value + "' valid:'" + valid + "'");
                }
            }
        }
    }
    
    private boolean validateDate(String dateFieldName, String dateFieldValue) {
        try{
            KNSServiceLocator.getDateTimeService().convertToSqlTimestamp(dateFieldValue);
            return true;
        } catch (ParseException e) {
            System.err.println("Putting an error in '" + dateFieldName + "' that had a value of '" + dateFieldValue + "'");
            GlobalVariables.getErrorMap().putError(dateFieldName, KeyConstants.ERROR_PROTOCOL_SEARCH_INVALID_DATE);
            return false;
        }
    }
    
    /**
     * 
     * This method returns an instance of a DictionaryValidationService implementation
     * @return
     */
    public DictionaryValidationService getDictionaryValidationService() {
        if (dictionaryValidationService == null) {
            dictionaryValidationService = KNSServiceLocator.getDictionaryValidationService();
        }
        return dictionaryValidationService;
    }

    /**
     * add 'copy' link to actions list
     * @see org.kuali.kra.lookup.KraLookupableHelperServiceImpl#getCustomActionUrls(org.kuali.rice.kns.bo.BusinessObject, java.util.List)
     */
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        if(kraAuthorizationService.hasPermission(getUserIdentifier(), (Protocol) businessObject, PermissionConstants.MODIFY_PROTOCOL)) {
            //htmlDataList = super.getCustomActionUrls(businessObject, pkNames);
            // Chnage "edit" to edit same document, NOT initializing a new Doc
            AnchorHtmlData editHtmlData = getViewLink(((Protocol) businessObject).getProtocolDocument());
            String href = editHtmlData.getHref();
            href = href.replace("viewDocument=true", "viewDocument=false");
            editHtmlData.setHref(href);
            editHtmlData.setDisplayText("edit");
            htmlDataList.add(editHtmlData);
            AnchorHtmlData htmlData = getUrlData(businessObject, KNSConstants.MAINTENANCE_COPY_METHOD_TO_CALL, pkNames);
            ProtocolDocument document = ((Protocol) businessObject).getProtocolDocument();
            htmlData.setHref("../DocCopyHandler.do?docId=" + document.getDocumentNumber()
                    + "&command=displayDocSearchView&documentTypeName=" + getDocumentTypeName());
            htmlDataList.add(htmlData);
        }
        if(kraAuthorizationService.hasPermission(getUserIdentifier(), (Protocol) businessObject, PermissionConstants.VIEW_PROTOCOL)) {
            htmlDataList.add(getViewLink(((Protocol) businessObject).getProtocolDocument()));
        }
        return htmlDataList;
    }


    /**
     * This override is reset field definition for research area lookup fields & investigator label.
     * @see org.kuali.core.lookup.AbstractLookupableHelperServiceImpl#getRows()
     */
    @Override
    public List<Row> getRows() {
        List<Row> rows =  super.getRows();
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (field.getPropertyName().equals(ProtocolLookupConstants.Property.RESEARCH_AREA_CODE)) {
                    super.updateLookupField(field,ProtocolLookupConstants.Property.RESEARCH_AREA_CODE,RESEARCH_AREA_CLASS_PATH);
                } else if (field.getPropertyName().equals(ProtocolLookupConstants.Property.PERFORMING_ORGANIZATION_ID)) {
                    super.updateLookupField(field,ProtocolLookupConstants.Property.ORGANIZATION_ID,ORGANIZATION_CLASS_PATH);
                } else if (field.getPropertyName().equals(ProtocolLookupConstants.Property.PROTOCOL_STATUS_CODE) || field.getPropertyName().equals(ProtocolLookupConstants.Property.PROTOCOL_TYPE_CODE)) {
                    // to disable lookup/inquiry display
                    field.setQuickFinderClassNameImpl(KNSConstants.EMPTY_STRING);
                } else if (field.getPropertyName().startsWith("leadUnit")) {
                    // This is to set label for search criteria.
                    if (field.getPropertyName().equals("leadUnitNumber")) {
                        field.setFieldLabel("Unit Number");
                    } else {
                        field.setFieldLabel("Unit Name");                        
                    }
                }
            }
        }
        return rows;
    }
             
    /**
     * 
     * This is spring bean will be used to get search results.
     * @param protocolDao
     */
    public void setProtocolDao(ProtocolDao protocolDao) {
        this.protocolDao = protocolDao;
    }
    
    /**
     * Sets the KC Person Service.
     * @param kcPersonService the kc person service
     */
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    /**
     * This method is for several fields that does not have inquiry created by lookup frame work.
     * @see org.kuali.core.lookup.AbstractLookupableHelperServiceImpl#getInquiryUrl(org.kuali.core.bo.BusinessObject, java.lang.String)
     */
    @Override
    public HtmlData getInquiryUrl(BusinessObject bo, String propertyName) {

        BusinessObject inqBo = bo;
        String inqPropertyName = propertyName;
        if (propertyName.equals(ProtocolLookupConstants.Property.LEAD_UNIT_NUMBER)) {
           inqBo = new Unit();
            ((Unit) inqBo).setUnitNumber(((Protocol) bo).getLeadUnitNumber());
            inqPropertyName = ProtocolLookupConstants.Property.UNIT_NUMBER;
        } else if (propertyName.equals(ProtocolLookupConstants.Property.INVESTIGATOR)) {
            Protocol protocol = (Protocol) bo;
            ProtocolPerson principalInvestigator = protocol.getPrincipalInvestigator();
            if (principalInvestigator != null) {
                if (StringUtils.isNotBlank(principalInvestigator.getPersonId())) {
                    inqBo = this.kcPersonService.getKcPersonByPersonId(principalInvestigator.getPersonId());
                    inqPropertyName = ProtocolLookupConstants.Property.PERSON_ID;
                } else {
                    if (principalInvestigator.getRolodexId() != null) {
                        inqBo = new Rolodex();
                        ((Rolodex) inqBo).setRolodexId(principalInvestigator.getRolodexId());
                        inqPropertyName = ProtocolLookupConstants.Property.ROLODEX_ID;
                    }
                }
            }
        }
        return super.getInquiryUrl(inqBo, inqPropertyName);
    }

    @Override
    protected String getHtmlAction() {
        return "protocolProtocol.do";
    }
    
    @Override
    protected String getDocumentTypeName() {
        return "ProtocolDocument";
    }
    
    @Override
    protected String getKeyFieldName() {
        return "protocolNumber"; 
    }

    private String getUserIdentifier() {
         return GlobalVariables.getUserSession().getPrincipalId();
    }

    public void setKraAuthorizationService(KraAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }

}
