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

import static org.kuali.kra.infrastructure.Constants.KEY_PERSON_ROLE;
import static org.kuali.kra.infrastructure.Constants.PRINCIPAL_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.Constants.CO_INVESTIGATOR_ROLE;

import java.util.*;

import org.apache.commons.lang.StringUtils;


import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.iu.uis.eden.Id;
import edu.iu.uis.eden.engine.RouteContext;
import edu.iu.uis.eden.exception.EdenUserNotFoundException;

import edu.iu.uis.eden.routeheader.DocumentContent;
import edu.iu.uis.eden.routetemplate.AbstractRoleAttribute;
import edu.iu.uis.eden.routetemplate.ResolvedQualifiedRole;
import edu.iu.uis.eden.routetemplate.Role;
import edu.iu.uis.eden.user.AuthenticationUserId;
import edu.iu.uis.eden.user.UserId;

public class ProposalPersonRoleAttribute extends AbstractRoleAttribute 
{

    private static final long serialVersionUID = 1L;

    private static final Role PROPOSAL_INVESTIGATOR = new Role(ProposalPersonRoleAttribute.class, "PROPOSALINVESTIGATOR", "ProposalInvestigator");
    private static final Role CO_INVESTIGATOR = new Role(ProposalPersonRoleAttribute.class, "COINVESTIGATOR", "CoInvestigator");
    private static final String PROPOSAL_INVESTIGATOR_ROLE_KEY = "PROPOSALINVESTIGATOR";
    private static final String COINVESTIGATOR_ROLE_KEY = "COINVESTIGATOR";



    public List<Role> getRoleNames() {
        List<Role> roles = new ArrayList<Role>();
        roles.add(PROPOSAL_INVESTIGATOR);
        roles.add(CO_INVESTIGATOR);
        return roles;
    }


    public ResolvedQualifiedRole resolveQualifiedRole(RouteContext routeContext, String roleName, String qualifiedRole)
    throws EdenUserNotFoundException {


        ResolvedQualifiedRole qualRole = new ResolvedQualifiedRole();
        List<Id> members = new ArrayList<Id>();
        UserId roleUserId = null;
        if (StringUtils.equals(PROPOSAL_INVESTIGATOR.getBaseName(), qualifiedRole)) {
            List<String> proposalinvestigator=getProposalInvestigator(routeContext);
            for (Iterator<String> pis = proposalinvestigator.iterator(); pis.hasNext();) {
                String  id = pis.next();
                roleUserId = new AuthenticationUserId(id);
                members.add(roleUserId);
            }  
            qualRole.setRecipients(members);
            qualRole.setAnnotation("ProposalInvestigator Approval");
            qualRole.setQualifiedRoleLabel(PROPOSAL_INVESTIGATOR.getLabel());

        } else if (StringUtils.equals(CO_INVESTIGATOR.getBaseName(), qualifiedRole)) {

            List<String> coinvestigators=getProposalCoInvestigators(routeContext);
            for (Iterator<String> coi = coinvestigators.iterator(); coi.hasNext();) {
                String  id = coi.next();
                roleUserId = new AuthenticationUserId(id);
                members.add(roleUserId);
            }
            qualRole.setRecipients(members);
            qualRole.setAnnotation("CO-Investigator Approval");
            qualRole.setQualifiedRoleLabel(CO_INVESTIGATOR.getLabel());
        } 



        return qualRole;

    }

    public List<String> getQualifiedRoleNames(String roleName, DocumentContent docContent) throws EdenUserNotFoundException {
        List<String> qualifiedRoleNames = new ArrayList<String>();

        if ( PROPOSAL_INVESTIGATOR_ROLE_KEY.equals(roleName)) {

            qualifiedRoleNames.add(PROPOSAL_INVESTIGATOR_ROLE_KEY);

        }
        else if(COINVESTIGATOR_ROLE_KEY.equals(roleName))
        {
            qualifiedRoleNames.add(COINVESTIGATOR_ROLE_KEY);
        }
        return  qualifiedRoleNames;
    }

    public List<String> getProposalCoInvestigators(RouteContext routeContext) {
        // TODO Auto-generated method stub
        List<String> coinvestigators = new ArrayList<String>();
        Element rootElement=routeContext.getDocumentContent().getApplicationContent(); 
        if(rootElement!=null){
            Element documentElement = getChildElement(rootElement, "document");
            Element proposalPersonElement=getChildElement(documentElement, "proposalPersons");
            NodeList proposalperonList=proposalPersonElement.getChildNodes();
            for (int i = 0; i < proposalperonList.getLength(); i++) {
                Node proposalpersonNode =proposalperonList.item(i);
                if(getChildElementTextValue(proposalpersonNode,"proposalPersonRoleId")!=null){
                    if(getChildElementTextValue(proposalpersonNode,"proposalPersonRoleId").equals(CO_INVESTIGATOR_ROLE)){
                        coinvestigators.add(getChildElementTextValue(proposalpersonNode, "userName"));
                    }
                }
            }
        }
        return coinvestigators;
    }
    public List<String> getProposalInvestigator(RouteContext routeContext) {
        // TODO Auto-generated method stub
        List<String> Principalinvestigator = new ArrayList<String>();
        Element rootElement=routeContext.getDocumentContent().getApplicationContent();
        if(rootElement!=null){
            Element documentElement =getChildElement(rootElement, "document");
            Element proposalPersonElement=getChildElement(documentElement, "proposalPersons");
            NodeList proposalperonList=proposalPersonElement.getChildNodes();
            for (int i = 0; i < proposalperonList.getLength(); i++) {
                Node proposalpersonNode =proposalperonList.item(i);
                if(getChildElementTextValue(proposalpersonNode,"proposalPersonRoleId")!=null){
                    if(getChildElementTextValue(proposalpersonNode,"proposalPersonRoleId").equals(PRINCIPAL_INVESTIGATOR_ROLE)){
                        Principalinvestigator.add(getChildElementTextValue(proposalpersonNode, "userName"));
                    }
                }
            }
        }
        return Principalinvestigator;
    } 
    public List<String> getProposalInvestigators(RouteContext routeContext) {

        List<String> Investigators = new ArrayList<String>();
        Element rootElement=routeContext.getDocumentContent().getApplicationContent();
        if(rootElement!=null){
            Element documentElement = getChildElement(rootElement, "document");
            Element proposalPersonElement=getChildElement(documentElement, "proposalPersons");
            NodeList proposalperonList=proposalPersonElement.getChildNodes();
            for (int i = 0; i < proposalperonList.getLength(); i++) {
                Node proposalpersonNode =proposalperonList.item(i);
                if(getChildElementTextValue(proposalpersonNode,"proposalPersonRoleId")!=null){
                    if((getChildElementTextValue(proposalpersonNode, "proposalPersonRoleId").equals(PRINCIPAL_INVESTIGATOR_ROLE)) || (getChildElementTextValue(proposalpersonNode, "proposalPersonRoleId").equals(CO_INVESTIGATOR_ROLE))){
                        Investigators.add(getChildElementTextValue(proposalpersonNode, "userName"));
                    }
                }
            }
        }
        return Investigators;
    }

    public List<String> getProposalKeyPersons(RouteContext routeContext) {
        List<String> KeyPersons = new ArrayList<String>();
        Element rootElement=routeContext.getDocumentContent().getApplicationContent();
        if(rootElement!=null){
            Element documentElement = getChildElement(rootElement, "document");
            Element proposalPersonElement=getChildElement(documentElement, "proposalPersons");
            NodeList proposalperonList=proposalPersonElement.getChildNodes();
            for (int i = 0; i < proposalperonList.getLength(); i++) {
                Node proposalpersonNode =proposalperonList.item(i);
                if(getChildElementTextValue(proposalpersonNode,"proposalPersonRoleId")!=null){
                    if(getChildElementTextValue(proposalpersonNode, "proposalPersonRoleId").equals(KEY_PERSON_ROLE)){
                        KeyPersons.add(getChildElementTextValue(proposalpersonNode, "userName"));
                    }
                }

            }
        }
        return KeyPersons;

    }

    public List<String> getProposalPersons(RouteContext routeContext) {
        List<String> ProposalPersons = new ArrayList<String>();
        Element rootElement=routeContext.getDocumentContent().getApplicationContent();
        if(rootElement!=null){
            Element documentElement = getChildElement(rootElement, "document");
            Element proposalPersonElement=getChildElement(documentElement, "proposalPersons");
            NodeList proposalperonList=proposalPersonElement.getChildNodes();
            for (int i = 0; i < proposalperonList.getLength(); i++) {
                Node proposalpersonNode =proposalperonList.item(i);
                if(getChildElementTextValue(proposalpersonNode,"proposalPersonRoleId")!=null){
                    if((getChildElementTextValue(proposalpersonNode, "proposalPersonRoleId").equals(PRINCIPAL_INVESTIGATOR_ROLE)) || (getChildElementTextValue(proposalpersonNode, "proposalPersonRoleId").equals(CO_INVESTIGATOR_ROLE)) || (getChildElementTextValue(proposalpersonNode, "proposalPersonRoleId").equals(KEY_PERSON_ROLE))){
                        ProposalPersons.add(getChildElementTextValue(proposalpersonNode, "userName"));
                    }
                }
            }
        }
        return ProposalPersons;

    }


    /**
     * Returns a node child with the specified tag name of the specified parent node,
     * or null if no such child node is found.
     * @param parent the parent node
     * @param name the name of the child node
     * @return child node if found, null otherwise
     */
    public static Element getChildElement(Node parent, String name) {
        NodeList childList = parent.getChildNodes();
        for (int i = 0; i < childList.getLength(); i++) {
            Node node = childList.item(i);
            // we must test against NodeName, not just LocalName
            // LocalName seems to be null - I am guessing this is because
            // the DocumentBuilderFactory is not "namespace aware"
            // although I would have expected LocalName to default to
            // NodeName
            if (node.getNodeType() == Node.ELEMENT_NODE
                    && (name.equals(node.getLocalName())
                            || name.equals(node.getNodeName()))) {
                return (Element) node;
            }
        }
        return null;
    }



    /**
     * Returns the text value of a child element with the given name, of the given parent element,
     * or null if the child does not exist or does not have a child text node
     * @param parent parent element
     * @param name name of child element
     * @return the text value of a child element with the given name, of the given parent element,
     * or null if the child does not exist or does not have a child text node
     */
    public static String getChildElementTextValue(Node parent, String name) {
        Element child = getChildElement(parent, name);
        if (child == null) {
            return null;
        }
        Node textNode = child.getFirstChild();
        if (textNode == null) {
            return null;
        }
        return textNode.getNodeValue();
    }





}
