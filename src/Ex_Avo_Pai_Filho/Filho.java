package Ex_Avo_Pai_Filho;

public class Filho extends Pessoa{
    private int serieEscolar;

    public Filho(){}

    public Filho(int serieEscolar) {
        this.serieEscolar = serieEscolar;
    }

    public Filho(String nome, String cpf, Integer idade, int serieEscolar) {
        super(nome, cpf, idade);
        this.serieEscolar = serieEscolar;
    }

    @Override
    public String getTipoVoz() {
        return "";
    }

    public int getSerieEscolar() {
        return serieEscolar;
    }

    public void setSerieEscolar(int serieEscolar) {
        this.serieEscolar = serieEscolar;
    }
}
