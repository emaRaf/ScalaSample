// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/emanuele.raffero/Downloads/play-scala-starter-example-2.6.x/conf/routes
// @DATE:Wed Apr 03 13:58:28 CEST 2019


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
