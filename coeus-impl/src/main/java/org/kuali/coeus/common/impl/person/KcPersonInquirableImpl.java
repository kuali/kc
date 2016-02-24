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
package org.kuali.coeus.common.impl.person;

import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.kns.inquiry.KualiInquirableImpl;
import org.kuali.rice.krad.bo.BusinessObject;

import java.util.Map;

/**
 * This class is used to perform an inquiry on a KC Person.
 */
public class KcPersonInquirableImpl extends KualiInquirableImpl {
    
    private KcPersonService kcPersonService;
    
    @Override
    public BusinessObject getBusinessObject(@SuppressWarnings("unchecked") Map fieldValues) {
        final String personId = (String) fieldValues.get("personId");
        
        return personId != null ? this.getKcPersonService().getKcPersonByPersonId(personId) : null;
    }
    
    /**
     * Gets the Kc Person Service.
     * <p>
     * Currently the inquiry definition in the DD does not allow you to specify a SpringBean therefore
     * injection cannot be used for the KcPersonService.
     * </p>
     * @return the Kc person Service.
     */
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }
}
