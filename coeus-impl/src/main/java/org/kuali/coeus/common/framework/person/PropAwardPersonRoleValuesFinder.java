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
package org.kuali.coeus.common.framework.person;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;
import org.kuali.rice.krad.uif.field.InputField;
import org.kuali.rice.krad.uif.view.ViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class PropAwardPersonRoleValuesFinder extends UifKeyValuesFinderBase {

    private static final Log LOG = LogFactory.getLog(PropAwardPersonRoleValuesFinder.class);
    private PropAwardPersonRoleService propAwardPersonRoleService;
    
    public PropAwardPersonRoleValuesFinder() {
    	super();
    	setAddBlankOption(false);
    }
    
    protected abstract String getSponsorCodeFromModel(ViewModel model);
    
    @Override
    public List<KeyValue> getKeyValues(ViewModel model, InputField field){
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.addAll(getKeyValues(getSponsorCodeFromModel(model)));
        if (piAlreadyExists(model,field)) {
            for (KeyValue keyValue : getKeyValues(getSponsorCodeFromModel(model))) {
                if (keyValue.getKey().equals(PropAwardPersonRole.PRINCIPAL_INVESTIGATOR)) {
                    keyValues.remove(keyValue);
                }
            }
        }
        return keyValues;
    }

    public List<KeyValue> getKeyValues(String sponsorCode) {
        Collection<PropAwardPersonRole> roles = new ArrayList<PropAwardPersonRole>();
        roles.addAll(getPropAwardPersonRoleService().getRolesByHierarchy(sponsorCode));

        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        addKeyValue(keyValues, roles, PropAwardPersonRole.PRINCIPAL_INVESTIGATOR);
        addKeyValue(keyValues, roles, PropAwardPersonRole.MULTI_PI);
        addKeyValue(keyValues, roles, PropAwardPersonRole.CO_INVESTIGATOR);
        addKeyValue(keyValues, roles, PropAwardPersonRole.KEY_PERSON);
        for (PropAwardPersonRole role : roles) {
            addKeyValue(keyValues, role);
        }
        return keyValues;
    }
    
    protected void addKeyValue(List<KeyValue> keyValues, Collection<PropAwardPersonRole> roles, String roleId) {
        PropAwardPersonRole curRole = getRoleById(roles, roleId);
        if (curRole != null) {
            addKeyValue(keyValues, curRole);
            roles.remove(curRole);
        }
    }
    
    protected void addKeyValue(List<KeyValue> keyValues, PropAwardPersonRole role) {
        if (role != null) {
            keyValues.add(new ConcreteKeyValue(role.getCode(), role.getDescription()));
        }
    }
    
    protected PropAwardPersonRole getRoleById(Collection<PropAwardPersonRole> roles, String roleId) {
        for (PropAwardPersonRole role : roles) {
            if (StringUtils.equals(role.getCode(), roleId)) {
                return role;
            }
        }
        return null;
    }
	
	protected PropAwardPersonRoleService getPropAwardPersonRoleService() {
		if (propAwardPersonRoleService == null) {
			propAwardPersonRoleService = KcServiceLocator.getService(PropAwardPersonRoleService.class);
		}
		return propAwardPersonRoleService;
	}

    protected abstract boolean piAlreadyExists(ViewModel model, InputField field);

	public void setPropAwardPersonRoleService(
			PropAwardPersonRoleService propAwardPersonRoleService) {
		this.propAwardPersonRoleService = propAwardPersonRoleService;
	}
}
