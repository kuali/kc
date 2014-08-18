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
package org.kuali.kra.protocol.permission;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.permissions.impl.web.bean.Role;
import org.kuali.coeus.common.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.rice.core.api.criteria.Predicate;
import org.kuali.rice.core.api.criteria.PredicateFactory;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kim.api.permission.PermissionQueryResults;
import org.kuali.rice.kim.api.type.KimType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * The PermissionsHelperBase is used to manage the Permissions tab web page.
 * It contains the data, forms, and methods needed to render the page.
 */
public abstract class PermissionsHelperBase extends org.kuali.coeus.common.permissions.impl.web.struts.form.PermissionsHelperBase {
    

    private static final long serialVersionUID = 5896277052902587682L;
    
    protected static final String AGGREGATOR_NAME = "Aggregator";
    protected static final String VIEWER_NAME = "Viewer";
    protected static final String UNASSIGNED_NAME = "unassigned";

    //A collection of role names within the namespace that should not be assignable 
    //in the permissions page.
    protected Collection<String> excludeRoles;
    
    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the document.
     */
    protected ProtocolFormBase form;
    
    /**
     * Stores mapping of role names to display names.
     */
    protected Map<String, String> displayNameMap = null;
    
    private String roleType;

    /**
     * Constructs a PermissionsHelperBase.
     * @param form the form
     */
    public PermissionsHelperBase(ProtocolFormBase form, String roleType) {
        super(roleType);
        this.roleType = roleType;
        this.form = form;        
    }
    
    /**
     * 
     * This method initializes excluded role names within the namespace that should not be assignable 
     * in the permissions page.
     */
    protected abstract void initExcludedRolesHook();

    /*
     * Build the mapping of role names to display.
     */
    protected abstract void buildDisplayNameMap();

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
        addNonDerivedRoles(keyValues);
        return keyValues;
    }
    
    
    protected void addNonDerivedRoles(List<KeyValue> keyValues) {
        List<org.kuali.rice.kim.api.role.Role> kimRoles = getSortedKimRoles(roleType);
        for (org.kuali.rice.kim.api.role.Role kimRole : kimRoles) {
            String roleName = kimRole.getName();
            if (!excludeRoles.contains(roleName) && (!displayNameMap.keySet().contains(roleName)) ) {
                KimType type = getSystemAuthorizationService().getKimTypeInfoForRole(kimRole);
                // filter out derived roles and default type roles
                if (!(StringUtils.startsWith(type.getName(), "Derived Role")) && !(StringUtils.startsWith(type.getName(), "Default"))) {
                    KeyValue pair = new ConcreteKeyValue(roleName, roleName);    
                    keyValues.add(pair);
                }
            }
        }
    }
       
    
    protected SystemAuthorizationService getSystemAuthorizationService() {
        return KcServiceLocator.getService(SystemAuthorizationService.class);
    }           
            
  
    /*
     * Get the ProtocolBase.
     */
    protected ProtocolBase getProtocol() {
        ProtocolDocumentBase document = form.getProtocolDocument();
        if (document == null || document.getProtocol() == null) {
            throw new IllegalArgumentException("invalid (null) ProtocolDocumentBase in ProtocolFormBase");
        }
        return document.getProtocol();
    }

    
    @Override
    public String getUnassignedRoleName() {
        return RoleConstants.PROTOCOL_UNASSIGNED;
    }
    
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
        initExcludedRolesHook();

        List<Role> roles = new ArrayList<Role>();
        List<org.kuali.rice.kim.api.role.Role> kimRoles = getSortedKimRoles(roleType);
        for (org.kuali.rice.kim.api.role.Role kimRole : kimRoles) {
            
            if (!excludeRoles.contains(kimRole.getName()) ) {
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

    
    @Override
    protected List<KcPerson> getPersonsInRole(String roleName) {
        KcAuthorizationService kraAuthorizationService = KcServiceLocator.getService(KcAuthorizationService.class);
        KcPersonService kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        List<String> users = kraAuthorizationService.getPrincipalsInRole(getProtocol(), roleName);

        final List<KcPerson> persons = new ArrayList<KcPerson>();
        for(String userId : users) {
            KcPerson person = kcPersonService.getKcPersonByPersonId(userId);
            if (person != null && person.getActive()) {
                persons.add(person);
            }
        }

        return persons;
    }
    
    public String getRoleType() {
        return roleType;
    }

}
