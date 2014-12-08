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
package com.rsmart.kuali.kra.reporting.web.struts.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.kra.infrastructure.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase;
import org.kuali.rice.krad.util.GlobalVariables;

import com.rsmart.rfabric.auth.tokenauth.AuthTokenGenerator;
import com.rsmart.rfabric.auth.tokenauth.AuthTokenURLGenerator;

/**
 * 
 * This class represents the Struts Action for Commitments page(AwardCommitments.jsp)
 */
public class ReportForwardAction extends KualiDocumentActionBase {

    private static Log LOG = LogFactory.getLog(ReportForwardAction.class);
    private static final String URL_RELATIVE = "rsmart.report.url.relative";
    private static final String URL_BASE = "rsmart.report.url.base";
    private static final String QUERY_BASE = "rsmart.report.query.base";
    private static final String SHARED_SECRET = "rsmart.report.shared.secret";
    private static final String CLUSTER_ID_VAR = "RSMART_CLUSTER";
    
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
        
        String currentUserId = GlobalVariables.getUserSession().getPrincipalName();
        String clientId = getClientId(request);
        String awardId = request.getParameter("awardId");
        AuthTokenGenerator tokenGenerator = new AuthTokenGenerator();
        
        String sharedSecret = ConfigContext.getCurrentContextConfig().getProperty(SHARED_SECRET);
        tokenGenerator.setSecret(sharedSecret);
        
        AuthTokenURLGenerator tokenURLGenerator = new AuthTokenURLGenerator();
        tokenURLGenerator.setTokenGenerator(tokenGenerator);
//        AuthTokenURLGenerator tokenURLGenerator = (AuthTokenURLGenerator) KraServiceLocator.getAppContext().getBean("urlGenerator");
        boolean isPI = isPrincipalInvestigator(currentUserId);

        String urlRelative = ConfigContext.getCurrentContextConfig().getProperty(URL_RELATIVE);
        String urlBase = ConfigContext.getCurrentContextConfig().getProperty(URL_BASE);
        String queryBase = ConfigContext.getCurrentContextConfig().getProperty(QUERY_BASE);
        String credentials[] = new String[] {currentUserId, Boolean.toString(isPI), clientId};
        String url = null;
        
        if (Boolean.parseBoolean(urlRelative)) {
          LOG.debug("generating relative URL");
          url = tokenURLGenerator.generateRelativeURL(request, urlBase, queryBase + awardId, credentials);
        } else {
          LOG.debug("generating absolute URL");
          url = tokenURLGenerator.generateAbsoluteURL(urlBase, queryBase + awardId, credentials);
        }
        
        LOG.debug("redirecting to url \"" + url + "\"");
        response.sendRedirect(url);
        return null;
    }    
  
    private boolean isPrincipalInvestigator(String principalId) {
        Map<String, String> proposalKeys = new HashMap<String, String>();
        proposalKeys.put("personId", principalId);
        proposalKeys.put("proposalPersonRoleId", Constants.PRINCIPAL_INVESTIGATOR_ROLE);
          
        // TODO verify this is the correct implementation for KC6.
        List<ProposalPerson> proposalPersons = (List<ProposalPerson>) getBusinessObjectService().findMatching(ProposalPerson.class, proposalKeys);
        return (proposalPersons != null && proposalPersons.size() > 0);
//        if (proposalPersons != null && proposalPersons.size() > 0) {
//            return true;
//        }
//        
//        Map<String, String> awardKeys = new HashMap<String, String>();
//        awardKeys.put("personId", principalId);
//        awardKeys.put("proposalPersonRoleId", Constants.PRINCIPAL_INVESTIGATOR_ROLE);
//
//        List<AwardPerson> awardPersons = (List<AwardPerson>) getBusinessObjectService().findMatching(AwardPerson.class, awardKeys);
//
//        return (awardPersons != null && awardPersons.size() > 0);
    }

    
}
