package com.java.rxjava;

import com.java.concurrent.executor.ScheduledThread;
import io.reactivex.*;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import java.util.*;

/**
 * 学习RXJava2.0
 * http://gank.io/post/560e15be2dca930e00da1083#toc_11
 * 一个在Java VM上使用可观测的序列来组成异步的、基于事件的程序的库
 */
public class LittleRxJava2 {
    public static void main(String[] args) {
        System.out.println("==================基本使用==================");
        testObservable();
        System.out.println("==================线程调度==================");
        testThreadSchedule();
        System.out.println("==================变换==================");
        testTransformation();
        System.out.println("==================flatMap==================");
        testFlatMap();
        System.out.println("==================concatMap==================");
        testConcatMap();
    }

    /**
     * 基本使用
     * Observable是被观察者, Observer是观察者, subscribe表示订阅
     */
    private static void testObservable() {
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> { // 第一步.初始化Observable
            System.out.println("Observable emit 1" + ", thread=" + Thread.currentThread().getName());   // RxJava默认事件的生产和消费都是在同一个线程、即被观察者和观察者在同一个线程
            emitter.onNext(1);  // emitter不能发射null, emitter.onNext(null)
            System.out.println("Observable emit 2");
            emitter.onNext(2);
            System.out.println("Observable emit 3");
            emitter.onNext(3);
            emitter.onComplete();
            System.out.println("Observable emit 4");
            emitter.onNext(4);
        }).subscribe(new Observer<Integer>() {  // 第三步.订阅
            // 第二步.初始化Observer
            private Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe" + ", thread=" + Thread.currentThread().getName());
                mDisposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext integer=" + integer + ", isDisposed=" + mDisposable.isDisposed());
                if (integer == 2) {
                    // Disposable可以做到切断的操作, 使Observer观察者不再接收上游事件, 表示解除订阅
                    mDisposable.dispose();
                    System.out.println("isDisposed=" + mDisposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError, e=" + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    /**
     * 线程调度
     */
    private static void testThreadSchedule() {
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            System.out.println("Observable thread name is " + Thread.currentThread().getName());
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onComplete();
        }).subscribeOn(Schedulers.io())  // subscribeOn指定事件生产的线程, 即被观察者的运行线程
                .observeOn(Schedulers.single())    // observeOn指定事件消费的线程, 即观察者的运行线程
                .subscribe(integer -> {
                    System.out.println("After observeOn(single)，Current thread is " + Thread.currentThread().getName() + ", value=" + integer);
                });
    }

    /**
     * 变换
     */
    private static void testTransformation() {
        String[] emitterValue = {"1", "2", "3"};
        Observable.fromArray(emitterValue).map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return Integer.valueOf(s);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("从String变换为Integer: " + integer);
            }
        });
    }

    /**
     * flatMap
     */
    private static void testFlatMap() {
        List<String> list1 = new ArrayList<>();
        list1.add("list-1");
        list1.add("list-2");
        list1.add("list-3");
        list1.add("list-4");

        Observable.just(list1).flatMap(new Function<List<String>, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(List<String> strings) throws Exception {
                return Observable.fromArray(strings.toArray(new String[strings.size()]));
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("flatMap变换---s=" + s);
            }
        });
    }

    /**
     * concatMap
     */
    private static void testConcatMap() {
        List<String> list1 = new ArrayList<>();
        list1.add("list-1");
        list1.add("list-2");
        list1.add("list-3");
        list1.add("list-4");

        Observable.just(list1).concatMap(new Function<List<String>, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(List<String> strings) throws Exception {
                return Observable.fromArray(strings.toArray(new String[strings.size()]));
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("concatMap变换---s=" + s);
            }
        });
    }
}
