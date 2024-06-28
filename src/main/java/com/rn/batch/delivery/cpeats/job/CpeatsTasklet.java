package com.rn.batch.delivery.cpeats.job;


import com.rn.batch.api.code.MallCd;
import com.rn.batch.api.code.OrgCd;
import com.rn.batch.delivery.common.DeliveryService;
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
public class CpeatsTasklet implements Tasklet {

    private final DeliveryService deliveryService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        log.info("CpeatsTasklet start!!!");
        deliveryService.vatHist(MallCd.CPE, OrgCd.cpeats);
        return RepeatStatus.FINISHED;
    }
}
