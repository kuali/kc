<%--
 Copyright 2006-2009 The Kuali Foundation
 
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

<c:set var="awardAttributes" value="${DataDictionary.Award.attributes}" />
<c:set var="awardPreAwardAuthorizationCommentAttributes" value="${DataDictionary.AwardComment.attributes}" />
<c:set var="action" value="awardTimeAndMoney" />

<kul:tab tabTitle="Preaward Authorizations" defaultOpen="false" tabErrorKey="document.awardList[0].preAwardAuthorizedAmount,document.awardList[0].preAwardEffectiveDate,document.awardList[0].awardComments[3].comments">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Sponsor Authorization</span>
        </h3>
        <table id="Pre-Award-Authorizations" cellpadding="0" cellspacing="0" summary="Pre Award Authorizations">
        	<tr>
            	<th width="250" align="right" scope="row"><div align="right">Authorized Amount:</div></th>
            	<td>
            	  	<div align="left">
            	  	 	<kul:htmlControlAttribute property="document.awardList[0].preAwardAuthorizedAmount" attributeEntry="${awardAttributes.preAwardAuthorizedAmount}"/>
            	 	</div>
            	</td>
            </tr>
            <tr>
            	<th width="250" align="right" scope="row"><div align="right">Effective Date:</div></th>
            	<td>
            	  	<div align="left">
            	  	 	<kul:htmlControlAttribute property="document.awardList[0].preAwardEffectiveDate" attributeEntry="${awardAttributes.preAwardEffectiveDate}" datePicker="true"/>
            	 	</div>
            	</td>
             </tr>
             <tr>
             	<th width="250" align="right" scope="row"><div align="right">Comments:</div></th>
        	 	<td class="infoline">
            	 	<div align="left">
            	  	 	<kul:htmlControlAttribute property="document.awardList[0].awardComments[3].comments" attributeEntry="${awardPreAwardAuthorizationCommentAttributes.comments}"/>
            	  	 	<kra:expandedTextArea textAreaFieldName="document.awardList[0].awardComments[3].comments" action="${action}" textAreaLabel="${awardPreAwardAuthorizationCommentAttributes.comments.label}" />
            	 	</div>
             	</td>
             </tr>
         </table>
    </div>
    
    <div class="tab-container" align="center">
        <div class="left-errmsg-tab">
            <kul:errors keyMatch="document.awardList[0].preAwardInstitutionalAuthorizedAmount,document.awardList[0].preAwardInstitutionalEffectiveDate,document.awardList[0].awardComments[4].comments"/>
            <br/>
        </div>
    	<h3>
    		<span class="subhead-left">Institutional Authorization</span>
        </h3>
        <table id="Pre-Award-Authorizations" cellpadding="0" cellspacing="0" summary="Pre Award Authorizations">
        	<tr>
            	<th width="250" align="right" scope="row"><div align="right">Authorized Amount:</div></th>
            	<td>
            	  	<div align="left">
            	  	 	<kul:htmlControlAttribute property="document.awardList[0].preAwardInstitutionalAuthorizedAmount" attributeEntry="${awardAttributes.preAwardInstitutionalAuthorizedAmount}"/>
            	 	</div>
            	</td>
            </tr>
            <tr>
            	<th width="250" align="right" scope="row"><div align="right">Effective Date:</div></th>
            	<td>
            	  	<div align="left">
            	  	 	<kul:htmlControlAttribute property="document.awardList[0].preAwardInstitutionalEffectiveDate" attributeEntry="${awardAttributes.preAwardInstitutionalEffectiveDate}" datePicker="true"/>
            	 	</div>
            	</td>
             </tr>
             <tr>
             	<th width="250" align="right" scope="row"><div align="right">Comments:</div></th>
        	 	<td class="infoline">
            	 	<div align="left">
            	  	 	<kul:htmlControlAttribute property="document.awardList[0].awardComments[4].comments" attributeEntry="${awardPreAwardAuthorizationCommentAttributes.comments}"/>
            	  	 	<kra:expandedTextArea textAreaFieldName="document.awardList[0].awardComments[4].comments" action="${action}" textAreaLabel="${awardPreAwardAuthorizationCommentAttributes.comments.label}" />
            	 	</div>
             	</td>
             </tr>
         </table>
    </div>
</kul:tab>
