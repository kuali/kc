/*
 * Copyright 2005-2013 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.maintenance;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.OrganizationYnq;
import org.kuali.kra.bo.Ynq;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.CongressionalDistrict;
import org.kuali.kra.service.YnqService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.kns.web.ui.Section;
import org.kuali.rice.krad.service.SequenceAccessorService;

public class OrganizationMaintenableImpl extends KraMaintainableImpl {
    private static final long serialVersionUID = 7123853550462673935L;
    
    public static final String ORGANIZATION_ID_SEQUENCE_NAME = "SEQ_ORGANIZATION_ID";
    public static final String AUTO_GEN_ORGANIZATION_ID_PARM = "AUTO_GENERATE_ORGANIZATION_ID";
    public static final String SECTION_ID = "Edit Organization";
    public static final String ORGANIZATION_ID_NAME = "organizationId";
    
    private transient ParameterService parameterService;
    private transient SequenceAccessorService sequenceAccessorService;

    /**
     * This is a hook for initializing the BO from the maintenance framework.
     * It initializes the {@link Explanation}s collection.
     *
     */
    @Override
    public void setGenerateDefaultValues(String docTypeName) {
        super.setGenerateDefaultValues(docTypeName);
        if (getBusinessObject().getOrganizationYnqs() == null || getBusinessObject().getOrganizationYnqs().isEmpty()) {
            initOrganizationYnq();
        }
        if (isAutoGenerateCode()) {
            Organization organization = getBusinessObject();
            organization.setOrganizationId(getSequenceAccessorService().getNextAvailableSequenceNumber(ORGANIZATION_ID_SEQUENCE_NAME, Organization.class).toString());
        }
    }

    
    /**
     * This is just trying to populate existing organization that has no ynq.
     * @see org.kuali.core.maintenance.KualiMaintainableImpl#getCoreSections(org.kuali.core.maintenance.Maintainable)
     */
    @Override
    public List<Section> getCoreSections(MaintenanceDocument document, Maintainable oldMaintainable) {
        Organization organization = getBusinessObject();
        if (organization.getOrganizationYnqs() == null || organization.getOrganizationYnqs().isEmpty()) {
            initOrganizationYnq();
        }
        return super.getCoreSections(document, oldMaintainable);
    }


    /**
     * 
     * This method generate organizationynqs list based on ynq type or 'organization'
     */
    private void initOrganizationYnq() {
        Organization organization = getBusinessObject();
        List<OrganizationYnq> organizationYnqs = organization.getOrganizationYnqs();
        if (!organizationYnqs.isEmpty()) {
            throw new AssertionError();
        }
        
        List<Ynq> ynqs = getOrganizationTypeYnqs();
        for (Ynq ynq : ynqs) {
            OrganizationYnq organizationYnq = new OrganizationYnq();
            organizationYnq.setYnq(ynq);
            organizationYnq.setQuestionId(ynq.getQuestionId());
            
            if (StringUtils.isNotBlank(organization.getOrganizationId())) {
                organizationYnq.setOrganizationId(organization.getOrganizationId()); 
            }
            organizationYnqs.add(organizationYnq);
        }
    }

    /**
     * 
     * This method calls ynqservice to get ynq list of organization type.
     * @return
     */
    private List<Ynq> getOrganizationTypeYnqs() {
        return KraServiceLocator.getService(YnqService.class).getYnq("O");
    }


    @Override
    @SuppressWarnings("unchecked")
    public Map<String, String> populateBusinessObject(Map<String, String> fieldValues, MaintenanceDocument maintenanceDocument, String methodToCall) {
        Map<String, String> map = super.populateBusinessObject(fieldValues, maintenanceDocument, methodToCall);
        formatCongressionalDistrict(getBusinessObject());
        return map;
    }

    /**
     * This method pads the district number to CongressionalDistrict.DISTRICT_NUMBER_LENGTH
     * characters (A congressional district consists of a state code, followed by a dash,
     * followed by a district number).
     * @param getOrganization
     */
    private void formatCongressionalDistrict(Organization organization) {
        String district = organization.getCongressionalDistrict();
        if (district != null) {
            int dashPosition = district.indexOf('-');
            if (dashPosition >= 0) {
                // everything up to, and including, the dash
                String stateCodePlusDash = district.substring(0, dashPosition + 1);
                String paddedDistrictNumber = StringUtils.leftPad(district.substring(dashPosition + 1), CongressionalDistrict.DISTRICT_NUMBER_LENGTH, '0');
                organization.setCongressionalDistrict(stateCodePlusDash + paddedDistrictNumber);
            }
        }
    }
    
    @Override
    public Organization getBusinessObject() {
        return (Organization) super.getBusinessObject();
    }
    
    /**
     * 
     * @see org.kuali.kra.maintenance.KraMaintainableImpl#getSections(org.kuali.rice.kns.document.MaintenanceDocument, org.kuali.rice.kns.maintenance.Maintainable)
     */
    @SuppressWarnings("unchecked")
    public List<Section> getSections(MaintenanceDocument document, Maintainable oldMaintainable) {
        List<Section> sections = super.getSections(document, oldMaintainable);
        if (isAutoGenerateCode()) {
            disableOrganizationId(sections);
        }
        return sections;
    }
    
    protected void disableOrganizationId(List<Section> sections) {
        for (Section section : sections) {
            if (StringUtils.equals(section.getSectionId(), SECTION_ID)) {
                for (Row row : section.getRows()) {
                    for (Field field : row.getFields()) {
                        if (StringUtils.equals(field.getPropertyName(), ORGANIZATION_ID_NAME)) {
                            field.setReadOnly(true);
                        }
                    }
                }
            }
        }        
    }

    @Override
    public void processAfterCopy(MaintenanceDocument document, Map<String, String[]> parameters) {
        super.processAfterCopy(document, parameters);
        setGenerateDefaultValues(document.getDocumentHeader().getWorkflowDocument().getDocumentTypeName());       
    }

    protected boolean isAutoGenerateCode() {
        return getParameterService().getParameterValueAsBoolean(Constants.KC_GENERIC_PARAMETER_NAMESPACE, 
                Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, AUTO_GEN_ORGANIZATION_ID_PARM);
    }

    protected ParameterService getParameterService() {
        if (parameterService == null) {
            parameterService = KraServiceLocator.getService(ParameterService.class);
        }
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    protected SequenceAccessorService getSequenceAccessorService() {
        if(sequenceAccessorService == null) {
            sequenceAccessorService = KraServiceLocator.getService(SequenceAccessorService.class);
        }
        return sequenceAccessorService;
    }

    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }
}
