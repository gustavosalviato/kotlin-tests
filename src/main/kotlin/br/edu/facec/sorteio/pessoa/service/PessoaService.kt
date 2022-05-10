package br.edu.facec.sorteio.pessoa.service

import br.edu.facec.sorteio.pessoa.entity.Pessoa
import java.util.*

interface PessoaService {
    fun save(pessoa: Pessoa): UUID
    fun findById(id: UUID): Pessoa?
    fun findAll(): List<Pessoa>
    fun deleteById(id: UUID)
}