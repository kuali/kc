<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<c:set var="groupMemberAttributes" value="${DataDictionary.KimDocumentGroupMember.attributes}" />
<c:set var="groupQualifierAttributes" value="${DataDictionary.GroupDocumentQualifier.attributes}" />
<c:set var="kimAttributes" value="${DataDictionary.KimAttributeImpl.attributes}" />

<c:if test="${!empty KualiForm.document.kimType.attributeDefinitions}">
	<kul:tab tabTitle="Attributes" defaultOpen="true" tabErrorKey="document.qualifier*">
		<div class="tab-container" align="center">
		    <table cellpadding="0" cellspacing="0" summary="">
		    	<tr>
					<c:forEach var="attrDefn" items="${KualiForm.document.kimType.attributeDefinitions}" varStatus="status">
		    			<c:set var="fieldName" value="${attrDefn.kimAttribute.attributeName}" />
		    			<c:set var="attrEntry" value="${KualiForm.document.attributeEntry[fieldName]}" />
		     		    <kul:htmlAttributeHeaderCell attributeEntry="${attrEntry}" useShortLabel="false" horizontal="false" />
			        </c:forEach>
		    	</tr>     
				<tr>
					<c:forEach var="qualifier" items="${KualiForm.document.kimType.attributeDefinitions}" varStatus="statusQualifier">
						<c:set var="fieldName" value="${qualifier.kimAttribute.attributeName}" />
		    			<c:set var="attrEntry" value="${KualiForm.document.attributeEntry[fieldName]}" />
		    			<c:set var="attrDefinition" value="${KualiForm.document.definitionsKeyedByAttributeName[fieldName]}"/>
			            <td align="left" valign="middle">
			               	<div align="center"> <kul:htmlControlAttribute property="document.qualifiers[${statusQualifier.index}].attrVal"  attributeEntry="${attrEntry}" readOnly="${readOnly}" />
			               	<c:if test="${!empty attrDefinition.lookupBoClass and not readOnly}">
				       		  <kim:attributeLookup attributeDefinitions="${KualiForm.document.definitions}" pathPrefix="document" attr="${attrDefinition}" />
				          	</c:if>
							</div>
						</td>
			        </c:forEach>
			    </tr>
			</table>
		</div>
	</kul:tab>
</c:if>