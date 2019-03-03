import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

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

    // Function (Method reference that replace lambda function)
    private void printValue(int n) {
        System.out.println("Number is => " + n);
    }

}
