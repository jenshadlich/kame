package de.jeha.kame.crawler.scheduler.tasks;

import com.google.common.collect.ImmutableMultimap;
import de.jeha.kame.crawler.scheduler.services.SchedulerLifecycle;
import io.dropwizard.servlets.tasks.Task;

import java.io.PrintWriter;

/**
 * @author jenshadlich@googlemail.com
 */
public class SuspendSchedulerTask extends Task {

    private final SchedulerLifecycle scheduler;

    public SuspendSchedulerTask(SchedulerLifecycle scheduler) {
        super("suspendScheduler");
        this.scheduler = scheduler;
    }

    @Override
    public void execute(ImmutableMultimap<String, String> parameters, PrintWriter output) throws Exception {
        output.write("Suspend scheduler...");
        output.flush();
        scheduler.suspend();

        output.write("Done!");
    }

}
