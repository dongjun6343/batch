package com.rn.batch.scheduler;

import com.rn.batch.global.util.teams.TeamsWebhookService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class CpeatsStoreScheduler {

    private final JobLauncher jobLauncher;
    private final Job cpeatsStoreJob;
    private final TeamsWebhookService teamsWebhookService;

    @Value("${teams.webhook.batch.url}")
    public String TEAMS_BATCH_URL;

    @Scheduled(cron = "0 */5 * * * ?") // 10분마다 배치 - 테스트
//    @Scheduled(cron = "0 0 1,19 * * ?")
    public void cpeatsStoreJobRun() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException,
            JobParametersInvalidException, JobRestartException {

        teamsWebhookService.sendTeamsMessage("[BATCH JOB START]", "쿠팡이츠 배달앱 스크래핑을 시작합니다.", TEAMS_BATCH_URL);

        JobParameters jobParameters = new JobParameters(Collections.singletonMap("requestTime", new JobParameter(System.currentTimeMillis(), Long.class)));
        jobLauncher.run(cpeatsStoreJob, jobParameters);
    }
}
