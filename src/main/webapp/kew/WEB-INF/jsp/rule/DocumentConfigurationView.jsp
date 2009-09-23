<%--
 Copyright 2009 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<c:set var="documentTypeAttributes" value="${DataDictionary.DocumentType.attributes}" scope="request" />
<c:set var="permissionAttributes" value="${DataDictionary.PermissionImpl.attributes}" scope="request" />
<c:set var="responsibilityAttributes" value="${DataDictionary.ResponsibilityImpl.attributes}" scope="request" />
<c:set var="documentType" value="${KualiForm.documentType}" scope="request" />
<c:set var="attributeLabels" value="${KualiForm.attributeLabels}" scope="request" />
<c:set var="kimAttributes" value="${DataDictionary.KimAttributes.attributes}" scope="request" />

<style type="text/css">
tr.overridden td {
    text-decoration: line-through;
    color: #909090;
}
tr.overridden td a {
    color: #909090;
}
</style>

<kul:page headerTitle="Document Configuration - ${documentType.name}" transactionalDocument="false"
	showDocumentInfo="false" htmlFormAction="DocumentConfigurationView" docTitle="Document Configuration - ${documentType.name}">
	<html-el:hidden property="documentTypeName" />
    <c:if test="${empty documentType}">
        Unknown Document Type - <c:out value="${KualiForm.documentTypeName}" />
    </c:if>
<%--
    TODO: remove hard coded KIM class Impl names - if anything, redirect to the action to allow the code the make the
    determination of how to implement
    TODO: some attributes need to 
--%>
    <c:if test="${!empty documentType}">
	<kul:tabTop
	  tabTitle="Document Information"
	  defaultOpen="true">
	  	<div class="tab-container" style="width:auto;">
          <table class="datatable" cellspacing="0" cellpadding="0" align="center" style="text-align: left; margin-left: auto; margin-right: auto;">
            <tbody>
              <tr>
                <kul:htmlAttributeHeaderCell scope="col" align="left" 
                	attributeEntry="${documentTypeAttributes.name}" />
                <td>
                	<kul:inquiry boClassName="org.kuali.rice.kew.doctype.bo.DocumentType" 
                	             keyValues="documentTypeId=${documentType.documentTypeId}" render="true">
    	                <kul:htmlControlAttribute attributeEntry="${documentTypeAttributes.name}"
    	                	property="documentType.name"
    	                	readOnly="true" />         
	                </kul:inquiry>
	                <c:if test="${KualiForm.canInitiateDocumentTypeDocument}">
	                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	<a href="<c:url value="${ConfigProperties.kr.url}/${Constants.MAINTENANCE_ACTION}">
                      <c:param name="methodToCall" value="edit" />
                      <c:param name="businessObjectClassName" value="org.kuali.rice.kew.doctype.bo.DocumentType"/>
                      <c:param name="documentTypeId" value="${documentType.documentTypeId}"/>
                      <c:param name="name" value="${documentType.name}"/>
                    </c:url>" target="_blank">Edit Document Type</a>       
                    </c:if>
                </td>
                <kul:htmlAttributeHeaderCell scope="col" align="left" 
                	attributeEntry="${documentTypeAttributes.unresolvedDocHandlerUrl}" />
                <td>
	                <kul:htmlControlAttribute attributeEntry="${documentTypeAttributes.unresolvedDocHandlerUrl}"
	                	property="documentType.docHandlerUrl"
	                	readOnly="true" />                
                </td>
			  </tr>
			  <tr>
                <kul:htmlAttributeHeaderCell scope="col" align="left" 
                	attributeEntry="${documentTypeAttributes.label}" />
                <td>
	                <kul:htmlControlAttribute attributeEntry="${documentTypeAttributes.label}"
	                	property="documentType.label"
	                	readOnly="true" />                
                </td>
                <kul:htmlAttributeHeaderCell scope="col" align="left" 
                	attributeEntry="${documentTypeAttributes.unresolvedHelpDefinitionUrl}" />
                <td>
	                <kul:htmlControlAttribute attributeEntry="${documentTypeAttributes.unresolvedHelpDefinitionUrl}"
	                	property="documentType.helpDefinitionUrl"
	                	readOnly="true" />                
                </td>
			  </tr>
			  <tr>
                <kul:htmlAttributeHeaderCell scope="col" align="left" 
                	attributeEntry="${documentTypeAttributes['parentDocType.name']}" />
                <td>
                	<c:if test="${!empty KualiForm.parentDocumentType.name}">
	                	<a href="?documentTypeName=${KualiForm.parentDocumentType.name}">
			                <kul:htmlControlAttribute attributeEntry="${documentTypeAttributes['parentDocType.name']}"
			                	property="parentDocumentType.name"
			                	readOnly="true" />                
	                		<c:if test="${fn:length( KualiForm.parentDocumentType.name ) <= 10}">
				                (<c:out value="${KualiForm.parentDocumentType.label}" />)                
	                		</c:if>
		                </a>
	                </c:if>
                	&nbsp;
                </td>
                <th align="left" scope="col">
                	Child Document Types
                </th>
                <td>
                	<c:forEach var="childDocType" items="${KualiForm.childDocumentTypes}" varStatus="status">
                		<a href="?documentTypeName=${childDocType.name}"><c:out value="${childDocType.name}" />
                		<c:if test="${fn:length( childDocType.name ) <= 10}">
                			(<c:out value="${childDocType.label}" />)
                		</c:if>
                		</a><br />
                	</c:forEach>
                	&nbsp;
                </td>
			  </tr>
			</tbody>
	 	  </table>
	 	</div>
   	    <kul:tab tabTitle="Permissions" defaultOpen="true">
			<div class="tab-container" style="width:auto;">
			 Gray lines that are stricken through represent inherited permissions that have been overridden by a more specific permission.
			 <%-- loop over the document types, going up the hierarchy --%>
                <c:forEach var="permDocTypeName" items="${KualiForm.docTypeHierarchyList}">
				  <c:choose>
  				    <c:when test="${permDocTypeName == documentType.name}">
				      <c:set var="tabLabel" value="Defined For This Document" />
				    </c:when>
				    <c:otherwise>
				      <c:set var="tabLabel" value="Inherited From: ${permDocTypeName}" />
				    </c:otherwise>
				  </c:choose>				  
	              <kul:subtab width="100%" subTabTitle="${tabLabel}" noShowHideButton="false">
					  <c:set var="permissions" value="${KualiForm.permissionsByDocumentType[permDocTypeName]}" scope="request" />
	              	  <c:import url="DocumentConfigurationViewPermissionList.jsp" />
    	          </kul:subtab>
	          </c:forEach>
			</div> 	  
 	    </kul:tab>

 	    <kul:tab tabTitle="Workflow / Responsibilities" defaultOpen="true" >
 	  		<div class="tab-container" style="width:auto;">
             Gray lines that are stricken through represent inherited responsibilities that have been overridden by a more specific responsibilities.
 	  			<kul:subtab width="100%" subTabTitle="Exception Routing" noShowHideButton="true">
				  <c:set var="responsibilities" value="${KualiForm.exceptionResponsibilities}" scope="request" />
	           	  <c:import url="DocumentConfigurationViewResponsibilityList.jsp" />
 	  			</kul:subtab>
 	  			<c:set var="routeNodeIndentLevel" value="0" />
				<c:forEach var="node" items="${KualiForm.routeNodes}">
				   <%-- ${node.nodeType} - ${node.routeNodeName}<br /> --%>
				   <c:if test="${fn:contains(node.nodeType,'SplitNode')}">
					<table class="datatable" cellpadding="0" cellspacing="0" align="center"
					       style="width: 100%; text-align: left; margin-left: auto; margin-right: auto; padding-left: ${routeNodeIndentLevel}em;">
                        <c:set var="routeNodeIndentLevel" value="${routeNodeIndentLevel + 5}" />
					    <tbody>
					        <tr>
				            <td class="tab-subhead">
				                <span class="left">Split Node: ${node.routeNodeName}</span>
				            </td>
    				        </tr>
    				        <tr><td style="padding-left: ${routeNodeIndentLevel}em;">
                   </c:if>
				   <c:if test="${node.routeNodeName != 'AdHoc' && !fn:contains(node.nodeType,'NoOpNode') && !fn:contains(node.nodeType,'SplitNode') && !fn:contains(node.nodeType,'JoinNode')}">
					  <c:set var="responsibilities" value="${KualiForm.responsibilityMap[node.routeNodeName]}" scope="request" />
		              <kul:subtab width="100%" subTabTitle="Route Node: ${node.routeNodeName}" noShowHideButton="true">
		              	  <c:import url="DocumentConfigurationViewResponsibilityList.jsp" />
		              </kul:subtab>	              
		           </c:if>
                   <c:if test="${fn:contains(node.nodeType,'JoinNode')}">
                            </td></tr>
                        </tbody>
                    </table>
                   <c:set var="routeNodeIndentLevel" value="${routeNodeIndentLevel - 5}" />
                   </c:if>
	            </c:forEach>
 	  		</div>
 	    </kul:tab>
 	  </kul:tabTop>
 	  <kul:panelFooter />
  </c:if>
	
</kul:page>
