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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.bo.BusinessObject;
import org.kuali.core.web.struts.form.LookupForm;
import org.kuali.core.web.ui.Field;
import org.kuali.core.web.ui.Row;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.bo.ProtocolPerson;
import org.kuali.kra.irb.dao.ProtocolDao;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.rice.kns.util.KNSConstants;

public class ProtocolLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {
    private static final String ROLODEX_ID = "rolodexId";
    private static final String UNIT_NUMBER = "unitNumber";
    private static final String PERSON_ID = "personId";
    private static final String COLUMN = ":";
    private static final String PRINCIPAL_INVESTIGATOR_ID = "principalInvestigatorId";
    private static final String FUNDING_SOURCE = "fundingSource";
    private static final String FUNDING_SOURCE_TYPE_CODE = "fundingSourceTypeCode";
    private static final String RESEARCH_AREA_CODE = "researchAreaCode";
    private List <String> searchFieldNames = new ArrayList <String> ();
    private Map <String, String> searchMap = new HashMap<String, String>();
    private Map <String, String> indicatorNames = new HashMap<String, String>();
    

    ProtocolDao protocolDao;
    
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {

        //need to set backlocation & docformkey here.  Otherwise, they are empty
        super.setBackLocationDocFormKey(fieldValues);
        return protocolDao.getProtocols(fieldValues);
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
        indicatorMap.put(RESEARCH_AREA_CODE, "");
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
        indicatorNames.put("personEmployeeIndicator", PERSON_ID);
        indicatorNames.put("investigatorEmployeeIndicator", PRINCIPAL_INVESTIGATOR_ID);
        indicatorNames.put(FUNDING_SOURCE_TYPE_CODE,FUNDING_SOURCE);
        
        // map to enum
        searchMap.put("personIdY", "EMPLOYEEPERSON");
        searchMap.put("personIdN", "ROLODEXPERSON");
        searchMap.put("principalInvestigatorIdY", "EMPLOYEEINVESTIGATOR");
        searchMap.put("principalInvestigatorIdN", "ROLODEXINVESTIGATOR");
        searchMap.put("fundingSource1", "SPONSOR");
        searchMap.put("fundingSource2", "UNIT");
        // the rest fundingsource not ready yet
        searchMap.put("fundingSource3", "UNIT");
        searchMap.put("fundingSource4", "UNIT");
        searchMap.put("fundingSource5", "UNIT");
        searchMap.put("fundingSource6", "UNIT");
        searchMap.put(RESEARCH_AREA_CODE, "RESEARCHAREA");
        
        searchFieldNames.add(PERSON_ID);
        searchFieldNames.add(PRINCIPAL_INVESTIGATOR_ID);
        searchFieldNames.add(FUNDING_SOURCE);
        searchFieldNames.add(RESEARCH_AREA_CODE);
       
    }
    
    /*
     * set up indicator fields and make it dropdown_fresh, so the lookup page will be refreshed if dropdown selection is changed.
     */
    private void setupIndicator(Field field) {
        field.setFieldType(Field.DROPDOWN_REFRESH);
        if (StringUtils.isBlank(field.getPropertyValue())) {
            if (field.getPropertyName().equals(FUNDING_SOURCE_TYPE_CODE)) {
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

        field.setFieldConversions(lookupProperty.getKeyName()+COLUMN+lookupProperty.getFieldName());
        field.setLookupParameters(lookupProperty.getFieldName()+COLUMN+lookupProperty.getKeyName());
        field.setInquiryParameters(lookupProperty.getFieldName()+COLUMN+lookupProperty.getKeyName());
        field.setQuickFinderClassNameImpl(lookupProperty.getClassName());
                   
    }

    /**
     * 
     * This class is to set up keyname,fieldname, classname for lookup field that has search icon
     * Some of the fields has multiple lookup classes.
     */
    public enum LookupProperty {

        EMPLOYEEPERSON(PERSON_ID,PERSON_ID,"org.kuali.kra.bo.Person"),
        ROLODEXPERSON(ROLODEX_ID,PERSON_ID,"org.kuali.kra.bo.Rolodex"),
        EMPLOYEEINVESTIGATOR(PERSON_ID,PRINCIPAL_INVESTIGATOR_ID,"org.kuali.kra.bo.Person"),
        ROLODEXINVESTIGATOR(ROLODEX_ID,PRINCIPAL_INVESTIGATOR_ID,"org.kuali.kra.bo.Rolodex"),
        SPONSOR("sponsorCode",FUNDING_SOURCE,"org.kuali.kra.bo.Sponsor"),
        UNIT(UNIT_NUMBER,FUNDING_SOURCE,"org.kuali.kra.bo.Unit"),
        RESEARCHAREA(RESEARCH_AREA_CODE,RESEARCH_AREA_CODE,"org.kuali.kra.bo.ResearchArea");

        private String keyName;
        private String fieldName;
        private String className;
        
        
        private LookupProperty(String keyName, String fieldName, String className){
          this.keyName = keyName;
          this.fieldName = fieldName;
          this.className = className;
        }

        public String getKeyName() {
            return keyName;
        }
        public void setKeyName(String keyName) {
            this.keyName = keyName;
        }
        public String getFieldName() {
            return fieldName;
        }
        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
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
            inqPropertyName = UNIT_NUMBER;
        } else if (propertyName.equals(PRINCIPAL_INVESTIGATOR_ID)) {
            Protocol protocol = (Protocol) bo;
            ProtocolPerson principalInvestigator = protocol.getPrincipalInvestigator();
            if (principalInvestigator != null) {
                if (StringUtils.isNotBlank(principalInvestigator.getPersonId())) {
                    inqBo = new Person();
                    ((Person) inqBo).setPersonId(principalInvestigator.getPersonId());
                    inqPropertyName = PERSON_ID;
                } else {
                    if (principalInvestigator.getRolodexId() != null) {
                        ((Rolodex) inqBo).setRolodexId(principalInvestigator.getRolodexId());
                        inqPropertyName = ROLODEX_ID;
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
