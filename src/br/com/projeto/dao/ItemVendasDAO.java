
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.ItemVendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class ItemVendasDAO {
    
    private Connection con;

    public ItemVendasDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    public void CadastrarItem(ItemVendas obj ){
        
        try {
            String SQL = "insert into tb_itensvendas (venda_id,produto_id,qtd, subtotal) "
                    + "values(?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(SQL);
            stmt.setInt(1, obj.getVenda().getId());
            stmt.setInt(2, obj.getProduto().getId());
            stmt.setInt(3, obj.getQtd());
            stmt.setDouble(4, obj.getSubtotal());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Item registrado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar !" + e);

        }
        
    }
    
}
