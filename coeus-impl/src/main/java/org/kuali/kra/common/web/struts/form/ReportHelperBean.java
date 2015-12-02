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
package org.kuali.kra.common.web.struts.form;

import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.proposal.framework.report.CurrentAndPendingReportService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.framework.print.CurrentReportBean;
import org.kuali.coeus.common.framework.print.PendingReportBean;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.ui.ResultRow;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.io.Serializable;
import java.util.*;

/**
 *  Helper to prepare Current and Pending Report
 */
public class ReportHelperBean implements Serializable {
    private KualiDocumentFormBase form;
    private String personId;
    private KcPerson targetPerson;
    private boolean institutionalProposalExists;
    private String proposalNumber;
    private static final String DEV_PROPOSAL_NUMBER_FIELD_NAME = "devProposalNumber";
    private static final String PROP_SEQ_STATUS = "ACTIVE";
    private static final String PROP_NUMBER = "proposalNumber";
    private static final int PROP_TYPE_CONTINUATION = 4;
    private static final int PROP_TYPE_TASK_ORDER = 6;
    private static final int PROP_PENDING_STATUS = 1;

    public ReportHelperBean(KualiDocumentFormBase form) {
        this.form = form;
        setTargetPerson(new KcPerson());
        if(form.getDocument() instanceof InstitutionalProposalDocument) {
            institutionalProposalExists = true;
            proposalNumber = findProposalNumberFromInstitutionalProposal();
        } else if(form.getDocument() instanceof ProposalDevelopmentDocument) {
            institutionalProposalExists = doesInstitutionalProposalExistForProposalNumber();
            proposalNumber = findProposalNumberFromDevelopmentProposal();
        }
    }
    public ReportHelperBean() {
        setTargetPerson(new KcPerson());
        
    }
    public String getPersonId() {
        return personId;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public KcPerson getTargetPerson() {
        return targetPerson;
    }

    public boolean isInstituteProposalAvailable() {
        return institutionalProposalExists;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
        if(personId != null) {
            targetPerson = getKcPersonService().getKcPersonByPersonId(personId);
        }
    }

    public void setTargetPerson(KcPerson targetPerson) {
        this.targetPerson = targetPerson;
    }

    public List<ResultRow> prepareCurrentReport() {
        return new CurrentReportHelperBean().prepareCurrentReport();
    }

    public List<ResultRow> preparePendingReport() {
        return new PendingReportHelperBean().preparePendingReport();
    }

    public String getTargetPersonName() {
        return targetPerson.getFullName();
    }

    protected boolean doesInstitutionalProposalExistForProposalNumber() {
        Map map = Collections.singletonMap(DEV_PROPOSAL_NUMBER_FIELD_NAME, findProposalNumberFromDevelopmentProposal());
        return ((Collection<ProposalAdminDetails>)getBusinessObjectService().findMatching(ProposalAdminDetails.class, map)).stream()
        	.anyMatch(adminDetails -> adminDetails.getInstProposalId() != null);
    }

    protected BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }

    protected KcPersonService getKcPersonService() {
        return KcServiceLocator.getService(KcPersonService.class);
    }

    protected CurrentAndPendingReportService getCurrentAndPendingReportService() {
        return KcServiceLocator.getService(CurrentAndPendingReportService.class);
    }

    private String findProposalNumberFromDevelopmentProposal() {
        return ((ProposalDevelopmentDocument) form.getDocument()).getDevelopmentProposal().getProposalNumber();
    }

    private String findProposalNumberFromInstitutionalProposal() {
        return ((InstitutionalProposalDocument) form.getDocument()).getInstitutionalProposal().getProposalNumber();
    }

    private class PendingReportHelperBean implements Serializable {

        public List<ResultRow> preparePendingReport() {
            List<ResultRow> resultRows = new ArrayList<ResultRow>();
            Map<String, String> proposalNumberMap = new HashMap<String, String>();
            List<InstitutionalProposal> institutionalProposalList = null;  
            
            for(PendingReportBean bean: loadReportData()) {
                proposalNumberMap.put(PROP_NUMBER, String.valueOf(bean.getProposalNumber()));
                institutionalProposalList = (List<InstitutionalProposal>) getBusinessObjectService()
                                        .findMatching(InstitutionalProposal.class,proposalNumberMap);
                for(InstitutionalProposal institutionalProposal:institutionalProposalList){
                    
                    if(institutionalProposal.getProposalSequenceStatus().equals(PROP_SEQ_STATUS) && institutionalProposal.getStatusCode()== PROP_PENDING_STATUS 
                            && institutionalProposal.getProposalTypeCode()!= PROP_TYPE_CONTINUATION && institutionalProposal.getProposalTypeCode()!= PROP_TYPE_TASK_ORDER ){
                   
                        resultRows.add(bean.createResultRow());
                    }
                }
            }

            return resultRows;
        }

        private List<PendingReportBean> loadReportData() {
            return getCurrentAndPendingReportService().loadPendingReportData(personId);
        }
    }

    private class CurrentReportHelperBean implements Serializable {
        public List<ResultRow> prepareCurrentReport() {
            List<ResultRow> resultRows = new ArrayList<ResultRow>();
            for(CurrentReportBean bean: loadReportData()) {
                resultRows.add(bean.createResultRow());
            }

            return resultRows;
        }

        private List<CurrentReportBean> loadReportData() {
            return getCurrentAndPendingReportService().loadCurrentReportData(personId);
        }
    }
}
