package org.kuali.coeus.instprop.impl.api;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.kuali.coeus.sys.framework.controller.rest.RestController;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.dao.InstitutionalProposalDao;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value="/api")
@Controller("institutionalProposalPersonOrderingReportController")
public class InstitutionalProposalPersonOrderingReportController extends RestController {

	@Autowired
	@Qualifier("institutionalProposalDao")
	private InstitutionalProposalDao institutionalProposalDao;
	
	@RequestMapping(method= RequestMethod.GET, value="/v1/institutional-proposal-person-ordering-report/",
        consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody List<String> generateOrderingMismatchReport(@RequestParam(required=false) Instant startDate, @RequestParam(required=false) Instant endDate) {
		return institutionalProposalDao.getProposalsModifiedBetween(Date.from(startDate), Date.from(endDate)).stream()
			.filter(instProp -> {
				List<InstitutionalProposalPerson> unsortedList = new ArrayList<>(instProp.getUnsortedProjectPersons());
				List<InstitutionalProposalPerson> sortedList = instProp.getProjectPersons();
				return !unsortedList.equals(sortedList);
			}).map(InstitutionalProposal::getProposalNumber).distinct().collect(Collectors.toList());
	}

	public InstitutionalProposalDao getInstitutionalProposalDao() {
		return institutionalProposalDao;
	}

	public void setInstitutionalProposalDao(InstitutionalProposalDao institutionalProposalDao) {
		this.institutionalProposalDao = institutionalProposalDao;
	}
	
}
