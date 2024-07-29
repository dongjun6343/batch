package com.rn.batch.delivery.common;

import com.rn.batch.global.util.teams.TeamsWebhookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JobLoggerListener implements JobExecutionListener {
    private static String BEFORE_MESSAGE = "{} job is Running";

    private static String AFTER_MESSGE = "{} job is Done. (Status: {})"; // job의 상태

    @Value("${teams.webhook.batch.url}")
    public String TEAMS_BATCH_URL;

    private final TeamsWebhookService teamsWebhookService;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("beforeJob : " + BEFORE_MESSAGE, jobExecution.getJobInstance().getJobName());
        teamsWebhookService.sendTeamsMessage("[ BATCH JOB START !!! ] \n\n", jobExecution.getJobInstance().getJobName()
                + " 배치 작업 시작 -> " + jobExecution.getStartTime(), TEAMS_BATCH_URL);
    }

    /**
     * FAILED 시 관리자가 바로 알림을 받을 수 있도록 teams webhook 추가
     */
    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("afterJob :" + AFTER_MESSGE, jobExecution.getJobInstance().getJobName(), jobExecution.getStatus());

        if (jobExecution.getStatus() == BatchStatus.FAILED) {
            teamsWebhookService.sendTeamsMessage("[ BATCH JOB FAIL !!! ] \n\n", jobExecution.getJobInstance().getJobName()
                    + " 배치 작업 실패 -> " + jobExecution.getEndTime(), TEAMS_BATCH_URL);
            log.warn("Job is Failed");
        } else {
            teamsWebhookService.sendTeamsMessage("[ BATCH JOB SUCCESS !!! ] \n\n", jobExecution.getJobInstance().getJobName()
                    + " 배치 작업 성공 -> " + jobExecution.getEndTime(), TEAMS_BATCH_URL);
        }
    }
}
