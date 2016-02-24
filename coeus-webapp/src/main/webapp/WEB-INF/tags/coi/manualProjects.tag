<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
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

<c:set var="coiDisclProjectAttributes" value="${DataDictionary.CoiDisclProject.attributes}" />
<c:set var="coiDiscDetailAttributes" value="${DataDictionary.CoiDiscDetail.attributes}" />
<kul:tab defaultOpen="false" tabTitle="Manual Event and Financial Entities" auditCluster="financialEntityDiscAuditErrors" tabAuditKey="document.coiDisclosureList[0].coiDisclProjects[0].coiDiscDetails*" useRiceAuditMode="true"
    tabErrorKey="disclosureHelper.newCoiDisclProject.*" >
	<div class="tab-container" align="center">
	<c:choose>
	<c:when test="${fn:length(KualiForm.document.coiDisclosureList[0].coiDisclProjects) == 0}">
	
      <div class="div_manual_new">
                <h3>
    		        <span class="subhead-left">New Event Project</span>
    		        <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.coi.CoiDiscDetail" altText="help"/></span>
                </h3>
        <table id="newpEvent-table" cellpadding="0" cellspacing="0" summary="">
        	<tbody class="addline">
            <tr>
                <th align="right" valign="middle">
                   <kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.disclosureEventType}" />
                </th>
                <td align="left" valign="middle">
                    <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.disclosureEventType" 
                            attributeEntry="${coiDisclProjectAttributes.disclosureEventType}" onchange="handleEventType(this)" /> 
                </td>
            </tr>
            <tr>
                <th align="right" valign="middle">
                   <span><kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.coiProjectId}" /></span>
                </th>
                <td align="left" valign="middle" colspan="2">
                    <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.coiProjectId" 
                            attributeEntry="${coiDisclProjectAttributes.coiProjectId}" /> 
                </td>
            </tr>
            <tr>
                <th align="right" valign="middle">
                   <span><kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.coiProjectTitle}" /></span>
                </th>
                <td align="left" valign="middle" colspan="2">
                    <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.coiProjectTitle" 
                            attributeEntry="${coiDisclProjectAttributes.coiProjectTitle}" /> 
                </td>
            </tr>                        
            <tr>
                <th align="right" valign="middle">
                   <span><kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.longTextField1}" /></span>
                </th>
                <td align="left" valign="middle" colspan="2">
                    <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.longTextField1" 
                            attributeEntry="${coiDisclProjectAttributes.longTextField1}" /> 
                </td>
            </tr>
            <tr>
                 <th align="right" valign="middle">
                   <span><kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.shortTextField1}" /></span>
                </th>
                <td align="left" valign="middle" colspan="2" >
                    <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.shortTextField1" 
                            attributeEntry="${coiDisclProjectAttributes.shortTextField1}" /> 
                </td>
            </tr>
            <tr>
                <th align="right" valign="middle" >
                   <span><kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.shortTextField3}" /></span>
                </th>
               <td align="left" valign="middle" colspan="2" >
                    <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.shortTextField3" 
                            attributeEntry="${coiDisclProjectAttributes.shortTextField3}" />
          
                 </td>
            </tr>
            <tr>
                <th align="right" valign="middle" >
                   <span><kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.longTextField2}" /></span>
                </th>
               <td align="left" valign="middle" colspan="2" >
                       <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.longTextField2" 
                            attributeEntry="${coiDisclProjectAttributes.longTextField2}" />
          
                 </td>
            </tr>
            <tr>
                <th align="right" valign="middle">
                   <span><kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.shortTextField2}" /></span>
                </th>
                <td align="left" valign="middle" colspan="2" >
                    <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.shortTextField2" 
                            attributeEntry="${coiDisclProjectAttributes.shortTextField2}" /> 
                </td>
            </tr>
                <th align="right" valign="middle" >
                   <span><kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.numberField1}" /></span>
                </th>
               <td align="left" valign="middle" colspan="2" >
                       <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.numberField1" 
                            attributeEntry="${coiDisclProjectAttributes.numberField1}" />
          
                 </td>
            </tr>
            <tr>
                <th align="right" valign="middle">
                   <span><kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.longTextField3}" /></span>
                </th>
                <td align="left" valign="middle" colspan="2" >
                    <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.longTextField3" 
                            attributeEntry="${coiDisclProjectAttributes.longTextField3}" /> 
                </td>
            </tr>
            <tr>
                <th align="right" valign="middle" >
                   <span><kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.numberField2}" /></span>
                </th>
                <td align="left" valign="middle" colspan="2" >
                       <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.numberField2" 
                            attributeEntry="${coiDisclProjectAttributes.numberField2}" />
          
                </td>
            </tr>            
            <tr>
                <th align="right" valign="middle">
                   <span><kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.dateField1}" /></span>
                </th>
                <td align="left" valign="middle" colspan="2" >
                    <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.dateField1" 
                            attributeEntry="${coiDisclProjectAttributes.dateField1}" /> 
                </td>
            </tr>
            <tr>
                <th align="right" valign="middle" >
                   <span><kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.dateField2}" /></span>
                </th>
               <td align="left" valign="middle" colspan="2" >
                       <kul:htmlControlAttribute property="disclosureHelper.newCoiDisclProject.dateField2" 
                            attributeEntry="${coiDisclProjectAttributes.dateField2}" />
          
                 </td>
            </tr>
            <tr>
                <th align="right" valign="middle" >
                    <span><kul:htmlAttributeLabel attributeEntry="${coiDisclProjectAttributes.selectBox1}" /></span>
                </th>
                <td align="left" valign="middle" colspan="2" >
                    ${kfunc:registerEditableProperty(KualiForm, "disclosureHelper.newCoiDisclProject.selectBox1")}
                    <input type="hidden" name="disclosureHelper.newCoiDisclProject.selectBox1" id="disclosureHelper.newCoiDisclProject.selectBox1"
                              value="${disclosureHelpler.newCoiDisclProject.selectBox1}" />
                    <select name="selectBox1-placeholder" id="selectBox1-placeholder" onchange="setSelectBox1Value(this)">
                    </select>
                </td>
            </tr>
                <tr>
                    <td align="center" colspan="6">
                        <div align="center">
                            <html:image property="methodToCall.addManualProject.anchor${tabKey}"
                                        src='${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton addButton"/>
                        </div>
                    </td>
                </tr>
                </tbody>
              </table>
           </div> <%-- proposal div --%>              


	</c:when>
	<c:otherwise>


 
       <div class="div_manualproject">
        	<c:forEach var="disclProject" items="${KualiForm.document.coiDisclosureList[0].coiDisclProjects}" varStatus="status">
                 <kra-coi:genericFinancialEntity disclProject="${disclProject}"  idx="${status.index}" boLocation="document.coiDisclosureList[0].coiDisclProjects[${status.index}]" />	            
        	</c:forEach> 
        </div> 

	</c:otherwise>
   </c:choose> 

	</div>
</kul:tab>

