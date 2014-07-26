package org.kuali.coeus.propdev.impl.budget.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.coeus.common.budget.framework.core.BudgetService;
import org.kuali.coeus.common.budget.framework.summary.BudgetSummaryService;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.sys.framework.controller.KcCommonControllerService;
import org.kuali.coeus.sys.framework.controller.UifExportControllerService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.TransactionalDocumentControllerService;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.kuali.rice.krad.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

public abstract class ProposalBudgetControllerBase {

    @Autowired
    @Qualifier("uifExportControllerService")
    private UifExportControllerService uifExportControllerService;

    @Autowired
    @Qualifier("kcCommonControllerService")
    private KcCommonControllerService kcCommonControllerService;

    @Autowired
    @Qualifier("transactionalDocumentControllerService")
    private TransactionalDocumentControllerService transactionalDocumentControllerService;

    @Autowired
    @Qualifier("collectionControllerService")
    private CollectionControllerService collectionControllerService;

    @Autowired
    @Qualifier("fileControllerService")
    private FileControllerService fileControllerService;

    @Autowired
    @Qualifier("modelAndViewService")
    private ModelAndViewService modelAndViewService;

    @Autowired
    @Qualifier("navigationControllerService")
    private NavigationControllerService navigationControllerService;

    @Autowired
    @Qualifier("queryControllerService")
    private QueryControllerService queryControllerService;

    @Autowired
    @Qualifier("refreshControllerService")
    private RefreshControllerService refreshControllerService;

    @Autowired
	@Qualifier("proposalBudgetService")
	private BudgetService<DevelopmentProposal> budgetService;
	
	@Autowired
	@Qualifier("dataObjectService")
	private DataObjectService dataObjectService;
	
	@Autowired
	@Qualifier("budgetSummaryService")
    private BudgetSummaryService budgetSummaryService;

    @Autowired
    @Qualifier("kualiConfigurationService")
    private ConfigurationService kualiConfigurationService;

    protected UifFormBase createInitialForm(HttpServletRequest request) {
        return new ProposalBudgetForm();
    }
    
    @ModelAttribute(value = "KualiForm")
    public UifFormBase initForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UifFormBase form =  getKcCommonControllerService().initForm(this.createInitialForm(request), request, response);
        return form;
    }	
    
    public ModelAndView save(ProposalBudgetForm form) throws Exception {
    	getDataObjectService().save(form.getBudget());
        return getModelAndViewService().getModelAndView(form);
    }
    
    protected ModelAndView navigate(ProposalBudgetForm form) throws Exception {
		form.setPageId(form.getActionParamaterValue(UifParameters.NAVIGATE_TO_PAGE_ID));
		form.setDirtyForm(false);
		return save(form);
    }

	public BudgetService<DevelopmentProposal> getBudgetService() {
		return budgetService;
	}

	public void setBudgetService(BudgetService<DevelopmentProposal> budgetService) {
		this.budgetService = budgetService;
	}

	public DataObjectService getDataObjectService() {
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}
	
	public ConfigurationService getKualiConfigurationService() {
		return kualiConfigurationService;
	}

	public void setKualiConfigurationService(ConfigurationService kualiConfigurationService) {
		this.kualiConfigurationService = kualiConfigurationService;
	}
	
	public BudgetSummaryService getBudgetSummaryService() {
		return budgetSummaryService;
	}

	public void setBudgetSummaryService(BudgetSummaryService budgetSummaryService) {
		this.budgetSummaryService = budgetSummaryService;
	}

    public UifExportControllerService getUifExportControllerService() {
        return uifExportControllerService;
    }

    public void setUifExportControllerService(UifExportControllerService uifExportControllerService) {
        this.uifExportControllerService = uifExportControllerService;
    }

    public KcCommonControllerService getKcCommonControllerService() {
        return kcCommonControllerService;
    }

    public void setKcCommonControllerService(KcCommonControllerService kcCommonControllerService) {
        this.kcCommonControllerService = kcCommonControllerService;
    }

    public TransactionalDocumentControllerService getTransactionalDocumentControllerService() {
        return transactionalDocumentControllerService;
    }

    public void setTransactionalDocumentControllerService(TransactionalDocumentControllerService transactionalDocumentControllerService) {
        this.transactionalDocumentControllerService = transactionalDocumentControllerService;
    }

    public CollectionControllerService getCollectionControllerService() {
        return collectionControllerService;
    }

    public void setCollectionControllerService(CollectionControllerService collectionControllerService) {
        this.collectionControllerService = collectionControllerService;
    }

    public FileControllerService getFileControllerService() {
        return fileControllerService;
    }

    public void setFileControllerService(FileControllerService fileControllerService) {
        this.fileControllerService = fileControllerService;
    }

    public ModelAndViewService getModelAndViewService() {
        return modelAndViewService;
    }

    public void setModelAndViewService(ModelAndViewService modelAndViewService) {
        this.modelAndViewService = modelAndViewService;
    }

    public NavigationControllerService getNavigationControllerService() {
        return navigationControllerService;
    }
    public void setNavigationControllerService(NavigationControllerService navigationControllerService) {
        this.navigationControllerService = navigationControllerService;
    }

    public QueryControllerService getQueryControllerService() {
        return queryControllerService;
    }

    public void setQueryControllerService(QueryControllerService queryControllerService) {
        this.queryControllerService = queryControllerService;
    }

    public RefreshControllerService getRefreshControllerService() {
        return refreshControllerService;
    }

    public void setRefreshControllerService(RefreshControllerService refreshControllerService) {
        this.refreshControllerService = refreshControllerService;
    }

}
