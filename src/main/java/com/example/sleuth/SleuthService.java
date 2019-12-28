package com.example.sleuth;

import brave.Span;
import brave.Tracer;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class SleuthService {
    private static final Logger logger = getLogger(SleuthService.class);

    private Tracer tracer;

    public SleuthService(Tracer tracer) {
        this.tracer = tracer;
    }

    public void doSomeWorkSameSpan() throws InterruptedException {
        Thread.sleep(1000L);
        logger.info("Doing some work");
    }

    public void doSomeWorkNewSpan() throws InterruptedException {
        logger.info("I'm in the original span");

        Span newSpan = tracer.nextSpan().name("newSpan").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
            Thread.sleep(1000L);
            logger.info("I'm in the new span doing some cool work that needs its own span");
        } finally {
            newSpan.finish();
        }

        logger.info("I'm in the original span");
    }
}
