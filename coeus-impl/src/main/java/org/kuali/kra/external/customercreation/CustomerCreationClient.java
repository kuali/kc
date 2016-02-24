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
package org.kuali.kra.external.customercreation;

import java.util.List;

import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.kra.external.service.KcDtoService;
import org.kuali.kra.external.sponsor.SponsorDTO;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

public interface CustomerCreationClient {

    public List<String> createCustomer(Sponsor sponsor, String initiatedByPrincipalName);

    public List<KeyValue> getCustomerTypes();

    public boolean isValidCustomer(String customerNumber);
    
    public void setSponsorDtoService(KcDtoService<SponsorDTO, Sponsor> dtoService);
    
    public void setParameterService(ParameterService parameterService);
}
