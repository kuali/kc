package org.kuali.coeus.common.impl.auth.docperm;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.auth.docperm.DocumentAccess;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

import static org.kuali.rice.core.api.criteria.PredicateFactory.*;

@Component("documentAccessDerivedRoleTypeService")
public class DocumentAccessDerivedRoleTypeServiceImpl extends DerivedRoleTypeServiceBase {

    protected List<String> requiredAttributes = Collections.singletonList(KcKimAttributes.DOCUMENT_NUMBER);

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Autowired
    @Qualifier("roleService")
    private RoleService roleService;

    @Override
    public List<RoleMembership> getRoleMembersFromDerivedRole(String namespaceCode, String roleName, Map<String, String> qualification) {
        if (StringUtils.isBlank(namespaceCode)) {
            throw new IllegalArgumentException("namespaceCode was null or blank");
        }

        if (roleName == null) {
            throw new IllegalArgumentException("roleName was null");
        }

        validateRequiredAttributesAgainstReceived(qualification);

        final String documentNumber = qualification.get(KcKimAttributes.DOCUMENT_NUMBER);
        final Collection<DocumentAccess> accesses = dataObjectService.findMatching(DocumentAccess.class, QueryByCriteria.Builder.fromPredicates(
                equal("documentNumber", documentNumber),
                equal("roleName", roleName),
                equal("namespaceCode", namespaceCode)
        )).getResults();

        final String roleId = roleService.getRoleIdByNamespaceCodeAndName(namespaceCode, roleName);
        final List<RoleMembership> memberships = new ArrayList<>();
        if (StringUtils.isNotBlank(roleId)) {
            for (DocumentAccess access : accesses) {
                memberships.add(RoleMembership.Builder.create(roleId, null, access.getPrincipalId(), MemberType.PRINCIPAL, Collections.singletonMap("documentNumber", documentNumber)).build());
            }
        }

        return memberships;
    }

    @Override
    public List<String> getRequiredAttributes() {
        return requiredAttributes;
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}
