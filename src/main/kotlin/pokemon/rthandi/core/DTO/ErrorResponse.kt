package pokemon.rthandi.core.DTO

import kotlinx.serialization.Serializable

@Serializable
class ErrorResponse {
  val message: String = "Something went wrong"
}