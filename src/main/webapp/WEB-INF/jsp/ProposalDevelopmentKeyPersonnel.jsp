<%--
 Copyright 2005-2006 The Kuali Foundation.

 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.opensource.org/licenses/ecl1.php

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="proposalDevelopmentKeyPersonnel"
	documentTypeName="ProposalDevelopmentDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="save"
  	headerTabActive="keyPersonnel">

    <kul:uncollapsable tabTitle="Add Key Personnel">
          <div align="center">
            <table  cellpadding="0" cellspacing="0" class="grid" summary="">
              <tr>
                <th class="grid"><div align="right">Person:</div></th>
                <td nowrap class="grid"><label> Employee Search</label>

                  <label> <img src="${ConfigProperties.kr.externalizable.images.url}searchicon.gif" alt="sdf" width="16" height="16"><br>
                  Non-employee Search <img src="${ConfigProperties.kr.externalizable.images.url}searchicon.gif" alt="sdf" width="16" height="16"></label></td>
                <th class="grid"><div align="right">Proposal Role:</div></th>
                <td class="grid" ><label>
                  <select name="asdf" id="asdf">
                    <option selected>select:</option>
                    <option>Principal Investigator</option>

                    <option>Co-Investigator</option>
                    <option>Key Study Person</option>
                  </select>
                  </label>
                </td>
              </tr>
            </table>
            <br>

            <span><img src="${ConfigProperties.kr.externalizable.images.url}tinybutton-clear1.gif" alt="clear" width="40" height="15">&nbsp;<img src="${ConfigProperties.kr.externalizable.images.url}tinybutton-addpers.gif" alt="add personnel" width="83" height="15"></span></div>
    </kul:uncollapsable>

</kul:documentPage>