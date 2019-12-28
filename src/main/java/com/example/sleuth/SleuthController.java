package com.example.sleuth;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
public class SleuthController {

    private static final Logger logger = getLogger(SleuthController.class);

    private SleuthService sleuthService;

    public SleuthController(SleuthService sleuthService) {
        this.sleuthService = sleuthService;
    }

    @GetMapping("/")
    public String helloSleuth() {
        logger.info("Hello Sleuth");
        return "success";
    }

    @GetMapping("/same-span")
    public String helloSleuthSameSpan() throws InterruptedException {
        logger.info("Same Span");
        sleuthService.doSomeWorkSameSpan();
        return "success";
    }

    @GetMapping("/new-span")
    public String helloSleuthNewSpan() throws InterruptedException {
        logger.info("New Span");
        sleuthService.doSomeWorkNewSpan();
        return "success";
    }
}
