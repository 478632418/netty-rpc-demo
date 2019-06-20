# netty-rpc-demo
1）其中netty-rpc是根据《自己用 Netty 实现一个简单的 RPC https://blog.csdn.net/qq_38182963/article/details/79764922》 <br>博文中代码整理的。
该实现方式未实现服务发现功能。<br>
2）如果想知道服务发现原理，可以参考《RPC基本原理以及如何用Netty来实现RPC https://www.jianshu.com/p/8876c9f3cd7f》,<br>内部实现原理，将服务ip和端口存储到zk的一个数上，实现共享。<br>
3）其中Reactor-Pattern是来自《Reactor模式详解＋源码实现 https://www.jianshu.com/p/188ef8462100》.<br>
Reactor模式是事件驱动模型，有一个或多个并发输入源，有一个Service Handler，有多个Request Handlers；这个Service Handler会同步的将输入的请求（Event）多路复用的分发给相应的Request Handler。从结构上，这有点类似生产者消费者模式，即有一个或多个生产者将事件放入一个Queue中，而一个或多个消费者主动的从这个Queue中Poll事件来处理；而Reactor模式则并没有Queue来做缓冲，每当一个Event输入到Service Handler之后，该Service Handler会主动的根据不同的Event类型将其分发给对应的Request Handler来处理。<br>
4）如果搞明白Spark中RpcEnv,Endpoint,EndpointRef，可以参考《深入解析Spark中的RPC https://blog.csdn.net/imgxr/article/details/80131262》，<br>作者是清华研究生，他将SPARK的RPC模块单独拉出来独立为一个Netty RPC功能，用来分析SPAR RPC实现结构，从这个代码参考更容易点。<br>
