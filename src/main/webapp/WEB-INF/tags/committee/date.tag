<%--
 Copyright 2006-2008 The Kuali Foundation
 
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
<%@ attribute name="property" required="true" %>

	<html:text property="${property}" size="12"
		maxlength="12" styleId="${property}" disabled="false" />
	<img src="${ConfigProperties.kr.externalizable.images.url}cal.gif"
		id="${property}_datepicker" style="cursor: pointer;"
		title="Date selector" alt="Date selector"
		onmouseover="this.style.backgroundColor='red';"
		onmouseout="this.style.backgroundColor='transparent';" />
		<script type="text/javascript">
			                  Calendar.setup(
			                          {
			                            inputField : "${property}", // ID of the input field
			                            ifFormat : "%m/%d/%Y", // the date format
			                            button : "${property}_datepicker" // ID of the button
			                          }
			                  );
		</script>