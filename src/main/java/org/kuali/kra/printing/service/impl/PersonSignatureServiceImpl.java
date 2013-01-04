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
package org.kuali.kra.printing.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.PersonSignature;
import org.kuali.kra.dao.PersonSignatureDao;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.printing.service.PersonSignatureService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

public class PersonSignatureServiceImpl implements PersonSignatureService {
    
    private static final String CORRESPONDENCE_SIGNATURE_TYPE = "CORRESPONDENCE_SIGNATURE_TYPE";
    private BusinessObjectService businessObjectService;
    private ParameterService parameterService;
    private RoleService roleService;
    private PersonSignatureDao personSignatureDao;

    /**
     * Configure signature type required
     * D - Always use Default signature
     * S - Always use signed in user signature, if not found use the default signature
     * N - No signature is required
     */
    public enum SignatureTypes {
        DEFAULT_SIGNATURE("D"), 
        SIGNED_IN_USER_SIGNATURE("S"), 
        NO_SIGNATURE_REQURIED("N");

        private String signatureType;

        private SignatureTypes(String signatureType) {
            this.signatureType = signatureType;
        }

        public String getSignatureType() {
            return signatureType;
        }

        public static SignatureTypes getSignatureMode(String signatureType) {
            for (SignatureTypes sType : values() ){
                if (sType.signatureType.equals(signatureType)) {
                    return sType;
                }
            }
            return null;
        }
        
    }    

    public byte[] applySignature(String leadUnitNumber, String administratorType,
            byte[] pdfBytes) throws Exception {
        identifyModeAndApplySignature(leadUnitNumber, administratorType, pdfBytes);
        return null;
    }
    
    /**
     * This method is to identify signature mode and invoke appropriate method
     * to sign the document.
     */
    protected void identifyModeAndApplySignature(String leadUnitNumber, String administratorType,
            byte[] pdfBytes) {
        String signatureTypeParam = getSignatureTypeParameter("KC-PROTOCOL");
        SignatureTypes signatureType = SignatureTypes.NO_SIGNATURE_REQURIED;
        
        if(ObjectUtils.isNotNull(signatureTypeParam)) {
            signatureType = SignatureTypes.getSignatureMode(signatureTypeParam);
        }
        
        switch(signatureType) {
            case DEFAULT_SIGNATURE :
                break;
            case SIGNED_IN_USER_SIGNATURE :
                break;
            case NO_SIGNATURE_REQURIED :
                break;
        }
    }
    
    /**
     * This method is to print default module admin signature
     * @param leadUnitNumber
     * @param administratorType
     * @param pdfBytes
     */
    protected void printDefaultSignature(String leadUnitNumber, String administratorType, 
            byte[] pdfBytes) {
        PersonSignature adminSignature = getAdminSignature(leadUnitNumber, administratorType);
        if(ObjectUtils.isNotNull(adminSignature)) {
            if(ObjectUtils.isNotNull(adminSignature.getAttachmentContent())) {
                applyAutographInDocument(adminSignature, pdfBytes);
            }
        }
    }

    /**
     * This method is to print logged in user signature.
     * If logged in user signature is not available, get the admin signature.
     * @param personId
     * @param leadUnitNumber
     * @param administratorType
     * @param pdfBytes
     */
    protected void printLoggedInUserSignature(String personId, String leadUnitNumber, String administratorType,
            byte[] pdfBytes) {
        PersonSignature userSignature = getLoggedinPersonSignature(personId);
        if(ObjectUtils.isNull(userSignature)) {
            userSignature = getAdminSignature(leadUnitNumber, administratorType);
        }
        if(ObjectUtils.isNotNull(userSignature)) {
            if(ObjectUtils.isNotNull(userSignature.getAttachmentContent())) {
                applyAutographInDocument(userSignature, pdfBytes);
            }
        }
    }
    
    protected void applyAutographInDocument(PersonSignature personSignature, byte[] pdfBytes) {
        
    }
    
    /**
     * This method is to get the default admin signature
     * @param leadUnitNumber
     * @param administratorType
     * @return
     */
    protected PersonSignature getAdminSignature(String leadUnitNumber, String administratorType) {
        List<RoleMembership> moduleAdministrators = getAdministrators(leadUnitNumber, administratorType);
        Collection<String> personIdsForSignature = new ArrayList<String>();
        for(RoleMembership roleMembership : moduleAdministrators) {
            personIdsForSignature.add(roleMembership.getMemberId());
        }
        List<PersonSignature> personSignatures = getPersonSignatureDao().getPersonSignatureForPersonIds(personIdsForSignature);
        
        PersonSignature adminSignature = null;
        for(PersonSignature personSignature : personSignatures) {
            if(personSignature.isDefaultAdminSignature()) {
                adminSignature = personSignature;
                break;
            }
        }
        return adminSignature;
    }
    
    /**
     * This method is to get logged in person signature
     * @param personId
     * @return
     */
    protected PersonSignature getLoggedinPersonSignature(String personId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(Constants.PERSON_SIGNATURE_PERSON_ID, personId);
        fieldValues.put(Constants.PERSON_SIGNATURE_ACTIVE, Boolean.TRUE);
        PersonSignature personSignature = (PersonSignature) getBusinessObjectService().findByPrimaryKey(PersonSignature.class, fieldValues);
        return personSignature;
    }
    
    
    protected List<RoleMembership> getAdministrators(String leadUnitNumber, String administratorType) {    
        List<String> roleIds = new ArrayList<String>();
        String roleId = getRoleService().getRoleIdByNamespaceCodeAndName(RoleConstants.DEPARTMENT_ROLE_TYPE, administratorType);
        roleIds.add(roleId);
        Map<String,String> attrSet =new HashMap<String,String>();
        attrSet.put(KcKimAttributes.UNIT_NUMBER, leadUnitNumber);
        return getRoleService().getRoleMembers(roleIds, attrSet);
    }  
    
    protected String getSignatureTypeParameter(String moduleNameSpace) {
        return getParameterService().getParameterValueAsString(moduleNameSpace, ParameterConstants.DOCUMENT_COMPONENT, CORRESPONDENCE_SIGNATURE_TYPE);
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public PersonSignatureDao getPersonSignatureDao() {
        return personSignatureDao;
    }

    public void setPersonSignatureDao(PersonSignatureDao personSignatureDao) {
        this.personSignatureDao = personSignatureDao;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
