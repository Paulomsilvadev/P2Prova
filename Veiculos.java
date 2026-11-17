public class Veiculos {
    private String placa;
    private String modelo;
    private int ano;
    private double diaria;
    private boolean disponivel;

    public Veiculos(String placa, String modelo, int ano, double diaria, boolean disponivel) {
        setPlaca(placa);
        setModelo(modelo);
        setAno(ano);
        setDiaria(diaria);
        setDisponivel(disponivel);
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        if (ano > 1950) {
            this.ano = ano; 
        }
    }

    public double getDiaria() {
        return diaria;
    }

    public void setDiaria(double diaria) {
        if (diaria > 0) {
            this.diaria = diaria;
        }
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Placa: " + placa + " | Modelo: " + modelo + " | Ano: " + ano + 
               " | Diária: R$ " + String.format("%.2f", diaria) + " | Status: " + (disponivel ? "Disponível" : "Alugado");
    }
}