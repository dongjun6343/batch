package com.rn.batch.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@Deprecated
public class JobLauncherController {

//    private final Job yogiyoJob;
//    private final JobLauncher jobLauncher;
//
//    @PostMapping("/batch")
//    public String launch() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
//
//        JobParameters jobParameters = new JobParameters(Collections.singletonMap("requestTime", new JobParameter(System.currentTimeMillis(), Long.class)));
//        jobLauncher.run(yogiyoJob, jobParameters); // spring.batch.job.enabled: false 설정!!
//        return "batch completed";
//    }
}
