package org.kuali.coeus.propdev.impl.budget.person;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonSalaryDetails;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonService;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelBudgetService;
import org.kuali.coeus.common.budget.framework.personnel.TbnPerson;
import org.kuali.coeus.common.view.wizard.framework.WizardControllerService;
import org.kuali.coeus.common.view.wizard.framework.WizardResultsDto;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetControllerBase;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetForm;
import org.kuali.coeus.common.framework.person.PersonTypeConstants;
import org.kuali.rice.krad.uif.UifParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/proposalBudget")
public class ProposalBudgetProjectPersonnelController extends ProposalBudgetControllerBase {

    @Autowired
    @Qualifier("wizardControllerService")
    private WizardControllerService wizardControllerService;

	@Autowired
	@Qualifier("budgetPersonService")
	private BudgetPersonService budgetPersonService;
	
	@Autowired
	@Qualifier("budgetPersonnelBudgetService")
	BudgetPersonnelBudgetService budgetPersonnelBudgetService;

	private static final String EDIT_PROJECT_PERSONNEL_DIALOG_ID = "PropBudget-EditPersonnel-Section";
	
	@RequestMapping(params="methodToCall=searchProjectPersonnel")
	public ModelAndView searchProjectPersonnel(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
       form.getAddProjectPersonnelHelper().getResults().clear();
       form.getAddProjectPersonnelHelper().setResults(getWizardControllerService().performWizardSearch(form.getAddProjectPersonnelHelper().getLookupFieldValues(),form.getAddProjectPersonnelHelper().getLineType()));
       return getModelAndViewService().getModelAndView(form);
	}

	@RequestMapping(params="methodToCall=addProjectPersonnel")
	public ModelAndView addProjectPersonnel(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
	   if(StringUtils.equals(form.getAddProjectPersonnelHelper().getLineType(), PersonTypeConstants.TBN.getCode())) {
	       for (TbnPerson person : form.getAddProjectPersonnelHelper().getTbnPersons()) {
		       for (int index=0 ; index < person.getQuantity();  index++) {
		    	   BudgetPerson newPerson = new BudgetPerson(person);
	    	       getBudgetPersonService().addBudgetPerson(form.getBudget(), newPerson);
		       }
	       }
	   }else {
	       for (Object object : form.getAddProjectPersonnelHelper().getResults()) {
               WizardResultsDto wizardResult = (WizardResultsDto) object;
               BudgetPerson newBudgetPerson = new BudgetPerson();
	           if (wizardResult.isSelected() == true) {
                   if (wizardResult.isSelected() == true) {
                       if (wizardResult.getKcPerson() != null) {
                           newBudgetPerson.setPersonId(wizardResult.getKcPerson().getPersonId());
                           newBudgetPerson.setPersonName(wizardResult.getKcPerson().getFullName());
                           newBudgetPerson.setUserName(wizardResult.getKcPerson().getUserName());
                       } else if (wizardResult.getRolodex() != null) {
                           newBudgetPerson.setRolodexId(wizardResult.getRolodex().getRolodexId());
                           newBudgetPerson.setPersonName(wizardResult.getRolodex().getFullName());
                       }
                       getBudgetPersonService().addBudgetPerson(form.getBudget(), newBudgetPerson);
                   }
	           }
	       }
	   }
       form.getAddProjectPersonnelHelper().reset();
       return getModelAndViewService().getModelAndView(form);
	}

	@RequestMapping(params="methodToCall=editPersonDetails")
	public ModelAndView editPersonDetails(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
	    String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);
        if (StringUtils.isNotEmpty(selectedLine)) {
		       BudgetPerson editBudgetPerson = form.getBudget().getBudgetPerson(Integer.parseInt(selectedLine));
		       form.getAddProjectPersonnelHelper().setEditBudgetPerson(editBudgetPerson);
		       form.getAddProjectPersonnelHelper().setEditLineIndex(selectedLine);
	    }
    	return getModelAndViewService().showDialog(EDIT_PROJECT_PERSONNEL_DIALOG_ID, true, form);
	}

	@RequestMapping(params="methodToCall=updatePersonDetails")
	public ModelAndView updatePersonDetails(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
	    int selectedLine = Integer.parseInt(form.getAddProjectPersonnelHelper().getEditLineIndex());
	    getBudgetPersonService().refreshBudgetPerson(form.getAddProjectPersonnelHelper().getEditBudgetPerson());
	    form.getBudget().getBudgetPersons().set(selectedLine, form.getAddProjectPersonnelHelper().getEditBudgetPerson());
	    getCollectionControllerService().saveLine(form);
	    return getModelAndViewService().getModelAndView(form);
	}
	
	@RequestMapping(params="methodToCall=calculatePersonSalaryDetails")
	public ModelAndView calculatePersonSalaryDetails(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
	    Budget budget = form.getBudget();
	    int selectedLine = Integer.parseInt(form.getAddProjectPersonnelHelper().getEditLineIndex());
	    List<BudgetPersonSalaryDetails> budgetPersonSalaryDetails = new ArrayList<BudgetPersonSalaryDetails>();
        budgetPersonSalaryDetails =  budgetPersonnelBudgetService.calculatePersonSalary(budget, selectedLine);        
        form.getBudget().getBudgetPerson(selectedLine).setBudgetPersonSalaryDetails(budgetPersonSalaryDetails);
	    return getModelAndViewService().getModelAndView(form);
	}
	
	@RequestMapping(params="methodToCall=syncFromProposal")
	public ModelAndView syncFromProposal(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
	    getBudgetPersonService().synchBudgetPersonsToProposal(form.getBudget());
	    return getModelAndViewService().getModelAndView(form);
	}

    public WizardControllerService getWizardControllerService() {
        return wizardControllerService;
    }

    public void setWizardControllerService(WizardControllerService wizardControllerService) {
        this.wizardControllerService = wizardControllerService;
    }

    public BudgetPersonService getBudgetPersonService() {
		return budgetPersonService;
	}

	public void setBudgetPersonService(BudgetPersonService budgetPersonService) {
		this.budgetPersonService = budgetPersonService;
	}

	public BudgetPersonnelBudgetService getBudgetPersonnelBudgetService() {
		return budgetPersonnelBudgetService;
	}

	public void setBudgetPersonnelBudgetService(
			BudgetPersonnelBudgetService budgetPersonnelBudgetService) {
		this.budgetPersonnelBudgetService = budgetPersonnelBudgetService;
	}

}
