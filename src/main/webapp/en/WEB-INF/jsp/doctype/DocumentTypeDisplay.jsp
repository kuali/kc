<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
  <c:if test="${DocumentTypeForm.existingDocumentType != null}">
	<tr>
	  <td class="thnormal" width="20%">&nbsp;</td>
	  <td class="thnormal">Existing Document Type Values</td>
	  <td class="thnormal">New Document Type Values</td>
	</tr>
  </c:if>

    <tr>
	  <td class="thnormal" width="25%" align="right">Document Id:</td>
	  <c:if test="${DocumentTypeForm.existingDocumentType != null}">
        <td class="datacell"><c:out value="${DocumentTypeForm.existingDocumentType.routeHeaderId}" />&nbsp;<c:if test="${DocumentTypeForm.existingDocumentType.routeHeaderId != null && DocumentTypeForm.existingDocumentType.routeHeaderId != 0}"><a href="<c:url value="RouteLog.do" ><c:param name="routeHeaderId" value="${DocumentTypeForm.existingDocumentType.routeHeaderId}"/></c:url>">
	      <img alt="Route Log for Document" src="images/my_route_log.gif"/></a></c:if>
	    </td>
	  </c:if>
	  <td class="datacell"><c:out value="${DocumentTypeForm.flexDoc.routeHeaderId}" />&nbsp;<c:if test="${DocumentTypeForm.flexDoc.routeHeaderId != null && DocumentTypeForm.flexDoc.routeHeaderId != 0}"><a href="<c:url value="RouteLog.do" ><c:param name="routeHeaderId" value="${DocumentTypeForm.flexDoc.routeHeaderId}"/></c:url>">
	    <img alt="Route Log for Document" src="images/my_route_log.gif"/></a></c:if>
	  </td>
	</tr>

    <tr>
	  <td class="thnormal" width="25%" align="right">Document Type Id:</td>
	  <c:if test="${DocumentTypeForm.existingDocumentType != null}">
        <td class="datacell"><c:out value="${DocumentTypeForm.existingDocumentType.documentTypeId}" />&nbsp;
	    </td>
	  </c:if>
	  <td class="datacell"><c:out value="${DocumentTypeForm.documentType.documentTypeId}" />&nbsp;
	  </td>
	</tr>

	<tr>
	  <td class="thnormal" width="25%" align="right">Previous Version Id:</td>
	  <c:if test="${DocumentTypeForm.existingDocumentType != null}">
        <td class="datacell">
        	<c:if test="${DocumentTypeForm.existingDocumentType.previousVersionId != 0 && DocumentTypeForm.existingDocumentType.previousVersionId != null
        	         && DocumentTypeForm.existingDocumentType.previousVersionId != DocumentTypeForm.existingDocumentType.documentTypeId}">
	        	<a href="<c:url value="DocumentType.do"><c:param name="docTypeId" value="${DocumentTypeForm.existingDocumentType.previousVersionId}"/>
	        		<c:param name="methodToCall" value="report"/></c:url>">
	        		<c:out value="${DocumentTypeForm.existingDocumentType.previousVersionId}"/>
	        	</a>
        	</c:if>&nbsp;
	    </td>
	  </c:if>
	    <td class="datacell">
        	<c:if test="${DocumentTypeForm.documentType.previousVersionId != 0 && DocumentTypeForm.documentType.previousVersionId != null
        	   && DocumentTypeForm.documentType.previousVersionId != DocumentTypeForm.documentType.documentTypeId}">
	        	<a href="<c:url value="DocumentType.do"><c:param name="docTypeId" value="${DocumentTypeForm.documentType.previousVersionId}"/>
	        		<c:param name="methodToCall" value="report"/></c:url>">
	        		<c:out value="${DocumentTypeForm.documentType.previousVersionId}"/>
	        	</a>
        	</c:if>&nbsp;
	    </td>
	</tr>

	<tr>
	  <td class="thnormal" width="25%" align="right">Document Type Name:</td>
	<c:if test="${DocumentTypeForm.existingDocumentType != null}">
	  <td class="datacell"><c:out value="${DocumentTypeForm.existingDocumentType.name}" />&nbsp;</td>
 	</c:if>
	  <td class="datacell"><c:out value="${DocumentTypeForm.documentType.name}" />&nbsp;</td>
	</tr>

	<tr>
	  <td class="thnormal" width="25%" align="right">Document Type Label:</td>
	<c:if test="${DocumentTypeForm.existingDocumentType != null}">
	  <td class="datacell"><c:out value="${DocumentTypeForm.existingDocumentType.label}" />&nbsp;</td>
 	</c:if>
	  <td class="datacell"><c:out value="${DocumentTypeForm.documentType.label}" />&nbsp;</td>
	</tr>

	<tr>
	  <td class="thnormal" width="25%" align="right">Parent Document Type Name:</td>
	<c:if test="${DocumentTypeForm.existingDocumentType != null}">
	  <c:choose>
	   <c:when test="${DocumentTypeForm.existingDocumentType.parentDocType != null}">
	    <td class="datacell">
	      <a href="<c:url value="DocumentType.do"><c:param name="docTypeId" value="${DocumentTypeForm.existingDocumentType.docTypeParentId}"/>
	        		<c:param name="methodToCall" value="report"/></c:url>">
	        		<c:out value="${DocumentTypeForm.existingDocumentType.parentDocType.name}"/>
	      </a>&nbsp;
	     </td>
	   </c:when>
	  <c:otherwise>
	    <td class="datacell">&nbsp;</td>
	  </c:otherwise>
 	  </c:choose>
 	</c:if>
	  <c:choose>
	   <c:when test="${DocumentTypeForm.documentType.parentDocType != null}">
		  <td class="datacell">
		  	<a href="<c:url value="DocumentType.do"><c:param name="docTypeId" value="${DocumentTypeForm.documentType.docTypeParentId}"/>
	        		<c:param name="methodToCall" value="report"/></c:url>">
	        		<c:out value="${DocumentTypeForm.documentType.parentDocType.name}"/>
	        </a>
		  </td>
	   </c:when>
	  <c:otherwise>
	    <td class="datacell">&nbsp;</td>
	  </c:otherwise>
 	  </c:choose>
	</tr>

	<tr>
	  <td class="thnormal" width="25%" align="right">Superuser Workgroup Name:</td>
	<c:if test="${DocumentTypeForm.existingDocumentType != null}">
	  <td class="datacell"><c:out value="${DocumentTypeForm.existingDocumentType.superUserWorkgroup.groupNameId.nameId}" />&nbsp;</td>
 	</c:if>
	  <td class="datacell"><c:out value="${DocumentTypeForm.documentType.superUserWorkgroup.groupNameId.nameId}" />&nbsp;</td>
	</tr>

	<tr>
	  <td class="thnormal" width="25%" align="right">Blanket Approve Workgroup Name:</td>
	<c:if test="${DocumentTypeForm.existingDocumentType != null}">
	  <td class="datacell"><c:out value="${DocumentTypeForm.existingDocumentType.blanketApproveWorkgroup.groupNameId.nameId}" />&nbsp;</td>
 	</c:if>
	  <td class="datacell"><c:out value="${DocumentTypeForm.documentType.blanketApproveWorkgroup.groupNameId.nameId}" />&nbsp;</td>
	</tr>

	<tr>
	  <td class="thnormal" width="25%" align="right">Postprocessor Name:</td>
	<c:if test="${DocumentTypeForm.existingDocumentType != null}">
	  <td class="datacell"><c:out value="${DocumentTypeForm.existingDocumentType.postProcessorName}" />&nbsp;</td>
 	</c:if>
	  <td class="datacell"><c:out value="${DocumentTypeForm.documentType.postProcessorName}" />&nbsp;</td>
	</tr>

	<tr>
	  <td class="thnormal" width="25%" align="right">DocHandler URL:</td>
	<c:if test="${DocumentTypeForm.existingDocumentType != null}">
	  <td class="datacell"><c:out value="${DocumentTypeForm.existingDocumentType.docHandlerUrl}" />&nbsp;</td>
 	</c:if>
	  <td class="datacell"><c:out value="${DocumentTypeForm.documentType.docHandlerUrl}" />&nbsp;</td>
	</tr>
<%--
	<tr>
	  <td class="thnormal" width="25%" align="right">Custom Action List Attribute Class Name:</td>
	<c:if test="${DocumentTypeForm.existingDocumentType != null}">
	  <td class="datacell"><c:out value="${DocumentTypeForm.existingDocumentType.customActionListAttributeClassName}" />&nbsp;</td>
 	</c:if>
	  <td class="datacell"><c:out value="${DocumentTypeForm.documentType.customActionListAttributeClassName}" />&nbsp;</td>
	</tr>
-
	<tr>
	  <td class="thnormal" width="25%" align="right">Custom Email Attribute Class Name:</td>
	<c:if test="${DocumentTypeForm.existingDocumentType != null}">
	  <td class="datacell"><c:out value="${DocumentTypeForm.existingDocumentType.customEmailAttributeClassName}" />&nbsp;</td>
 	</c:if>
	  <td class="datacell"><c:out value="${DocumentTypeForm.documentType.customEmailAttributeClassName}" />&nbsp;</td>
	</tr>

	<tr>
	  <td class="thnormal" width="25%" align="right">Custom Note Attribute Class Name:</td>
	<c:if test="${DocumentTypeForm.existingDocumentType != null}">
	  <td class="datacell"><c:out value="${DocumentTypeForm.existingDocumentType.customNoteAttributeClassName}" />&nbsp;</td>
 	</c:if>
	  <td class="datacell"><c:out value="${DocumentTypeForm.documentType.customNoteAttributeClassName}" />&nbsp;</td>
	</tr>
--%>
     <tr>
	    <td class="thnormal" width="25%" align="right">Document Type Active Indicator:</td>
	    <c:if test="${DocumentTypeForm.existingDocumentType != null}">
		  <td class="datacell"><c:out value="${DocumentTypeForm.existingDocumentType.docTypeActiveIndicatorDisplayValue}" />&nbsp;</td>
	    </c:if>
	    <td class="datacell"><c:out value="${DocumentTypeForm.documentType.docTypeActiveIndicatorDisplayValue}" />&nbsp;</td>
	 </tr>

	 <tr>
	    <td class="thnormal" width="25%" align="right">Description:</td>
	    <c:if test="${DocumentTypeForm.existingDocumentType != null}">
	    <td class="datacell"><c:out value="${DocumentTypeForm.existingDocumentType.description}" />&nbsp;</td>
 	    </c:if>
	    <td class="datacell"><c:out value="${DocumentTypeForm.documentType.description}" />&nbsp;</td>
	 </tr>

     <tr>
       <td class="thnormal" width="25%" align="right">Default Approve: </td>
      <c:if test="${DocumentTypeForm.existingDocumentType != null}">
       <td class="datacell"><c:out value="${DocumentTypeForm.existingDocumentType.defaultApprovePolicyDisplayValue}" />&nbsp;</td>
      </c:if>
       <td class="datacell"><c:out value="${DocumentTypeForm.documentType.defaultApprovePolicyDisplayValue}" />&nbsp;</td>
     </tr>

     <tr>
       <td class="thnormal" width="25%" align="right">Pre Approve: </td>
      <c:if test="${DocumentTypeForm.existingDocumentType != null}">
       <td class="datacell"><c:out value="${DocumentTypeForm.existingDocumentType.preApprovePolicyDisplayValue}" />&nbsp;</td>
      </c:if>
       <td class="datacell"><c:out value="${DocumentTypeForm.documentType.preApprovePolicyDisplayValue}" />&nbsp;</td>
     </tr>

     <tr>
       <td class="thnormal" width="25%" align="right">Intiator Must Route: </td>
      <c:if test="${DocumentTypeForm.existingDocumentType != null}">
       <td class="datacell"><c:out value="${DocumentTypeForm.existingDocumentType.initiatorMustRouteDisplayValue}" />&nbsp;</td>
      </c:if>
       <td class="datacell"><c:out value="${DocumentTypeForm.documentType.initiatorMustRouteDisplayValue}" />&nbsp;</td>
     </tr>

     <tr>
        <td class="thnormal" valign="top" width="25%" align="right">Document Type Searchable Attributes: </td>
      <c:if test="${DocumentTypeForm.existingDocumentType != null}">
        <td class="datacell" valign="top">
          <table align="left" border="0" cellpadding="1" cellspacing="1" >
            <c:choose>
              <c:when test="${DocumentTypeForm.documentType.searchableAttributesInherited}">
                <c:forEach var="attribute" items="${DocumentTypeForm.existingDocumentType.parentDocType.documentTypeAttributes}" >
                  <c:set var="searchableAttribute" value="${attribute.ruleAttribute}" />
                  <tr><td align="right"><strong>Attribute Name:</strong></td><td><c:out value="${searchableAttribute.name}" />&nbsp;</td></tr>
                  <tr><td align="right"><strong>Class Name:</strong></td><td><c:out value="${searchableAttribute.className}" />&nbsp;</td></tr>
                  <tr><td colspan=2>&nbsp;</td></tr>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <c:forEach var="attribute" items="${DocumentTypeForm.existingDocumentType.documentTypeAttributes}" >
                  <c:set var="searchableAttribute" value="${attribute.ruleAttribute}" />
                  <tr><td align="right"><strong>Attribute Name:</strong></td><td><c:out value="${searchableAttribute.name}" />&nbsp;</td></tr>
                  <tr><td align="right"><strong>Class Name:</strong></td><td><c:out value="${searchableAttribute.className}" />&nbsp;</td></tr>
                  <tr><td colspan=2>&nbsp;</td></tr>
                </c:forEach>
              </c:otherwise>
            </c:choose>
          </table>
          &nbsp;
        </td>
      </c:if>
        <td class="datacell" valign="top">
          <table align="left" border="0" cellpadding="1" cellspacing="1">
	        <c:choose>
	          <c:when test="${DocumentTypeForm.documentType.searchableAttributesInherited}">
	            <c:forEach var="attribute" items="${DocumentTypeForm.documentType.parentDocType.documentTypeAttributes}" >
	              <c:set var="searchableAttribute" value="${attribute.ruleAttribute}" />
	              <tr><td align="right"><strong>Attribute Name:</strong></td><td><c:out value="${searchableAttribute.name}" />&nbsp;</td></tr>
	              <tr><td align="right"><strong>Class Name:</strong></td><td><c:out value="${searchableAttribute.className}" />&nbsp;</td></tr>
	              <tr><td colspan=2>&nbsp;</td></tr>
	            </c:forEach>
	          </c:when>
	          <c:otherwise>
	            <c:forEach var="attribute" items="${DocumentTypeForm.documentType.documentTypeAttributes}" >
	              <c:set var="searchableAttribute" value="${attribute.ruleAttribute}" />
	              <tr><td align="right"><strong>Attribute Name:</strong></td><td><c:out value="${searchableAttribute.name}" />&nbsp;</td></tr>
	              <tr><td align="right"><strong>Class Name:</strong></td><td><c:out value="${searchableAttribute.className}" />&nbsp;</td></tr>
	              <tr><td colspan=2>&nbsp;</td></tr>
	            </c:forEach>
              </c:otherwise>
	        </c:choose>
	      </table>
          &nbsp;
        </td>
      </tr>
      

	 <tr>
	  	<td class="thnormal" valign="top" width="25%" align="right">Document Type Route Path: </td>
	  	<td class="datacell" valign="top">
	  	  To View the Route Path of the Document, Export the Document as XML using the button below.
	  	</td>
<%--	  <c:if test="${DocumentTypeForm.existingDocumentType != null}">
		<td class="datacell" valign="top">
  		<table align="left" border="0" cellpadding="1" cellspacing="1" >
		  <c:forEach var="routeLevel" items="${DocumentTypeForm.existingDocumentType.routeLevels}" >
		    <tr><td align="right"><strong>Route Level:</strong></td><td><c:out value="${routeLevel.routeLevelPriority}" />&nbsp;</td></tr>
	  		<tr><td align="right"><strong>Route Level Name:</strong></td><td><c:out value="${routeLevel.routeLevelName}" />&nbsp;</td></tr>
	  		<tr><td align="right"><strong>Route Method:</strong></td><td><c:out value="${routeLevel.routeMethodName}" />&nbsp;</td></tr>
	  		<tr><td align="right"><strong>Exception Workgroup Name:</strong></td><td><c:out value="${routeLevel.exceptionWorkgroupName}" />&nbsp;</td></tr>
	  		<tr><td align="right"><strong>Final Approver:</strong></td><td><c:out value="${routeLevel.finalApprovalDisplayValue}" />&nbsp;</td></tr>
	  		<tr><td align="right"><strong>Mandatory Route:</strong></td><td><c:out value="${routeLevel.mandatoryRouteDisplayValue}" />&nbsp;</td></tr>
	  		<tr><td align="right"><strong>Activation:</strong></td><td><c:out value="${routeLevel.activationTypeDisplayValue}" />&nbsp;</td></tr>
			<tr><td colspan=2>&nbsp;</td></tr>
		  </c:forEach>
		  </table>
		  &nbsp;
		</td>
	  </c:if>
	  	<td class="datacell" valign="top">
	  		<table align="left" border="0" cellpadding="1" cellspacing="1">
	  	 <c:forEach var="routeLevel" items="${DocumentTypeForm.documentType.routeLevels}" >
	  	 <tr><td align="right"><strong>Route Level:</strong></td><td><c:out value="${routeLevel.routeLevelPriority}" />&nbsp;</td></tr>
			 <tr><td align="right"><strong>Route Level Name:</strong></td><td><c:out value="${routeLevel.routeLevelName}" />&nbsp;</td></tr>
	  	 <tr><td align="right"><strong>Route Method:</strong></td><td><c:out value="${routeLevel.routeMethodName}" />&nbsp;</td></tr>
	  	 <tr><td align="right"><strong>Exception Workgroup Name:</strong></td><td><c:out value="${routeLevel.exceptionWorkgroupName}" />&nbsp;</td></tr>
	  	 <tr><td align="right"><strong>Final Approver:</strong></td><td><c:out value="${routeLevel.finalApprovalDisplayValue}" />&nbsp;</td></tr>
	  	 <tr><td align="right"><strong>Mandatory Route:</strong></td><td><c:out value="${routeLevel.mandatoryRouteDisplayValue}" />&nbsp;</td></tr>
	  	 <tr><td align="right"><strong>Activation:</strong></td><td><c:out value="${routeLevel.activationTypeDisplayValue}" />&nbsp;</td></tr>
 	  	 <tr><td colspan=2>&nbsp;</td></tr>
	  	 </c:forEach>
	  	 </table>
	  	 &nbsp;
	    </td>--%>
	  </tr>

	<c:if test="${! empty annotation}">
	  <tr>
		<td class="thnormal" width="25%" align="right">Annotation:</td>
		<td class="datacell" colspan="2"><c:out value="${annotation}" />&nbsp;</td>
	  </tr>
	</c:if>
	<tr>
    <td class="thnormal" width="25%" align="right">Xml Config Data</td>
    <td class="datacell" colspan="2">
      <div style="height: 300px; overflow: auto">
        <pre><c:out value="${DocumentTypeForm.exportedXml}"/></pre>
      </div>
    </td>
  </tr>
      <td colspan="2" align="center" class="thnormal"  >
        <html-el:image property="methodToCall.export" src="${resourcePath}images/buttonsmall_export.gif" align="absmiddle" />&nbsp;&nbsp;&nbsp;&nbsp;
        <html-el:image property="methodToCall.exportHierarchy" src="${resourcePath}images/buttonsmall_exporthier.gif" align="absmiddle"/>
      </td>
    </tr>

</table>