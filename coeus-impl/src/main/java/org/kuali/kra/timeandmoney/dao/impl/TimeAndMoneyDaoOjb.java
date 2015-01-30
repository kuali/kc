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
package org.kuali.kra.timeandmoney.dao.impl;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.accesslayer.LookupException;
import org.kuali.kra.timeandmoney.dao.TimeAndMoneyDao;
import org.kuali.kra.timeandmoney.history.TimeAndMoneyActionSummary;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

public class TimeAndMoneyDaoOjb extends PlatformAwareDaoBaseOjb implements TimeAndMoneyDao {

    public void runScripts(List<TimeAndMoneyActionSummary> timeAndMoneyActionSummaryItems, String awardNumber) throws LookupException, SQLException{
        Statement stmt = null;
        PersistenceBroker pbInstance = getPersistenceBroker(true);
        TimeAndMoneyActionSummary timeAndMoneyActionSummary;
        
        try {
            stmt = pbInstance.serviceConnectionManager().getConnection().createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT DISTINCT A.NOTICE_DATE, C.DESCRIPTION, B.AWARD_AMOUNT_INFO_ID, C.DESCRIPTION, B.OBLIGATED_CHANGE, B.AMOUNT_OBLIGATED_TO_DATE, ");
            sb.append("B.OBLIGATION_EXPIRATION_DATE, B.CURRENT_FUND_EFFECTIVE_DATE FROM AWARD_AMOUNT_TRANSACTION A, AWARD_AMOUNT_INFO B, AWARD_TRANSACTION_TYPE C ");
            sb.append("WHERE A.AWARD_NUMBER = B.AWARD_NUMBER AND A.TRANSACTION_ID = B.TNM_DOCUMENT_NUMBER AND B.AWARD_NUMBER = '");
            sb.append(awardNumber);
            sb.append("' AND A.TRANSACTION_TYPE_CODE = C.AWARD_TRANSACTION_TYPE_CODE");
            sb.append(" ORDER BY B.AWARD_AMOUNT_INFO_ID");
            
            ResultSet rs = stmt.executeQuery(sb.toString());
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
                timeAndMoneyActionSummaryItems.add(timeAndMoneyActionSummary);
            }
            
        }
        catch (LookupException e) {
            throw e;
        }
        catch (SQLException e) {
            throw e;
        }
        Collections.reverse(timeAndMoneyActionSummaryItems);
    }
 
}
