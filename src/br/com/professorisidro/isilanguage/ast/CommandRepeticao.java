package br.com.professorisidro.isilanguage.ast;

import java.util.ArrayList;

public class CommandRepeticao extends AbstractCommand{

    private String condition;

    private ArrayList<AbstractCommand> listaBloco;

    public CommandRepeticao(String condition, ArrayList<AbstractCommand> listaBloco) {
        this.condition = condition;
        this.listaBloco = listaBloco;
    }
    @Override
    public String generateJavaCode() {
        StringBuilder str = new StringBuilder();

        str.append("while ("+condition+") {\n");
        for (AbstractCommand cmd: listaBloco) {
            str.append(cmd.generateJavaCode());
        }
        str.append("}");
        return str.toString();
    }

    @Override
    public String toString() {
        return "CommandRepeticao [condition=" + condition + ", listaBloco= " + listaBloco + "]";
    }
}
