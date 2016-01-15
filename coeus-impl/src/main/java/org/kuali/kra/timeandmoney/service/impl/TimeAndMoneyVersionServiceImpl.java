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
package org.kuali.kra.timeandmoney.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.version.service.AwardVersionService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyVersionService;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.KRADConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.sql.DataSource;

public class TimeAndMoneyVersionServiceImpl implements TimeAndMoneyVersionService {

    public static final String ROOT_AWARD_NUMBER = "rootAwardNumber";
    public static final String DOCUMENT_STATUS = "documentStatus";
    private AwardVersionService awardVersionService;
    private DocumentService documentService;
    private BusinessObjectService businessObjectService;
    private DataSource dataSource;
    private ParameterService parameterService;
    private GlobalVariableService globalVariableService;

    /*
     * Find any existing T&amp;M document for the given award number, with the intent to
     * edit it.  If open one is found, return it. If no open one is found, create new one.
     */
    @Override
    public TimeAndMoneyDocument findOpenedTimeAndMoney(String rootAwardNumber) throws WorkflowException {
        TimeAndMoneyDocument result = null;
        TimeAndMoneyDocument timeAndMoneyDocument = getBusinessObjectService().findBySinglePrimaryKey(TimeAndMoneyDocument.class, 
        		this.getCurrentTimeAndMoneyDocumentNumber(rootAwardNumber));
        if (timeAndMoneyDocument == null) {
            throw new WorkflowException("Missing Time and Money Document");
        } else {
            if (!VersionStatus.PENDING.toString().equals(timeAndMoneyDocument.getDocumentStatus())) {
            	if (validateCreateNewTimeAndMoneyDocument(rootAwardNumber)) {
            		timeAndMoneyDocument = editOrVersionTandMDocument(rootAwardNumber);
            	} else {
            		return null;
            	}
            }
        }
        return timeAndMoneyDocument;
    }
    
    @Override
    public boolean validateCreateNewTimeAndMoneyDocument(String awardNumber) {
    	if (!allowTimeAndMoneyWhenPendingAwardExists() 
    			&& awardVersionService.isPendingAwardInAwardHierarchy(awardNumber)
    			&& awardVersionService.isActiveAwardInAwardHierarchy(awardNumber)) {
    		globalVariableService.getMessageMap().putError(KRADConstants.GLOBAL_ERRORS, KeyConstants.CREATE_TIME_AND_MONEY_PENDING_AWARD_EXISTS_ERROR);
    		return false;
    	}
    	return true;
    }
    
    protected boolean allowTimeAndMoneyWhenPendingAwardExists() {
    	return parameterService.getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_TIME_AND_MONEY, ParameterConstants.DOCUMENT_COMPONENT, Constants.ALLOW_TM_WHEN_PENDING_AWARD_PARAM);
    }


    private TimeAndMoneyDocument editOrVersionTandMDocument(String rootAwardNumber) throws WorkflowException {
        Award rootAward = getAwardVersionService().getWorkingAwardVersion(rootAwardNumber);
        TimeAndMoneyDocument timeAndMoneyDocument = (TimeAndMoneyDocument) getDocumentService().getNewDocument(TimeAndMoneyDocument.class);
        timeAndMoneyDocument.getDocumentHeader().setDocumentDescription("timeandmoney document");
        timeAndMoneyDocument.setRootAwardNumber(rootAwardNumber);
        timeAndMoneyDocument.setAwardNumber(rootAward.getAwardNumber());
        timeAndMoneyDocument.setAward(rootAward);
        AwardAmountTransaction aat = new AwardAmountTransaction();
        aat.setAwardNumber("000000-00000");//need to initialize one element in this collection because the doc is saved on creation.
        aat.setDocumentNumber(timeAndMoneyDocument.getDocumentNumber());
        aat.setTransactionTypeCode(null);
        timeAndMoneyDocument.getAwardAmountTransactions().add(aat);
        getDocumentService().saveDocument(timeAndMoneyDocument);
        return timeAndMoneyDocument;
    }

    public void updateDocumentStatus(TimeAndMoneyDocument document, VersionStatus status) {
        if (status.equals(VersionStatus.ACTIVE)) {
            archiveActiveTimeAndMoneyDocs(document.getAwardNumber());
        }
        document.setDocumentStatus(status.toString());
        businessObjectService.save(document);
    }

    private void archiveActiveTimeAndMoneyDocs(String awardNumber) {
        Map<String, Object> values = new HashMap<>();
        values.put(ROOT_AWARD_NUMBER, awardNumber);
        values.put(DOCUMENT_STATUS, VersionStatus.ACTIVE.name());
        Collection<TimeAndMoneyDocument> documents = businessObjectService.findMatching(TimeAndMoneyDocument.class, values);
        for (TimeAndMoneyDocument document : documents) {
            document.setDocumentStatus(VersionStatus.ARCHIVED.name());
            businessObjectService.save(document);
        }
    }
    
    public String getCurrentTimeAndMoneyDocumentNumber(String awardNumber) {
    	try (Connection connection = dataSource.getConnection()) {
    		try (PreparedStatement stmt = connection.prepareStatement("select * from " +
    				"(select document_number, " +
    				" case TIME_AND_MONEY_DOC_STATUS when 'PENDING' then 1 when 'ACTIVE' then 2 else 3 end as STATUS_ORDER " + 
    				" from TIME_AND_MONEY_DOCUMENT where award_number = ? and TIME_AND_MONEY_DOC_STATUS != 'CANCELED' order by STATUS_ORDER, DOCUMENT_NUMBER) sorted_tm "
    				+ getLimitSql(connection, 1))) {
    			stmt.setString(1, awardNumber);
    			stmt.setMaxRows(1);
    			try (ResultSet rs = stmt.executeQuery()) {
    				if (rs.next()) {
    					return rs.getString(1);
    				}
    			}
    		}
    	} catch (SQLException e) {
    		throw new RuntimeException(e);
		}
    	return null;
    }
    
    String getLimitSql(Connection connection, Integer num) throws SQLException {
    	if (StringUtils.containsIgnoreCase(connection.getMetaData().getDatabaseProductName(), "oracle")) {
    		return "where rownum <= " + num;
    	} else if (StringUtils.containsIgnoreCase(connection.getMetaData().getDatabaseProductName(), "mysql")) {
    		return "limit 0, " + num;
    	} else {
    		throw new UnsupportedOperationException("Unsupported database detected");
    	}
    }

    public AwardVersionService getAwardVersionService() {
        return awardVersionService;
    }

    public void setAwardVersionService(AwardVersionService awardVersionService) {
        this.awardVersionService = awardVersionService;
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ParameterService getParameterService() {
		return parameterService;
	}

	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}

	public GlobalVariableService getGlobalVariableService() {
		return globalVariableService;
	}

	public void setGlobalVariableService(GlobalVariableService globalVariableService) {
		this.globalVariableService = globalVariableService;
	}
}
