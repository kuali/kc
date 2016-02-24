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

<c:set var="procedureViewedBySpecies" value="${KualiForm.iacucProtocolProceduresHelper.procedureViewedBySpecies}" />

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

<kra:permission value="${KualiForm.iacucProtocolProceduresHelper.modifyProtocolProcedures}">
	<kul:innerTab tabTitle="Procedures" parentTab="${parentTabName}" defaultOpen="true" tabErrorKey="" useCurrentTabIndexAsKey="true">
		<h3>
  			<span class="subhead-left">Included Categories</span>
  			<span class="subhead-right"><kul:help parameterNamespace="KC-IACUC" parameterDetailType="Document" parameterName="iacucProtocolIncludedCategoriesHelp" altText="Help"/></span>
     	</h3>
   		<table id="included-categories-table" cellpadding=0 cellspacing=0 summary="">
        	<tr>
				<c:set var="displayTop" value="false" />
      			<c:forEach var="procedureCategories" items="${KualiForm.iacucProtocolProceduresHelper.allProcedures}" varStatus="status">
				<c:set var="name" value="${KualiForm.iacucProtocolProceduresHelper.allProcedures[status.index].iacucProcedureCategory.procedureCategory}" />
				<c:set var="previousCategoryName" value="${KualiForm.iacucProtocolProceduresHelper.allProcedures[status.index-1].iacucProcedureCategory.procedureCategory}" />
      			<c:if test="${status.first}">
            		<td style="vertical-align:top;">
					<c:set var="displayTop" value="true" />
            	</c:if>
				<c:if test="${name != previousCategoryName}">
					<c:if test="${!displayTop}">
						</br>
					</c:if>
			    	<b><c:out value="${name}" /></b>
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
						<c:if test="${name == previousCategoryName && name == nextCategoryName}">
			    			<b><c:out value="${name} (Continued)" /></b>
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
   				<span class="subhead-right"><kul:help parameterNamespace="KC-IACUC" parameterDetailType="Document" parameterName="iacucProtocolProceduresSpecificSectionHelp" altText="Help"/></span>
       		</h3>
  			<c:choose>
  				<c:when test="${procedureViewedBySpecies}">
		   			<kra-iacuc:protocolProcedureCategoriesAndSpeciesStudyGroups
		                    collectionReference="${KualiForm.document.protocol.iacucProtocolStudyGroupBeans[status.index].iacucProtocolSpeciesStudyGroups}"
		                    collectionProperty="document.protocolList[0].iacucProtocolStudyGroupBeans[${status.index}].iacucProtocolSpeciesStudyGroups"
		                    procedureBeanProperty="document.protocolList[0].iacucProtocolStudyGroupBeans[${status.index}]"
		                    procedureBeanIndex="${status.index}"/>
  				</c:when>
  				<c:otherwise>
		   			<kra-iacuc:protocolProcedureCategoriesAndStudyGroups
		                    collectionReference="${KualiForm.document.protocol.iacucProtocolStudyGroupBeans[status.index].iacucProtocolStudyGroups}"
		                    collectionProperty="document.protocolList[0].iacucProtocolStudyGroupBeans[${status.index}].iacucProtocolStudyGroups"
		                    procedureBeanProperty="document.protocolList[0].iacucProtocolStudyGroupBeans[${status.index}]"
		                    procedureBeanIndex="${status.index}"/>
  				</c:otherwise>
  			</c:choose>
		</kul:innerTab>
	</div>
</c:forEach>

