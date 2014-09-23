package org.kuali.kra.coi.permission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.permissions.impl.web.struts.form.PermissionsHelperBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureForm;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.rice.krad.util.GlobalVariables;

public class PermissionHelper extends PermissionsHelperBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4240749393003506333L;
	
	private CoiDisclosureForm form;
	
	private transient KcAuthorizationService kraAuthService;
	private transient KcPersonService kcPersonService;
	
	public PermissionHelper(CoiDisclosureForm form) {
		super(RoleConstants.COI_DISCLOSURE_ROLE_TYPE);
		this.form = form;
	}
	
	public CoiDisclosure getDisclosure() {
		return this.form.getCoiDisclosureDocument().getCoiDisclosure();
	}

	@Override
	public boolean canModifyPermissions() {
		return getKraAuthService().hasPermission(GlobalVariables.getUserSession().getPrincipalId(), getDisclosure(), RoleConstants.COI_ADMINISTRATOR);
	}

	@Override
	protected boolean isStandardRoleName(String roleName) {
		return StringUtils.equalsIgnoreCase(roleName, RoleConstants.COI_ADMINISTRATOR) || 
				StringUtils.equalsIgnoreCase(roleName, RoleConstants.COI_REVIEWER);
	}

	@Override
	protected List<KcPerson> getPersonsInRole(String roleName) {
        List<String> users = getKraAuthService().getPrincipalsInRole(roleName, getDisclosure());
        final List<KcPerson> persons = new ArrayList<KcPerson>();
        for(String userId : users) {
            KcPerson person = getKcPersonService().getKcPersonByPersonId(userId);
            if (person != null && person.getActive()) {
                persons.add(person);
            }
        }

        return persons;
	}

	public KcAuthorizationService getKraAuthService() {
		if (kraAuthService == null) {
			kraAuthService = KcServiceLocator.getService(KcAuthorizationService.class);
		}
		return kraAuthService;
	}

	public void setKraAuthService(KcAuthorizationService kraAuthService) {
		this.kraAuthService = kraAuthService;
	}

	public KcPersonService getKcPersonService() {
		if (kcPersonService == null) {
			kcPersonService = KcServiceLocator.getService(KcPersonService.class);
		}
		return kcPersonService;
	}

	public void setKcPersonService(KcPersonService kcPersonService) {
		this.kcPersonService = kcPersonService;
	}

}
