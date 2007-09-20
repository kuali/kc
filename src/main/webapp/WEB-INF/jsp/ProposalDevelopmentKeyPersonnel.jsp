<%--
 Copyright 2005-2006 The Kuali Foundation.

 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.opensource.org/licenses/ecl1.php

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="proposalPersonAttributes" value="${DataDictionary.ProposalPerson.attributes}" />

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="proposalDevelopmentKeyPersonnel"
	documentTypeName="ProposalDevelopmentDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="save"
  	headerTabActive="keyPersonnel">

    <kul:uncollapsable tabTitle="Add Key Personnel">
          <div align="center">
            <table  cellpadding="0" cellspacing="0" class="grid" summary="">
              <tr>
                <th class="grid"><div align="right">Person:</div></th>
                <td nowrap class="grid"><label> Employee Search</label>

                  <label><kul:lookup boClassName="org.kuali.kra.bo.Person" 
                                fieldConversions="personId:newPersonId" /></label><br>
                  <label>Non-employee Search</label> 
                  <label><kul:lookup boClassName="org.kuali.kra.bo.Rolodex" 
                                fieldConversions="rolodexId:newRolodexId" /></label></td>
                <th class="grid"><div align="right">Proposal Role:</div></th>
                <td class="grid" >
                  <label><kul:htmlControlAttribute property="newProposalPerson.proposalPersonRoleId" attributeEntry="${proposalPersonAttributes.proposalPersonRoleId}" /> </label>
                </td>
              </tr>
            </table>
            <br>


            <span><html:image property="methodToCall.insertProposalPerson" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-addpers.gif" title="Add Proposal Person" alt="Add Proposal Person" styleClass="tinybutton"/></span>
          </div>

    </kul:uncollapsable>

    <br/>

    <kra-pd:keyPersons/>

    <kra-pd:creditSplit/>

    <kul:panelFooter />

    <kul:documentControls transactionalDocument="true" suppressRoutingControls="true" />

<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;
</SCRIPT>
<script language="javascript" src="scripts/kuali_application.js"></script>
<script language="javascript" src="dwr/interface/ProposalDevelopmentService.js"></script>

</kul:documentPage>