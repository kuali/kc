package org.kuali.coeus.org.kuali.rice.krad.uif.container;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.component.Component;
import org.kuali.rice.krad.uif.container.CollectionGroup;
import org.kuali.rice.krad.uif.container.CollectionGroupBuilder;
import org.kuali.rice.krad.uif.element.Action;
import org.kuali.rice.krad.uif.lifecycle.ViewLifecycleUtils;
import org.kuali.rice.krad.uif.util.ComponentUtils;
import org.kuali.rice.krad.uif.util.ContextUtils;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;
import org.kuali.rice.krad.uif.util.ScriptUtils;
import org.kuali.rice.krad.uif.view.FormView;
import org.kuali.rice.krad.uif.view.View;
import org.kuali.rice.krad.web.form.UifFormBase;

import java.util.List;

/**
 */
public class CollectionGroupBuilderOverride extends CollectionGroupBuilder {

    /**
     * Creates new {@code Component} instances for the add line
     * <p/>
     * <p>
     * Adds context to the action fields for the add line so that the collection
     * the action was performed on can be determined
     * </p>
     *
     * @param view            view instance the collection belongs to
     * @param model           top level object containing the data
     * @param collectionGroup collection group component for the collection
     */
    @Override
    protected List<? extends Component> getAddLineActionComponents(View view, Object model,
                                                                   CollectionGroup collectionGroup) {
        String lineSuffix = UifConstants.IdSuffixes.ADD_LINE;

        List<? extends Component> lineActionComponents = ComponentUtils.copyComponentList(
                collectionGroup.getAddLineActions(), lineSuffix);

        List<Action> actions = ViewLifecycleUtils.getElementsOfTypeDeep(lineActionComponents, Action.class);

        if (collectionGroup.isAddWithDialog() && (collectionGroup.getAddLineDialog().getFooter() != null) &&
                !collectionGroup.getAddLineDialog().getFooter().getItems().isEmpty()) {
            List<Action> addLineDialogActions = ViewLifecycleUtils.getElementsOfTypeDeep(
                    collectionGroup.getAddLineDialog().getFooter().getItems(), Action.class);

            if (addLineDialogActions != null) {
                actions.addAll(addLineDialogActions);
            }
        }

        for (Action action : actions) {
            action.addActionParameter(UifParameters.SELECTED_COLLECTION_PATH,
                    collectionGroup.getBindingInfo().getBindingPath());
            action.addActionParameter(UifParameters.SELECTED_COLLECTION_ID, collectionGroup.getId());
            action.setJumpToIdAfterSubmit(collectionGroup.getId());
            action.addActionParameter(UifParameters.ACTION_TYPE, UifParameters.ADD_LINE);

            boolean isPageUpdateAction =  StringUtils.isNotBlank(action.getAjaxReturnType())
                    && action.getAjaxReturnType().equals(UifConstants.AjaxReturnTypes.UPDATEPAGE.getKey());

            if (StringUtils.isBlank(action.getRefreshId()) && !isPageUpdateAction) {
                action.setRefreshId(collectionGroup.getId());
            }

            if (collectionGroup.isAddWithDialog() && view instanceof FormView && ((FormView) view)
                    .isValidateClientSide()) {
                action.setPerformClientSideValidation(true);
            }

            if (action.isPerformClientSideValidation()) {
                String preSubmitScript = "var valid=" + UifConstants.JsFunctions.VALIDATE_ADD_LINE + "('" +
                        collectionGroup.getId() + "');";

                // prepend custom presubmit script which should evaluate to a boolean
                if (StringUtils.isNotBlank(action.getPreSubmitCall())) {
                    preSubmitScript = ScriptUtils.appendScript(preSubmitScript,
                            "if (valid){valid=function(){" + action.getPreSubmitCall() + "}();}");
                }

                preSubmitScript += "return valid;";

                action.setPreSubmitCall(preSubmitScript);
                action.setPerformClientSideValidation(false);
            } else if (collectionGroup.isAddWithDialog()) {
                action.setPreSubmitCall("closeLightbox(); return true;");
            }
        }

        // get add line for context
        String addLinePath = collectionGroup.getAddLineBindingInfo().getBindingPath();
        Object addLine = ObjectPropertyUtils.getPropertyValue(model, addLinePath);

        ContextUtils.updateContextForLine(collectionGroup.getAddLineDialog(), collectionGroup, addLine, -1, lineSuffix);
        ContextUtils.updateContextsForLine(actions, collectionGroup, addLine, -1, lineSuffix);

        return lineActionComponents;
    }
}
