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
package co.kuali.coeus.reporting.web.struts.action;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.common.framework.auth.UnitAuthorizationService;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.exception.AuthorizationException;

/**
 * 
 * This class represents the Struts Action for Commitments page(AwardCommitments.jsp)
 */
public class ReportForwardAction extends KualiDocumentActionBase {

    private static final String Y = "Y";
	private static final String N = "N";
	private static Log LOG = LogFactory.getLog(ReportForwardAction.class);
    private static final String URL_BASE = "rsmart.report.url.base";
    private static final String QUERY_BASE = "rsmart.report.query.base";
    private static final String ADHOC_LIST_QUERY = "rsmart.report.adhoc.list.query";
    private static final String CLUSTER_ID_VAR = "RSMART_CLUSTER";
    
    private DataObjectService dataObjectService;
    private Object tokenURLGenerator;
    private UnitAuthorizationService unitAuthorizationService;
    private GlobalVariableService globalVariableService;
    private UnitService unitService;
    private SystemAuthorizationService systemAuthorizationService;
    private RoleService roleService;

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
        
        String currentUserName = getGlobalVariableService().getUserSession().getPrincipalName();
        String currentUserId = getGlobalVariableService().getUserSession().getPrincipalId();
        String clientId = getClientId(request);
        String reportId = request.getParameter("reportId");
        
        if (!getUnitAuthorizationService().hasPermission(currentUserId, RoleConstants.DEPARTMENT_ROLE_TYPE, PermissionConstants.RUN_GLOBAL_REPORTS)) {
        	throw new AuthorizationException(currentUserName, "Run", "Reports");
        }

        boolean isPI = isPrincipalInvestigator(currentUserName);
        String unitPermissions = getUnitQualifiersForGlobalReports(currentUserId).entrySet().stream()
        		.map(entry -> { return entry.getKey() + ":" + entry.getValue(); })
        		.collect(Collectors.joining(","));

        String urlBase = getKualiConfigurationService().getPropertyValueAsString(URL_BASE);
        String queryBase = getKualiConfigurationService().getPropertyValueAsString(QUERY_BASE);
        String adHocView = getKualiConfigurationService().getPropertyValueAsString(ADHOC_LIST_QUERY);
        String credentials[] = new String[] {currentUserName, Boolean.toString(isPI), clientId, unitPermissions};
        final String url;

        final String query;
        if (StringUtils.isNotBlank(reportId)) {
        	query = queryBase + reportId;
        } else {
        	query = adHocView;
        }
        LOG.debug("generating absolute URL");
        final Method generateAbsoluteURL = getTokenURLGenerator().getClass().getMethod("generateAbsoluteURL", String.class, String.class, String[].class);
        url = (String) generateAbsoluteURL.invoke(getTokenURLGenerator(), urlBase, query, credentials);
        
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

    protected Map<String, String> getUnitQualifiersForGlobalReports(String userId) {
	    Map<String, String> qualifiedRoleAttributes = new HashMap<>();
	    qualifiedRoleAttributes.put(KcKimAttributes.UNIT_NUMBER, "*");
	    Map<String,String> qualification =new HashMap<>(qualifiedRoleAttributes);
	    final Set<String> roleIds = new HashSet<>(getSystemAuthorizationService().getRoleIdsForPermission(PermissionConstants.RUN_GLOBAL_REPORTS, RoleConstants.DEPARTMENT_ROLE_TYPE));

	    Set<Map<String,String>> qualifiers = new HashSet<>(getRoleService().getNestedRoleQualifiersForPrincipalByRoleIds(userId, new ArrayList<>(roleIds), qualification));
	
	    return qualifiers.stream().collect(Collectors.toMap(qualifier -> {
	    	if (qualifier.containsKey(KcKimAttributes.UNIT_NUMBER)) {
	    		return qualifier.get(KcKimAttributes.UNIT_NUMBER);
	    	} else {
	    		return getUnitService().getTopUnit().getUnitNumber();
	    	}
	    }, qualifier -> {
	    	if (qualifier.containsKey(KcKimAttributes.UNIT_NUMBER) && qualifier.containsKey(KcKimAttributes.SUBUNITS)) {
	    		return qualifier.get(KcKimAttributes.SUBUNITS).substring(0, 1).toUpperCase();
	    	} else if (qualifier.containsKey(KcKimAttributes.UNIT_NUMBER)) {
	    		return N;
	    	} else {
	    		return Y;
	    	}
	    }, (v1, v2) -> v2));
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

	public UnitAuthorizationService getUnitAuthorizationService() {
		if (unitAuthorizationService == null) {
			unitAuthorizationService = KcServiceLocator.getService(UnitAuthorizationService.class);
		}
		return unitAuthorizationService;
	}

	public void setUnitAuthorizationService(UnitAuthorizationService unitAuthorizationService) {
		this.unitAuthorizationService = unitAuthorizationService;
	}
	
	public UnitService getUnitService() {
		if (unitService == null) {
			unitService = KcServiceLocator.getService(UnitService.class);
		}
		return unitService;
	}

	public void setUnitService(UnitService unitService) {
		this.unitService = unitService;
	}

	public SystemAuthorizationService getSystemAuthorizationService() {
		if (systemAuthorizationService == null) {
			systemAuthorizationService = KcServiceLocator.getService(SystemAuthorizationService.class);
		}
		return systemAuthorizationService;
	}

	public void setSystemAuthorizationService(
			SystemAuthorizationService systemAuthorizationService) {
		this.systemAuthorizationService = systemAuthorizationService;
	}

	public RoleService getRoleService() {
		if (roleService == null) {
			roleService = KcServiceLocator.getService(RoleService.class);
		}
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
}
