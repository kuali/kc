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
package org.kuali.kra.bo;

import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.external.agency.AgencyCreationClient;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.kra.maintenance.KraMaintenanceDocument;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.bo.DocumentHeader;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.service.SequenceAccessorService;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.kns.web.ui.Section;

public class SponsorMaintainableImpl extends KraMaintainableImpl {

    private static final long serialVersionUID = 3366318004175290243L;
    
    public static final String SPONSOR_CODE_SEQUENCE_NAME = "SEQ_SPONSOR_CODE";
    public static final String AUTO_GEN_SPONSOR_CODE_PARM = "AUTO_GENERATE_SPONSOR_CODE";
    public static final String SECTION_ID = "Edit Sponsor";
    public static final String SPONSOR_CODE_NAME = "sponsorCode";
    
    
    private transient ParameterService parameterService;
    private transient SequenceAccessorService sequenceAccessorService;
   
    /**
     * 
     * @see org.kuali.rice.kns.maintenance.KualiMaintainableImpl#setGenerateDefaultValues(java.lang.String)
     */
    @Override
    public void setGenerateDefaultValues(String docTypeName) {
        super.setGenerateDefaultValues(docTypeName);
        Sponsor sponsor = (Sponsor) getBusinessObject();
        if (isAutoGenerateCode()) {
            sponsor.setSponsorCode(getSequenceAccessorService().getNextAvailableSequenceNumber(SPONSOR_CODE_SEQUENCE_NAME, Sponsor.class).toString());
        }
    }
    
    /**
     * 
     * @see org.kuali.kra.maintenance.KraMaintainableImpl#getSections(org.kuali.rice.kns.document.MaintenanceDocument, org.kuali.rice.kns.maintenance.Maintainable)
     */
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
    
    
    
    protected boolean isAutoGenerateCode() {
        return getParameterService().getIndicatorParameter(Constants.KC_GENERIC_PARAMETER_NAMESPACE, 
                Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, AUTO_GEN_SPONSOR_CODE_PARM);
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

    @Override
    public void doRouteStatusChange(DocumentHeader documentHeader) {
        super.doRouteStatusChange(documentHeader);
        
        if(documentHeader.getWorkflowDocument().stateIsFinal()){
            //TODO creat & check parameter
            //if(getParamter().getValue().equals("YES")){
            try {
                MaintenanceDocument document = (MaintenanceDocument)KNSServiceLocator.getDocumentService().getByDocumentHeaderId(documentHeader.getDocumentNumber());
                createFinancialSystemAgency((Sponsor)document.getDocumentBusinessObject());
            }
            catch (WorkflowException e) {
                e.printStackTrace();
            }  
            //} 
        }
    }
    
    private void createFinancialSystemAgency(Sponsor sponsor){
        AgencyCreationClient aclient = (AgencyCreationClient) KraServiceLocator.getService("agencyCreationClient");
        try {
            aclient.createAgency(sponsor);
        }
        catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        catch (WorkflowException e) {
            e.printStackTrace();
        }
    }
    
    
    
}
