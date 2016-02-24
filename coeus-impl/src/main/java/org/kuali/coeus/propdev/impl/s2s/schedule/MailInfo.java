/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.propdev.impl.s2s.schedule;

import org.apache.commons.lang3.StringUtils;
import org.kuali.rice.core.api.mail.MailMessage;


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
     * @param mailMessage New value of property dunsNumber.
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
