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
package org.kuali.kra.irb.actions.notification;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.print.ProtocolXmlStream;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.ken.service.NotificationService;
import org.kuali.rice.ken.util.Util;
import org.kuali.rice.kew.util.XmlHelper;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kim.service.RoleService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

/**
 * 
 * This class implements action notification api
 */
public class ProtocolActionsNotificationServiceImpl implements ProtocolActionsNotificationService {

    private NotificationService notificationService;
    private RoleService kimRoleManagementService;
    private KcPersonService kcPersonService;
    private List<String> notificationTemplates;
    private KualiConfigurationService kualiConfigurationService;
    private ProtocolXmlStream protocolXmlStream;
    private DocumentService documentService;
    private ProtocolOnlineReviewService protocolOnlineReviewService;
    /**
     * 
     * @see org.kuali.kra.irb.actions.notification.ProtocolActionsNotificationService#sendActionsNotification(org.kuali.kra.irb.Protocol,
     *      org.kuali.kra.irb.actions.notification.NotificationEventBase)
     */
    public void sendActionsNotification(Protocol protocol, NotificationEventBase notificationEvent) throws Exception {
        String actionNotificationTemplate = notificationTemplates.get(0);
        InputStream inputStream = this.getClass().getResourceAsStream(actionNotificationTemplate);
        Document notificationRequestDocument;

        try {
            // transForm();
            notificationRequestDocument = Util.parse(new InputSource(inputStream), false, false, null);
            Element recipients = (Element) notificationRequestDocument.getElementsByTagName("recipients").item(0);
            notificationEvent.getRecipients(recipients);

            Element sender = (Element) notificationRequestDocument.getElementsByTagName("sender").item(0);
            sender.setTextContent(GlobalVariables.getUserSession().getPrincipalName());

             Element message = (Element) notificationRequestDocument.getElementsByTagName("message").item(0);
            // message.setTextContent(getTransFormData(protocol, notificationEvent.getTemplatePath()));
            String messageBody = getTransFormData(protocol, notificationEvent.getTemplate());
            if (ProtocolActionType.NOTIFY_IRB.equals(notificationEvent.getActionTypeCode())) {
                // this url is needed for embedded mode
                String applicationUrl = kualiConfigurationService.getPropertyString(KNSConstants.APPLICATION_URL_KEY);
                messageBody = messageBody.replace("submissionId=", "submissionId="+protocol.getProtocolSubmission().getSubmissionId());
                messageBody = messageBody.replace("../", applicationUrl + "/");
            } 
            if (ReviewCompleteEvent.REVIEW_COMPLETE.equals(notificationEvent.getActionTypeCode())) {
                // this url is needed for embedded mode
                messageBody = messageBody.replace("reviewerNameHolder", GlobalVariables.getUserSession().getPerson().getName());
            } 
            messageBody = messageBody.replace("$amp;", "&amp;");
            
            message.setTextContent(messageBody);

            Element title = (Element) notificationRequestDocument.getElementsByTagName("title").item(0);
            title.setTextContent(notificationEvent.getTitle());

            Element sendDateTime = (Element) notificationRequestDocument.getElementsByTagName("sendDateTime").item(0);
            sendDateTime.setTextContent(Util.toXSDDateTimeString(Calendar.getInstance().getTime()));
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        String XML = Util.writeNode(notificationRequestDocument, true);
        // Waiting for rice KEN bootstrap to be corrected
        notificationService.sendNotification(XML);
    }


    /**
     * 
     * @see org.kuali.kra.irb.actions.notification.ProtocolActionsNotificationService#addIrbAdminToRecipients(org.w3c.dom.Element,
     *      org.kuali.kra.irb.Protocol)
     */
    public void addIrbAdminToRecipients(Element recipients, Protocol protocol, List<String> userNames) {
        Collection<String> ids = kimRoleManagementService
                .getRoleMemberPrincipalIds("KC-UNT", RoleConstants.IRB_ADMINISTRATOR, null);
        for (String id : ids) {
            KcPerson kcPersons = kcPersonService.getKcPersonByPersonId(id);
            try {
                if (StringUtils.isNotBlank(kcPersons.getUserName()) && !userNames.contains(kcPersons.getUserName())) {
//                        && (userNames.isEmpty() || StringUtils.isBlank(protocol.getPrincipalInvestigator().getPersonId()) || !kcPersons.getUserName()
//                                .equals(protocol.getPrincipalInvestigator().getPerson().getUserName()))) {
                   // if (!userNames.contains(kcPersons.getUserName())) {
                        XmlHelper.appendXml(recipients, "<user>" + kcPersons.getUserName() + "</user>");
                        userNames.add(kcPersons.getUserName());
                  //  }
                }
            }
            catch (Exception e) {

            }
        }

    }

    /**
     * 
     * @see org.kuali.kra.irb.actions.notification.ProtocolActionsNotificationService#addInitiatorToRecipients(org.w3c.dom.Element, org.kuali.kra.irb.Protocol)
     */
    public void addInitiatorToRecipients(Element recipients, Protocol protocol, List<String> userNames) {
        try {
            if (protocol.getProtocolDocument().getDocumentHeader() == null) {
                ProtocolDocument retrievedDocument = (ProtocolDocument) documentService.getByDocumentHeaderId(protocol
                        .getProtocolDocument().getDocumentNumber());
                protocol.setProtocolDocument(retrievedDocument);
            }
            KcPerson kcPersons = kcPersonService.getKcPersonByPersonId(protocol.getProtocolDocument().getDocumentHeader()
                    .getWorkflowDocument().getInitiatorPrincipalId());
            if (StringUtils.isNotBlank(kcPersons.getUserName())
                    && (StringUtils.isBlank(protocol.getPrincipalInvestigator().getPersonId()) || !kcPersons.getUserName().equals(
                            protocol.getPrincipalInvestigator().getPerson().getUserName()))) {
                if (!userNames.contains(kcPersons.getUserName())) {
                    XmlHelper.appendXml(recipients, "<user>" + kcPersons.getUserName() + "</user>");
                    userNames.add(kcPersons.getUserName());
                }
            }
        }
        catch (Exception e) {

        }

    }
    
    /**
     * 
     * @see org.kuali.kra.irb.actions.notification.ProtocolActionsNotificationService#addReviewerToRecipients(org.w3c.dom.Element, org.kuali.kra.irb.Protocol, java.util.List)
     */
    public void addReviewerToRecipients(Element recipients, Protocol protocol, List<String> userNames) {
        try {
            for (ProtocolOnlineReview protocolOnlineReview  : protocolOnlineReviewService.getProtocolReviews(protocol.getProtocolSubmission().getSubmissionId())) {
                KcPerson reviewer = protocolOnlineReview.getProtocolReviewer().getPerson();
                
                if (StringUtils.isNotBlank(reviewer.getUserName())
                        && (StringUtils.isBlank(protocol.getPrincipalInvestigator().getPersonId()) || !reviewer.getUserName().equals(
                                protocol.getPrincipalInvestigator().getPerson().getUserName()))) {
                    if (!userNames.contains(reviewer.getUserName())) {
                        XmlHelper.appendXml(recipients, "<user>" + reviewer.getUserName() + "</user>");
                        userNames.add(reviewer.getUserName());
                    }
                }
            }
        }
        catch (Exception e) {

        }

    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    public void setKimRoleManagementService(RoleService kimRoleManagementService) {
        this.kimRoleManagementService = kimRoleManagementService;
    }


    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }


    public void setNotificationTemplates(List<String> notificationTemplates) {
        this.notificationTemplates = notificationTemplates;
    }

    /*
     * call transformer to convert xml to html based on the xsl template
     */
    public String getTransFormData(Protocol protocol, StreamSource xsltSource) throws Exception {

        String XML = protocolXmlStream.generateXmlStreamForNotification(protocol);
        XML = XML.replace("<Protocol xmlns=\"http://irb.mit.edu/irbnamespace\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">", "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Protocol xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
        XML = XML.replace("</Protocol>", getUserTag() + "</Protocol>");
        StreamSource xmlSource = new StreamSource(new ByteArrayInputStream(XML.getBytes("UTF-8")));
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(xsltSource);
        StringWriter writer = new StringWriter();
        Result result = new StreamResult(writer);
        transformer.transform(xmlSource, result);
        return writer.toString();

    }

    /*
     * 
     */
    protected String getUserTag() {
        Person person = GlobalVariables.getUserSession().getPerson();
        StringBuffer sb = new StringBuffer();
        sb = sb.append("<user><firstName>").append(person.getFirstName()).append("</firstName>").append("<lastName>")
           .append(person.getLastName()).append("</lastName></user>");
        return sb.toString();
    }

    public void setKualiConfigurationService(KualiConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }


    public void setProtocolXmlStream(ProtocolXmlStream protocolXmlStream) {
        this.protocolXmlStream = protocolXmlStream;
    }


    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }


    public void setProtocolOnlineReviewService(ProtocolOnlineReviewService protocolOnlineReviewService) {
        this.protocolOnlineReviewService = protocolOnlineReviewService;
    }

}
