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
public class CpeatsBatchConfig {

    private final CpeatsTasklet cpeatsTasklet;

    @Bean
    public Job cpeatsJob(JobRepository jobRepository, Step jobCpeatsListenerStep) {
        return new JobBuilder("cpeatsJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(jobCpeatsListenerStep)
                .listener(new JobLoggerListener())
                .build();
    }

    @Bean
    public Step jobCpeatsListenerStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("jobCpeatsListenerStep", jobRepository)
                .tasklet(cpeatsTasklet, platformTransactionManager)
                .build();
    }
}
