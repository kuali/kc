package org.kuali.coeus.org.kuali.rice.krad.uif.lifecycle;

import org.kuali.rice.krad.uif.lifecycle.*;
import org.kuali.rice.krad.uif.lifecycle.initialize.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Kc Override of ViewLifecyclePhaseBuilderBase to insert KcAssignIdsTask customization
 */
public class KcViewLifecyclePhaseBuilderBase extends ViewLifecyclePhaseBuilderBase {

    /**
     * Creates an instance of the {@link org.kuali.rice.krad.uif.lifecycle.PreProcessElementPhase} phase with configured tasks.
     *
     * @return view lifecycle phase instance
     */
    @Override
    protected ViewLifecyclePhase buildPreProcessPhase() {
        PreProcessElementPhase phase = new PreProcessElementPhase();

        List<ViewLifecycleTask<?>> tasks = new ArrayList<ViewLifecycleTask<?>>();

        tasks.add(new KcAssignIdsTask());
        tasks.add(new PopulatePathTask());
        tasks.add(new SortContainerTask());
        tasks.add(new PrepareForCacheTask());

        phase.setTasks(tasks);
        phase.setSkipLifecycleTasks(new ArrayList<ViewLifecycleTask<?>>());

        return phase;
    }

    /**
     * Creates an instance of the {@link org.kuali.rice.krad.uif.lifecycle.InitializeComponentPhase} phase with configured tasks.
     *
     * @return view lifecycle phase instance
     */
    protected ViewLifecyclePhase buildInitializePhase() {
        InitializeComponentPhase phase = new InitializeComponentPhase();

        List<ViewLifecycleTask<?>> tasks = new ArrayList<ViewLifecycleTask<?>>();

        tasks.add(new KcAssignIdsTask());
        tasks.add(new PopulatePathTask());
        tasks.add(new PopulateComponentFromExpressionGraphTask());
        tasks.add(new ComponentDefaultInitializeTask());
        tasks.add(new InitializeDataFieldFromDictionaryTask());
        tasks.add(new PopulateReplacersAndModifiersFromExpressionGraphTask());
        tasks.add(new InitializeContainerFromHelperTask());
        tasks.add(new ProcessRemoteFieldsHolderTask());
        tasks.add(new InitializeDataFieldFromDictionaryTask());
        tasks.add(new RunComponentModifiersTask());
        tasks.add(new HelperCustomInitializeTask());

        phase.setTasks(tasks);

        List<ViewLifecycleTask<?>> skipLifecycleTasks = new ArrayList<ViewLifecycleTask<?>>();

        skipLifecycleTasks.add(new KcAssignIdsTask());

        phase.setSkipLifecycleTasks(skipLifecycleTasks);

        return phase;
    }

}
