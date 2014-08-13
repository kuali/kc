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
