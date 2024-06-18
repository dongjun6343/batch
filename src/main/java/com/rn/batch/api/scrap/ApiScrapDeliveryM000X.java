package com.rn.batch.api.scrap;

import com.rn.batch.api.dto.DeliveryM0001RequestDto;
import com.rn.batch.api.dto.DeliveryM0001ResponseDto;
import com.rn.batch.api.service.ApiScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiScrapDeliveryM000X {
    private final ApiScrapService apiScrapService;

    public DeliveryM0001ResponseDto getM0001(DeliveryM0001RequestDto requestDto) {
        return apiScrapService.requestScrapData(requestDto, DeliveryM0001ResponseDto.class);
    }
}
