package com.catalogo.model;


public class Titulo {
    private int id;
    private String tipo;            
    private String titulo;
    private String autorDiretor;
    private int anoLancamento;
    private String genero;
    private String sinopse;

    
    public Titulo() {}

    
    public Titulo(int id, String tipo, String titulo, String autorDiretor, int anoLancamento, String sinopse, String genero) {
        this.id = id;
        this.tipo = tipo;
        this.titulo = titulo;
        this.autorDiretor = autorDiretor;
        this.anoLancamento = anoLancamento;
        this.sinopse = sinopse;
        this.genero = genero;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutorDiretor() { return autorDiretor; }
    public void setAutorDiretor(String autorDiretor) { this.autorDiretor = autorDiretor; }

    public int getAnoLancamento() { return anoLancamento; }
    public void setAnoLancamento(int anoLancamento) { this.anoLancamento = anoLancamento; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getSinopse() { return sinopse; }
    public void setSinopse(String sinopse) { this.sinopse = sinopse; }

}

