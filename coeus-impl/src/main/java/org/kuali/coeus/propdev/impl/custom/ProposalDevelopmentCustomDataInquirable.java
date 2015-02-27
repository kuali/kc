package org.kuali.coeus.propdev.impl.custom;

import org.kuali.coeus.common.framework.custom.arg.ArgValueLookup;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.inquiry.InquirableImpl;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("proposalDevelopmentCustomDataInquirable")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProposalDevelopmentCustomDataInquirable extends InquirableImpl {
    @Override
    public Object retrieveDataObject(Map<String, String> parameters) {
        Map<String,String> filteredParamters = new HashMap<String,String>();
        filteredParamters.put("value",parameters.get("value"));
        filteredParamters.put("argumentName",parameters.get("argumentName"));

        return getLegacyDataAdapter().findObjectBySearch(ArgValueLookup.class,parameters);

    }
}
