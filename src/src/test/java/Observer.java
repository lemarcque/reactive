import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Observer {


    @Test
    @Ignore
    public void testObserver() {
        // Create an Observable
        Observable<String> observable = new Observable<String>() {
            @Override
            protected void subscribeActual(io.reactivex.Observer<? super String> observer) {
                observer.onNext("Salut");
                observer.onComplete();
            }
        };

        // Create an Observer
        io.reactivex.Observer<String> observer = new io.reactivex.Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("Stream diffuse : " + s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Stream finish with error : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Streams are completed.");
            }
        };

        // Subscribing
        observable.subscribe(observer);
    }

    @Test
    @Ignore
    public void testLambdaObserver() {

        // Create an Observable
        Observable
                .just(100, 32, 300, 45, 59, 150)
                .filter(n -> n > 100)
                .map(n -> (int) n / 10)
                .doOnNext(this::printValue)     // Method reference as an input of the function
                .doOnComplete(() -> System.out.println("The Stream has completed."))
                .subscribe();
    }

    @Test
    @Ignore
    public void testMap() {

        Function<Integer, Integer> powTwo = n -> n * n;
        Function<List<Integer>, int[]> copy = ints -> {
            int[] newTab = new int[ints.size()];
            int index = 0;
            for(int n : ints) {
                newTab[index] = n;
                index++;
            }

            return newTab;
        };

        Function<int[], int[]> mapPow = new Function<int[], int[]>() {
            @Override
            public int[] apply(int[] ints) throws Exception {
                int[] newTab = new int[ints.length];
                int index = 0;
                for(int n : ints) {
                    newTab[index] = n * n;
                    index++;
                }

                return newTab;
            }
        };

        Consumer<List<Integer>> printArray = ints -> {
            for(int n : ints) {
                System.out.println(n);
            }
        };

        // Create List
        List<Integer> hundred = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            hundred.add(i);
        }

        Observable
                .fromArray(hundred)
                .map(copy)
                .flatMap((Function<int[], ObservableSource<List<Integer>>>) ints -> {
                    List<Integer> result = new ArrayList<>();

                    for(int n : ints) {
                        result.add(n);
                        result.add(n * 1000);
                    }
                    return Observable.just(result);
                })
                // convert observable of array to emit one item at a time
                .flatMap(Observable::fromIterable)
                .map(n -> n * n)
                .filter(n -> n  < 100)
                .take(5)
                .doOnNext(System.out::println)
                .doOnError(System.out::println)
                .subscribe();
    }

    @Test
    public void testSingle() {
        // Create a Single stream
        Single
                .just("Pomme")
                .doOnError((e) -> System.out.println("Process terminated with ERROR : " + e.getMessage()))
                .doOnSuccess(s -> System.out.println(("Print single : " + s)))
                .doOnTerminate(() -> System.out.println("Process terminated"))
                .subscribe();
        
    }

    @Test
    public void testIterator() {
        List<Integer> numbers = Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 8, 9
        );


        Iterator<Integer> iterator = numbers.iterator();
        while(iterator.hasNext()) {
            System.out.println("Iterator : " + iterator.next());
        }
    }


    // Function (Method reference that replace lambda function)
    private void printValue(int n) {
        System.out.println("Number is => " + n);
    }

}
