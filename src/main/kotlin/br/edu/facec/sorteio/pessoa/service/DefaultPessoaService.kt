package br.edu.facec.sorteio.pessoa.service

import br.edu.facec.sorteio.pessoa.entity.Pessoa
import br.edu.facec.sorteio.pessoa.repository.PessoaRepository
import java.time.LocalDate
import java.util.*

class DefaultPessoaService(
    private val pessoaRepository: PessoaRepository
) : PessoaService {

    override fun save(pessoa: Pessoa): UUID {
        if (!pessoa.hasNascimentoLessThan(LocalDate.now())) {
            throw IllegalArgumentException("A pessoa deve ter data de nascimento menor do que o data atual")
        }
        pessoaRepository.save(pessoa)
        return pessoa.id
    }

    override fun findById(id: UUID): Pessoa? {
        val found = pessoaRepository.findById(id)
        if (found == null) {
            throw IllegalStateException("Pessoa não encontrada.")
        }
        return found
    }

    override fun findAll(): List<Pessoa> {
        return pessoaRepository.findAll()
    }

    override fun deleteById(id: UUID) {
        if (!pessoaRepository.existsById(id)){
            throw IllegalStateException("A pessoa que você deseja remover não existe.")
        }
        pessoaRepository.deleteById(id)
    }

}