package com.airline.booking_ms.model.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "UserFeignClient",url = "http://localhost:9093/user-ms/")
public interface UserFeignClient {

    @GetMapping("/user/checking-auth")
    boolean jwtAuthCheckUser(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String auth,
                         @RequestHeader(name = "user-id") Long adminId);



}

