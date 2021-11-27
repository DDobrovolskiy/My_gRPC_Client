package ru.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import ru.example.grpc.GreetingServiceGrpc;
import ru.example.grpc.GreetingServiceOuterClass;

import java.nio.channels.Channel;
import java.util.Iterator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forTarget("localhost:8080")
                .usePlaintext()
                .build();

        GreetingServiceGrpc.GreetingServiceBlockingStub stub =
                GreetingServiceGrpc.newBlockingStub(managedChannel);

        GreetingServiceOuterClass.HelloRequest request = GreetingServiceOuterClass
                .HelloRequest
                .newBuilder()
                .setName("Dmitriy")
                .build();

        Iterator<GreetingServiceOuterClass.HelloResponse> response = stub.greeting(request);

        while (response.hasNext()) {
            System.out.println(response.next());
        }

        managedChannel.shutdownNow();
    }
}
