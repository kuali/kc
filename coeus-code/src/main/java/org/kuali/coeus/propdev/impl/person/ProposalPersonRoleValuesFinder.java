/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.propdev.impl.person;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.person.PropAwardPersonRole;
import org.kuali.coeus.common.framework.person.PropAwardPersonRoleService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;
import org.kuali.rice.krad.uif.field.InputField;
import org.kuali.rice.krad.uif.view.ViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.kuali.kra.infrastructure.Constants.*;

public class ProposalPersonRoleValuesFinder extends UifKeyValuesFinderBase {

    private static final Log LOG = LogFactory.getLog(ProposalPersonRoleValuesFinder.class);
    private PropAwardPersonRoleService propAwardPersonRoleService;
    
    public ProposalPersonRoleValuesFinder() {
    	super();
    	setAddBlankOption(false);
    }
    
    @Override
    public List<KeyValue> getKeyValues(ViewModel model, InputField field){
        return getKeyValues(((ProposalDevelopmentDocumentForm) model).getProposalDevelopmentDocument());
    }
    
    public List<KeyValue> getKeyValues(ProposalDevelopmentDocument document) {
        Collection<PropAwardPersonRole> roles = new ArrayList<PropAwardPersonRole>();
        roles.addAll(getPropAwardPersonRoleDao().getRolesByHierarchy(document.getDevelopmentProposal().getSponsorCode()));
        final DevelopmentProposal developmentProposal = document.getDevelopmentProposal();

        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        addKeyValue(keyValues, roles, PropAwardPersonRole.PRINCIPAL_INVESTIGATOR, developmentProposal);
        addKeyValue(keyValues, roles, PropAwardPersonRole.MULTI_PI, developmentProposal);
        addKeyValue(keyValues, roles, PropAwardPersonRole.CO_INVESTIGATOR, developmentProposal);
        addKeyValue(keyValues, roles, PropAwardPersonRole.KEY_PERSON, developmentProposal);
        for (PropAwardPersonRole role : roles) {
            addKeyValue(keyValues, role, developmentProposal);
        }
        return keyValues;
    }
    
    protected void addKeyValue(List<KeyValue> keyValues, Collection<PropAwardPersonRole> roles, String roleId, DevelopmentProposal developmentProposal) {
        PropAwardPersonRole curRole = getRoleById(roles, roleId);
        if (curRole != null) {
            addKeyValue(keyValues, curRole, developmentProposal);
            roles.remove(curRole);
        }
    }
    
    protected void addKeyValue(List<KeyValue> keyValues, PropAwardPersonRole role, DevelopmentProposal developmentProposal) {
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
	
	protected PropAwardPersonRoleService getPropAwardPersonRoleDao() {
		if (propAwardPersonRoleService == null) {
			propAwardPersonRoleService = KcServiceLocator.getService(PropAwardPersonRoleService.class);
		}
		return propAwardPersonRoleService;
	}

	public void setPropAwardPersonRoleDao(
			PropAwardPersonRoleService propAwardPersonRoleService) {
		this.propAwardPersonRoleService = propAwardPersonRoleService;
	}
}
