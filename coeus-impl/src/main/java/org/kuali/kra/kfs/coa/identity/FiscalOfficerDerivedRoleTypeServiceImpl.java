package org.kuali.kra.kfs.coa.identity;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FiscalOfficerDerivedRoleTypeServiceImpl  extends DerivedRoleTypeServiceBase {

    protected static final String FISCAL_OFFICER_ROLE_NAMESPACE = "KFS-SYS";
    protected static final String FISCAL_OFFICER_ROLE_NAME = "Fiscal Officer";

    protected static final String ACCOUNT_NUMBER_QUALIFIER = "accountNumber";
    protected static final String FIN_COA_CODE_QUALIFIER = "chartOfAccountsCode";

    protected RoleService roleService;
    protected AwardService awardService;

    protected List<String> requiredAttributes = new ArrayList<String>();
    {
        requiredAttributes.add(KcKimAttributes.AWARD);
    }

    @Override
    public boolean hasDerivedRole(String principalId, List<String> groupIds, String namespaceCode,
                                  String roleName, Map<String,String> qualification) {
        Map<String, String> kfsQualification = translateAwardToAccountQualification(qualification);
        List<String> fiscalOfficerRoleId = getFiscalOfficerRoleIdAsList();

        if (!fiscalOfficerRoleId.isEmpty()) {
            return roleService.principalHasRole(principalId, fiscalOfficerRoleId, kfsQualification);
        }

        return false;
    }

    public List<RoleMembership> getRoleMembersFromDerivedRole(String namespaceCode, String roleName, Map<String,String> qualification) {
        validateRequiredAttributesAgainstReceived(qualification);
        Map<String, String> kfsQualification = translateAwardToAccountQualification(qualification);
        List<String> fiscalOfficerRoleId = getFiscalOfficerRoleIdAsList();

        if (!fiscalOfficerRoleId.isEmpty()) {
            return roleService.getRoleMembers(fiscalOfficerRoleId, kfsQualification);
        }

        return new ArrayList<RoleMembership>();
    }

    protected Map<String, String> translateAwardToAccountQualification(Map<String,String> qualification) {
        if (qualification == null) {
            return new HashMap<String, String>();
        }
        Map<String, String> translatedQualifications = new HashMap<String, String>(qualification);
        Award award = null;
        String awardIdStr = translatedQualifications.get(KcKimAttributes.AWARD);
        if (StringUtils.isNotBlank(awardIdStr) && awardIdStr.matches("\\d+")) {
            Long awardId = Long.valueOf(awardIdStr);
            award = getAwardService().getAward(awardId);
        }
        if (award != null && StringUtils.isNotBlank(award.getAccountNumber()) && StringUtils.isNotBlank(award.getFinancialChartOfAccountsCode())) {
            translatedQualifications.remove(KcKimAttributes.AWARD);
            translatedQualifications.put(ACCOUNT_NUMBER_QUALIFIER, award.getAccountNumber());
            translatedQualifications.put(FIN_COA_CODE_QUALIFIER, award.getFinancialChartOfAccountsCode());
        }
        return translatedQualifications;
    }

    protected String getFiscalOfficerRoleId() {
        Role fiscalOfficerRole = getRoleService().getRoleByNamespaceCodeAndName(FISCAL_OFFICER_ROLE_NAMESPACE, FISCAL_OFFICER_ROLE_NAME);
        if (fiscalOfficerRole != null && StringUtils.isNotBlank(fiscalOfficerRole.getId())) {
            return fiscalOfficerRole.getId();
        }
        return null;
    }

    protected List<String> getFiscalOfficerRoleIdAsList() {
        List<String> roleIdList = new ArrayList<String>();
        String fiscalOfficerRoleId = getFiscalOfficerRoleId();

        if (StringUtils.isNotBlank(fiscalOfficerRoleId)) {
            roleIdList.add(fiscalOfficerRoleId);
        }
        return roleIdList;
    }

    @Override
    public boolean dynamicRoleMembership(String namespaceCode, String roleName) {
        super.dynamicRoleMembership(namespaceCode, roleName);
        return true;
    }

    protected AwardService getAwardService() {
        return awardService;
    }

    public void setAwardService(AwardService awardService) {
        this.awardService = awardService;
    }

    protected RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

}
