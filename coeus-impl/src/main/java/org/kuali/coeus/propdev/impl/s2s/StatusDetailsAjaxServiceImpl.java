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

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("statusDetailsAjaxService")
public class StatusDetailsAjaxServiceImpl implements StatusDetailsAjaxService {

    private static final Log LOG = LogFactory.getLog(StatusDetailsAjaxServiceImpl.class);

    @Autowired
    @Qualifier("s2sSubmissionService")
    private S2sSubmissionService s2sSubmissionService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    public String getStatusDetails(String ggTrackingId, String proposalNumber) {
        if(isAuthorizedToAccess(proposalNumber)){
            return s2sSubmissionService.getStatusDetails(ggTrackingId, proposalNumber);
        }
        return StringUtils.EMPTY;
    }

        /*
     * a utility method to check if dwr/ajax call really has authorization
     * 'updateProtocolFundingSource' also accessed by non ajax call
     */

    private boolean isAuthorizedToAccess(String proposalNumber) {
        boolean isAuthorized = true;
        if(proposalNumber.contains(Constants.COLON)){
            if (globalVariableService.getUserSession() != null) {
                String[] invalues = StringUtils.split(proposalNumber, Constants.COLON);
                String docFormKey = invalues[1];
                if (StringUtils.isBlank(docFormKey)) {
                    isAuthorized = false;
                } else {
                    Object formObj = globalVariableService.getUserSession().retrieveObject(docFormKey);
                    if (formObj == null || !(formObj instanceof ProposalDevelopmentDocumentForm)) {
                        isAuthorized = false;
                    } else {
                        Map<String, Boolean> editModes = ((ProposalDevelopmentDocumentForm)formObj).getEditModes();
                        isAuthorized = (BooleanUtils.toBoolean(editModes.get(AuthorizationConstants.EditMode.FULL_ENTRY))
                                || BooleanUtils.toBoolean(editModes.get(AuthorizationConstants.EditMode.VIEW_ONLY))
                                || BooleanUtils.toBoolean(editModes.get("modifyProposal")))
                                && BooleanUtils.toBoolean(editModes.get("submitToSponsor"));
                    }
                }
            } else {
                LOG.info("dwr/ajax does not have session ");
            }
        }
        return isAuthorized;
    }

    public S2sSubmissionService getS2sSubmissionService() {
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
