package org.kuali.coeus.sys.framework.controller;

import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.bo.AdHocRouteRecipient;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.exception.DocumentAuthorizationException;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/** public & protected methods from Rice's DocumentControllerBase */
public interface DocumentControllerService extends UifControllerService, KcCommonControllerService {

    //public methods

    ModelAndView docHandler(DocumentFormBase form, BindingResult result,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception;

    ModelAndView reload(DocumentFormBase form, BindingResult result,
                               HttpServletRequest request, HttpServletResponse response) throws Exception;

    ModelAndView cancel(UifFormBase form, BindingResult result,
                               HttpServletRequest request, HttpServletResponse response);

    ModelAndView save(DocumentFormBase form, BindingResult result,
                             HttpServletRequest request, HttpServletResponse response) throws Exception;

    ModelAndView complete(DocumentFormBase form, BindingResult result,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception;

    ModelAndView route(DocumentFormBase form, BindingResult result,
                              HttpServletRequest request, HttpServletResponse response);

    ModelAndView blanketApprove(DocumentFormBase form, BindingResult result,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception;

    ModelAndView approve(DocumentFormBase form, BindingResult result,
                                HttpServletRequest request, HttpServletResponse response) throws Exception;

    ModelAndView disapprove(DocumentFormBase form, BindingResult result,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception;

    ModelAndView fyi(DocumentFormBase form, BindingResult result,
                            HttpServletRequest request, HttpServletResponse response) throws Exception;

    ModelAndView acknowledge(DocumentFormBase form, BindingResult result,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception;

    ModelAndView sendAdHocRequests(DocumentFormBase form, BindingResult result,
                                          HttpServletRequest request, HttpServletResponse response);

    ModelAndView supervisorFunctions(DocumentFormBase form, BindingResult result,
                                            HttpServletRequest request, HttpServletResponse response) throws Exception;

    ModelAndView insertNote(UifFormBase uifForm, BindingResult result,
                                   HttpServletRequest request, HttpServletResponse response);

    ModelAndView deleteNote(UifFormBase uifForm, BindingResult result,
                                   HttpServletRequest request, HttpServletResponse response);

    ModelAndView downloadAttachment(UifFormBase uifForm, BindingResult result,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws ServletRequestBindingException, FileNotFoundException, IOException;

    ModelAndView downloadBOAttachment(UifFormBase uifForm, BindingResult result,
                                             HttpServletRequest request,
                                             HttpServletResponse response) throws ServletRequestBindingException, FileNotFoundException, IOException;
    
    ModelAndView cancelAttachment(UifFormBase uifForm, BindingResult result,
                                         HttpServletRequest request, HttpServletResponse response);
    
    //protected methods

    @Override
    DocumentFormBase createInitialForm(HttpServletRequest request);

    void loadDocument(DocumentFormBase form) throws WorkflowException;

    void createDocument(DocumentFormBase form) throws WorkflowException;

    void performWorkflowAction(DocumentFormBase form, UifConstants.WorkflowAction action, boolean checkSensitiveData);

    String checkAndWarnAboutSensitiveData(DocumentFormBase form, HttpServletRequest request,
                                                    HttpServletResponse response, String fieldName, String fieldValue, String caller,
                                                    String context) throws Exception;
    
    List<AdHocRouteRecipient> combineAdHocRecipients(DocumentFormBase form);
    
    DocumentAuthorizationException buildAuthorizationException(String action, Document document);
}
