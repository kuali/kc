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
package org.kuali.kra.negotiations.lookup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.UrlFactory;
import org.kuali.rice.kns.web.struts.form.LookupForm;
import org.kuali.rice.kns.web.ui.Column;
import org.kuali.rice.kns.web.ui.ResultRow;

/**
 * Negotiation Lookup Helper Service
 */
public class NegotiationLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {

    private static final long serialVersionUID = -5559605739121335896L;
    private static final String USER_ID = "userId";
   
    private NegotiationDao negotiationDao;


    @SuppressWarnings("unchecked")
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        super.setBackLocationDocFormKey(fieldValues);
        if (this.getParameters().containsKey(USER_ID)) {
            fieldValues.put("associatedNegotiable.piId", ((String[]) this.getParameters().get(USER_ID))[0]);
            fieldValues.put("negotiatorPersonId", ((String[]) this.getParameters().get(USER_ID))[0]);
        }
        List<Negotiation> negotiations = new ArrayList<Negotiation>();
        negotiations.addAll(getNegotiationDao().getNegotiationResults(fieldValues));
        
        return negotiations;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        htmlDataList.add(getOpenLink(((Negotiation) businessObject).getDocument()));
        return htmlDataList;
    }
    
    protected AnchorHtmlData getOpenLink(Document document) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText("open");
        Properties parameters = new Properties();
        parameters.put(KNSConstants.DISPATCH_REQUEST_PARAMETER, KNSConstants.DOC_HANDLER_METHOD);
        parameters.put(KNSConstants.PARAMETER_COMMAND, KEWConstants.DOCSEARCH_COMMAND);
        parameters.put(KNSConstants.DOCUMENT_TYPE_NAME, getDocumentTypeName());
        parameters.put("docId", document.getDocumentNumber());
        String href  = UrlFactory.parameterizeUrl("../" + getHtmlAction(), parameters);
        
        htmlData.setHref(href);
        return htmlData;

    }
    

    
    @Override
    protected String getDocumentTypeName() {
        return "NegotiationDocument";
    }
    
    @Override
    protected String getKeyFieldName() {
        return "negotiationId";
    } 

    @Override
    protected String getHtmlAction() {
        return "negotiationNegotiation.do";
    }



    public NegotiationDao getNegotiationDao() {
        return negotiationDao;
    }



    public void setNegotiationDao(NegotiationDao negotiationDao) {
        this.negotiationDao = negotiationDao;
    }
    
    /**
     * Call's the super class's performLookup function and edits the URLs for the unit name, unit number, sponsor name, and pi name.
     * @see org.kuali.kra.lookup.KraLookupableHelperServiceImpl#performLookup(org.kuali.rice.kns.web.struts.form.LookupForm, java.util.Collection, boolean)
     */
    @Override
    public Collection performLookup(LookupForm lookupForm, Collection resultTable, boolean bounded) {
        final String leadUnitName = "associatedNegotiable.leadUnitName";
        final String leadUnitNumber = "associatedNegotiable.leadUnitNumber";
        final String sponsorName = "associatedNegotiable.sponsorName";
        final String piName = "associatedNegotiable.piName";
                
        Collection lookupStuff = super.performLookup(lookupForm, resultTable, bounded);
        Iterator i = resultTable.iterator();
        while (i.hasNext()) {
            ResultRow row = (ResultRow) i.next();
            for (Column column : row.getColumns()) {
                //the unit name, pi Name and sponsor name don't need to generate links.
                if (StringUtils.equalsIgnoreCase(column.getPropertyName(), leadUnitName) 
                        || StringUtils.equalsIgnoreCase(column.getPropertyName(), sponsorName)
                        || StringUtils.equalsIgnoreCase(column.getPropertyName(), piName)) {
                    column.setPropertyURL("");
                    for (AnchorHtmlData data : column.getColumnAnchors()) {
                        if (data != null) {
                            data.setHref("");
                        }
                        
                    }
                }
                if (StringUtils.equalsIgnoreCase(column.getPropertyName(), leadUnitNumber)){
                    String unitNumber = column.getPropertyValue();
                    //String newUrl = "http://127.0.0.1:8080/kc-dev/kr/inquiry.do?businessObjectClassName=org.kuali.kra.bo.Unit&unitNumber=" + unitNumber + "&methodToCall=start";
                    String newUrl = "inquiry.do?businessObjectClassName=org.kuali.kra.bo.Unit&unitNumber=" + unitNumber + "&methodToCall=start";
                    column.setPropertyURL(newUrl);
                    for (AnchorHtmlData data : column.getColumnAnchors()) {
                        if (data != null) {
                            data.setHref(newUrl);
                        }
                        
                    }
                }
            }
        }
        return lookupStuff;
    }

}
