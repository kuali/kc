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
package org.kuali.kra.service.impl;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.service.KcEmailService;
import org.kuali.kra.util.EmailAttachment;
import org.kuali.rice.core.api.config.property.ConfigContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class KcEmailServiceImpl implements KcEmailService {
    private static final Log LOG = LogFactory.getLog(KcEmailServiceImpl.class);
    
    public static final String DEFAULT_ENCODING = "UTF-8";
    
    public void sendEmail(String from, Set<String> toAddresses, String subject, Set<String> ccAddresses,
                          Set<String> bccAddresses, String body, boolean htmlMessage) {
        sendEmailWithAttachments(from, toAddresses, subject, ccAddresses, bccAddresses, body, htmlMessage, null);
    }

    public void sendEmailWithAttachments(String from, Set<String> toAddresses, String subject, Set<String> ccAddresses,
                                         Set<String> bccAddresses, String body, boolean htmlMessage, List<EmailAttachment> attachments) {
        JavaMailSender sender = createSender();
        
        if (sender != null) {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = null;
            
            try {
                helper = new MimeMessageHelper(message, true, DEFAULT_ENCODING);
                helper.setFrom(from);
                
                if (CollectionUtils.isNotEmpty(toAddresses)) {
                    for (String toAddress: toAddresses) {
                        helper.addTo(toAddress);
                    }
                }
                
                if (StringUtils.isNotBlank(subject)) {
                    helper.setSubject(subject);
                } else {
                    LOG.warn("Sending message with empty subject.");
                }
                
                helper.setText(body, htmlMessage);
                
                if (CollectionUtils.isNotEmpty(ccAddresses)) {
                    for (String ccAddress: ccAddresses) {
                        helper.addCc(ccAddress);
                    }
                }
                
                if (CollectionUtils.isNotEmpty(bccAddresses)) {
                    for (String bccAddress : bccAddresses) {
                        helper.addBcc(bccAddress);
                    }
                }
                
                if (CollectionUtils.isNotEmpty(attachments)) {
                    for (EmailAttachment attachment : attachments) {
                        helper.addAttachment(attachment.getFileName(), new ByteArrayResource(attachment.getContents()), attachment.getMimeType());
                    }
                }
                
                sender.send(message);
            } catch (MessagingException ex) {
                LOG.error("Failed to create mime message helper.", ex);
            } catch (Exception e) {
                LOG.error("Failed to send email.", e);
            }
        } else {
            LOG.info("Failed to send email due to inability to obtain valid email sender, please check your configuration.");
        }
    }
    
    public String getDefaultFromAddress() {
        return getConfigProperties().getProperty("mail.from");
    }

    
    private JavaMailSender createSender() {
        Properties props = getConfigProperties();
        JavaMailSenderImpl sender = null;

        if (props != null && StringUtils.isNotBlank(props.getProperty("mail.smtp.host")) &&
                StringUtils.isNotBlank(props.getProperty("mail.smtp.port")) && 
                StringUtils.isNotBlank(props.getProperty("mail.smtp.user")) &&
                StringUtils.isNotBlank(props.getProperty("mail.user.credentials"))) {
            try {
            sender = new JavaMailSenderImpl();
        
            sender.setJavaMailProperties(props);
            sender.setHost(props.getProperty("mail.smtp.host"));
            sender.setPort(Integer.parseInt(props.getProperty("mail.smtp.port")));
            sender.setUsername(props.getProperty("mail.smtp.user"));
            sender.setPassword(props.getProperty("mail.user.credentials"));
            } catch (Exception e) {
                LOG.warn("Unable to create email sender due to invalid configuration. The properties [mail.smtp.host, " +
                     "mail.smtp.port, mail.smtp.user, mail.user.credentials] need to be set.");
                sender = null;
            }
        } else {
            LOG.warn("Unable to create email sender due to missing configuration.  The properties [mail.smtp.host, " +
                     "mail.smtp.port, mail.smtp.user, mail.user.credentials] need to be set.");
        }
        
        return sender;
    }
    
    private Properties getConfigProperties() {
        return ConfigContext.getCurrentContextConfig().getProperties();
    }
}
