package test.quartz;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyParaJob implements Job {
	private int myCount = 0;
	public static int myStaticCount = 0;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String jobName = context.getJobDetail().getKey().getName();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		
        JobDataMap data = context.getJobDetail().getJobDataMap();
        
		System.out.println("任务Key:" + jobName + " 正在执行，执行时间: " + dateFormat.format(Calendar.getInstance().getTime()));
		System.out.println("***private成员变量为:" + myCount + ",static成员变量为" + myStaticCount + "");
		myCount++;
		myStaticCount++;
	}
}
