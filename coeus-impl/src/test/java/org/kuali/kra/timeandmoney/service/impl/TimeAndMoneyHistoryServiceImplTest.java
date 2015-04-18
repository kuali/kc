package org.kuali.kra.timeandmoney.service.impl;

import java.util.*;

import org.junit.Test;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.timeandmoney.AwardAmountInfoHistory;
import org.kuali.kra.timeandmoney.history.TransactionDetail;
import org.kuali.kra.timeandmoney.history.TransactionDetailType;

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
}
