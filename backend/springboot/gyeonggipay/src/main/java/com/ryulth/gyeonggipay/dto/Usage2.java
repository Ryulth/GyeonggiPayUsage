//package com.ryulth.gyeonggipay.dto;
//
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "usage")
//@ToString
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class Usage {
//    @Id
//    @Column(name = "usage_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(name = "usage_name", nullable = false)
//    private String name;
//
//    @Column(name = "usage_address",nullable = false)
//    private String address;
//
//    @Column(name = "usage_latitude")
//    private String latitude;
//
//    @Column(name = "usage_longitude")
//    private String longitude;
//
//    @Column(name ="usage_url")
//    private String url;
//
//    @Builder
//    private Usage(String name, String address, String latitude, String longitude, String url){
//        this.name = name;
//        this.address = address;
//        this.latitude = latitude;
//        this.longitude = longitude;
//        this.url = url;
//    }
//
//}
