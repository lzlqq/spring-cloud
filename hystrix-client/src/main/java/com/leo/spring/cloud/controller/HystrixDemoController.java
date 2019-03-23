package com.leo.spring.cloud.controller;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class HystrixDemoController {

    private final Random random =new Random();
    /**
     * 当{@link #helloWorld2()} 方法调用超市货失败时，
     * fallback方法{@link #errorContent()}作为替代返回
     * @return
     */
    @GetMapping("hello-world2")
    public String helloWorld2() throws InterruptedException {
      return new HelloWorldCommand().execute();
    }

    private class HelloWorldCommand extends com.netflix.hystrix.HystrixCommand<String>{

        protected HelloWorldCommand() {
            super(HystrixCommandGroupKey.Factory.asKey("HelloWorld"),
                    100);
        }

        @Override
        protected String run() throws Exception {
                  //超时触发容错
            int value =random.nextInt(200);
            System.out.println("helloWorld2() costs "+value+" ms.");
            Thread.sleep(value);
            return "Hello World2";
        }

        protected  String getFallback(){
            return HystrixDemoController.this.errorContent();
        }
    }

    /**
     * 当{@link #helloWorld()} 方法调用超市货失败时，
     * fallback方法{@link #errorContent()}作为替代返回
     * @return
     */
    @GetMapping("hello-world")
    @HystrixCommand(fallbackMethod = "errorContent",
        commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
            value = "100")
        }
    )
    public String helloWorld() throws InterruptedException {
        //超时触发容错
        int value =random.nextInt(200);
        System.out.println("helloWorld() costs "+value+" ms.");
        Thread.sleep(value);
        return "Hello World";
    }

    /**
     * AbstractQueuedSynchronizer#acquire,#release
     * @return
     */
    public String errorContent(){
        return "Fault";
    }
}
