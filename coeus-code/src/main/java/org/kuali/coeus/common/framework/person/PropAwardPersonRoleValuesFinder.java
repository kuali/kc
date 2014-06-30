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
package org.kuali.coeus.common.framework.person;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.kuali.kra.infrastructure.Constants.*;

public abstract class PropAwardPersonRoleValuesFinder extends FormViewAwareUifKeyValuesFinderBase {

    private static final Log LOG = LogFactory.getLog(PropAwardPersonRoleValuesFinder.class);
    private PropAwardPersonRoleService propAwardPersonRoleService;
    
    public PropAwardPersonRoleValuesFinder() {
    	super();
    	setAddBlankOption(false);
    }
    
    protected abstract String getSponsorCodeFromModel(ViewModel model);

    @Override
    public List<KeyValue> getKeyValues(){
        return getKeyValues(getSponsorCode());
    }
    
    @Override
    public List<KeyValue> getKeyValues(ViewModel model, InputField field){
        return getKeyValues(getSponsorCodeFromModel(model));
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
            keyValues.add(new ConcreteKeyValue(role.getId().toString(), role.getDescription()));
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

	public void setPropAwardPersonRoleService(
			PropAwardPersonRoleService propAwardPersonRoleService) {
		this.propAwardPersonRoleService = propAwardPersonRoleService;
	}
	
    /**
     * A temporary solution until we move IP and Award to KRAD
     * @return
     */
    protected String getSponsorCode() {
    	String sponsorCode = null;
    	if(getDocument() instanceof InstitutionalProposalDocument) {
        	InstitutionalProposal institutionalProposal = ((InstitutionalProposalDocument)getDocument()).getInstitutionalProposal();
        	sponsorCode = institutionalProposal.getSponsorCode();
    	}else {
        	Award award = ((AwardDocument)getDocument()).getAward();
        	sponsorCode = award.getSponsorCode();
    	}
        return sponsorCode;
    }

}
