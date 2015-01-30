<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ page import="java.util.HashMap" %>

<link rel="stylesheet" href="css/jquery/questionnaire.css" type="text/css" />
<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
<link rel="stylesheet" href="css/jquery/kuali-stylesheet.css" type="text/css" />
<link rel="stylesheet" href="css/jquery/jquery.treeview.css" type="text/css" />
<script type="text/javascript" src="scripts/jquery/jquery.treeview.js"></script>
<script type="text/javascript" src="scripts/jquery/CalendarPopup.js"></script>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="proposalPersonAttributes" value="${DataDictionary.ProposalPerson.attributes}" />
<c:set var="readOnly" value="${not KualiForm.editingMode['modifyProposal']}" scope="request" />
<c:set var="isHierarchyParent" value="${KualiForm.document.developmentProposalList[0].hierarchyStatus == KualiForm.hierarchyParentStatus}" />

<jsp:useBean id="newMap" class="java.util.HashMap" scope="request" />

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="proposalDevelopmentKeyPersonnel"
	documentTypeName="ProposalDevelopmentDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="keyPersonnel">
  	
  	<div id="workarea">
  	<style>
  		#addPersonDiv table {
  			border-right: 0;
  			display: table;
  			width: auto;
  		}
  		#addPersonDiv td {
  			padding: 0;
  			border-left: 0;
  			border-bottom: 0;
  		}
  		#addPersonDiv td.grid {
  			border-bottom: 1px solid #999999;
  			border-left: 1px solid #999999;
  			padding: 2px;
  		}
  	</style>
  	<div align="right"><kul:help documentTypeName="ProposalDevelopmentDocument" pageName="Key Personnel" /></div>

<c:set var="viewOnly" value="${not KualiForm.editingMode['modifyProposal']}" />
<kra:section permission="modifyProposal">
<c:if test="${not isHierarchyParent}">
	<div id="addPersonDiv">
    <kra:uncollapsable tabTitle="Add Key Person" tabErrorKey="newProposalPerson*" auditCluster="keyPersonnelAuditErrors" tabAuditKey="newProposalPerson*" styleClass="addline">
          <div align="center">
            <table  cellpadding="0" cellspacing="0" class="grid" summary="" style="border-right: 1px solid #999999;">
              <tr>
                <th class="grid"><div align="right">*Person:</div></th>
<c:choose>                  
  <c:when test="${empty KualiForm.newPersonId && empty KualiForm.newRolodexId}">
                <td nowrap class="grid">
                <label> Employee Search</label>
                  <label><kul:lookup boClassName="org.kuali.coeus.common.framework.person.KcPerson" 
                                fieldConversions="personId:newPersonId" /></label><br>
                  <label>Non-employee Search</label> 
                  <label><kul:lookup boClassName="org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex"
                                fieldConversions="rolodexId:newRolodexId" /></label></td>
  </c:when>
  <c:otherwise>
                  <td nowrap class="grid"><label><kul:htmlControlAttribute property="newProposalPerson.fullName" attributeEntry="${proposalPersonAttributes.fullName}" readOnly="true"/></label><br/></td>
  </c:otherwise>
</c:choose>
                </td>
                <th class="grid"><div align="right">*Proposal Role:</div></th>
                <td class="grid" >
<c:set var="roleIdAttribute" value="${proposalPersonAttributes.proposalPersonRoleId}" />
<c:if test="${KualiForm.document.developmentProposalList[0].sponsorNihMultiplePi}">
  <c:set var="roleIdAttribute" value="${proposalPersonAttributes.nonNihProposalPersonRoleId}" />
</c:if>
                  <kul:htmlControlAttribute property="newProposalPerson.proposalPersonRoleId" attributeEntry="${proposalPersonAttributes.proposalPersonRoleId}" />
                </td>
                <th class="grid" id="projectRoleHeader"><div align="right">*Key Person Role<div id="projectRoleRequiredDesc">(Only for Key Persons)</div>:</div></th>
                <td class="grid" id="projectRoleField">
                  <kul:htmlControlAttribute property="newProposalPerson.projectRole" attributeEntry="${proposalPersonAttributes.projectRole}"/>
                </td>
                <c:if test="${KualiForm.document.developmentProposalList[0].sponsorNihMultiplePi}">
                <th class="grid" id="multiPiHeader"><div align="right">Multiple PI:</div></th>
                <td class="grid" id="multiPiField">
                  <kul:htmlControlAttribute property="newProposalPerson.multiplePi" attributeEntry="${proposalPersonAttributes.multiplePi}"/>
                </td>
                </c:if>
                <script type="text/javascript">
                  function proposalRoleChange(formItem) {
                      if ( jq(formItem).val() == 'KP' ) {
                          jq('#projectRoleHeader').show();
                          jq('#projectRoleField').show();
                      } else {
                          jq('#projectRoleHeader').hide();
                          jq('#projectRoleField').hide();
                      }
                      if ( jq(formItem).val() == 'COI' ) {
                          jq('#multiPiHeader').show();
                          jq('#multiPiField').show();
                      } else {
                          jq('#multiPiHeader').hide();
                          jq('#multiPiField').hide();
                      }
                  }
                  jq(document).ready(function() {
                      proposalRoleChange(jq(jq_escape('newProposalPerson.proposalPersonRoleId')));
                      jq('#projectRoleRequiredDesc').hide();
                      jq(jq_escape('newProposalPerson.proposalPersonRoleId')).change(function() {
                          proposalRoleChange(this);
                      });
                  });
                </script>
              </tr>
            </table>
           
            <br>
            <html:image property="methodToCall.clearProposalPerson" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-clear1.gif" title="Clear Fields" alt="Clear Fields" styleClass="tinybutton"/>
            <html:image property="methodToCall.insertProposalPerson" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-addperson.gif" title="Add Proposal Person" alt="Add Proposal Person" styleClass="tinybutton addButton"/>
            <c:choose>
            <c:when test="${KualiForm.document.developmentProposalList[0].sponsorNihMultiplePi}">
            <br>
             <strong>PI/Contact is a required Proposal Role prior to submission. Only one PI/Contact is allowed. 
            For single PI submissions, please designate the lead investigator as PI/Contact &amp; other senior personnel as Key Persons. 
            For multiple PI submissions, please designate one PI/Contact. 
            Add additional lead investigators as co-Investigators and check the Multiple PI box. Add other senior personnel as Key Persons.<br>
            </c:when>
            <c:otherwise>
            <br>
            <strong>Principal Investigator is a required field prior to submission. Only one PI is allowed.</strong>
            </c:otherwise>
            </c:choose>
          </div>
    </kra:uncollapsable>
</c:if>
</div>
</kra:section>

    <br/>

    <kra-pd:keyPersons/>

  <c:if test="${not empty viewOnly && ! viewOnly and fn:length(KualiForm.document.developmentProposalList[0].proposalPersons) > 0 and !isHierarchyParent }">
  	<c:set var="extraButtonSource" value="${ConfigProperties.kra.externalizable.images.url}buttonsmall_deletesel.gif"/>
  	<c:set var="extraButtonProperty" value="methodToCall.deletePerson"/>
  	<c:set var="extraButtonAlt" value="Delete a Key Person"/>
  </c:if>  
  
  	<p>
	<kul:documentControls 
		transactionalDocument="false"
		suppressRoutingControls="true"
		suppressCancelButton="true"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		viewOnly="${viewOnly}"
		/>
	</p>

<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;
</SCRIPT>
<script language="javascript" src="scripts/kuali_application.js"></script>
<script type="text/javascript">var $j = jQuery.noConflict();</script>
<script language="javascript" src="dwr/interface/UnitService.js"></script>
<script type="text/javascript" src="scripts/questionnaireAnswer.js"></script>

</div>
</kul:documentPage>
