/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.web.krad;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.field.AttributeQueryResult;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


public interface UifControllerService {

    /**
     * Create/obtain the model(form) object before it is passed to the Binder/BeanWrapper. This method
     * is not intended to be overridden by client applications as it handles framework setup and session
     * maintenance. Clients should override createInitialForm() instead when they need custom form initialization.
     *
     * @param request the http request that was made
     * @param response the http response object
     */
    public UifFormBase initForm(UifFormBase form, HttpServletRequest request, HttpServletResponse response);

    /**
     * Default method mapping for cases where the method to call is not passed, calls the start method
     */
    public ModelAndView defaultMapping(UifFormBase form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response);

    /**
     * Initial method called when requesting a new view instance which checks authorization and forwards
     * the view for rendering
     */
    public ModelAndView start(UifFormBase form, HttpServletRequest request,
            HttpServletResponse response);

    /**
     * Invokes the configured {@link org.kuali.rice.krad.uif.view.ViewAuthorizer} to verify the user has access to
     * open the view. An exception is thrown if access has not been granted
     *
     * <p>
     * Note this method is invoked automatically by the controller interceptor for each request
     * </p>
     *
     * @param form - form instance containing the request data
     * @param methodToCall - the request parameter 'methodToCall' which is used to determine the controller
     * method invoked
     */
    public void checkViewAuthorization(UifFormBase form, String methodToCall) throws AuthorizationException;

    /**
     * Invoked when a session timeout occurs, default impl does nothing but render the view
     */
    public ModelAndView sessionTimeout(UifFormBase form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response);

    /**
     * Called by the add line action for a new collection line. Method
     * determines which collection the add action was selected for and invokes
     * the view helper service to add the line
     */
    public ModelAndView addLine(UifFormBase uifForm, BindingResult result, HttpServletRequest request,
            HttpServletResponse response);

    /**
     * Called by the add blank line action for a new collection line
     *
     * <p>
     * Method determines which collection the add action was selected for and invokes the view helper service to
     * add the blank line.
     * </p>
     *
     * @param uifForm - form instance containing the request data
     * @return the  ModelAndView object
     */
    public ModelAndView addBlankLine(UifFormBase uifForm, HttpServletRequest request,
            HttpServletResponse response);

    /**
     * Called by the save line action for a new collection line. Does server side validation and provides hook
     * for client application to persist specific data.
     */
    public ModelAndView saveLine(UifFormBase uifForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response);

    /**
     * Called by the delete line action for a model collection. Method
     * determines which collection the action was selected for and the line
     * index that should be removed, then invokes the view helper service to
     * process the action
     */
    public ModelAndView deleteLine(UifFormBase uifForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response);

    /**
     * Just returns as if return with no value was selected.
     */
    public ModelAndView cancel(UifFormBase form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response);

    /**
     * Attempts to go back by looking at various return mechanisms in HistoryFlow and on the form.  If a back cannot
     * be determined, returns to the application url.
     */
    public ModelAndView back(UifFormBase form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response);

    /**
     * Invoked to navigate back one page in history.
     *
     * @param form - form object that should contain the history object
     */
    public ModelAndView returnToPrevious(UifFormBase form);

    /**
     * Invoked to navigate back to the first page in history.
     *
     * @param form - form object that should contain the history object
     */
    public ModelAndView returnToHub(UifFormBase form);

    /**
     * Invoked to navigate back to a history entry. The homeFlag will determine whether navigation
     * will be back to the first or last history entry.
     *
     * @param form - form object that should contain the history object
     * @param homeFlag - if true will navigate back to first entry else will navigate to last entry
     * in the history
     */
    public ModelAndView returnToHistory(UifFormBase form, boolean homeFlag);

    /**
     * Handles menu navigation between view pages
     */
    public ModelAndView navigate(UifFormBase form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response);

    /**
     * Invoked to refresh a view, generally when returning from another view (for example a lookup))
     */
    public ModelAndView refresh(UifFormBase form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response) throws Exception;

    /**
     * Builds up a URL to the lookup view based on the given post action
     * parameters and redirects
     */
    public ModelAndView performLookup(UifFormBase form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response);

    /**
     * Checks the form/view against all current and future validations and returns warnings for any validations
     * that fail
     */
    public ModelAndView checkForm(UifFormBase form, BindingResult result, HttpServletRequest request,
            HttpServletResponse response);

    /**
     * Invoked to provide the options for a suggest widget. The valid options are retrieved by the associated
     * <code>AttributeQuery</code> for the field containing the suggest widget. The controller method picks
     * out the query parameters from the request and calls <code>AttributeQueryService</code> to perform the
     * suggest query and prepare the result object that will be exposed with JSON
     */
    public AttributeQueryResult performFieldSuggest(UifFormBase form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response);

    /**
     * Invoked to execute the <code>AttributeQuery</code> associated with a field given the query parameters
     * found in the request. This controller method picks out the query parameters from the request and calls
     * <code>AttributeQueryService</code> to perform the field query and prepare the result object
     * that will be exposed with JSON. The result is then used to update field values in the UI with client
     * script.
     */
    public AttributeQueryResult performFieldQuery(UifFormBase form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response);

    /**
     * Common return point for dialogs
     *
     * <p>
     * Determines the user responses to the dialog. Performs dialog management and then redirects to the
     * original contoller method.
     * </p>
     *
     * @param form - current form
     * @param result - binding result
     * @param request - http request
     * @param response - http response
     * @return ModelAndView setup for redirect to original controller methodToCall
     * @throws Exception
     */
    public ModelAndView returnFromLightbox(UifFormBase form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response);

    /**
     * Generates exportable table data as CSV based on the rich table selected
     *
     * @param form - current form
     * @param result - binding result
     * @param request - http request
     * @param response - http response
     * @return
     */
    public String tableCsvRetrieval(UifFormBase form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response);

    /**
     * Generates exportable table data in xsl based on the rich table selected
     *
     * @param form - current form
     * @param result - binding result
     * @param request - http request
     * @param response - http response
     * @return
     */
    public String tableXlsRetrieval(UifFormBase form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response);

    /**
     * Generates exportable table data based on the rich table selected
     *
     * @param form - current form
     * @param result - binding result
     * @param request - http request
     * @param response - http response
     * @return
     */
    public String tableXmlRetrieval(UifFormBase form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response);

    /**
     * Retrieve a page defined by the page number parameter for a collection
     *
     * @param form -  Holds properties necessary to determine the <code>View</code> instance that will be used to
     * render
     * the UI
     * @param result -   represents binding results
     * @param request - http servlet request data
     * @param response - http servlet response object
     * @return the  ModelAndView object
     * @throws Exception
     */
    public ModelAndView retrieveCollectionPage(UifFormBase form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * Retrieves the original component as it exists in postedView without attempting to refresh it; fast and
     * consistent when this is all that is needed
     *
     * <p>By passing in the "changeProperties" parameter to this controller method, properties can be changed on
     * the retrieved component.  However, keep in mind that since this method does not call the lifecycle on
     * the returned component, properties which require a lifecycle to be run to affect the output of a component
     * should not be set.  Main use case is to affect attributes which are only used by the ftl.  The
     * "changeProperties" parameter must be in JSON in string from, ie "{\"propertyPath\": true}"; note the use
     * of escaping, as this is required.  The propertyPath defines the property on the component that needs to be
     * changed during this retrieval.  This call must be using the "update-component" return type.</p>
     *
     * @param form -  Holds properties necessary to determine the <code>View</code> instance that will be used to
     * render
     * the UI
     * @param result -   represents binding results
     * @param request - http servlet request data
     * @param response - http servlet response object
     * @return the  ModelAndView object
     * @throws Exception
     */
    public ModelAndView retrieveOriginalComponent(UifFormBase form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * Get method for getting aaData for jquery datatables which are using sAjaxSource option.
     *
     * <p>This will render the aaData JSON for the displayed page of the table matching the tableId passed in the
     * request parameters.</p>
     */
    public ModelAndView tableJsonRetrieval(UifFormBase form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response);

    public ModelAndView getUIFModelAndView(UifFormBase form);

}