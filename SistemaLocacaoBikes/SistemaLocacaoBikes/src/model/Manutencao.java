/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
public class Manutencao {
    private int id;
    private int bicicletaId;
    private String descricao;
    private Date data;
    
    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getBicicletaId() { return bicicletaId; }
    public void setBicicletaId(int bicicletaId) { this.bicicletaId =
    bicicletaId; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao =
    descricao; }
    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }
}
