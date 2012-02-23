 <%--
 Copyright 2005-2010 The Kuali Foundation

 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.osedu.org/licenses/ECL-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
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
                                        src='${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
                        </div>
                    </td>
                </tr>
              </table>
           </div> <%-- proposal div --%>              


	</c:when>
	<c:otherwise>


 
       <div class="div_manualproject">
              
                                  
            <%-- New data --%>
            
            <%-- Existing data --%>

        	<c:forEach var="disclProject" items="${KualiForm.document.coiDisclosureList[0].coiDisclProjects}" varStatus="status">
                 <kra-coi:proposalFinancialEntity disclProject="${disclProject}"  idx="${status.index}"/>	            
        	</c:forEach> 
            <%-- Existing data --%>
     </div> <%-- proposal list div --%>

	</c:otherwise>
   </c:choose> 

    </div>
</kul:tab>

