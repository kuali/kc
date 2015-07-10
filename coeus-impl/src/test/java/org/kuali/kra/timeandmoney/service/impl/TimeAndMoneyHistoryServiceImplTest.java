package org.kuali.kra.timeandmoney.service.impl;

import java.util.*;

import org.junit.Test;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.bo.CommentType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.timeandmoney.AwardAmountInfoHistory;
import org.kuali.kra.timeandmoney.AwardVersionHistory;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TransactionDetail;
import org.kuali.kra.timeandmoney.history.TransactionDetailType;
import org.kuali.rice.kew.api.exception.WorkflowException;

import static org.junit.Assert.*;

public class TimeAndMoneyHistoryServiceImplTest {
	@Test
	public void test_captureMoneyInfos_noPrimary() {
		TimeAndMoneyHistoryServiceImpl timeAndMoneyHistoryServiceImpl  = new TimeAndMoneyHistoryServiceImpl() {
			
			@Override
			public List<TransactionDetail> getTransactions(Long transactionId) {
				return new ArrayList<>();
			}

		};

		assertTrue(timeAndMoneyHistoryServiceImpl.captureMoneyInfos("1", new ArrayList<AwardAmountInfo>(){{
			add(new AwardAmountInfo());
		}}).isEmpty());
		
	}
	
	@Test
	public void test_captureMoneyInfos_withPrimary() {
		TimeAndMoneyHistoryServiceImpl timeAndMoneyHistoryServiceImpl  = new TimeAndMoneyHistoryServiceImpl() {
			
			@Override
			public List<TransactionDetail> getTransactions(Long transactionId) {
				return new ArrayList<TransactionDetail>() {{
					TransactionDetail detail = new TransactionDetail();
					detail.setSourceAwardNumber("Doug");
					detail.setDestinationAwardNumber("Travis");
					detail.setTransactionDetailType(TransactionDetailType.PRIMARY.toString());
					
					add(detail);
				}};
			}

		};

		assertFalse(timeAndMoneyHistoryServiceImpl.captureMoneyInfos("1", new ArrayList<AwardAmountInfo>(){{
			AwardAmountInfo info = new AwardAmountInfo();
			info.setTimeAndMoneyDocumentNumber("1");
			add(info);
		}}).isEmpty());
		
	}

	@Test
	public void test_captureMoneyInfos_withIntermediate() {
		TimeAndMoneyHistoryServiceImpl timeAndMoneyHistoryServiceImpl  = new TimeAndMoneyHistoryServiceImpl() {
			
			@Override
			public List<TransactionDetail> getTransactions(Long transactionId) {
				return new ArrayList<TransactionDetail>() {{
					TransactionDetail detail = new TransactionDetail();
					detail.setSourceAwardNumber("Doug");
					detail.setDestinationAwardNumber("Travis");
					detail.setTransactionDetailType(TransactionDetailType.PRIMARY.toString());
					
					add(detail);

					detail = new TransactionDetail();
					detail.setSourceAwardNumber("Doug");
					detail.setDestinationAwardNumber("Travis");
					detail.setTransactionDetailType(TransactionDetailType.INTERMEDIATE.toString());
					
					add(detail);
				}};
			}

		};

		List<AwardAmountInfoHistory> captureMoneyInfos = timeAndMoneyHistoryServiceImpl.captureMoneyInfos("1", new ArrayList<AwardAmountInfo>(){{
			AwardAmountInfo info = new AwardAmountInfo();
			info.setTimeAndMoneyDocumentNumber("1");
			add(info);
		}});
		assertFalse(captureMoneyInfos.isEmpty());
		assertEquals(1, captureMoneyInfos.get(0).getIntermediateDetails().size());
		
	}
	
	@Test
	public void test_buildAwardVersionHistoryList_withoutTimeAndMoney() throws WorkflowException {
 		TimeAndMoneyHistoryServiceImpl timeAndMoneyHistoryServiceImpl = new TimeAndMoneyHistoryServiceImpl() {
 			@Override
 			String getDocHandlerUrl(String documentNumber) {
 				return "http://foobar";
 			}
 			@Override
 			boolean isBackdoorUserInUse() {
 				return false;
 			}
 		};
 		List<Award> awardList = new ArrayList<Award>() {{
 			Award award1 = new Award();
 			award1.setSequenceNumber(1);
 			award1.setAwardDocument(new AwardDocument());
 			CommentType commentType = new CommentType();
 			commentType.setCommentTypeCode(Constants.CURRENT_ACTION_COMMENT_TYPE_CODE);
			award1.add(new AwardComment(commentType, "Test"));
 			add(award1);
 		}};
		List<AwardVersionHistory> awardVersionHistoryList = timeAndMoneyHistoryServiceImpl.buildAwardVersionHistoryList(awardList, new ArrayList<TimeAndMoneyDocument>());
		assertEquals(1, awardVersionHistoryList.size());
		assertTrue("AwardVersionHistory timeAndMoneyDocumentHistoryList is not empty", awardVersionHistoryList.get(0).getTimeAndMoneyDocumentHistoryList().isEmpty());
		assertTrue("AwardVersionHistory description line is empty", awardVersionHistoryList.get(0).getAwardDescriptionLine().length() > 0);
	}
}