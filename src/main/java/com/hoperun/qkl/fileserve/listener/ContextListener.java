package com.hoperun.qkl.fileserve.listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ContextListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.debug("event:{},fired", event);
        if (event instanceof ContextStoppedEvent || event instanceof ContextClosedEvent) {
            log.debug("event:{},fired", event);
            StackTraceElement[] sts = Thread.currentThread().getStackTrace();
            if(sts != null && sts.length>0){
                  for(StackTraceElement se : sts){
                       log.info(se.toString());
                  }
            }
        }
    }
}
