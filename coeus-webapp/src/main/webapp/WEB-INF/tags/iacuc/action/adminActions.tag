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

<kra:permission value="${KualiForm.actionHelper.canAdministrativelyWithdraw or 
						 KualiForm.actionHelper.canAdministrativelyApprove or
						 KualiForm.actionHelper.canReviewNotRequired or 
						 KualiForm.actionHelper.canAdministrativelyMarkIncomplete}">

	<kul:innerTab tabTitle="Administrative Determination" parentTab="" defaultOpen="false" >
	
		<div class="innerTab-container" align="left">
		   <table class="tab" cellpadding="0" cellspacing="0" summary=""> 
	            <tbody>
		 			 <kra-iacuc-action:adminWithdrawAction />
				     <kra-iacuc-action:adminApproveAction />
		    		 <kra-iacuc-action:adminMarkIncompleteAction />
		    		 <kra-iacuc-action:adminReviewNotRequiredAction />
	            </tbody>
	        </table>
	    </div>
	    
	</kul:innerTab>

</kra:permission>
