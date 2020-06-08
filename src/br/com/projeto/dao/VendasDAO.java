package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
import br.com.projeto.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class VendasDAO {

    private Connection con;

    public VendasDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrarVenda(Vendas obj) {

        try {
            String SQL = "insert into tb_vendas (cliente_id,data_venda,total_venda, observacoes) "
                    + "values(?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(SQL);
            stmt.setInt(1, obj.getCliente().getId());
            stmt.setString(2, obj.getDataVenda());
            stmt.setDouble(3, obj.getTotalVenda());
            stmt.setString(4, obj.getObs());

            stmt.execute();
            stmt.close();
            //JOptionPane.showMessageDialog(null, "Venda registrada com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar !" + e);

        }

    }

    public int retornaUltimaVenda() {

        try {

            int idVenda = 0;
            String sql = "select max(id) id from tb_vendas"; // retorna maior id da tabela
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Vendas p = new Vendas();
                p.setId(rs.getInt("id"));
                idVenda = p.getId();
            }
            return idVenda;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Vendas> listarVendasPorPeriodo(LocalDate data_inicio, LocalDate data_final) {

        try {
            List<Vendas> lista = new ArrayList<>();  // criar a lista
            String SQL = "select v.id,  date_format(v.data_venda,'%d/%m/%y') as data_formatada ,c.nome,v.total_venda , v.observacoes from tb_vendas as v "
                    + "inner join tb_clientes as c on (v.cliente_id = c.id) where v.data_venda BETWEEN '" + data_inicio.toString()
                    + "' AND '" + data_final.toString() + "'";
            PreparedStatement stmt = con.prepareStatement(SQL);
            //stmt.setString(1, data_inicio.toString());
            //stmt.setString(2, data_final.toString());
            ResultSet rs = stmt.executeQuery(SQL);
            
            while (rs.next()) {
                Vendas obj = new Vendas();
                Clientes c = new Clientes();
                
                
                obj.setId(rs.getInt("v.id"));
                obj.setDataVenda(rs.getString("data_formatada"));
                c.setNome(rs.getString("c.nome"));
                obj.setTotalVenda(rs.getDouble("v.total_venda"));
                obj.setObs(rs.getString("v.observacoes"));
                obj.setCliente(c); // colocamos o obj Cliente dentro do obj de Vendas

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            return null;
        }

    }

}
