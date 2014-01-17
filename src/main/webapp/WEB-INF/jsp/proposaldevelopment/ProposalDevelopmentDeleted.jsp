<%--
 Copyright 2005-2014 The Kuali Foundation

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
<c:set var="readOnly" value="true" scope="request" />
<kul:page showDocumentInfo="false"
	      headerTitle="Development Proposal Deleted"
	      docTitle="Development Proposal Deleted"
	      transactionalDocument="false"
	      htmlFormAction="proposalDevelopmentProposal"
	      defaultMethodToCall="notify"
	      errorKey="*">

    <div class="topblurb">
		<div align="center">
		   <table cellpadding="10" cellspacing="0" border="0" class="container2">
				<tr>
					<td>
						<div align="left" valign="top">
							<strong>Error Message</strong>
						</div>
					</td>
					<td align="left">
						<div align="left">
							<font color="red">The Development Proposal has been deleted.</font>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td align="left">
						<div>
							<html:image property="methodToCall.close" value="true" 
								   src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif"
								   styleClass="tinybutton" title="close" alt="Close"/>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
  	
  	
  
  
</kul:page>