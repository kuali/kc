/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.web.ui.KeyLabelPair;
import org.kuali.kra.proposaldevelopment.bo.PostSubmissionStatus;
import org.kuali.rice.kns.service.KNSServiceLocator;
public class PostSubmissionStatusValuesFinder extends KeyValuesBase {
    public List getKeyValues() {
        Collection<PostSubmissionStatus> allPostSubmissionStatus = loadAllPostSubmissionStatus();
        List<KeyLabelPair> keyLabelPairs = new ArrayList<KeyLabelPair>();
        Collection<PostSubmissionStatus> filteredCollection = filterCollection(allPostSubmissionStatus);
        for (PostSubmissionStatus postsubmissionstatus : filteredCollection) {
            Integer key = postsubmissionstatus.getPostSubmissionStatusCode();
            String label = postsubmissionstatus.getDescription();
            keyLabelPairs.add(new KeyLabelPair(key, label));
        }
        return keyLabelPairs;

    }


    protected Collection<PostSubmissionStatus> loadAllPostSubmissionStatus() {
        BusinessObjectService boService = KNSServiceLocator.getBusinessObjectService();
        return boService.findAll(PostSubmissionStatus.class);
    }


    protected Collection<PostSubmissionStatus> filterCollection(Collection<PostSubmissionStatus> postSubmissionstatus) {
        Collection<PostSubmissionStatus> filteredpostsubmissionstatus = new ArrayList<PostSubmissionStatus>();    
        for (PostSubmissionStatus postsubmission : postSubmissionstatus) {
            if (postsubmission.getPostSubmissionStatusCode()!=1) {
                filteredpostsubmissionstatus.add(postsubmission);

            }            
        }
        return filteredpostsubmissionstatus;
    }
}
