
package br.com.projeto.model;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Utilitarios {

    public void LimpaTela(JPanel container){
        Component components[] = container.getComponents();
        for(Component component : components){
            if(component instanceof JTextField){
                ((JTextField) component ).setText(null);
            }
        }
    }
    // neste medoto vamos informar o Painel desejado e iremos percorrer todos os campos de textos do Painel e deixarmos com valor nulo/em branco
}
