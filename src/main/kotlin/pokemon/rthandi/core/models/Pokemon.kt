package pokemon.rthandi.core.models

import kotlinx.serialization.Serializable
import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.long
import org.ktorm.schema.varchar

interface Pokemon: Entity<Pokemon> {
  companion object: Entity.Factory<Pokemon>()

  val id: Long?
  var name: String
  val type: String?
}

interface FirePokemon: Pokemon {
  companion object: Entity.Factory<Pokemon>()

  override val type: String
    get() = "fire"
}

interface WaterPokemon: Pokemon {
  companion object: Entity.Factory<Pokemon>()

  override val type: String
    get() = "water"
}

interface GrassPokemon: Pokemon {
  companion object: Entity.Factory<Pokemon>()

  override val type: String
    get() = "grass"
}

object Pokemons: Table<Pokemon>("pokemons") {
  val id = long("id").primaryKey().bindTo(Pokemon::id)
  val name = varchar("name").bindTo(Pokemon::name)
  val type = varchar("type").bindTo(Pokemon::type)
}