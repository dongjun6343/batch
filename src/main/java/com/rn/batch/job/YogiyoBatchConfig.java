package com.rn.batch.job;

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
public class YogiyoBatchConfig {

    private final YogiyoApiTasklet yogiyoApiTasklet;

    @Bean
    public Job yogiyoJob(JobRepository jobRepository, Step jobListenerStep) {
        return new JobBuilder("yogiyoJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(jobListenerStep)
                .listener(new JobLoggerListener())
                .build();
    }

    @Bean
    public Step jobListenerStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("jobListenerStep", jobRepository)
                .tasklet(yogiyoApiTasklet, platformTransactionManager)
                .build();
    }
}
