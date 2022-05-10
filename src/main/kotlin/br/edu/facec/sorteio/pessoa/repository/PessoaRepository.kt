package br.edu.facec.sorteio.pessoa.repository

import br.edu.facec.sorteio.pessoa.entity.Pessoa
import java.util.UUID

interface PessoaRepository {
    fun save(pessoa: Pessoa)
    fun existsById(id: UUID): Boolean
    fun findById(id: UUID): Pessoa?
    fun findAll(): List<Pessoa>
    fun deleteById(id: UUID)
}