package Ex_Pessoa_Avo_Pai_Filho;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Reflexao {

    public static void imprimeDadosClasse(String classeNome) {

        try {

            Class<?> cls = Class.forName(classeNome);


            String message = "PODE SER ATRIBUÍDO DE PESSOA: "
                    + ((Pessoa.class.isAssignableFrom(cls)) ? " SIM \n\n" : " NÃO \n\n")
                    + processaClasse(cls);

            JTextArea textArea = new JTextArea(message, 25, 45);
            textArea.setEditable(false);
            textArea.setWrapStyleWord(true);

            JScrollPane sp = new JScrollPane(textArea);
            sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            JOptionPane.showMessageDialog(null, sp);


        } catch (Throwable e) {
            JOptionPane.showMessageDialog(null, e);
            System.exit(1);
        }
    }

    private static String processaClasse(Class<?> cls) {
        return processaClasseHeranca(cls, "");
    }

    private static String processaClasseHeranca(Class<?> cls, String texto) {

        Class<?> superclass = cls.getSuperclass();

        StringBuilder resultado = new StringBuilder(texto);

        if (superclass != Object.class) {
            resultado.append(processaClasseHeranca(superclass, resultado.toString()));
        }

        resultado.append("\nCLASS:")
                .append(cls.getSimpleName())
                .append(descreveComponentesClasse(cls));

        return resultado.toString();
    }

    private static String descreveComponentesClasse(Class<?> cls) {
        return formataDescricao(cls.getDeclaredMethods()) + formataDescricao(cls.getDeclaredFields());

    }

    private static String formataDescricao(Method[] metodos) {
        StringBuilder descricao = new StringBuilder();
        if (metodos.length > 0) {
            Arrays.stream(metodos).forEach(mtd -> {

                descricao.append("\nFullname: ")
                        .append(mtd)
                        .append("\n\n--- Descricao geral ---\n")
                        .append("\n     Nome: ").append(mtd.getName())
                        .append("\n     Modificadores: ").append(Modifier.toString(mtd.getModifiers()))
                        .append("\n     Tipo do retorno: ").append(mtd.getReturnType())
                        .append("\n     Numero de parametros: ").append(mtd.getParameterCount())
                        .append("\n     Lista de parametros: \n");

                if (mtd.getParameterCount() > 0) {
                    Arrays.stream(mtd.getParameterTypes()).forEach(par ->
                            descricao.append("            * ").append(par.getName()).append("\n"));
                } else {
                    descricao.append("        Não há parametros nesse metodo\n");
                }

                descricao.append("------------------------------------------------------------------------------------------------\n\n");

            });
        } else {
            descricao.append("\nNão há métodos na classe\n");
        }
        return descricao.toString();
    }

    private static String formataDescricao(Field[] campos) {

        StringBuilder descricao = new StringBuilder();
        if (campos.length > 0) {
            Arrays.stream(campos).forEach(fld -> descricao.append("\nFullname: ").append(fld)
                    .append("\n\n--- Descricao geral ---\n")
                    .append("\n     Nome: ").append(fld.getName())
                    .append("\n     Modificadores: ").append(Modifier.toString(fld.getModifiers()))
                    .append("\n     Tipo: ").append(fld.getType().getSimpleName())
                    .append("\n------------------------------------------------------------------------------------------------\n"));
        } else {
            descricao.append("\nNão há atributos\n");
        }
        return descricao.toString();
    }


}
