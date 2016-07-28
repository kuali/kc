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
package org.kuali.kra.award.version.service.impl;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.history.VersionHistory;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.version.service.AwardVersionService;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Award Version Service implementation
 */
@Transactional
public class AwardVersionServiceImpl extends PlatformAwareDaoBaseOjb implements AwardVersionService {


	private static final String AWARD_AMOUNT_INFOS = "awardAmountInfos";
	private static final String AWARD_SEQUENCE_STATUS = "awardSequenceStatus";
	private static final String AWARD_NUMBER = "awardNumber";

	@Autowired
	@Qualifier("versionHistoryService")
    private VersionHistoryService versionHistoryService;
    
    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;

    @Autowired
    @Qualifier("awardService")
    private AwardService awardService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    /**
     * This method returns the proper Award for displaying information in T&amp;M, Budget and Award documents.  Logic for canceled documents.
     * @param awardNumber
     * @return
     */
    @Override
    public Award getWorkingAwardVersion(String awardNumber) {
    	Map<String, Object> values = new HashMap<>();
    	values.put(AWARD_NUMBER, awardNumber);
    	values.put(AWARD_SEQUENCE_STATUS, VersionStatus.ACTIVE.toString());
    	Award award = getBusinessObjectService().findMatching(Award.class, values).stream().findFirst().orElse(null);
    	if (award == null) {
    		values.put(AWARD_SEQUENCE_STATUS, VersionStatus.PENDING.toString());
    		award = getBusinessObjectService().findMatching(Award.class, values).stream().findFirst().orElse(null);
    	}
    	return award;
    }
    
    @Override
    public List<Award> getAllActiveAwardsForHierarchy(String awardNumber) {
    	QueryByCriteria queryCrit = getQueryForAwardHierarchyByStatus(awardNumber, VersionStatus.ACTIVE.toString());
    	queryCrit.addPrefetchedRelationship(AWARD_AMOUNT_INFOS);
    	return new ArrayList<Award>(getPersistenceBrokerTemplate().getCollectionByQuery(queryCrit));
    }

	QueryByCriteria getQueryForAwardHierarchyByStatus(String awardNumber, final String status) {
		Criteria crit = new Criteria();
    	crit.addLike(AWARD_NUMBER, awardNumber.substring(0, 6) + "%");
		crit.addEqualTo(AWARD_SEQUENCE_STATUS, status);
    	QueryByCriteria queryCrit = QueryFactory.newQuery(Award.class, crit);
		return queryCrit;
	}
    
    @Override
    public Award getActiveAwardVersion(String awardNumber) {
        List<VersionHistory> versions = versionHistoryService.findVersionHistory(Award.class, awardNumber);
        VersionHistory result = getActiveVersionHistory(versions);
        return (result == null) ? null : (Award) result.getSequenceOwner();
    }

    @Override
    public Award getPendingAwardVersion(String awardNumber) {
        List<VersionHistory> versions = versionHistoryService.findVersionHistory(Award.class, awardNumber);
        VersionHistory result = getPendingVersionHistory(versions);
        return (result == null) ? null : (Award) result.getSequenceOwner();
    }

    @Override
    public AwardDocument createAndSaveNewAwardVersion(AwardDocument awardDocument) throws Exception {
        awardDocument.getAward().setNewVersion(true);
        AwardDocument newAwardDocument = awardService.createNewAwardVersion(awardDocument);
        documentService.saveDocument(newAwardDocument);
        awardService.updateAwardSequenceStatus(newAwardDocument.getAward(), VersionStatus.PENDING);
        getVersionHistoryService().updateVersionHistory(newAwardDocument.getAward(), VersionStatus.PENDING,
                globalVariableService.getUserSession().getPrincipalName());
        return newAwardDocument;
    }

    private VersionHistory getPendingVersionHistory (List<VersionHistory> list) {
        VersionHistory returnVal = null;
        for(VersionHistory vh : list) {
            if(vh.getStatus().equals(VersionStatus.PENDING)) {
                returnVal = vh;
            }
        }
        return returnVal;
    }
    
    private VersionHistory getActiveVersionHistory (List<VersionHistory> list) {
        VersionHistory returnVal = null;
        for(VersionHistory vh : list) {
            if(vh.getStatus().equals(VersionStatus.ACTIVE)) {
                returnVal = vh;
            }
        }
        return returnVal;
    }
    
    @Override
    public boolean isPendingAwardInAwardHierarchy(String awardNumber) {
    	return getPersistenceBrokerTemplate().getCount(getQueryForAwardHierarchyByStatus(awardNumber, VersionStatus.PENDING.toString())) != 0;
    }
    
    @Override
    public boolean isActiveAwardInAwardHierarchy(String awardNumber) {
    	return getPersistenceBrokerTemplate().getCount(getQueryForAwardHierarchyByStatus(awardNumber, VersionStatus.ACTIVE.toString())) != 0;    	
    }

    public VersionHistoryService getVersionHistoryService() {
        return versionHistoryService;
    }

    public void setVersionHistoryService(VersionHistoryService versionHistoryService) {
        this.versionHistoryService = versionHistoryService;
    }

	public BusinessObjectService getBusinessObjectService() {
		return businessObjectService;
	}

	public void setBusinessObjectService(BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}
}
