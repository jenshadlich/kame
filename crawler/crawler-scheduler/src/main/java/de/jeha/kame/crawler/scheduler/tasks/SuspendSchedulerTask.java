package de.jeha.kame.crawler.scheduler.tasks;

import com.google.common.collect.ImmutableMultimap;
import io.dropwizard.servlets.tasks.Task;
import org.quartz.Scheduler;

import java.io.PrintWriter;

/**
 * @author jenshadlich@googlemail.com
 */
public class SuspendSchedulerTask extends Task {

    private final Scheduler scheduler;

    public SuspendSchedulerTask(Scheduler scheduler) {
        super("suspendScheduler");
        this.scheduler = scheduler;
    }

    @Override
    public void execute(ImmutableMultimap<String, String> parameters, PrintWriter output) throws Exception {
        output.write("Suspend scheduler...");
        output.flush();
        scheduler.standby();

        output.write("Done!");
    }
}
