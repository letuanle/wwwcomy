package test.quartz;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzParaTest {
	public static void main(String[] args) throws Throwable {
		SchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		Date runTime = DateBuilder.nextGivenSecondDate(null, 10);
		JobDetail job = JobBuilder.newJob(MyParaJob.class).withIdentity("job1", "group1").build();
		// 每隔五秒执行，重复4次
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).withRepeatCount(4)).startAt(runTime).build();
		scheduler.scheduleJob(job, trigger);
		scheduler.start();
		try {
			Thread.sleep(65L * 1000L);
		} catch (Exception e) {

		}
		scheduler.shutdown(true);
	}
}
