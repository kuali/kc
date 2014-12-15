package org.kuali.kra.external.customercreation;

import java.util.List;

import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.kra.external.service.KcDtoService;
import org.kuali.kra.external.sponsor.SponsorDTO;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

public interface CustomerCreationClient {

    public List<String> createCustomer(Sponsor sponsor, String initiatedByPrincipalName);

    public List<KeyValue> getCustomerTypes();

    public boolean isValidCustomer(String customerNumber);
    
    public void setSponsorDtoService(KcDtoService<SponsorDTO, Sponsor> dtoService);
    
    public void setParameterService(ParameterService parameterService);
}
