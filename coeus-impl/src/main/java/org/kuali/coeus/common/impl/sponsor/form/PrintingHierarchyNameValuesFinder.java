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
package org.kuali.coeus.common.impl.sponsor.form;

import org.kuali.coeus.common.impl.sponsor.SponsorHierarchyMaintenanceService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.List;

public class PrintingHierarchyNameValuesFinder extends UifKeyValuesFinderBase {

    private SponsorHierarchyMaintenanceService sponsorHierarchyMaintenanceService;
    private ParameterService parameterService;

    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> result = new ArrayList<KeyValue>();
        String hierarchyName = getParameterService().getParameterValueAsString(
                Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, 
                Constants.SPONSOR_HIERARCHY_PRINTING_NAME_PARAM);
        List<String> groupNames = getSponsorHierarchyMaintenanceService().getUniqueGroupingNames(hierarchyName, 1);
        for (String group : groupNames) {
            result.add(new ConcreteKeyValue(group, group));
        }
        return result;
    }

    public SponsorHierarchyMaintenanceService getSponsorHierarchyMaintenanceService() {
        if (sponsorHierarchyMaintenanceService == null) {
            sponsorHierarchyMaintenanceService = KcServiceLocator.getService(SponsorHierarchyMaintenanceService.class);
        }
        return sponsorHierarchyMaintenanceService;
    }

    public void setSponsorHierarchyMaintenanceService(SponsorHierarchyMaintenanceService sponsorHierarchyMaintenanceService) {
        this.sponsorHierarchyMaintenanceService = sponsorHierarchyMaintenanceService;
    }

    public ParameterService getParameterService() {
        if (parameterService == null) {
            parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

}
