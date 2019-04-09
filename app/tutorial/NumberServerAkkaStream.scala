package tutorial

import akka.stream.scaladsl.Source
import akka.util.ByteString
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.{Directive, RequestContext, Route, RouteResult}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Flow

import scala.io.StdIn

object NumberServerAkkaStream {

  def main(args: Array[String]): Unit = {


    implicit val actorSystem = ActorSystem("system")
    implicit val actorMaterializer = ActorMaterializer()
    implicit val executionContext = actorSystem.dispatcher

    val numbers = Source.unfold(0L) { (n) =>
      val next = n + 1
      Some((next, next))
    }.map(n => ByteString(n + "\r\n"))

    val route = path("numbers") {
      /*get {
      complete(HttpEntity(ContentTypes.`text/html(UTF-8)`,numbers))
    }*/
      get {
        complete(HttpResponse(entity = HttpEntity(ContentTypes.`text/html(UTF-8)`, numbers)))
      }
    }
    val map = Flow[RequestContext].map(route)
    val bindingFuture = Http().bindAndHandle(route, "localhost", 9898)
    println(s"Server online at http://localhost:9898/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => actorSystem.terminate()) // and shutdown when done


  }

}
