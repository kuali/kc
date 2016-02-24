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
package org.kuali.coeus.propdev.impl.lock;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.impl.lock.KcPessimisticLockService;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.service.DataDictionaryService;
import org.kuali.rice.krad.service.PessimisticLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component("proposalBudgetLockService")
@Transactional
public class ProposalBudgetLockServiceImpl implements ProposalBudgetLockService {

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Autowired
    @Qualifier("dataDictionaryService")
    protected DataDictionaryService dataDictionaryService;

    @Autowired
    @Qualifier("globalVariableService")
    protected GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("pessimisticLockService")
    protected PessimisticLockService pessimisticLockService;

    @Autowired
    @Qualifier("kcPessimisticLockService")
    protected KcPessimisticLockService KcPessimisticLockService;

    @Override
    public void establishBudgetLock(ProposalDevelopmentBudgetExt budget){
        ProposalDevelopmentDocument document = budget.getDevelopmentProposal().getProposalDocument();
        getPessimisticLockService().releaseAllLocksForUser(document.getPessimisticLocks(),getGlobalVariableService().getUserSession().getPerson());
        if (getKcPessimisticLockService().isPessimisticLockNeeded(document, getGlobalVariableService().getUserSession().getPerson(),true,
                getBudgetLockDescriptor(document.getDevelopmentProposal().getProposalNumber(),budget.getBudgetVersionNumber()))) {
            PessimisticLock pessimisticLock = getPessimisticLockService().generateNewLock(document.getDocumentNumber(),
                    getBudgetLockDescriptor(document.getDevelopmentProposal().getProposalNumber(),
                            budget.getBudgetVersionNumber()),
                            getGlobalVariableService().getUserSession().getPerson());
            document.addPessimisticLock(pessimisticLock);
        }
    }

    @Override
    public void deleteBudgetLock(ProposalDevelopmentBudgetExt budget) {
        ProposalDevelopmentDocument document = budget.getDevelopmentProposal().getProposalDocument();
        for (PessimisticLock lock : document.getPessimisticLocks()) {
            if (lock.isOwnedByUser(getGlobalVariableService().getUserSession().getPerson()) &&
                    doesBudgetVersionMatchDescriptor(lock.getLockDescriptor(), budget.getBudgetVersionNumber())) {
                getDataObjectService().delete(lock);
            }
        }
        document.refreshPessimisticLocks();
    }

    @Override
    public boolean doesBudgetVersionMatchDescriptor(String lockDescriptor, int budgetVersionNumber) {
        String[] lockDescriptorValues = StringUtils.split(lockDescriptor, "-");
        return lockDescriptorValues != null && lockDescriptorValues.length == 3 &&
                StringUtils.equals(lockDescriptorValues[1], KraAuthorizationConstants.LOCK_DESCRIPTOR_BUDGET) &&
                NumberUtils.isNumber(lockDescriptorValues[2]) &&
                Integer.parseInt(lockDescriptorValues[2]) == budgetVersionNumber;
    }

    protected String getBudgetLockDescriptor(String proposalNumber, int budgetVersion) {
        return proposalNumber + "-" + KraAuthorizationConstants.LOCK_DESCRIPTOR_BUDGET + "-" +budgetVersion;
    }

    public DataDictionaryService getDataDictionaryService() {
        return dataDictionaryService;
    }

    public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
        this.dataDictionaryService = dataDictionaryService;
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public PessimisticLockService getPessimisticLockService() {
        return pessimisticLockService;
    }

    public void setPessimisticLockService(PessimisticLockService pessimisticLockService) {
        this.pessimisticLockService = pessimisticLockService;
    }

    public KcPessimisticLockService getKcPessimisticLockService() {
        return KcPessimisticLockService;
    }

    public void setKcPessimisticLockService(KcPessimisticLockService kcPessimisticLockService) {
        KcPessimisticLockService = kcPessimisticLockService;
    }
}
