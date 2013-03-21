/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.reporting.lookup;

import java.util.List;

import javax.persistence.Column;

import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;

@SuppressWarnings("deprecation")
public class CustReportLookupableHelperServiceImpl  extends KualiLookupableHelperServiceImpl {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    
    @Override
    public List<Row> getRows() {
        List<Row> rows = super.getRows();
        List<org.kuali.rice.kns.web.ui.Column> rows1 = super.getColumns();

        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (field.getPropertyName().equals("name")) {
                    field.setFieldDirectInquiryEnabled(false);
                    field.setFieldConversions("name:name,id:id");
                }
            }
        }
        return rows;
    }

}
