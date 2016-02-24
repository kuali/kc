/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
