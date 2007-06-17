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
          <td nowrap class="tabtitle">Searchable Attributes</td>
          <td width=100 align=right nowrap>
          	<c:choose>
          	<c:when test="${DocumentTypeForm.searchableAttributeVisible}" >
		          <html-el:image property="methodToCall.hide" src="images/tinybutton-hide.gif" onclick="document.forms[0].visibleSelected.value = 'searchableAttribute'"/>
	          </c:when>
	          <c:otherwise>
							<html-el:image property="methodToCall.show" src="images/tinybutton-show.gif" onclick="document.forms[0].visibleSelected.value = 'searchableAttribute'"/>
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
 	<c:when test="${DocumentTypeForm.searchableAttributeVisible}" >
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
		        <c:when test="${DocumentTypeForm.documentType.searchableAttributesInherited}">
		        <c:forEach var="searchableAttribute" items="${DocumentTypeForm.documentType.parentDocType.documentTypeAttributes}" >
                <c:set var="attribute" value="${searchableAttribute.ruleAttribute}" />
		        <tr>
		          <td width="33%" class="headercell3-b-l"><strong><c:out value="${attribute.name}" /></strong> (inherited)</td>
		          <td width="66%" class="headercell3-b-l">&nbsp;</td>
		        </tr>
                <tr>
                  <td width="33%" align=right class="data-category">Attribute Name:</td>
                  <td width="66%" class="datacell"><span class="greyout-text"><c:out value="${attribute.name}" /></span>&nbsp;</td>
                </tr>
		        <tr>
		          <td width="33%" align=right class="data-category">Class Name:</td>
		          <td width="66%" class="datacell"><span class="greyout-text"><c:out value="${attribute.className}" /></span>&nbsp;</td>
		        </tr>
		        </c:forEach>
		        </c:when>
		        
		        <c:otherwise>
			        <logic-el:iterate id="searchableAttribute" name="DocumentTypeForm" property="documentType.documentTypeAttributes" indexId="ctr">
                    <c:set var="attribute" value="${searchableAttribute.ruleAttribute}" />
		        <tr>
		          <td width="33%" class="headercell3-b-l"><strong><c:out value="${attribute.name}" /></strong></td>         
		          <td width="66%" class="headercell3-b-l" align="left">
		            <a href="javascript:setMethod('deleteSearchableAttribute'); setParamValue('searchableAttributeIndex', <c:out value="${ctr}" />); post_to_action('DocumentTypeForm', 'DocumentType.do');">delete </a>
                    <html-el:hidden property="documentType.documentTypeAttribute[${ctr}].documentTypeAttributeId" /> 
                    <html-el:hidden property="documentType.documentTypeAttribute[${ctr}].documentTypeId" /> 
                    <html-el:hidden property="documentType.documentTypeAttribute[${ctr}].ruleAttributeId" /> 
		          </td>
		        </tr>
                <tr>
                  <td width="33%" align=right class="data-category">Attribute Name:</td>
                  <td width="66%" class="datacell"><span class="greyout-text"><c:out value="${attribute.name}" /></span>&nbsp;</td>
                  <%-- TODO is this necessary since we have the hidden ruleAttribute property above?
                  <html-el:hidden property="documentType.documentTypeAttribute[${ctr}].ruleAttribute.name" /> 
                   --%>
                </tr>
		        <tr>
		          <td width="33%" align=right class="data-category">Class Name:</td>
		          <td width="66%" class="datacell"><span class="greyout-text"><c:out value="${attribute.className}" /></span>&nbsp;</td>
                  <%-- TODO is this necessary since we have the hidden ruleAttribute property above?
		          <html-el:hidden property="documentType.documentTypeAttribute[${ctr}].ruleAttribute.className" /> 
                   --%>
		        </tr>
		      
		        </logic-el:iterate>
		        </c:otherwise>
		        </c:choose>
					
		      <tr>
		        <td width="33%" class="headercell3-b-l"><strong>New Searchable Attribute</strong></td>
			    <td width="66%" align="right" class="headercell3-b-l"><html-el:image property="methodToCall.clearSearchableAttribute" src="images/tinybutton-clearfields.gif" /></td>
		      </tr>  
		      <tr>
		         <td width="33%" align=right class="data-category">*Attribute Name:</td>
		         <td width="66%" class="datacell">
		            <table border=0 cellspacing=0 cellpadding=0>
		              <tr>
		                <td rowspan=2 nowrap>
                          <c:if test="${DocumentTypeForm.searchableAttribute != null}">
			                  <c:out value="${DocumentTypeForm.searchableAttribute.name}"/>
                              <html-el:hidden property="searchableAttribute.ruleAttributeId" />
			                  <html-el:hidden property="searchableAttribute.name" />
			                  <html-el:hidden property="searchableAttribute.type" value="SearchableAttribute"/>
                          </c:if>
                          &nbsp;
		                </td>
		                <td nowrap>&nbsp;&nbsp;&nbsp;&nbsp;<html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'RuleAttributeLookupableImplService';" /></td>
		                <td><a href="javascript: workflowHelpPop('DocumentTypeSearchableAttributeName')"><img src="images/my_cp_inf.gif" title="Help" alt="Help" hspace=10 vspace=5 border=0 align=absmiddle></a></td>
		              </tr>
		            </table>
                </td>
		       </tr>
              <tr>
                 <td width="33%" align=right class="data-category">*Class Name:</td>
                 <td width="66%" class="datacell">
                    <table border=0 cellspacing=0 cellpadding=0>
                      <tr>
                        <td rowspan=2 nowrap>
                          <c:if test="${DocumentTypeForm.searchableAttribute != null}">
                              <c:out value="${DocumentTypeForm.searchableAttribute.className}"/>
                              <html-el:hidden property="searchableAttribute.className" />
                          </c:if>
                          &nbsp;
                        </td>
                        <td nowrap colspan="2">&nbsp;</td>
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
		        
		      <table width="100%" border=0 cellspacing=0 cellpadding=0>
		        <tr>
		          <td class="spacercell">
		            <div align=center>
		  	           <html-el:image property="methodToCall.addSearchableAttribute" src="images/tinybutton-addattrib.gif" />
				           <c:if test="${DocumentTypeForm.documentType.parentDocType != null && !DocumentTypeForm.documentType.searchableAttributesInherited}">
			  		           <html-el:image property="methodToCall.restoreInheritedSearchableAttributes" src="images/tinybutton-restinherattri.gif" />
		      	       </c:if>
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
		<c:if test="${!DocumentTypeForm.documentType.searchableAttributesInherited}">
	    <logic-el:iterate id="level" name="DocumentTypeForm" property="documentType.documentTypeAttributes" indexId="ctr">
        <html-el:hidden property="documentType.documentTypeAttribute[${ctr}].documentTypeAttributeId" /> 
        <html-el:hidden property="documentType.documentTypeAttribute[${ctr}].documentTypeId" /> 
        <html-el:hidden property="documentType.documentTypeAttribute[${ctr}].ruleAttributeId" /> 
	    </logic-el:iterate>
		</c:if>
        <html-el:hidden property="searchableAttribute.ruleAttributeId" />
		<html-el:hidden property="searchableAttribute.name" />
        <html-el:hidden property="searchableAttribute.className" />
        <html-el:hidden property="searchableAttribute.type" />
	</c:otherwise>
</c:choose>
