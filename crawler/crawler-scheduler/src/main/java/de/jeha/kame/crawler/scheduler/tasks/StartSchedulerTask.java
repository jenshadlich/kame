package de.jeha.kame.crawler.scheduler.tasks;

import com.google.common.collect.ImmutableMultimap;
import io.dropwizard.servlets.tasks.Task;
import org.quartz.Scheduler;

import java.io.PrintWriter;

/**
 * @author jenshadlich@googlemail.com
 */
public class StartSchedulerTask extends Task {

    private final Scheduler scheduler;

    public StartSchedulerTask(Scheduler scheduler) {
        super("startScheduler");
        this.scheduler = scheduler;
    }

    @Override
    public void execute(ImmutableMultimap<String, String> parameters, PrintWriter output) throws Exception {
        output.write("Start scheduler...");
        output.flush();
        scheduler.start();

        output.write("Done!");
    }
}
