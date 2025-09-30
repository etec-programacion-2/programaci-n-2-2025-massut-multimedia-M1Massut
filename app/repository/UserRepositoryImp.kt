package org.example.repository

import org.example.model.User
import org.example.persistence.UsersTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepositoryImpl : UserRepository {

    override fun create(user: Receta) {
        transaction {
            UsersTable.insert {
                it[name] = receta.name
                it[email] = receta.email
            }
        }
    }

    override fun read(id: Int): User? {
        return transaction {
            UsersTable.select { UsersTable.id eq id }
                .map { toUser(it) }
                .singleOrNull()
        }
    }

    override fun readAll(): List<User> {
        return transaction {
            UsersTable.selectAll()
                .map { toUser(it) }
        }
    }

    override fun update(id: Int, user: User) {
        transaction {
            UsersTable.update({ UsersTable.id eq id }) {
                it[name] = receta.name
                it[email] = receta.email
            }
        }
    }

    override fun delete(id: Int) {
        transaction {
            UsersTable.deleteWhere { UsersTable.id eq id }
        }
    }

    private fun toUser(row: ResultRow): User {
        return User(
            id = row[UsersTable.id],
            name = row[UsersTable.name],
            email = row[UsersTable.email]
        )
    }
}