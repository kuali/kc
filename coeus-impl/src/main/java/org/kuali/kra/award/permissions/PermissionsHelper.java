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
        List<String> users = kraAuthService.getPrincipalsInRole(roleName, getAward());

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
