<%--
 Copyright 2005-2008 The Kuali Foundation
 
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

<%@ attribute name="lookup" required="false" description="indicates whether the lookup specific footer should be shown" %>
<%@ attribute name="feedbackKey" required="false" description="application resources key that contains feedback contact address only used when lookup attribute is false"%>

<c:choose>
	<c:when test="${lookup}" >
        <!-- lookup/inquiry footer -->
    </c:when>
	<c:otherwise>
		<!-- footer -->
		<c:choose>
			<c:when test="${empty feedbackKey}">
				<c:set var="feedbackKey" value="app.feedback.link"/>
			</c:when>
		</c:choose>
		
					<p>&nbsp;</p>
				</td>
				<td width="20"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" width="20" height="20"/></td>
			</tr>
		</table>
	</c:otherwise>
</c:choose>

