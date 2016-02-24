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
package org.kuali.kra.subawardReporting.printing.print;

import java.io.ByteArrayInputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.subaward.bo.SubAwardForms;
import org.kuali.kra.subaward.reporting.printing.service.SubAwardPrintingService;
import org.kuali.rice.krad.service.BusinessObjectService;

public class SubAwardFDPModification extends AbstractPrint {
    
    private BusinessObjectService businessObjectService;
    
    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public Map<String,Source> getXSLTemplateWithBookmarks() {
        Map<String,Source> sourceMap = new LinkedHashMap<String,Source>(); 
        List<SubAwardForms> printFormTemplates = (List<SubAwardForms>)getReportParameters().get(SubAwardPrintingService.SELECTED_TEMPLATES);
        for (SubAwardForms sponsorFormTemplate : printFormTemplates) {
            SubAwardForms sponsorTemplate = (SubAwardForms) getBusinessObjectService().findBySinglePrimaryKey(SubAwardForms.class, 
                    sponsorFormTemplate.getFormId());
            sourceMap.put(sponsorFormTemplate.getDescription(),new StreamSource(new ByteArrayInputStream(sponsorTemplate.getAttachmentContent())));
        }
        
        return sourceMap;
    }

}
