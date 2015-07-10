/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.impl.compliance.core;

import org.kuali.coeus.sys.framework.keyvalue.DataObjectValuesFinder;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewApprovalType;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.data.DataObjectService;

import java.util.List;

/**
 * See {@link #getKeyValues()}.
 */
public class SpecialReviewApprovalTypeValuesFinder extends DataObjectValuesFinder {

    public static final String DESCRIPTION = "description";
    public static final String APPROVAL_TYPE_CODE = "approvalTypeCode";

    /**
     * Gets the keyvalue pair for {@link SpecialReviewApprovalType SpecialReviewApprovalType}.
     * The key is the specialReviewApprovalTypeCode and the value is the description.
     * 
     * @return a list of {@link KeyValue KeyValue}
     */
    @Override
    public List<KeyValue> getKeyValues() {
    	setKeyValueAttributes();
        setOrderByField(DESCRIPTION);
        final List<KeyValue> specialReviewApprovalTypes = super.getKeyValues();
        return specialReviewApprovalTypes;
    }

    protected void setKeyValueAttributes() {
        setDataObjectClass(SpecialReviewApprovalType.class);
        setKeyAttributeName(APPROVAL_TYPE_CODE);
        setLabelAttributeName(DESCRIPTION);
    }
    
    public DataObjectService getDataObjectService() {
    	if (super.getDataObjectService() == null) {
    		this.setDataObjectService(KcServiceLocator.getService(DataObjectService.class));
    	}
    	return super.getDataObjectService();
    }   
}
