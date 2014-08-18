package org.kuali.coeus.common.impl.org;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.api.org.OrganizationContract;
import org.kuali.coeus.common.api.org.OrganizationRepositoryService;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.common.framework.org.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("organizationRepositoryService")
public class OrganizationRepositoryServiceImpl implements OrganizationRepositoryService {

    @Autowired
    @Qualifier("rolodexService")
    private RolodexService rolodexService;

    @Autowired
    @Qualifier("organizationService")
    private OrganizationService organizationService;

    @Override
    public String getCognizantFedAgency(OrganizationContract organization) {
        if (organization == null) {
            throw new IllegalArgumentException("organization is null");
        }

        final StringBuilder fedAgency = new StringBuilder();
        if (organization.getCognizantAuditor() != null) {
            RolodexContract rolodex = rolodexService.getRolodex(organization.getCognizantAuditor());
            if (rolodex != null) {
                fedAgency.append(rolodex.getOrganization());
                fedAgency.append(", ");
                fedAgency.append(StringUtils.trimToEmpty(rolodex.getFirstName()));
                fedAgency.append(" ");
                fedAgency.append(StringUtils.trimToEmpty(rolodex.getLastName()));
                fedAgency.append(" ");
                if (rolodex.getPhoneNumber() != null) {
                    if (rolodex.getPhoneNumber().length() < 180) {
                        fedAgency.append(rolodex.getPhoneNumber());
                    } else {
                        fedAgency.append(rolodex.getPhoneNumber().substring(0, 180));
                    }
                }
            }
        }
        return fedAgency.toString();
    }

    @Override
    public OrganizationContract getOrganization(String organizationId) {
        if (StringUtils.isBlank(organizationId)) {
            throw new IllegalArgumentException("organizationId is blank");
        }

        return organizationService.getOrganization(organizationId);
    }

    public RolodexService getRolodexService() {
        return rolodexService;
    }

    public void setRolodexService(RolodexService rolodexService) {
        this.rolodexService = rolodexService;
    }

    public OrganizationService getOrganizationService() {
        return organizationService;
    }

    public void setOrganizationService(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }
}
