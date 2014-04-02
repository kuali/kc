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
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.kra.proposaldevelopment.web.krad.ProposalDevelopmentDocumentForm;
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
    private ParameterService parameterService;
    private DataObjectService dataObjectService;
    private KeyPersonnelService keyPersonnelService;
    
    public ProposalPersonRoleValuesFinder() {
    	super();
    	setAddBlankOption(false);
    }
    
    @Override
    public List<KeyValue> getKeyValues(ViewModel model, InputField field){
        return getKeyValues(((ProposalDevelopmentDocumentForm) model).getProposalDevelopmentDocument());
    }
    
    public List<KeyValue> getKeyValues(ProposalDevelopmentDocument document) {
        Collection<ProposalPersonRole> roles = new ArrayList<ProposalPersonRole>();
        roles.addAll(getDataObjectService().findMatching(ProposalPersonRole.class, QueryByCriteria.Builder.create().build()).getResults());
        final DevelopmentProposal developmentProposal = document.getDevelopmentProposal();

        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        addKeyValue(keyValues, roles, ProposalPersonRole.PRINCIPAL_INVESTIGATOR, developmentProposal);
        addKeyValue(keyValues, roles, ProposalPersonRole.CO_INVESTIGATOR, developmentProposal);
        addKeyValue(keyValues, roles, ProposalPersonRole.KEY_PERSON, developmentProposal);
        for (ProposalPersonRole role : roles) {
            addKeyValue(keyValues, role, developmentProposal);
        }
        return keyValues;
    }
    
    protected void addKeyValue(List<KeyValue> keyValues, Collection<ProposalPersonRole> roles, String roleId, DevelopmentProposal developmentProposal) {
        ProposalPersonRole curRole = getRoleById(roles, roleId);
        if (curRole != null) {
            addKeyValue(keyValues, curRole, developmentProposal);
            roles.remove(curRole);
        }
    }
    
    protected void addKeyValue(List<KeyValue> keyValues, ProposalPersonRole role, DevelopmentProposal developmentProposal) {
        if (role != null) {
            LOG.debug("Adding role " + role.getProposalPersonRoleId());
            LOG.debug("With description " + findRoleDescription(role, developmentProposal));

            keyValues.add(new ConcreteKeyValue(role.getProposalPersonRoleId(), findRoleDescription(role, developmentProposal)));
    
            LOG.debug("Returning " + keyValues);
        }
    }
    
    protected ProposalPersonRole getRoleById(Collection<ProposalPersonRole> roles, String roleId) {
        for (ProposalPersonRole role : roles) {
            if (StringUtils.equals(role.getProposalPersonRoleId(), roleId)) {
                return role;
            }
        }
        return null;
    }
    
    protected String getRoleIdPrefix(DevelopmentProposal proposal) {
        return proposal.isSponsorNihMultiplePi() ? "nih." : "";
    }

    protected String findRoleDescription(ProposalPersonRole role, DevelopmentProposal proposal) {
          return this.getParameterService().getParameterValueAsString(KC_GENERIC_PARAMETER_NAMESPACE, KC_ALL_PARAMETER_DETAIL_TYPE_CODE,
                PERSON_ROLE_PARAMETER_PREFIX + getRoleIdPrefix(proposal) + role.getProposalPersonRoleId().toLowerCase());
    }

    /**
     * Locate from Spring a singleton instance of the <code>{@link KeyPersonnelService}</code>.
     * 
     * @return KeyPersonnelService
     */
    protected KeyPersonnelService getKeyPersonnelService() {
    	if (keyPersonnelService == null) {
    		keyPersonnelService = KcServiceLocator.getService(KeyPersonnelService.class);
    	}
    	return keyPersonnelService;
    }
    
	public void setKeyPersonnelService(KeyPersonnelService keyPersonnelService) {
		this.keyPersonnelService = keyPersonnelService;
	}
    
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }
    
	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}

	protected DataObjectService getDataObjectService() {
		if (dataObjectService == null) {
			dataObjectService = KcServiceLocator.getService(DataObjectService.class);
		}
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}
}
