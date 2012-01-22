/*
 * Copyright 2005-2010 The Kuali Foundation.
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
package org.kuali.kra.s2s.polling;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.api.mail.MailMessage;

//import org.kuali.core.mail.MailMessage;

/**
 * 
 * This class is used to store information relating to emails to be sent.
 */
public class MailInfo {

    private String footer;
    private String dunsNumber;
    private String to;
    private String cc;
    private String bcc;
    private String message;
    private String subject;
    private MailMessage mailMessage;
    
    
    /**
     * 
     * Constructs a MailInfo.java.
     */
    public MailInfo(){
    }
    
    /**
     * Getter for property footer.
     * 
     * @return Value of property footer.
     */
    public String getFooter() {
        return footer;
    }

    /**
     * Setter for property footer.
     * 
     * @param footer New value of property footer.
     */
    public void setFooter(java.lang.String footer) {
        this.footer = footer;
    }

    /**
     * Getter for property dunsNumber.
     * 
     * @return Value of property dunsNumber.
     */
    public String getDunsNumber() {
        return dunsNumber;
    }

    /**
     * Setter for property dunsNumber.
     * 
     * @param dunsNumber New value of property dunsNumber.
     */
    public void setDunsNumber(java.lang.String dunsNumber) {
        this.dunsNumber = dunsNumber;
    }
    
    /**
     * Getter for property mailMessage.
     * 
     * @return Value of property mailMessage.
     */
    public MailMessage getMailMessage() {
        createMailMessage();
        return mailMessage;
    }

    /**
     * Setter for property mailMessage.
     * 
     * @param MailMessage New value of property dunsNumber.
     */
    public void setMailMessage(MailMessage mailMessage) {
        this.mailMessage = mailMessage;
    }

    /**
     * Gets the to attribute. 
     * @return Returns the to.
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the to attribute value.
     * @param to The to to set.
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Gets the cc attribute. 
     * @return Returns the cc.
     */
    public String getCc() {
        return cc;
    }

    /**
     * Sets the cc attribute value.
     * @param cc The cc to set.
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * Gets the bcc attribute. 
     * @return Returns the bcc.
     */
    public String getBcc() {
        return bcc;
    }

    /**
     * Sets the bcc attribute value.
     * @param bcc The bcc to set.
     */
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    /**
     * Gets the message attribute. 
     * @return Returns the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message attribute value.
     * @param message The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the subject attribute. 
     * @return Returns the subject.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject attribute value.
     * @param subject The subject to set.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    /**
     * 
     * This method populates the MailMessage object with all parameters and returns it
     * @return {@link MailMessage} created from the available parameters
     */
    private MailMessage createMailMessage(){
        mailMessage = new MailMessage();
        mailMessage.addToAddress(to);
        if(StringUtils.isNotBlank(cc))
            mailMessage.addCcAddress(cc);
        if(StringUtils.isNotBlank(bcc))
            mailMessage.addBccAddress(bcc);
        mailMessage.setMessage(message);
        mailMessage.setSubject(subject);
        return mailMessage;
    }
    
    @Override
    public String toString() {
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("Mail To: ");
        stringBuffer.append(to);
        stringBuffer.append("; Subject: ");
        stringBuffer.append(subject);
        stringBuffer.append("; Message: ");
        stringBuffer.append(message);
        return stringBuffer.toString();
    }
}
