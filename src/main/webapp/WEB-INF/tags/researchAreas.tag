<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="isAuthorizedToMaintainResearchArea" required="true" type="java.lang.Boolean" description="whether user has permission" %>

<c:set var="readOnly" value="false"  scope="request"/>
<c:set var="questionnaireAttributes" value="${DataDictionary.Questionnaire.attributes}" />

<div class="tab-container" align="left" >
    <div style = "margin-bottom: 10px">
        <center>
            <input id="showhidebutton" type="image" src="kr/images/tinybutton-showinact.gif" /> 
        </center>
    </div>
    <div style = "border: 1px solid #BDBDBD; margin-left : 10px; margin-right : 10px" >
        <h3>
            <span class="subhead-left"> Research Areas Hierarchy </span>
            <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.irb.ResearchArea" altText="help"/> </span>
        </h3>
        <br/>
        <div align="left" style = " margin-left : 20px">
            <img src="static/images/jquery/hierarchy-root.png" width="14" height="14" border="0"> <a id="listcontrol00" style="margin-left:2px;">000001 : All Research Areas</a>
        </div> 

		<c:if test="${isAuthorizedToMaintainResearchArea}">
        <div class="hierarchydetail" id="listcontent00" style="margin-top:2px;margin-left:30px;text-align:left;">          
            <table width="100%" cellpadding="0" cellspacing="0" class="subelement">
                <thead>
                    <tr>
                          <%-- <th class="subelementheader" align="left"> --%>
                        <th style="background:#939393;height:18px;color:#FFFFFF;text-align:left;padding-left:4px;" align="left">
                         <%--    <a href="#" class="hidedetail"><img src="../images/tinybutton-hide.gif" align="absmiddle" border="0" width="45" height="15"></a> --%>
                          000001 : All Research Areas
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="subelementcontent">
                          
                            <table cellpadding="0" cellspacing="0" class="elementtable" width="100%">
                                <tr>
                                    <th style="text-align:right;">
                                      Node:
                                    </th>
                                    <th colspan="4">                                                                 
                                        <a href="#" id="paste0">
                                            <img src="static/images/tinybutton-pastenode.gif" width="79" height="15" border="0" alt="Paste Node" title="Paste your previously cut node structure under this node">
                                        </a>
                                    </th>
                                </tr>
                                <tr>
                                    <td class="infoline" style="width:60px;">&nbsp;
                                      
                                    </td>
                                    <td class="infoline" style="width:100px;">
                                        <b>Parent Code</b>
                                    </td>
                                    <td class="infoline" style="width:100px;">
                                        <b>Research Code</b>
                                    </td>
                                    <td class="infoline">
                                        <b>Research Area</b>
                                    </td> 
                                    <td class="infoline">
                                        <b>Active</b>
                                    </td>
                                    <td class="infoline" style="width:65px;">
                                        <b>Action</b>
                                    </td>
                                </tr>
                                <tr>
                                    <th style="text-align:right;">
                                      Add:
                                    </th>
                                    <td>
                                      000001
                                    </td>
                                    <td>
                                        <input type="text" name="m2" value="" style="width:100%;" maxlength="8" size="8"/>
                                    </td>
                                    <td>
                                        <input type="text" name="m3" value="" style="width:100%;" />
                                    </td>
                                    <td>
                                        <input type="checkbox" name="m4" checked/>
                                    </td>
                                    <th class="infoline" style="text-align:center;">
                                        <input type="image" id="add0" src="static/images/tinybutton-add1.gif" />  
                                    </th>  
                                </tr>
                            </table>
              
                        </td>
                    </tr>
                </tbody>
            </table>   
        </div>
        </c:if>

        <div style = "background:#EAEAEA; margin-left : 20px" >
          <ul id="researcharea" class="filetree"  >
          </ul>
        </div> 
   
    </div> 
  
</div>