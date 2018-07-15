package projrelatorio;


import br.ExcRepositorio;
import br.produto;
import br.repositorioProduto;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class principal {

    public static void main(String[] args) throws JRException {
        repositorioProduto rep = new repositorioProduto();
        JasperPrint relat;
        //Insere mais um produto e exibe o relatório
        String desc = JOptionPane.showInputDialog("Descrição do produto: ");
        double valor = Double.parseDouble(JOptionPane.showInputDialog("Valor: "));
        produto prod = new produto(desc, valor);
        try {
            rep.inserir(prod);
            relat = rep.gerar();
            JasperViewer.viewReport(relat, true);
        } catch (ExcRepositorio e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }
}