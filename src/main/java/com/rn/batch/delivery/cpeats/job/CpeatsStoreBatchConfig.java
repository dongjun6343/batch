package com.rn.batch.delivery.cpeats.job;

import com.rn.batch.delivery.common.JobLoggerListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CpeatsStoreBatchConfig {

    private final CpeatsStoreTasklet cpeatsStoreTasklet;
    private final JobLoggerListener jobLoggerListener;

    @Bean
    public Job cpeatsStoreJob(JobRepository jobRepository, Step jobCpeatsListenerStep) {
        return new JobBuilder("cpeatsStoreJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(jobCpeatsListenerStep)
                .listener(jobLoggerListener)
                .build();
    }

    @Bean
    public Step jobCpeatsListenerStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("jobCpeatsListenerStep", jobRepository)
                .tasklet(cpeatsStoreTasklet, platformTransactionManager)
                .build();
    }
}
