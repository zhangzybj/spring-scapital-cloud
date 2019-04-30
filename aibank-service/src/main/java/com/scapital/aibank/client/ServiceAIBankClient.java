package com.scapital.aibank.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "svcb-service", fallback = ServiceAIBankClient.ServiceAIBankClientFallback.class)
public interface ServiceAIBankClient {

    @GetMapping(value = "/")
    String printServiceB();

    @Component
    class ServiceAIBankClientFallback implements ServiceAIBankClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAIBankClientFallback.class);

        @Override
        public String printServiceB() {
            LOGGER.info("异常发生，进入fallback方法");
            return "SERVICE B FAILED! - FALLING BACK";
        }
    }
}