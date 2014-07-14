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
package org.kuali.coeus.common.impl.rolodex;

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
import org.kuali.rice.krad.service.SequenceAccessorService;

import java.util.List;
import java.util.Map;

public class RolodexMaintainableImpl extends KraMaintainableImpl {


    private static final long serialVersionUID = -6436670290567992063L;
    
    public static final String ROLODEX_ID_SEQUENCE_NAME = "SEQ_ROLODEX_ID";
    public static final String AUTO_GEN_ROLODEX_ID_PARM = "AUTO_GENERATE_NON_EMPLOYEE_ID";
    public static final String SECTION_ID = "Edit Address Book";
    public static final String ROLODEX_ID_NAME = "rolodexId";
    
    
    private transient ParameterService parameterService;
    private transient SequenceAccessorService sequenceAccessorService;
   
    @Override
    public void setGenerateDefaultValues(String docTypeName) {
        super.setGenerateDefaultValues(docTypeName);
        Rolodex rolodex = (Rolodex) getBusinessObject();
        if (isAutoGenerateCode()) {
            rolodex.setRolodexId(Integer.parseInt(getSequenceAccessorService().getNextAvailableSequenceNumber(ROLODEX_ID_SEQUENCE_NAME, Rolodex.class).toString()));
        }
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Section> getSections(MaintenanceDocument document, Maintainable oldMaintainable) {
        List<Section> sections = super.getSections(document, oldMaintainable);
        if (isAutoGenerateCode()) {
            disableRolodexId(sections);
        }
        return sections;
    }
    
    protected void disableRolodexId(List<Section> sections) {
        for (Section section : sections) {
            if (StringUtils.equals(section.getSectionId(), SECTION_ID)) {
                for (Row row : section.getRows()) {
                    for (Field field : row.getFields()) {
                        if (StringUtils.equals(field.getPropertyName(), ROLODEX_ID_NAME)) {
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
                Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, AUTO_GEN_ROLODEX_ID_PARM);
    }

    @Override
    public void saveDataObject() {
		if(getBusinessObject() instanceof Rolodex) {
			Rolodex rolodex = (Rolodex)getBusinessObject();
			rolodex.setSponsorAddressFlag(false);
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
