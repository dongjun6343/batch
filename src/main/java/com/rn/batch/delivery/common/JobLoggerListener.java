package com.rn.batch.delivery.common;

import com.rn.batch.global.util.teams.TeamsWebhookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
public class JobLoggerListener implements JobExecutionListener {
    private static String BEFORE_MESSAGE = "{} job is Running";

    private static String AFTER_MESSGE = "{} job is Done. (Status: {})"; // job의 상태

    //private final TeamsWebhookService teamsWebhookService;

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
            // teamsWebhookService.sendTeamsMessage("","","");
            log.warn("Job is Failed");
        }
    }
}
