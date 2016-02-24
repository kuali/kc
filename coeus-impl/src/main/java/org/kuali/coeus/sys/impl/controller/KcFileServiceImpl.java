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
package org.kuali.coeus.sys.impl.controller;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.controller.KcFileService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("kcFileService")
public class KcFileServiceImpl implements KcFileService {

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    public Long getMaxUploadSizeParameter() {
        long maxUploadSize = 0;
        String maxString = getParameterService().getParameterValueAsString(KRADConstants.KNS_NAMESPACE, ParameterConstants.ALL_COMPONENT, KRADConstants.MAX_UPLOAD_SIZE_PARM_NM);

        String suffix = StringUtils.substring(maxString, maxString.length() - 1);
        Long multiplier = Long.parseLong(StringUtils.stripEnd(maxString, suffix));
        if (StringUtils.equals(suffix,"K")) {
            maxUploadSize = multiplier * 1000L;
        } else if (StringUtils.equals(suffix,"M")) {
            maxUploadSize = multiplier * 1000000L;
        } else if (StringUtils.equals(suffix,"G")) {
            maxUploadSize = multiplier * 1000000000L;
        } else {
            maxUploadSize = Long.parseLong(maxString);
        }

        return maxUploadSize;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
