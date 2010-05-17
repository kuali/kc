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
package org.kuali.kra.committee.print;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.printing.xmlstream.XmlStream;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DateTimeService;

/**
 * This class generates XML that confirms with the XSD related to 
 * committee reports. The data for XML is derived from
 * {@link ResearchDocumentBase} and {@link Map} of details passed to the class.
 */
public class CommitteeXmlStream implements XmlStream {

    private DateTimeService dateTimeService;
    private BusinessObjectService businessObjectService;
    
    /**
     * This method generates XML committee report. It uses data passed in
     * {@link ResearchDocumentBase} for populating the XML nodes. The XMl once
     * generated is returned as {@link XmlObject}
     * 
     * @param document
     *            using which XML is generated
     * @param reportParameters
     *            parameters related to XML generation
     * @return {@link XmlObject} representing the XML
     */
    public Map<String, XmlObject> generateXmlStream(ResearchDocumentBase document, Map<String, Object> reportParameters) {
        Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
        // TODO: need CommitteeDocument specific factory
        XmlObject xmlObject = XmlObject.Factory.newInstance();
        xmlObjectList.put("Sample", xmlObject);
        return xmlObjectList;
    }

    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
