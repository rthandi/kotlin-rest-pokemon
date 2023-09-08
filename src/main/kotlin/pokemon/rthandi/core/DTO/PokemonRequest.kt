package pokemon.rthandi.core.DTO

import kotlinx.serialization.Serializable

@Serializable
data class PokemonRequest(
  val id: Long,
  val name: String,
)