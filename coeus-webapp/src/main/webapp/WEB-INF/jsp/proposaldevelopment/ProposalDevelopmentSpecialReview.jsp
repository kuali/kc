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

<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="proposalDevelopmentSpecialReview"
	documentTypeName="ProposalDevelopmentDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="specialReview">
  	
<div align="right"><kul:help documentTypeName="ProposalDevelopmentDocument" pageName="Special Review" /></div>

<div id="workarea">
	<kra-specialreview:specialReviewPage businessObjectClassName="org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview"
	                                     attributes="${DataDictionary.ProposalSpecialReview.attributes}"
	                                     exemptionAttributes="${DataDictionary.ProposalSpecialReviewExemption.attributes}"
	                                     collectionReference="${KualiForm.document.developmentProposal.propSpecialReviews}"
	                                     collectionProperty="document.developmentProposalList[0].propSpecialReviews"
	                                     action="proposalDevelopmentSpecialReview" />
	
	<kul:panelFooter />
</div>

<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" suppressCancelButton="true" />
<script language="javascript" src="scripts/kuali_application.js"></script>

</kul:documentPage>
