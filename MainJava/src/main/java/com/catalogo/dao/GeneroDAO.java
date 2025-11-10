package com.catalogo.dao;

import java.sql.*;
import java.util.*;
import com.catalogo.model.Genero;

public class GeneroDAO {
    private Connection conn;

    public GeneroDAO() throws SQLException {
        conn = Conexao.getConnection();
    }

    public List<Genero> listarTodos() throws SQLException {
        List<Genero> generos = new ArrayList<>();
        String sql = "SELECT * FROM generos ORDER BY nome";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Genero g = new Genero();
                g.setId(rs.getInt("id"));
                g.setNome(rs.getString("nome"));
                generos.add(g);
            }
        }
        return generos;
    }
}
