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
package org.kuali.coeus.award.finance.timeAndMoney.api;


import org.kuali.coeus.award.finance.timeAndMoney.TimeAndMoneyPosts;
import org.kuali.coeus.award.finance.timeAndMoney.dao.TimeAndMoneyPostsDao;
import org.kuali.coeus.award.finance.timeAndMoney.dto.AwardAmountTransactionDto;
import org.kuali.coeus.award.finance.timeAndMoney.dto.TimeAndMoneyDto;
import org.kuali.coeus.award.finance.timeAndMoney.dto.TimeAndMoneyPostDto;
import org.kuali.coeus.award.finance.timeAndMoney.dto.TransactionDetailDto;
import org.kuali.coeus.common.api.document.service.CommonApiService;
import org.kuali.coeus.sys.framework.controller.rest.RestController;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.coeus.sys.framework.rest.UnprocessableEntityException;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.award.dao.AwardDao;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.version.service.AwardVersionService;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.dao.TimeAndMoneyDao;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TransactionDetail;
import org.kuali.kra.timeandmoney.rules.TimeAndMoneyAwardDateSaveRuleImpl;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyService;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyVersionService;
import org.kuali.kra.timeandmoney.transactions.PendingTransaction;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.exception.ValidationException;
import org.kuali.rice.krad.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping(value="/api/v1")
@Controller("timeAndMoneyController")
public class TimeAndMoneyController extends RestController {

    @Autowired
    @Qualifier("timeAndMoneyPostsDao")
    private TimeAndMoneyPostsDao timeAndMoneyPostsDao;

    @Autowired
    @Qualifier("timeAndMoneyDao")
    private TimeAndMoneyDao timeAndMoneyDao;

    @Autowired
    @Qualifier("commonApiService")
    private CommonApiService commonApiService;

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("timeAndMoneyService")
    private TimeAndMoneyService timeAndMoneyService;
    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;
    @Autowired
    @Qualifier("awardDao")
    private AwardDao awardDao;
    @Autowired
    @Qualifier("awardService")
    private AwardService awardService;
    @Autowired
    @Qualifier("awardHierarchyService")
    private AwardHierarchyService awardHierarchyService;
    @Autowired
    @Qualifier("awardVersionService")
    private AwardVersionService awardVersionService;
    @Autowired
    @Qualifier("timeAndMoneyVersionService")
    private TimeAndMoneyVersionService timeAndMoneyVersionService;

    @RequestMapping(method= RequestMethod.GET, value="/time-and-money-posts/",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    List<TimeAndMoneyPostDto> getTimeAndMoneyPosts() {
        List<TimeAndMoneyPosts> timeAndMoneyPosts;
        timeAndMoneyPosts = timeAndMoneyPostsDao.getActiveTimeAndMoneyPosts();
        return timeAndMoneyPosts.stream().map(
                timeAndMoneyPost -> translateTimeAndMoney(timeAndMoneyPost)
        ).collect(Collectors.toList());
    }

    @RequestMapping(method= RequestMethod.GET, value="/time-and-money-posts/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    TimeAndMoneyPostDto getPost(@PathVariable Long id) {
        TimeAndMoneyPosts timeAndMoneyPosts = timeAndMoneyPostsDao.getTimeAndMoneyPost(id);
        if(timeAndMoneyPosts == null) {
            throw new ResourceNotFoundException("Time and money posts with id " + id + " not found.");
        }
        return translateTimeAndMoney(timeAndMoneyPosts);
    }

    @RequestMapping(method= RequestMethod.PUT, value="/time-and-money-posts/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    TimeAndMoneyPostDto putTimeAndMoneyPost(@RequestBody TimeAndMoneyPostDto timeAndMoneyPostDto, @PathVariable Long id) {
        TimeAndMoneyPosts timeAndMoneyPosts = timeAndMoneyPostsDao.getTimeAndMoneyPost(id);
        if(timeAndMoneyPosts == null) {
            throw new ResourceNotFoundException("Time and money posts with id " + id + " not found.");
        }
        timeAndMoneyPosts.setActive(timeAndMoneyPostDto.isActive());
        dataObjectService.save(timeAndMoneyPosts);
        return commonApiService.convertObject(timeAndMoneyPosts, TimeAndMoneyPostDto.class);
    }

    @RequestMapping(method= RequestMethod.GET, value="/time-and-money-documents/{documentNumber}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    TimeAndMoneyDto getTimeAndMoneydocument(@PathVariable String documentNumber) {
        return getTimeAndMoneyDto(documentNumber);
    }

    @RequestMapping(method= RequestMethod.POST, value="/time-and-money-documents/",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String createTimeAndMoneyDocument(@RequestBody TimeAndMoneyDto timeAndMoneyDto) throws Exception {
        commonApiService.clearErrors();
        String awardId = timeAndMoneyDto.getAwardId();
        Award award = awardDao.getAward(Long.parseLong(awardId));
        if(award == null) {
            throw new ResourceNotFoundException("Award with award id " + awardId + " not found.");
        }
        TimeAndMoneyAwardDateSaveRuleImpl timeAndMoneyAwardDateSaveRuleImpl = new TimeAndMoneyAwardDateSaveRuleImpl();
        timeAndMoneyAwardDateSaveRuleImpl.enforceAwardStartDatePopulated(award);
        TimeAndMoneyDocument timeAndMoneyDocument;
        if(globalVariableService.getMessageMap().hasNoErrors()) {
            String rootAwardNumber = awardService.getRootAwardNumber(award.getAwardNumber());
            String documentNumber = timeAndMoneyVersionService.getCurrentTimeAndMoneyDocumentNumber(rootAwardNumber);
            if (documentNumber == null) {
                if (!timeAndMoneyVersionService.validateCreateNewTimeAndMoneyDocument(rootAwardNumber)) {
                    throw new UnprocessableEntityException(commonApiService.getValidationErrors());
                }
                try {
                    timeAndMoneyDocument = createTimeAndMoneyDocument(timeAndMoneyDto, award, rootAwardNumber);
                    documentService.saveDocument(timeAndMoneyDocument);
                } catch (ValidationException exception) {
                    throw new UnprocessableEntityException(commonApiService.getValidationErrors());
                }
            } else {
                throw new UnprocessableEntityException("A time and money document already exists for this award.");
            }
        } else {
            throw new UnprocessableEntityException(commonApiService.getValidationErrors());
        }
        return timeAndMoneyDocument.getDocumentNumber();
    }

    private TimeAndMoneyDocument createTimeAndMoneyDocument(TimeAndMoneyDto timeAndMoneyDto, Award award, String rootAwardNumber) throws Exception {
        TimeAndMoneyDocument timeAndMoneyDocument;
        timeAndMoneyDocument = timeAndMoneyService.setupTimeAndMoneyDocument(rootAwardNumber, award);
        commonApiService.saveDocument(timeAndMoneyDocument);
        addTransactionDetails(timeAndMoneyDto, timeAndMoneyDocument);
        updateAwardAmountTransactionInformation(timeAndMoneyDocument, timeAndMoneyDto.getAwardAmountTransactions());
        captureAmountAndDateChanges(award, rootAwardNumber, timeAndMoneyDocument);
        return timeAndMoneyDocument;
    }

    private void updateAwardAmountTransactionInformation(TimeAndMoneyDocument timeAndMoneyDocument, List<AwardAmountTransactionDto> awardAmountTransactions) {
        if(awardAmountTransactions.size() == 0) {
            throw new UnprocessableEntityException("T & M doc cannot processed without transaction information.");
        }
        timeAndMoneyDocument.getAwardAmountTransactions().get(0).setNoticeDate(awardAmountTransactions.get(0).getNoticeDate());
        timeAndMoneyDocument.getAwardAmountTransactions().get(0).setTransactionTypeCode(awardAmountTransactions.get(0).getTransactionTypeCode());
        timeAndMoneyDocument.getAwardAmountTransactions().get(0).setComments(awardAmountTransactions.get(0).getComments());
    }

    private void captureAmountAndDateChanges(Award award, String rootAwardNumber, TimeAndMoneyDocument timeAndMoneyDocument) throws Exception {
        timeAndMoneyService.populateAwardHierarchyItems(timeAndMoneyDocument, rootAwardNumber, new ArrayList<>());
        Map<String, AwardHierarchyNode> awardHierarchyNodeItems = new HashMap<>();
        awardHierarchyService.populateAwardHierarchyNodes(timeAndMoneyDocument.getAwardHierarchyItems(), awardHierarchyNodeItems,
                award.getAwardNumber(), award.getSequenceNumber().toString());

        final ArrayList<AwardHierarchyNode> awardHierarchyNodes = new ArrayList<>(awardHierarchyNodeItems.values());
        // have to do this because the awardHierarchyNodes have nothing at index 0.
        // -000001 is at index1, -000002 is at index2 and so on.
        awardHierarchyNodes.add(0, null);
        timeAndMoneyService.captureDateChangeTransactions(timeAndMoneyDocument, awardHierarchyNodes);
        captureSingleNodeMoneyTransactions(timeAndMoneyDocument, awardHierarchyNodes);
    }

    protected void addTransactionDetails(TimeAndMoneyDto timeAndMoneyDto, TimeAndMoneyDocument timeAndMoneyDocument) {
        if(timeAndMoneyDto.getTransactionDetails() != null) {
            timeAndMoneyDocument.setPendingTransactions(timeAndMoneyDto.getTransactionDetails().stream().map(transactionDetail -> {
                        PendingTransaction newPendingTransaction = commonApiService.convertObject(transactionDetail, PendingTransaction.class);
                        newPendingTransaction.setAnticipatedAmount(newPendingTransaction.getAnticipatedDirectAmount().add(newPendingTransaction.getAnticipatedIndirectAmount()));
                        newPendingTransaction.setObligatedAmount(newPendingTransaction.getObligatedDirectAmount().add(newPendingTransaction.getObligatedIndirectAmount()));
                        return newPendingTransaction;
                    }
            ).collect(Collectors.toList()));
        }
    }

    @RequestMapping(method= RequestMethod.PUT, value="/time-and-money-documents/{documentNumber}", params="version",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String versionTimeAndMoney(@RequestBody TimeAndMoneyDto timeAndMoneyDto, @PathVariable String documentNumber) throws Exception {
        commonApiService.clearErrors();
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyDao.getTimeAndMoneyDocument(documentNumber);
        if(timeAndMoneyDocument == null) {
            throw new ResourceNotFoundException("Time and money document with number " + documentNumber + " was not found.");
        }
        String rootAwardNumber = timeAndMoneyDocument.getRootAwardNumber();
        TimeAndMoneyDocument finalTandM = timeAndMoneyVersionService.findOpenedTimeAndMoney(rootAwardNumber);
        addTransactionDetails(timeAndMoneyDto, finalTandM);
        Award award = awardVersionService.getWorkingAwardVersion(rootAwardNumber);
        updateAwardAmountTransactions(finalTandM, timeAndMoneyDto.getAwardAmountTransactions(), rootAwardNumber);
        finalTandM.setAward(award);
        captureAmountAndDateChanges(award, rootAwardNumber, finalTandM);
        documentService.saveDocument(finalTandM);
        return finalTandM.getDocumentNumber();
    }

    private void updateAwardAmountTransactions(TimeAndMoneyDocument finalTandM, List<AwardAmountTransactionDto> awardAmountTransactions, String rootAwardNumber) {
        finalTandM.getAwardAmountTransactions().get(0).setAwardNumber(rootAwardNumber);
        finalTandM.getAwardAmountTransactions().get(0).setDocumentNumber(finalTandM.getDocumentNumber());

        updateAwardAmountTransactionInformation(finalTandM, awardAmountTransactions);
    }

    @RequestMapping(method= RequestMethod.PUT, value="/time-and-money-documents/{documentNumber}", params="submit",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public TimeAndMoneyDto submitDocument(@PathVariable String documentNumber) throws WorkflowException {
        commonApiService.clearErrors();
        TimeAndMoneyDocument timeAndMoneyDocument = (TimeAndMoneyDocument) commonApiService.getDocumentFromDocId(Long.parseLong(documentNumber));
        Award award = awardVersionService.getWorkingAwardVersion(timeAndMoneyDocument.getRootAwardNumber());
        timeAndMoneyDocument.setAward(award);
        commonApiService.routeDocument(timeAndMoneyDocument);
        return getTimeAndMoneyDto(timeAndMoneyDocument.getDocumentNumber());
    }

    public void captureSingleNodeMoneyTransactions(TimeAndMoneyDocument timeAndMoneyDocument, List<AwardHierarchyNode> awardHierarchyNodeItems) throws Exception {
        List<TransactionDetail> moneyTransactionDetailItems = new ArrayList<>();
        timeAndMoneyService.updateAwardAmountTransactions(timeAndMoneyDocument);
        if (timeAndMoneyDocument.getAwardHierarchyNodes().size() == 1) {
            for(Map.Entry<String, AwardHierarchyNode> awardHierarchyNode : timeAndMoneyDocument.getAwardHierarchyNodes().entrySet()) {
                timeAndMoneyService.captureMoneyChanges(awardHierarchyNodeItems, timeAndMoneyDocument, moneyTransactionDetailItems, awardHierarchyNode);
            }
        }
    }
    protected void addTransactionDetails(String documentNumber, TimeAndMoneyDto timeAndMoneyDto) {
        List<TransactionDetail> transactionDetails = timeAndMoneyDao.getTransactionDetailsForDocument(documentNumber);
        List<TransactionDetailDto> transactionDetailsDtos = transactionDetails.stream().map(transactionDetail ->
                        commonApiService.convertObject(transactionDetail, TransactionDetailDto.class)
        ).collect(Collectors.toList());
        timeAndMoneyDto.setTransactionDetails(transactionDetailsDtos);
    }

    protected TimeAndMoneyPostDto translateTimeAndMoney(TimeAndMoneyPosts timeAndMoneyPost) {
        TimeAndMoneyPostDto timeAndMoneyPostDto = commonApiService.convertObject(timeAndMoneyPost, TimeAndMoneyPostDto.class);
        TimeAndMoneyDto timeAndMoneyDto = getTimeAndMoneyDto(timeAndMoneyPost.getDocumentNumber());
        timeAndMoneyPostDto.setTimeAndMoney(timeAndMoneyDto);
        return timeAndMoneyPostDto;
    }

    protected TimeAndMoneyDto getTimeAndMoneyDto(String documentNumber) {
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyDao.getTimeAndMoneyDocument(documentNumber);
        if(timeAndMoneyDocument == null) {
            throw new ResourceNotFoundException("Time and money document with document number " + documentNumber + " not found.");
        }
        TimeAndMoneyDto timeAndMoneyDto = commonApiService.convertObject(timeAndMoneyDocument, TimeAndMoneyDto.class);
        addTransactionDetails(documentNumber, timeAndMoneyDto);
        return timeAndMoneyDto;
    }

}
