package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
import br.com.projeto.model.Fornecedores;
import br.com.projeto.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    private Connection con;

    public ProdutosDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrarProduto(Produtos obj) {
        try {
            String SQL = "insert into tb_produtos (descricao,preco,qtd_estoque,for_id) "
                    + "values(?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(SQL);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQuantidade());
            stmt.setInt(4, obj.getFornecedor().getId());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar !" + e);

        }

    }

    public void alterarProduto(Produtos obj) {

        try {
            String SQL = "update tb_produtos set descricao=?,preco=?,qtd_estoque=?,for_id=? where id=?";

            PreparedStatement stmt = con.prepareStatement(SQL);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQuantidade());
            stmt.setInt(4, obj.getFornecedor().getId());
            stmt.setInt(5, obj.getId());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar !" + e);

        }

    }

    public void excluirProduto(Produtos obj) {

        try {
            String SQL = "delete from tb_produtos where id = ?";

            PreparedStatement stmt = con.prepareStatement(SQL);
            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Produto xcluido  com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir !" + e);

        }

    }

    public List<Produtos> listarProdutos() {

        try {
            List<Produtos> lista = new ArrayList<>();  // criar a lista
            String SQL = "select p.id, p.descricao ,p.preco,p.qtd_estoque , f.nome from tb_produtos as p "
                    + "inner join tb_fornecedores as f on (p.for_id = f.id)";
            PreparedStatement stmt = con.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQuantidade(rs.getInt("p.qtd_estoque"));
                f.setNome(rs.getString("f.nome"));
                obj.setFornecedor(f); // aula 44 explica 
                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            return null;
        }

    }

    public List<Produtos> buscaProdutoPorNome(String nome) {

        try {
            List<Produtos> lista = new ArrayList<>();  // criar a lista
            String SQL = "select p.id, p.descricao ,p.preco,p.qtd_estoque , f.nome from tb_produtos as p "
                    + "inner join tb_fornecedores as f on (p.for_id = f.id) where p.descricao like '%" + nome + "%'";
            PreparedStatement stmt = con.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQuantidade(rs.getInt("p.qtd_estoque"));
                f.setNome(rs.getString("f.nome"));
                obj.setFornecedor(f); // aula 44 explica 
                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            return null;
        }

    }

    public Produtos buscaProdutoPorCodigo(int codigo) {

        try {

            String SQL = "select * from tb_produtos where id = '" + codigo + "'";
            PreparedStatement stmt = con.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery(SQL);
            Produtos obj = new Produtos();
            while (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setPreco(rs.getDouble("preco"));
                obj.setQuantidade(rs.getInt("qtd_estoque"));
                ;
            }

            return obj;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            return null;
        }

    }

    public void baixaEstoque(int id, int qtd_nova) {
        try {
            String SQL = "update tb_produtos set qtd_estoque=? where id=?";
            PreparedStatement stmt = con.prepareStatement(SQL);
            stmt.setInt(1, qtd_nova);
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "eero ao dar baixa no estoque: "+ e);
        }

    }

    public int retornaEstoqueAtual(int id){
        try {
            int qtd_estoque = 0;
            String SQL = "select qtd_estoque from tb_produtos where id = ?";
            PreparedStatement stmt = con.prepareStatement(SQL);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                Produtos p = new Produtos();
                qtd_estoque = (rs.getInt("qtd_estoque"));
            }
            return qtd_estoque;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        
    }
}
