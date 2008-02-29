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

<c:set var="budgetPersonAttributes" value="${DataDictionary.BudgetPerson.attributes}" />

<div id="workarea">
<kul:tabTop tabTitle="Project Personnel" defaultOpen="true">
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Project Personnel</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </div>
        <table cellpadding=0 cellspacing="0"  summary="">
        	<tr>
	        	<th>&nbsp;</th>
	            <th>Person</th>
	            <th><kul:htmlAttributeLabel attributeEntry="${budgetPersonAttributes.jobCode}" /></div></th>
	            <th><kul:htmlAttributeLabel attributeEntry="${budgetPersonAttributes.appointmentType}" /></div></th>
	            <th><kul:htmlAttributeLabel attributeEntry="${budgetPersonAttributes.calculationBase}" /></div></th>
	            <th><kul:htmlAttributeLabel attributeEntry="${budgetPersonAttributes.effectiveDate}" /></th>
	            <th>Actions</th>
			</tr>
            <c:forEach var="person" items="${KualiForm.document.budgetPersons}" varStatus="status">
            <tr>
              	<th scope="row"><div align="center">${status.index + 1}</div></th>
              	<td>${person.personName} <span class="fineprint">(add role)</span></td>
              	<td><kul:htmlControlAttribute property="document.budgetPerson[${status.index}].jobCode" attributeEntry="${budgetPersonAttributes.jobCode}" /></td>
              	<td><kul:htmlControlAttribute property="document.budgetPerson[${status.index}].appointmentType" attributeEntry="${budgetPersonAttributes.appointmentType}"/>
              		<%--
              		<div align="center"><span>
						<select name="prtcl_aprv_pnd_ind_param.0" value="">
                    		<option value="">REG Employee</option>
                    		<option value="P">SUM Employee</option>
                    		<option value="A">TMP Employee</option>
                    		<option>9M Duration</option>
                    		<option>10M Duration</option>
                    		<option>11M Duration</option>
                    		<option>12M Duration</option>
                  		</select>
              		</span></div>
              		 --%>
              	</td>
              	<td>
              		<div align="center">
                  		<kul:htmlControlAttribute property="document.budgetPerson[${status.index}].calculationBase" attributeEntry="${budgetPersonAttributes.calculationBase}" />
              		</div>
              	</td>
              	<td>
              		<div align="center">
						<kul:htmlControlAttribute property="document.budgetPerson[${status.index}].effectiveDate" attributeEntry="${budgetPersonAttributes.effectiveDate}" />
                  	</div>
                </td>
              	<td><div align=center><html:image property="methodToCall.deletePerson" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' /></div></td>
			</tr>
        	</c:forEach>
        </table>
   	</div>
</kul:tabTop>
</div>

<kul:panelFooter />