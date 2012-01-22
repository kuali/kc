package org.kuali.kra.common.web.struts.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.common.printing.CurrentReportBean;
import org.kuali.kra.common.printing.PendingReportBean;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.kra.printing.service.CurrentAndPendingReportService;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.ui.ResultRow;
import org.kuali.rice.krad.service.BusinessObjectService;

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
        return findProposalAdminDetails() != null;
    }

    protected ProposalAdminDetails findProposalAdminDetails() {
        Map map = Collections.singletonMap(DEV_PROPOSAL_NUMBER_FIELD_NAME, findProposalNumberFromDevelopmentProposal());
        Collection proposalAdminDetailses = getBusinessObjectService().findMatching(ProposalAdminDetails.class, map);
        return proposalAdminDetailses.size() > 0 ? ((ProposalAdminDetails) proposalAdminDetailses.iterator().next()) : null;
    }

    protected BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    protected KcPersonService getKcPersonService() {
        return KraServiceLocator.getService(KcPersonService.class);
    }

    protected CurrentAndPendingReportService getCurrentAndPendingReportService() {
        return KraServiceLocator.getService(CurrentAndPendingReportService.class);
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
