/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.lookup;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.web.struts.form.LookupForm;
import org.kuali.rice.kns.web.ui.Field;

public abstract class KraLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {

    //@Override
    public String getActionUrls(BusinessObject businessObject) {
        return "<a href=\"../"+getHtmlAction()+"?methodToCall=docHandler&command=initiate&docTypeName="+getDocumentTypeName()
            +"&"+getKeyFieldName()+"="+ObjectUtils.getPropertyValue(businessObject, getKeyFieldName()).toString()+"\">edit</a>";
    }

    /**
     * To force to it to show action links, such as 'edit'.
     * @see org.kuali.core.lookup.AbstractLookupableHelperServiceImpl#performLookup(org.kuali.core.web.struts.form.LookupForm, java.util.Collection, boolean)
     */
    @Override
    public Collection performLookup(LookupForm lookupForm, Collection resultTable, boolean bounded) {
        lookupForm.setShowMaintenanceLinks(true);
        return super.performLookup(lookupForm, resultTable, bounded);
    }

    /**
     * 
     * This method to set both fields if child class override getSearchResults
     * @param fieldValues
     */
    protected void setBackLocationDocFormKey(Map<String, String> fieldValues) {

        //need to set backlocation & docformkey here.  Otherwise, they are empty
        for (Entry<String,String> entry : fieldValues.entrySet()) {
            if (entry.getKey().equals(KNSConstants.BACK_LOCATION)) {
                setBackLocation(entry.getValue());
            } else if (entry.getKey().equals(KNSConstants.DOC_FORM_KEY)) {
                setDocFormKey(entry.getValue());
            }
        }
    }
 
    /**
     * 
     * This method is to set up field definitions for lookup fields.  
     * These fields does not have a reference object defined for it in repository & bo.
     * @param field
     * @param keyName
     * @param className
     */
    protected void updateLookupField(Field field, String keyName, String className) {
        if (StringUtils.isNotBlank(keyName) && StringUtils.isNotBlank(className)) {
            field.setFieldConversions(keyName+":"+field.getPropertyName());
            field.setLookupParameters(field.getPropertyName()+":"+keyName);
            field.setInquiryParameters(field.getPropertyName()+":"+keyName);
            field.setQuickFinderClassNameImpl(className);
            if ("Not Available".equals(field.getPropertyValue())) {
                field.setPropertyValue("");
                field.setReadOnly(false);
            }
        } else {
            field.setFieldConversions("");
            field.setLookupParameters("");
            field.setInquiryParameters("");
            field.setQuickFinderClassNameImpl("");
            field.setPropertyValue("Not Available");
            field.setReadOnly(true);
        }
        
    }

        
    /**
     * htmlaction for 'edit' link
     */
    protected abstract String getHtmlAction();
    
    /**
     * 
     * This method to set document type of the lookup bo's document
     * @return
     */
    protected abstract String getDocumentTypeName();
    
    /**
     * 
     * This method is set the key field to retrieve bo for editing.  May change to  a map if there are multiple fields.
     * @return
     */
    protected abstract String getKeyFieldName();

}
