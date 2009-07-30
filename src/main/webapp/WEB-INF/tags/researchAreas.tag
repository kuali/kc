<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="readOnly" value="false"  scope="request"/>
<c:set var="questionnaireAttributes" value="${DataDictionary.Questionnaire.attributes}" />

<div class="tab-container" align="left">
    <h3>
        <span class="subhead-left"> Research Areas Hierarchy </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.bo.ResearchArea" altText="help"/> </span>
    </h3>
    
        
                                            <img src="static/images/jquery/hierarchy-root.png" width="14" height="14" border="0"> <a id="listcontrol00" style="margin-left:2px;">000001 : All Research Areas</a>
                                            <div id="treecontrol" style="display:inline;">
                                                &nbsp;&nbsp;&nbsp;&nbsp;<a title="Collapse the entire tree below" href="#"><img src="static/images/jquery/minus.gif" /> Collapse All</a>
                                                &nbsp;&nbsp;&nbsp;&nbsp;<a title="Expand the entire tree below" href="#"><img src="static/images/jquery/plus.gif" /> Expand All</a>
                                                <!--&nbsp;&nbsp;&nbsp;&nbsp;<a title="Toggle the tree below, opening closed branches, closing open branches" href="#">Toggle All</a>-->
                                                <!--<a href="#"><img align="absmiddle" src="../images/searchicon.gif" width="16" height="16" border="0" alt="Search for Group" title="Search for Area of Research"></a>-->
                                            </div>
                                            <div class="hierarchydetail" id="listcontent00" style="margin-top:2px;text-align:left;">
                                                
                                                <table width="100%" cellpadding="0" cellspacing="0" class="subelement">
                                                    <thead>
                                                        <tr>
                                                            <%-- <th class="subelementheader" align="left"> --%>
                                                            <th style="background:#939393;height:18px;color:#FFFFFF;text-align:left;padding-left:4px;" align="left">
                                                                <a href="#" class="hidedetail"><img src="../images/tinybutton-hide.gif" align="absmiddle" border="0" width="45" height="15"></a>
                                                                000001 : All Research Areas
                                                            </th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td class="subelementcontent">
                                                                
                                                                <table cellpadding="0" cellspacing="0" class="elementtable" width="100%">
                                                                    <!--<tr>
                                                                        <th style="text-align:right;">Node:</th>
                                                                        <th colspan="4">
                                                                            <span class="fineprint">Area of Research root can not be moved.</span>
                                                                        </th>
                                                                    </tr>-->
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
                                                                        <td class="infoline" style="width:65px;">
                                                                            <b>Action</b>
                                                                        </td>
                                                                    </tr>
                                                                    <!--<tr>
                                                                        <th style="text-align:right;">
                                                                            Edit:
                                                                        </th>
                                                                        <td colspan='4'>
                                                                           <span class="fineprint">Area of Research root can not be modified.</span>
                                                                        </th> 
                                                                    </tr>-->
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
                                                                        <th class="infoline" style="text-align:center;">
              <input type="image" id="add0" src="static/images/tinybutton-add1.gif" />  
                 <%-- <a href="#"><img src="../images/tinybutton-add1.gif" width="40" height="15" border="0" title="Add this Sub-group"></a>--%>
                                                                        </th>  
                                                                    </tr>
                                                                </table>
                                                        
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                
                                            </div>

<%-- 0000001 --%>

  <div style = "background:#e4e4e4" >
  <ul id="example" class="filetree"  >
        <%-- <li><span class="folder">00000</span>
        </li> --%>
    </ul>
   </div> 
</div>