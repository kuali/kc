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
import org.kuali.core.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.core.web.struts.form.LookupForm;
import org.kuali.core.web.ui.Field;
import org.kuali.core.web.ui.Row;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.bo.ProtocolPerson;
import org.kuali.kra.irb.dao.ProtocolDao;

public class ProtocolLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {
    private static final String CLASS_NAME = "className";
    private static final String KEY_NAME = "keyName";
    private static final String FIELD_NAME = "fieldName";
    private static final String PERSON_ID = "personId";
    private static final String PERSON = "person";
    private static final String INVESTIGATOR_ID = "principalInvestigatorId";
    private static final String FUNDING_SOURCE = "fundingSource";
    private static final Map<String, String> PERSON_MAP = new HashMap<String, String>();
    static {
        PERSON_MAP.put(CLASS_NAME, "org.kuali.kra.bo.Person");
        PERSON_MAP.put(KEY_NAME, PERSON_ID);
        //PERSON_MAP.put(FIELD_NAME, PERSON_ID);
    }
    private static final Map<String, String> ROLODEX_MAP = new HashMap<String, String>();
    static {
        ROLODEX_MAP.put(CLASS_NAME, "org.kuali.kra.bo.Rolodex");
        ROLODEX_MAP.put(KEY_NAME, "rolodexId");
        //ROLODEX_MAP.put(FIELD_NAME, PERSON_ID);
    }
    private static final Map<String, String> SPONSOR_MAP = new HashMap<String, String>();
    static {
        SPONSOR_MAP.put(CLASS_NAME, "org.kuali.kra.bo.Sponsor");
        SPONSOR_MAP.put(KEY_NAME, "sponsorCode");
        SPONSOR_MAP.put(FIELD_NAME, FUNDING_SOURCE);
    }
    private static final Map<String, String> UNIT_MAP = new HashMap<String, String>();
    static {
        UNIT_MAP.put(CLASS_NAME, "org.kuali.kra.bo.Unit");
        UNIT_MAP.put(KEY_NAME, "unitNumber");
        UNIT_MAP.put(FIELD_NAME, FUNDING_SOURCE);
    }
    private static final Map<String, String> RESEARCH_AREA_MAP = new HashMap<String, String>();
    static {
        RESEARCH_AREA_MAP.put(CLASS_NAME, "org.kuali.kra.bo.ResearchArea");
        RESEARCH_AREA_MAP.put(KEY_NAME, "researchAreaCode");
        RESEARCH_AREA_MAP.put(FIELD_NAME, "researchAreaCode");
    }
    private static final List <Map<String, String>> FUNDING_MAP_LIST = new ArrayList<Map<String, String>>();
    static {
        FUNDING_MAP_LIST.add(0, SPONSOR_MAP);
        FUNDING_MAP_LIST.add(1, UNIT_MAP);
        // TODO : the rest are not ready yet
        FUNDING_MAP_LIST.add(2, UNIT_MAP);
        FUNDING_MAP_LIST.add(3, UNIT_MAP);
        FUNDING_MAP_LIST.add(4, UNIT_MAP);
        FUNDING_MAP_LIST.add(5, UNIT_MAP);
    }


    ProtocolDao protocolDao;
    
    @Override
    public String getActionUrls(BusinessObject businessObject) {
      //  return super.getActionUrls(businessObject);
      //  try to make it generic
        String idName = "";
        String docTypeName="";
        if (businessObject instanceof Protocol) {
            idName="&protocolId=";
            docTypeName="ProtocolDocument";
        }
//        String docTypeName = getDataDictionaryService().getDataDictionary().getMaintenanceDocumentEntryForBusinessObjectClass(getBusinessObjectClass()).getDocumentTypeName();
        String editUrl = getMaintenanceUrl(businessObject, "edit");
        int idx1 = editUrl.indexOf(idName);
        int idx2 = editUrl.indexOf("\"", idx1+1);
        String protocolIdParam = editUrl.substring(idx1, idx2);
        //return "<a href=\"../protocolProtocol.do?methodToCall=docHandler&command=initiate&docTypeName="+docTypeName+editUrl.substring(idx1, idx2)+"\">edit</a>";
        return "<a href=\"../protocolProtocol.do?methodToCall=docHandler&command=initiate&docTypeName="+docTypeName+"&protocolNumber="+((Protocol)businessObject).getProtocolNumber()+"\">edit</a>";
    }

    @Override
    public String getMaintenanceUrl(BusinessObject businessObject, String methodToCall) {
        // TODO Auto-generated method stub
        return super.getMaintenanceUrl(businessObject, methodToCall);
    }

    @Override
    public Collection performLookup(LookupForm lookupForm, Collection resultTable, boolean bounded) {
        // TODO Auto-generated method stub
        lookupForm.setShowMaintenanceLinks(true);
        return super.performLookup(lookupForm, resultTable, bounded);
    }

    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {

        //need to set backlocation & docformkey here.  Otherwise, they are empty
        for (Entry<String,String> entry : fieldValues.entrySet()) {
            if (entry.getKey().equals("backLocation")) {
                setBackLocation(entry.getValue());
            } else if (entry.getKey().equals("docFormKey")) {
                setDocFormKey(entry.getValue());
            }
        }
        return protocolDao.getProtocols(fieldValues);
    }

    
    @Override
    public List<Row> getRows() {
        List<Row> rows =  super.getRows();
        String employeeIndicator="Y";
        Integer funcingSource = 1;
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (field.getPropertyName().endsWith("EmployeeIndicator")) {
                    field.setFieldType(Field.DROPDOWN_REFRESH);
                    if (StringUtils.isBlank(field.getPropertyValue())) {
                        field.setPropertyValue("Y") ;
                    }
                    employeeIndicator = field.getPropertyValue();
                } else if (field.getPropertyName().equals(PERSON_ID) || field.getPropertyName().equals(INVESTIGATOR_ID)) {
                    updatePersonLookup(field, employeeIndicator.equals("Y"));
                } else if (field.getPropertyName().equals("fundingSourceTypeCode")) {
                    field.setFieldType(Field.DROPDOWN_REFRESH);
                    if (StringUtils.isBlank(field.getPropertyValue())) {
                        field.setPropertyValue("1") ;
                    } 
                    funcingSource = Integer.parseInt(field.getPropertyValue());
                } else if (field.getPropertyName().equals(FUNDING_SOURCE)) {
                    updateField(field, FUNDING_MAP_LIST.get(funcingSource - 1));
                }else if (field.getPropertyName().equals("researchAreaCode")) {
                    updateField(field, RESEARCH_AREA_MAP);
                }
            }
        }
        return rows;
    }

    private void updatePersonLookup(Field field, boolean isEmployee) {
        if (field.getPropertyName().equals(PERSON_ID)) {
            PERSON_MAP.put(FIELD_NAME, PERSON_ID);                
            ROLODEX_MAP.put(FIELD_NAME, PERSON_ID);
        } else {
            PERSON_MAP.put(FIELD_NAME, INVESTIGATOR_ID);
            ROLODEX_MAP.put(FIELD_NAME, INVESTIGATOR_ID);
        }
        if (isEmployee) {
            updateField(field, PERSON_MAP);
        } else {
            updateField(field, ROLODEX_MAP);
            
        }
        
    }
    
    private void updateField(Field field, Map<String,String> lookupMap) {
        field.setFieldConversions(lookupMap.get(KEY_NAME)+":"+lookupMap.get(FIELD_NAME));
        field.setLookupParameters(lookupMap.get(FIELD_NAME)+":"+lookupMap.get(KEY_NAME));
        field.setInquiryParameters(lookupMap.get(FIELD_NAME)+":"+lookupMap.get(KEY_NAME));
        field.setQuickFinderClassNameImpl(lookupMap.get(CLASS_NAME));
                   
    }

    public ProtocolDao getProtocolDao() {
        return protocolDao;
    }

    public void setProtocolDao(ProtocolDao protocolDao) {
        this.protocolDao = protocolDao;
    }

    @Override
    public String getInquiryUrl(BusinessObject bo, String propertyName) {

        BusinessObject inqBo = bo;
        String inqPropertyName = propertyName;
        if (propertyName.equals("leadUnitNumber")) {
           inqBo = new Unit();
            ((Unit)inqBo).setUnitNumber(((Protocol)bo).getLeadUnitNumber());
            inqPropertyName = "unitNumber";
            //return super.getInquiryUrl(inqBo, "unitNumber");
        } else if (propertyName.equals("principalInvestigatorId")) {
            Protocol protocol = (Protocol)bo;
            ProtocolPerson principalInvestigator = protocol.getPrincipalInvestigator();
            if (principalInvestigator != null) {
                if (StringUtils.isNotBlank(principalInvestigator.getPersonId())) {
                    inqBo = new Person();
                    ((Person)inqBo).setPersonId(principalInvestigator.getPersonId());
                    inqPropertyName = "personId";
                } else {
                    if (principalInvestigator.getRolodexId() != null) {
                        ((Rolodex)inqBo).setRolodexId(principalInvestigator.getRolodexId());
                        inqPropertyName = "rolodexId";
                    }
                }
            }
        }
        return super.getInquiryUrl(inqBo, inqPropertyName);
    }




}
