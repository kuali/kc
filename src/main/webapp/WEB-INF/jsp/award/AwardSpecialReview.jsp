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
<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="awardSpecialReview"
	documentTypeName="AwardDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="specialReview"
  	extraTopButtons="${KualiForm.extraTopButtons}" >

<div align="right">
  	    <kul:help parameterNamespace="KC-AWARD" parameterDetailType="Document" parameterName="awardSpecialReviewHelpUrl" altText="help"/>    
</div>

<div id="workarea">
	<kra-specialreview:specialReviewPage businessObjectClassName="org.kuali.kra.award.specialreview.AwardSpecialReview"
	                                     attributes="${DataDictionary.AwardSpecialReview.attributes}"
	                                     exemptionAttributes="${DataDictionary.AwardSpecialReviewExemption.attributes}"
	                                     collectionReference="${KualiForm.document.award.specialReviews}"
	                                     collectionProperty="document.awardList[0].specialReviews"
	                                     action="awardSpecialReview" />
	
	<kul:panelFooter />
</div>

<kul:documentControls transactionalDocument="false" suppressRoutingControls="true" suppressCancelButton="true" />
<script language="javascript" src="scripts/kuali_application.js"></script>

</kul:documentPage>
