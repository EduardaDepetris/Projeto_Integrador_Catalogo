<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Editar ${titulo.titulo}</title>
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="style/style.css">
</head>
<body>
    <header class="topbar">
        <div class="logo">
            <a href="listar"><img src="images/logo.png" width="60" alt="Logo"/></a>
        </div>

        <nav class="menu">
            <p>Editando: ${titulo.titulo}</p>
        </nav>
    </header>

    <main>
        <section class="form-section">
            <form action="editar" method="post" accept-charset="UTF-8">
                <input type="hidden" name="id" value="${titulo.id}">

                <label for="titulo">Título:</label>
                <input type="text" id="titulo" name="titulo" value="${titulo.titulo}" required>

                <br>

                <label for="tipo">Tipo:</label>
                <select id="tipo" name="tipo" required>
                    <option value="livro" ${titulo.tipo == 'livro' ? 'selected' : ''}>Livro</option>
                    <option value="filme" ${titulo.tipo == 'filme' ? 'selected' : ''}>Filme</option>
                    <option value="serie" ${titulo.tipo == 'serie' ? 'selected' : ''}>Série</option>
                </select>

                <br>

                <label for="autorDiretor">Autor/Diretor:</label>
                <input type="text" id="autorDiretor" name="autorDiretor" value="${titulo.autorDiretor}" required>

                <br>

                <label for="anoLancamento">Ano de Lançamento:</label>
                <input type="number" id="anoLancamento" name="anoLancamento" value="${titulo.anoLancamento}" min="1800" max="2099" required>

                <br>

                <div class="form-genero">
                    <label for="genero">Gêneros:</label>
                    <select name="genero" multiple required>
                        <c:forEach var="g" items="${generos}">
                            <option value="${g.id}" ${titulo.genero.contains(g.nome) ? 'selected' : ''}>${g.nome}</option>
                        </c:forEach>
                    </select>
                </div>

                <br>

                <label for="sinopse">Sinopse:</label>
                <textarea id="sinopse" name="sinopse" rows="4">${titulo.sinopse}</textarea>

                <br>

                <button type="submit">Salvar Alterações</button>
            </form>
        </section>

        <div class="voltar">
            <a href="detalhes?id=${titulo.id}">Cancelar</a>
        </div>

        <div class="voltar">
            <a href="excluir?id=${titulo.id}" class="botao-excluir">Excluir</a>
        </div>
    </main>

    <footer>
        <p>Site desenvolvido por Eduarda Depetris. 2025</p>
    </footer>
</body>
</html>