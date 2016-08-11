package com.knoldus.controllers;

import static akka.http.javadsl.server.Directives.get;
import static akka.http.javadsl.server.Directives.route;
import static akka.http.javadsl.server.Directives.path;
import static akka.http.javadsl.server.Directives.complete;
import akka.http.javadsl.server.Route;

/**
 * Created by harmeet on 9/8/16.
 */
public class PingPongApi  {

    public final Route handleGetPingRequest() {
        return get(() -> route(
                path("ping", () -> complete("pong"))
        ));
    }
}
