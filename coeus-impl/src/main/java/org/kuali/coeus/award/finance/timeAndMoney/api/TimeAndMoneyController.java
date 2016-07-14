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
import org.kuali.coeus.award.finance.timeAndMoney.dto.TimeAndMoneyDto;
import org.kuali.coeus.award.finance.timeAndMoney.dto.TimeAndMoneyPostDto;
import org.kuali.coeus.award.finance.timeAndMoney.dto.TransactionDetailDto;
import org.kuali.coeus.common.api.document.service.CommonApiService;
import org.kuali.coeus.sys.framework.controller.rest.RestController;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.kra.timeandmoney.dao.TimeAndMoneyDao;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TransactionDetail;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
