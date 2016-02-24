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
package org.kuali.kra.timeandmoney.service.impl;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.sys.framework.controller.DocHandlerService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.dao.AwardDao;
import org.kuali.kra.award.dao.ojb.AwardDaoOjb;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.version.service.AwardVersionService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.timeandmoney.AwardAmountInfoHistory;
import org.kuali.kra.timeandmoney.AwardVersionHistory;
import org.kuali.kra.timeandmoney.TimeAndMoneyDocumentHistory;
import org.kuali.kra.timeandmoney.dao.TimeAndMoneyDao;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TransactionDetail;
import org.kuali.kra.timeandmoney.history.TransactionDetailType;
import org.kuali.kra.timeandmoney.history.TransactionType;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyHistoryService;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.ken.util.NotificationConstants;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

import java.util.*;
import java.util.stream.Collectors;

public class TimeAndMoneyHistoryServiceImpl implements TimeAndMoneyHistoryService {

	private static final String AWARD_VERSIONS_TO_DISPLAY_PARM = "AWARD_VERSIONS_TO_DISPLAY_FOR_TIME_AND_MONEY_HISTORY";
	private static final String TIME_AND_MONEY_DOCUMENT_NUMBER = "timeAndMoneyDocumentNumber";
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
	private static final String DOCUMENT_NUMBER = "documentNumber";
	private static final String NONE = "None";
	private static final String AWARD_NUMBER = "awardNumber";
	private static final String ROOT_AWARD_NUMBER_VAL = "00001";
	private static final String MM_DD_YY_FORMAT = "MM/dd/yy";

	private BusinessObjectService businessObjectService;
	private DocumentService documentService;
	private AwardVersionService awardVersionService;
	private DocHandlerService docHandlerService;
	private DateTimeService dateTimeService;
	private GlobalVariableService globalVariableService;
	private AwardDao awardDao;
	private TimeAndMoneyDao timeAndMoneyDao;
	private ParameterService parameterService;

	@Override
	public List<AwardVersionHistory> buildTimeAndMoneyHistoryObjects(String awardNumber, boolean bounded) throws WorkflowException {
		Integer maxAwards = new Integer(getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_TIME_AND_MONEY, ParameterConstants.DOCUMENT_COMPONENT, AWARD_VERSIONS_TO_DISPLAY_PARM));
		List<Award> awardVersionList = new ArrayList<>(awardDao.retrieveAwardsByCriteria(Collections.singletonMap(AWARD_NUMBER, awardNumber), null, 1, bounded ? maxAwards : -1).getResults());
		List<TimeAndMoneyDocument> docs = getTimeAndMoneyDao().getTimeAndMoneyDocumentForAwards(awardVersionList.stream().map((Award award) -> award.getAwardId()).collect(Collectors.toList()));
		return buildAwardVersionHistoryList(awardVersionList, docs);
	}

	List<AwardVersionHistory> buildAwardVersionHistoryList(List<Award> awardVersionList, List<TimeAndMoneyDocument> docs) throws WorkflowException {
		List<AwardVersionHistory> awardVersionHistoryCollection = new ArrayList<>();
		for (Award award : awardVersionList) {
            if (!award.getAwardSequenceStatus().equalsIgnoreCase(VersionStatus.CANCELED.toString())) {
                AwardVersionHistory awardVersionHistory = new AwardVersionHistory(award);
                awardVersionHistory.setDocumentUrl(buildForwardUrl(award.getAwardDocument().getDocumentNumber()));
                awardVersionHistory.setAwardDescriptionLine(award.getAwardDescriptionLine());
                awardVersionHistory.setTimeAndMoneyDocumentHistoryList(getDocHistoryAndValidInfosAssociatedWithAwardVersion(docs, award.getAwardAmountInfos(), award));
                awardVersionHistoryCollection.add(awardVersionHistory);
            }
		}
		return awardVersionHistoryCollection;
	}

	public List<TimeAndMoneyDocumentHistory> getDocHistoryAndValidInfosAssociatedWithAwardVersion(List<TimeAndMoneyDocument> docs, List<AwardAmountInfo> awardAmountInfos, Award award)
			throws WorkflowException {
		List<TimeAndMoneyDocumentHistory> timeAndMoneyDocumentHistoryList = new ArrayList<>();
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
		List<AwardAmountInfoHistory> primaryInfos = new ArrayList<>();

		primaryInfos.addAll(captureMoneyInfos(doc.getDocumentNumber(), validInfos));
		primaryInfos.addAll(captureDateInfos(doc, validInfos));
		primaryInfos.addAll(captureInitialTransactionInfo(doc, validInfos));
		return primaryInfos;
	}

	protected List<AwardAmountInfoHistory> captureMoneyInfos(String timeAndMoneyDocumentNumber, List<AwardAmountInfo> validInfos) {
		List<AwardAmountInfoHistory> moneyInfoHistoryList = new ArrayList<>();

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
		Map<String, Object> values = new HashMap<>();
		values.put(TRANSACTION_ID, transactionId);
		return ((List<TransactionDetail>) businessObjectService.findMatchingOrderBy(TransactionDetail.class, values, TRANSACTION_DETAIL_ID, true));
	}


	protected List<AwardAmountInfoHistory> captureDateInfos(TimeAndMoneyDocument doc, List<AwardAmountInfo> validInfos) {
		List<AwardAmountInfoHistory> dateInfoHistoryList = new ArrayList<>();
		Map<String, Object> fieldValues = new HashMap<>();
		for (AwardAmountInfo awardAmountInfo : validInfos) {
			if (!(awardAmountInfo.getTimeAndMoneyDocumentNumber() == null)) {
				if (StringUtils.equalsIgnoreCase(doc.getDocumentNumber(), awardAmountInfo.getTimeAndMoneyDocumentNumber())) {
					fieldValues.put(SOURCE_AWARD_NUMBER, awardAmountInfo.getAwardNumber());
					fieldValues.put(TRANSACTION_DETAIL_TYPE, TransactionDetailType.DATE.toString());
					fieldValues.put(TIME_AND_MONEY_DOCUMENT_NUMBER, awardAmountInfo.getTimeAndMoneyDocumentNumber());
					fieldValues.put(TRANSACTION_ID, awardAmountInfo.getTransactionId());
					Collection<TransactionDetail> dateTransactionDetails = businessObjectService.findMatching(TransactionDetail.class, fieldValues);
					if (!dateTransactionDetails.isEmpty()) {
						AwardAmountInfoHistory awardAmountInfoHistory = new AwardAmountInfoHistory();
						awardAmountInfoHistory.setAwardAmountInfo(awardAmountInfo);
						awardAmountInfoHistory.setTransactionType(TransactionType.DATE.toString());
						awardAmountInfoHistory.setDateDetail(dateTransactionDetails.iterator().next());
						dateInfoHistoryList.add(awardAmountInfoHistory);
					}
				}
			}
		}
		return dateInfoHistoryList;
	}

	
	protected List<AwardAmountInfoHistory> captureInitialTransactionInfo(TimeAndMoneyDocument doc, List<AwardAmountInfo> validInfos) {
		List<AwardAmountInfoHistory> initialInfoHistoryList = new ArrayList<>();
		Map<String, Object> fieldValues = new HashMap<>();
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
			noticeDate = NONE;
		}
		if (!(aat.getAwardTransactionType() == null)) {
			transactionTypeDescription = aat.getAwardTransactionType().getDescription();
		} else {
			transactionTypeDescription = NONE;
		}
		return "Time And Money Document: " + transactionTypeDescription + ", notice date: " + noticeDate + ", updated " + getUpdateTimeAndUser(doc) + ". Comments: "
				+ (aat.getComments() == null ? NONE : aat.getComments());
	}

	protected List<AwardAmountInfo> getValidAwardAmountInfosAssociatedWithAwardVersion(List<AwardAmountInfo> awardAmountInfos, Award award) {
		List<AwardAmountInfo> validInfos = new ArrayList<>();
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
		List<TimeAndMoneyDocument> validDocs = new ArrayList<>();
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
		List<TimeAndMoneyDocument> tempCanceledDocs = new ArrayList<>();
		for (TimeAndMoneyDocument doc : docs) {
            if (isDocCancelled(doc)) {
                tempCanceledDocs.add(doc);
            }
        }
		docs.removeAll(tempCanceledDocs);
	}

    protected boolean isDocCancelled(KcTransactionalDocumentBase doc) {
        return doc.getDocumentHeader().hasWorkflowDocument() && doc.getDocumentHeader().getWorkflowDocument().isCanceled();
    }

    public List<TimeAndMoneyDocument> buildTimeAndMoneyListForAwardDisplay(Award award, boolean bounded) throws WorkflowException {
		Map<String, Object> fieldValues1 = new HashMap<>();
		// get the award number.
		fieldValues1.put(ROOT_AWARD_NUMBER, award.getAwardNumber());
		List<TimeAndMoneyDocument> docs = (List<TimeAndMoneyDocument>) businessObjectService.findMatchingOrderBy(TimeAndMoneyDocument.class, fieldValues1, DOCUMENT_NUMBER, true);
		// we don't want canceled docs to show in history.
		removeCanceledDocs(docs);
		return docs;
	}

	protected Map<String, String> getHashMapToFindActiveAward(String goToAwardNumber) {
		Map<String, String> map = new HashMap<>();
		map.put(AWARD_NUMBER, goToAwardNumber);

		return map;
	}

	/**
	 * This method searches generates the next Award Node Number in Sequence.
	 */
	public String getRootAwardNumberForDocumentSearch(String awardNumber) {
		String[] splitAwardNumber = awardNumber.split(DASH);
		StringBuilder returnString = new StringBuilder(12);
		returnString.append(splitAwardNumber[0]);
		returnString.append(DASH);
		returnString.append(ROOT_AWARD_NUMBER_VAL);
		return returnString.toString();
	}

	protected String getUpdateTimeAndUser(TimeAndMoneyDocument doc) {
		String createDateStr = null;
		String updateUser = null;
		if (doc.getUpdateTimestamp() != null) {
			createDateStr = getDateTimeService().toString(doc.getUpdateTimestamp(), MM_DD_YY_FORMAT);
			updateUser = doc.getUpdateUser().length() > NUMBER_30 ? doc.getUpdateUser().substring(0, NUMBER_30) : doc.getUpdateUser();
		}
		return createDateStr + " by " + updateUser;
	}

	/**
	 * Takes a routeHeaderId for a particular document and constructs the URL to
	 * forward to that document
	 *
	 * @return String
	 */
	protected String buildForwardUrl(String documentNumber) {
		String forward = getDocHandlerUrl(documentNumber);
		forward = forward.replaceFirst(DEFAULT_TAB, ALTERNATE_OPEN_TAB);
		if (!forward.contains("?")) {
			forward += "?";
		} else {
			forward += "&";
		}
		forward += KewApiConstants.DOCUMENT_ID_PARAMETER + "=" + documentNumber;
		forward += "&" + KewApiConstants.COMMAND_PARAMETER + "=" + NotificationConstants.NOTIFICATION_DETAIL_VIEWS.DOC_SEARCH_VIEW;
		if (isBackdoorUserInUse()) {
			forward += "&" + KewApiConstants.BACKDOOR_ID_PARAMETER + "=" + globalVariableService.getUserSession().getPrincipalName();
		}

		return "<a href=\"" + forward + "\"target=\"_blank\">" + documentNumber + "</a>";
	}

	boolean isBackdoorUserInUse() {
		return globalVariableService.getUserSession().isBackdoorInUse();
	}

	String getDocHandlerUrl(String documentNumber) {
		String forward = getDocHandlerService().getDocHandlerUrl(documentNumber);
		return forward;
	}

	public DocumentService getDocumentService() {
		return documentService;
	}

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}

	public BusinessObjectService getBusinessObjectService() {
		return businessObjectService;
	}

	public void setBusinessObjectService(BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}

	public AwardVersionService getAwardVersionService() {
		return awardVersionService;
	}

	public void setAwardVersionService(AwardVersionService awardVersionService) {
		this.awardVersionService = awardVersionService;
	}

	public DocHandlerService getDocHandlerService() {
		return docHandlerService;
	}

	public void setDocHandlerService(DocHandlerService docHandlerService) {
		this.docHandlerService = docHandlerService;
	}

	public GlobalVariableService getGlobalVariableService() {
		return globalVariableService;
	}

	public void setGlobalVariableService(GlobalVariableService globalVariableService) {
		this.globalVariableService = globalVariableService;
	}

	public DateTimeService getDateTimeService() {
		return dateTimeService;
	}

	public void setDateTimeService(DateTimeService dateTimeService) {
		this.dateTimeService = dateTimeService;
	}

	public AwardDao getAwardDao() {
		return awardDao;
	}

	public void setAwardDao(AwardDao awardDao) {
		this.awardDao = awardDao;
	}

	public TimeAndMoneyDao getTimeAndMoneyDao() {
		return timeAndMoneyDao;
	}

	public void setTimeAndMoneyDao(TimeAndMoneyDao timeAndMoneyDao) {
		this.timeAndMoneyDao = timeAndMoneyDao;
	}

	public ParameterService getParameterService() {
		return parameterService;
	}

	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}
}
