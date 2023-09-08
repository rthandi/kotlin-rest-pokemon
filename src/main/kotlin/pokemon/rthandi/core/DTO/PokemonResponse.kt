package pokemon.rthandi.core.DTO

import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
  val id: Long,
  val name: String,
)
