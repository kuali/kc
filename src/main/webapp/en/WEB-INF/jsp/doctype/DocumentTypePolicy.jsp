<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<table width="100%" border=0 cellspacing=0 cellpadding=0>
  <tr>
    <td width=12><img src="images/tab-topleft1.gif" alt="" width=12 height=27></td>
    <td width=200 nowrap background="images/tab-back.gif">
      <table width="100%" border=0 cellspacing=0 cellpadding=0>
        <tr>
		  		<td nowrap class="tabtitle">Policies </td>
          <td width=100 align=right nowrap>
          	<c:choose>
          	<c:when test="${DocumentTypeForm.policyVisible}" >
		          <html-el:image property="methodToCall.hide" src="images/tinybutton-hide.gif" onclick="document.forms[0].visibleSelected.value = 'policy'"/>
	          </c:when>
	          <c:otherwise>
							<html-el:image property="methodToCall.show" src="images/tinybutton-show.gif" onclick="document.forms[0].visibleSelected.value = 'policy'"/>
						</c:otherwise>
						</c:choose>
          </td>
        </tr>
      </table>
    </td>
    <td width=15><img src="images/tab-bevel1.gif" alt="" width=15 height=27></td>
    <td width="95%" align=right background="images/tab-rightback1.gif"><img src="images/tab-topright1.gif" alt="" width=12 height=27></td>
  </tr>
</table>
<c:choose>
 	<c:when test="${DocumentTypeForm.policyVisible}" >
		<table width="100%" border=0 cellspacing=0 cellpadding=0>
		  <tr>
		    <td width=8 class="bordercell-left"><img src="images/pixel_clear.gif" alt="" width="8" height="8"></td>
		    <td>
		      <table width="100%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		          <td align="right" class="spacercell">
		    				<c:if test="${DocumentTypeForm.documentType.parentDocType != null}">
			            <html-el:image property="methodToCall.restoreInheritedPolicies" src="images/tinybutton-resetinhval.gif" />
			          </c:if>
		          </td>
		        </tr>
		      </table>
		
		      <table width="100%" border=0 cellpadding=0 cellspacing=0 class="bord-r-t">
 	            <tr>
		          <td width="33%" align=right class="data-category">*Default Approve:</td>
		          <c:if test="${DocumentTypeForm.existingDocumentType != null}">
		            <td width="33%" class="datacell"><span class="greyout-text"><c:out value="${DocumentTypeForm.existingDocumentType.defaultApprovePolicyDisplayValue}" /></span></td>
		          </c:if>
		            <td class="datacell">
		              <table border=0 cellspacing=0 cellpadding=0>
		               <c:if test="${DocumentTypeForm.documentType.parentDocType != null}" >
		                <tr>
		                  <td nowrap>
		                    <html-el:radio property="defaultApprove" value="I"/>&nbsp;<bean-el:message key="general.label.inherited"/> (value: <c:out value="${DocumentTypeForm.documentType.parentDocType.defaultApprovePolicyDisplayValue}"/>)
		                  </td>
		                </tr>
		               </c:if>
		                <tr>
										  <td nowrap>
										    <html-el:radio property="defaultApprove" value="true"/>&nbsp;<bean-el:message key="general.label.active"/>
										  </td>
										  <td rowspan=2><a href="javascript: workflowHelpPop('DocumentTypeDefaultApprovePolicy')"><img src="images/my_cp_inf.gif" title="Help" alt="Help" hspace=10 vspace=5 border=0 align=absmiddle></a></td>
										</tr>
										<tr>
										  <td nowrap>
										    <html-el:radio property="defaultApprove" value="false"/>&nbsp;<bean-el:message key="general.label.inactive"/>
											</td>
		                </tr>
		              </table>
		            </td>
		        </tr>
		
		        <tr>
		          <td width="33%" align=right class="data-category">*Pre Approve:</td>
		          <c:if test="${DocumentTypeForm.existingDocumentType != null}">
		            <td width="33%" class="datacell"><span class="greyout-text"><c:out value="${DocumentTypeForm.existingDocumentType.preApprovePolicyDisplayValue}" /></span></td>
		          </c:if>
		            <td class="datacell">
		              <table border=0 cellspacing=0 cellpadding=0>
									<c:if test="${DocumentTypeForm.documentType.parentDocType != null}" >
		                <tr>
		                  <td nowrap>
		                    <html-el:radio property="preApprove" value="I"/>&nbsp;<bean-el:message key="general.label.inherited"/> (value: <c:out value="${DocumentTypeForm.documentType.parentDocType.preApprovePolicyDisplayValue}"/>)
		                  </td>
		                </tr>
		              </c:if>
		                <tr>
								  		<td nowrap>
								   		 <html-el:radio property="preApprove" value="true"/>&nbsp;<bean-el:message key="general.label.active"/>
								 		</td>
								 		 <td rowspan=2><a href="javascript: workflowHelpPop('DocumentTypePreApprovePolicy')"><img src="images/my_cp_inf.gif" title="Help" alt="Help" hspace=10 vspace=5 border=0 align=absmiddle></a></td>
										</tr>
										<tr>
										  <td nowrap>
										    <html-el:radio property="preApprove" value="false"/>&nbsp;<bean-el:message key="general.label.inactive"/>
										  </td>
		                </tr>
		              </table>
		            </td>
		        </tr>
		        
		        
		        
		        
		        <tr>
		          <td width="33%" align=right class="data-category">*Initiator Must Route:</td>
		          <c:if test="${DocumentTypeForm.existingDocumentType != null}">
		            <td width="33%" class="datacell"><span class="greyout-text"><c:out value="${DocumentTypeForm.existingDocumentType.initiatorMustRouteDisplayValue}" /></span></td>
		          </c:if>
		            <td class="datacell">
		              <table border=0 cellspacing=0 cellpadding=0>
		               <c:if test="${DocumentTypeForm.documentType.parentDocType != null}" >
		                <tr>
		                  <td nowrap>
		                    <html-el:radio property="initiatorMustRoute" value="I"/>&nbsp;<bean-el:message key="general.label.inherited"/> (value: <c:out value="${DocumentTypeForm.documentType.parentDocType.initiatorMustRouteDisplayValue}"/>)
		                  </td>
		                </tr>
		               </c:if>
		                <tr>
										  <td nowrap>
										    <html-el:radio property="initiatorMustRoute" value="true"/>&nbsp;<bean-el:message key="general.label.active"/>
										  </td>
										  <td rowspan=2><a href="javascript: workflowHelpPop('InitiatorMustRoute')"><img src="images/my_cp_inf.gif" title="Help" alt="Help" hspace=10 vspace=5 border=0 align=absmiddle></a></td>
										</tr>
										<tr>
										  <td nowrap>
										    <html-el:radio property="initiatorMustRoute" value="false"/>&nbsp;<bean-el:message key="general.label.inactive"/>
											</td>
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
		<html-el:hidden property="defaultApprove" />
		<html-el:hidden property="preApprove" />
		<html-el:hidden property="initiatorMustRoute" />
	</c:otherwise>
</c:choose>
