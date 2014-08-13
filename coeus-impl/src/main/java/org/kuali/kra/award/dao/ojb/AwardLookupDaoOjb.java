/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.award.dao.ojb;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.coeus.common.impl.version.history.VersionHistoryLookupDao;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.dao.AwardLookupDao;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.dao.impl.LookupDaoOjb;
import org.kuali.rice.krad.lookup.CollectionIncomplete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class AwardLookupDaoOjb extends LookupDaoOjb  implements AwardLookupDao{
    private VersionHistoryLookupDao versionHistoryLookupDao;

    @SuppressWarnings("rawtypes")
    @Override
    public List<? extends BusinessObject> getAwardSearchResults(Map fieldValues, boolean usePrimaryKeys) {
        List<Award> searchResults = (List<Award>)getVersionHistoryLookupDao().
                getSequenceOwnerSearchResults(Award.class, fieldValues, usePrimaryKeys);
        List<String> activeAwards = new ArrayList<String>();
        List<Long> awardIds = new ArrayList<Long>();
        for (Object object : searchResults) {
            Award awardSearchBo = (Award)object;
            if(awardSearchBo.getVersionHistory().isActiveVersion()){
                activeAwards.add(awardSearchBo.getAwardNumber());
                awardIds.add(awardSearchBo.getAwardId());
            }else if(!activeAwards.contains(awardSearchBo.getAwardNumber()) && checkAwardHasActiveTnMDocument(awardSearchBo)){
                activeAwards.add(awardSearchBo.getAwardNumber());
                awardIds.add(awardSearchBo.getAwardId());
            }
        }
        if(awardIds.isEmpty()){
            return new ArrayList<Award>();
        }
        Criteria awardCr = new Criteria();
        awardCr.addIn("awardId", awardIds);
        List<Award> awardList = (List<Award>)getPersistenceBrokerTemplate().getCollectionByQuery(QueryFactory.newQuery(Award.class, awardCr));
        if (searchResults instanceof CollectionIncomplete) {
            awardList = new CollectionIncomplete<Award>(awardList, ((CollectionIncomplete<Award>)searchResults).getActualSizeIfTruncated());
        }

        return awardList;    
    }

    private boolean checkAwardHasActiveTnMDocument(Award awardSearckBo) {
        
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        String[] splitAwardNumber = awardSearckBo.getAwardNumber().split("-");
        StringBuilder rootAwardNumberBuilder = new StringBuilder(12);
        rootAwardNumberBuilder.append(splitAwardNumber[0]);
        rootAwardNumberBuilder.append("-00001");
        String rootAwardNumber = rootAwardNumberBuilder.toString();
        fieldValues.put("rootAwardNumber", rootAwardNumber);
        Criteria tnmCriteria = new Criteria();
        tnmCriteria.addEqualTo("rootAwardNumber", rootAwardNumber);
        QueryByCriteria query = new QueryByCriteria(TimeAndMoneyDocument.class,tnmCriteria);
        query.addOrderBy("documentNumber", true);
        List<TimeAndMoneyDocument> timeAndMoneyDocuments = 
                (List<TimeAndMoneyDocument>)getPersistenceBrokerTemplate().getCollectionByQuery(query);
        if(!timeAndMoneyDocuments.isEmpty()){
            TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyDocuments.get(0);
            DocumentHeader documentHeader = timeAndMoneyDocument.getDocumentHeader();
            WorkflowDocument tnmWorkFlowDoc = documentHeader==null || !documentHeader.hasWorkflowDocument() 
                    ? null : documentHeader.getWorkflowDocument();
            if(tnmWorkFlowDoc!=null && tnmWorkFlowDoc.isFinal()){
                return true;
            }
        }
        
        return false;
    }

    public VersionHistoryLookupDao getVersionHistoryLookupDao() {
        return versionHistoryLookupDao;
    }

    public void setVersionHistoryLookupDao(VersionHistoryLookupDao versionHistoryLookupDao) {
        this.versionHistoryLookupDao = versionHistoryLookupDao;
    }


}
