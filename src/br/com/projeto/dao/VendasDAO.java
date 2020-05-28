package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            JOptionPane.showMessageDialog(null, "Venda registrada com sucesso!");

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

}
