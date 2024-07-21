package com.rn.batch.api.dto;

import com.rn.batch.api.code.DeliveryCd;
import com.rn.batch.api.code.MallCd;
import lombok.Builder;
import lombok.Data;

@Data
public class DeliveryP0006RequestDto {
    private String appCd = "ReadNumberApp";
    private String orgCd = "mall";
    private MallCd mallCd;
    private DeliveryCd svcCd = DeliveryCd.P0006;
    private String userId;
    private String userPw;
    private String dateFrom;
    private String dateTo;
    private String detailYn = "N";
    private String detailListYn = "N";
    private String processYn = "N";
    private String langType = "ENG";

    @Builder
    public DeliveryP0006RequestDto(MallCd mallCd, String userId, String userPw, String dateFrom, String dateTo) {
        this.mallCd = mallCd;
        this.userId = userId;
        this.userPw = userPw;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}
