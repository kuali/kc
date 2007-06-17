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
          <td nowrap class="tabtitle">Route Levels</td>
          <td width=100 align=right nowrap>
          	<c:choose>
          	<c:when test="${DocumentTypeForm.routeLevelVisible}" >
		          <html-el:image property="methodToCall.hide" src="images/tinybutton-hide.gif" onclick="document.forms[0].visibleSelected.value = 'routeLevel'"/>
	          </c:when>
	          <c:otherwise>
							<html-el:image property="methodToCall.show" src="images/tinybutton-show.gif" onclick="document.forms[0].visibleSelected.value = 'routeLevel'"/>
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
 	<c:when test="${DocumentTypeForm.routeLevelVisible}" >
        <html-el:hidden property="routeLevel.routeMethodName" />
        <html-el:hidden property="routeLevel.routeMethodCode" />
        <html-el:hidden property="newRouteModuleVisible" />
        <html-el:hidden property="ruleTemplate" />
		<table width="100%" border=0 cellspacing=0 cellpadding=0>
		  <tr>
		    <td width=8 class="bordercell-left"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
		    <td>
		      <table width="100%" border=0 cellspacing=0 cellpadding=0>
		        <tr>
		          <td align=right class="spacercell">&nbsp;</td>
		        </tr>
		      </table>
		   
		      <table width="100%" border=0 cellpadding=0 cellspacing=0 class="bord-r-t">   
		      <c:choose> 
		        <c:when test="${DocumentTypeForm.documentType.routeLevelsInherited}">
		        <c:forEach var="level" items="${DocumentTypeForm.documentType.parentDocType.routeLevels}" >
		        <tr>
		          <td width="33%" class="headercell3-b-l"><strong><c:out value="${level.routeLevelPriority}" /> <c:out value="${level.routeLevelName}" /></strong> (inherited)</td>
		          <td width="66%" class="headercell3-b-l">&nbsp;</td>
		        </tr>
		        <tr>
		          <td width="33%" align=right class="data-category">Route Level:</td>
		          <td width="66%" class="datacell"><span class="greyout-text"><c:out value="${level.routeLevelPriority}" /></span>&nbsp;</td>
		        </tr>
		        <tr>
		          <td width="33%" align=right class="data-category">Route Level Name:</td>
		          <td width="66%" class="datacell"><span class="greyout-text"><c:out value="${level.routeLevelName}" /></span>&nbsp;</td>
		        </tr>
		        <tr>
		          <td width="33%" align=right class="data-category">Route Method:</td>
		          <td width="66%" class="datacell"><span class="greyout-text"><c:out value="${level.routeMethodName}" /></span>&nbsp;</td>
		        </tr>
		        <tr>
		          <td width="33%" align=right class="data-category">Exception Workgroup:</td>
		          <td width="66%" class="datacell"><span class="greyout-text"><c:out value="${level.exceptionWorkgroupName}" />&nbsp;</td>
		        </tr>
		        <tr>
		          <td width="33%" align=right class="data-category">Final Approver:</td>
		          <td width="66%" class="datacell"><span class="greyout-text">
		             <c:out value="${level.finalApproval}" />&nbsp;</span>
		 				  </td>
		        </tr>
		        <tr>
		          <td width="33%" align=right class="data-category">Mandatory Route:</td>
		          <td width="66%" class="datacell"><span class="greyout-text"> 
		            <c:out value="${level.mandatoryRoute}" />&nbsp;</span>
		          </td>
		        </tr>
		        <tr>
		          <td width="33%" align=right class="data-category">Activation:</td>
		          <td width="66%" class="datacell"><span class="greyout-text">
		           <c:out value="${level.activationTypeValue}" />&nbsp;</span>
		          </td>
		        </tr>
		        </c:forEach>
		        </c:when>
		        
		        <c:otherwise>
				       <logic-el:iterate id="level" name="DocumentTypeForm" property="documentType.routeLevels" indexId="ctr">
		        <tr>
		          <td width="33%" class="headercell3-b-l"><strong><c:out value="${level.routeLevelPriority}" /> <c:out value="${level.routeLevelName}" /></strong></td>         
		          <td width="66%" class="headercell3-b-l" align="left">
		            <a href="javascript:setMethod('editRouteLevel'); setParamValue('routeLevelIndex', <c:out value="${ctr}" />); post_to_action('DocumentTypeForm', 'DocumentType.do')"> edit </a> &nbsp;&nbsp;&nbsp;&nbsp;  
		            <c:if test="${!level.exceptionRoute && !level.adHocRoute}">
		            <a href="javascript:setMethod('deleteRouteLevel'); setParamValue('routeLevelIndex', <c:out value="${ctr}" />); post_to_action('DocumentTypeForm', 'DocumentType.do');">delete </a>
		            </c:if>
		            
				 	      <c:if test="${DocumentTypeForm.routeLevelSize >1 && level.routeLevelPriority != -1 && level.routeLevelPriority != 0}">
					        <c:choose>
					          <c:when test="${ctr == 2}" >
					            <html-el:image src="images/arrow-down-large.gif" property="methodToCall.moveRouteLevelDown" onclick="document.forms[0].moveRouteLevel.value=${ctr}" alt="Move Route Level Down" />
					          </c:when>
					          <c:when test="${DocumentTypeForm.routeLevelSize == ctr-1}" >
					            <html-el:image src="images/arrow-up-large.gif" property="methodToCall.moveRouteLevelUp" onclick="document.forms[0].moveRouteLevel.value=${ctr}" alt="Move Route Level Up" />
					          </c:when>
					          <c:otherwise>
					            <html-el:image src="images/arrow-up-large.gif" property="methodToCall.moveRouteLevelUp" onclick="document.forms[0].moveRouteLevel.value=${ctr}" alt="Move Route Level Up" />
					            <html-el:image src="images/arrow-down-large.gif" property="methodToCall.moveRouteLevelDown" onclick="document.forms[0].moveRouteLevel.value=${ctr}" alt="Move Route Level Down" />
					          </c:otherwise>
					        </c:choose>
					      </c:if>
		            
		            <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].exceptionRoute" />
		            <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].adHocRoute" />
		          </td>
		        </tr>
		        <tr>
		          <td width="33%" align=right class="data-category">Route Level:</td>
		          <td width="66%" class="datacell"><span class="greyout-text"><c:out value="${level.routeLevelPriority}" /></span>&nbsp;</td>
		          <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].routeLevelPriority" /> 
		        </tr>
		        <tr>
		          <td width="33%" align=right class="data-category">Route Level Name:</td>
		          <td width="66%" class="datacell"><span class="greyout-text"><c:out value="${level.routeLevelName}" /></span>&nbsp;</td>
		          <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].routeLevelName" /> 
		        </tr>
		        <tr>
		          <td width="33%" align=right class="data-category">Route Method:</td>
		          <td width="66%" class="datacell"><span class="greyout-text"><c:out value="${level.routeMethodName}" /></span>&nbsp;</td>
		          <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].routeMethodName" /> 
		          <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].routeMethodCode" /> 
		        </tr>
		        <tr>
		          <td width="33%" align=right class="data-category">Exception Workgroup:</td>
		          <td width="66%" class="datacell"><span class="greyout-text"><c:out value="${level.exceptionWorkgroupName}" />&nbsp;</td>
		          <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].exceptionWorkgroupName" /> 
		          <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].exceptionWorkgroupId"/> 
		        </tr>
		        <tr>
		          <td width="33%" align=right class="data-category">Final Approver:</td>
		          <td width="66%" class="datacell"><span class="greyout-text"><c:out value="${level.finalApproval}" /></span>&nbsp;</td>
		          <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].finalApproval" />
		          <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].finalApprovalInd" /> 
		        </tr>
		        <tr>
		          <td width="33%" align=right class="data-category">Mandatory Route:</td>
		           <td width="66%" class="datacell"><span class="greyout-text"><c:out value="${level.mandatoryRoute}" /></span>&nbsp;</td>
		           <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].mandatoryRoute" /> 
		           <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].mandatoryRouteInd" /> 
		        </tr>
		        <tr>
		          <td width="33%" align=right class="data-category">Activation:</td>
		          <td width="66%" class="datacell"><span class="greyout-text"><c:out value="${level.activationTypeValue}" /></span>&nbsp;</td>
		           <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].activationTypeValue" /> 
		           <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].activationType" /> 
		        </tr>
    	        </logic-el:iterate>
		        </c:otherwise>
		        </c:choose>
					
		      <tr>
		        <td width="33%" class="headercell3-b-l"><strong>New Route Level</strong></td>
			    <td width="66%" align="right" class="headercell3-b-l"><html-el:image property="methodToCall.clearRouteLevel" src="images/tinybutton-clearfields.gif" /></td>
		      </tr>  
		      <tr>
		        <td width="33%" align=right class="data-category">Route Level:</td>
		        <td width="66%" class="datacell">     
		         <span class="greyout-text"><c:out value="${DocumentTypeForm.routeLevel.routeLevelPriority}" /> </span>
		         <html-el:hidden property="routeLevel.routeLevelPriority" />
		        <a href="javascript: workflowHelpPop('DocumentTypeRouteLevel')"><img src="images/my_cp_inf.gif" title="Help" alt="Help" hspace=10 vspace=5 border=0 align=absmiddle></a>
		        </td>
		      </tr>
		      
		      <tr>
		         <td width="33%" align=right class="data-category">*Route Level Name:</td>
		         <td width="66%" class="datacell">
		          <c:choose>
		          <c:when test="${DocumentTypeForm.routeLevel.exceptionRoute || DocumentTypeForm.routeLevel.adHocRoute}">
		          <span class="greyout-text"><c:out value="${DocumentTypeForm.routeLevel.routeLevelName}" /></span>
		          <html-el:hidden property="routeLevel.routeLevelName" />
		          </c:when>
		          <c:otherwise>
		          <html-el:text property="routeLevel.routeLevelName" />
		          </c:otherwise>
		        </c:choose>  
		        <a href="javascript: workflowHelpPop('DocumentTypeRouteLevelName')"><img src="images/my_cp_inf.gif" title="Help" alt="Help" hspace=10 vspace=5 border=0 align=absmiddle></a></td>
		       </tr>
		       
		       <tr>
		         <td width="33%" align=right class="data-category">*Route Method:</td>
		         <td width="66%" class="datacell">
		         <c:choose>
		         <c:when test="${DocumentTypeForm.routeLevel.exceptionRoute || DocumentTypeForm.routeLevel.adHocRoute}">
			      <span class="greyout-text"><c:out value="${DocumentTypeForm.routeLevel.routeMethodName}" /> </span>
		          </c:when>
		          <c:otherwise>	  
		            <table border=0 cellspacing=0 cellpadding=0>
		                  <tr id="newRouteModule" style="display:none">
		                    <td><html-el:text property="newRouteModuleName" size="60" />&nbsp;&nbsp;<a href="javascript:displayNewRouteModuleEntry('no');">cancel new</a></td>
		                  </tr>
		                  <tr id="routeMethod" style="display:inline">
		                    <td nowrap>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${Constants.ROUTE_LEVEL_METHOD_NAME_ROUTE_MODULE}"/>&nbsp; 
		                      <c:set var="routeModules" value="${DocumentTypeForm.routeModules}" scope="request" />
                              <html-el:select property="routeModuleName" onchange="if(this.value == 'addNewRM') {displayNewRouteModuleEntry('yes');}" >
                                <html-el:options collection="routeModules" labelProperty="value" property="key" filter="false"/>
                              </html-el:select>&nbsp;&nbsp;<a href="javascript: workflowHelpPop('RouteMethod')"><img src="images/my_cp_inf.gif" title="Help" alt="Help" hspace=10 vspace=5 border=0 align=absmiddle></a><br><br>	                
		                      &nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${Constants.ROUTE_LEVEL_METHOD_NAME_FLEX_RM}"/>&nbsp;<html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'RuleTemplateLookupableImplService';" />
                                &nbsp;<c:out value="${DocumentTypeForm.ruleTemplate}"/>
		                    </td>
		                  </tr>
		            </table>
		          </c:otherwise>
		         </c:choose> 
		         </td>  
		       </tr>
		       
		       <tr>
		         <td width="33%" align=right class="data-category">*Exception Workgroup:</td>
		         <td width="66%" class="datacell">
		           <html-el:text property="routeLevel.exceptionWorkgroupName" />
		           <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'WorkGroupLookupableImplService';document.forms[0].elements['lookupType'].value = 'exceptionWorkgroupId';" />
		         </td>
		       </tr>
		       <tr>
		         <td width="33%" align=right class="data-category">*Final Approver:</td>
		         <td width="66%" class="datacell">
		         <c:choose>
		        <c:when test="${DocumentTypeForm.routeLevel.exceptionRoute || DocumentTypeForm.routeLevel.adHocRoute}">
		         <span class="greyout-text"><c:out value="${DocumentTypeForm.routeLevel.finalApproval}" /> </span>
		          <html-el:hidden property="routeLevel.finalApproval" />
		        </c:when>
		        <c:otherwise>
		           <table border=0 cellspacing=0 cellpadding=0>
		             <tr>
		               <td nowrap>
		                 <html-el:radio property="routeLevel.finalApproval" value="Yes"/> Yes
		               </td>
		               <td rowspan=2><a href="javascript: helpPop(639)"><img src="images/my_cp_inf.gif" title="Help" alt="Help" hspace=10 vspace=5 border=0 align=absmiddle></a></td>
		             </tr>
		             <tr>
		               <td nowrap>
		                 <html-el:radio property="routeLevel.finalApproval" value="No"/> No
		               </td>
		               <td>&nbsp;</td>
		             </tr>
		           </table>
		         </c:otherwise>
		         </c:choose>
		         </td>
		       </tr>
		       <tr>
		         <td width="33%" align=right class="data-category">*Mandatory Route:</td>
		         <td width="66%" class="datacell">
		         <c:choose>
		         <c:when test="${DocumentTypeForm.routeLevel.exceptionRoute || DocumentTypeForm.routeLevel.adHocRoute}">
		         <span class="greyout-text"><c:out value="${DocumentTypeForm.routeLevel.mandatoryRoute}" /> </span>
		          <html-el:hidden property="routeLevel.mandatoryRoute" />
		        </c:when>
		        <c:otherwise>
		           <table border=0 cellspacing=0 cellpadding=0>
		             <tr>
		               <td nowrap>
		                 <html-el:radio property="routeLevel.mandatoryRoute" value="Yes" />Yes
		               </td>
		               <td rowspan=2><a href="javascript: helpPop(640)"><img src="images/my_cp_inf.gif" title="Help" alt="Help" hspace=10 vspace=5 border=0 align=absmiddle></a></td>
		             </tr>
		             <tr>
		               <td nowrap>
		                 <html-el:radio property="routeLevel.mandatoryRoute" value="No"/> No
		               </td>
		               <td>&nbsp;</td>
		             </tr>
		           </table>
		         </c:otherwise>
		         </c:choose>
		         </td>
		       </tr>
		       <tr>
		         <td width="33%" align=right class="data-category">*Activation:</td>
		         <td width="66%" class="datacell">
		         <c:choose>
		         <c:when test="${DocumentTypeForm.routeLevel.exceptionRoute || DocumentTypeForm.routeLevel.adHocRoute}">
		         <span class="greyout-text"><c:out value="${DocumentTypeForm.routeLevel.activationTypeValue}" /> </span>
		          <html-el:hidden property="routeLevel.activationTypeValue" />
		        </c:when>
		        <c:otherwise>
		           <table border=0 cellspacing=0 cellpadding=0>
		             <tr>
		               <td nowrap>
		                 <html-el:radio property="routeLevel.activationTypeValue" value="Sequential" />Sequential
		               </td>
		               <td rowspan=2><a href="javascript: helpPop(641)"><img src="images/my_cp_inf.gif" title="Help" alt="Help" hspace=10 vspace=5 border=0 align=absmiddle></a></td>
		             </tr>
		             <tr>
		               <td nowrap>
		                 <html-el:radio property="routeLevel.activationTypeValue" value="Parallel" />Parallel
		               </td>
		               <td>&nbsp;</td>
		             </tr>
		           </table>
		         </c:otherwise>
		         </c:choose>
		         </td>
		       </tr>
		      </table>  
		              
		      <table width="100%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		          <td class="spacercell">&nbsp;</td>
		        </tr>
		      </table>
		        
		      <table width="100%" border=0 cellspacing=0 cellpadding=0>
		        <tr>
		          <td class="spacercell">
		            <div align=center>
		            <c:choose>
		             <c:when test="${DocumentTypeForm.editingRouteLevelInd}" >
		              <html-el:image property="methodToCall.saveRouteLevel" src="images/tinybutton-savertlvl.gif" /> &nbsp; &nbsp;
				           <c:if test="${DocumentTypeForm.documentType.parentDocType != null && !DocumentTypeForm.documentType.routeLevelsInherited}">
			  		           <html-el:image property="methodToCall.restoreInheritedRouteLevels" src="images/tinybutton-restoreinhrtlvl.gif" />
		      	       </c:if>
		              <html-el:image property="methodToCall.cancelEditRouteLevel" src="images/tinybutton-cancel.gif" />
		             </c:when>
		             <c:otherwise>
		  	           <html-el:image property="methodToCall.addRouteLevel" src="images/tinybutton-addroutelevel.gif" />
				           <c:if test="${DocumentTypeForm.documentType.parentDocType != null && !DocumentTypeForm.documentType.routeLevelsInherited}">
			  		           <html-el:image property="methodToCall.restoreInheritedRouteLevels" src="images/tinybutton-restoreinhrtlvl.gif" />
		      	       </c:if>
		             </c:otherwise>
		            </c:choose>
		            </div>
		          </td>
		        </tr>
		      </table>
		    </td>
		    <td width=8 class="bordercell-right"><img src="images/pixel_clear.gif" alt="" width="8" height="8"></td>
		  </tr>
		</table>
	</c:when>
	<c:otherwise>
		<c:if test="${!DocumentTypeForm.documentType.routeLevelsInherited}">
	      <logic-el:iterate id="level" name="DocumentTypeForm" property="documentType.routeLevels" indexId="ctr">
		  <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].exceptionRoute" />
	      <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].adHocRoute" />
          <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].routeLevelPriority" /> 
          <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].routeLevelName" /> 
          <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].routeMethodName" /> 
          <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].routeMethodCode" /> 
          <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].exceptionWorkgroupName" /> 
          <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].exceptionWorkgroupId"/> 
          <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].finalApproval" />
          <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].finalApprovalInd" /> 
          <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].mandatoryRoute" /> 
          <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].mandatoryRouteInd" /> 
          <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].activationTypeValue" /> 
          <html-el:hidden property="documentType.documentTypeRouteLevel[${ctr}].activationType" /> 
          </logic-el:iterate>
		</c:if>
	    <html-el:hidden property="routeLevel.routeLevelPriority" />
        <html-el:hidden property="routeLevel.routeLevelName" />
        <html-el:hidden property="routeLevel.routeMethodName" />
        <html-el:hidden property="routeLevel.routeMethodCode" />
        <html-el:hidden property="routeLevel.exceptionWorkgroupName" />
        <html-el:hidden property="routeLevel.finalApproval" />
        <html-el:hidden property="routeLevel.mandatoryRoute" />
        <html-el:hidden property="routeLevel.activationTypeValue" />
        <html-el:hidden property="newRouteModuleVisible" />
        <html-el:hidden property="newRouteModuleName" />
        <html-el:hidden property="routeModuleName" />
        <html-el:hidden property="ruleTemplate" />
	</c:otherwise>
</c:choose>
