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
package org.kuali.coeus.common.impl.org;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.org.OrganizationYnq;
import org.kuali.coeus.common.framework.ynq.Ynq;
import org.kuali.coeus.common.framework.ynq.YnqService;
import org.kuali.coeus.propdev.impl.location.CongressionalDistrict;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.kns.web.ui.Section;
import org.kuali.rice.krad.service.SequenceAccessorService;

import java.util.List;
import java.util.Map;

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
        return KcServiceLocator.getService(YnqService.class).getYnq("O");
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
     * @param organization
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
    
    @Override
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
            parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    protected SequenceAccessorService getSequenceAccessorService() {
        if(sequenceAccessorService == null) {
            sequenceAccessorService = KcServiceLocator.getService(SequenceAccessorService.class);
        }
        return sequenceAccessorService;
    }

    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }
}
