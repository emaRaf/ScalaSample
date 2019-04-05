// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/emanuele.raffero/Documents/git/ScalaSample/conf/routes
// @DATE:Fri Apr 05 10:26:55 CEST 2019


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
