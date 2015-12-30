package org.kuali.coeus.common.impl.custom.arg;

import org.kuali.coeus.common.framework.custom.arg.ArgValueLookup;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.rice.krad.data.platform.MaxValueIncrementerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.sql.DataSource;

public class ArgValueLookupMaintainableImpl extends KraMaintainableImpl {

    public static final String ARG_VALUE_SEQUENCE_NAME = "SEQ_ARG_VALUE_LOOKUP_ID";

    @Autowired
    @Qualifier("kradApplicationDataSource")
    private DataSource kradApplicationDataSource;

    @Override
    public void prepareForSave() {
        ArgValueLookup argValueLookup = (ArgValueLookup) this.businessObject;
        if(argValueLookup.getId() == null) {
            Long nextArgValueId = MaxValueIncrementerFactory.getIncrementer(getKradApplicationDataSource(), ARG_VALUE_SEQUENCE_NAME).nextLongValue();
            argValueLookup.setId(nextArgValueId);
        }
        super.prepareForSave();
    }

    public DataSource getKradApplicationDataSource() {
        if(kradApplicationDataSource == null) {
            kradApplicationDataSource = KcServiceLocator.getService("kradApplicationDataSource");
        }
        return kradApplicationDataSource;
    }

    public void setKradApplicationDataSource(DataSource kradApplicationDataSource) {
        this.kradApplicationDataSource = kradApplicationDataSource;
    }
}
