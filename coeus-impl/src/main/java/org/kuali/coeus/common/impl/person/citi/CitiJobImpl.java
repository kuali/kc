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
package org.kuali.coeus.common.impl.person.citi;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.person.citi.CitiDataProcessingService;
import org.kuali.coeus.common.framework.person.citi.CitiJob;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.rice.krad.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("citiJob")
public class CitiJobImpl implements CitiJob {

    private static final Log LOG = LogFactory.getLog(CitiJobImpl.class);
    private static final String KC_SYSTEM_PRINCIPAL_NM = "kc";

    @Autowired
    @Qualifier("citiDataProcessingService")
    private CitiDataProcessingService citiDataProcessingService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Override
    public void execute() {
        LOG.info("Starting CITI job");

        getGlobalVariableService().doInNewGlobalVariables(new UserSession(KC_SYSTEM_PRINCIPAL_NM), () -> {
            getCitiDataProcessingService().processRecords();
            return null;
        });

        LOG.info("Finished CITI job");
    }

    public CitiDataProcessingService getCitiDataProcessingService() {
        return citiDataProcessingService;
    }

    public void setCitiDataProcessingService(CitiDataProcessingService citiDataProcessingService) {
        this.citiDataProcessingService = citiDataProcessingService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }
}
