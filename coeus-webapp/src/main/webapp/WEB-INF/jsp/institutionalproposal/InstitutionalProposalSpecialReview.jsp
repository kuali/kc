<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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
	htmlFormAction="institutionalProposalSpecialReview"
	documentTypeName="InstitutionalProposalDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="specialReview">
  	
<div align="right"><kul:help documentTypeName="InstitutionalProposalDocument" pageName="Special Review" /></div>

<div id="workarea">
	<kra-specialreview:specialReviewPage businessObjectClassName="org.kuali.kra.institutionalproposal.specialreview.InstitutionalProposalSpecialReview"
	                                     attributes="${DataDictionary.InstitutionalProposalSpecialReview.attributes}"
	                                     exemptionAttributes="${DataDictionary.InstitutionalProposalSpecialReviewExemption.attributes}"
	                                     collectionReference="${KualiForm.document.institutionalProposal.specialReviews}"
	                                     collectionProperty="document.institutionalProposalList[0].specialReviews"
	                                     action="institutionalProposalSpecialReview" />
	
	<kul:panelFooter />
</div>

<kul:documentControls transactionalDocument="false" suppressRoutingControls="true" suppressCancelButton="true"/>
<script language="javascript" src="scripts/kuali_application.js"></script>

</kul:documentPage>
