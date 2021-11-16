package com.mentoring.parallelism.mentoring;

import reactor.core.publisher.Flux;

public class ThreadDemo {
    public static void main(String[] args){
        Flux<Object> flux = Flux.create(objectFluxSink -> {
            printThreadName("create");
            objectFluxSink.next(1);
        })
                .doOnNext(i->printThreadName("next "+i));
        flux.subscribe(v -> printThreadName("sub "+v));
    }

    private static void printThreadName(String msg){
        System.out.println(msg + "\t\t : Thread : " + Thread.currentThread().getName());

    }
}
