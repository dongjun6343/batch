package com.rn.batch.api.scrap;

import com.rn.batch.api.dto.DeliveryP0006RequestDto;
import com.rn.batch.api.dto.DeliveryP0006ResponseDto;
import com.rn.batch.api.service.ApiScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiScrapDeliveryP000X {
    private final ApiScrapService apiScrapService;

    public DeliveryP0006ResponseDto getP0006(DeliveryP0006RequestDto requestDto) {
        return apiScrapService.requestScrapData(requestDto, DeliveryP0006ResponseDto.class);
    }
}
