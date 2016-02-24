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

<%@ page import="org.kuali.kra.infrastructure.Constants"%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="protocolOnlineReviewInactive"
	documentTypeName="ProtocolOnlineReviewDocument"
	renderMultipart="true"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="onlineReview">
<script type="text/javascript">
    var $j = jQuery.noConflict();
</script>




<c:set var="protocolAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="onlineReviewAttributes" value = "${DataDictionary.ProtocolOnlineReview.attributes}"/>
<c:set var="protocolReviewerAttributes" value="${DataDictionary.ProtocolReviewerBean.attributes}" />

<c:set var="readOnly" value = "true"/>

<style type="text/css">
   .compare { color: #666666 }
   .compare td, .compare th { color:#666666; }
</style>




	<kul:documentOverview editingMode="${KualiForm.editingMode}" />
	
	<kul:tab tabTitle="Protocol Online Review Basic Fields" defaultOpen="true" tabErrorKey="" >
	
	
	 <table cellpadding="0" cellspacing="0" class="datatable" title="view/edit document overview information" summary="view/edit document overview information">
				<tr>
                	<th width = "25%" class="grid">
                		<div align="right">
                			Reviewer:
                		</div>
                	</th>
                	<td width = "25%" nowrap class="grid">
						<c:out value = "${KualiForm.document.protocolOnlineReview.protocolReviewer.fullName}"/>
					</td>
                <th width = "25%" class="grid">
                	<div align="right">
                		<kul:htmlAttributeLabel attributeEntry="${onlineReviewAttributes.dateRequested}" noColon="false" />
                	</div>
                </th>
                <td width = "25%" class="grid">
					<kul:htmlControlAttribute property="document.protocolOnlineReview.dateRequested" attributeEntry="${onlineReviewAttributes.dateRequested}" datePicker="true" readOnly = "${readOnly}" />
                </td>
              </tr>
              <tr>
				<th width = "25%" class="grid">
					<div align="right">
						<kul:htmlAttributeLabel attributeEntry="${onlineReviewAttributes.protocolOnlineReviewStatusCode}" noColon="false" />
					</div>
				</th>
              	<td width = "25%" class = "grid">
              		<kul:htmlControlAttribute property="document.protocolOnlineReview.protocolOnlineReviewStatus.description" attributeEntry="${onlineReviewAttributes.protocolOnlineReviewStatusCode}" datePicker="false" readOnly="${readOnly}" />
              	</td>
                <th width = "25%" class="grid">
                	<div align="right">
                		<kul:htmlAttributeLabel attributeEntry="${onlineReviewAttributes.dateDue}" noColon="false"  />
                	</div>
                </th>
                <td width = "25%" class="grid" >
                	<kul:htmlControlAttribute property="document.protocolOnlineReview.dateDue" attributeEntry="${onlineReviewAttributes.dateDue}" datePicker="true" readOnly = "${readOnly}" />
                </td>
              </tr>
			  <tr>
              
              	<th width = "25%" class="grid">
                	<div align="right">
                		<kul:htmlAttributeLabel attributeEntry="${onlineReviewAttributes.protocolOnlineReviewDeterminationRecommendationCode}" noColon="false" />
                	</div>
                </th>
                <td width = "25%" class="grid" >
                	<kul:htmlControlAttribute property="document.protocolOnlineReview.protocolOnlineReviewDeterminationRecommendationCode" attributeEntry="${onlineReviewAttributes.protocolOnlineReviewDeterminationRecommendationCode}" datePicker="false" readOnly="${readOnly}" />
                </td>
                <th width = "25%" class="grid">
           			<div align="right">
                		<kul:htmlAttributeLabel attributeEntry="${protocolReviewerAttributes.reviewerTypeCode}" noColon="false" />
                	</div>
                </th>
                <td width = "25%" class="grid" >
                	<kul:htmlControlAttribute property="document.protocolOnlineReview.protocolReviewer.reviewerTypeCode"
		                                                                                  attributeEntry="${protocolReviewerAttributes.reviewerTypeCode}" readOnly = "${readOnly}"/>
				</td>
              </tr>
 			  <tr>
              
              	<th width = "25%" class="grid">
                	<div align="right">
                		Explanation:
                	</div>
                </th>
                <td colspan="3">
	                    <div align="left">
	                     
	                        Online Review can be edited/viewed only if the following conditions are met :
	                        <font color = "red"><p> 
	                        <li>The protocol submission status is 'Submitted to committee' or 'In Agenda'. </li>	                        
	                        <li>You must be an Irb Admin or assigned reviewer.</li>
	                        </p></font>
	                        
	                        </div>
                </td>
               </tr>
         	</table>
	
	</kul:tab>

	<kul:routeLog />

<kul:panelFooter />
<%-- <kul:panelFooter /> --%>
	<kul:documentControls 
		transactionalDocument="true"
		suppressRoutingControls="false"
		suppressCancelButton="false"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		viewOnly="${KualiForm.editingMode['viewOnly']}"
		/>


<script language="javascript">
//enableJavaScript()

$j(document).ready(function() {

	$j("#globalbuttons").find('input').each(function() {
              //alert($j(this).attr("name"));
              if ($j(this).attr("name") != 'methodToCall.close') {
            	  $j(this).hide();
              } 
          });

    });

</script>
</kul:documentPage>
