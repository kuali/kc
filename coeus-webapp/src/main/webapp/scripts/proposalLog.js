/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
//load rolodex info on change in the rolodex text field
jQuery(document).ready(function() { loadRolodexInfoById()});

var routeSelector = 'input[name*="route"]';
var mergeNumberId = 'document.newMaintainableObject.mergedWith';
var mergeNumberSelector = jq_escape(mergeNumberId);
jQuery(document).ready(function() { 
	jQuery(routeSelector).click(function() { loadMatchingTemporaryLogs(); return false; });
	jQuery(mergeNumberSelector).parent().find('input').hide();
	var span = jQuery('<span/>').attr('id', mergeNumberId + '.span');
	span.html(jQuery(mergeNumberSelector).val());
	jQuery(mergeNumberSelector).parent().append(span);
});

function loadMatchingTemporaryLogs() {
	var proposalLogTypeCode = jQuery(jq_escape('document.newMaintainableObject.proposalLogTypeCode')).val();	
	var proposalLogTypeDescription = jQuery(jq_escape('document.newMaintainableObject.proposalLogTypeCode.div')).html();
	var queryString = 'methodToCall=getMatchingTemporaryProposals' + 
          	'&proposalLogTypeCode=' + (proposalLogTypeCode ? proposalLogTypeCode : '') +
          	'&proposalLogTypeCodeDescription=' + (proposalLogTypeDescription ? jQuery.trim(proposalLogTypeDescription) : '') +
          	'&piId=' + jQuery(jq_escape('document.newMaintainableObject.piId')).val() + 
          	'&rolodexId=' + jQuery(jq_escape('document.newMaintainableObject.rolodexId')).val();
	  jQuery.ajax({
          url: "../mergeProposalLog.do",
          type: 'GET',
          dataType: 'html',
          data: queryString,
          cache: false,
          async: true,
          timeout: 30000,
          error: function(){
             alert('Error finding matching temporary proposal logs.');
          },
          success: function(xml){
        	  try {
        		  var logToMerge = jQuery(mergeNumberSelector); 
        		  logToMerge.siblings('div').remove();
        		  logToMerge.parent().append('<div/>');
        		  logToMerge.siblings('div').hide();
        		  logToMerge.siblings('div').append(xml);
        		  var newDiv = logToMerge.siblings('div').find('div');
        		  if (newDiv.find('table tr').length > 1) {
        			  newDiv.find('a.mergeLink').click(function() {
        				  var proposalNumber = jQuery(this).attr('proposalnumber');
        				  jQuery(jq_escape(mergeNumberId + '.span')).html(proposalNumber);
        				  jQuery(mergeNumberSelector).attr('value', proposalNumber);
        				  jQuery.fancybox.close();
            			  jQuery(routeSelector).unbind('click');
            			  jQuery(routeSelector).click();        				  
        			  });
        			  newDiv.find('a.cancel').click(function() {
        				  jQuery.fancybox.close();
            			  jQuery(routeSelector).unbind('click');
            			  jQuery(routeSelector).click();        				  
        			  });
	        		  logToMerge.siblings('a').remove();
	        		  var fancyBoxLink = jQuery('<a href="#' + newDiv.attr('id') + '"/>');
	        		  fancyBoxLink.hide();
	        		  logToMerge.parent().append(fancyBoxLink);
	        		  fancyBoxLink.fancybox({'type' : 'inline', 'centerOnScroll' : true, });
	        		  fancyBoxLink.click();
        		  } else {
        			  jQuery(routeSelector).unbind('click');
        			  jQuery(routeSelector).click();
        		  }
        	  } catch (e) {
        		  alert(e);
        	  }
          }
         });
}
