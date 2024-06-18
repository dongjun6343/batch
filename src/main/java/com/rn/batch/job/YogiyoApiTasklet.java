package com.rn.batch.job;


import com.rn.batch.api.code.MallCd;
import com.rn.batch.delivery.yogiyo.service.YogiyoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class YogiyoApiTasklet implements Tasklet {

    private final YogiyoService yogiyoService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("YogiyoApiTasklet start!!!");
        yogiyoService.vatHist(MallCd.YO);
        return RepeatStatus.FINISHED;
    }
}
