package pokemon.rthandi.core.services

import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.add
import org.ktorm.entity.filter
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList
import pokemon.rthandi.core.DTO.PokemonRequest
import pokemon.rthandi.core.models.*

class PokemonService {
  private val database = Database.connect(
    url = "jdbc:postgresql://localhost:5438/postgres",
    driver = "org.postgresql.Driver",
    user = "postgres",
    //TODO: Set this in env vars
    password = "postgres"
  )

  fun createPokemon(pokemonRequest: PokemonRequest): Boolean {
    val newPokemon: Pokemon
    when (pokemonRequest.type) {
      "fire" -> {
        newPokemon = FirePokemon {
          name = pokemonRequest.name
          type = "fire"
        }
      }

      "water" -> {
        newPokemon = WaterPokemon {
          name = pokemonRequest.name
          type = "water"
        }
      }

      "grass" -> {
        newPokemon = GrassPokemon {
          name = pokemonRequest.name
          type = "grass"
        }
      }

      else -> {
        newPokemon = Pokemon {
          name = pokemonRequest.name
        }
      }
    }

    val result = database.sequenceOf(Pokemons).add(newPokemon)

    return result == 1
  }

  fun findAllPokemons(type: String?): List<Pokemon> {
    if (type == null)
      return database.sequenceOf(Pokemons).toList()
    return database.sequenceOf(Pokemons)
      .filter { it.type eq type }
      .toList()
  }
}