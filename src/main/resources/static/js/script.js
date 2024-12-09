$(document).ready(function() {
    // Enviar requisição DELETE para excluir filme
    $(".btn-deletar-filme").on("click", function() {
        const id = $(this).data("id");
        if (confirm("Tem certeza que deseja excluir este filme?")) {
            $.ajax({
                url: `/api/filmes/${id}`,
                type: 'DELETE',
                success: function(result) {
                    alert("Filme excluído com sucesso!");
                    location.reload();
                },
                error: function(err) {
                    alert("Erro ao excluir filme!");
                }
            });
        }
    });

    // Enviar requisição PUT para editar filme
    $(".btn-editar-filme").on("click", function() {
        const id = $(this).data("id");
        $.ajax({
            url: `/api/filmes/${id}`,
            type: 'PUT',
            data: {
                titulo: $("#titulo").val(),
                genero: $("#genero").val(),
                sinopse: $("#sinopse").val(),
                anoLancamento: $("#anoLancamento").val()
            },
            success: function(result) {
                alert("Filme atualizado com sucesso!");
                window.location.href = "/filmes";
            },
            error: function(err) {
                alert("Erro ao atualizar filme!");
            }
        });
    });
});