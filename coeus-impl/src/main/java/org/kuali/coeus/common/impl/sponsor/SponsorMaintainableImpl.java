/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.impl.sponsor;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.kns.web.ui.Section;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

public class SponsorMaintainableImpl extends KraMaintainableImpl {

    private static final long serialVersionUID = 3366318004175290243L;

    public static final String AUTO_GEN_SPONSOR_CODE_PARM = "AUTO_GENERATE_SPONSOR_CODE";
    public static final String SECTION_ID = "Edit Sponsor";
    public static final String SPONSOR_CODE_NAME = "sponsorCode";
    public static final String ROLODEX_ID_SEQUENCE_NAME = "SEQ_ROLODEX_ID";
    
    
    private transient ParameterService parameterService;
    private transient DataFieldMaxValueIncrementer sponsorCodeIncrementer;
    private transient SequenceAccessorService sequenceAccessorService;

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
    
    
}
