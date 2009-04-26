/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.workflow;

import static org.kuali.kra.infrastructure.Constants.CO_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.Constants.KEY_PERSON_ROLE;
import static org.kuali.kra.infrastructure.Constants.PRINCIPAL_INVESTIGATOR_ROLE;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.kew.engine.RouteContext;
import org.kuali.rice.kew.identity.Id;
import org.kuali.rice.kew.routeheader.DocumentContent;
import org.kuali.rice.kew.rule.AbstractRoleAttribute;
import org.kuali.rice.kew.rule.ResolvedQualifiedRole;
import org.kuali.rice.kew.rule.Role;
import org.kuali.rice.kew.user.AuthenticationUserId;
import org.kuali.rice.kew.user.UserId;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ProposalPersonRoleAttribute extends AbstractRoleAttribute 
{

    private static final long serialVersionUID = 1L;

    private static final Role PRINCIPAL_INVESTIGATOR = new Role(ProposalPersonRoleAttribute.class, "PRINCIPALINVESTIGATOR", "PrincipalInvestigator");
    private static final Role CO_INVESTIGATOR = new Role(ProposalPersonRoleAttribute.class, "COINVESTIGATOR", "CoInvestigator");
    private static final Role KEYPERSON = new Role(ProposalPersonRoleAttribute.class, "KEYPERSON", "Keyperson");
    private static final String PRINCIPAL_INVESTIGATOR_ROLE_KEY = "PRINCIPALINVESTIGATOR";
    private static final String COINVESTIGATOR_ROLE_KEY = "COINVESTIGATOR";
    private static final String KEYPERSON_ROLE_KEY = "KEYPERSON";



    public List<Role> getRoleNames() {
        List<Role> roles = new ArrayList<Role>();
        roles.add(PRINCIPAL_INVESTIGATOR);
        roles.add(CO_INVESTIGATOR);
        roles.add(KEYPERSON);
        return roles;
    }


    public ResolvedQualifiedRole resolveQualifiedRole(RouteContext routeContext, String roleName, String qualifiedRole) {


        ResolvedQualifiedRole qualRole = new ResolvedQualifiedRole();
        List<Id> members = new ArrayList<Id>();
        UserId roleUserId = null;
        if (StringUtils.equals(PRINCIPAL_INVESTIGATOR.getBaseName(), qualifiedRole)) {
            List<String> proposalinvestigator=getPrincipalInvestigator(routeContext);
            for (Iterator<String> pis = proposalinvestigator.iterator(); pis.hasNext();) {
                String  id = pis.next();
                roleUserId = new AuthenticationUserId(id);
                members.add(roleUserId);
           }
            qualRole.setRecipients(members);
            qualRole.setAnnotation("ProposalInvestigator Approval");
            qualRole.setQualifiedRoleLabel(PRINCIPAL_INVESTIGATOR.getLabel());
        } else if (StringUtils.equals(CO_INVESTIGATOR.getBaseName(), qualifiedRole)) {
            List<String> coinvestigators=getCoInvestigators(routeContext);
            for (Iterator<String> coi = coinvestigators.iterator(); coi.hasNext();) {
                String  id = coi.next();
                roleUserId = new AuthenticationUserId(id);
                members.add(roleUserId);
            }
            qualRole.setRecipients(members);
            qualRole.setAnnotation("CO-Investigator Approval");
            qualRole.setQualifiedRoleLabel(CO_INVESTIGATOR.getLabel());
        } else if(StringUtils.equals(KEYPERSON.getBaseName(), qualifiedRole)){
            List<String> keypersons=getKeyPersons(routeContext);
            for (Iterator<String> kp = keypersons.iterator(); kp.hasNext();) {
                String  id = kp.next();
                roleUserId = new AuthenticationUserId(id);
                members.add(roleUserId);
            }
            qualRole.setRecipients(members);
            qualRole.setAnnotation("Keyperson Approval");
            qualRole.setQualifiedRoleLabel(KEYPERSON.getLabel());
        }
        return qualRole;
    }
    public List<String> getQualifiedRoleNames(String roleName, DocumentContent docContent) {
        List<String> qualifiedRoleNames = new ArrayList<String>();

        if ( PRINCIPAL_INVESTIGATOR_ROLE_KEY.equals(roleName)) {

            qualifiedRoleNames.add(PRINCIPAL_INVESTIGATOR_ROLE_KEY);

        }
        else if(COINVESTIGATOR_ROLE_KEY.equals(roleName))
        {
            qualifiedRoleNames.add(COINVESTIGATOR_ROLE_KEY);
        }
        else if(KEYPERSON_ROLE_KEY.equals(roleName)){

            qualifiedRoleNames.add(KEYPERSON_ROLE_KEY);
        }
        return  qualifiedRoleNames;
    }

    public List<String> getCoInvestigators(RouteContext routeContext) {
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
                        if(StringUtils.isNotBlank(getChildElementTextValue(proposalpersonNode,"userName"))){
                            coinvestigators.add(getChildElementTextValue(proposalpersonNode, "userName"));
                        }
                    }
                }
            }
        }
        return coinvestigators;
    }
    public List<String> getPrincipalInvestigator(RouteContext routeContext) {
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
                        if(StringUtils.isNotBlank(getChildElementTextValue(proposalpersonNode,"userName"))){
                            Principalinvestigator.add(getChildElementTextValue(proposalpersonNode, "userName"));
                        }
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
                        if(StringUtils.isNotBlank(getChildElementTextValue(proposalpersonNode,"userName"))){
                            Investigators.add(getChildElementTextValue(proposalpersonNode, "userName"));
                        }
                    }
                }
            }
        }
        return Investigators;
    }
    public List<String> getKeyPersons(RouteContext routeContext) {
        List<String> KeyPersons = new ArrayList<String>();
        Element rootElement=routeContext.getDocumentContent().getApplicationContent();
        if(rootElement!=null){
            Element documentElement = getChildElement(rootElement, "document");
            Element proposalPersonElement=getChildElement(documentElement, "proposalPersons");
            NodeList proposalperonList=proposalPersonElement.getChildNodes();
            for (int i = 0; i < proposalperonList.getLength(); i++) {
                Node proposalpersonNode =proposalperonList.item(i);
                if(getChildElementTextValue(proposalpersonNode,"proposalPersonRoleId")!=null){
                    if(getChildElementTextValue(proposalpersonNode, "proposalPersonRoleId").equals(KEY_PERSON_ROLE) && getChildElementTextValue(proposalpersonNode,"optInCertificationStatus").equals("Y")){
                        if(StringUtils.isNotBlank(getChildElementTextValue(proposalpersonNode,"userName"))){
                            KeyPersons.add(getChildElementTextValue(proposalpersonNode, "userName"));
                        }
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
                        if(StringUtils.isNotBlank(getChildElementTextValue(proposalpersonNode,"userName"))){
                            ProposalPersons.add(getChildElementTextValue(proposalpersonNode, "userName"));
                        }
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
