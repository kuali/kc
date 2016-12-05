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
<%@ attribute name="helpParameterNamespace" required="false" %>
<%@ attribute name="helpParameterDetailType" required="false" %>
<%@ attribute name="helpParameterName" required="false" %>
	<script type="text/javascript" src="scripts/jquery/jquery.treeview.js"></script>
	<link rel="stylesheet" href="css/medusa.css" type="text/css" />
	<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
	<link rel="stylesheet" href="css/jquery/kuali-stylesheet.css" type="text/css" />
	<link rel="stylesheet" href="css/jquery/jquery.treeview.css" type="text/css" />
	<script type="text/javascript">
	   var $jq = jQuery.noConflict();
	</script>	
	
<div class="tab-container"  align="center">
    <c:if test="${! empty helpParameterNamespace and ! empty helpParameterDetailType and ! empty helpParameterName}">
	<span class="subhead-right">
   		<kul:help parameterNamespace="${helpParameterNamespace}" parameterDetailType="${helpParameterDetailType}" parameterName="${helpParameterName}" altText="help"/>
	</span>
	</c:if>
	<h3> 
		<span class="subhead-left" style="display: inline;" >Medusa</span>
		<span class="subhead-right" style="display: inline;" id="treecontrol">
		<a href="#" title="Collapse the entire tree below"><img src="static/images/jquery/minus.gif" /> Collapse All</a>
		<a href="#" title="Expand the entire tree below"><img src="static/images/jquery/plus.gif" /> Expand All</a>
		</span>
    </h3>
<table cellspacing="0" cellpadding="0" class="elementtable">	
	<tr>
    	<th style="width: 60px; text-align: right;">View:</th>
        <th style="text-align: center;">
            ${kfunc:registerEditableProperty(KualiForm, "medusaBean.medusaViewRadio")}
        	<c:choose>
        		<c:when test="${KualiForm.medusaBean.medusaViewRadio == 0}">        			
        			<input class="nobord" type="radio" value="0" name="medusaBean.medusaViewRadio" checked="true"/>
        		</c:when>        		
        		<c:otherwise>
        			<input class="nobord" type="radio" value="0" name="medusaBean.medusaViewRadio"/>
        		</c:otherwise>
        	</c:choose>	  
        	Proposal > Award
        </th>	
        <th style="text-align: center;">        
        	<c:choose>
        		<c:when test="${KualiForm.medusaBean.medusaViewRadio == 1}">        	
        			<input class="nobord" type="radio" value="1" name="medusaBean.medusaViewRadio" checked="true"/>
        		</c:when>        		
        		<c:otherwise>        	
        			<input class="nobord" type="radio" value="1" name="medusaBean.medusaViewRadio"/>
        		</c:otherwise>
        	</c:choose> 
        	Award > Proposal
        </th>
        <c:if test="${KualiForm.medusaBean.moduleName != 'irb' && KualiForm.medusaBean.moduleName != 'iacuc'}" >
	        <th style="text-align: center; background-color: rgb(195, 195, 195); width: 250px;">
	        	<c:choose>
		        	<c:when test="${KualiForm.medusaBean.complianceModulesCheckbox == 'includeComplianceModules'}">
						<input id="complianceModulesCheckbox" type="checkbox" checked="true" />Include Compliance Modules
					</c:when>
					<c:otherwise>
						<input id="complianceModulesCheckbox" type="checkbox" />Include Compliance Modules
					</c:otherwise>
				</c:choose>
			</th>
		</c:if>
        <th style="text-align: center; background-color: rgb(195, 195, 195); width: 60px;">
				<html:image src="${ConfigProperties.kra.externalizable.images.url}tinybutton-refresh.gif" styleClass="tinybutton" alt="Refresh" property="methodToCall.refreshView" />
		</th>	
    </tr>
</table>

<c:if test="${not empty KualiForm.medusaBean.parentNodes}" >   
<c:set var="openned" value="false" scope="request"/>
<div style = "background:#e4e4e4; margin: 10px 0pt 0pt; clear: left; position: static; text-align: left;" >     
  <ul id="medusaview" class="filetree stripeli treeview medusatree"  >
		<c:forEach items="${KualiForm.medusaBean.parentNodes}" var="node">
			<kra-m:medusaTreeNode node="${node}" openned="${openned}"/>
		</c:forEach>
  </ul>
  <div id="medusadetails" class="medusadetails">
  </div> 
</div> 

</c:if>

${kfunc:registerEditableProperty(KualiForm, "medusaBean.complianceModulesCheckbox")}
<input type="hidden" id="medusaBean.complianceModulesCheckbox" name="medusaBean.complianceModulesCheckbox" value="${KualiForm.medusaBean.complianceModulesCheckbox}">
${kfunc:registerEditableProperty(KualiForm, "medusaBean.medusaViewRadio")}
<input type="hidden" id = "medusaBean.medusaViewRadio" name="medusaBean.medusaViewRadio" value="${KualiForm.medusaBean.medusaViewRadio}">
${kfunc:registerEditableProperty(KualiForm, "medusaBean.moduleName")}
<input type="hidden" id = "medusaBean.moduleName" name="medusaBean.moduleName" value="${KualiForm.medusaBean.moduleName}">
${kfunc:registerEditableProperty(KualiForm, "medusaBean.moduleIdentifier")}
<input type="hidden" id = "medusaBean.moduleIdentifier" name="medusaBean.moduleIdentifier" value="${KualiForm.medusaBean.moduleIdentifier}">

<script type="text/javascript">
   $jq(document).ready(function() {
      $jq("#complianceModulesCheckbox").change(function() {
    	  if($jq(this).is(":checked")) {
    		  $jq("#medusaBean\\.complianceModulesCheckbox").val("includeComplianceModules");
    	  } else {
    		  $jq("#medusaBean\\.complianceModulesCheckbox").val("dontIncludeComplianceModules");
    	  }
      });
   });
</script>

</div>
