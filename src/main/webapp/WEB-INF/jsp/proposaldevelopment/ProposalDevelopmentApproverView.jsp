<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="hierarchyStatus" value="${KualiForm.document.developmentProposalList[0].hierarchyStatus}" />
<c:set var="hierarchyChildStatus" value="${KualiForm.hierarchyChildStatus}"/>
<c:set var="readOnly" value="${not KualiForm.editingMode['modifyProposal']}" scope="request" /> 
<%-- Proposal Actions Page - Submit To Grants.gov Button - Commented Temporarily--%>
<c:set var="extraButtons" value="${KualiForm.extraActionsButtons}" scope="request"/>
<kra:section permission="submitToSponsor">
  
</kra:section>

<kul:documentPage
showDocumentInfo="false"
	htmlFormAction="proposalDevelopmentApproverView"
		documentTypeName="ProposalDevelopmentDocument"
			renderMultipart="false"
				showTabButtons="true"
					auditCount="0"
						headerDispatch="${KualiForm.headerDispatch}"
							headerTabActive="approverView">
							
<div align="right"><kul:help documentTypeName="ProposalDevelopmentDocument" pageName="Proposal Approver View" /></div>

<kra-pd:proposalDevelopmentApproverView /> 



<script language="javascript" src="scripts/kuali_application.js"></script>
<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;
</SCRIPT>
</kul:documentPage>
