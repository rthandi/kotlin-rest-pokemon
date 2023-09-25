package pokemon.rthandi

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import pokemon.rthandi.core.configureCoreRoutes
import kotlin.test.*
import pokemon.rthandi.plugins.*

class ApplicationTest {
  @Test
  fun testRoot() = testApplication {
    application {
      configureCoreRoutes()
    }
    client.get("/").apply {
      assertEquals(HttpStatusCode.OK, status)
      assertEquals("Hello World!", bodyAsText())
    }
  }
}
