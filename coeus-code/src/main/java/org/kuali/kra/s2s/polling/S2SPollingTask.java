/*
 * Copyright 2005-2014 The Kuali Foundation.
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

import gov.grants.apply.services.applicantwebservices_v2.GetApplicationListResponse;
import gov.grants.apply.services.applicantwebservices_v2.GetApplicationListResponse.ApplicationInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.bo.S2sAppSubmission;
import org.kuali.kra.s2s.service.S2SService;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.rice.core.api.mail.MailMessage;
import org.kuali.rice.krad.exception.InvalidAddressException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.MailService;

import javax.mail.MessagingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * This class is run by the S2S Scheduler. ON every trigger, it polls data from Grants.gov for status of submitted proposals. On
 * receiving status, if it has changed from what exists in database, it updates the status in database and also sends emails
 * regarding status. All the required parameter configurations are injected from spring-beans.xml
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class S2SPollingTask {

    private static final Log LOG = LogFactory.getLog(S2SPollingTask.class);
    private final List<String> lstStatus = new ArrayList<String>();
    private final Map<String, String> sortMsgKeyMap = new Hashtable<String, String>();
    private BusinessObjectService businessObjectService = null;
    private S2SService s2SService = null;

    private String stopPollInterval;
    private String mailInterval;
    private Map<String, String> statusMap = new HashMap<String, String>();
    private List<MailInfo> mailInfoList = new ArrayList<MailInfo>();

    private static final String KEY_PROPOSAL_NUMBER = "proposalNumber";
    private static final String KEY_STATUS = "status";
    private static final String DATE_FORMAT = "dd-MMM-yyyy hh:mm a";

    private static final String SORT_ID_A = "A";
    private static final String SORT_ID_B = "B";
    private static final String SORT_ID_C = "C";
    private static final String SORT_ID_D = "D";
    private static final String SORT_ID_E = "E";
    private static final String SORT_ID_F = "F";
    private static final String SORT_ID_Z = "Z";


    public S2SPollingTask() {
        lstStatus.add(S2SConstants.GRANTS_GOV_SUBMISSION_MESSAGE.toUpperCase());
        lstStatus.add(S2SConstants.STATUS_RECEIVING.toUpperCase());
        lstStatus.add(S2SConstants.STATUS_RECEIVED.toUpperCase());
        lstStatus.add(S2SConstants.STATUS_PROCESSING.toUpperCase());
        lstStatus.add(S2SConstants.STATUS_VALIDATED.toUpperCase());
        lstStatus.add(S2SConstants.STATUS_RECEIVED_BY_AGENCY.toUpperCase());
        lstStatus.add(S2SConstants.STATUS_AGENCY_TRACKING_NUMBER_ASSIGNED.toUpperCase());
        lstStatus.add(S2SConstants.STATUS_REJECTED_WITH_ERRORS.toUpperCase());
        lstStatus.add(S2SConstants.STATUS_GRANTS_GOV_SUBMISSION_ERROR.toUpperCase());

        sortMsgKeyMap.put("A", "Following proposals DID NOT validate at Grants.Gov");
        sortMsgKeyMap.put("B", "Following proposals are at an Unknown status at Grants.Gov");
        sortMsgKeyMap.put("C", "Following proposals will be dropped from the notification emails in next 24 hours");
        sortMsgKeyMap.put("D", "Following submissions status has not changed");
        sortMsgKeyMap.put("E", "Following submissions status has changed");
        sortMsgKeyMap.put("F", "Error occured while retrieving submissions");
        sortMsgKeyMap.put("Z", "");
    }

    /**
     * 
     * This method determines whether the particular submission record received as parameter must be polled or not based on its last
     * modified date.
     * 
     * @param appSubmission
     * @return boolean
     */
    private boolean getSubmissionDateValidity(S2sAppSubmission appSubmission) {
        Calendar lastModifiedDate = Calendar.getInstance();
        long stopPollingIntervalMillis = Integer.parseInt(this.getStopPollInterval()) * 60 * 60 * 1000L;
        if (appSubmission.getLastModifiedDate() != null) {
            lastModifiedDate.setTimeInMillis(appSubmission.getLastModifiedDate().getTime());
        }
        else {
            lastModifiedDate.setTimeInMillis(appSubmission.getReceivedDate().getTime());
        }

        return (new Date().getTime() - lastModifiedDate.getTimeInMillis() < stopPollingIntervalMillis);
    }

    /**
     * 
     * This method filters out the latest submission record for each proposal and returns it in a map.
     * 
     * @param submissionList {@link Collection} of all submissions
     * @return map of one submission record for each proposal
     */
    private Map<String, SubmissionData> populatePollingList() {
        Map<String, String> submissionMap = new HashMap<String, String>();
        Collection<S2sAppSubmission> submissionList = new ArrayList<S2sAppSubmission>();
        for (String status : statusMap.values()) {
            submissionMap.clear();
            submissionMap.put(KEY_STATUS, status);
            if (submissionList == null) {
                submissionList = businessObjectService.findMatching(S2sAppSubmission.class, submissionMap);
            }
            else {
                submissionList.addAll(businessObjectService.findMatching(S2sAppSubmission.class, submissionMap));
            }
        }

        Map<String, SubmissionData> pollingList = new HashMap<String, SubmissionData>();
        Iterator<S2sAppSubmission> appSubmissions = submissionList.iterator();
        while (appSubmissions.hasNext()) {
            S2sAppSubmission appSubmission = appSubmissions.next();
            if (pollingList.get(appSubmission.getProposalNumber()) != null) {
                if (appSubmission.getSubmissionNumber() > pollingList.get(appSubmission.getProposalNumber()).getS2sAppSubmission()
                        .getSubmissionNumber()) {
                    // Greater the submission number, more the latest
                    // Check if the record is not more than 6 months old
                    if (getSubmissionDateValidity(appSubmission)) {
                        SubmissionData submissionData = new SubmissionData();
                        submissionData.setS2sAppSubmission(appSubmission);
                        pollingList.put(appSubmission.getProposalNumber(), submissionData);
                    }
                }
            }
            else {
                if (getSubmissionDateValidity(appSubmission)) {
                    SubmissionData submissionData = new SubmissionData();
                    submissionData.setS2sAppSubmission(appSubmission);
                    pollingList.put(appSubmission.getProposalNumber(), submissionData);
                }
            }
        }
        return pollingList;
    }

    /**
     * This method is the starting point of execution for the thread that is scheduled by the scheduler service
     * 
     */
    public void execute() {
        LOG.info("Executing polling schedule for status -" + statusMap.values() + ":" + stopPollInterval);
        Map<String, SubmissionData> pollingList = populatePollingList();
        int appListSize = pollingList.size();
        Iterator<SubmissionData> submissions = pollingList.values().iterator();
        HashMap<String, Vector<SubmissionData>> htMails = new LinkedHashMap<String, Vector<SubmissionData>>();
        Vector<SubmissionData> submList = new Vector<SubmissionData>();
        Timestamp[] lastNotiDateArr = new Timestamp[appListSize];
        while (submissions.hasNext()) {
            SubmissionData localSubInfo = submissions.next();
            S2sAppSubmission appSubmission = localSubInfo.getS2sAppSubmission();
            Timestamp oldLastNotiDate = appSubmission.getLastNotifiedDate();
            Timestamp today = new Timestamp(new Date().getTime());
            boolean updateFlag = false;
            boolean sendEmailFlag = false;
            boolean statusChanged = false;
            GetApplicationListResponse applicationListResponse = null;

            try {
                ProposalDevelopmentDocument pdDoc = getProposalDevelopmentDocument(appSubmission.getProposalNumber());
                if (pdDoc != null) {
                    applicationListResponse = s2SService.fetchApplicationListResponse(pdDoc);
                }

                if (applicationListResponse.getApplicationInfo() == null
                        || applicationListResponse.getApplicationInfo().size() == 0) {
                    statusChanged = s2SService.checkForSubmissionStatusChange(pdDoc, appSubmission);
                    if (statusChanged == false
                            && appSubmission.getComments().equals(S2SConstants.STATUS_NO_RESPONSE_FROM_GRANTS_GOV)) {
                        localSubInfo.setSortId(SORT_ID_F);
                        sendEmailFlag = true;
                    }
                }
                else {
                    ApplicationInfo ggApplication = applicationListResponse.getApplicationInfo().get(0);
                    if (ggApplication != null) {
                        localSubInfo.setAcType('U');
                        statusChanged = !appSubmission.getStatus().equalsIgnoreCase(
                                ggApplication.getGrantsGovApplicationStatus().value());
                        s2SService.populateAppSubmission(pdDoc, appSubmission, ggApplication);
                    }
                }
            }
            catch (S2SException e) {
                LOG.error(e.getMessage(), e);
                appSubmission.setComments(e.getMessage());
                localSubInfo.setSortId(SORT_ID_F);
                sendEmailFlag = true;
            }

            String sortId = SORT_ID_Z;
            Timestamp lastNotifiedDate = appSubmission.getLastNotifiedDate();
            Timestamp statusChangedDate = appSubmission.getLastModifiedDate();
            Calendar lastNotifiedDateCal = Calendar.getInstance();
            if (lastNotifiedDate != null) {
                lastNotifiedDateCal.setTimeInMillis(lastNotifiedDate.getTime());
            }
            Calendar statusChangedDateCal = Calendar.getInstance();
            if (statusChangedDate != null) {
                statusChangedDateCal.setTimeInMillis(statusChangedDate.getTime());
            }
            Calendar recDateCal = Calendar.getInstance();
            recDateCal.setTimeInMillis(appSubmission.getReceivedDate().getTime());

            if (statusChanged) {
                if (appSubmission.getStatus().indexOf(S2SConstants.STATUS_GRANTS_GOV_SUBMISSION_ERROR) != -1) {
                    updateFlag = true;
                    sendEmailFlag = true;
                    sortId = SORT_ID_A;
                }
                else if (!lstStatus.contains(appSubmission.getStatus().trim().toUpperCase())) {
                    updateFlag = false;
                    sendEmailFlag = true;
                    sortId = SORT_ID_B;
                }
                else {
                    updateFlag = true;
                    sendEmailFlag = true;
                    sortId = SORT_ID_E;
                }
            }
            else {
                long lastModifiedTime = statusChangedDate == null ? appSubmission.getReceivedDate().getTime() : statusChangedDate
                        .getTime();
                long lastNotifiedTime = lastNotifiedDate == null ? lastModifiedTime : lastNotifiedDate.getTime();
                long mailDelta = today.getTime() - lastNotifiedTime;
                long delta = today.getTime() - lastModifiedTime;

                long stopPollDiff = ((Integer.parseInt(getStopPollInterval()) == 0 ? 4320L : Integer
                        .parseInt(getStopPollInterval())) - (delta / (60 * 60 * 1000)));
                if ((mailDelta / (1000 * 60)) >= (Integer.parseInt(getMailInterval()))) {
                    if (localSubInfo.getSortId() == null) {
                        if (stopPollDiff <= 24) {
                            sortId = SORT_ID_C;
                        }
                        else {
                            sortId = SORT_ID_D;
                            sortMsgKeyMap.put(SORT_ID_D, "Following submissions status has not been changed in "
                                    + getMailInterval() + " minutes");
                        }
                    }
                    updateFlag = true;
                    sendEmailFlag = true;
                }
            }
            if (sendEmailFlag) {
                Map<String, String> proposalMap = new HashMap<String, String>();
                proposalMap.put(KEY_PROPOSAL_NUMBER, appSubmission.getProposalNumber());
                DevelopmentProposal developmentProposal = (DevelopmentProposal) businessObjectService.findByPrimaryKey(
                        DevelopmentProposal.class, proposalMap);

                String dunsNum;
                if (developmentProposal.getApplicantOrganization().getOrganization().getDunsNumber() != null) {
                    dunsNum = developmentProposal.getApplicantOrganization().getOrganization().getDunsNumber();
                }
                else {
                    dunsNum = developmentProposal.getApplicantOrganization().getOrganizationId();
                }
                Vector<SubmissionData> mailGrpForDunNum = new Vector<SubmissionData>();
                mailGrpForDunNum.add(localSubInfo);
                htMails.put(dunsNum, mailGrpForDunNum);
                appSubmission.setLastNotifiedDate(today);
            }
            if (localSubInfo.getSortId() == null) {
                localSubInfo.setSortId(sortId);
            }
            if (updateFlag) {
                submList.addElement(localSubInfo);
                lastNotiDateArr[submList.size() - 1] = oldLastNotiDate;
            }

        }
        try {
            sendMail(htMails);
        }
        catch (InvalidAddressException ex) {
            LOG.error("Mail sending failed");
            LOG.error(ex.getMessage(), ex);
            int size = submList.size();
            for (int i = 0; i < size; i++) {
                SubmissionData localSubInfo = submList.elementAt(i);
                localSubInfo.getS2sAppSubmission().setLastNotifiedDate(lastNotiDateArr[i]);
            }
        } catch (MessagingException me) {
            LOG.error("Mail sending failed");
            LOG.error(me.getMessage(), me);
            int size = submList.size();
            for (int i = 0; i < size; i++) {
                SubmissionData localSubInfo = submList.elementAt(i);
                localSubInfo.getS2sAppSubmission().setLastNotifiedDate(lastNotiDateArr[i]);
            }
        }
        saveSubmissionDetails(submList);
    }

    private ProposalDevelopmentDocument getProposalDevelopmentDocument(String proposalNumber) {
        ProposalDevelopmentDocument pdDoc = null;
        Map<String, String> proposalMap = new HashMap<String, String>();
        proposalMap.put(KEY_PROPOSAL_NUMBER, proposalNumber);
        DevelopmentProposal developmentProposal = (DevelopmentProposal) businessObjectService.findByPrimaryKey(
                DevelopmentProposal.class, proposalMap);
        if (developmentProposal != null) {
            pdDoc = developmentProposal.getProposalDocument();
        }
        return pdDoc;
    }

    /**
     * 
     * This method saves submission data and status to database
     * 
     * @param submList
     */
    private void saveSubmissionDetails(Vector<SubmissionData> submList) {
        if (submList != null) {
            for (SubmissionData submissionData : submList) {
                S2sAppSubmission s2sAppSubmission = submissionData.getS2sAppSubmission();
                s2sAppSubmission.setUpdateUserSet(true);
                if(!s2sAppSubmission.getStatus().equalsIgnoreCase(S2SConstants.STATUS_PUREGED))
                    businessObjectService.save(s2sAppSubmission);
            }
        }
    }

    /**
     * 
     * This method sends mail for all submission status records that have changed relative to database
     * 
     * @param htMails
     * @throws InvalidAddressException
     * @throws Exception
     */
    private void sendMail(HashMap<String, Vector<SubmissionData>> htMails) throws InvalidAddressException , MessagingException {
        MailService mailService = KcServiceLocator.getService(MailService.class);
        if (htMails.isEmpty()) {
            return;
        }
        List<MailInfo> mailList = getMailInfoList();
        for (MailInfo mailInfo : mailList) {
            String dunsNum = mailInfo.getDunsNumber();
            Vector<SubmissionData> propList = htMails.get(dunsNum);
            if (propList == null) {
                continue;
            }
            htMails.remove(dunsNum);
            MailMessage mailMessage = parseNGetMailAttr(propList, mailInfo);
            if (mailMessage != null) {
                mailService.sendMessage(mailMessage);
            }
        }

        if (mailList.size() > 0 && !htMails.isEmpty()) {
            Iterator<String> it = htMails.keySet().iterator();
            while (it.hasNext()) {
                Vector<SubmissionData> nonDunsPropList = htMails.get(it.next());
                MailInfo mailInfo = mailList.get(0);
                MailMessage mailMessage = parseNGetMailAttr(nonDunsPropList, mailInfo);
                if (mailMessage != null) {
                    mailService.sendMessage(mailMessage);
                    LOG.debug("Sent mail with default duns to " + mailMessage.getToAddresses() + " Subject as "
                            + mailMessage.getSubject() + " Message as " + mailMessage.getMessage());
                }
            }
        }
    }

    /**
     * 
     * This method processes data that is to be sent by mail
     * 
     * @param propList
     * @param mailInfo
     * @return {@link MailMessage}
     */
    private MailMessage parseNGetMailAttr(Vector<SubmissionData> propList, MailInfo mailInfo) {
        if (propList == null || propList.isEmpty()) {
            return null;
        }

        MailMessage mailMessage = mailInfo.getMailMessage();
        StringBuffer message = new StringBuffer(mailMessage.getMessage());

        for (SubmissionData submissionData : propList) {

            S2sAppSubmission appSubmission = submissionData.getS2sAppSubmission();
            Timestamp lastNotifiedDate = appSubmission.getLastNotifiedDate();
            Timestamp statusChangedDate = appSubmission.getLastModifiedDate();
            Calendar lastNotifiedDateCal = Calendar.getInstance();

            if (lastNotifiedDate != null) {
                lastNotifiedDateCal.setTimeInMillis(lastNotifiedDate.getTime());
            }
            Calendar statusChangedDateCal = Calendar.getInstance();
            if (statusChangedDate != null) {
                statusChangedDateCal.setTimeInMillis(statusChangedDate.getTime());
            }
            Calendar recDateCal = Calendar.getInstance();
            recDateCal.setTimeInMillis(appSubmission.getReceivedDate().getTime());

            long lastModifiedTime = statusChangedDate == null ? appSubmission.getReceivedDate().getTime() : statusChangedDate
                    .getTime();
            Timestamp today = new Timestamp(new Date().getTime());
            long delta = today.getTime() - lastModifiedTime;
            double deltaHrs = ((double) Math.round((delta / (1000.0d * 60.0d * 60.0d)) * Math.pow(10.0, 2))) / 100;

            int days = 0;
            int hrs = 0;
            if (deltaHrs > 0) {
                days = (int) deltaHrs / 24;
                hrs = (int) (((double) Math.round((deltaHrs % 24) * Math.pow(10.0, 2))) / 100);

            }
            if (propList.size() > 0) {
                SubmissionData prevSubmissionData = propList.elementAt(propList.size() - 1);
                if (!prevSubmissionData.getSortId().equals(submissionData.getSortId())) {
                    message.append("\n\n");
                    message.append(sortMsgKeyMap.get(submissionData.getSortId()));
                    message.append("\n____________________________________________________");
                }
            }
            else {
                message.append("\n\n");
                message.append(sortMsgKeyMap.get(submissionData.getSortId()));
                message.append("\n____________________________________________________");
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            message.append('\n');
            message.append("Proposal Number : " + appSubmission.getProposalNumber() + "\n");
            message.append("Received Date : ");
            message.append(dateFormat.format(appSubmission.getReceivedDate()));
            message.append('\n');
            message.append("Grants.Gov Tracking Id : ");
            message.append(appSubmission.getGgTrackingId());
            message.append('\n');
            String agTrackId = appSubmission.getAgencyTrackingId() == null ? "Not updated yet" : appSubmission
                    .getAgencyTrackingId();
            message.append("Agency Tracking Id : ");
            message.append(agTrackId);
            message.append('\n');
            message.append("Current Status : ");
            message.append(appSubmission.getStatus());
            message.append('\n');
            String stChnageDate = appSubmission.getLastModifiedDate() == null ? "Not updated yet" : dateFormat.format(appSubmission
                    .getLastModifiedDate());
            message.append("Last Status Change : " + stChnageDate + "\t *** " + days + " day(s) and " + hrs + " hour(s) ***\n");
            message.append('\n');
        }
        message.append('\n');
        message.append(mailInfo.getFooter());
        mailMessage.setMessage(message.toString());

        return mailMessage;
    }

    /**
     * Getter for property stopPollInterval.
     * 
     * @return Value of property stopPollInterval.
     */
    public String getStopPollInterval() {
        return stopPollInterval;
    }

    /**
     * Setter for property stopPollInterval.
     * 
     * @param stopPollInterval New value of property stopPollInterval.
     */
    public void setStopPollInterval(String stopPollInterval) {
        this.stopPollInterval = stopPollInterval;
    }

    /**
     * Gets the statusMap attribute.
     * 
     * @return Returns the statusMap.
     */
    public Map<String, String> getStatusMap() {
        return statusMap;
    }

    /**
     * Sets the statusMap attribute value.
     * 
     * @param statusMap The statusMap to set.
     */
    public void setStatusMap(Map<String, String> statusMap) {
        this.statusMap = statusMap;
    }

    /**
     * Gets the mailInfoList attribute.
     * 
     * @return Returns the mailInfoList.
     */
    public List<MailInfo> getMailInfoList() {
        return mailInfoList;
    }

    /**
     * Sets the mailInfoList attribute value.
     * 
     * @param mailInfoList The mailInfoList to set.
     */
    public void setMailInfoList(List<MailInfo> mailInfoList) {
        this.mailInfoList = mailInfoList;
    }

    /**
     * Gets the mailInterval attribute.
     * 
     * @return Returns the mailInterval.
     */
    public String getMailInterval() {
        return mailInterval;
    }

    /**
     * Sets the mailInterval attribute value.
     * 
     * @param mailInterval The mailInterval to set.
     */
    public void setMailInterval(String mailInterval) {
        this.mailInterval = mailInterval;
    }


    /**
     * Sets the businessObjectService attribute value.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * @param s2SService the s2sService to set
     */
    public void sets2SService(S2SService s2SService) {
        this.s2SService = s2SService;
    }
}
