package com.knoldus.routes;

import akka.http.javadsl.server.Route;
import com.knoldus.controllers.PingPongApi;
import com.knoldus.websockets.WSPingPongApi;
import static akka.http.javadsl.server.Directives.route;

/**
 * Created by harmeet on 11/8/16.
 */

public final class InfillionRoutes {

    private InfillionRoutes(){}

    private static Route getHttpPingPongRoute(){
        final PingPongApi httpPingPongApi = new PingPongApi();
        return  httpPingPongApi.handleGetPingRequest();
    }

    private static Route getWSPingPongRoute(){
        final WSPingPongApi wsPingPongApi = new WSPingPongApi();
        return wsPingPongApi.connectRoute();
    }

    public static Route routes(){
        return route(getHttpPingPongRoute(), getWSPingPongRoute());
    }
}
