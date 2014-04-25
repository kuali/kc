package org.kuali.coeus.common.impl.person;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.coeus.common.framework.person.PropAwardPersonRole;
import org.kuali.coeus.common.framework.person.PropAwardPersonRoleService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.criteria.PredicateFactory;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("propAwardPersonRoleService")
public class PropAwardPersonRoleServiceImpl implements PropAwardPersonRoleService {
	
	private static final String SPONSOR_HIERARCHIES_PARM = "PERSON_ROLE_SPONSOR_HIERARCHIES";
	private static final String ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI = "ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI";
	private static final String NIH_MULTIPLE_PI_HIERARCHY = "NIH Multiple PI";

	@Autowired
	@Qualifier("dataObjectService")
	private DataObjectService dataObjectService;
	
	@Autowired
	@Qualifier("sponsorHierarchyService")
	private SponsorHierarchyService sponsorHierarchyService;
	
	@Autowired
	@Qualifier("parameterService")
	private ParameterService parameterService;

	@Override
	public PropAwardPersonRole getRole(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("The id cannot be null");
        }

		return getDataObjectService().find(PropAwardPersonRole.class, id);
	}

	@Override
	public PropAwardPersonRole getRole(String roleCode, String sponsorCode) {
        if (StringUtils.isBlank(roleCode)) {
            throw new IllegalArgumentException("The roleCode cannot be blank");
        }

        if (StringUtils.isBlank(sponsorCode)) {
            throw new IllegalArgumentException("The sponsorCode cannot be blank");
        }
        
		String hierarchyName = getSponsorHierarchy(sponsorCode);
		QueryByCriteria.Builder criteria = 
				QueryByCriteria.Builder.create().setPredicates(PredicateFactory.equal("code", roleCode), PredicateFactory.equal("sponsorHierarchyName", hierarchyName));
		return getDataObjectService().findUnique(PropAwardPersonRole.class, criteria.build());
	}

	@Override
	public Collection<PropAwardPersonRole> getRolesByHierarchy(String sponsorCode) {
        if (StringUtils.isBlank(sponsorCode)) {
            throw new IllegalArgumentException("The sponsorCode cannot be blank");
        }

		String hierarchyName = getSponsorHierarchy(sponsorCode);
		return getDataObjectService().findMatching(PropAwardPersonRole.class, QueryByCriteria.Builder.forAttribute("sponsorHierarchyName", hierarchyName).build()).getResults();
	}
	
	@Override
	public boolean isNihOtherSignificantContributerEnabledForSponsor(
			String sponsorCode) {
        return areAllSponsorsMultiPi() || getSponsorHierarchyService().isSponsorNihOsc(sponsorCode);
	}	
	
	protected String getSponsorHierarchy(String sponsorCode) {
		if (areAllSponsorsMultiPi()) {
			return NIH_MULTIPLE_PI_HIERARCHY;
		}
		for (String hierarchyName : getRoleHierarchies()) {
			if (getSponsorHierarchyService().isSponsorInHierarchy(sponsorCode, hierarchyName)) {
				return hierarchyName;
			}
		}
		return DEFAULT_SPONSOR_HIERARCHY_NAME;
	}
	
	protected Boolean areAllSponsorsMultiPi() {
		return getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, ALL_SPONSOR_HIERARCHY_NIH_MULTI_PI);
	}
	
	protected Collection<String> getRoleHierarchies() {
		return getParameterService().getParameterValuesAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, SPONSOR_HIERARCHIES_PARM);
	}
	
	protected DataObjectService getDataObjectService() {
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}

	protected SponsorHierarchyService getSponsorHierarchyService() {
		return sponsorHierarchyService;
	}

	public void setSponsorHierarchyService(
			SponsorHierarchyService sponsorHierarchyService) {
		this.sponsorHierarchyService = sponsorHierarchyService;
	}

	public ParameterService getParameterService() {
		return parameterService;
	}

	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}
}
