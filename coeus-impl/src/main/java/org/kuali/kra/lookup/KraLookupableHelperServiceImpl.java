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
package org.kuali.kra.lookup;

import org.apache.commons.lang3.StringUtils;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.web.struts.form.LookupForm;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;
import org.kuali.rice.krad.util.UrlFactory;

import java.util.*;
import java.util.Map.Entry;

public abstract class KraLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {

    private static final String COLUMN = ":";
    private static final String VIEW = "view";
    protected static final String MEDUSA = "medusa";
    /**
     * create 'edit' link
     */
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        addEditHtmlData(htmlDataList, businessObject);
        return htmlDataList;

}
    protected void addEditHtmlData(List<HtmlData> htmlDataList, BusinessObject businessObject) {
        Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, KRADConstants.DOC_HANDLER_METHOD);
        parameters.put(KRADConstants.PARAMETER_COMMAND, KewApiConstants.INITIATE_COMMAND);
        parameters.put(KRADConstants.DOCUMENT_TYPE_NAME, getDocumentTypeName());
        parameters.put(getKeyFieldName(), ObjectUtils.getPropertyValue(businessObject, getKeyFieldName()).toString());
        String href = UrlFactory.parameterizeUrl("../"+getHtmlAction(), parameters);
        
        AnchorHtmlData anchorHtmlData = new AnchorHtmlData(href, 
                KRADConstants.DOC_HANDLER_METHOD, KRADConstants.MAINTENANCE_EDIT_METHOD_TO_CALL);
        htmlDataList.add(anchorHtmlData);
    }

    protected AnchorHtmlData getViewLink(Document document) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText(VIEW);
        Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, KRADConstants.DOC_HANDLER_METHOD);
        parameters.put(KRADConstants.PARAMETER_COMMAND, KewApiConstants.DOCSEARCH_COMMAND);
        parameters.put(KRADConstants.DOCUMENT_TYPE_NAME, getDocumentTypeName());
        parameters.put("viewDocument", "true");
        parameters.put("docId", document.getDocumentNumber());
        String href  = UrlFactory.parameterizeUrl("../"+getHtmlAction(), parameters);
        
        htmlData.setHref(href);
        return htmlData;

    }
    
    protected AnchorHtmlData getMedusaLink(Document document, Boolean readOnly) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText(MEDUSA);
        Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, "medusa");
        parameters.put(KRADConstants.PARAMETER_COMMAND, KewApiConstants.DOCSEARCH_COMMAND);
        parameters.put(KRADConstants.DOCUMENT_TYPE_NAME, getDocumentTypeName());
        parameters.put("viewDocument", readOnly.toString());
        parameters.put("docId", document.getDocumentNumber());
        String href  = UrlFactory.parameterizeUrl("../"+getHtmlAction(), parameters);
        
        htmlData.setHref(href);
        return htmlData;
    }
    
    /**
     * 
     * @param document
     * @param methodToCall method to call on action
     * @param linkName 
     * @param readOnly whether the document should be readOnly or not
     * @return
     */
    protected AnchorHtmlData getCustomLink(Document document, String methodToCall, String linkName, Boolean readOnly) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText(linkName);
        Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, methodToCall);
        parameters.put(KRADConstants.PARAMETER_COMMAND, KewApiConstants.DOCSEARCH_COMMAND);
        parameters.put(KRADConstants.DOCUMENT_TYPE_NAME, getDocumentTypeName());
        parameters.put("viewDocument", readOnly.toString());
        parameters.put("docId", document.getDocumentNumber());
        String href  = UrlFactory.parameterizeUrl("../"+getHtmlAction(), parameters);
        
        htmlData.setHref(href);
        return htmlData;
    }

    
    /**
     * To force to it to show action links, such as 'edit' if it is not 'lookup' to search of return value.
     * @see org.kuali.core.lookup.AbstractLookupableHelperServiceImpl#performLookup(org.kuali.core.web.struts.form.LookupForm, java.util.Collection, boolean)
     */
    @Override
    public Collection performLookup(LookupForm lookupForm, Collection resultTable, boolean bounded) {
        if (!lookupForm.isHideReturnLink()) {
            lookupForm.setSuppressActions(true);
        } else {
            lookupForm.setShowMaintenanceLinks(true);
        }
        return super.performLookup(lookupForm, resultTable, bounded);
    }
    
    /**
     * 
     * This method to set both fields if child class override getSearchResults
     * @param fieldValues
     */
    protected void setBackLocationDocFormKey(Map<String, String> fieldValues) {

        //need to set backlocation & docformkey here.  Otherwise, they are empty
        for (Entry<String,String> entry : fieldValues.entrySet()) {
            if (entry.getKey().equals(KRADConstants.BACK_LOCATION)) {
                setBackLocation(entry.getValue());
            } else if (entry.getKey().equals(KRADConstants.DOC_FORM_KEY)) {
                setDocFormKey(entry.getValue());
            }
        }
    }
 
    /**
     * 
     * This method is to set up field definitions for lookup fields.  
     * These fields does not have a reference object defined for it in repository &amp; bo.
     * @param field
     * @param keyName
     * @param className
     */
    protected void updateLookupField(Field field, String keyName, String className) {
        if (StringUtils.isNotBlank(keyName) && StringUtils.isNotBlank(className)) {
            field.setFieldConversions(keyName+COLUMN+field.getPropertyName());
            field.setLookupParameters(field.getPropertyName()+COLUMN+keyName);
            field.setInquiryParameters(field.getPropertyName()+COLUMN+keyName);
            field.setQuickFinderClassNameImpl(className);
        } 
        
    }

        
    /**
     * htmlaction for 'edit' link
     */
    protected abstract String getHtmlAction();
    
    /**
     * 
     * This method to set document type of the lookup bo's document
     * @return
     */
    protected abstract String getDocumentTypeName();
    
    /**
     * 
     * This method is set the key field to retrieve bo for editing.  May change to  a map if there are multiple fields.
     * @return
     */
    protected abstract String getKeyFieldName();

}
