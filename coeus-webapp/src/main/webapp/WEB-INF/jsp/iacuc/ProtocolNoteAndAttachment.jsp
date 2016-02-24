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

<c:set var="readOnly" value="${!KualiForm.notesAttachmentsHelper.modifyAttachments}" scope="request" />

<c:set var="protocolAttachmentProtocolAttributes" value="${DataDictionary.IacucProtocolAttachmentProtocol.attributes}" />
<c:set var="attachmentFileAttributes" value="${DataDictionary.AttachmentFile.attributes}" />
<c:set var="protocolAttachmentFilterAttributes" value="${DataDictionary.IacucProtocolAttachmentFilter.attributes}" />
<c:set var="protocolNotesAttributes" value="${DataDictionary.IacucProtocolNotepad.attributes}" />

<script type="text/javascript" src="scripts/jquery/jquery.js"></script>
 
<script type="text/javascript">
    var $j = jQuery.noConflict();
    
    //This function is tied to the filter by drop down box on the
    //existing protocol attachments section
    function filterTableByCriteria(data) { 
        if (data.value == "") {
            showAllAttachmentRows();
        } else {
            var label = getSelectedLabel(data, data.value);
            filterAttachments(label);
        }
    }
    
    //This function pulls the option label given the selected option value
    function getSelectedLabel(data, selectedValue) {
        for (var i = 0; i < data.options.length; i++) {
            if (data.options[i].value == selectedValue) {
                return data.options[i].innerHTML;
            }
        }       
    }
    
    //This function makes all the attachment rows visible.  This is used when the
    //"select" option is chosen in the filter by drop down
    function showAllAttachmentRows() {
        $j("#protocol-attachment-table tr.fake-class-level-1").each(function(index) {
               $j(this).show();
        });
    }
    
    //This function takes the attachment type label and attempts to hide any rows
    //which do not match.  Added a fake css class to the "tr" element to make the
    //lookup easier.
    function filterAttachments(type) {
        $j("#protocol-attachment-table tr.fake-class-level-1").each(function(index) {
                var rowId = $j(this).attr("id");
                var index = (rowId.split("-"))[3];
                var rowAttachmentType = getAttachmentType(index);
                if (type === rowAttachmentType) {
                    $j("#protocol-attachment-row-" + index).show();
                } else {
                    $j("#protocol-attachment-row-" + index).hide();
                }
            });
    }
    
    //This function extracts the attachment type from the div within the current row.
    function getAttachmentType(index) {
        var attachmentType = $j("#attachment-type-" +index).html();
        return $j.trim(attachmentType);
    }
    
    //Sorts the table via the four possible criterias:
    //"ATTP", "Attachment Type"
    //"DESC", "Description"
    //"LAUP", "Last Updated"
    //"UPBY", "Last Updated By" 
    //
    //Also handles the "None" case...
    function sortTableByCriteria(data) {

        if (data.value == "ATTP") {
            sortByAttachmentType();
        } else if (data.value == "DESC") {
            sortByDescription();
        } else if (data.value == "LAUP") {
            sortByLastUpdated();
        } else if (data.value == "UPBY") {
            sortByUpdatedBy();
        } else {
            //do nothing?
        }
    }
    
    function sortByAttachmentType() {
        var rowList = new Array();
        var sortingList = new Array();
        $j("#protocol-attachment-table tr.fake-class-level-1").each(function(idx) {
            var rowId = $j(this).attr("id");
            var index = (rowId.split("-"))[3];
            var rowAttachmentType = getAttachmentType(index);
            rowList[index] = rowId;
            sortingList[index] = rowAttachmentType;            
        });
                
        sortLists(rowList, sortingList);        
        sortTableRows(rowList, sortingList);
    }
    
    function sortByDescription() {
        var rowList = new Array();
        var sortingList = new Array();
        $j("#protocol-attachment-table tr.fake-class-level-1").each(function(idx) {
            var rowId = $j(this).attr("id");
            var index = (rowId.split("-"))[3];
            var rowDescription = getDescription(index);
            rowList[index] = rowId;
            sortingList[index] = rowDescription;            
        });
        
        sortLists(rowList, sortingList); 
        sortTableRows(rowList, sortingList);
    }

    function sortByLastUpdated() {
        var rowList = new Array();
        var sortingList = new Array();
        $j("#protocol-attachment-table tr.fake-class-level-1").each(function(idx) {
            var rowId = $j(this).attr("id");
            var index = (rowId.split("-"))[3];
            var lastUpdated = getLastUpdated(index);
            rowList[index] = rowId;
            sortingList[index] = lastUpdated;            
        });
        
        sortListsByDate(rowList, sortingList); 
        sortTableRows(rowList, sortingList);
    }
    
    function sortByUpdatedBy() {
        var rowList = new Array();
        var sortingList = new Array();
        $j("#protocol-attachment-table tr.fake-class-level-1").each(function(idx) {
            var rowId = $j(this).attr("id");
            var index = (rowId.split("-"))[3];
            var updatedBy = getUpdatedBy(index);
            rowList[index] = rowId;
            sortingList[index] = updatedBy;            
        });
        
        sortLists(rowList, sortingList); 
        sortTableRows(rowList, sortingList);
    }    

    function sortLists(rowList, sortingList) {
        for (var i = 0; i < sortingList.length-1; i++) {
            for (var j = i+1; j < sortingList.length; j++) {
                var result = compareToStr(sortingList[i], sortingList[j]);
                if (result > 0) {
                    swapItems(i, j, sortingList);
                    swapItems(i, j, rowList);
                }
            }
        }
    }
    
    function sortListsByDate(rowList, sortingList) {
        for (var i = 0; i < sortingList.length-1; i++) {
            for (var j = i+1; j < sortingList.length; j++) {
                var result = compareToDate(sortingList[i], sortingList[j]);
                if (result > 0) {
                    swapItems(i, j, sortingList);
                    swapItems(i, j, rowList);
                }
            }
        }
    }    
    
    function swapItems(idx1, idx2, sortList) {
        var tmpItem = sortList[idx1];
        sortList[idx1] = sortList[idx2];
        sortList[idx2] = tmpItem;
    }
    
    function compareToStr(str1, str2) {
        return str1.localeCompare(str2);
    }

    function compareToDate(date1, date2) {
        var dateParts1 = date1.split(" ");
        var dateParts2 = date2.split(" ");
        
        var mmddyyyy1 = dateParts1[0].split("/");
        var mmddyyyy2 = dateParts2[0].split("/");
        
        if (compareYear(mmddyyyy1[2], mmddyyyy2[2]) > 0) {
            return 1;
        } else if (compareMonth(mmddyyyy1[0], mmddyyyy2[0]) > 0) {
            return 1;
        } else if (compareDay(mmddyyyy1[1], mmddyyyy2[1]) > 0) {
            return 1;
        } else {
            return compareTime(dateParts1[1], dateParts1[2], dateParts2[1], dateParts2[2]);
        }
            
        return 0;
    }
    
    function compareYear(year1, year2) {
        if (parseInt(year1) == parseInt(year2)) {
            return 0;
        } else {
            if (parseInt(year1) > parseInt(year2)) {
                return 1;
            } else {
                return -1;
            }
        }
    }
    
    function compareMonth(month1, month2) {
        if (parseInt(month1) == parseInt(month2)) {
            return 0;
        } else {
            if (parseInt(month1) > parseInt(month2)) {
                return 1;
            } else {
                return -1;
            }
        }
    }
    
    function compareDay(day1, day2) {
        if (parseInt(day1) == parseInt(day1)) {
            return 0;
        } else {
            if (parseInt(day1) > parseInt(day1)) {
                return 1;
            } else {
                return -1;
            }
        }
    }    
    
    function compareTime(hoursMinutes1, ampm1, hoursMinutes2, ampm2) {
        if (ampm1 != ampm2) {
            if (ampm1 == 'AM') {
                return -1;
            } else {
                return 1;
            }
        } else {
            var hours1 = (hoursMinutes1.split(":"))[0];
            var hours2 = (hoursMinutes2.split(":"))[0];
            
            if (parseInt(hours1) != parseInt(hours2)) {
                if (parseInt(hours1) > parseInt(hours2)) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                var min1 = (hoursMinutes1.split(":"))[1];
                var min2 = (hoursMinutes2.split(":"))[1];
                if (parseInt(min1) != parseInt(min2)) {
                    if (parseInt(min1) > parseInt(min2)) {
                        return 1;
                    } else {
                        return -1;
                    } 
                } else {
                    return 0;
                }
            }
        }
    }
    
    function sortTableRows(rowList, sortingList) {
        var rowEl;
        for (var i = 0; i < rowList.length; i++) {
            rowEl = $j("#" + rowList[i]).detach();
            $j("#protocol-attachment-table > tbody").append(rowEl);
        }
    }
    
    function getDescription(index) {
        var description = $j("#row-description-" +index+ " > textarea").html();
        return $j.trim(description);        
    }
    
    function getUpdatedBy(index) {
        var updatedBy = $j("#updated-by-" +index).html();
        return $j.trim(updatedBy);        
    }
    
    function getLastUpdated(index) {
        var lastUpdated = $j("#last-updated-" +index).html();
        return $j.trim(lastUpdated);
    }
    
    function alertList(label, theList) {
        var str = label + " : {";
        for (var x = 0; x < theList.length; x++) {
            if (x == (theList.length - 1)) {
                str += theList[x] + "}";
            } else {
                str += theList[x] + ", ";
            }
        }
        
        alert (str);
    }    
    
    $j(document).ready(function() {
        filterTableByCriteria(document.getElementById("notesAttachmentsHelper.newAttachmentFilter.filterBy"));
        sortTableByCriteria(document.getElementById("notesAttachmentsHelper.newAttachmentFilter.sortBy"));
    });
</script>

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="iacucProtocolNoteAndAttachment"
	documentTypeName="IacucProtocolDocument"
	renderMultipart="true"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="noteAndAttachment">
  	
<div align="right">
    <kra:shortUrl shortUrl="${KualiForm.shortUrl}"/>
    <kul:help documentTypeName="IacucProtocolDocument" pageName="Notes%20%26%20Attachments" />
</div>
<div id="workarea">
<kra-protocol:protocolAttachmentProtocol 
    protocolAttachmentProtocolAttributes="${protocolAttachmentProtocolAttributes}"
    attachmentFileAttributes="${attachmentFileAttributes}"
    protocolAttachmentFilterAttributes="${protocolAttachmentFilterAttributes}"
    action="iacucProtocolNoteAndAttachment"
    protocolAttachmentTypeByGroupValuesFinder="org.kuali.kra.iacuc.noteattachment.IacucProtocolAttachmentTypeByGroupValuesFinder"/>
<kra-protocol:protocolNotes protocolNotesAttributes="${protocolNotesAttributes}"/>
<kul:panelFooter />
</div>
    <kul:documentControls 
        transactionalDocument="false"
        suppressRoutingControls="true"
        extraButtonSource="${extraButtonSource}"
        extraButtonProperty="${extraButtonProperty}"
        extraButtonAlt="${extraButtonAlt}"
        viewOnly="${KualiForm.editingMode['viewOnly']}"
        />

</kul:documentPage>
