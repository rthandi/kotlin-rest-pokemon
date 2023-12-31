package pokemon.rthandi.core

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import pokemon.rthandi.core.DTO.ErrorResponse
import pokemon.rthandi.core.DTO.PokemonRequest
import pokemon.rthandi.core.DTO.PokemonResponse
import pokemon.rthandi.core.models.Pokemon
import pokemon.rthandi.core.services.PokemonService

fun Application.configureCoreRoutes() {
  routing {
    route("/pokemon") {
      val pokemonService = PokemonService()
      createPokemon(pokemonService)
      findAllPokemons(pokemonService)
    }
  }
}

fun Route.createPokemon(pokemonService: PokemonService) {
  post {
    val request = call.receive<PokemonRequest>()
    val success = pokemonService.createPokemon(pokemonRequest = request,)
    if (success)
      call.respond(HttpStatusCode.Created)
    else
      call.respond(HttpStatusCode.BadRequest, ErrorResponse("Cannot create pokemon"))
  }
}

fun Route.findAllPokemons(pokemonService: PokemonService) {
  get {
    val type = call.request.queryParameters["type"]
    val pokemons = pokemonService.findAllPokemons(type).map(Pokemon::toPokemonResponse)
    call.respond(message = pokemons)
  }
}

private fun Pokemon?.toPokemonResponse(): PokemonResponse? =
  this?.let { PokemonResponse(it.id!!, it.name) }
