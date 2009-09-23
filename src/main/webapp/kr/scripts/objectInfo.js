/*
 * Copyright 2005-2007 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
function loadUserInfo( userIdFieldName, universalIdFieldName, userNameFieldName ) {
	var userId = DWRUtil.getValue( userIdFieldName );

	if (userId == "") {
		clearRecipients( universalIdFieldName, "" );
		clearRecipients( userNameFieldName, "" );
	} else {
		var dwrReply = {
			callback:function(data) {
				if ( data != null && typeof data == 'object' ) {
					if ( universalIdFieldName != null && universalIdFieldName != "" ) {
						setRecipientValue( universalIdFieldName, data.principalId );
					}
					if ( userNameFieldName != null && userNameFieldName != "" ) {
						setRecipientValue( userNameFieldName, data.name );
					} else {
						// guess the DIV name
						divName = userIdFieldName.replace( ".principalName", ".name.div" );
						DWRUtil.setValue( divName, data.name );
					}
				} else {
					if ( universalIdFieldName != null && universalIdFieldName != "" ) {
						setRecipientValue( universalIdFieldName, "" );
					}
					if ( userNameFieldName != null && userNameFieldName != "" ) {
						setRecipientValue( userNameFieldName, wrapError( "person not found" ), true );
					} else {
						// guess the DIV name
						divName = userIdFieldName.replace( ".principalName", ".name.div" );
						DWRUtil.setValue( divName, wrapError( "person not found" ), { escapeHtml:false} );
					}
				}
			},
			errorHandler:function( errorMessage ) {
				window.status = errorMessage;
				if ( universalIdFieldName != null && universalIdFieldName != "" ) {
					setRecipientValue( universalIdFieldName, "" );
				}
				if ( userNameFieldName != null && userNameFieldName != "" ) {
					setRecipientValue( userNameFieldName, wrapError( "person not found" ), true );
				} else {
					// guess the DIV name
					divName = userIdFieldName.replace( ".principalName", ".name.div" );
					DWRUtil.setValue( divName, wrapError( "person not found" ), { escapeHtml:false} );
				}
			}
		};
		PersonService.getPersonByPrincipalName( userId, dwrReply );
	}
}

/**
 * This method is only used for docType.  docType now has the ability to update
 * attributes when onBlur is called on the docType text box.  In order to
 * stop the page from continually reloading we need to have a before and
 * after of the docTypeName.  Because hidden vars that are not listed in the
 * datadictionary are removed we have to use the current method.  This mothod
 * stores the current docTypeFullName on Page load.  the call is in page.tag.
 */
function storeCurrentDocTypeNameOnLoad(){
	var oldDocTypeField;
	var docTypeName = document.getElementById("docTypeFullName");
	if (document.createElement) {
		oldDocTypeField = document.createElement("input");
		oldDocTypeField.setAttribute("type", "hidden");
		oldDocTypeField.setAttribute("name", "oldDocTypeFieldName");
		oldDocTypeField.setAttribute("value", docTypeName.value);
		document.forms[0].appendChild(oldDocTypeField);
	}
}

/**
 * This method performs an ajax call to the docTypeService to 1. check for a valid
 * docTypeName.  If the name is valid then the page is reposted with the new
 * docTypeName.  This allows for the populating of the attributes on the doc search.
 *
 */
function validateDocTypeAndRefresh(docTypeNameField){

	var docTypeName = DWRUtil.getValue( docTypeNameField );
	var oldDocTypeName = document.forms[0].oldDocTypeFieldName.value;

	if(docTypeName != null && oldDocTypeName != docTypeName){

		var dwrReply = {
				callback:function(data) {
					if ( data != null && typeof data == 'object' ) {
						newField = document.createElement("input");
						newField.type = "hidden";
						newField.name = "docTypeFullName";
						newField.value = data.name;


						var frm = document.forms[0];
						frm.methodToCall='post';
						frm.refreshCaller = 'docTypeLookupable';

						frm.submit();
					}
				},
				errorHandler:function( errorMessage ) {
					window.status = errorMessage;
				}
			};
		DocumentTypeService.findByNameCaseInsensitive(docTypeName, dwrReply);
	}

}
