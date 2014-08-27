package org.kuali.coeus.propdev.impl.print;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.kuali.coeus.common.framework.print.PrintConstants;
import org.kuali.coeus.common.proposal.framework.report.CurrentAndPendingReportService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.util.KRADUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
public class CurrentOrPendingOrSelectedReportPrint {
	    
	@Autowired
	@Qualifier("currentAndPendingReportService")
	protected CurrentAndPendingReportService currentAndPendingReportService;
	
	   
	    public ByteArrayInputStream inputStream;
	    protected CurrentAndPendingReportService getCurrentAndPendingReportService (){
	        if (currentAndPendingReportService == null)
	            currentAndPendingReportService = KcServiceLocator.getService (CurrentAndPendingReportService.class);
	        return currentAndPendingReportService;
	    }

		public void setCurrentAndPendingReportService(
				CurrentAndPendingReportService currentAndPendingReportService) {
			this.currentAndPendingReportService = currentAndPendingReportService;
		}
	    /**
	     * Prepare current report (i.e. Awards that selected person is on)
	     * 
	     * @param mapping
	     * @param form
	     * @param request
	     * @param response
	     * @return
	     * @throws Exception
	     */
	    public void printCurrentReportPdf( ProposalDevelopmentDocumentForm form, HttpServletRequest request,
	            HttpServletResponse response) throws Exception {
	    	
	    	
	        CurrentAndPendingReportService currentAndPendingReportService =getCurrentAndPendingReportService();
	        Map<String, Object> reportParameters = new HashMap<String, Object>();
	        reportParameters.put(PrintConstants.PERSON_ID_KEY, form.getDevelopmentProposal().getProposalPerson(0).getPersonId());
	        reportParameters.put(PrintConstants.REPORT_PERSON_NAME_KEY, form.getDevelopmentProposal().getProposalPerson(0).getFullName());
	        KcFile attachmentDataSource = currentAndPendingReportService.printCurrentAndPendingSupportReport(
	                PrintConstants.CURRENT_REPORT_TYPE, reportParameters);
	        streamToResponse(attachmentDataSource, response);
	        
	    }

	    /**
	     * Prepare pending report (i.e. InstitutionalProposals that selected person is on) {@inheritDoc}
	     */
	    public void printPendingReportPdf(ProposalDevelopmentDocumentForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
	        CurrentAndPendingReportService currentAndPendingReportService =getCurrentAndPendingReportService();
	        Map<String, Object> reportParameters = new HashMap<String, Object>();
	        reportParameters.put(PrintConstants.PERSON_ID_KEY, form.getDevelopmentProposal().getProposalPerson(0).getPersonId());
	        reportParameters.put(PrintConstants.REPORT_PERSON_NAME_KEY, form.getDevelopmentProposal().getProposalPerson(0).getFullName());
	        KcFile attachmentDataSource = currentAndPendingReportService.printCurrentAndPendingSupportReport(
	                PrintConstants.PENDING_REPORT_TYPE, reportParameters);
	        streamToResponse(attachmentDataSource, response);
	        
	    }
	    
	    /**
	     *  print selected templates
	     */
	    public void printSelectedTemplates(ProposalDevelopmentDocumentForm form, HttpServletRequest request, HttpServletResponse response, ProposalDevelopmentPrintingService proposalDevelopmentPrintingService) throws Exception {
	    	Map<String, Object> reportParams = new HashMap<String, Object >();
	    	reportParams.put(ProposalDevelopmentPrintingService.SELECTED_TEMPLATES, form.getSponsorFormTemplates());

	    	KcFile attachmentDataSource = proposalDevelopmentPrintingService.printProposalDevelopmentReport(
	    			form.getDevelopmentProposal(), 
	    			ProposalDevelopmentPrintingService.PRINT_PROPOSAL_SPONSOR_FORMS,reportParams );
	    		streamToResponse(attachmentDataSource, response);
	    	
	    }

	    protected void streamToResponse(KcFile attachmentDataSource, HttpServletResponse response) throws Exception {
            byte[] data = attachmentDataSource.getData();
            long size = data.length;
        try ( ByteArrayInputStream inputStream = new ByteArrayInputStream(data)) {
            KRADUtils.addAttachmentToResponse(response,inputStream,attachmentDataSource.getType(),attachmentDataSource.getName(),size);
            response.flushBuffer();
        }
    }
	}