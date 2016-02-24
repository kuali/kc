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
	<kra:shortUrl shortUrl="${KualiForm.shortUrl}"/>
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
