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
<c:set var="readOnly" value="false"  scope="request"/>
<c:set var="questionnaireUsageAttributes" value="${DataDictionary.QuestionnaireUsage.attributes}" />
<c:set var="vers" value="${KualiForm.document.newMaintainableObject.businessObject.sequenceNumber}" />

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"><a href="#" class="usagepanelcontrol"><img src='kr/images/tinybutton-show.gif' alt='show/hide panel' title='show/hide panel' width='45' height='15' border='0' align='absmiddle'></a>
          Usage </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.coeus.common.questionnaire.framework.core.Questionnaire" altText="help"/> </span>
    </h3>
        
      <div id="usagepanelcontent">
        <table id = "usage-table" cellpadding="0" cellspacing="0" summary="">
        	<thead>
            <tr>
                <th><div align="left">&nbsp;</div></th> 
                <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${questionnaireUsageAttributes.moduleItemCode}" noColon="true" /></div></th>
                <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${questionnaireUsageAttributes.moduleSubItemCode}" noColon="true" /></div></th>
                <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${questionnaireUsageAttributes.ruleId}" noColon="true" /></div></th>
                <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${questionnaireUsageAttributes.mandatory}" noColon="true" /></div></th>
                <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${questionnaireUsageAttributes.questionnaireLabel}" noColon="true" /></div></th>
                <kul:htmlAttributeHeaderCell literalLabel="Version" scope="col"/>
                <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
            
            </tr>     
            
           <c:if test="${!KualiForm.readOnly}">
             <tr>
                <th class="infoline">
                    <c:out value="Add:" />
                </th>

                <td align="left" valign="middle" class="infoline">
                <div align="center">
                    <kul:htmlControlAttribute property="newQuestionnaireUsage.moduleItemCode" attributeEntry="${questionnaireUsageAttributes.moduleItemCode}" onchange="moduleCodeChange(this)" styleClass="fixed-size-select"/>
                </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                <div id="submodulediv" align="center">
                <%--
							                <html:select property="newQuestionnaireUsage.moduleSubItemCode" tabindex="0">
							                <c:forEach items="${krafn:getOptionList('org.kuali.kra.irb.personnel.ProtocolPersonRoleValuesFinder', paramMap)}" var="option">
							                <c:choose>
							                    <c:when test="${KualiForm.document.protocol.protocolPersons[personIndex].protocolPersonRoleId == option.key}">
							                    <option value="${option.key}" selected>${option.value}</option>
							                    </c:when>
							                    <c:otherwise>
							                    <option value="${option.key}">${option.value}</option>
							                    </c:otherwise>
							                </c:choose>
							                </c:forEach>
							                </html:select> --%>
                </div>
                </td>
                <td style="text-align: center;" class="infoline">
               		<kul:htmlControlAttribute property="newQuestionnaireUsage.ruleId" attributeEntry="${questionnaireUsageAttributes.ruleId}"/>
									<a href="#"><img border="0" title="Search Rule"
                            alt="Search Rule" class="tinybutton"
                            src="static/images/searchicon.gif"  onClick="clickSearchRule('newQuestionnaireUsage.ruleId')"></a>               		
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                    <kul:htmlControlAttribute property="newQuestionnaireUsage.mandatory" attributeEntry="${questionnaireUsageAttributes.mandatory}"/>
                </div>
                </td>
                <td class="infoline">
                <div align="center">
                    <kul:htmlControlAttribute property="newQuestionnaireUsage.questionnaireLabel" attributeEntry="${questionnaireUsageAttributes.questionnaireLabel}" />
                <div align="center">
                </td>
                <td class="infoline">   
                <div align="center">   
                     
                        ${vers}
                </div>
                </td>
                
                
                <td class="infoline">
                    <div align=center>&nbsp;
                        <input type="image" id="addUsage" name="addUsage" alt="Add a Usage" title="Add a Usage" src="static/images/tinybutton-add1.gif" class="tinybutton">
                    </div>
                </td>
            </tr>
            </c:if>
            </thead>
            <tbody>
            <tr class="usageTemplate" style="display: none;">
          		<th class="infoline">%COUNT%</th>
          		<td>%COEUS_MODULE_DESC%
          			<input type="hidden" name="document.newMaintainableObject.businessObject.questionnaireUsages[%INDEX%].moduleItemCode" value="%COEUS_MODULE_CODE%"/></td>
          		<td>%COEUS_SUBMODULE_DESC%
          			<input type="hidden" name="document.newMaintainableObject.businessObject.questionnaireUsages[%INDEX%].moduleSubItemCode" value="%COEUS_SUB_MODULE_CODE%"/></td>
          		<td>%RULE_ID%<input type="hidden" name="document.newMaintainableObject.businessObject.questionnaireUsages[%INDEX%].ruleId" value="%RULE_ID%"/></td>
          		<td>%MANDATORY%<input type="hidden" name="document.newMaintainableObject.businessObject.questionnaireUsages[%INDEX%].mandatory" value="%MANDATORY%"/></td>
          		<td>%LABEL%<input type="hidden" name="document.newMaintainableObject.businessObject.questionnaireUsages[%INDEX%].questionnaireLabel" value="%LABEL%"/></td>
          		<td>%SEQUENCE%<input type="hidden" name="document.newMaintainableObject.businessObject.questionnaireUsages[%INDEX%].questionnaireSequenceNumber" value="%SEQUENCE%"/></td>
          		<td style="text-align: center;"><c:if test="${!KualiForm.readOnly}">
          			<input type="image" id="deleteUsage%COUNT%" 
          				name="deleteUsage" class="deleteUsage" title="Delete Usage" src="static/images/tinybutton-delete1.gif" class="tinybutton">
          			<input type="hidden" name="document.newMaintainableObject.businessObject.questionnaireUsages[%INDEX%].delete" value="No" />
          		</c:if></td>
          	</tr>
          <c:forEach items="${KualiForm.document.newMaintainableObject.dataObject.questionnaireUsages}" var="usage" varStatus="status">
			<kul:checkErrors keyMatch="document.newMaintainableObject.businessObject.questionnaireUsages[${status.index}].moduleSubItemCode" auditMatch="document.newMaintainableObject.businessObject.questionnaireUsages[${status.index}].moduleSubItemCode"/></td>
          	<c:set var="errorStyle" value="${hasErrors ? 'background-color:#FFD5D5; background-image: none;' : '' }"/>
          
          	<tr id="usage${status.count}" style="${errorStyle}">
          		<th class="infoline" style="${errorStyle}"><c:out value="${status.count}"/></th>
          		<td style="${errorStyle}"><c:out value="${usage.coeusModule.description}"/>
          				<input type="hidden" name="document.newMaintainableObject.businessObject.questionnaireUsages[${status.index}].moduleItemCode" value="${usage.moduleItemCode}"/></td>
          		<td style="${errorStyle}"><c:out value="${usage.coeusSubModule.description == null ? '' : usage.coeusSubModule.description}"/>
          				<input type="hidden" name="document.newMaintainableObject.businessObject.questionnaireUsages[${status.index}].moduleSubItemCode" value="${usage.moduleSubItemCode}"/>
          		<td style="${errorStyle}"><c:out value="${usage.ruleId == null ? '' : usage.ruleId}"/>
          				<input type="hidden" name="document.newMaintainableObject.businessObject.questionnaireUsages[${status.index}].ruleId" value="${usage.ruleId}"/></td>
          		<td style="${errorStyle}"><c:out value="${usage.mandatory ? 'Yes' : 'No'}"/></td>
          		<td style="${errorStyle}"><c:out value="${usage.questionnaireLabel}"/></td>
          		<td style="${errorStyle}"><c:out value="${usage.questionnaireSequenceNumber}"/></td>
          		<td style="text-align: center; ${errorStyle}"><c:if test="${!KualiForm.readOnly}">
          			<input type="image" id="deleteUsage${status.count}" 
          				name="deleteUsage" class="deleteUsage" title="Delete Usage" src="static/images/tinybutton-delete1.gif" class="tinybutton">
          			<kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.questionnaireUsages[${status.index}].delete" attributeEntry="${questionnaireUsageAttributes.delete}" />
          		</c:if></td>
          	</tr>
          </c:forEach>
          </tbody>
        </table>
    </div>
</div>

<kul:checkErrors keyMatch="document.newMaintainableObject.businessObject.questionnaireUsages*" auditMatch="document.newMaintainableObject.businessObject.questionnaireUsages*"/>
  <script>

  				<c:if test="${!hasErrors}">
                	jq("#usagepanelcontent").hide();
                </c:if>
                jq("a.usagepanelcontrol").toggle(
                    function()
                    {
                        jq("#usagepanelcontent").slideDown(500);
                        jq("a.usagepanelcontrol").html("<img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' title='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
                    },function(){
                        jq("#usagepanelcontent").slideUp(500);
                        jq("a.usagepanelcontrol").html("<img src='kr/images/tinybutton-show.gif' alt='show/hide panel' title='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
                    }
                );
   </script>
