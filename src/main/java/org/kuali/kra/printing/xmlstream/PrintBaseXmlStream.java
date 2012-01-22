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
package org.kuali.kra.printing.xmlstream;

import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class...
 */
public abstract class PrintBaseXmlStream implements XmlStream {

    private BusinessObjectService businessObjectService;
    private DateTimeService dateTimeService;
    /**
     * @see org.kuali.kra.printing.xmlstream.XmlStream#getBusinessObjectService()
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * @see org.kuali.kra.printing.xmlstream.XmlStream#getDateTimeService()
     */
    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    /**
     * @see org.kuali.kra.printing.xmlstream.XmlStream#setBusinessObjectService(org.kuali.rice.krad.service.BusinessObjectService)
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * @see org.kuali.kra.printing.xmlstream.XmlStream#setDateTimeService(org.kuali.rice.core.api.datetime.DateTimeService)
     */
    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

}
