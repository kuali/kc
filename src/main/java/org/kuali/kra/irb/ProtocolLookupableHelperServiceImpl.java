/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;

class ProtocolLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {    

    private static final String RESEARCH_AREA_CLASS_PATH = ResearchArea.class.getName();
    private static final String ORGANIZATION_CLASS_PATH = Organization.class.getName();
    ProtocolDao protocolDao;

    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        // need to set backlocation & docformkey here. Otherwise, they are empty
        super.setBackLocationDocFormKey(fieldValues);
        return protocolDao.getProtocols(fieldValues);
    }

    /**
     * add 'copy' link to actions list
     * @see org.kuali.kra.lookup.KraLookupableHelperServiceImpl#getCustomActionUrls(org.kuali.rice.kns.bo.BusinessObject, java.util.List)
     */
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = super.getCustomActionUrls(businessObject, pkNames);
        AnchorHtmlData htmlData = getUrlData(businessObject, KNSConstants.MAINTENANCE_COPY_METHOD_TO_CALL, pkNames);;
        ProtocolDocument document = ((Protocol) businessObject).getProtocolDocument();
        htmlData.setHref("../DocCopyHandler.do?docId="+document.getDocumentNumber()
            +"&command=displayDocSearchView&documentTypeName="+getDocumentTypeName());
        htmlDataList.add(htmlData);
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
                    inqBo = new Person();
                    ((Person) inqBo).setPersonId(principalInvestigator.getPersonId());
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

    
}
