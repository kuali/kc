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
package org.kuali.kra.irb.lookup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.bo.BusinessObject;
import org.kuali.core.util.ObjectUtils;
import org.kuali.core.web.ui.Field;
import org.kuali.core.web.ui.Row;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.dao.ProtocolDao;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.rice.kns.util.KNSConstants;

public class ProtocolLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {
    private List <String> searchFieldNames = new ArrayList <String> ();
    private Map <String, String> searchMap = new HashMap<String, String>();
    private Map <String, String> indicatorNames = new HashMap<String, String>();
    

    ProtocolDao protocolDao;
    
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        // TODO : funding source temporarily unavailable. - remove after all are available
        for (Entry<String,String> entry : fieldValues.entrySet()) {
            if (entry.getKey().equals(ProtocolLookupConstants.Property.FUNDING_SOURCE) && "Not Available".equals(entry.getValue())) {
                entry.setValue("");
            } 
        }
        
        //need to set backlocation & docformkey here.  Otherwise, they are empty
        super.setBackLocationDocFormKey(fieldValues);
        return protocolDao.getProtocols(fieldValues);
    }

    
    @Override
    public String getActionUrls(BusinessObject businessObject) {
        String editLink =  super.getActionUrls(businessObject);
        ProtocolDocument document = ((Protocol)businessObject).getProtocolDocument();
        return editLink + "&nbsp<a href=\"../DocCopyHandler.do?docId="+document.getDocumentNumber()+"&command=displayDocSearchView&documentTypeName=ProtocolDocument\">copy</a>";
    }


    /**
     * This override is reset field definition for several lookup fields.
     * @see org.kuali.core.lookup.AbstractLookupableHelperServiceImpl#getRows()
     */
    @Override
    public List<Row> getRows() {
        List<Row> rows =  super.getRows();
        initMappingValues();
        Map <String, String> indicatorMap = new HashMap<String, String>();
        indicatorMap.put(ProtocolLookupConstants.Property.RESEARCH_AREA_CODE, "");
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (indicatorNames.containsKey(field.getPropertyName())) {
                    setupIndicator(field);
                    indicatorMap.put(indicatorNames.get(field.getPropertyName()), field.getPropertyValue());
                } else if (searchFieldNames.contains(field.getPropertyName())) {
                    updateField(field, searchMap.get(field.getPropertyName()+indicatorMap.get(field.getPropertyName())));
                }
            }
        }
        return rows;
    }
    
    private void initMappingValues() {
        indicatorNames.put(ProtocolLookupConstants.Property.PERSON_EMPLOYEE_INDICATOR, ProtocolLookupConstants.Property.PERSON_ID);
        indicatorNames.put(ProtocolLookupConstants.Property.INVESTIGATOR_EMPLOYEE_INDICATOR , ProtocolLookupConstants.Property.PRINCIPAL_INVESTIGATOR_ID);
        indicatorNames.put(ProtocolLookupConstants.Property.FUNDING_SOURCE_TYPE_CODE,ProtocolLookupConstants.Property.FUNDING_SOURCE);
        
        // map to enum
        searchMap.put(ProtocolLookupConstants.Key.EMPLOYEE_PERSON, "EMPLOYEEPERSON");
        searchMap.put(ProtocolLookupConstants.Key.ROLODEX_PERSON, "ROLODEXPERSON");
        searchMap.put(ProtocolLookupConstants.Key.EMPLOYEE_INVESTIGATOR, "EMPLOYEEPERSON");
        searchMap.put(ProtocolLookupConstants.Key.ROLODEX_INVESTIGATOR, "ROLODEXPERSON");
        searchMap.put(ProtocolLookupConstants.Key.FUNDING_SOURCE_SPONSOR, "SPONSOR");
        searchMap.put(ProtocolLookupConstants.Key.FUNDING_SOURCE_UNIT, "UNIT");
        searchMap.put(ProtocolLookupConstants.Key.FUNDING_SOURCE_PROPOSAL, "PROPOSAL");
        searchMap.put(ProtocolLookupConstants.Key.FUNDING_SOURCE_AWARD, "AWARD");
        // TODO : the rest fundingsource not ready yet
        searchMap.put(ProtocolLookupConstants.Key.FUNDING_SOURCE_OTHER, "NOTAVAILABLE");
        searchMap.put(ProtocolLookupConstants.Key.FUNDING_SOURCE_INSTITUTE_PROPOSALE, "NOTAVAILABLE");
        searchMap.put(ProtocolLookupConstants.Property.RESEARCH_AREA_CODE, "RESEARCHAREA");
        
        searchFieldNames.add(ProtocolLookupConstants.Property.PERSON_ID);
        searchFieldNames.add(ProtocolLookupConstants.Property.PRINCIPAL_INVESTIGATOR_ID);
        searchFieldNames.add(ProtocolLookupConstants.Property.FUNDING_SOURCE);
        searchFieldNames.add(ProtocolLookupConstants.Property.RESEARCH_AREA_CODE);
       
    }
    
    /*
     * set up indicator fields and make it dropdown_fresh, so the lookup page will be refreshed if dropdown selection is changed.
     */
    private void setupIndicator(Field field) {
        field.setFieldType(Field.DROPDOWN_REFRESH);
        if (StringUtils.isBlank(field.getPropertyValue())) {
            if (field.getPropertyName().equals(ProtocolLookupConstants.Property.FUNDING_SOURCE_TYPE_CODE)) {
                field.setPropertyValue("1");                
            } else {
                field.setPropertyValue("Y");                
            }
        }
    }
    
    /*
     * update the lookup field definition.  This is critical in protocol lookup because some lookup field 
     * has multiple lookup classes based options selected. 
     */
    private void updateField(Field field, String searchKeyName) {
        LookupProperty lookupProperty = Enum.valueOf(LookupProperty.class, searchKeyName);
        super.updateLookupField(field,lookupProperty.getKeyName(),lookupProperty.getClassName());                   
    }

    /**
     * 
     * This class is to set up keyname, classname for lookup field that has search icon
     * Some of the fields has multiple lookup classes.
     */
    public enum LookupProperty {

        EMPLOYEEPERSON(ProtocolLookupConstants.Property.PERSON_ID,"org.kuali.kra.bo.Person"),
        ROLODEXPERSON(ProtocolLookupConstants.Property.ROLODEX_ID,"org.kuali.kra.bo.Rolodex"),
        SPONSOR(ProtocolLookupConstants.Property.SPONSOR_CODE,"org.kuali.kra.bo.Sponsor"),
        UNIT(ProtocolLookupConstants.Property.UNIT_NUMBER,"org.kuali.kra.bo.Unit"),
        PROPOSAL(ProtocolLookupConstants.Property.PROPOSAL_NUMBER,"org.kuali.kra.irb.bo.LookupableDevelopmentProposal"),
        AWARD(ProtocolLookupConstants.Property.AWARD_NUMBER,"org.kuali.kra.award.bo.Award"),
        RESEARCHAREA(ProtocolLookupConstants.Property.RESEARCH_AREA_CODE,"org.kuali.kra.bo.ResearchArea"),
        NOTAVAILABLE("","");

        private String keyName;
        private String className;
        
        
        private LookupProperty(String keyName, String className){
          this.keyName = keyName;
          this.className = className;
        }

        public String getKeyName() {
            return keyName;
        }
        public void setKeyName(String keyName) {
            this.keyName = keyName;
        }
        public String getClassName() {
            return className;
        }
        public void setClassName(String className) {
            this.className = className;
        }
      }
    
    public ProtocolDao getProtocolDao() {
        return protocolDao;
    }

    public void setProtocolDao(ProtocolDao protocolDao) {
        this.protocolDao = protocolDao;
    }

    /**
     * This method is for several fields that does not have inquiry created by lookup frame work.
     * @see org.kuali.core.lookup.AbstractLookupableHelperServiceImpl#getInquiryUrl(org.kuali.core.bo.BusinessObject, java.lang.String)
     */
    @Override
    public String getInquiryUrl(BusinessObject bo, String propertyName) {

        BusinessObject inqBo = bo;
        String inqPropertyName = propertyName;
        if (propertyName.equals("leadUnitNumber")) {
           inqBo = new Unit();
            ((Unit) inqBo).setUnitNumber(((Protocol) bo).getLeadUnitNumber());
            inqPropertyName = ProtocolLookupConstants.Property.UNIT_NUMBER;
        } else if (propertyName.equals(ProtocolLookupConstants.Property.PRINCIPAL_INVESTIGATOR_ID)) {
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

    protected String getHtmlAction() {
        return "protocolProtocol.do";
    }
    
    protected String getDocumentTypeName() {
        return "ProtocolDocument";
    }
    
    protected String getKeyFieldName() {
        return "protocolNumber";
    }

    
}
