package com.rn.batch.api.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SvcCd {
    R0001("배달앱 계정정보"),
    P0001("배달앱 매출조회"),
    P0002("배달앱 정산내역"),
    P0006("음식점정보조회"),
    M0001("배달앱 부가세신고내역")
    ;

    private final String description;
}
