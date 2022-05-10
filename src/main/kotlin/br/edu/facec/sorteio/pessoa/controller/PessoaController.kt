package br.edu.facec.sorteio.pessoa.controller

import br.edu.facec.sorteio.pessoa.entity.Pessoa
import java.util.*

interface PessoaController {
    fun save(pessoa: Pessoa): UUID
    fun findById(id: UUID): Pessoa
    fun findAll(): List<Pessoa>
    fun deleteById(id: UUID)
}