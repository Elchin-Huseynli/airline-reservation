package com.airline.flight_ms.model.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "UserFeignClient",url = "http://localhost:9093/user-ms/")
public interface UserFeignClient {

    @GetMapping("/admin/checking-auth")
    boolean jwtAuthCheck(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String auth,
                         @RequestHeader(name = "admin-id") Long adminId);
}
