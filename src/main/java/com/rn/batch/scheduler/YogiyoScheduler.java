package com.rn.batch.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class YogiyoScheduler {

    private final JobLauncher jobLauncher;
    private final Job yogiyoJob;


    @Scheduled(cron = "0 0 3,15 * * ?")
    public void yogiyoJobRun() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException,
            JobParametersInvalidException, JobRestartException {

        JobParameters jobParameters = new JobParameters(Collections.singletonMap("requestTime", new JobParameter(System.currentTimeMillis(), Long.class)));
        jobLauncher.run(yogiyoJob, jobParameters);
    }
}
