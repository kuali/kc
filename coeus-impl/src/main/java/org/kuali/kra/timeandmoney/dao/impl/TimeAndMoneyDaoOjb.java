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
package org.kuali.kra.timeandmoney.dao.impl;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.accesslayer.LookupException;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.kuali.kra.timeandmoney.dao.TimeAndMoneyDao;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TimeAndMoneyActionSummary;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.timeandmoney.history.TransactionDetail;
import org.kuali.kra.timeandmoney.history.TransactionDetailType;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeAndMoneyDaoOjb extends PlatformAwareDaoBaseOjb implements TimeAndMoneyDao {

    private static final String DOCUMENT_STATUS = "documentStatus";
	private static final String AWARD_AMOUNT_INFOS_AWARD_ID = "awardAmountInfos.awardId";
	private static final String SUMMARY_SQL_QUERY;
    public static final String TIME_AND_MONEY_DOCUMENT_NUMBER = "timeAndMoneyDocumentNumber";
    public static final String TRANSACTION_DETAIL_TYPE = "transactionDetailType";

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

	static {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT A.NOTICE_DATE, C.DESCRIPTION, B.AWARD_AMOUNT_INFO_ID, C.DESCRIPTION, B.OBLIGATED_CHANGE, B.AMOUNT_OBLIGATED_TO_DATE,");
        sb.append(" B.OBLIGATION_EXPIRATION_DATE, B.CURRENT_FUND_EFFECTIVE_DATE FROM AWARD_AMOUNT_TRANSACTION A, AWARD_AMOUNT_INFO B, AWARD_TRANSACTION_TYPE C");
        sb.append(" WHERE A.AWARD_NUMBER = B.AWARD_NUMBER AND A.TRANSACTION_ID = B.TNM_DOCUMENT_NUMBER AND B.AWARD_NUMBER = ?");
        sb.append(" AND A.TRANSACTION_TYPE_CODE = C.AWARD_TRANSACTION_TYPE_CODE");
        sb.append(" ORDER BY B.AWARD_AMOUNT_INFO_ID DESC");
        SUMMARY_SQL_QUERY = sb.toString();
	}

	public List<TimeAndMoneyActionSummary> buildTimeAndMoneyActionSummaryForAward(String awardNumber) {
        List<TimeAndMoneyActionSummary> summaryItems = new ArrayList<>();
        PersistenceBroker pbInstance = getPersistenceBroker(true);
        TimeAndMoneyActionSummary timeAndMoneyActionSummary;
        
        try (PreparedStatement stmt = pbInstance.serviceConnectionManager().getConnection().prepareStatement(SUMMARY_SQL_QUERY)) {
            stmt.setString(1, awardNumber);
            try (ResultSet rs = stmt.executeQuery()) {
	            while(rs.next()){
	                timeAndMoneyActionSummary = new TimeAndMoneyActionSummary();
	                timeAndMoneyActionSummary.setNoticeDate(rs.getDate(1));
	                timeAndMoneyActionSummary.setTransactionType(rs.getString(2));
	                if(rs.getObject(5)!=null){
	                    timeAndMoneyActionSummary.setChangeAmount(new ScaleTwoDecimal((BigDecimal)rs.getObject(5)));
	
	                }
	                if(rs.getObject(6)!=null){
	                    timeAndMoneyActionSummary.setObligationCumulative(new ScaleTwoDecimal((BigDecimal)rs.getObject(6)));
	                }
	                timeAndMoneyActionSummary.setObligationEndDate(rs.getDate(7));
	                timeAndMoneyActionSummary.setObligationStartDate(rs.getDate(8));
	                summaryItems.add(timeAndMoneyActionSummary);
	            }
            }
        } catch (SQLException|LookupException e) {
            throw new RuntimeException(e);
        }
        return summaryItems;
    }
   
	@Override
    public List<TimeAndMoneyDocument> getTimeAndMoneyDocumentForAwards(List<Long> awardIds) {
    	Criteria crit = new Criteria();
    	crit.addIn(AWARD_AMOUNT_INFOS_AWARD_ID, awardIds);
    	crit.addNotEqualTo(DOCUMENT_STATUS, VersionStatus.CANCELED.toString()) ;
    	
    	QueryByCriteria criteria = new QueryByCriteria(TimeAndMoneyDocument.class, crit);
    	criteria.setDistinct(true);
    	
    	@SuppressWarnings("unchecked")
		List<TimeAndMoneyDocument> docs = new ArrayList<>(getPersistenceBrokerTemplate().getCollectionByQuery(criteria));
    	return docs;
    }

    @Override
    public TimeAndMoneyDocument getTimeAndMoneyDocument(String documentNumber) {
        return businessObjectService.findBySinglePrimaryKey(TimeAndMoneyDocument.class, documentNumber);
    }


    public List<TransactionDetail> getTransactionDetailsForDocument(String documentNumber) {
        Map<String, Object> criteria = new HashMap<>();
        criteria.put(TIME_AND_MONEY_DOCUMENT_NUMBER, documentNumber);
        criteria.put(TRANSACTION_DETAIL_TYPE, TransactionDetailType.PRIMARY.toString());

        return ((List<TransactionDetail>) businessObjectService.findMatching(TransactionDetail.class, criteria));
    }

}
