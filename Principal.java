import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GerenciadorVeiculos gerenciador = new GerenciadorVeiculos();

        gerenciador.inserir(new Veiculos("TJC-2129", "CG Start 160", 2024, 120, true));
        gerenciador.inserir(new Veiculos("RFD-8410", "CB 300F Twister", 2023, 1250, true));
        gerenciador.inserir(new Veiculos("KLM-5921", "Fazer FZ25", 2024, 100, false));
        gerenciador.inserir(new Veiculos("PQA-1047", "NXR 160 Bros", 2022, 100, true));
        gerenciador.inserir(new Veiculos("BGT-3382", "XRE 300 Sahara", 2024, 150, true));
        gerenciador.inserir(new Veiculos("HJK-7745", "Factor 150", 2021, 120, false));
        gerenciador.inserir(new Veiculos("ZXY-9012", "PCX 160", 2023, 90, true));
        gerenciador.inserir(new Veiculos("MNB-4568", "Lander 250", 2024, 120, true));
        gerenciador.inserir(new Veiculos("QWE-6193", "Biz 125", 2022, 40, false));
        gerenciador.inserir(new Veiculos("ASD-2840", "MT-03", 2024, 200, true));

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Inserir novo veículo");
            System.out.println("2 - Remover veículo");
            System.out.println("3 - Atualizar veículo");
            System.out.println("4 - Listar veículos (Ordenados por Placa)");
            System.out.println("5 - Relatório de veículos (Ordenados por Diária)");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            if (sc.hasNextInt()) {
                opcao = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Opção inválida! Digite um número.");
                sc.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Digite a placa (ex: ABC-1234): ");
                    String placa = sc.nextLine();
                    System.out.print("Digite o modelo: ");
                    String modelo = sc.nextLine();
                    System.out.print("Digite o ano (> 1950): ");
                    int ano = sc.nextInt();
                    System.out.print("Digite o valor da diária (> 0): ");
                    double diaria = sc.nextDouble();
                    System.out.print("Está disponível? (true/false): ");
                    boolean disponivel = sc.nextBoolean();
                    sc.nextLine();

                    Veiculos novo = new Veiculos(placa, modelo, ano, diaria, disponivel);
                    gerenciador.inserir(novo);
                    break;

                case 2:
                    System.out.print("Digite a placa do veículo que deseja remover: ");
                    String placaRemover = sc.nextLine();
                    gerenciador.remover(placaRemover);
                    break;

                case 3:
                    System.out.print("Digite a placa atual do veículo que deseja atualizar: ");
                    String placaAntiga = sc.nextLine();
                    System.out.print("Nova placa: ");
                    String novaPlaca = sc.nextLine();
                    System.out.print("Novo modelo: ");
                    String novoModelo = sc.nextLine();
                    System.out.print("Novo ano: ");
                    int novoAno = sc.nextInt();
                    System.out.print("Nova diária: ");
                    double novaDiaria = sc.nextDouble();
                    System.out.print("Está disponível? (true/false): ");
                    boolean novaDisp = sc.nextBoolean();
                    sc.nextLine();

                    gerenciador.atualizar(placaAntiga, novaPlaca, novoModelo, novoAno, novaDiaria, novaDisp);
                    break;

                case 4:
                    gerenciador.listarPrincipal();
                    break;

                case 5:
                    gerenciador.relatorioPorDiaria();
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        sc.close();
    }
}