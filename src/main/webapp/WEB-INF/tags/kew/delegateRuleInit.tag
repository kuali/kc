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

<kul:tabTop tabTitle="Choose Parent Rule" defaultOpen="true">

    <div class="tab-container" align=center >
        <h3>Select Parent Rule</h3>
	    <table cellpadding="0" cellspacing="0" class="datatable" summary="Parent Rule Section">
			<tr>
				<th align=right valign=middle class="bord-l-b">Select parent rule: </th>
				<td align=left valign=middle class="datacell">
				    <kul:htmlControlAttribute attributeEntry="${ruleAttributes.ruleBaseValuesId}" property="parentRuleId" readOnly="true" readOnlyAlternateDisplay="${fn:escapeXml(KualiForm.ruleDescription)}"/>
				    <kul:lookup boClassName="org.kuali.rice.kew.rule.RuleBaseValues" fieldConversions="ruleBaseValuesId:parentRuleId"/>
				</td>
			</tr>
	    </table>
    </div>

	<c:if test="${KualiForm.parentRule != null}">

    <div class="tab-container" align=center >
        <h3>Select Parent Responsibility to Delegate From</h3>
	    <table cellpadding="0" cellspacing="0" class="datatable" summary="Parent Responsibility Section">
			<tr>
				<th align=middle valign=middle class="bord-l-b" width="10%">Select</th>
				<th align=middle valign=middle class="bord-l-b">Reviewer</th>
				<th align=middle valign=middle class="bord-l-b">Type</th>
				<th align=middle valign=middle class="bord-l-b">Action Request Code</th>
			</tr>
			<c:forEach items="${KualiForm.parentRule.responsibilities}" varStatus="status" var="responsibility">
			    <td align=middle valign=middle class="datacell">
			      <html:radio property="parentResponsibilityId" title="Responsibility ${status.index}" value="${responsibility.responsibilityId}"/>
			    </td>
			    <td align=left valign=middle class="datacell">
			      ${KualiForm.reviewers[status.index]}
			    </td>
			    <td align=left valign=middle class="datacell">
			      ${KualiForm.responsibilityTypes[status.index]}
			    </td>
			    <td align=left valign=middle class="datacell">
			      ${KualiForm.actionRequestCodes[status.index]}
			    </td>
			</c:forEach>
	    </table>
    </div>

    </c:if>


</kul:tabTop>
