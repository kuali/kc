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
package org.kuali.coeus.propdev.impl.s2s;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.propdev.impl.s2s.connect.S2sCommunicationException;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component("s2sOpportunityLookupKradKnsHelperService")
public class S2sOpportunityLookupKradKnsHelperServiceImpl implements S2sOpportunityLookupKradKnsHelperService {

    private static final Log LOG = LogFactory.getLog(S2sOpportunityLookupKradKnsHelperServiceImpl.class);

    @Autowired
    @Qualifier("s2sSubmissionService")
    private S2sSubmissionService s2sSubmissionService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Override
    public List<S2sOpportunity> performSearch(String providerCode, String cfdaNumber, String opportunityId) {
        if (StringUtils.isBlank(providerCode)) {
            globalVariableService.getMessageMap().putError(Constants.PROVIDER_CODE, KeyConstants.ERROR_S2S_PROVIDER_INVALID);
        }

        if (StringUtils.isBlank(cfdaNumber) && StringUtils.isBlank(opportunityId)) {
            globalVariableService.getMessageMap().putError(Constants.NO_FIELD, KeyConstants.ERROR_IF_CFDANUMBER_AND_OPPORTUNITY_ID_IS_NULL);
        }

        if (globalVariableService.getMessageMap().hasNoErrors()) {
            List<S2sOpportunity> s2sOpportunity = new ArrayList<S2sOpportunity>();
            try {
                s2sOpportunity = getS2sSubmissionService().searchOpportunity(providerCode, cfdaNumber, opportunityId, "");
            }catch (S2sCommunicationException e) {
                LOG.error(e.getMessage(), e);
                getGlobalVariableService().getMessageMap().putError(Constants.NO_FIELD, e.getErrorKey(),e.getMessage());
                return Collections.emptyList();
            }
            if (s2sOpportunity != null && !s2sOpportunity.isEmpty()) {
                return s2sOpportunity;
            } else if (StringUtils.isNotBlank(cfdaNumber) && StringUtils.isNotBlank(opportunityId)) {
                try{
                    s2sOpportunity = getS2sSubmissionService().searchOpportunity(providerCode, cfdaNumber, "", "");
                }catch (S2sCommunicationException e) {
                    LOG.error(e.getMessage(), e);
                    getGlobalVariableService().getMessageMap().putError(Constants.NO_FIELD, e.getErrorKey(),e.getMessage());
                    return Collections.emptyList();
                }
                if (s2sOpportunity != null) {
                    return s2sOpportunity;
                } else{
                    if (StringUtils.isNotBlank(cfdaNumber)) {
                        getGlobalVariableService().getMessageMap().putError(Constants.CFDA_NUMBER, KeyConstants.ERROR_IF_CFDANUMBER_IS_INVALID);
                    }
                    if (StringUtils.isNotBlank(opportunityId)) {
                        getGlobalVariableService().getMessageMap().putError(Constants.OPPORTUNITY_ID,
                                KeyConstants.ERROR_IF_OPPORTUNITY_ID_IS_INVALID);
                    }
                }
                return Collections.emptyList();
            }
            return Collections.emptyList();
        }

        return Collections.emptyList();
    }

    protected S2sSubmissionService getS2sSubmissionService() {
        return s2sSubmissionService;
    }

    public void setS2sSubmissionService(S2sSubmissionService s2sSubmissionService) {
        this.s2sSubmissionService = s2sSubmissionService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }
}
