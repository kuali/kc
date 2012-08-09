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

<%@ attribute name="businessObjectClassName" required="true" 
              description="The specific per-module business class to use for the help pages" %>

<%@ attribute name="collectionReference" required="true" type="java.util.List" 
              description="The object reference to the collection that holds all the current Protocol Study groups" %>
<%@ attribute name="collectionProperty" required="true" 
              description="The property name of the collection that holds all the current Protocol Study Groups" %>


<c:set var="protocolDocumentAttributes" value="${DataDictionary.IacucProtocolDocument.attributes}" />
<c:set var="protocolAttributes" value="${DataDictionary.IacucProtocol.attributes}" />

<c:set var="readOnly" value="${!KualiForm.iacucProtocolProceduresHelper.modifyProtocolProcedures}" />

<c:set var="parentTabName" value="" />

<c:set var="procedureAttributes" value="${DataDictionary.IacucProcedure.attributes}" />


<script type="text/javascript">
    var $j = jQuery.noConflict();
    $j(document).ready(function(){
        // initial state
        // trigger
        $j(".checkBox").click(
        function() {
                funcHideShowProcedure(this.title, $j(this).is(':checked'));
            }
        );
        // function
        function funcHideShowProcedure(divName, isChecked) {
            if (isChecked) {
                $j("#" + divName).show();
        } else {
                $j("#" + divName).hide();
            }
        }
    }
);
</script>

<kul:tab tabTitle="Procedures" defaultOpen="true" tabErrorKey="" >
    <kra:permission value="${KualiForm.iacucProtocolProceduresHelper.modifyProtocolProcedures}">
	    <div class="tab-container" align="center">
			<kul:innerTab tabTitle="Included Categories" parentTab="${parentTabName}" defaultOpen="false" tabErrorKey="" useCurrentTabIndexAsKey="true">
		    	<table id="included-categories-table" cellpadding=0 cellspacing=0 summary="">
	               	<tr>
						<c:set var="displayTop" value="false" />
	        			<c:forEach var="procedureCategories" items="${KualiForm.iacucProtocolProceduresHelper.allProcedures}" varStatus="status">
							<c:set var="categoryName" value="${KualiForm.iacucProtocolProceduresHelper.allProcedures[status.index].iacucProcedureCategory.procedureCategory}" />
							<c:set var="previousCategoryName" value="${KualiForm.iacucProtocolProceduresHelper.allProcedures[status.index-1].iacucProcedureCategory.procedureCategory}" />
	        				<c:if test="${status.first}">
			            		<td style="vertical-align:top;">
								<c:set var="displayTop" value="true" />
			            	</c:if>
							<c:if test="${categoryName != previousCategoryName}">
								<c:if test="${!displayTop}">
									</br>
								</c:if>
						    	<b><c:out value="${categoryName}" /></b>
						    	</br>
							</c:if>
							<c:set var="procedureCategory" value="category${status.index}Procedure" />
	
					    	<c:set var="prop" value="iacucProtocolProceduresHelper.allProcedures[${status.index}].procedureSelected"/>
					        ${kfunc:registerEditableProperty(KualiForm, prop)} 
					        <input type="hidden" name="checkboxToReset" value="${prop}"/>
	
			            	<html:checkbox styleClass="checkBox" property="iacucProtocolProceduresHelper.allProcedures[${status.index}].procedureSelected" title="${procedureCategory}"/>
						    <c:out value="${KualiForm.iacucProtocolProceduresHelper.allProcedures[status.index].procedureDescription}" />
						    </br>
							<c:choose>
								<c:when test="${status.last}">
	    	            			</td>
								</c:when>
								<c:when test="${status.count % 5 == 0}">
		    	            		</td>
									<c:set var="displayTop" value="true" />
				            		<td style="vertical-align:top;">
									<c:set var="nextCategoryName" value="${KualiForm.iacucProtocolProceduresHelper.allProcedures[status.index+1].iacucProcedureCategory.procedureCategory}" />
									<c:if test="${categoryName == previousCategoryName && categoryName == nextCategoryName}">
						    			<b><c:out value="${categoryName} (Continued)" /></b>
						    			</br>
									</c:if>
								</c:when>
								<c:otherwise>
									<c:set var="displayTop" value="false" />
								</c:otherwise>
							</c:choose>
	    	            </c:forEach>
	               	</tr>
	
		         	<tr>
		         		<td colspan="4" class="infoline">
							<div align="center">
								<html:image property="methodToCall.updateIacucProtocolStudyGroupCategory.anchor${tabKey}"
								src="${ConfigProperties.kra.externalizable.images.url}tinybutton-update.gif" styleClass="tinybutton"/>
							</div>
						</td>
		         	</tr>
		    	</table>
			</kul:innerTab>
	    </div>
    </kra:permission>            

    <c:forEach var="protocolStudyGroups" items="${collectionReference}" varStatus="status">
		<c:set var="procedureSelected" value="${KualiForm.iacucProtocolProceduresHelper.allProcedures[status.index].procedureSelected}" />
		<c:set var="divDisplay" value="display: none" />
		<c:if test="${procedureSelected}">
			<c:set var="divDisplay" value="display: block" />
		</c:if>
		<c:set var="procedureCategory" value="category${status.index}Procedure" />
	    <div id="${procedureCategory}" style="${divDisplay}" class="tab-container" align="center">
			<c:set var="procCategory" value="${KualiForm.document.protocol.iacucProtocolStudyGroupBeans[status.index].procedureCategory}" />
			<c:set var="procDescription" value="${KualiForm.document.protocol.iacucProtocolStudyGroupBeans[status.index].procedureDescription}" />
			<kul:innerTab tabTitle="${procCategory} : ${procDescription}" parentTab="${parentTabName}" defaultOpen="true" tabErrorKey="iacucProtocolStudyGroupBeans[${status.index}]" useCurrentTabIndexAsKey="true">
		    	<h3>
		    		<span class="subhead-left">${procCategory} : ${procDescription}</span>
		    		<span class="subhead-right"><kul:help businessObjectClassName="${businessObjectClassName}" altText="help"/></span>
		        </h3>
	     		<kra-iacuc:protocolStudyGroups
	                      collectionReference="${KualiForm.document.protocol.iacucProtocolStudyGroupBeans[status.index].iacucProtocolStudyGroupDetailBeans}"
	                      collectionProperty="document.protocolList[0].iacucProtocolStudyGroupBeans[${status.index}].iacucProtocolStudyGroupDetailBeans"
	                      procedureBeanProperty="document.protocolList[0].iacucProtocolStudyGroupBeans[${status.index}]"
	                      procedureBeanIndex="${status.index}"/>
		 	</kul:innerTab>
		 </div>
    </c:forEach>
    
</kul:tab>    	 	
