<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:set var="readOnly" value="false"  scope="request"/>
<c:set var="questionnaireUsageAttributes" value="${DataDictionary.QuestionnaireUsage.attributes}" />
<c:set var="vers" value="${KualiForm.newQuestionnaire.sequenceNumber}" />
<c:if test="${KualiForm.document.newMaintainableObject.maintenanceAction == 'Edit' and KualiForm.document.documentHeader.workflowDocument.routeHeader.docRouteStatus == 'I'}">
   <c:set var="vers" value="${KualiForm.newQuestionnaire.sequenceNumber+1}" />
</c:if>
<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"><a href="#" class="usagepanelcontrol"><img src='kr/images/tinybutton-show.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'></a>
          Usage </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.questionnaire.Questionnaire" altText="help"/> </span>
    </h3>
        
      <div id="usagepanelcontent">
        <table id = "usage-table" cellpadding="0" cellspacing="0" summary="">
          	<tr>
          		<th><div align="left">&nbsp</div></th> 
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${questionnaireUsageAttributes.moduleItemCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${questionnaireUsageAttributes.questionnaireLabel}" noColon="true" /></div></th>
              	<kul:htmlAttributeHeaderCell literalLabel="Version" scope="col"/>
              	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	
          	</tr>     
          	
             <tr>
				<th class="infoline">
					<c:out value="Add:" />
				</th>

                <td align="left" valign="middle" class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="newQuestionnaireUsage.moduleItemCode" attributeEntry="${questionnaireUsageAttributes.moduleItemCode}" styleClass="fixed-size-select"/>
	            </div>
				</td>
                <td class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="newQuestionnaireUsage.questionnaireLabel" attributeEntry="${questionnaireUsageAttributes.questionnaireLabel}" />
                <div align="center">
                </td>
                <td class="infoline">   
                <div align="center">   
                     
                        ${vers}
				</div>
				</td>
				<td class="infoline">
					<div align=center>&nbsp;
					    <input type="image" id="addUsage" name="addUsage" src="static/images/tinybutton-add1.gif" class="tinybutton">
					</div>
                </td>
            </tr>
            <%-- use js to display for editing 
        	<c:forEach var="questionnaireUsage" items="${QuestionnaireForm.newQuestionnaire.questionnaireUsages}" varStatus="status">
	             <tr>
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td align="left" valign="middle">
	                	<div align="center"> <kul:htmlControlAttribute property="newQuestionnaire.questionnaireUsages[${status.index}].moduleItemCode" attributeEntry="${questionnaireUsageAttributes.moduleItemCode}"/>
					</div>
					</td>
	                <td>
	                <div align="center"> <kul:htmlControlAttribute property="newQuestionnaire.questionnaireUsages[${status.index}].questionnaireLabel"  attributeEntry="${questionnaireUsageAttributes.questionnaireLabel}" />
	                </div>
	                </td>
	                <td>     
	                <div align="center">           	
                        ${questionnaireUsage.questionnaireSequenceNumber}
					</div>
					</td>
					<td>
					<div align=center>&nbsp;
					    <input type="image" id="deleteUsage" name="deleteUsage" src="static/images/tinybutton-delete1.gif" class="tinybutton">
					</div>
	                </td>
	            </tr>
        	</c:forEach>        
           --%>
            
        </table>
    </div>
</div>

  <script>

				$("#usagepanelcontent").hide();
				$("a.usagepanelcontrol").toggle(
					function()
					{
						$("#usagepanelcontent").slideDown(500);
						$("a.usagepanelcontrol").html("<img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
					},function(){
						$("#usagepanelcontent").slideUp(500);
						$("a.usagepanelcontrol").html("<img src='kr/images/tinybutton-show.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
					}
				);
   </script>