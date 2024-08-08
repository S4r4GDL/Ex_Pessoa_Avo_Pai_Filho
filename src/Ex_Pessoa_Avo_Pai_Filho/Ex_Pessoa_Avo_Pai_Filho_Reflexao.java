package Ex_Pessoa_Avo_Pai_Filho;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Stream;

public class Ex_Pessoa_Avo_Pai_Filho_Reflexao {
    public static void main(String[] args){

        try {

            Class<?> cls = Class.forName(
                    JOptionPane.showInputDialog("Nome da classe que descende de Pessoa:",
                            "Ex_Pessoa_Avo_Pai_Filho."));

            System.out.println("CLASS: " + cls.getName());

            if(Pessoa.class.isAssignableFrom(cls)){
                System.out.println(
                        cls.getSimpleName() +
                                " is assignable from Pessoa.class\n");
            }


            printFields(cls);
            printMethods(cls);



        } catch (Throwable e) {
            JOptionPane.showMessageDialog(null, e);
            System.exit(1);
        }
    }

    private static void printMethods(Class<?> cls) {

        Method[] declaredMethods = cls.getDeclaredMethods();
        Method[] superMethods = cls.getSuperclass().getDeclaredMethods();

        Stream<Method> allMethods = Stream.concat(Arrays.stream(declaredMethods), Arrays.stream(superMethods));
        allMethods.forEach(mtd -> {

            System.out.println("Fullname: " + mtd +
                    "\n\n--- Descricao geral ---\n" +
                    "\n     Nome: " + mtd.getName() +
                    "\n     Modificadores: " + Modifier.toString(mtd.getModifiers())+
                    "\n     Tipo do retorno: " + mtd.getReturnType()+
                    "\n     Numero de parametros: " + mtd.getParameterCount()+
                    "\n     Lista de parametros: ");

            if(mtd.getParameterCount()>0){
                Arrays.stream(mtd.getParameterTypes()).forEach(par -> {
                    System.out.println("            * " + par.getName());
                });
            }
            else {
                System.out.println("        Não há parametros nesse metodo");
            }

            System.out.println("------------------------------------------------\n");
        });
    }

    private static void printFields(Class<?> cls) {

        Field[] declaredFields = cls.getDeclaredFields();
        Field[] superFields = cls.getSuperclass().getDeclaredFields();

        Stream<Field> allFields = Stream.concat(Arrays.stream(declaredFields), Arrays.stream(superFields));
        allFields.forEach(fld -> {

            System.out.println("\nFullname: " + fld +
                    "\n\n--- Descricao geral ---\n" +
                    "\n     Nome: " + fld.getName() +
                    "\n     Modificadores: " + Modifier.toString(fld.getModifiers()) +
                    "\n     Tipo: " + fld.getType().getSimpleName());
            });

            System.out.println("------------------------------------------------\n");
    }

}
