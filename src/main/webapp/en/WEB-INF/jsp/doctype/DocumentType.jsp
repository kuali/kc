<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<table width="100%" border=0 cellspacing=0 cellpadding=0>
  <tr>
    <td width=12><img src="images/tab-topleft.gif" alt="" width=12 height=29></td>
    <td width=200 nowrap background="images/tab-back.gif">
	  <table width="100%" border=0 cellspacing=0 cellpadding=0>
		<tr>
          <td nowrap class="tabtitle">Document Type</td>
          <td width=100 align=right nowrap>
          	<c:choose>
          	<c:when test="${DocumentTypeForm.docTypeVisible}" >
		          <html-el:image property="methodToCall.hide" src="images/tinybutton-hide.gif" onclick="document.forms[0].visibleSelected.value = 'docType'"/>
	          </c:when>
	          <c:otherwise>
							<html-el:image property="methodToCall.show" src="images/tinybutton-show.gif" onclick="document.forms[0].visibleSelected.value = 'docType'"/>
						</c:otherwise>
						</c:choose>
          </td>
        </tr>
      </table>
    </td>
    <td width=15><img src="images/tab-bevel.gif" alt="" width=15 height=29></td>
    <td width="95%" align=right valign=top background="images/tab-rightback.gif"><img src="images/tab-topright.gif" alt="" width=12 height=29 align=top></td>
  </tr>
</table>
<c:choose>
 	<c:when test="${DocumentTypeForm.docTypeVisible}" >
		<table width="100%" border=0 cellspacing=0 cellpadding=0>
		  <tr>
		    <td width=8 class="bordercell-left"><img src="images/pixel_clear.gif" alt="" width="8" height="8"></td>
		    <td>
		      <table width="100%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		          <td align="right" class="spacercell">
		          	<html-el:image property="methodToCall.clearDocType" src="images/tinybutton-clearfields.gif" />
		          </td>
		        </tr>
		      </table>
		      <table width="100%" border=0 cellpadding=0 cellspacing=0 class="bord-r-t">
		      <c:if test="${DocumentTypeForm.existingDocumentType != null}">
		  		<tr>
		          <td width="33%" class="headercell3-b-l">&nbsp;</td>
		          <td width="33%" align=left class="headercell2">Existing</td>
		          <td width="33%" align=left class="headercell2">New</td>
		        </tr>
		      </c:if>
			    <tr>
		          <td width="33%" align=right class="data-category">*Document Type Name:<br></td>
		          <c:if test="${DocumentTypeForm.existingDocumentType != null}">
		  	        <td width="33%" class="datacell"><span class="greyout-text"><c:out value="${DocumentTypeForm.existingDocumentType.name}" /></span>&nbsp;</td>
			        </c:if>
		          <td class="datacell">
		            <c:choose>
		             <c:when test="${DocumentTypeForm.existingDocumentType != null}">
												<html-el:hidden property="documentType.name" /><c:out value="${DocumentTypeForm.documentType.name}" />
									</c:when>
									<c:otherwise>
										<html-el:text property="documentType.name" />&nbsp;<a href="javascript: workflowHelpPop('DocumentTypeName')"><img src="images/my_cp_inf.gif" title="Help" alt="Help" border=0 align=absmiddle></a>
									</c:otherwise>
								</c:choose>
			     	  </td>
		        </tr>
			    <tr>
		          <td width="33%" align=right nowrap class="data-category">*Document Type Label:<br></td>
		          <c:if test="${DocumentTypeForm.existingDocumentType != null}">
		            <td width="33%" class="datacell"><span class="greyout-text"><c:out value="${DocumentTypeForm.existingDocumentType.label}" /></span>&nbsp;</td>
		          </c:if>
		 			    <td class="datacell">
					      <html-el:text property="documentType.label" />&nbsp;<a href="javascript: workflowHelpPop('DocumentTypeLabel')"><img src="images/my_cp_inf.gif" title="Help" alt="Help" border=0 align=absmiddle></a>
					    </td>
		     </tr>

		        <tr>
		          <td width="33%" height=25 align=right class="data-category">Parent Document Type Name:<br></td>
		          <c:if test="${DocumentTypeForm.existingDocumentType != null}">
		            <c:choose>
		             <c:when test="${DocumentTypeForm.existingDocumentType.parentDocType != null}">
		               <td width="33%" class="datacell"><span class="greyout-text"><c:out value="${DocumentTypeForm.existingDocumentType.parentDocType.name}" /></span></td>
		             </c:when>
		             <c:otherwise>
		              <td width="33%" class="datacell">&nbsp;
		             </c:otherwise>
		            </c:choose>
		          </c:if>
		            <td height=25 nowrap class="datacell">
		              <html-el:hidden property="parentDocTypeName" />
		              <c:out value="${DocumentTypeForm.parentDocTypeName}" />&nbsp;
		              <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'DocumentTypeLookupableImplService';" />
		              <c:if test="${DocumentTypeForm.documentType.parentDocType != null}">
		              	<html-el:image property="methodToCall.clearParentDocType" src="images/buttonsmall_clear.gif"/>
		     	 	  		</c:if>
		     	  	  <a href="javascript: workflowHelpPop('ParentDocumentTypeName')"><img src="images/my_cp_inf.gif" alt="Help" hspace="5" border=0 align=absmiddle title="Help"></a>
		            </td>
		        </tr>

		        <tr>
		          <td width="33%" height=25 align=right class="data-category">*Super User Workgroup:</td>
		          <c:if test="${DocumentTypeForm.existingDocumentType != null}">
		            <td width="33%" class="datacell"><span class="greyout-text"><c:out value="${DocumentTypeForm.existingDocumentType.superUserWorkgroupName}" /></span>&nbsp;</td>
		          </c:if>
				 			 <td height=25 class="datacell">
		           <html-el:text property="documentType.superUserWorkgroupName" />&nbsp;<html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'WorkGroupLookupableImplService';document.forms[0].elements['lookupType'].value = 'superUserWorkgroupId';" />
		           <a href="javascript: workflowHelpPop('SuperUserWorkgroup')"><img src="images/my_cp_inf.gif" title="Help" alt="Help" border=0 align=absmiddle></a>
				 </td>
		       </tr>
		        <tr>
		          <td width="33%" height=25 align=right class="data-category">*Blanket Approve Workgroup:</td>
		          <c:if test="${DocumentTypeForm.existingDocumentType != null}">
		            <td width="33%" class="datacell"><span class="greyout-text"><c:out value="${DocumentTypeForm.existingDocumentType.blanketApproveWorkgroupName}" /></span>&nbsp;</td>
		          </c:if>
				        <td height=25 class="datacell">
			           <html-el:text property="documentType.blanketApproveWorkgroupName" />&nbsp;<html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'WorkGroupLookupableImplService';document.forms[0].elements['lookupType'].value = 'blanketApproveWorkgroupId';" />
			           <a href="javascript: workflowHelpPop('BlanketApproveWorkgroup')"><img src="images/my_cp_inf.gif" title="Help" alt="Help" border=0 align=absmiddle></a>
					 			</td>
		       </tr>

		        <tr>
		          <td width="33%" align=right class="data-category">*Postprocessor Name:<br></td>
		          <c:if test="${DocumentTypeForm.existingDocumentType != null}">
		            <td width="33%" class="datacell"><span class="greyout-text"><c:out value="${DocumentTypeForm.existingDocumentType.postProcessorName}" /></span>&nbsp;</td>
		          </c:if>
		            <td class="datacell">
		              <html-el:text property="documentType.postProcessorName" />&nbsp;
		              <a href="javascript: workflowHelpPop('PostProcessorName')"><img src="images/my_cp_inf.gif" title="Help" alt="Help" border=0 align=absmiddle></a>
		            </td>
		        </tr>

		        <tr>
		          <td width="33%" align=right class="data-category">*DocHandler URL:<br></td>
		          <c:if test="${DocumentTypeForm.existingDocumentType != null}">
		            <td width="33%" class="datacell"><span class="greyout-text"><c:out value="${DocumentTypeForm.existingDocumentType.docHandlerUrl}" /></span>&nbsp;</td>
		          </c:if>
		            <td class="datacell">
		              <html-el:text property="documentType.docHandlerUrl" />&nbsp;
		              <a href="javascript: workflowHelpPop('DocHandlerURL')"><img src="images/my_cp_inf.gif" title="Help" alt="Help" border=0 align=absmiddle></a>
		            </td>
		          </tr>

		          <tr>
		            <td width="33%" align=right class="data-category">Document Type JNDI URL:</td>
		            <c:if test="${DocumentTypeForm.existingDocumentType != null}">
		              <td width="33%" class="datacell"><span class="greyout-text"><c:out value="${DocumentTypeForm.existingDocumentType.postProcessorJndiUrl}" /></span>&nbsp;</td>
		            </c:if>
		              <td class="datacell">
		                <html-el:text property="documentType.postProcessorJndiUrl" />&nbsp;
		                <a href="javascript: workflowHelpPop('DocumentTypeJNDIURL')"><img src="images/my_cp_inf.gif" title="Help" alt="Help" border=0 align=absmiddle></a>
		              </td>
		          </tr>

		          <tr>
		            <td width="33%" align=right valign="top" class="data-category">Custom Action List Attribute Class Name:</td>
		            <c:if test="${DocumentTypeForm.existingDocumentType != null}">
		              <td width="33%" valign="top" class="datacell"><span class="greyout-text"><c:out value="${DocumentTypeForm.existingDocumentType.customActionListAttributeClassName}" /></span>&nbsp;</td>
		            </c:if>
		              <td class="datacell">
		                <html-el:text property="documentType.customActionListAttributeClassName" />&nbsp;
		                <a href="javascript: workflowHelpPop('CustomActionListAttributeClassName')"><img src="images/my_cp_inf.gif" title="Help" alt="Help" border=0 align=absmiddle></a>
		              </td>
		          </tr>

		          <tr>
		            <td width="33%" align=right valign="top" class="data-category">Custom Email Attribute Class Name:</td>
		            <c:if test="${DocumentTypeForm.existingDocumentType != null}">
		              <td width="33%" valign="top" class="datacell"><span class="greyout-text"><c:out value="${DocumentTypeForm.existingDocumentType.customEmailAttributeClassName}" /></span>&nbsp;</td>
		            </c:if>
		              <td class="datacell">
		                <html-el:text property="documentType.customEmailAttributeClassName" />&nbsp;
		                <a href="javascript: workflowHelpPop('CustomEmailAttributeClassName')"><img src="images/my_cp_inf.gif" title="Help" alt="Help" border=0 align=absmiddle></a>
		              </td>
		          </tr>

		          <tr>
		            <td width="33%" align=right valign="top" class="data-category">Custom Note Attribute Class Name:</td>
		            <c:if test="${DocumentTypeForm.existingDocumentType != null}">
		              <td width="33%" valign="top" class="datacell"><span class="greyout-text"><c:out value="${DocumentTypeForm.existingDocumentType.customNoteAttributeClassName}" /></span>&nbsp;</td>
		            </c:if>
		              <td class="datacell">
		                <html-el:text property="documentType.customNoteAttributeClassName" />&nbsp;
		                <a href="javascript: workflowHelpPop('CustomNoteAttributeClassName')"><img src="images/my_cp_inf.gif" title="Help" alt="Help" border=0 align=absmiddle></a>
		              </td>
		          </tr>

		          <tr>
		            <td width="33%" align=right class="data-category">*Active:</td>
		            <c:if test="${DocumentTypeForm.existingDocumentType != null}">
		              <td width="33%" class="datacell"><span class="greyout-text"><c:out value="${DocumentTypeForm.existingDocumentType.docTypeActiveIndicatorDisplayValue}" /></span>&nbsp;</td>
		            </c:if>
		            <td class="datacell">
		              <table border=0 cellspacing=0 cellpadding=0>
		                <tr>
		                  <td nowrap>
		                    <html-el:radio property="documentType.activeInd" value="true"/>&nbsp;<bean-el:message key="general.label.active"/>
		                  </td>
		                  <td rowspan=2><a href="javascript: workflowHelpPop('DocumentTypeActiveIndicator')"><img src="images/my_cp_inf.gif" title="Help" alt="Help" hspace=10 vspace=5 border=0 align=absmiddle></a></td>
		                </tr>
		                <tr>
		                  <td nowrap>
		                    <html-el:radio property="documentType.activeInd" value="false"/>&nbsp;<bean-el:message key="general.label.inactive"/>
		                  </td>
		                </tr>
		              </table>
		            </td>
		          </tr>


		          <tr>
		            <td width="33%" align=right valign="top" class="data-category">Description:</td>
		            <c:if test="${DocumentTypeForm.existingDocumentType != null}">
		              <td width="33%" valign="top" class="datacell"><span class="greyout-text"><c:out value="${DocumentTypeForm.existingDocumentType.description}" /></span>&nbsp;</td>
		            </c:if>
		              <td class="datacell">
		                <table border=0 cellspacing=0 cellpadding=0>
		                  <tr>
		                    <td>
		                      <html-el:textarea property="documentType.description" cols="30" rows="3" />&nbsp;
		                    </td>
		                    <td><a href="javascript: workflowHelpPop('DocumentTypeDescription')"><img src="images/my_cp_inf.gif" title="Help" alt="Help" hspace=4 border=0 align=absmiddle></a></td>
		                  </tr>
		                </table>
		              </td>
		          </tr>
		        </table>

		        <table width="100%" border="0" cellspacing="0" cellpadding="0">
		          <tr>
		            <td class="spacercell">&nbsp;</td>
		          </tr>
		        </table>
		      </td>
		      <td width=8 class="bordercell-right"><img src="images/pixel_clear.gif" alt="" width="8" height="8"></td>
		    </tr>
		  </table>
	</c:when>
	<c:otherwise>
		<html-el:hidden property="documentType.name" />
		<html-el:hidden property="documentType.label" />
		<html-el:hidden property="parentDocTypeName" />
		<html-el:hidden property="documentType.superUserWorkgroupName" />
		<html-el:hidden property="documentType.blanketApproveWorkgroupName" />
		<html-el:hidden property="documentType.postProcessorName" />
		<html-el:hidden property="documentType.docHandlerUrl" />
		<html-el:hidden property="documentType.postProcessorJndiUrl" />
		<html-el:hidden property="documentType.advancedDocSearchUrl" />
		<html-el:hidden property="documentType.customActionListAttributeClassName" />
		<html-el:hidden property="documentType.customEmailAttributeClassName" />
		<html-el:hidden property="documentType.customNoteAttributeClassName" />
		<html-el:hidden property="documentType.activeInd"/>
		<html-el:hidden property="documentType.description" />
	</c:otherwise>
</c:choose>
