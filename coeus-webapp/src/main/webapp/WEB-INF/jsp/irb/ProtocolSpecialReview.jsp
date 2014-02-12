<%--
 Copyright 2005-2013 The Kuali Foundation

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
	htmlFormAction="protocolSpecialReview"
	documentTypeName="ProtocolDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="specialReview">

<div align="right"><kul:help documentTypeName="ProtocolDocument" pageName="Special Review" /></div>

<div id="workarea">
	<kra-specialreview:specialReviewPage businessObjectClassName="org.kuali.kra.irb.specialreview.ProtocolSpecialReview"
	                                     attributes="${DataDictionary.ProtocolSpecialReview.attributes}"
	                                     exemptionAttributes="${DataDictionary.ProtocolSpecialReviewExemption.attributes}"
	                                     collectionReference="${KualiForm.document.protocol.specialReviews}"
	                                     collectionProperty="document.protocolList[0].specialReviews"
	                                     action="protocolSpecialReview" />
	
	<kul:panelFooter />
</div>

<kul:documentControls transactionalDocument="false" suppressRoutingControls="true" />

</kul:documentPage>
