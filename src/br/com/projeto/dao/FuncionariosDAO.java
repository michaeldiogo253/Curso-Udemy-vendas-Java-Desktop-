package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Funcionarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author michael
 */
public class FuncionariosDAO {

    private Connection con;

    public FuncionariosDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrarFuncionario(Funcionarios obj) {

        try {
            String SQL = "insert into tb_funcionarios (nome,rg,cpf,email,senha, cargo, nivel_acesso,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(SQL);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_Acesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getUf());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar !" + e);

        }

        
    }

    public void alterarFuncionario() {

    }

    public void excluirFuncionario() {

    }
    
    
    public void buscaFuncionarioPorNome(String nome){
        
    }
    
}
