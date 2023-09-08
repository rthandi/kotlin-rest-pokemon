package pokemon.rthandi.core

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import pokemon.rthandi.core.DTO.ErrorResponse
import pokemon.rthandi.core.DTO.PokemonRequest
import pokemon.rthandi.core.services.PokemonService

fun Application.configureCoreRoutes() {
  routing {
    route("/") {
      val pokemonService = PokemonService()
      createPokemon(pokemonService)
    }
  }
}

fun Route.createPokemon(pokemonService: PokemonService) {
  post {
    val request = call.receive<PokemonRequest>()
    val success = pokemonService.createPokemon(pokemonRequest = request)
    if (success)
      call.respond(HttpStatusCode.Created)
    else
      call.respond(HttpStatusCode.BadRequest, ErrorResponse("Cannot create book"))
  }
}