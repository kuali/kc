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
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.ken.service.NotificationService;
import org.kuali.rice.ken.util.Util;
import org.kuali.rice.kew.util.XmlHelper;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kim.service.RoleService;
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
     * This method is to use it to generate protocol document xml for testing now. eventually, we should use the protocol xml data
     * created for protocol report TODO : remove this once the protocol report xml data is available
     * 
     * @param protocol
     * @return
     * @throws Exception
     */
    private String generateProtocolDocumentXml(Protocol protocol) throws Exception {
        InputStream is = this.getClass().getResourceAsStream("/org/kuali/kra/irb/protocoldata_template.xml");
        Document notificationRequestDocument;

        try {
            notificationRequestDocument = Util.parse(new InputSource(is), false, false, null);
            Element protocolNumber = (Element) notificationRequestDocument.getElementsByTagName("protocolNumber").item(0);
            protocolNumber.setTextContent(protocol.getProtocolNumber());

            Element documentNumber = (Element) notificationRequestDocument.getElementsByTagName("documentNumber").item(0);
            documentNumber.setTextContent(protocol.getProtocolDocument().getDocumentNumber());
            Element pi = (Element) notificationRequestDocument.getElementsByTagName("pi").item(0);
            setPi(pi, protocol.getPrincipalInvestigator());
            Element user = (Element) notificationRequestDocument.getElementsByTagName("user").item(0);
            setUser(user);
            Element reviewers = (Element) notificationRequestDocument.getElementsByTagName("protocolReviewers").item(0);
            setReviewerNames(reviewers, protocol.getProtocolSubmission().getProtocolReviewers());
            Element title = (Element) notificationRequestDocument.getElementsByTagName("title").item(0);
            title.setTextContent(protocol.getTitle());

        }
        finally {
            if (is != null) {
                is.close();
            }
        }

        return Util.writeNode(notificationRequestDocument, true);
        // Waiting for rice KEN bootstrap to be corrected
        // notificationService.sendNotification(XML);
    }

    /**
     * 
     * @see org.kuali.kra.irb.actions.notification.ProtocolActionsNotificationService#addIrbAdminToRecipients(org.w3c.dom.Element,
     *      org.kuali.kra.irb.Protocol)
     */
    public void addIrbAdminToRecipients(Element recipients, Protocol protocol) {
        Collection<String> ids = kimRoleManagementService
                .getRoleMemberPrincipalIds("KC-UNT", RoleConstants.IRB_ADMINISTRATOR, null);
        for (String id : ids) {
            KcPerson kcPersons = kcPersonService.getKcPersonByPersonId(id);
            try {
                if (StringUtils.isNotBlank(kcPersons.getUserName())
                        && (StringUtils.isBlank(protocol.getPrincipalInvestigator().getPersonId()) || !kcPersons.getUserName()
                                .equals(protocol.getPrincipalInvestigator().getPerson().getUserName()))) {
                    XmlHelper.appendXml(recipients, "<user>" + kcPersons.getUserName() + "</user>");
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
    public void addInitiatorToRecipients(Element recipients, Protocol protocol) {
        KcPerson kcPersons = kcPersonService.getKcPersonByPersonId(protocol.getProtocolDocument().getDocumentHeader()
                .getWorkflowDocument().getInitiatorPrincipalId());
        try {
            if (StringUtils.isNotBlank(kcPersons.getUserName())
                    && (StringUtils.isBlank(protocol.getPrincipalInvestigator().getPersonId()) || !kcPersons.getUserName().equals(
                            protocol.getPrincipalInvestigator().getPerson().getUserName()))) {
                XmlHelper.appendXml(recipients, "<user>" + kcPersons.getUserName() + "</user>");
            }
        }
        catch (Exception e) {

        }

    }

    /*
     * add the pi node to protocol xml document
     */
    private void setPi(Element piNode, ProtocolPerson pi) {
        try {
            if (StringUtils.isNotBlank(pi.getPersonId())) {
                XmlHelper.appendXml(piNode, "<firstName>" + pi.getPerson().getFirstName() + "</firstName>");
                XmlHelper.appendXml(piNode, "<lastName>" + pi.getPerson().getLastName() + "</lastName>");
            }
            else {
                XmlHelper.appendXml(piNode, "<firstName>" + pi.getRolodex().getFirstName() + "</firstName>");
                XmlHelper.appendXml(piNode, "<lastName>" + pi.getRolodex().getLastName() + "</lastName>");
            }
        }
        catch (Exception e) {

        }
    }
    
    private void setUser(Element userNode) {
        Person person = GlobalVariables.getUserSession().getPerson();
        try {
                XmlHelper.appendXml(userNode, "<firstName>" + person.getFirstName() + "</firstName>");
                XmlHelper.appendXml(userNode, "<lastName>" + person.getLastName() + "</lastName>");
        }  catch (Exception e) {

        }
    }

    /*
     * add reviewer to protocol xml document
     */
    private void setReviewerNames(Element reviewerNode, List<ProtocolReviewer> reviewers) {
        int i = 0;
        try {
            for (ProtocolReviewer reviewer : reviewers) {
                XmlHelper.appendXml(reviewerNode, "<reviewer>" + (i++) + "</reviewer>");
                XmlHelper.appendXml(reviewerNode.getLastChild(), "<idx>" + (i++) + "</idx>");
                XmlHelper.appendXml(reviewerNode.getLastChild(), "<fullName>" + reviewer.getFullName() + "</fullName>");
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
    private String getTransFormData(Protocol protocol, StreamSource xsltSource) throws Exception {

        // JAXP reads data using the Source interface
        // ClassLoader.getSystemResource("notifyIrb_notification.xml").getFile();

        // StreamSource xsltSource = new StreamSource(this.getClass().getResourceAsStream(xlsPath));
        String XML = generateProtocolDocumentXml(protocol);
        // InputStream is = new ByteArrayInputStream(XML.getBytes("UTF-8"));
        // InputStream
        StreamSource xmlSource = new StreamSource(new ByteArrayInputStream(XML.getBytes("UTF-8")));
        // StreamSource xmlSource = new
        // StreamSource(this.getClass().getResourceAsStream("/org/kuali/kra/irb/notificationDummyProtocolData.xml"));

        // the factory pattern supports different XSLT processors
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(xsltSource);
        // OutputStream os = new OutputStream();
        StringWriter writer = new StringWriter();
        Result result = new StreamResult(writer);
        transformer.transform(xmlSource, result);
        // writer.toString();
        return writer.toString();

    }


    public void setKualiConfigurationService(KualiConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }

}
