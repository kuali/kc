package org.kuali.kra.award.lookup;


import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.kns.lookup.Lookupable;
import org.kuali.rice.krad.lookup.LookupForm;
import org.kuali.rice.krad.lookup.LookupableImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Component("awardLookupableKrad")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AwardLookupableKrad extends LookupableImpl implements InitializingBean {

    @Autowired
    @Qualifier("awardLookupable")
    private Lookupable awardLookupable;

    @Override
    public Collection<?> performSearch(LookupForm form, Map<String, String> searchCriteria, boolean bounded) {
        return getAwardLookupable().getSearchResults(searchCriteria);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setDataObjectClass(getAwardLookupable().getBusinessObjectClass());
    }

    @Override
    public Class<?> getDataObjectClass() {
        if (super.getDataObjectClass() == null) {
            setDataObjectClass(getAwardLookupable().getBusinessObjectClass());
        }

        return super.getDataObjectClass();
    }

    public Lookupable getAwardLookupable() {
        if (awardLookupable == null) {
            awardLookupable = KcServiceLocator.getService("awardLookupable");
        }

        return awardLookupable;
    }

    public void setAwardLookupable(Lookupable awardLookupable) {
        this.awardLookupable = awardLookupable;
    }
}
