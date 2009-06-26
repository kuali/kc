/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.permissions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.document.authorization.AwardTask;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.Person;
import org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase;
import org.kuali.kra.infrastructure.AwardRoleConstants;
import org.kuali.kra.infrastructure.AwardTaskNames;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.service.KraAuthorizationService;

/**
 * The PermissionsHelper is used to manage the Permissions tab web page.
 * It contains the data, forms, and methods needed to render the page.
 */
public class PermissionsHelper extends PermissionsHelperBase {
    
    private static final String AGGREGATOR_NAME = "Aggregator";
    private static final String VIEWER_NAME = "Viewer";
    private static final String UNASSIGNED_NAME = "unassigned";
    
    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the document.
     */
    private AwardForm form;
    
    /**
     * Stores mapping of role names to display names.
     */
    private Map<String, String> displayNameMap = null;
    
    /**
     * Constructs a PermissionsHelper.
     * @param form the form
     */
    public PermissionsHelper(AwardForm form) {
        super(RoleConstants.AWARD_ROLE_TYPE);
        this.form = form;
    }   

    /*
     * Build the mapping of role names to display.
     */
    private void buildDisplayNameMap() {
        if (displayNameMap == null) {
            displayNameMap = new HashMap<String, String>();
            displayNameMap.put(AwardRoleConstants.AWARD_MODIFIER.getAwardRole(), AGGREGATOR_NAME);
            displayNameMap.put(AwardRoleConstants.AWARD_VIEWER.getAwardRole(), VIEWER_NAME);
            displayNameMap.put(AwardRoleConstants.AWARD_UNASSIGNED.getAwardRole(), UNASSIGNED_NAME);
        }
    }

    /*
     * Get the Award.
     */
    private Award getAward() {
        AwardDocument document = form.getAwardDocument();
        if (document == null || document.getAward() == null) {
            throw new IllegalArgumentException("invalid (null) AWARDDocument in AWARDForm");
        }
        return document.getAward();
    }
    
    /**
     * @see org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase#getUnassignedRoleName()
     */
    @Override
    public String getUnassignedRoleName() {
        return AwardRoleConstants.AWARD_UNASSIGNED.getAwardRole();
    }

    /**
     * @see org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase#isStandardRoleName(java.lang.String)
     */
    @Override
    protected boolean isStandardRoleName(String roleName) {
        return StringUtils.equals(roleName, AwardRoleConstants.AWARD_MODIFIER.getAwardRole()) 
                || StringUtils.equals(roleName, AwardRoleConstants.AWARD_VIEWER.getAwardRole());
    }
    
    /**
     * @see org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase#getRoleDisplayName(java.lang.String)
     */
    @Override
    protected String getRoleDisplayName(String roleName) {
        buildDisplayNameMap();
        String displayName = displayNameMap.get(roleName);
        if (displayName == null) {
            displayName = roleName;
        }
        return displayName;
    }
    
    /**
     * @see org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase#getPersonsInRole(java.lang.String)
     */
    @Override
    protected List<Person> getPersonsInRole(String roleName) {
        KraAuthorizationService kraAuthService = KraServiceLocator.getService(KraAuthorizationService.class);
        return kraAuthService.getPersonsInRole(getAward(), roleName);
    }

    /**
     * @see org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase#canModifyPermissions()
     */
    @Override
    public boolean canModifyPermissions() {
        AwardTask task = new AwardTask(AwardTaskNames.MODIFY_AWARD_ROLES.getAwardTaskName(), getAward());
        return getTaskAuthorizationService().isAuthorized(getUserName(), task);
    }
}
