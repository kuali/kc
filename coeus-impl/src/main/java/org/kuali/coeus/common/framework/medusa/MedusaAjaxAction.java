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
package org.kuali.coeus.common.framework.medusa;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MedusaAjaxAction extends KualiDocumentActionBase {

    private MedusaService medusaService;
    protected  MedusaService getMedusaService (){
        if (medusaService == null)
            medusaService = KcServiceLocator.getService(MedusaService.class);
        return medusaService;
    }
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        MedusaForm medusaForm = (MedusaForm)form;
        medusaForm.getMedusaBean().setCurrentNode(getMedusaService().getMedusaNode(medusaForm.getMedusaBean().getModuleName(), medusaForm.getMedusaBean().getModuleIdentifier()));
        
        return super.execute(mapping, medusaForm, request, response);
    }
    
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        MedusaForm medusaForm = (MedusaForm)form;
        medusaForm.setCommand("displayDocSearchView");

        MedusaNode node = getMedusaService().getMedusaNode(medusaForm.getMedusaBean().getModuleName(), medusaForm.getMedusaBean().getModuleIdentifier());
        
        if (StringUtils.equals(node.getType(), "IP")) {
            InstitutionalProposal proposal = (InstitutionalProposal)node.getBo();
            medusaForm.setDocId(proposal.getInstitutionalProposalDocument().getDocumentNumber());
        } else if (StringUtils.equals(node.getType(), "award")) {
            Award award = (Award)node.getBo();
            medusaForm.setDocId(award.getAwardDocument().getDocumentNumber());
        } else if (StringUtils.equals(node.getType(), "DP")) {
            DevelopmentProposal devProposal = (DevelopmentProposal)node.getBo();
            medusaForm.setDocId(devProposal.getProposalDocument().getDocumentNumber());
        }else if (StringUtils.equals(node.getType(), "subaward")) {
            SubAward subAward = (SubAward)node.getBo();
            medusaForm.setDocId(subAward.getSubAwardDocument().getDocumentNumber());
        }
            
        return super.docHandler(mapping, form, request, response);
    }
}
