package Ex_Pessoa_Avo_Pai_Filho;

public class Avo extends Pessoa {
    private int numeroINSS;

    public Avo(){}

    public Avo(int numeroINSS) {
        this.numeroINSS = numeroINSS;
    }

    public Avo(String nome, String cpf, Integer idade, int numeroINSS) {
        super(nome, cpf, idade);
        this.numeroINSS = numeroINSS;
    }

    @Override
    public String getTipoVoz() {
        return "Roca";
    }

    public int getNumeroINSS() {
        return numeroINSS;
    }

    public void setNumeroINSS(int numeroINSS) {
        this.numeroINSS = numeroINSS;
    }

}
