# reactive
Test of the frameworks RxJava 2

## Non-format definition
`Reactive programming is programming with asynchronous data streams`
- _Stream_ : A stream is a sequence of observable events ordered in time. / The streams is the subject (or "observable") being observed.
- _Obseravble_ : A collection of items over the time.
- _Subscribing_ : The operation of "listening" to the streams.
- _Observer_ (or Subscriber) : The object that listen to the event. The function (often callback) are observers. Subscribers consume items from Obserable.
- _FRP_ : Programming with reactive systems combined with functional operators.

## Difference
In 1.x the difference between Observer and Subscriber is that a Subscriber allows to subscribe and unsubscribe, however an Observer only allows to subscribe.

But in 2.x Observer is used to subscribe to an Observable, and Subscriber is used to subscribe to a Flowable. And if you want to be able to unsubscribe, you need to use ResourceObserver and ResourceSubscriber respectively.

Also Subscriber support backpressure?.

Source : https://github.com/ReactiveX/RxJava/issues/4515#issue-175814190 / https://stackoverflow.com/a/41634705

# Benefits
FP Raises the level of abstraction of my code, so that i can focus on the interdependance of events that define the business logic, rather than having to constantly fiddle with a large amount of implementation details. __code in RP will be more consise__.
The benefits is more evident for modern webapps and mobile apps that are highly interactive with a multitude of UI events related to data events. Apps has evolved to be more real-time. App nowadays have an abundatly of real-time events of every kind that enable a highly interactive experience to the users.

source :
- https://en.wikipedia.org/wiki/Observer_pattern
- https://gist.github.com/staltz/868e7e9bc2a7b8c1f754
