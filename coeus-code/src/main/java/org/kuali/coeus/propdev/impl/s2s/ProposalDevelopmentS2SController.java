/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.propdev.impl.s2s;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentAction;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.propdev.impl.s2s.connect.S2sCommunicationException;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.s2s.service.PrintResult;
import org.kuali.kra.s2s.service.PrintService;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Controller
public class ProposalDevelopmentS2SController extends ProposalDevelopmentControllerBase {

	private static final String ERROR_NO_GRANTS_GOV_FORM_SELECTED = "error.proposalDevelopment.no.grants.gov.form.selected";
	private PrintService printService;
	private static final Log LOG = LogFactory.getLog(ProposalDevelopmentS2SController.class);
	
    @Autowired
    @Qualifier("s2sSubmissionService")
    private S2sSubmissionService s2sSubmissionService;
    
    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=refresh", "refreshCaller=S2sOpportunity-LookupView"})
   public ModelAndView refresh(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)
           throws Exception {
       ProposalDevelopmentDocument document = form.getProposalDevelopmentDocument();
       DevelopmentProposal proposal = document.getDevelopmentProposal();
       if(form.getNewS2sOpportunity() != null 
               && StringUtils.isNotEmpty(form.getNewS2sOpportunity().getOpportunityId())) {
           proposal.setS2sOpportunity(form.getNewS2sOpportunity());
           proposal.getS2sOpportunity().setDevelopmentProposal(proposal);

           //Set Opportunity Title in the Sponsor & Program Information section
           proposal.setProgramAnnouncementTitle(form.getNewS2sOpportunity().getOpportunityTitle());
           form.setNewS2sOpportunity(new S2sOpportunity());
       }

       S2sOpportunity s2sOpportunity = proposal.getS2sOpportunity();
       s2sOpportunity.setS2sProvider(dataObjectService.find(S2sProvider.class, s2sOpportunity.getProviderCode()));
       Boolean mandatoryFormNotAvailable = false;
       List<S2sOppForms> s2sOppForms = new ArrayList<S2sOppForms>();
       try {
           if (s2sOpportunity != null && s2sOpportunity.getSchemaUrl() != null) {
               s2sOppForms = getS2sSubmissionService().parseOpportunityForms(s2sOpportunity);
               proposal.setS2sOppForms(s2sOppForms);
               if(s2sOppForms!=null){
                   for(S2sOppForms s2sOppForm:s2sOppForms){
                       if(s2sOppForm.getMandatory() && !s2sOppForm.getAvailable()){
                           mandatoryFormNotAvailable = true;
                           break;
                       }
                   }
               }
               if(!mandatoryFormNotAvailable){
                   Collections.sort(s2sOppForms, new Comparator<S2sOppForms>() {
                       public int compare(S2sOppForms arg0, S2sOppForms arg1) {
                           int result = arg0.getMandatory().compareTo(arg1.getMandatory())*-1;
                           if (result == 0) {
                               result = arg0.getFormName().compareTo(arg1.getFormName());
                           }
                           return result;
                       }
                   });
                   s2sOpportunity.setS2sOppForms(s2sOppForms);
               }else{
                   GlobalVariables.getMessageMap().putError(Constants.NO_FIELD, KeyConstants.ERROR_IF_OPPORTUNITY_ID_IS_INVALID, s2sOpportunity.getOpportunityId());
                   proposal.setS2sOpportunity(new S2sOpportunity());
               }            
           }
       }catch(S2sCommunicationException ex){
           if(ex.getErrorKey().equals(KeyConstants.ERROR_GRANTSGOV_NO_FORM_ELEMENT)) {
               ex.setMessage(s2sOpportunity.getOpportunityId());
           }
           GlobalVariables.getMessageMap().putError(Constants.NO_FIELD, ex.getErrorKey(),ex.getMessageWithParams());
           proposal.setS2sOpportunity(new S2sOpportunity());
       }
       return getTransactionalDocumentControllerService().refresh(form, result, request, response);
   }
   
   @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=clearOpportunity"})
   public ModelAndView clearOpportunity(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)
           throws Exception {
       ProposalDevelopmentDocument document = form.getProposalDevelopmentDocument();
       DevelopmentProposal proposal = document.getDevelopmentProposal();
       getLegacyDataAdapter().delete(proposal.getS2sOpportunity());
       proposal.setS2sOpportunity(null);
       return getTransactionalDocumentControllerService().refresh(form, result, request, response);
   }

   protected PrintService getPrintService(){
	      if (printService == null)
	      	printService = KcServiceLocator.getService(PrintService.class);
	      return printService;
	  } 
	      
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=printForms")
   public ModelAndView printForms(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
	       HttpServletRequest request, HttpServletResponse response) throws Exception {
	   	   ProposalDevelopmentDocument document = form.getProposalDevelopmentDocument();
	   	   if(document.getDevelopmentProposal().getSelectedS2sOppForms().isEmpty()){    // error, no form is selected
	              GlobalVariables.getMessageMap().putError("noKey", ERROR_NO_GRANTS_GOV_FORM_SELECTED);
	              return getTransactionalDocumentControllerService().refresh(form, result, request, response);
	          }
	   	   PrintResult printResult = getPrintService().printForm(document);
	       setValidationErrorMessage(printResult.getErrors());
	       KcFile attachmentDataSource = printResult.getFile();
	   	   if (document.getDevelopmentProposal().getGrantsGovSelectFlag()) {
	              String loggingDirectory = KcServiceLocator.getService(ConfigurationService.class).getPropertyValueAsString(Constants.PRINT_XML_DIRECTORY);
	              String saveXmlFolderName = document.getSaveXmlFolderName();
	              System.out.println("save xml folder  "+ saveXmlFolderName);
	              if (StringUtils.isNotBlank(loggingDirectory)) {
	                  File directory = new File(loggingDirectory);
	                  if(!directory.exists()){
	                      directory.createNewFile();
	                  }
	                  if(!loggingDirectory.endsWith("/")){
	                      loggingDirectory+="/";
	                  }
	                  File grantsGovXmlDirectoryFile = new File(loggingDirectory + saveXmlFolderName + ".zip");
	                  System.out.println("grantgovxmlfile " +grantsGovXmlDirectoryFile);
	                  byte[] bytes = new byte[(int) grantsGovXmlDirectoryFile.length()];
	                  FileInputStream fileInputStream = new FileInputStream(grantsGovXmlDirectoryFile);
	                  fileInputStream.read(bytes);
	                  ByteArrayOutputStream baos = null;
	                  try {
	                      baos = new ByteArrayOutputStream(bytes.length);
	                      baos.write(bytes);
	                      WebUtils.saveMimeOutputStreamAsFile(response, "binary/octet-stream", baos, saveXmlFolderName + ".zip");
	   
	                  }
	                  finally {
	                      try {
	                          if (baos != null) {
	                              baos.close();
	                              baos = null;
	                          }
	                      }
	                      catch (IOException ioEx) {
	                          LOG.warn(ioEx.getMessage(), ioEx);
	                      }
	                  }
	              }
	              document.getDevelopmentProposal().setGrantsGovSelectFlag(false);
	              //return mapping.findForward(Constants.MAPPING_BASIC);
	              return getTransactionalDocumentControllerService().refresh(form, result, request, response);
	          }
	          if(attachmentDataSource==null || attachmentDataSource.getData()==null){
	              //return mapping.findForward(Constants.MAPPING_PROPOSAL_ACTIONS);
	       	   return getTransactionalDocumentControllerService().refresh(form, result, request, response);
	          }
	          ByteArrayOutputStream baos = null;
	          try{
	              baos = new ByteArrayOutputStream(attachmentDataSource.getData().length);
	              baos.write(attachmentDataSource.getData());
	              WebUtils.saveMimeOutputStreamAsFile(response, attachmentDataSource.getType(), baos, attachmentDataSource.getName());
	              System.out.println(attachmentDataSource.getName());
	          }finally{
	              try{
	                  if(baos!=null){
	                      baos.close();
	                      baos = null;
	                  }
	              }catch(IOException ioEx){
	                  LOG.warn(ioEx.getMessage(), ioEx);
	              }
	          }        
	          return getTransactionalDocumentControllerService().refresh(form, result, request, response);
	      }
	      
	      /**
	      *
	      * This method is to put validation errors on UI
	      *
	      * @param errors
	      *            List of validation errors which has to be displayed on UI.
	      */
	   
	     protected void setValidationErrorMessage(List<org.kuali.kra.s2s.util.AuditError> errors) {
	         if (errors != null) {
	        	 LOG.info("Error list size:" + errors.size() + errors.toString());
	             List<org.kuali.rice.kns.util.AuditError> auditErrors = new ArrayList<>();
	             for (org.kuali.kra.s2s.util.AuditError error : errors) {
	                 auditErrors.add(new org.kuali.rice.kns.util.AuditError(error.getErrorKey(),
	                         Constants.GRANTS_GOV_GENERIC_ERROR_KEY, error.getLink(),
	                         new String[]{error.getMessageKey()}));
	             }
	             if (!auditErrors.isEmpty()) {
	                 KNSGlobalVariables.getAuditErrorMap().put(
	                         "grantsGovAuditErrors",
	                         new AuditCluster(Constants.GRANTS_GOV_OPPORTUNITY_PANEL,
	                                 auditErrors, Constants.GRANTSGOV_ERRORS)
	                 );
	             }
	         }
	     }
   
    public S2sSubmissionService getS2sSubmissionService() {
        return s2sSubmissionService;
    }

    public void setS2sSubmissionService(S2sSubmissionService s2sSubmissionService) {
        this.s2sSubmissionService = s2sSubmissionService;
    }
    
    public DataObjectService getDataObjectService() {
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}
}
