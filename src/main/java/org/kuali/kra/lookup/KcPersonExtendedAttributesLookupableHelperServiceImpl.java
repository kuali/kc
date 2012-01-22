package org.kuali.kra.lookup;

import java.util.List;

import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;

public class KcPersonExtendedAttributesLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8135788972031192656L;

    /**
     * @see org.kuali.rice.kns.lookup.AbstractLookupableHelperServiceImpl#getRows()
     */
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
