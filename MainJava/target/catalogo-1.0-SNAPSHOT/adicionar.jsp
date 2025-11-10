<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.catalogo.dao.TituloDAO, com.catalogo.model.Titulo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Adicionar</title>
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="style/style.css">
</head>
<body>
        <header class="topbar">
            <div class="logo">
                <a href="listar"><img src="images/logo.png" width="60" alt="Logo"/></a>
            </div>

            <nav class="menu">
                <p>Adicionar Título</p>
            </nav>
        </header>


    <main>

        <section class="form-section">
            <form action="adicionar" method="post" accept-charset="UTF-8">
                <label for="titulo">Título:</label>
                <input type="text" id="titulo" name="titulo" required>

                <br>

                <label for="tipo">Tipo:</label>

                <select id="tipo" name="tipo" required>
                    <option value="">Selecione</option>
                    <option value="livro">Livro</option>
                    <option value="filme">Filme</option>
                    <option value="serie">Série</option>
                </select>

                <br>

                <label for="autorDiretor">Autor/Diretor:</label>
                <input type="text" id="autorDiretor" name="autorDiretor" required>

                <br>

                <label for="anoLancamento">Ano de Lançamento:</label>
                <input type="number" id="anoLancamento" name="anoLancamento" min="1800" max="2099" required>

                <br>

                <div class="form-genero">
                    <label for="genero">Gêneros: <i>(Para mais de 1 gênero, pressione CRTL enquanto seleciona)</i></label>
                    <select name="genero" multiple required>
                        <c:forEach var="g" items="${generos}">
                            <option value="${g.id}">${g.nome}</option>
                        </c:forEach>
                    </select>
                </div>
                

                <br>

                <label for="sinopse">Sinopse:</label>
                <textarea id="sinopse" name="sinopse" rows="4"></textarea>

                <br>

                <button type="submit">Adicionar</button>
            </form>
        </section>


        <div class="voltar">
            <a href="listar">Voltar</a>
        </div>
    </main>

    <footer>
        <p>Site desenvolvido por Eduarda Depetris. 2025</p>
    </footer>


</body>
</html>