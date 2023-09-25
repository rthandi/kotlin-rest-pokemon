package pokemon.rthandi.core.services

import org.ktorm.database.Database
import org.ktorm.entity.add
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
        }
      }

      "water" -> {
        newPokemon = WaterPokemon {
          name = pokemonRequest.name
        }
      }

      "grass" -> {
        newPokemon = GrassPokemon {
          name = pokemonRequest.name
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

  fun findAllPokemons(): List<Pokemon> {
    return database.sequenceOf(Pokemons).toList()
  }
}