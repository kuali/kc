package org.kuali.coeus.common.impl.person.attr;

import org.kuali.coeus.sys.framework.lookup.KcKualiLookupableHelperServiceImpl;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("kcPersonExtendedAttributesLookupableHelperService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class KcPersonExtendedAttributesLookupableHelperServiceImpl extends KcKualiLookupableHelperServiceImpl {


    private static final long serialVersionUID = 8135788972031192656L;

    @Override
    public List<Row> getRows() {
        List<Row> rows =  super.getRows();
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (field.getPropertyName().equals("personId")) {
                    field.setFieldConversions("principalId:personId");
                    field.setQuickFinderClassNameImpl(Person.class.getName());
                    field.setFieldDirectInquiryEnabled(true);
                    field.setInquiryParameters("personId:principalId");
                }
            }
        }
        return rows;
    }

}
