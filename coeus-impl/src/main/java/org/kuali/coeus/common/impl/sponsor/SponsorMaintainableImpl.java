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
package org.kuali.coeus.common.impl.sponsor;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.datadictionary.MaintainableFieldDefinition;
import org.kuali.rice.kns.datadictionary.MaintainableItemDefinition;
import org.kuali.rice.kns.datadictionary.MaintainableSectionDefinition;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.document.authorization.MaintenanceDocumentPresentationController;
import org.kuali.rice.kns.document.authorization.MaintenanceDocumentRestrictions;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.kns.web.ui.Section;
import org.kuali.rice.kns.web.ui.SectionBridge;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

public class SponsorMaintainableImpl extends KraMaintainableImpl {

    private static final Logger LOG = Logger.getLogger(SponsorMaintainableImpl.class);
    private static final long serialVersionUID = 3366318004175290243L;

    public static final String AUTO_GEN_SPONSOR_CODE_PARM = "AUTO_GENERATE_SPONSOR_CODE";
    public static final String SECTION_ID = "Edit Sponsor";
    public static final String SPONSOR_CODE_NAME = "sponsorCode";
    public static final String ROLODEX_ID_SEQUENCE_NAME = "SEQ_ROLODEX_ID";
    
    
    private transient ParameterService parameterService;
    private transient DataFieldMaxValueIncrementer sponsorCodeIncrementer;
    private transient SequenceAccessorService sequenceAccessorService;
    private transient GlobalVariableService globalVariableService;
    private transient BusinessObjectService businessObjectService;

    @Override
    public void setGenerateDefaultValues(String docTypeName) {
        super.setGenerateDefaultValues(docTypeName);
        Sponsor sponsor = (Sponsor) getBusinessObject();
        if (isAutoGenerateCode()) {
            sponsor.setSponsorCode(getSponsorCodeIncrementer().nextStringValue());
        }
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Section> getSections(MaintenanceDocument document, Maintainable oldMaintainable) {
        List<Section> sections = super.getSections(document, oldMaintainable);
        if (isAutoGenerateCode()) {
            disableSponsorCode(sections);
        }
        return sections;
    }

    /**
     * This method overrides the getCoreSections method in order to filter out kfs fields when kfs integration is turned off.  Without this logic,
     * this maintainable to attempt to call KFS in order to execute values finders.
     */
    @Override
    public List<Section> getCoreSections(MaintenanceDocument document, Maintainable oldMaintainable) {
        List<Section> sections = new ArrayList<>();
        MaintenanceDocumentRestrictions maintenanceRestrictions = this.getBusinessObjectAuthorizationService().getMaintenanceDocumentRestrictions(document, getGlobalVariableService().getUserSession().getPerson());
        MaintenanceDocumentPresentationController maintenanceDocumentPresentationController = (MaintenanceDocumentPresentationController)this.getDocumentHelperService().getDocumentPresentationController(document);
        Set<String> conditionallyRequiredFields = maintenanceDocumentPresentationController.getConditionallyRequiredPropertyNames(document);
        List<MaintainableSectionDefinition> sectionDefinitions = this.getMaintenanceDocumentDictionaryService().getMaintainableSections(this.getDocumentTypeName());

        if (!getParameterService().getParameterValueAsBoolean(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.PARAMETER_COMPONENT_DOCUMENT, SponsorConstants.FIN_SYSTEM_INTEGRATION_ON_SPONSOR)) {
            filterKFSFields(sectionDefinitions);
        }

        try {
            for (MaintainableSectionDefinition maintSectionDef : sectionDefinitions) {
                List<String> displayedFieldNames = new ArrayList<>();
                if (!maintenanceRestrictions.isHiddenSectionId(maintSectionDef.getId())) {
                    for (MaintainableItemDefinition item :  maintSectionDef.getMaintainableItems()) {
                        if(item instanceof MaintainableFieldDefinition) {
                            displayedFieldNames.add(item.getName());
                        }
                    }

                    Section section1 = SectionBridge.toSection(maintSectionDef, this.getBusinessObject(), this, oldMaintainable, this.getMaintenanceAction(), displayedFieldNames, conditionallyRequiredFields);
                    if (maintenanceRestrictions.isReadOnlySectionId(maintSectionDef.getId())) {
                        section1.setReadOnly(true);
                    }

                    sections.add(section1);
                }
            }

            return sections;
        } catch (InstantiationException|IllegalAccessException var13) {
            LOG.error("Unable to create instance of object class" + var13.getMessage());
            throw new RuntimeException("Unable to create instance of object class" + var13.getMessage());
        }
    }

    protected void filterKFSFields(List<MaintainableSectionDefinition> sections) {
        for (MaintainableSectionDefinition section : sections) {
            if (StringUtils.equals(section.getId(), SECTION_ID)) {
                for (Iterator<MaintainableItemDefinition> i = section.getMaintainableItems().iterator(); i.hasNext();) {
                    final MaintainableItemDefinition item = i.next();
                    if (StringUtils.equals(item.getName(), "dunningCampaignId")
                            || StringUtils.equals(item.getName(), "customerExists")
                            || StringUtils.equals(item.getName(), "customerNumber")
                            || StringUtils.equals(item.getName(), "customerTypeCode")
                            || StringUtils.equals(item.getName(), "state")) {
                        i.remove();
                    }
                }
            }
        }
    }

    protected void disableSponsorCode(List<Section> sections) {
        for (Section section : sections) {
            if (StringUtils.equals(section.getSectionId(), SECTION_ID)) {
                for (Row row : section.getRows()) {
                    for (Field field : row.getFields()) {
                        if (StringUtils.equals(field.getPropertyName(), SPONSOR_CODE_NAME)) {
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
                Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, AUTO_GEN_SPONSOR_CODE_PARM);
    }
    
    @Override
    public void saveDataObject() {
    		if(getBusinessObject() instanceof Sponsor) {
        		Sponsor sponsor = (Sponsor)getBusinessObject();
        		Rolodex rolodex = sponsor.getRolodex();
        		if(rolodex != null && (rolodex.getRolodexId() == null || ! sponsor.getSponsorCode().equals(rolodex.getSponsorCode()))) {
        			rolodex.setRolodexId(Integer.parseInt(getSequenceAccessorService().getNextAvailableSequenceNumber(ROLODEX_ID_SEQUENCE_NAME, Rolodex.class).toString()));
        			sponsor.setRolodexId(rolodex.getRolodexId());
        		}
        		if(rolodex != null) { 
        			rolodex.setSponsorCode(sponsor.getSponsorCode());
        			rolodex.setOrganization(sponsor.getSponsorName());
        			rolodex.setOwnedByUnit(sponsor.getOwnedByUnit());
        			rolodex.setSponsorAddressFlag(true);
        			rolodex.setActive(sponsor.isActive());
                    getBusinessObjectService().save(rolodex);
        		}
        	}
    		super.saveDataObject();
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

    public DataFieldMaxValueIncrementer getSponsorCodeIncrementer() {
        if (sponsorCodeIncrementer == null) {
            sponsorCodeIncrementer = KcServiceLocator.getService("sponsorCodeIncrementer");
        }
        return sponsorCodeIncrementer;
    }

    public void setSponsorCodeIncrementer(DataFieldMaxValueIncrementer sponsorCodeIncrementer) {
        this.sponsorCodeIncrementer = sponsorCodeIncrementer;
    }

	public SequenceAccessorService getSequenceAccessorService() {
		if (sequenceAccessorService == null) {
			sequenceAccessorService = KcServiceLocator.getService("sequenceAccessorService");
        }
		return sequenceAccessorService;
	}

	public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
		this.sequenceAccessorService = sequenceAccessorService;
	}

    public GlobalVariableService getGlobalVariableService() {
        if (globalVariableService == null) {
            globalVariableService = KcServiceLocator.getService(GlobalVariableService.class);
        }
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
