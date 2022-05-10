package br.edu.facec.sorteio.pessoa.entity.service

import br.edu.facec.sorteio.pessoa.entity.Pessoa
import br.edu.facec.sorteio.pessoa.repository.PessoaRepository
import br.edu.facec.sorteio.pessoa.service.DefaultPessoaService
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.useDefaultRepresentation
import org.assertj.core.error.ConditionAndGroupGenericParameterTypeShouldBeTheSame
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import java.util.*

internal class DefaultPessoaServiceTest {

    @Test
    internal fun deve_listar_todas_as_pessoas() {
        val repository = mock<PessoaRepository>()
        val unit = DefaultPessoaService(repository)

        unit.findAll()


        assertThat(unit.findAll().isNullOrEmpty())
    }

    @Test
    internal fun deve_disparar_execao_quando_id_da_pessoa_nao_encontrado() {
        val repository = mock<PessoaRepository>()
        val unit = DefaultPessoaService(repository)
        val id = UUID.randomUUID()

        whenever(repository.findById(id)).thenReturn(null)

        Assertions.assertThatThrownBy{
            unit.deleteById(id)
        }.isInstanceOf(IllegalStateException::class.java)
            .hasMessage("A pessoa que você deseja remover não existe.")
    }



    @Test
    internal fun deve_excluir_a_pessoa_pelo_id() {
        val repository = mock<PessoaRepository>()
        val id = UUID.randomUUID()

        val pessoa = Pessoa(
            id,
            "Asdrubral",
            "4499999999",
            "asdrubal@gmail.com"
        )

        val unit = DefaultPessoaService(repository);

        if (repository.existsById(id)){
            unit.deleteById(id)
        }

    }

    @Test
    internal fun deve_encontrar_a_pessoa_pelo_id() {
        val repository = mock<PessoaRepository>()
        val id = UUID.randomUUID()

        val pessoa = Pessoa(
            id,
            "Asdrubral",
            "4499999999",
            "asdrubal@gmail.com"
        )

        whenever(repository.findById(id)).thenReturn(pessoa)

        val unit = DefaultPessoaService(repository)

        val resultado = unit.findById(id)

        assertThat(resultado).isEqualTo(pessoa)

        verify(repository, times(1)).findById(id)
        verifyNoMoreInteractions(repository)
    }

    @Test
    internal fun deve_disparar_excecao_quando_nao_encontrar_a_pessoa_pelo_id() {
        val repository = mock<PessoaRepository>()
        val unit = DefaultPessoaService(repository)
        val id = UUID.randomUUID()
        whenever(repository.findById(id)).thenReturn(null)

        Assertions.assertThatThrownBy {
            unit.findById(id)
        }.isInstanceOf(IllegalStateException::class.java)
            .hasMessage("Pessoa não encontrada.")

        verify(repository, times(1)).findById(id)
        verifyNoMoreInteractions(repository)
    }
}

