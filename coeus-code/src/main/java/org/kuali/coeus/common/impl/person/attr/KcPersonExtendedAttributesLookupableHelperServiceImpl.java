package org.kuali.coeus.common.impl.person.attr;

import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;

import java.util.List;

public class KcPersonExtendedAttributesLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {


    private static final long serialVersionUID = 8135788972031192656L;

    @Override
    public List<Row> getRows() {
        List<Row> rows =  super.getRows();
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (field.getPropertyName().equals("personId")) {
                    field.setFieldConversions("principalId:personId");
                    field.setQuickFinderClassNameImpl("org.kuali.rice.kim.api.identity.Person");
                    field.setFieldDirectInquiryEnabled(true);
                    field.setInquiryParameters("personId:principalId");
                }
            }
        }
        return rows;
    }

}
