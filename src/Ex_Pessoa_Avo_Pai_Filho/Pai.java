package Ex_Pessoa_Avo_Pai_Filho;

public class Pai extends Pessoa{
    private String cargo;

    public Pai(){}

    public Pai(String cargo) {
        this.cargo = cargo;
    }

    public Pai(String nome, String cpf, Integer idade, String cargo) {
        super(nome, cpf, idade);
        this.cargo = cargo;
    }

    @Override
    public String getTipoVoz() {
        return "Grave";
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public void setFilhos(Filho f1, Filho f2){

    }
}
