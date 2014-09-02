package org.kuali.coeus.propdev.impl.core;

import java.util.Collection;

import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.lookup.LookupableImpl;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.uif.element.Link;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("propDevLookupableHelperService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PropDevLookupableHelperServiceImpl extends LookupableImpl implements PropDevLookupableHelperService {
	
	private static final long serialVersionUID = 1L;
	
    @Autowired
    @Qualifier("kcAuthorizationService") 
    private KcAuthorizationService kcAuthorizationService;
    
    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;
    
    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;
	
	/**
	 * This method determines Action URLs to be displayed for current search result line
	 * Default implementation is to return true
	 * @param Object dataObject current line item from the search result collection
	 */
    @Override
	public boolean showActionUrls(Collection<?> lookupResults) {
		
		boolean canViewProposal = false;
		Object dataObject = null;
		if(lookupResults != null && lookupResults.size() > 0) {
			dataObject = lookupResults.iterator().next();
		}
		if(dataObject != null && dataObject instanceof DevelopmentProposal) {
			DevelopmentProposal developmentProposal = (DevelopmentProposal)dataObject;
			canViewProposal = getKcAuthorizationService().hasPermission(getGlobalVariableService().getUserSession().getPrincipalId(),
					developmentProposal.getDocument(), PermissionConstants.VIEW_PROPOSAL);
		}
	    return canViewProposal;
	}

    /**
     * Invoked to build view action URL for a result row.
     * @param actionLink link that will be used to return the view action URL
     * @param model lookup form containing the data
     * @param title will be assigned as the href text and title
     */
	@Override
	public void buildPropDevViewActionLink(Link actionLink, Object model, String title) {
		actionLink.setTitle(title);
		actionLink.setLinkText(title);
		actionLink.setHref(getConfigurationService().getPropertyValueAsString(KRADConstants.WORKFLOW_URL_KEY)
                + KRADConstants.DOCHANDLER_DO_URL
                + actionLink.getHref()
                + KRADConstants.DOCHANDLER_URL_CHUNK+"&viewDocument=true");
		
	}
	
    /**
     * Invoked to build edit action URL for a result row.
     * @param actionLink link that will be used to return the edit action URL
     * @param model lookup form containing the data
     * @param title will be assigned as the href text and title
     * @throws WorkflowException 
     */
	@Override
	public void buildPropDevEditActionLink(Link actionLink, Object model,String title) throws WorkflowException {
		
		boolean canModifyProposal = getKcAuthorizationService().hasPermission(getGlobalVariableService().getUserSession().getPrincipalId(), 
				(ProposalDevelopmentDocument)(getDocumentService().getByDocumentHeaderId(actionLink.getHref())), 
				PermissionConstants.MODIFY_PROPOSAL);
		if(canModifyProposal) {
			actionLink.setTitle(title);
			actionLink.setLinkText(title);
			actionLink.setHref(getConfigurationService().getPropertyValueAsString(KRADConstants.WORKFLOW_URL_KEY)
	                + KRADConstants.DOCHANDLER_DO_URL
	                + actionLink.getHref()
	                + KRADConstants.DOCHANDLER_URL_CHUNK);
		}
	}

	public KcAuthorizationService getKcAuthorizationService() {
		return kcAuthorizationService;
	}

	public void setKcAuthorizationService(KcAuthorizationService kcAuthorizationService) {
		this.kcAuthorizationService = kcAuthorizationService;
	}


	public GlobalVariableService getGlobalVariableService() {
		return globalVariableService;
	}

	public void setGlobalVariableService(GlobalVariableService globalVariableService) {
		this.globalVariableService = globalVariableService;
	}


	public DocumentService getDocumentService() {
		return documentService;
	}

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}

}
