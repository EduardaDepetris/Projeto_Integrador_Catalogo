package com.catalogo.dao;

import com.catalogo.model.Titulo;
import java.sql.*;
import java.util.*;

public class TituloDAO {
    private Connection conn;

    public TituloDAO() throws SQLException {
        this.conn = Conexao.getConnection();
    }

    public List<Titulo> listarTodos() throws SQLException {
        List<Titulo> lista = new ArrayList<>();

        String sql = "SELECT t.*, GROUP_CONCAT(g.nome SEPARATOR ', ') AS genero " +
                     "FROM titulos t " +
                     "LEFT JOIN titulos_generos tg ON t.id = tg.id_titulo " +
                     "LEFT JOIN generos g ON tg.id_genero = g.id " +
                     "GROUP BY t.id";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearTitulo(rs));
            }
        }

        return lista;
    }

    public List<Titulo> listarPorTipo(String tipo) throws SQLException {
        List<Titulo> lista = new ArrayList<>();

        String sql = "SELECT t.*, GROUP_CONCAT(g.nome SEPARATOR ', ') AS genero " +
                     "FROM titulos t " +
                     "LEFT JOIN titulos_generos tg ON t.id = tg.id_titulo " +
                     "LEFT JOIN generos g ON tg.id_genero = g.id " +
                     "WHERE t.tipo = ? " +
                     "GROUP BY t.id";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipo);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearTitulo(rs));
                }
            }
        }

        return lista;
    }

    private Titulo mapearTitulo(ResultSet rs) throws SQLException {
        Titulo t = new Titulo();
        t.setId(rs.getInt("id"));
        t.setTipo(rs.getString("tipo"));
        t.setTitulo(rs.getString("titulo"));
        t.setAutorDiretor(rs.getString("autor_diretor"));
        t.setAnoLancamento(rs.getInt("ano_lancamento"));
        t.setSinopse(rs.getString("sinopse"));

        String genero = rs.getString("genero");
        t.setGenero(genero != null ? genero : "Gênero não informado");

        return t;
    }

    public void adicionar(Titulo titulo, String[] generosSelecionados) throws SQLException {
        String sql = "INSERT INTO titulos (titulo, tipo, autor_diretor, ano_lancamento, sinopse) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, titulo.getTitulo());
            stmt.setString(2, titulo.getTipo());
            stmt.setString(3, titulo.getAutorDiretor());
            stmt.setInt(4, titulo.getAnoLancamento());
            stmt.setString(5, titulo.getSinopse());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int idTitulo = rs.getInt(1);

                if (generosSelecionados != null) {
                    String sqlGenero = "INSERT INTO titulos_generos (id_titulo, id_genero) VALUES (?, ?)";
                    try (PreparedStatement stmtGenero = conn.prepareStatement(sqlGenero)) {
                        for (String idGenero : generosSelecionados) {
                            stmtGenero.setInt(1, idTitulo);
                            stmtGenero.setInt(2, Integer.parseInt(idGenero));
                            stmtGenero.executeUpdate();
                        }
                    }
                }
            }
        }
    }

    public Titulo buscarPorId(int id) throws SQLException {
        String sql = "SELECT t.*, GROUP_CONCAT(g.nome SEPARATOR ', ') AS genero " +
                     "FROM titulos t " +
                     "LEFT JOIN titulos_generos tg ON t.id = tg.id_titulo " +
                     "LEFT JOIN generos g ON tg.id_genero = g.id " +
                     "WHERE t.id = ? GROUP BY t.id";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearTitulo(rs);
                }
            }
        }
        return null;
    }

    public void atualizar(int id, String titulo, String tipo, String autorDiretor, int anoLancamento,
                          String[] generosSelecionados, String sinopse) throws SQLException {

        String sql = "UPDATE titulos SET titulo = ?, tipo = ?, autor_diretor = ?, ano_lancamento = ?, sinopse = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            stmt.setString(2, tipo);
            stmt.setString(3, autorDiretor);
            stmt.setInt(4, anoLancamento);
            stmt.setString(5, sinopse);
            stmt.setInt(6, id);
            stmt.executeUpdate();
        }

        String deleteSQL = "DELETE FROM titulos_generos WHERE id_titulo = ?";
        try (PreparedStatement stmtDel = conn.prepareStatement(deleteSQL)) {
            stmtDel.setInt(1, id);
            stmtDel.executeUpdate();
        }

        if (generosSelecionados != null) {
            String insertSQL = "INSERT INTO titulos_generos (id_titulo, id_genero) VALUES (?, ?)";
            try (PreparedStatement stmtIns = conn.prepareStatement(insertSQL)) {
                for (String idGenero : generosSelecionados) {
                    stmtIns.setInt(1, id);
                    stmtIns.setInt(2, Integer.parseInt(idGenero));
                    stmtIns.executeUpdate();
                }
            }
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM titulos_generos WHERE id_titulo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }

        sql = "DELETE FROM titulos WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Titulo> buscarPorTituloOuAutor(String termo) throws SQLException {
        List<Titulo> lista = new ArrayList<>();

        String sql = "SELECT t.*, GROUP_CONCAT(g.nome SEPARATOR ', ') AS genero " +
                    "FROM titulos t " +
                    "LEFT JOIN titulos_generos tg ON t.id = tg.id_titulo " +
                    "LEFT JOIN generos g ON tg.id_genero = g.id " +
                    "WHERE t.titulo LIKE ? OR t.autor_diretor LIKE ? " +
                    "GROUP BY t.id";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            String termoLike = "%" + termo + "%";
            stmt.setString(1, termoLike);
            stmt.setString(2, termoLike);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearTitulo(rs));
                }
            }
        }

        return lista;
    }
}