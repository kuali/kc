/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.web.struts.action;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.WebUtils;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.RoleRight;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RightConstants;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.NarrativeStatus;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.proposaldevelopment.bo.NarrativeUserRights;
import org.kuali.kra.proposaldevelopment.bo.ProposalUserRoles;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;

public class ProposalDevelopmentAbstractsAttachmentsAction extends ProposalDevelopmentAction {
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentAbstractsAttachmentsAction.class);

    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pd = proposalDevelopmentForm.getProposalDevelopmentDocument();
        List<Narrative> narrList = pd.getNarratives();
        for (Narrative narrative : narrList) {
            populateNarrativeUserRights(pd, narrative);
        }
        return super.save(mapping, form, request, response);
    }

    public ActionForward addProposalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument propDoc = proposalDevelopmentForm.getProposalDevelopmentDocument();
        Narrative narr = propDoc.getNewNarrative();
        narr.setProposalNumber(propDoc.getProposalNumber());
        narr.setModuleNumber(propDoc.getProposalNextValue(Constants.NARRATIVE_MODULE_NUMBER));
        narr.setModuleSequenceNumber(propDoc.getProposalNextValue(Constants.NARRATIVE_MODULE_SEQUENCE_NUMBER));
        narr.setUpdateUser(propDoc.getUpdateUser());
        narr.setUpdateTimestamp(propDoc.getUpdateTimestamp());

        Map narrTypeMap = new HashMap();
        narrTypeMap.put("narrativeTypeCode", narr.getNarrativeTypeCode());
        BusinessObjectService service = getService(BusinessObjectService.class);
        NarrativeType narrType = (NarrativeType) service.findByPrimaryKey(NarrativeType.class, narrTypeMap);
        if (narrType != null)
            narr.setNarrativeType(narrType);

        Map narrStatusMap = new HashMap();
        narrStatusMap.put("narrativeStatusCode", narr.getModuleStatusCode());
        NarrativeStatus narrStatus = (NarrativeStatus) service.findByPrimaryKey(NarrativeStatus.class, narrStatusMap);
        if (narrStatus != null)
            narr.setNarrativeStatus(narrStatus);
        populateNarrativeAttachment(narr);
        populateNarrativeUserRights(propDoc, narr);
        propDoc.getNarratives().add(narr);
        propDoc.setNewNarrative(new Narrative());

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    private void populateNarrativeAttachment(Narrative narr) throws IOException{
        FormFile narrFile = narr.getNarrativeFile();
        byte[] fileData = narrFile.getFileData();
        if (fileData.length > 0) {
            LOG.info("File exists. Creating new NarrativeAttachment and adding it to Narrative collection ");
            NarrativeAttachment narrAtt;
            if (narr.getNarrativeAttachmentList().isEmpty()){
                narrAtt = new NarrativeAttachment(); 
            }else{
                narrAtt = narr.getNarrativeAttachmentList().get(0);
            }
             
            narrAtt.setFileName(narrFile.getFileName());
            narrAtt.setContentType(narrFile.getContentType());
            narrAtt.setNarrativeData(narrFile.getFileData());
            narrAtt.setProposalNumber(narr.getProposalNumber());
            narrAtt.setModuleNumber(narr.getModuleNumber());
            narr.setFileName(narrAtt.getFileName());
//            if (narr.getNarrativeAttachmentList().isEmpty())
//                narr.getNarrativeAttachmentList().add(narrPdf);
//            else
//                narr.getNarrativeAttachmentList().set(0, narrPdf);
        }
    }
    private void populateNarrativeUserRights(ProposalDevelopmentDocument pd, Narrative narr) {
        List<NarrativeUserRights> narrUserRights = narr.getNarrativeUserRights();
        // Collection<ProposalUserRoles> usrRights = getBusinessObjectService().findMatching(ProposalUserRoles.class, proRoleMap);
        List<ProposalUserRoles> usrRights = pd.getProposalUserRoles();
        for (ProposalUserRoles proposalUserRoles : usrRights) {
            boolean continueFlag = false;
            Map personVal = new HashMap();
            personVal.put("personId", proposalUserRoles.getUserId());
            Person per = (Person) getBusinessObjectService().findByPrimaryKey(Person.class, personVal);
            String personName = per.getFirstName() + " " + per.getLastName();
            for (NarrativeUserRights tempNarrUserRight : narrUserRights) {
                if (tempNarrUserRight.getUserId().equalsIgnoreCase(proposalUserRoles.getUserId())) {
                    continueFlag = true;
                    tempNarrUserRight.setPersonName(personName);
                    break;
                }
            }
            if (continueFlag)
                continue;
            Map proRoleMap = new HashMap();
            proRoleMap.put("roleId", proposalUserRoles.getRoleId());
            // List<RoleRights> roleRights = proposalUserRoles.getRoleRightsList();
            Collection<RoleRight> roleRights = getBusinessObjectService().findMatching(RoleRight.class, proRoleMap);
            String accessType = "N";
            for (RoleRight roleRight : roleRights) {
                if (roleRight.getRightId().equals(RightConstants.VIEW_NARRATIVE)) {// create RightConstants class and define these
                    // there
                    accessType = "R";
                }
                else if (roleRight.getRightId().equals(RightConstants.MODIFY_NARRATIVE)) {
                    accessType = "M";
                }
            }
            NarrativeUserRights narrUserRight = new NarrativeUserRights();
            narrUserRight.setProposalNumber(narr.getProposalNumber());
            narrUserRight.setModuleNumber(narr.getModuleNumber());
            narrUserRight.setUserId(proposalUserRoles.getUserId());
            narrUserRight.setAccessType(accessType);
            narrUserRight.setPersonName(personName);
            narrUserRights.add(narrUserRight);
        }

    }

    public ActionForward downloadProposalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        String line = request.getParameter("line");
        int lineNumber = line == null ? 0 : Integer.parseInt(line);
        ProposalDevelopmentDocument pd = proposalDevelopmentForm.getProposalDevelopmentDocument();
        Narrative narr = pd.getNarratives().get(lineNumber);
        NarrativeAttachment narrAttachment = findNarrativeAttachment(narr);
        if(narrAttachment==null && !narr.getNarrativeAttachmentList().isEmpty()){//get it from the memory
            narrAttachment = narr.getNarrativeAttachmentList().get(0);
        }
        streamToResponse(narrAttachment,response);
//        byte[] xbts = narrAttachment.getContent();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream(xbts.length);
//        baos.write(xbts);
//        WebUtils.saveMimeOutputStreamAsFile(response, narrAttachment.getContentType(), baos, narrAttachment.getFileName());
        return null;
    }
    private NarrativeAttachment findNarrativeAttachment(Narrative narr){
        Map narrAttVal = new HashMap();
        narrAttVal.put("proposalNumber", narr.getProposalNumber());
        narrAttVal.put("moduleNumber", narr.getModuleNumber());
        return (NarrativeAttachment)getBusinessObjectService().findByPrimaryKey(NarrativeAttachment.class, narrAttVal);
    }
    public void streamToResponse(AttachmentDataSource dataSource,HttpServletResponse response) throws Exception{
        byte[] xbts = dataSource.getContent();
        ByteArrayOutputStream baos = null;
        try{
            baos = new ByteArrayOutputStream(xbts.length);
            baos.write(xbts);
            WebUtils.saveMimeOutputStreamAsFile(response, dataSource.getContentType(), baos, dataSource.getFileName());
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
    }

    public ActionForward deleteProposalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        Narrative narr = proposalDevelopmentForm.getProposalDevelopmentDocument().getNarratives().get(getLineToDelete(request));
        NarrativeAttachment narrAtt = new NarrativeAttachment();
        narrAtt.setProposalNumber(narr.getProposalNumber());
        narrAtt.setModuleNumber(narr.getModuleNumber());
        if (narr.getNarrativeAttachmentList().isEmpty())
            narr.getNarrativeAttachmentList().add(narrAtt);
        else
            narr.getNarrativeAttachmentList().set(0, narrAtt);
        proposalDevelopmentForm.getProposalDevelopmentDocument().getNarratives().remove(getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward getProposalAttachmentRights(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        proposalDevelopmentForm.setShowMaintenanceLinks(false);
        String line = request.getParameter("line");
        int lineNumber = line == null ? 0 : Integer.parseInt(line);
        ProposalDevelopmentDocument pd = proposalDevelopmentForm.getProposalDevelopmentDocument();
        Narrative narr = pd.getNarratives().get(lineNumber);
        populateNarrativeUserRights(pd, narr);
        request.setAttribute("line", line);
        // proposalDevelopmentForm.getProposalDevelopmentDocument().getNarratives().remove(getLineToDelete(request));
        return mapping.findForward("attachmentRights");
    }

    public ActionForward addProposalAttachmentRights(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return mapping.findForward(Constants.MAPPING_CLOSE_PAGE);
    }

    
    public ActionForward replaceProposalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pd = proposalDevelopmentForm.getProposalDevelopmentDocument();
        Narrative narr = pd.getNarratives().get(getLineToDelete(request));
        NarrativeAttachment narrAtt = findNarrativeAttachment(narr);
        if (narr.getNarrativeAttachmentList().isEmpty())
            narr.getNarrativeAttachmentList().add(narrAtt);
        else
            narr.getNarrativeAttachmentList().set(0, narrAtt);
        populateNarrativeAttachment(narr);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    private BusinessObjectService getBusinessObjectService() {
        return getService(BusinessObjectService.class);
    }

}
