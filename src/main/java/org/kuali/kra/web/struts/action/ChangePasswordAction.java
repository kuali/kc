/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.web.struts.action;

import java.security.GeneralSecurityException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.EncryptionService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.struts.action.KualiAction;
import org.kuali.kra.bo.Person;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.PersonService;
import org.kuali.kra.web.struts.form.ChangePasswordForm;

/**
 * The Change Password Action handles the changing of a user's password.  As is standard
 * in the industry, the user must enter their current password along with their new password.
 * Because the actual text is hidden during typing (astericks on entry), they must also enter
 * the new password a second time in order to confirm the new password.
 */
public class ChangePasswordAction extends KualiAction {
    
    /**
     * Navigates to the Change Password web page.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward changePassword(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        request.setAttribute(org.kuali.kra.infrastructure.Constants.HTML_FORM_ACTION, "changePassword");
        return mapping.findForward("basic");
    }
    
    /**
     * Updates the user's password based upon the submission of the ChangePasswordForm.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward updatePassword(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) {
        
        ActionForward forward = null;
        ChangePasswordForm cpForm = (ChangePasswordForm) form;
        String currentPassword = cpForm.getCurrentPassword();
        String newPassword = cpForm.getNewPassword();
        String confirmPassword = cpForm.getConfirmPassword();
        
        Person person = getPerson();
        if (!isValid(person, currentPassword, newPassword, confirmPassword)) {
            request.setAttribute(org.kuali.kra.infrastructure.Constants.HTML_FORM_ACTION, "changePassword");
            forward = mapping.findForward("basic");
        } else {
            saveNewPassword(person, newPassword);
            forward = mapping.findForward("portal");
        }
        return forward;
    }
    
    /**
     * Saves the new password to the database.
     * @param person [in] the person whose's password is being changed
     * @param newPassword [in] the new password
     */
    private void saveNewPassword(Person person, String newPassword) {
        try {
            // Encrypt the password before saving it.
            EncryptionService es = KraServiceLocator.getService(EncryptionService.class);
            String hashedPassword = es.hash(newPassword.trim());
            person.setPassword(hashedPassword);
            BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
            businessObjectService.save(person);
        }
        catch (GeneralSecurityException e) {
            e.printStackTrace();
        }  
    }

    /**
     * Get the person from the database.  This is the user in the current session.
     * @return the person
     */
    private Person getPerson() {
        UniversalUser user = GlobalVariables.getUserSession().getUniversalUser();
        String username = user.getPersonUserIdentifier();
        PersonService personService = KraServiceLocator.getService(PersonService.class);
        return personService.getPersonByName(username);
    }
    
    /**
     * Is the change of password valid?  Three requirements must be met:
     * <ul>
     * <li>The passwords must not be blank.</li>
     * <li>The current password must be correct, i.e. matches the password in the database</li>
     * <li>The new and confirmed passwords must be equivalent.</li>
     * </ul>
     * @param person [in] the person whose password is being changed
     * @param currentPassword [in] the current password as entered by the user
     * @param newPassword [in] the new password
     * @param confirmPassword [in] the confirm password
     * @return true if valid; otherwise false
     */
    private boolean isValid(Person person, String currentPassword, String newPassword, String confirmPassword) {
        boolean isValid = true;
       
        if (isBlank(currentPassword, newPassword, confirmPassword)) {
            isValid = false;
        } else if (!isValidCurrentPassword(person, currentPassword)) {
            isValid = false;
        } else if (!isConfirmed(newPassword, confirmPassword)) {
            isValid = false;
        }
       
        return isValid;
    }
    
    /**
     * Are any of the given passwords blank?
     * @param currentPassword [in] the current password as entered by the user
     * @param newPassword [in] the new password
     * @param confirmPassword [in] the confirm password
     * @return true if any is blank; otherwise false
     */
    private boolean isBlank(String currentPassword, String newPassword, String confirmPassword) {
        boolean isBlank = false;
        if (StringUtils.isBlank(currentPassword)) {
            isBlank = true;
            GlobalVariables.getErrorMap().putError(Constants.CHANGE_PASSWORD_PROPERTY_KEY, 
                                                   KeyConstants.ERROR_EMPTY_PASSWORD, "Current");
        }
        if (StringUtils.isBlank(newPassword)) {
            isBlank = true;
            GlobalVariables.getErrorMap().putError(Constants.CHANGE_PASSWORD_PROPERTY_KEY, 
                                                   KeyConstants.ERROR_EMPTY_PASSWORD, "New");
        }
        if (StringUtils.isBlank(confirmPassword)) {
            isBlank = true;
            GlobalVariables.getErrorMap().putError(Constants.CHANGE_PASSWORD_PROPERTY_KEY, 
                                                   KeyConstants.ERROR_EMPTY_PASSWORD, "Confirm");
        }
        return isBlank;
    }
    
    /**
     * Is the given current password valid?  In other words, does it match the real password
     * in the database?
     * @param person [in] the person whose password is being changed
     * @param currentPassword [in] the current password entered by the user
     * @return true if valid; otherwise false
     */
    private boolean isValidCurrentPassword(Person person, String currentPassword) {
        boolean isValid = true;
        String password = person.getPassword();
        
        EncryptionService es = KraServiceLocator.getService(EncryptionService.class);
        
        try {
            String hashedPassword = es.hash(currentPassword.trim());
            if (!StringUtils.equals(password, hashedPassword)) {
                isValid = false;
                GlobalVariables.getErrorMap().putError(Constants.CHANGE_PASSWORD_PROPERTY_KEY, 
                                                       KeyConstants.ERROR_INVALID_PASSWORD, "Current");
            }
        }
        catch (GeneralSecurityException e) {        
            isValid = false;
            GlobalVariables.getErrorMap().putError(Constants.CHANGE_PASSWORD_PROPERTY_KEY, 
                                                   KeyConstants.ERROR_INVALID_PASSWORD, "Current");
        }
       
        return isValid;
    }
    
    /**
     * Does the new password and the confirm password match?
     * @param newPassword [in] the new password
     * @param confirmPassword [in] the confirm password
     * @return true if they match; otherwise false
     */
    private boolean isConfirmed(String newPassword, String confirmPassword) {
        boolean isConfirmed = true;
        if (!StringUtils.equals(newPassword, confirmPassword)) {
            isConfirmed = false;
            GlobalVariables.getErrorMap().putError(Constants.CHANGE_PASSWORD_PROPERTY_KEY, 
                                                   KeyConstants.ERROR_PASSWORD_MISMATCH);
        }
        return isConfirmed;
    }
}
