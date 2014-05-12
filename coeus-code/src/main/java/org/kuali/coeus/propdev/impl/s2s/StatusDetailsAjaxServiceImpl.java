package org.kuali.coeus.propdev.impl.s2s;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.s2s.service.S2SService;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("statusDetailsAjaxService")
public class StatusDetailsAjaxServiceImpl implements StatusDetailsAjaxService {

    private static final Log LOG = LogFactory.getLog(StatusDetailsAjaxServiceImpl.class);

    @Autowired
    @Qualifier("s2SService")
    private S2SService s2SService;

    public String getStatusDetails(String ggTrackingId, String proposalNumber) {
        if(isAuthorizedToAccess(proposalNumber)){
            return s2SService.getStatusDetails(ggTrackingId, proposalNumber);
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
            if (GlobalVariables.getUserSession() != null) {
                String[] invalues = StringUtils.split(proposalNumber, Constants.COLON);
                String docFormKey = invalues[1];
                if (StringUtils.isBlank(docFormKey)) {
                    isAuthorized = false;
                } else {
                    Object formObj = GlobalVariables.getUserSession().retrieveObject(docFormKey);
                    if (formObj == null || !(formObj instanceof ProposalDevelopmentForm)) {
                        isAuthorized = false;
                    } else {
                        Map<String, String> editModes = ((ProposalDevelopmentForm)formObj).getEditingMode();
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

    public S2SService getS2SService() {
        return s2SService;
    }

    public void setS2SService(S2SService s2SService) {
        this.s2SService = s2SService;
    }
}
