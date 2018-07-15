/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br;

/**
 *
 * @author Anderson
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class repositorioProduto {

    public repositorioProduto() {
    }

    public void inserir(produto prod) throws ExcRepositorio {
        String desc = prod.getDescricao();
        double preco = prod.getPreco();
        String SQL = "insert into tb_produtos(cod,descricao,preco) values(nextval('cod_sequence_produto'), '"+desc+"', "+preco+")";
        //String SQL = "insert into tb_produtos (cod,descricao, preco) values "
        //        + "('" + desc + "', " + preco + ")";
        Connection conn = null;
        Statement stat = null;
        try {
            conn = gConexao.getConexao();
            stat = conn.createStatement();
            stat.executeUpdate(SQL);
        } catch (SQLException e) {
            throw new ExcRepositorio("Erro na conexão ao inserir: " + e.getMessage());
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    throw new ExcRepositorio("Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
    }

    public JasperPrint gerar() throws ExcRepositorio, JRException {
        JasperPrint rel = null;
        try {
            Connection con = gConexao.getConexao();
            HashMap map = new HashMap();
            String arquivoJasper = "src/relatorios/relatorio.jasper";
            rel = JasperFillManager.fillReport(arquivoJasper, map, con);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return rel;
    }
}