/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.workflow;
import java.util.*;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.service.ProposalPersonRoleService;
import edu.iu.uis.eden.Id;
import edu.iu.uis.eden.engine.RouteContext;
import edu.iu.uis.eden.exception.EdenUserNotFoundException;
import edu.iu.uis.eden.exception.WorkflowRuntimeException;
import edu.iu.uis.eden.routeheader.DocumentContent;
import edu.iu.uis.eden.routetemplate.AbstractRoleAttribute;
import edu.iu.uis.eden.routetemplate.ResolvedQualifiedRole;
import edu.iu.uis.eden.routetemplate.Role;
import edu.iu.uis.eden.user.AuthenticationUserId;
import edu.iu.uis.eden.user.UserId;

public class ProposalPersonRoleAttribute extends AbstractRoleAttribute 
{

    private static final long serialVersionUID = 1L;
    private static final Role PROPOSAL_INVESTIGATOR_ROLE = new Role(ProposalPersonRoleAttribute.class, "PROPOSALINVESTIGATOR", "ProposalInvestigator");
    private static final Role CO_INVESTIGATOR_ROLE = new Role(ProposalPersonRoleAttribute.class, "COINVESTIGATOR", "CoInvestigator");
    private static final Role INVESTIGATORS = new Role(ProposalPersonRoleAttribute.class, "INVESTIGATORS", "Investigators");
    private static final Role KEY_PERSON_ROLE = new Role(ProposalPersonRoleAttribute.class, "KEYPERSON", "KeyPerson");
    private static final Role PROPOSAL_PERSON_ROLE = new Role(ProposalPersonRoleAttribute.class, "PROPOSALPERSONS", "ProposalPersons");
    private static final List<Role> ROLES;
    static {
        List<Role> tmp = new ArrayList<Role>(1);
        tmp.add(PROPOSAL_INVESTIGATOR_ROLE);
        tmp.add(CO_INVESTIGATOR_ROLE);
        tmp.add(INVESTIGATORS);
        tmp.add(KEY_PERSON_ROLE);
        tmp.add(PROPOSAL_PERSON_ROLE);
        ROLES= Collections.unmodifiableList(tmp);
    }

    public ResolvedQualifiedRole resolveQualifiedRole(RouteContext routeContext, String roleName, String qualifiedRole)
    throws EdenUserNotFoundException {


        ResolvedQualifiedRole qualRole = new ResolvedQualifiedRole();
        List<Id> members = new ArrayList<Id>();
        UserId roleUserId = null;
        ProposalPersonRoleService proposalpersonroleservice = KraServiceLocator.getService(ProposalPersonRoleService.class);
        String  documentid= routeContext.getDocument().getDocumentTypeId().toString();
        if (StringUtils.equals(PROPOSAL_INVESTIGATOR_ROLE.getBaseName(), qualifiedRole)) {
            List<String> proposalinvestigator=proposalpersonroleservice.getProposalInvestigator(documentid);
            for (Iterator<String> pis = proposalinvestigator.iterator(); pis.hasNext();) {
                String  id = pis.next();
                roleUserId = new AuthenticationUserId(id);
                members.add(roleUserId);
            }  
        } else if (StringUtils.equals(CO_INVESTIGATOR_ROLE.getBaseName(), qualifiedRole)) {

            List<String> coinvestigators=proposalpersonroleservice.getProposalCoInvestigators(documentid);
            for (Iterator<String> coi = coinvestigators.iterator(); coi.hasNext();) {
                String  id = coi.next();
                roleUserId = new AuthenticationUserId(id);
                members.add(roleUserId);
            }
        } else if (StringUtils.equals(INVESTIGATORS.getBaseName(), qualifiedRole)) {
            List<String> investigators=proposalpersonroleservice.getProposalInvestigators(documentid);
            for (Iterator<String> ins = investigators.iterator(); ins.hasNext();) {
                String  id = ins.next();
                roleUserId = new AuthenticationUserId(id);
                members.add(roleUserId);
            }

        }else if (StringUtils.equals(KEY_PERSON_ROLE.getBaseName(), qualifiedRole)) {
            List<String> keypersons=proposalpersonroleservice.getProposalKeyPersons(documentid);
            for (Iterator<String> kps = keypersons.iterator(); kps.hasNext();) {
                String  id = kps.next();
                roleUserId = new AuthenticationUserId(id);
                members.add(roleUserId);
            }
        }else if (StringUtils.equals(PROPOSAL_PERSON_ROLE.getBaseName(), qualifiedRole)) {
            List<String> proposalpersons=proposalpersonroleservice.getProposalPersons(documentid);
            for (Iterator<String> pps = proposalpersons.iterator(); pps.hasNext();) {
                String  id = pps.next();
                roleUserId = new AuthenticationUserId(id);
                members.add(roleUserId);
            }
        } 
        else {
            // throw an exception if you get an unrecognized roleName
            throw new WorkflowRuntimeException("unable to process unknown role '" + roleName + "'");
        }

        qualRole.setRecipients(members);
        return qualRole;

    }

    public List<String> getQualifiedRoleNames(String roleName, DocumentContent documentContent) throws EdenUserNotFoundException {
        List<String> qualifiedRoleNames = new ArrayList<String>(); 
        for (Iterator<Role> person_role = ROLES.iterator(); person_role.hasNext();) {
            Role rolenames = person_role.next();
            if(rolenames!= null){

                qualifiedRoleNames.add(rolenames.getBaseName());
            }
        }
        return qualifiedRoleNames;
    }

    public List<Role> getRoleNames() {
        // TODO Auto-generated method stub
        return ROLES;
    }

}
