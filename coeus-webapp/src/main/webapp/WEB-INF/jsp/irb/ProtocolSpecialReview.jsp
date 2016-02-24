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
	htmlFormAction="protocolSpecialReview"
	documentTypeName="ProtocolDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="specialReview">

<div align="right">
   <kra:shortUrl shortUrl="${KualiForm.shortUrl}"/>
   <kul:help documentTypeName="ProtocolDocument" pageName="Special Review" />
</div>

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
