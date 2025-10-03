import java.util.Scanner;

public class SistemaControleTemperatura {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double temperaturaMinima;
        double temperaturaMaxima;
        double temperaturaAtual;
        double somaTemperaturas = 0;
        double menorTemperatura = 1000;
        double maiorTemperatura = -1000;
        int temperaturasAcimaMaximo = 0;
        int totalMedicoes = 24; //Caso vá testar professor, sugiro alterar esse valor para um menor para não precisar fazer 24 inputs de temperaturas, a lógica continua a memsa.

        // Entrada dos limites de temperatura
        System.out.println("=== SISTEMA DE CONTROLE DE TEMPERATURA DA ESTUFA ===");
        System.out.print("Digite a temperatura mínima permitida (°C): ");
        temperaturaMinima = scanner.nextDouble();

        System.out.print("Digite a temperatura máxima permitida (°C): ");
        temperaturaMaxima = scanner.nextDouble();

        System.out.println("\n=== REGISTRO DE TEMPERATURAS (24 HORAS) ===");

        // Laço para coletar temperaturas por 24 horas
        for (int hora = 1; hora <= totalMedicoes; hora++) {
            System.out.print("Digite a temperatura para a hora " + hora + ": ");
            temperaturaAtual = scanner.nextDouble();

            // Atualiza soma para cálculo da média
            somaTemperaturas += temperaturaAtual;

            // Verifica menor temperatura
            if (temperaturaAtual < menorTemperatura) {
                menorTemperatura = temperaturaAtual;
            }

            // Verifica maior temperatura
            if (temperaturaAtual > maiorTemperatura) {
                maiorTemperatura = temperaturaAtual;
            }

            // Conta temperaturas acima do limite máximo
            if (temperaturaAtual > temperaturaMaxima) {
                temperaturasAcimaMaximo++;
            }

            // Exibe alertas imediatos baseados em operadores lógicos
            System.out.println("Status: " + verificarStatusTemperatura(temperaturaAtual, temperaturaMinima, temperaturaMaxima));
            System.out.println("---");
        }

        // Cálculos finais
        double mediaTemperaturas = somaTemperaturas / totalMedicoes;

        // Exibição do relatório final
        System.out.println("\n=== RELATÓRIO FINAL - 24 HORAS ===");
        System.out.printf("Temperatura média: %.2f°C\n", mediaTemperaturas);
        System.out.printf("Menor temperatura registrada: %.2f°C\n", menorTemperatura);
        System.out.printf("Maior temperatura registrada: %.2f°C\n", maiorTemperatura);
        System.out.println("Temperaturas acima do limite máximo: " + temperaturasAcimaMaximo);

        // Análise final com operadores lógicos
        System.out.println("\n=== ANÁLISE DO PERÍODO ===");
        exibirAnaliseFinal(mediaTemperaturas, menorTemperatura, maiorTemperatura,
                temperaturaMinima, temperaturaMaxima, temperaturasAcimaMaximo);

        scanner.close();
    }

    // Método para verificar status individual da temperatura
    public static String verificarStatusTemperatura(double temperatura, double min, double max) {
        // Operadores lógicos para determinar o status
        if (temperatura < min) {
            return "ALERTA CRÍTICO: Temperatura ABAIXO do mínimo!";
        } else if (temperatura > max) {
            return "ALERTA: Temperatura ACIMA do máximo!";
        } else if (temperatura >= (max - 2) && temperatura <= max) {
            return "ATENÇÃO: Temperatura próxima do limite máximo";
        } else if (temperatura >= min && temperatura <= (min + 2)) {
            return "ATENÇÃO: Temperatura próxima do limite mínimo";
        } else {
            return "Temperatura dentro dos padrões";
        }
    }

    // Método para análise final do período
    public static void exibirAnaliseFinal(double media, double menor, double maior,
                                          double minPermitido, double maxPermitido, int acimaMaximo) {

        // Operadores lógicos para análise completa
        boolean periodoCritico = (menor < minPermitido) || (maior > maxPermitido);
        boolean periodoEstavel = (menor >= minPermitido) && (maior <= maxPermitido);
        boolean muitosAlertas = acimaMaximo > 5;
        boolean mediaAdequada = (media >= minPermitido) && (media <= maxPermitido);

        System.out.println("Resultado da análise:");

        if (periodoCritico && muitosAlertas) {
            System.out.println("PERÍODO CRÍTICO: Temperaturas fora dos limites múltiplas vezes!");
        } else if (periodoCritico) {
            System.out.println("PERÍODO INSTÁVEL: Algumas temperaturas fora dos limites");
        } else if (periodoEstavel && mediaAdequada) {
            System.out.println("PERÍODO ESTÁVEL: Temperaturas dentro dos padrões");
        } else {
            System.out.println("PERÍODO REGULAR: Pequenas variações detectadas");
        }

        // Análise adicional
        if (maior - menor > 10) {
            System.out.println("OBSERVAÇÃO: Grande amplitude térmica detectada");
        }

        if (acimaMaximo == 0 && periodoEstavel) {
            System.out.println("Excelente controle de temperatura!");
        }
    }
}