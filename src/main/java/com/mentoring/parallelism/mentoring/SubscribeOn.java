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
        flux
                .doFirst(() -> printThreadName("first2"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> printThreadName("first1"))
                .subscribe(v -> printThreadName("sub "+v));



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
