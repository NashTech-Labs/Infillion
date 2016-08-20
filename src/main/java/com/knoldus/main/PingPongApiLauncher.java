package com.knoldus.main;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import com.knoldus.routes.InfillionRoutes;


import java.io.IOException;
import java.util.concurrent.CompletionStage;

/**
 * Created by harmeet on 9/8/16.
 */
public class PingPongApiLauncher {
    public static void main(String[] args) throws IOException {
        final ActorSystem system = ActorSystem.create();
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        final Route route = InfillionRoutes.routes();
        final Flow<HttpRequest, HttpResponse, NotUsed> flow = route.flow(system, materializer);

        final Http http = Http.get(system);
        final CompletionStage<ServerBinding> bindings = http.bindAndHandle(flow, ConnectHttp.toHost("127.0.0.1", 8080), materializer);

        System.out.println("Type RETURN to exit");
        System.in.read();

        bindings
                .thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate());

    }
}
