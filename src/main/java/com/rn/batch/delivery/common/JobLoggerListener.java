package com.rn.batch.delivery.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

@Slf4j
public class JobLoggerListener implements JobExecutionListener {
    private static String BEFORE_MESSAGE = "{} job is Running";

    private static String AFTER_MESSGE = "{} job is Done. (Status: {})"; // job의 상태

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("beforeJob : " + BEFORE_MESSAGE, jobExecution.getJobInstance().getJobName());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("afterJob :" + AFTER_MESSGE,
                jobExecution.getJobInstance().getJobName(),
                jobExecution.getStatus());

        if (jobExecution.getStatus() == BatchStatus.FAILED) {
            // todo FAILED 시 관리자가 바로 알림을 받을 수 있도록 teams webhook 추가
            log.warn("Job is Failed");
        }
    }
}
