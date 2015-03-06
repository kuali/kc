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
package co.kuali.coeus.reporting.web.struts.action;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase;
import org.kuali.rice.krad.data.DataObjectService;

/**
 * 
 * This class represents the Struts Action for Commitments page(AwardCommitments.jsp)
 */
public class ReportForwardAction extends KualiDocumentActionBase {

    private static Log LOG = LogFactory.getLog(ReportForwardAction.class);
    private static final String URL_RELATIVE = "rsmart.report.url.relative";
    private static final String URL_BASE = "rsmart.report.url.base";
    private static final String QUERY_BASE = "rsmart.report.query.base";
    private static final String CLUSTER_ID_VAR = "RSMART_CLUSTER";
    
    private DataObjectService dataObjectService;
    private Object tokenURLGenerator;
    private GlobalVariableService globalVariableService;

	protected String getClientId(final HttpServletRequest request) {
      String clientId = System.getenv(CLUSTER_ID_VAR);
      
      if (clientId == null) {
        clientId = request.getServerName();
        LOG.debug("Client ID could not be found in env. vars., using server name: " + clientId);
      } else {
        LOG.debug("Client ID is: " + clientId); 
      }
      
      return clientId;
    }
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        String currentUserId = getGlobalVariableService().getUserSession().getPrincipalName();
        String clientId = getClientId(request);
        String awardId = request.getParameter("awardId");

        boolean isPI = isPrincipalInvestigator(currentUserId);

        String urlRelative = getKualiConfigurationService().getPropertyValueAsString(URL_RELATIVE);
        String urlBase = getKualiConfigurationService().getPropertyValueAsString(URL_BASE);
        String queryBase = getKualiConfigurationService().getPropertyValueAsString(QUERY_BASE);
        String credentials[] = new String[] {currentUserId, Boolean.toString(isPI), clientId};
        final String url;
        
        if (Boolean.parseBoolean(urlRelative)) {
            LOG.debug("generating relative URL");
            final Method generateRelativeURL = getTokenURLGenerator().getClass().getMethod("tokenURLGenerator", HttpServletRequest.class, String.class, String.class, String[].class);
            url = (String) generateRelativeURL.invoke(getTokenURLGenerator(), request, urlBase, queryBase + awardId, credentials);
        } else {
            LOG.debug("generating absolute URL");
            final Method generateAbsoluteURL = getTokenURLGenerator().getClass().getMethod("generateAbsoluteURL", String.class, String.class, String[].class);
            url = (String) generateAbsoluteURL.invoke(getTokenURLGenerator(), urlBase, queryBase + awardId, credentials);
        }
        
        LOG.debug("redirecting to url \"" + url + "\"");
        response.sendRedirect(url);
        return null;
    }    
  
    private boolean isPrincipalInvestigator(String principalId) {
        Map<String, String> proposalKeys = new HashMap<String, String>();
        proposalKeys.put("personId", principalId);
        proposalKeys.put("proposalPersonRoleId", Constants.PRINCIPAL_INVESTIGATOR_ROLE);

        List<ProposalPerson> proposalPersons = getDataObjectService().findMatching(ProposalPerson.class, QueryByCriteria.Builder.andAttributes(proposalKeys).build()).getResults();
        return (proposalPersons != null && proposalPersons.size() > 0);
    }
    
    public DataObjectService getDataObjectService() {
		if (dataObjectService == null) {
            dataObjectService = KcServiceLocator.getService(DataObjectService.class);
        }

        return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}

    public GlobalVariableService getGlobalVariableService() {
        if (globalVariableService == null) {
            globalVariableService = KcServiceLocator.getService(GlobalVariableService.class);
        }

        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public Object getTokenURLGenerator() {
        if (tokenURLGenerator == null) {
            tokenURLGenerator = KcServiceLocator.getService("urlGenerator");
        }

        return tokenURLGenerator;
    }

    public void setTokenURLGenerator(Object tokenURLGenerator) {
        this.tokenURLGenerator = tokenURLGenerator;
    }
}
