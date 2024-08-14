package Ex_Pessoa_Avo_Pai_Filho;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Reflexao {

    public static void geraDadosClasse(String classeNome) {
        String resultado = "";

        try {

            Class<?> cls = Class.forName(classeNome);

            resultado = processaClasse(cls) + cls.getSimpleName() + "\n PODE SER ATRIBUÍDO DE PESSOA: "
                    + ((Pessoa.class.isAssignableFrom(cls)) ? " SIM \n" : " NÃO \n");


        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro no metodo gerar os dados da classe: " +  e);
            System.exit(1);
        }

        imprimeDadosClasse(resultado);
    }

    public static void imprimeDadosClasse(String message) {

        try {
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
        return processaClasseHeranca(cls, new StringBuilder()).toString();
    }

    private static StringBuilder processaClasseHeranca(Class<?> cls, StringBuilder texto) {

        Class<?> superclass = cls.getSuperclass();

        StringBuilder resultado = texto.append("\nCLASS: ")
                .append(cls.getSimpleName())
                .append(descreveComponentesClasse(cls));

        if (superclass != Object.class) {
            return processaClasseHeranca(superclass, resultado);
        }

        return resultado;


    }

    private static String descreveComponentesClasse(Class<?> cls) {
        return formataDescricao(cls.getDeclaredMethods()) + formataDescricao(cls.getDeclaredFields());

    }

    private static String formataDescricao(Method[] metodos) {
        StringBuilder descricao = new StringBuilder();
        if (metodos.length > 0) {
            Arrays.stream(metodos).forEach(mtd -> {
                descricao.append(
                        String.format("""
                                        
                                        METODO: %s\
                                        
                                        
                                            --- Descricao geral ---\
                                        
                                                    Nome completo: %s\
                                        
                                                    Modificadores: %s\
                                        
                                                    Tipo do retorno: %s\
                                        
                                                    Numero de parametros: %s\
                                        
                                        
                                        """,
                                mtd.getName(),
                                mtd,
                                Modifier.toString(mtd.getModifiers()),
                                mtd.getReturnType(),
                                mtd.getParameterCount()));

                if (mtd.getParameterCount() > 0) {
                    descricao.append("Lista de parametros:\n");
                    Arrays.stream(mtd.getParameterTypes()).forEach(par ->
                            descricao.append("            * ").append(par.getName()).append("\n"));
                } else {
                    descricao.append("Não há parametros nesse metodo\n");
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
            Arrays.stream(campos).forEach(fld -> descricao.append(
                    String.format("""
                                    ATRIBUTO: %s\
                                    
                                    
                                        --- Descricao geral ---\
                                    
                                            Nome completo: %s\
                                    
                                            Modificadores: %s\
                                    
                                            Tipo: %s\
                                    
                                    ------------------------------------------------------------------------------------------------\
                                    """,
                            fld.getName(),
                            fld,
                            Modifier.toString(fld.getModifiers()),
                            fld.getType().getSimpleName())));

        } else {
            descricao.append("\nNão há atributos\n");
        }
        return descricao.toString();
    }


}
