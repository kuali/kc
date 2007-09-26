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

<c:if test="${!empty creditSplitEnabledFlag}">
<kul:tab tabTitle="Combined Credit Split" defaultOpen="true" tabErrorKey="">
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Combined Credit Split</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType" altText="help"/></span>
        </div>

        <table  cellpadding="0" cellspacing="0"  summary="">
            <tbody>
              <tr>
<c:forEach items="${KualiForm.investigatorCreditTypes}" var="invType" >
<th width="20%">${invType}</th>
</c:forEach>
              </tr>
            </tbody>
        </table>
    </div>
</kul:tab>
</c:if>

