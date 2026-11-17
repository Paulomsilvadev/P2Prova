public class GerenciadorVeiculos {
    private Veiculos[] vetor;
    private int tamanhoLogico;
    private int capacidade;

    public GerenciadorVeiculos() {
        this.capacidade = 10;
        this.vetor = new Veiculos[capacidade];
        this.tamanhoLogico = 0;
    }

    private void redimensionar() {
        int crescimento = Math.max(1, capacidade / 2);
        int novaCapacidade = capacidade + crescimento;
        Veiculos[] novoVetor = new Veiculos[novaCapacidade];
        
        for (int i = 0; i < tamanhoLogico; i++) {
            novoVetor[i] = vetor[i];
        }
        
        vetor = novoVetor;
        capacidade = novaCapacidade;
        System.out.println("\n[Aviso] Vetor cheio! Nova capacidade redimensionada para: " + capacidade);
    }

    public int buscaBinariaRecursiva(String placa, int inicio, int fim) {
        if (inicio > fim) {
            return -1;
        }
        int meio = inicio + (fim - inicio) / 2;
        int comparacao = vetor[meio].getPlaca().compareToIgnoreCase(placa);
        
        if (comparacao == 0) {
            return meio;
        } else if (comparacao > 0) {
            return buscaBinariaRecursiva(placa, inicio, meio - 1);
        } else {
            return buscaBinariaRecursiva(placa, meio + 1, fim);
        }
    }

    public boolean inserir(Veiculos v) {
        if (tamanhoLogico > 0 && buscaBinariaRecursiva(v.getPlaca(), 0, tamanhoLogico - 1) != -1) {
            System.out.println("Erro: Já existe um veículo cadastrado com a placa " + v.getPlaca());
            return false;
        }
        
        if (tamanhoLogico >= capacidade) {
            redimensionar();
        }

        int i = tamanhoLogico - 1;
        while (i >= 0 && vetor[i].getPlaca().compareToIgnoreCase(v.getPlaca()) > 0) {
            vetor[i + 1] = vetor[i];
            i--;
        }
        vetor[i + 1] = v;
        tamanhoLogico++;
        return true;
    }

    public boolean remover(String placa) {
        int pos = buscaBinariaRecursiva(placa, 0, tamanhoLogico - 1);
        if (pos == -1) {
            System.out.println("Erro: Veículo não encontrado.");
            return false;
        }

        if (!vetor[pos].isDisponivel()) {
            System.out.println("Erro: Não é permitido remover veículo que esteja alugado!");
            return false;
        }

        for (int i = pos; i < tamanhoLogico - 1; i++) {
            vetor[i] = vetor[i + 1];
        }
        vetor[tamanhoLogico - 1] = null;
        tamanhoLogico--;
        System.out.println("Veículo removido com sucesso!");
        return true;
    }

    public boolean atualizar(String placaAntiga, String novaPlaca, String novoModelo, int novoAno, double novaDiaria, boolean novaDisponibilidade) {
        int pos = buscaBinariaRecursiva(placaAntiga, 0, tamanhoLogico - 1);
        if (pos == -1) {
            System.out.println("Erro: Veículo não encontrado.");
            return false;
        }

        if (!placaAntiga.equalsIgnoreCase(novaPlaca)) {
            if (buscaBinariaRecursiva(novaPlaca, 0, tamanhoLogico - 1) != -1) {
                System.out.println("Erro: A nova placa informada já pertence a outro veículo.");
                return false;
            }
        }

        Veiculos v = vetor[pos];
        v.setModelo(novoModelo);
        v.setAno(novoAno);
        v.setDiaria(novaDiaria);
        v.setDisponivel(novaDisponibilidade);

        if (!placaAntiga.equalsIgnoreCase(novaPlaca)) {
            v.setPlaca(novaPlaca);
            reordenarPrincipal(); 
        }

        System.out.println("Veículo atualizado com sucesso!");
        return true;
    }

    private void reordenarPrincipal() {
        for (int i = 1; i < tamanhoLogico; i++) {
            Veiculos chave = vetor[i];
            int j = i - 1;
            while (j >= 0 && vetor[j].getPlaca().compareToIgnoreCase(chave.getPlaca()) > 0) {
                vetor[j + 1] = vetor[j];
                j--;
            }
            vetor[j + 1] = chave;
        }
    }

    public void listarPrincipal() {
        if (tamanhoLogico == 0) {
            System.out.println("Nenhum veículo cadastrado.");
            return;
        }
        System.out.println("\n--- Lista de Veículos (Ordenados por Placa) ---");
        for (int i = 0; i < tamanhoLogico; i++) {
            System.out.println(vetor[i]);
        }
    }

    public void relatorioPorDiaria() {
        if (tamanhoLogico == 0) {
            System.out.println("Nenhum veículo cadastrado.");
            return;
        }

        Veiculos[] copia = new Veiculos[tamanhoLogico];
        for (int i = 0; i < tamanhoLogico; i++) {
            copia[i] = vetor[i];
        }

        for (int i = 1; i < copia.length; i++) {
            Veiculos chave = copia[i];
            int j = i - 1;
            while (j >= 0 && copia[j].getDiaria() > chave.getDiaria()) {
                copia[j + 1] = copia[j];
                j--;
            }
            copia[j + 1] = chave;
        }

        System.out.println("\n--- Relatório: Veículos Ordenados por Diária ---");
        for (int i = 0; i < copia.length; i++) {
            System.out.println(copia[i]);
        }
    }
}