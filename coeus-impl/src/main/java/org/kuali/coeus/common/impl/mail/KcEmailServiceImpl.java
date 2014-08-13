/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.impl.mail;


import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.mail.EmailAttachment;
import org.kuali.coeus.common.framework.mail.KcEmailService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Set;

@Component("kcEmailService")
public class KcEmailServiceImpl implements KcEmailService {
    private static final Log LOG = LogFactory.getLog(KcEmailServiceImpl.class);
    
    public static final String DEFAULT_ENCODING = "UTF-8";

    @Autowired
    @Qualifier("mailSender")
    private JavaMailSenderImpl mailSender;
    
    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    @Autowired
    @Qualifier("kualiConfigurationService")
    private ConfigurationService configurationService;

    public void sendEmail(String from, Set<String> toAddresses, String subject, Set<String> ccAddresses,
                          Set<String> bccAddresses, String body, boolean htmlMessage) {
        sendEmailWithAttachments(from, toAddresses, subject, ccAddresses, bccAddresses, body, htmlMessage, null);
    }

    public void sendEmailWithAttachments(String from, Set<String> toAddresses, String subject, Set<String> ccAddresses,
                                         Set<String> bccAddresses, String body, boolean htmlMessage, List<EmailAttachment> attachments) {
        
        if (mailSender != null) {
            if(CollectionUtils.isEmpty(toAddresses) && 
                    CollectionUtils.isEmpty(ccAddresses) && 
                    CollectionUtils.isEmpty(bccAddresses)){
                return;
            }
            
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = null;
            
            try {
                helper = new MimeMessageHelper(message, true, DEFAULT_ENCODING);
                helper.setFrom(from);
                
                if (StringUtils.isNotBlank(subject)) {
                    helper.setSubject(subject);
                } else {
                    LOG.warn("Sending message with empty subject.");
                }
                
                
                if(isEmailTestEnabled()){
                    helper.setText(getTestMessageBody(body,toAddresses,ccAddresses,bccAddresses),true);
                    String toAddress = getEmailNotificationTestAddress();
                    if(StringUtils.isNotBlank(getEmailNotificationTestAddress())){
                        helper.addTo(toAddress);
                    }
                }else{
                    helper.setText(body, htmlMessage);
                    if (CollectionUtils.isNotEmpty(toAddresses)) {
                        for (String toAddress: toAddresses) {
                            try{
                                helper.addTo(toAddress);
                            }catch(Exception ex){
                                LOG.warn("Could not set to address:",ex);
                            }
                        }
                    }
                    if (CollectionUtils.isNotEmpty(ccAddresses)) {
                        for (String ccAddress: ccAddresses) {
                            try{
                                helper.addCc(ccAddress);
                            }catch(Exception ex){
                                LOG.warn("Could not set to address:",ex);
                            }
                        }
                    }
                    if (CollectionUtils.isNotEmpty(bccAddresses)) {
                        for (String bccAddress : bccAddresses) {
                            try{
                                helper.addBcc(bccAddress);
                            }catch(Exception ex){
                                LOG.warn("Could not set to address:",ex);
                            }
                        }
                    }
                }
                
                if (CollectionUtils.isNotEmpty(attachments)) {
                    for (EmailAttachment attachment : attachments) {
                        try{
                            helper.addAttachment(attachment.getFileName(), new ByteArrayResource(attachment.getContents()), attachment.getMimeType());
                        }catch(Exception ex){
                            LOG.warn("Could not set to address:",ex);
                        }
                    }
                }
                
                mailSender.send(message);
                
            } catch (MessagingException ex) {
                LOG.error("Failed to create mime message helper.", ex);
            } catch (Exception e) {
                LOG.error("Failed to send email.", e);
            }
        } else {
            LOG.info("Failed to send email due to inability to obtain valid email mailSender, please check your configuration.");
        }
    }
    
    private String getEmailNotificationTestAddress() {
        String testAddress = parameterService.getParameterValueAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE,  
                Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, "EMAIL_NOTIFICATION_TEST_ADDRESS");
        return testAddress;
    }
    private String getTestMessageBody(String body, Set<String> toAddresses, Set<String> ccAddresses, Set<String> bccAddresses) {
        StringBuffer testEmailBody = new StringBuffer("");
        testEmailBody.append( "-----------------------------------------------------------<br/>");
        testEmailBody.append("TEST MODE<br/>");
        testEmailBody.append("In Production mode this mail will be sent to the following... <br/>");
        if (CollectionUtils.isNotEmpty(toAddresses)) {
            testEmailBody.append("TO: ");
            testEmailBody.append(toAddresses);
        }
        if (CollectionUtils.isNotEmpty(ccAddresses)) {
            testEmailBody.append("CC: ");
            testEmailBody.append(toAddresses);
        }
        if (CollectionUtils.isNotEmpty(bccAddresses)) {
            testEmailBody.append("BCC: ");
            testEmailBody.append(toAddresses);
        }
        testEmailBody.append("<br/>-----------------------------------------------------------");
        return testEmailBody.toString()+"<br/>"+body;
        
    }
    private boolean isEmailTestEnabled() {
        Boolean emailTestEnabled = parameterService.getParameterValueAsBoolean(Constants.KC_GENERIC_PARAMETER_NAMESPACE,  
                Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, "EMAIL_NOTIFICATIONS_TEST_ENABLED");
        return emailTestEnabled==null?false:emailTestEnabled;
    }
    public String getDefaultFromAddress() {
        String fromAddress = parameterService.getParameterValueAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE,  
                Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, "EMAIL_NOTIFICATION_FROM_ADDRESS");
        return fromAddress!=null?fromAddress:configurationService.getPropertyValueAsString("mail.from");
    }

    public void setMailSender(JavaMailSenderImpl mailSender) {
    	this.mailSender = mailSender;
    }
    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
    public JavaMailSenderImpl getMailSender() {
    	return mailSender;
    }

    public void setParameterService(ParameterService parameterService) {
    	this.parameterService = parameterService;
    }
    
    public ParameterService getParameterService() {
    	return parameterService;
    }
}
