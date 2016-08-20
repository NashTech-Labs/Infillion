package com.knoldus.websockets

import akka.http.javadsl.server.Route
import akka.http.scaladsl.server.Directives
import akka.http.scaladsl.testkit.{ScalatestRouteTest, WSProbe}
import org.scalatest.{Matchers, WordSpec}

/**
  * Created by harmeet on 17/8/16.
  */
class WSPingPongApiTest extends WordSpec with Matchers with Directives with ScalatestRouteTest {

  "ping pong socket" in {
    val pingPongWebSocket: Route = new WSPingPongApi().connectRoute();
    val wsClient = WSProbe();
    WS("", wsClient.flow) ~> pingPongWebSocket ~> check {

      isWebSocketUpgrade shouldEqual true

      wsClient.sendMessage("James")
      wsClient.expectMessage("Hello James")
    }
  }
}
