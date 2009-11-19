<%--
 Copyright 2006-2009 The Kuali Foundation
 
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
<kul:tabTop tabTitle="Medusa" defaultOpen="true" tabErrorKey="">
<div class="tab-container"  align="center">
	<h3> 
		<span class="subhead-left">Medusa</span>
    </h3>
<table cellspacing="0" cellpadding="0" class="elementtable">	
	<tr>
    	<th style="width: 60px; text-align: right;">View:</th>
        <th style="text-align: center;">
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
   
<div style = "background:#e4e4e4; margin: 10px 0pt 0pt; clear: left; height: 285px; overflow-y: scroll; overflow-x: hidden; position: relative;" >     
  <ul id="medusaview" class="filetree stripeli treeview"  >
        <%-- <li><span class="folder">00000</span>
        </li> --%>
    </ul>
   </div>
</div>

<input type="hidden" id = "medusaBean.medusaViewRadio" name="medusaBean.medusaViewRadio" value="${KualiForm.medusaBean.medusaViewRadio}">
<input type="hidden" id = "medusaBean.moduleName" name="medusaBean.moduleName" value="${KualiForm.medusaBean.moduleName}">
<input type="hidden" id = "medusaBean.moduleIdentifier" name="medusaBean.moduleIdentifier" value="${KualiForm.medusaBean.moduleIdentifier}">

	<script language="JavaScript" type="text/javascript" src="dwr/engine.js"></script>

	<script language="JavaScript" type="text/javascript" src="dwr/util.js"></script>
		
	<script language="JavaScript" type="text/javascript" src="dwr/interface/AwardHierarchyUIService.js"></script>	

	<script src="scripts/jquery/jquery.js"></script>
	<link rel="stylesheet" href="css/jquery/screen.css" type="text/css" />
	<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
	<link rel="stylesheet" href="css/jquery/kuali-stylesheet.css" type="text/css" />
	<link rel="stylesheet" href="css/jquery/jquery.treeview.css" type="text/css" />
	
	<script type="text/javascript" src="scripts/jquery/jquery.treeview.js"></script>
      
</kul:tabTop>   

