package br.edu.facec.sorteio.pessoa.entity

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.UUID

internal class PessoaTest {

    @Test
    internal fun telefone_deve_ser_informado() {
        assertThatThrownBy {
            Pessoa(
                UUID.randomUUID(),
                "Gustavo Salviato",
                "",
                "asdrubal@gmail.com"
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Telefone da pessoa deve ser informado.")
    }

    @Test
    internal fun email_deve_ser_informado() {
        assertThatThrownBy {
            Pessoa(
                UUID.randomUUID(),
                "Gustavo Salviato",
                "4499999999",
                ""
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("E-mail da pessoa deve ser informado.")
    }

    @Test
    internal fun nome_da_pessoa_nao_deve_ser_vazio() {
        assertThatThrownBy {
            Pessoa(
                UUID.randomUUID(),
                "",
                "4499999999",
                "asdrubal@gmail.com"
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Nome da pessoa deve ser informado.")
    }

    @Test
    internal fun deve_possuir_o_id_igual_ao_esperado() {
        val expectedId = UUID.randomUUID()

        val unit = Pessoa(
            telefone = "4499999999",
            id = expectedId,
            nome = "Fulano",
            email = "asdrubal@gmail.com"
        )

        assertThat(unit.hasId(expectedId)).isTrue()
    }

    @Test
    internal fun nao_deve_possuir_o_id_igual_ao_esperado() {
        val expectedId = UUID.randomUUID()

        val unit = Pessoa(
            telefone = "4499999999",
            id = UUID.randomUUID(),
            nome = "Fulano",
            email = "asdrubal@gmail.com"
        )

        assertThat(unit.hasId(expectedId)).isFalse()
    }

}

