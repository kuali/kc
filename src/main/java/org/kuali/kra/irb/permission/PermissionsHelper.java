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
package org.kuali.kra.irb.permission;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.common.permissions.web.bean.Role;
import org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.core.api.criteria.Predicate;
import org.kuali.rice.core.api.criteria.PredicateFactory;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kim.api.permission.PermissionQueryResults;

/**
 * The PermissionsHelper is used to manage the Permissions tab web page.
 * It contains the data, forms, and methods needed to render the page.
 */
public class PermissionsHelper extends PermissionsHelperBase {
    
    private static final String AGGREGATOR_NAME = "Aggregator";
    private static final String VIEWER_NAME = "Viewer";
    private static final String UNASSIGNED_NAME = "unassigned";
    private static final Log LOG = LogFactory.getLog(PermissionsHelper.class);

    //A collection of role names within the namespace that should not be assignable 
    //in the permissions page.
    private static final Collection<String> excludeRoles;
    
    
    static {
        excludeRoles = new HashSet<String>();
        excludeRoles.add(RoleConstants.IRB_PROTOCOL_ONLINE_REVIEWER);
        excludeRoles.add(RoleConstants.ACTIVE_COMMITTEE_MEMBER);
        excludeRoles.add(RoleConstants.ACTIVE_COMMITTEE_MEMBER_SCHEDULED_DATE);
        excludeRoles.add(RoleConstants.PROTOCOL_APPROVER);
        excludeRoles.add(RoleConstants.ACTIVE_COMMITTEE_MEMBER_ON_PROTOCOL);
    }
    
    
    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the document.
     */
    private ProtocolForm form;
    
    /**
     * Stores mapping of role names to display names.
     */
    private Map<String, String> displayNameMap = null;
    
    /**
     * Constructs a PermissionsHelper.
     * @param form the form
     */
    public PermissionsHelper(ProtocolForm form) {
        super(RoleConstants.PROTOCOL_ROLE_TYPE);
        this.form = form;
    }   

    /*
     * Build the mapping of role names to display.
     */
    private void buildDisplayNameMap() {
        if (displayNameMap == null) {
            displayNameMap = new HashMap<String, String>();
            displayNameMap.put(RoleConstants.PROTOCOL_AGGREGATOR, AGGREGATOR_NAME);
            displayNameMap.put(RoleConstants.PROTOCOL_VIEWER, VIEWER_NAME);
            displayNameMap.put(RoleConstants.PROTOCOL_UNASSIGNED, UNASSIGNED_NAME);
        }
    }

    @Override
    /*
     * This is used to populate the drop down list in the users panel in the 
     * permissions tab.
     */
    public List<KeyValue> getRoleSelection() {
        buildDisplayNameMap();
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        for (String role : displayNameMap.keySet()) {           
            KeyValue pair = new ConcreteKeyValue(role, displayNameMap.get(role));    
            keyValues.add(pair);
        }
        return keyValues;
    }
    
    
    /*
     * Get the Protocol.
     */
    private Protocol getProtocol() {
        ProtocolDocument document = form.getDocument();
        if (document == null || document.getProtocol() == null) {
            throw new IllegalArgumentException("invalid (null) ProtocolDocument in ProtocolForm");
        }
        return document.getProtocol();
    }
    
    /**
     * @see org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase#getUnassignedRoleName()
     */
    @Override
    public String getUnassignedRoleName() {
        return RoleConstants.PROTOCOL_UNASSIGNED;
    }

    /**
     * @see org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase#isStandardRoleName(java.lang.String)
     */
    @Override
    protected boolean isStandardRoleName(String roleName) {
        return StringUtils.equals(roleName, RoleConstants.PROTOCOL_AGGREGATOR) ||
               StringUtils.equals(roleName, RoleConstants.PROTOCOL_VIEWER);
    }
    
    /**
     * @see org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase#getRoleDisplayName(java.lang.String)
     */
    @Override
    protected String getRoleDisplayName(String roleName) {
        buildDisplayNameMap();
        String displayName = displayNameMap.get(roleName);
        /*Without this code the values other than aggregator, 
         * viewer and unassigned do not appear in the assigned roles*/
        if (displayName == null) {
            displayName = roleName;
        }
        return displayName;
    }
    
    
    /*
     * Build the list of roles for the document.
     */
    @Override
    protected void buildRoles(String roleType) {
       
        List<Role> roles = new ArrayList<Role>();
        List<org.kuali.rice.kim.api.role.Role> kimRoles = getSortedKimRoles(roleType);
        for (org.kuali.rice.kim.api.role.Role kimRole : kimRoles) {
            
            if ( !excludeRoles.contains(kimRole.getName()) ) {
                QueryByCriteria.Builder queryBuilder = QueryByCriteria.Builder.create();
                List<Predicate> predicates = new ArrayList<Predicate>();
                predicates.add(PredicateFactory.equal("rolePermissions.roleId", kimRole.getId()));
                queryBuilder.setPredicates(PredicateFactory.and(predicates.toArray(new Predicate[] {})));
                PermissionQueryResults permissionResults = getKimPermissionService().findPermissions(queryBuilder.build());
                Role role = new Role(kimRole.getName(), getRoleDisplayName(kimRole.getName()), permissionResults.getResults());
                roles.add(role);
            }
        }
        setRoles(roles);
    }

    
    /**
     * @see org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase#getPersonsInRole(java.lang.String)
     */
    @Override
    protected List<KcPerson> getPersonsInRole(String roleName) {
        KraAuthorizationService kraAuthorizationService = KraServiceLocator.getService(KraAuthorizationService.class);
        return kraAuthorizationService.getPersonsInRole(getProtocol(), roleName);
    }

    /**
     * @see org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase#canModifyPermissions()
     */
    @Override
    public boolean canModifyPermissions() {
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_ROLES, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
}
