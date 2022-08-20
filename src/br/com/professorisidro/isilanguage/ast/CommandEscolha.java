package br.com.professorisidro.isilanguage.ast;

import java.util.ArrayList;

public class CommandEscolha extends AbstractCommand {
    private String escolha;
    private ArrayList<ArrayList<AbstractCommand>> casos;
    private ArrayList<String> condicao;
    private ArrayList<AbstractCommand> padrao;

    public CommandEscolha(String escolha,
                          ArrayList<ArrayList<AbstractCommand>> casos,
                          ArrayList<String> condicao,
                          ArrayList<AbstractCommand> padrao) {
        this.casos = casos;
        this.escolha = escolha;
        this.condicao = condicao;
        this.padrao = padrao;
    }

    public void setCasos(ArrayList<ArrayList<AbstractCommand>> casos) {
        this.casos = casos;
    }

    public void setCondicao(ArrayList<String> condicao) {
        this.condicao = condicao;
    }

    public void setPadrao(ArrayList<AbstractCommand> padrao) {
        this.padrao = padrao;
    }

    @Override
    public String generateJavaCode() {
        // TODO Auto-generated method stub
        StringBuilder str = new StringBuilder();
        str.append("switch ("+escolha+") {\n");
        if (casos.size() > 0) {
            int i = 0;
            for (ArrayList<AbstractCommand> arrayCmd: casos) {
                str.append("    case ");
                str.append(condicao.get(i));
                str.append(":\n");
                arrayCmd.forEach(cmd -> {
                    str.append("        " + cmd.generateJavaCode() + "\n");
                });
                i++;
            }
        }
        if (padrao.size() > 0) {
            str.append("    default:\n");
            padrao.forEach(padrao -> {
                str.append("        " + padrao.generateJavaCode() + "\n");
            });
        }
        str.append("}\n");
        return str.toString();
    }

    @Override
    public String generateCppCode() {
        // TODO Auto-generated method stub
        StringBuilder str = new StringBuilder();
        str.append("switch ("+escolha+") {\n");
        if (casos.size() > 0) {
            int i = 0;
            for (ArrayList<AbstractCommand> arrayCmd: casos) {
                str.append("    case ");
                str.append(condicao.get(i));
                str.append(":\n");
                arrayCmd.forEach(cmd -> {
                    str.append("        " + cmd.generateCppCode() + "\n");
                });
                i++;
            }
        }
        if (padrao.size() > 0) {
            str.append("    default:\n");
            padrao.forEach(padrao -> {
                str.append("        " + padrao.generateCppCode() + "\n");
            });
        }
        str.append("}\n");
        return str.toString();
    }

    @Override
    public String toString() {
        return "CommandEscolha [String=" + escolha + ", casos=" + casos + ", condicao=" + condicao + " padrao=" + padrao +
                "]";
    }
}
