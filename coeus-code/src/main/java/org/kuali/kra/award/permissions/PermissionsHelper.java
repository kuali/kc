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
package org.kuali.kra.award.permissions;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.permissions.impl.web.struts.form.PermissionsHelperBase;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.document.authorization.AwardTask;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.infrastructure.AwardRoleConstants;
import org.kuali.kra.award.infrastructure.AwardTaskNames;
import org.kuali.kra.infrastructure.RoleConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    @Override
    public String getUnassignedRoleName() {
        return AwardRoleConstants.AWARD_UNASSIGNED.getAwardRole();
    }

    @Override
    protected boolean isStandardRoleName(String roleName) {
        return StringUtils.equals(roleName, AwardRoleConstants.AWARD_MODIFIER.getAwardRole()) 
                || StringUtils.equals(roleName, AwardRoleConstants.AWARD_VIEWER.getAwardRole());
    }
    
    @Override
    protected String getRoleDisplayName(String roleName) {
        buildDisplayNameMap();
        String displayName = displayNameMap.get(roleName);
        if (displayName == null) {
            displayName = roleName;
        }
        return displayName;
    }
    
    @Override
    protected List<KcPerson> getPersonsInRole(String roleName) {
        KcAuthorizationService kraAuthService = KcServiceLocator.getService(KcAuthorizationService.class);
        KcPersonService kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        List<String> users = kraAuthService.getPrincipalsInRole(getAward(), roleName);

        final List<KcPerson> persons = new ArrayList<KcPerson>();
        for(String userId : users) {
            KcPerson person = kcPersonService.getKcPersonByPersonId(userId);
            if (person != null && person.getActive()) {
                persons.add(person);
            }
        }

        return persons;
    }

    @Override
    public boolean canModifyPermissions() {
        AwardTask task = new AwardTask(AwardTaskNames.MODIFY_AWARD_ROLES.getAwardTaskName(), getAward());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    public boolean getMaintainAwardReportTracking() {
        AwardTask task = new AwardTask(AwardTaskNames.MAINTAIN_REPORT_TRACKING.getAwardTaskName(), getAward());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
}
