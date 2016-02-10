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
package org.kuali.coeus.dc;

import org.kuali.coeus.dc.access.kim.*;
import org.kuali.coeus.dc.common.rice.parameter.ParameterDao;
import org.kuali.coeus.dc.common.rice.parameter.ParameterDaoImpl;
import org.kuali.coeus.dc.access.proposal.*;
import org.kuali.coeus.dc.award.amntinfo.AwardAmountInfoDuplicatesDao;
import org.kuali.coeus.dc.award.amntinfo.AwardAmountInfoDuplicatesDaoImpl;
import org.kuali.coeus.dc.common.db.*;
import org.kuali.coeus.dc.pprole.ProposalPersonRoleDao;
import org.kuali.coeus.dc.pprole.ProposalPersonRoleDaoImpl;
import org.kuali.coeus.dc.questseq.QuestSeqDao;
import org.kuali.coeus.dc.questseq.QuestSeqDaoImpl;
import org.kuali.coeus.dc.subaward.SubAwardAmountInfoDao;
import org.kuali.coeus.dc.subaward.SubAwardAmountInfoDaoImpl;
import org.kuali.coeus.dc.tm.KewDocHeaderDao;
import org.kuali.coeus.dc.tm.KewDocHeaderDaoImpl;
import org.kuali.coeus.dc.tm.TimeAndMoneyDocumentStatusDao;
import org.kuali.coeus.dc.tm.TimeAndMoneyDocumentStatusDaoImpl;

public final class CliOptionsBasedDaoFactory {

    private CliOptions cliOptions;

    public DbValidatorDaoService getDbValidatorDaoService(){
        if (cliOptions.isMySql()) {
            DbValidatorDaoServiceMySqlImpl service = new DbValidatorDaoServiceMySqlImpl();
            service.setConnectionService(getConnectionDaoService());
            return service;
        } else if(cliOptions.isOracle()) {
            DbValidatorDaoServiceOracleImpl service = new DbValidatorDaoServiceOracleImpl();
            service.setConnectionService(getConnectionDaoService());
            return service;
        }
        return null;
    }

    public ConnectionDaoService getConnectionDaoService() {
        if (cliOptions.isMySql()) {
            ConnectionDaoServiceMySqlImpl connectionService = new ConnectionDaoServiceMySqlImpl();
            connectionService.setCoeusConnectionString(cliOptions.getCoeusConnectionString());
            connectionService.setRiceConnectionString(cliOptions.getRiceConnectionString());
            connectionService.setCoeusUser(cliOptions.getCoeusUser());
            connectionService.setRiceUser(cliOptions.getRiceUser());
            connectionService.setCoeusPassword(cliOptions.getCoeusPassword());
            connectionService.setRicePassword(cliOptions.getRicePassword());
            return connectionService;
        } else if(cliOptions.isOracle()) {
            ConnectionDaoServiceOracleImpl connectionService = new ConnectionDaoServiceOracleImpl();
            connectionService.setCoeusConnectionString(cliOptions.getCoeusConnectionString());
            connectionService.setRiceConnectionString(cliOptions.getRiceConnectionString());
            connectionService.setCoeusUser(cliOptions.getCoeusUser());
            connectionService.setRiceUser(cliOptions.getRiceUser());
            connectionService.setCoeusPassword(cliOptions.getCoeusPassword());
            connectionService.setRicePassword(cliOptions.getRicePassword());
            return connectionService;
        }
        return null;
    }

    public RoleDao getRoleDao() {
        RoleDaoImpl dao = new RoleDaoImpl();
        dao.setConnectionDaoService(getConnectionDaoService());
        dao.setKimTypeDao(getKimTypeDao());
        dao.setSequenceDaoService(getSequenceDaoService());

        return dao;
    }

    public KimTypeDao getKimTypeDao() {
        KimTypeDaoImpl dao = new KimTypeDaoImpl();
        dao.setConnectionService(getConnectionDaoService());
        return dao;
    }

    public SequenceDaoService getSequenceDaoService() {
        if (cliOptions.isMySql()) {
            SequenceDaoServiceMySqlImpl sequenceDaoService = new SequenceDaoServiceMySqlImpl();
            sequenceDaoService.setConnectionDaoService(getConnectionDaoService());
            return sequenceDaoService;
        } else if(cliOptions.isOracle()) {
            SequenceDaoServiceOracleImpl sequenceDaoService = new SequenceDaoServiceOracleImpl();
            sequenceDaoService.setConnectionDaoService(getConnectionDaoService());
            return sequenceDaoService;
        }
        return null;
    }

    public ProposalKimAttributeDefnDao getProposalKimAttributeDefnDao() {
        KimAttributeDefnDaoImpl dao = new KimAttributeDefnDaoImpl();
        dao.setConnectionDaoService(getConnectionDaoService());
        return dao;
    }

    public KimAttributeDocumentValueHandler getProposalKimAttributeDocumentValueHandler() {
        ProposalKimAttributeDocumentValueHandler handler = new ProposalKimAttributeDocumentValueHandler();
        handler.setProposalKimAttributeDefnDao(getProposalKimAttributeDefnDao());
        handler.setConnectionDaoService(getConnectionDaoService());
        handler.setDelete(cliOptions.deleteCleanupPolicy());
        return handler;
    }

    public ProposalRoleDao getProposalRoleDao() {
        ProposalRoleDaoImpl rs = new ProposalRoleDaoImpl();
        rs.setConnectionDaoService(getConnectionDaoService());
        return rs;
    }

    public ParameterDao getParameterDao() {
        ParameterDaoImpl ps = new ParameterDaoImpl();
        ps.setConnectionDaoService(getConnectionDaoService());
        return ps;
    }

    public ProposalPersonRoleDao getProposalPersonRoleDao() {
        ProposalPersonRoleDaoImpl ps = new ProposalPersonRoleDaoImpl();
        ps.setConnectionDaoService(getConnectionDaoService());
        ps.setParameterDao(getParameterDao());
        return ps;
    }

    public QuestSeqDao getQuestSeqDao() {
        QuestSeqDaoImpl qsd = new QuestSeqDaoImpl();
        qsd.setConnectionDaoService(getConnectionDaoService());
        return qsd;
    }
    
    public KewDocHeaderDao getKewDocHeaderDao() {
    	KewDocHeaderDaoImpl docHeaderDao = new KewDocHeaderDaoImpl();
    	docHeaderDao.setConnectionDaoService(getConnectionDaoService());
    	return docHeaderDao;
    }
    
    public TimeAndMoneyDocumentStatusDao getTimeAndMoneyDocumentStatusDao() {
    	TimeAndMoneyDocumentStatusDaoImpl dao = new TimeAndMoneyDocumentStatusDaoImpl();
    	dao.setConnectionDaoService(getConnectionDaoService());
    	dao.setKewDocHeaderDao(getKewDocHeaderDao());
    	return dao;
    }
    
    public SubAwardAmountInfoDao getSubAwardAmountInfoDao() {
    	SubAwardAmountInfoDaoImpl dao = new SubAwardAmountInfoDaoImpl();
    	dao.setConnectionDaoService(getConnectionDaoService());
    	return dao;
    }
    
    public AwardAmountInfoDuplicatesDao getAwardAmountInfoDuplicatesDao() {
    	AwardAmountInfoDuplicatesDaoImpl dao = new AwardAmountInfoDuplicatesDaoImpl();
    	dao.setConnectionDaoService(getConnectionDaoService());
    	return dao;
    }

    public CliOptions getCliOptions() {
        return cliOptions;
    }

    public void setCliOptions(CliOptions cliOptions) {
        this.cliOptions = cliOptions;
    }
}
