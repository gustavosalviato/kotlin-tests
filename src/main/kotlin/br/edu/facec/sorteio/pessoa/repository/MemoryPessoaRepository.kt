package br.edu.facec.sorteio.pessoa.repository

import br.edu.facec.sorteio.pessoa.entity.Pessoa
import java.util.*

class MemoryPessoaRepository : PessoaRepository {

    private val database: MutableSet<Pessoa> = mutableSetOf()

    override fun save(pessoa: Pessoa) {
        database.add(pessoa)
    }

    override fun existsById(id: UUID): Boolean {
        return database.filter { it.hasId(id) }.isNotEmpty()
    }

    override fun findById(id: UUID): Pessoa? {
        for (pessoa in database) {
            if (pessoa.hasId(id)) {
                return pessoa
            }
        }
        return null
    }

    override fun findAll(): List<Pessoa> {
        return database.toList()
    }

    override fun deleteById(id: UUID) {
        database.removeIf { it.hasId(id) }
    }


}