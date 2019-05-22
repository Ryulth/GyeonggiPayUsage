package com.ryulth.gyeonggipay.dto;

import lombok.*;

import javax.persistence.Id;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Usage {
    @Id
    private long id;
    private String name;
    private String address;
    private String latitude;
    private String longitude;
    private String url;
    @Builder
    private Usage(String name, String address, String latitude, String longitude, String url){
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.url = url;
    }
}
