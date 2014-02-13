<%--
 Copyright 2005-2013 The Kuali Foundation
 
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

${kfunc:registerEditableProperty(KualiForm, "medusaBean.medusaViewRadio")}
<input type="hidden" id = "medusaBean.medusaViewRadio" name="medusaBean.medusaViewRadio" value="${KualiForm.medusaBean.medusaViewRadio}">
${kfunc:registerEditableProperty(KualiForm, "medusaBean.moduleName")}
<input type="hidden" id = "medusaBean.moduleName" name="medusaBean.moduleName" value="${KualiForm.medusaBean.moduleName}">
${kfunc:registerEditableProperty(KualiForm, "medusaBean.moduleIdentifier")}
<input type="hidden" id = "medusaBean.moduleIdentifier" name="medusaBean.moduleIdentifier" value="${KualiForm.medusaBean.moduleIdentifier}">

</div>