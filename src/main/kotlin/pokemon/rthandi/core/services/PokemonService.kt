package pokemon.rthandi.core.services

import org.ktorm.database.Database

class PokemonService {
  private val database = Database.connect(
    url = "jdbc:postgresql://localhost:5438/postgres",
    driver = "org.postgresql.Driver",
    user = "postgres",
    password = "postgres"
  )
}