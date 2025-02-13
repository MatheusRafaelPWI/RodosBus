package controller;

import java.security.Timestamp;
import model.Rota;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import model.Passageiro;

public class rotaDao extends conectarDao {

    private PreparedStatement ps;

    public rotaDao() {
        super();
    }

    public ArrayList<Rota> selecionarRotas() {
        ArrayList<Rota> rotas = new ArrayList<>();

        String sql = "SELECT ID_ROTA, DS_ORIGEM, DS_DESTINO, VL_DISTANCIA, DS_DURACAO, VL_PRECO, DT_SAIDA, DT_CHEGADA, "
                + "MOT.ID_MOTORISTA,MOT.DS_NOME FROM TB_ROTA ROT LEFT JOIN TB_MOTORISTA MOT ON ROT.ID_MOTORISTA = MOT.ID_MOTORISTA";

        try {
            ps = mycon.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID_ROTA");
                String destino = resultSet.getString("DS_DESTINO");
                String origem = resultSet.getString("DS_ORIGEM");

                java.sql.Timestamp saidaTimestamp = resultSet.getTimestamp("DT_SAIDA");
                java.sql.Timestamp chegadaTimestamp = resultSet.getTimestamp("DT_CHEGADA");

                Date saida = new Date(saidaTimestamp.getTime());
                Date chegada = new Date(chegadaTimestamp.getTime());

                Double preco = resultSet.getDouble("VL_PRECO");
                int idMotorista = resultSet.getInt("ID_MOTORISTA");
                String NomeMotorista = resultSet.getString("DS_NOME");

                Rota rota = new Rota();

                rota.setIdRota(id);
                rota.setDestino(destino);
                rota.setOrigem(origem);
                rota.setDtChegada(chegada);
                rota.setDtSaida(saida);

                rota.setVlPreco(preco);
                rota.setIdMotorista(idMotorista);
                rota.setNomeMotorista(NomeMotorista);
                rotas.add(rota);
            }

            ps.close();
            resultSet.close();
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar Rotas! " + err.getMessage());
        }

        return rotas;
    }

    public void incluirRota(Rota rota) {
        String sql = "INSERT INTO TB_ROTA (DS_ORIGEM, DS_DESTINO, VL_DISTANCIA, DS_DURACAO, VL_PRECO, DT_SAIDA, DT_CHEGADA, ID_MOTORISTA) VALUES (?,?,?,?,?,?,?,?)";

        try {
            ps = mycon.prepareStatement(sql);

            ps.setString(1, rota.getOrigem());
            ps.setString(2, rota.getDestino());
            ps.setDouble(3, rota.getVlDistancia());
            ps.setString(4, rota.getDsDuracao());
            ps.setDouble(5, rota.getVlPreco());
            ps.setDate(6, new java.sql.Date(rota.getDtSaida().getTime()));
            ps.setDate(7, new java.sql.Date(rota.getDtChegada().getTime()));
            ps.setInt(8, rota.getIdMotorista());

            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Cadastro concluído com Sucesso!");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar!" + err.getMessage());
        }
    }

    public Rota selecionarUmaRota(int id) {
        String sql = "SELECT ID_ROTA, DS_ORIGEM, DS_DESTINO, VL_DISTANCIA, DS_DURACAO, VL_PRECO, DT_SAIDA, DT_CHEGADA, "
                + "MOT.ID_MOTORISTA, MOT.DS_NOME FROM TB_ROTA ROT LEFT JOIN TB_MOTORISTA MOT ON ROT.ID_MOTORISTA = MOT.ID_MOTORISTA WHERE ID_ROTA =" + id;

        Rota rota = new Rota();

        try {
            PreparedStatement ps = mycon.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) { 
                int IdRota = resultSet.getInt("ID_ROTA");
                String origem = resultSet.getString("DS_ORIGEM");
                String destino = resultSet.getString("DS_DESTINO");
                double vlDistancia = resultSet.getDouble("VL_DISTANCIA");
                String dsDuracao = resultSet.getString("DS_DURACAO");
                double vlpreco = resultSet.getDouble("VL_PRECO");
                Date dtchegada = resultSet.getDate("DT_CHEGADA");
                Date dtsaida = resultSet.getDate("DT_SAIDA");

                int Idmotorista = resultSet.getInt("ID_MOTORISTA");
                String NomeMotorista = resultSet.getString("DS_NOME");

                rota.setIdRota(IdRota); 
                rota.setDestino(destino);
                rota.setOrigem(origem);
                rota.setDtChegada(dtchegada);
                rota.setDtSaida(dtsaida);

                rota.setVlPreco(vlpreco);
                rota.setIdMotorista(Idmotorista);
                rota.setNomeMotorista(NomeMotorista);
            } else {
                System.out.println("Rota not found for ID: " + id);
            }

            ps.close();
            resultSet.close();
        } catch (SQLException err) {
            err.printStackTrace();
        }

        return rota;
    }

    public void excluir(int id) {
        sql = "DELETE FROM TB_ROTA WHERE ID_ROTA = '" + id + "'";

        try {
            ps = mycon.prepareStatement(sql);
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Registro excluido com sucesso!");

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir usuário!" + err.getMessage());
        }
    }

    public ArrayList<Rota> selecionarRotasFiltro(Date DATASAIDA, Date DATAVOLTA, String ORIGEM, String DESTINO) {
        ArrayList<Rota> rotas = new ArrayList<>();

        // Monta a cláusula WHERE da consulta com base nos parâmetros fornecidos
        String whereClause = " WHERE 1 = 1"; // Condição sempre verdadeira para facilitar a concatenação

        if (DATASAIDA != null) {
            whereClause += " AND DT_SAIDA >= ?";
        }

        if (DATAVOLTA != null) {
            whereClause += " AND DT_CHEGADA <= ?";
        }

        if (ORIGEM != null && !ORIGEM.isEmpty()) {
            whereClause += " AND DS_ORIGEM LIKE ?";
        }

        if (DESTINO != null && !DESTINO.isEmpty()) {
            whereClause += " AND DS_DESTINO LIKE ?";
        }

        String sql = "SELECT ID_ROTA, DS_ORIGEM, DS_DESTINO, VL_DISTANCIA, DS_DURACAO, VL_PRECO, DT_SAIDA, DT_CHEGADA, "
                + "MOT.ID_MOTORISTA, MOT.DS_NOME FROM TB_ROTA ROT LEFT JOIN TB_MOTORISTA MOT ON ROT.ID_MOTORISTA = MOT.ID_MOTORISTA"
                + whereClause;

        try {
            ps = mycon.prepareStatement(sql);

            // Define os parâmetros da consulta com base nos filtros fornecidos
            int parameterIndex = 1;

            if (DATASAIDA != null) {
                ps.setTimestamp(parameterIndex++, new java.sql.Timestamp(DATASAIDA.getTime()));
            }

            if (DATAVOLTA != null) {
                ps.setTimestamp(parameterIndex++, new java.sql.Timestamp(DATAVOLTA.getTime()));
            }

            if (ORIGEM != null && !ORIGEM.isEmpty()) {
                ps.setString(parameterIndex++, ORIGEM);
            }

            if (DESTINO != null && !DESTINO.isEmpty()) {
                ps.setString(parameterIndex++, DESTINO);
            }

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID_ROTA");
                String destino = resultSet.getString("DS_DESTINO");
                String origem = resultSet.getString("DS_ORIGEM");

                java.sql.Timestamp saidaTimestamp = resultSet.getTimestamp("DT_SAIDA");
                java.sql.Timestamp chegadaTimestamp = resultSet.getTimestamp("DT_CHEGADA");

                Date saida = new Date(saidaTimestamp.getTime());
                Date chegada = new Date(chegadaTimestamp.getTime());

                Double preco = resultSet.getDouble("VL_PRECO");
                int idMotorista = resultSet.getInt("ID_MOTORISTA");
                String NomeMotorista = resultSet.getString("DS_NOME");

                Rota rota = new Rota();

                rota.setIdRota(id);
                rota.setDestino(destino);
                rota.setOrigem(origem);
                rota.setDtChegada(chegada);
                rota.setDtSaida(saida);

                rota.setVlPreco(preco);
                rota.setIdMotorista(idMotorista);
                rota.setNomeMotorista(NomeMotorista);
                rotas.add(rota);
            }

            ps.close();
            resultSet.close();
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar Rotas! " + err.getMessage());
        }

        return rotas;
    }
  public void alterar(Rota obj) {
    String sql = "UPDATE TB_ROTA SET DS_ORIGEM = ?, DS_DESTINO = ?, VL_PRECO= ?, DT_CHEGADA = ?, DT_SAIDA = ?, ID_MOTORISTA = ?  WHERE ID_ROTA = ?";
    try {
        PreparedStatement ps = mycon.prepareStatement(sql);

        ps.setString(1, obj.getDestino());
        ps.setString(2, obj.getOrigem());
                      ps.setDouble(3, obj.getVlPreco());
        ps.setDate(4,  new java.sql.Date(obj.getDtChegada().getTime()));
        ps.setDate(5,  new java.sql.Date(obj.getDtSaida().getTime())); // Você deve fornecer o ID do motorista a ser atualizado
        ps.setInt(6,obj.getIdMotorista());
        ps.setInt(7, obj.getIdRota());
        int rowsUpdated = ps.executeUpdate();
        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(null, "Registro Alterado com Sucesso !");
        } else {
            
        }
        
        ps.close();
    } catch (SQLException err) {
        
        err.printStackTrace();
    }
    }
    }


