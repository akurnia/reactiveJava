package com.mentoring.parallelism.mentoring;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class SubscribeOn {
    public static void main(String[] args){
        Flux<Object> flux = Flux.create(objectFluxSink -> {
                    printThreadName("create");
                    objectFluxSink.next(1);
                })
                .doOnNext(i->printThreadName("next "+i));
        Runnable runnable = ()-> flux
                .doFirst(() -> printThreadName("first2"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> printThreadName("first1"))
                .subscribe(v -> printThreadName("sub "+v));

        for (int i=0; i<2; i++ ){
            new Thread(runnable).start();
        }


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void printThreadName(String msg){
        System.out.println(msg + "\t\t : Thread : " + Thread.currentThread().getName());

    }
}
