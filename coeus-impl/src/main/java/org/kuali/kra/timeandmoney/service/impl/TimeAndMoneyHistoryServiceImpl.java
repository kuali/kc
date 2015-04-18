/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.timeandmoney.service.impl;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.controller.DocHandlerService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.version.service.AwardVersionService;
import org.kuali.kra.timeandmoney.AwardAmountInfoHistory;
import org.kuali.kra.timeandmoney.AwardVersionHistory;
import org.kuali.kra.timeandmoney.TimeAndMoneyDocumentHistory;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TransactionDetail;
import org.kuali.kra.timeandmoney.history.TransactionDetailType;
import org.kuali.kra.timeandmoney.history.TransactionType;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyHistoryService;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.ken.util.NotificationConstants;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.*;

public class TimeAndMoneyHistoryServiceImpl implements TimeAndMoneyHistoryService {

	private static final String DATE_CHANGE_TRANSACTION_ID = "-1";
	private static final String TIME_AND_MONEY_DOCUMENT_NUMBER = "timeAndMoneyDocumentNumber";
	private static final String AWARD_DOCUMENT = "awardDocument";
	private static final String ROOT_AWARD_NUMBER = "rootAwardNumber";
	private static final String SOURCE_AWARD_NUMBER = "sourceAwardNumber";
	private static final String DESTINATION_AWARD_NUMBER = "destinationAwardNumber";
	private static final String TRANSACTION_DETAIL_TYPE = "transactionDetailType";
	private static final String TRANSACTION_ID = "transactionId";
	private static final String TRANSACTION_DETAIL_ID = "transactionDetailId";
	private static final int NUMBER_30 = 30;
	private static final String DASH = "-";
	private static final String DEFAULT_TAB = "Versions";
	private static final String ALTERNATE_OPEN_TAB = "Parameters";
	public static final int INITIAL_TRANSACTION_ID = 0;

	private BusinessObjectService businessObjectService;
	private DocumentService documentService;
	private AwardVersionService awardVersionService;

	@SuppressWarnings("unchecked")
	public void buildTimeAndMoneyHistoryObjects(String awardNumber, List<AwardVersionHistory> awardVersionHistoryCollection) throws WorkflowException {
		List<Award> awardVersionList = (List<Award>) businessObjectService.findMatchingOrderBy(Award.class, getHashMapToFindActiveAward(awardNumber), "sequenceNumber", true);
		// we want the list in reverse chronological order.
		Collections.reverse(awardVersionList);
		List<TimeAndMoneyDocument> docs = null;
		Map<String, Object> fieldValues1 = new HashMap<String, Object>();
		// get the root award number.
		fieldValues1.put(ROOT_AWARD_NUMBER, getRootAwardNumberForDocumentSearch(awardVersionList.get(0).getAwardNumber()));
		docs = (List<TimeAndMoneyDocument>) businessObjectService.findMatchingOrderBy(TimeAndMoneyDocument.class, fieldValues1, "documentNumber", true);
		// we don't want canceled docs to show in history.
		removeCanceledDocs(docs);
		for (Award award : awardVersionList) {
			AwardVersionHistory awardVersionHistory = new AwardVersionHistory(award);
			awardVersionHistory.setDocumentUrl(buildForwardUrl(award.getAwardDocument().getDocumentNumber()));
			awardVersionHistory.setAwardDescriptionLine(buildNewAwardDescriptionLine(award));
			awardVersionHistory.setTimeAndMoneyDocumentHistoryList(getDocHistoryAndValidInfosAssociatedWithAwardVersion(docs, award.getAwardAmountInfos(), award));

			awardVersionHistoryCollection.add(awardVersionHistory);
		}
	}

	public List<TimeAndMoneyDocumentHistory> getDocHistoryAndValidInfosAssociatedWithAwardVersion(List<TimeAndMoneyDocument> docs, List<AwardAmountInfo> awardAmountInfos, Award award)
			throws WorkflowException {
		List<TimeAndMoneyDocumentHistory> timeAndMoneyDocumentHistoryList = new ArrayList<TimeAndMoneyDocumentHistory>();
		List<AwardAmountInfo> validInfos = getValidAwardAmountInfosAssociatedWithAwardVersion(awardAmountInfos, award);
		List<TimeAndMoneyDocument> awardVersionDocs = getValidDocumentsCreatedForAwardVersion(docs, validInfos);
		// we want the list in reverse chronological order.
		Collections.reverse(awardVersionDocs);
		for (TimeAndMoneyDocument doc : awardVersionDocs) {
			TimeAndMoneyDocumentHistory docHistory = new TimeAndMoneyDocumentHistory();
			docHistory.setTimeAndMoneyDocument(doc);
			docHistory.setDocumentUrl(buildForwardUrl(doc.getDocumentNumber()));
			docHistory.setTimeAndMoneyDocumentDescriptionLine(buildNewTimeAndMoneyDescriptionLine(doc));
			docHistory.setValidAwardAmountInfoHistoryList(retrieveAwardAmountInfosFromPrimaryTransactions(doc, validInfos));
			timeAndMoneyDocumentHistoryList.add(docHistory);
		}

		return timeAndMoneyDocumentHistoryList;
	}

	protected List<AwardAmountInfoHistory> retrieveAwardAmountInfosFromPrimaryTransactions(TimeAndMoneyDocument doc, List<AwardAmountInfo> validInfos) {
		List<AwardAmountInfoHistory> primaryInfos = new ArrayList<AwardAmountInfoHistory>();

		primaryInfos.addAll(captureMoneyInfos(doc.getDocumentNumber(), validInfos));
		primaryInfos.addAll(captureDateInfos(doc, validInfos));
		primaryInfos.addAll(captureInitialTransactionInfo(doc, validInfos));
		return primaryInfos;
	}

	protected List<AwardAmountInfoHistory> captureMoneyInfos(String timeAndMoneyDocumentNumber, List<AwardAmountInfo> validInfos) {
		List<AwardAmountInfoHistory> moneyInfoHistoryList = new ArrayList<AwardAmountInfoHistory>();

		for (AwardAmountInfo awardAmountInfo : validInfos) {
			if (awardAmountInfo.getTimeAndMoneyDocumentNumber() != null && StringUtils.equalsIgnoreCase(timeAndMoneyDocumentNumber, awardAmountInfo.getTimeAndMoneyDocumentNumber())) {
				CollectionUtils.addIgnoreNull(moneyInfoHistoryList, getMoneyInfoHistory(awardAmountInfo, getTransactions(awardAmountInfo.getTransactionId())));
			}
		}
		return moneyInfoHistoryList;
	}

	private AwardAmountInfoHistory getMoneyInfoHistory(AwardAmountInfo awardAmountInfo, List<TransactionDetail> transactions) {
		TransactionDetail primaryTransaction = getPrimaryTransaction(transactions);
		if (primaryTransaction != null) {
			AwardAmountInfoHistory awardAmountInfoHistory = new AwardAmountInfoHistory();
			awardAmountInfoHistory.setAwardAmountInfo(awardAmountInfo);
			awardAmountInfoHistory.setTransactionType(TransactionType.MONEY.toString());
			awardAmountInfoHistory.setPrimaryDetail(primaryTransaction);
			awardAmountInfoHistory.setIntermediateDetails(getIntermediateTransactions(transactions));
			return awardAmountInfoHistory;
		}
		return null;
	}

	protected TransactionDetail getPrimaryTransaction(List<TransactionDetail> transactions) {
		for (TransactionDetail detail : transactions) {
			if (TransactionDetailType.PRIMARY.toString().equals(detail.getTransactionDetailType())) {
				return detail;
			}
		}
		return null;
	}

	protected List<TransactionDetail> getIntermediateTransactions(List<TransactionDetail> transactions) {
		return ListUtils.select(transactions, new Predicate<TransactionDetail>() {

			@Override
			public boolean evaluate(TransactionDetail detail) {
				return TransactionDetailType.INTERMEDIATE.toString().equals(detail.getTransactionDetailType());
			}

		});
	}

	protected List<TransactionDetail> getTransactions(Long transactionId) {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put(TRANSACTION_ID, transactionId);
		return ((List<TransactionDetail>) businessObjectService.findMatchingOrderBy(TransactionDetail.class, values, TRANSACTION_DETAIL_ID, true));
	}

	@SuppressWarnings("unchecked")
	protected List<AwardAmountInfoHistory> captureDateInfos(TimeAndMoneyDocument doc, List<AwardAmountInfo> validInfos) {
		List<AwardAmountInfoHistory> dateInfoHistoryList = new ArrayList<AwardAmountInfoHistory>();
		Map<String, Object> fieldValues = new HashMap<String, Object>();
		for (AwardAmountInfo awardAmountInfo : validInfos) {
			if (!(awardAmountInfo.getTimeAndMoneyDocumentNumber() == null)) {
				if (StringUtils.equalsIgnoreCase(doc.getDocumentNumber(), awardAmountInfo.getTimeAndMoneyDocumentNumber())) {
					if (awardAmountInfo.getTransactionId() == null) {
						fieldValues.put(SOURCE_AWARD_NUMBER, awardAmountInfo.getAwardNumber());
						fieldValues.put(TRANSACTION_ID, DATE_CHANGE_TRANSACTION_ID);
						fieldValues.put(TIME_AND_MONEY_DOCUMENT_NUMBER, awardAmountInfo.getTimeAndMoneyDocumentNumber());
						List<TransactionDetail> dateTransactionDetails = ((List<TransactionDetail>) businessObjectService.findMatchingOrderBy(TransactionDetail.class, fieldValues,
								SOURCE_AWARD_NUMBER, true));
						if (dateTransactionDetails.size() > 0) {
							AwardAmountInfoHistory awardAmountInfoHistory = new AwardAmountInfoHistory();
							awardAmountInfoHistory.setAwardAmountInfo(awardAmountInfo);
							awardAmountInfoHistory.setTransactionType(TransactionType.DATE.toString());
							dateInfoHistoryList.add(awardAmountInfoHistory);
						}
					}
				}
			}
		}
		return dateInfoHistoryList;
	}

	@SuppressWarnings("unchecked")
	protected List<AwardAmountInfoHistory> captureInitialTransactionInfo(TimeAndMoneyDocument doc, List<AwardAmountInfo> validInfos) {
		List<AwardAmountInfoHistory> initialInfoHistoryList = new ArrayList<AwardAmountInfoHistory>();
		Map<String, Object> fieldValues = new HashMap<String, Object>();
		for (AwardAmountInfo awardAmountInfo : validInfos) {
			if (!(awardAmountInfo.getTimeAndMoneyDocumentNumber() == null)) {
				if (StringUtils.equalsIgnoreCase(doc.getDocumentNumber(), awardAmountInfo.getTimeAndMoneyDocumentNumber())) {
					fieldValues.put(DESTINATION_AWARD_NUMBER, awardAmountInfo.getAwardNumber());
					fieldValues.put(TRANSACTION_ID, 0);
					fieldValues.put(TIME_AND_MONEY_DOCUMENT_NUMBER, awardAmountInfo.getTimeAndMoneyDocumentNumber());
					fieldValues.put(TRANSACTION_DETAIL_TYPE, TransactionDetailType.PRIMARY.toString());
					List<TransactionDetail> transactionDetailsA = ((List<TransactionDetail>) businessObjectService.findMatchingOrderBy(TransactionDetail.class, fieldValues, SOURCE_AWARD_NUMBER, true));
					if (transactionDetailsA.size() > 0) {
						AwardAmountInfoHistory awardAmountInfoHistory = new AwardAmountInfoHistory();
						awardAmountInfoHistory.setAwardAmountInfo(awardAmountInfo);
						awardAmountInfoHistory.setTransactionType(TransactionType.INITIAL.toString());
						initialInfoHistoryList.add(awardAmountInfoHistory);
						break;
					} else {
						break;
					}
				}
			}
		}
		return initialInfoHistoryList;
	}

	protected String buildNewTimeAndMoneyDescriptionLine(TimeAndMoneyDocument doc) {
		AwardAmountTransaction aat = doc.getAwardAmountTransactions().get(0);
		String noticeDate;
		String transactionTypeDescription;

		if (!(aat.getNoticeDate() == null)) {
			noticeDate = aat.getNoticeDate().toString();
		} else {
			noticeDate = "None";
		}
		if (!(aat.getAwardTransactionType() == null)) {
			transactionTypeDescription = aat.getAwardTransactionType().getDescription();
		} else {
			transactionTypeDescription = "None";
		}
		return "Time And Money Document: " + transactionTypeDescription + ", notice date: " + noticeDate + ", updated " + getUpdateTimeAndUser(doc) + ". Comments: "
				+ (aat.getComments() == null ? "None" : aat.getComments());
	}

	protected List<AwardAmountInfo> getValidAwardAmountInfosAssociatedWithAwardVersion(List<AwardAmountInfo> awardAmountInfos, Award award) {
		List<AwardAmountInfo> validInfos = new ArrayList<AwardAmountInfo>();
		for (AwardAmountInfo awardAmountInfo : awardAmountInfos) {
			if (!(awardAmountInfo.getOriginatingAwardVersion() == null)) {
				if (awardAmountInfo.getOriginatingAwardVersion().equals(award.getSequenceNumber())) {
					validInfos.add(awardAmountInfo);
				}
			}
		}
		return validInfos;
	}

	protected List<TimeAndMoneyDocument> getValidDocumentsCreatedForAwardVersion(List<TimeAndMoneyDocument> docs, List<AwardAmountInfo> validInfos) {
		List<TimeAndMoneyDocument> validDocs = new ArrayList<TimeAndMoneyDocument>();
		for (TimeAndMoneyDocument doc : docs) {
			if (isInValidInfosCollection(doc, validInfos)) {
				validDocs.add(doc);
			}
		}
		return validDocs;
	}

	protected Boolean isInValidInfosCollection(TimeAndMoneyDocument doc, List<AwardAmountInfo> validInfos) {
		Boolean valid = false;
		for (AwardAmountInfo awardAmountInfo : validInfos) {
			if (!(awardAmountInfo.getTimeAndMoneyDocumentNumber() == null)) {
				if (awardAmountInfo.getTimeAndMoneyDocumentNumber().equals(doc.getDocumentNumber())) {
					valid = true;
					break;
				}
			}
		}
		return valid;
	}

	protected void removeCanceledDocs(List<TimeAndMoneyDocument> docs) {
		List<TimeAndMoneyDocument> tempCanceledDocs = new ArrayList<TimeAndMoneyDocument>();
		for (TimeAndMoneyDocument doc : docs) {
			if (doc.getDocumentHeader().hasWorkflowDocument()) {
				if (doc.getDocumentHeader().getWorkflowDocument().isCanceled()) {
					tempCanceledDocs.add(doc);
				}
			}
		}
		docs.removeAll(tempCanceledDocs);
	}

	@SuppressWarnings("unchecked")
	public List<TimeAndMoneyDocument> buildTimeAndMoneyListForAwardDisplay(Award award) throws WorkflowException {
		Map<String, Object> fieldValues1 = new HashMap<String, Object>();
		// get the award number.
		fieldValues1.put(ROOT_AWARD_NUMBER, award.getAwardNumber());
		List<TimeAndMoneyDocument> docs = (List<TimeAndMoneyDocument>) businessObjectService.findMatchingOrderBy(TimeAndMoneyDocument.class, fieldValues1, "documentNumber", true);
		// we don't want canceled docs to show in history.
		removeCanceledDocs(docs);
		return docs;
	}

	public AwardVersionService getAwardVersionService() {
		awardVersionService = KcServiceLocator.getService(AwardVersionService.class);
		return awardVersionService;
	}

	protected Map<String, String> getHashMapToFindActiveAward(String goToAwardNumber) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("awardNumber", goToAwardNumber);
		return map;
	}

	public void setAwardVersionService(AwardVersionService awardVersionService) {
		this.awardVersionService = awardVersionService;
	}

	/**
	 * This method searches generates the next Award Node Number in Sequence.
	 * 
	 * @param awardNumber
	 * @return
	 */
	public String getRootAwardNumberForDocumentSearch(String awardNumber) {
		String[] splitAwardNumber = awardNumber.split(DASH);
		StringBuilder returnString = new StringBuilder(12);
		returnString.append(splitAwardNumber[0]);
		returnString.append(DASH);
		returnString.append("00001");
		return returnString.toString();
	}

	protected String buildAwardDescriptionLine(Award award, AwardAmountInfo awardAmountInfo, TimeAndMoneyDocument timeAndMoneyDocument) {
		AwardAmountTransaction aat = timeAndMoneyDocument.getAwardAmountTransactions().get(0);
		String noticeDate;
		String transactionTypeDescription;
		String versionNumber;
		if (awardAmountInfo == null || awardAmountInfo.getOriginatingAwardVersion() == null) {
			versionNumber = award.getSequenceNumber().toString();
		} else {
			versionNumber = awardAmountInfo.getOriginatingAwardVersion().toString();
		}
		if (!(aat.getNoticeDate() == null)) {
			noticeDate = aat.getNoticeDate().toString();
		} else {
			noticeDate = "None";
		}
		if (!(award.getAwardTransactionType() == null)) {
			transactionTypeDescription = award.getAwardTransactionType().getDescription();
		} else {
			transactionTypeDescription = "None";
		}
		return "Award Version " + versionNumber + ", " + transactionTypeDescription + ", notice date: " + noticeDate + ", updated " + getUpdateTimeAndUser(award);
	}

	protected String buildNewAwardDescriptionLine(Award award) {
		String noticeDate;
		String transactionTypeDescription;
		String versionNumber;

		versionNumber = award.getSequenceNumber().toString();

		if (!(award.getNoticeDate() == null)) {
			noticeDate = award.getNoticeDate().toString();
		} else {
			noticeDate = "None";
		}
		if (!(award.getAwardTransactionType() == null)) {
			transactionTypeDescription = award.getAwardTransactionType().getDescription();
		} else {
			transactionTypeDescription = "None";
		}
		return "Award Version " + versionNumber + ", " + transactionTypeDescription + ", notice date: " + noticeDate + ", updated " + getUpdateTimeAndUser(award) + ". Comments:"
				+ (award.getAwardCurrentActionComments().getComments() == null ? "None." : award.getAwardCurrentActionComments().getComments());
	}

	protected String getUpdateTimeAndUser(Award award) {
		return award.getUpdateTimeAndUser();
	}

	protected String getUpdateTimeAndUser(TimeAndMoneyDocument doc) {
		String createDateStr = null;
		String updateUser = null;
		if (doc.getUpdateTimestamp() != null) {
			createDateStr = CoreApiServiceLocator.getDateTimeService().toString(doc.getUpdateTimestamp(), "MM/dd/yy");
			updateUser = doc.getUpdateUser().length() > NUMBER_30 ? doc.getUpdateUser().substring(0, NUMBER_30) : doc.getUpdateUser();
		}
		return createDateStr + " by " + updateUser;
	}

	/**
	 * Gets the documentService attribute.
	 * 
	 * @return Returns the documentService.
	 */
	public DocumentService getDocumentService() {
		return documentService;
	}

	/**
	 * Sets the documentService attribute value.
	 * 
	 * @param documentService
	 *            The documentService to set.
	 */
	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}

	/**
	 * Gets the businessObjectService attribute.
	 * 
	 * @return Returns the businessObjectService.
	 */
	public BusinessObjectService getBusinessObjectService() {
		return businessObjectService;
	}

	/**
	 * Sets the businessObjectService attribute value.
	 * 
	 * @param businessObjectService
	 *            The businessObjectService to set.
	 */
	public void setBusinessObjectService(BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}

	/**
	 * Takes a routeHeaderId for a particular document and constructs the URL to
	 * forward to that document
	 *
	 * @return String
	 */
	protected String buildForwardUrl(String documentNumber) {
		DocHandlerService researchDocumentService = KcServiceLocator.getService(DocHandlerService.class);
		String forward = researchDocumentService.getDocHandlerUrl(documentNumber);
		forward = forward.replaceFirst(DEFAULT_TAB, ALTERNATE_OPEN_TAB);
		if (forward.indexOf("?") == -1) {
			forward += "?";
		} else {
			forward += "&";
		}
		forward += KewApiConstants.DOCUMENT_ID_PARAMETER + "=" + documentNumber;
		forward += "&" + KewApiConstants.COMMAND_PARAMETER + "=" + NotificationConstants.NOTIFICATION_DETAIL_VIEWS.DOC_SEARCH_VIEW;
		if (GlobalVariables.getUserSession().isBackdoorInUse()) {
			forward += "&" + KewApiConstants.BACKDOOR_ID_PARAMETER + "=" + GlobalVariables.getUserSession().getPrincipalName();
		}

		String returnVal = "<a href=\"" + forward + "\"target=\"_blank\">" + documentNumber + "</a>";
		return returnVal;
	}

}
