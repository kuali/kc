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
package org.kuali.kra.award.dao.ojb;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.accesslayer.LookupException;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.impl.version.history.VersionHistoryLookupDao;
import org.kuali.kra.award.dao.AwardLookupDao;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.WorkflowRuntimeException;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.dao.impl.LookupDaoOjb;
import org.kuali.rice.krad.lookup.CollectionIncomplete;
import org.springmodules.orm.ojb.OjbFactoryUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class AwardLookupDaoOjb extends LookupDaoOjb  implements AwardLookupDao{

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(AwardLookupDaoOjb.class);

    private static final String ACTIVE_AWARD_SEQUENCE_STATUS  = "ACTIVE";  // Final
    private static final String PENDING_AWARD_SEQUENCE_STATUS = "PENDING"; // Saved
    private static final String ACTIVE_OR_PENDING_AWARD_SEQUENCE_STATUS = "ACTIVE|PENDING";
    private static final String BOTH_AWARD_SEQUENCE_STATUS = "BOTH";

    private VersionHistoryLookupDao versionHistoryLookupDao;

    @SuppressWarnings("rawtypes")
    @Override
    public List<? extends BusinessObject> getAwardSearchResults(Map fieldValues, boolean usePrimaryKeys) {
        if (fieldValues.containsKey("awardSequenceStatus") &&
                fieldValues.get("awardSequenceStatus").toString().equalsIgnoreCase(BOTH_AWARD_SEQUENCE_STATUS)) {
            fieldValues.remove("awardSequenceStatus");
            fieldValues.put("awardSequenceStatus", ACTIVE_OR_PENDING_AWARD_SEQUENCE_STATUS);
        }

        List<Award> searchResults = (List<Award>)getVersionHistoryLookupDao().
                getSequenceOwnerSearchResults(Award.class, fieldValues, usePrimaryKeys);
        List<String> activeAwards = new ArrayList<>();
        List<Long> awardIds = new ArrayList<>();
        for (Object object : searchResults) {
            Award awardSearchBo = (Award)object;
            boolean addAwardToList = false;

            if (fieldValues.containsKey("awardSequenceStatus")) {
                if (fieldValues.get("awardSequenceStatus").toString().equalsIgnoreCase(PENDING_AWARD_SEQUENCE_STATUS)) {
                    if (awardSearchBo.getVersionHistory().getStatusForOjb().equals(VersionStatus.PENDING.toString())) {
                        addAwardToList = true;
                    }
                }
                else if (fieldValues.get("awardSequenceStatus").toString().equalsIgnoreCase(ACTIVE_AWARD_SEQUENCE_STATUS)) {
                    if (awardSearchBo.getAwardSequenceStatus() .equals(VersionStatus.ACTIVE.toString())) {
                        addAwardToList = true;
                    }
                }
                else if(fieldValues.get("awardSequenceStatus").toString().equalsIgnoreCase(ACTIVE_OR_PENDING_AWARD_SEQUENCE_STATUS)) {
                    if ((awardSearchBo.getVersionHistory().getStatusForOjb().equals(VersionStatus.PENDING.toString()) &&
                            isMostRecentVersion(awardSearchBo.getAwardNumber(), PENDING_AWARD_SEQUENCE_STATUS, "")) ||
                            (awardSearchBo.getVersionHistory().getStatusForOjb().equals(VersionStatus.ACTIVE.toString()) &&
                                    isMostRecentVersion(awardSearchBo.getAwardNumber(), "", ACTIVE_AWARD_SEQUENCE_STATUS)) ) {
                        addAwardToList = true;
                    }
                }
            }

            else if(!activeAwards.contains(awardSearchBo.getAwardNumber()) && checkAwardHasActiveTnMDocument(awardSearchBo)){
                addAwardToList = true;
            }

            if (addAwardToList) {
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

    public boolean isMostRecentVersion(String awardNumber, String pendingStatus, String activeStatus) {
        PersistenceBroker broker = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        boolean isMostRecentVersion = false;

        try {
            broker = getPersistenceBroker(false);
            conn = broker.serviceConnectionManager().getConnection();
            String query = "select award_number from award b where award_sequence_status in (?, ?) and " +
                    "award_number= ? and sequence_number=(select max(sequence_number) from award p where p.award_number=b.award_number)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, pendingStatus);
            stmt.setString(2, activeStatus);
            stmt.setString(3, awardNumber);
            rs = stmt.executeQuery();

            try {
                if  (rs.next()) {
                    isMostRecentVersion = true;
                }
            }
            finally {
                try { rs.close(); } catch (Exception ignore) { }
            }
        } catch (SQLException sqle) {
            LOG.error("SQLException: " + sqle.getMessage(), sqle);
            throw new WorkflowRuntimeException(sqle);
        } catch (LookupException le) {
            LOG.error("LookupException: " + le.getMessage(), le);
            throw new WorkflowRuntimeException(le);
        } finally {
            try {
                if(stmt != null) {
                    stmt.close();
                }
                if (broker != null) {
                    OjbFactoryUtils.releasePersistenceBroker(broker, this.getPersistenceBrokerTemplate().getPbKey());
                }
            } catch (Exception e) {
                LOG.error("Failed closing connection: " + e.getMessage(), e);
            }
        }
        return isMostRecentVersion;
    }

    public VersionHistoryLookupDao getVersionHistoryLookupDao() {
        return versionHistoryLookupDao;
    }

    public void setVersionHistoryLookupDao(VersionHistoryLookupDao versionHistoryLookupDao) {
        this.versionHistoryLookupDao = versionHistoryLookupDao;
    }


}
