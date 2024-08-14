package Ex_Pessoa_Avo_Pai_Filho;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Stream;


public class Reflexao {

    public void printClasse(String classe){

        try {

            Class<?> cls = Class.forName(classe);


            String message = "ENCONTRADO: " + cls.getName()
                    + "\nNOME DA CLASSE: " + cls.getSimpleName()
                    + "\nPODE SER ATRIBUÍDO DE PESSOA: " + ((Pessoa.class.isAssignableFrom(cls)) ? " SIM \n" : " NÃO \n")
                    + processaClasse(cls, " ");

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

    private String processaClasse(Class<?> cls, String result) {

        Class<?> superclass = cls.getSuperclass();

        result = result.concat(getAllFields(cls));
        result = result.concat(getAllMethods(cls));

        if(superclass != Object.class) {

            result = result.concat(processaClasse(superclass, result));
        }

        return result;
    }

    private static String getAllMethods(Class<?> cls) {

        StringBuilder methods = new StringBuilder();

        Method[] declaredMethods = cls.getDeclaredMethods();

        Stream<Method> allMethods = Arrays.stream(declaredMethods);

        if (Arrays.stream(declaredMethods).findAny().isPresent()) {
            allMethods.forEach(mtd -> {

                methods.append("\nFullname: ")
                        .append(mtd)
                        .append("\n\n--- Descricao geral ---\n")
                        .append("\n     Nome: ").append(mtd.getName())
                        .append("\n     Modificadores: ").append(Modifier.toString(mtd.getModifiers()))
                        .append("\n     Tipo do retorno: ").append(mtd.getReturnType())
                        .append("\n     Numero de parametros: ").append(mtd.getParameterCount())
                        .append("\n     Lista de parametros: \n");

                if (mtd.getParameterCount() > 0) {
                    Arrays.stream(mtd.getParameterTypes()).forEach(par -> {
                        methods.append("            * ").append(par.getName()).append("\n");
                    });
                } else {
                    methods.append("        Não há parametros nesse metodo\n");
                }

                methods.append("------------------------------------------------------------------------------------------------\n\n");

            });
        } else {
            methods.append("\nNão há métodos\n");
        }
        return methods.toString();
    }

    private static String getAllFields(Class<?> cls) {

        StringBuilder fields = new StringBuilder();

        Field[] declaredFields = cls.getDeclaredFields();

        Stream<Field> allFields = Arrays.stream(declaredFields);
        if (Arrays.stream(declaredFields).findAny().isPresent()) {
            allFields.forEach(fld -> {

                fields.append("\nFullname: ").append(fld)
                        .append("\n\n--- Descricao geral ---\n")
                        .append("\n     Nome: ").append(fld.getName())
                        .append("\n     Modificadores: ").append(Modifier.toString(fld.getModifiers()))
                        .append("\n     Tipo: ").append(fld.getType().getSimpleName())
                        .append("\n------------------------------------------------------------------------------------------------\n");

            });
        } else {
            fields.append("\nNão há atributos\n");
        }
        return fields.toString();
    }


}
