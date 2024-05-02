package br.com.fiap.vendaCRUD.dto.produtoDto;

import br.com.fiap.vendaCRUD.model.TipoProduto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AtualizacaoProdutoDto(

        @NotBlank(message = "O nome não pode estar em branco")
        String nome,

        @NotBlank(message = "A descrição não pode estar em branco")
        String descricao,

        @NotNull(message = "O preço não pode estar em branco")
        @Positive(message = "O preço deve ser um número positivo")
        Double preco,

        @NotNull(message = "O tipo do produto não pode estar em branco")
        TipoProduto tipoProduto) {
}
