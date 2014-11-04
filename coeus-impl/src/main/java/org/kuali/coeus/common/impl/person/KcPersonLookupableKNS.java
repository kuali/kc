package org.kuali.coeus.common.impl.person;

import org.kuali.rice.kns.lookup.KualiLookupableImpl;
import org.kuali.rice.kns.lookup.LookupableHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("kcPersonLookupableKNS")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Lazy
public class KcPersonLookupableKNS extends KualiLookupableImpl {
	
	@Autowired
    @Qualifier("kcPersonLookupableHelperServiceKNS")
	public void setLookupableHelperService(LookupableHelperService lookupableHelperService){
		super.setLookupableHelperService(lookupableHelperService);
	}

}
