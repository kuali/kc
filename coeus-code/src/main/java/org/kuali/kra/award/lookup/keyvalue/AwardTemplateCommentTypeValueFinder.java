/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.award.lookup.keyvalue;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.CommentType;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.*;

/**
 * This class is used to get the Frequency BO for AwardProposalDue control
 */
public class AwardTemplateCommentTypeValueFinder  extends UifKeyValuesFinderBase {
    
    /**
     * Constructs the list of Comment BOs.  Each entry
     * in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * report class code and the "value" is the textual description that is viewed
     * by a user.  The list is obtained from the AwardDocument if any are defined there. 
     * Otherwise, it is obtained from a lookup of the COMMENT_TYPE database table
     * via the "KeyValueFinderService".
     * 
     * @return the list of &lt;key, value&gt; pairs of comment types.  The first entry
     * is always &lt;"", "select:"&gt;.
     *
     */
    @Override
    public List<KeyValue> getKeyValues() {
        KeyValuesService keyValuesService = (KeyValuesService) KcServiceLocator.getService("keyValuesService");
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("templateFlag", Boolean.TRUE);
        Collection reportClasses = keyValuesService.findMatching(CommentType.class, fieldValues);
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        for (Iterator iter = reportClasses.iterator(); iter.hasNext();) {
            CommentType comment = (CommentType) iter.next();
            keyValues.add(new ConcreteKeyValue(comment.getCommentTypeCode(), comment.getDescription()));                            
        }
                
        return keyValues;
    }
   

}
