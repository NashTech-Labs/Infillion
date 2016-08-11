package com.knoldus.controllers;

import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.testkit.JUnitRouteTest;
import akka.http.javadsl.testkit.TestRoute;
import org.junit.Test;

/**
 * Created by harmeet on 9/8/16.
 */
@SuppressWarnings("serial")
public class PingPongApiTest extends JUnitRouteTest {

    @Test
    public void testHandleGetPingRequest() {
        TestRoute route = testRoute(new PingPongApi().handleGetPingRequest());

        route.run(HttpRequest.GET("/ping"))
                .assertStatusCode(StatusCodes.OK)
                .assertEntity("pong");
    }
}
