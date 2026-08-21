package model;

/**
 * ATENÇÃO: todos os valores desta classe são PARÂMETROS FICTÍCIOS,
 * usados apenas para fazer a primeira versão funcionar.
 * Deverão ser revisados conforme as regras comerciais reais da empresa.
 *
 * Este é o ÚNICO lugar que você precisa editar para mudar preços,
 * multiplicadores e faixas.
 */
public final class ConfiguracaoHonorarios {

    // Impede instanciar a classe (ela só guarda configuração).
    private ConfiguracaoHonorarios() {}

    // ----- Base do cálculo -----
    public static final double CUSTO_MENSAL       = 60000.00; // custo mensal do escritório
    public static final double MARGEM_LUCRO       = 0.30;     // 30% sobre o faturamento
    public static final int    CLIENTES_ESTIMADOS = 100;      // clientes ativos estimados

    // ----- Multiplicadores de regime tributário -----
    public static final double MULT_SIMPLES   = 1.00;
    public static final double MULT_PRESUMIDO = 1.15;
    public static final double MULT_REAL      = 1.35;

    // ----- Multiplicadores de ramo de atividade -----
    public static final double MULT_SERVICO   = 1.00;
    public static final double MULT_COMERCIO  = 1.10;
    public static final double MULT_INDUSTRIA = 1.25;

    // ----- Adicionais fixos de serviços -----
    public static final double ADIC_DEP_PESSOAL     = 150.00;
    public static final double ADIC_ESCRITA_FISCAL  = 250.00;
    public static final double ADIC_CONTABILIDADE   = 200.00;
    public static final double ADIC_SOCIETARIO      = 100.00;

    // ----- Adicionais de pró-labore e emissão de NF -----
    public static final double ADIC_PRO_LABORE = 75.00;
    public static final double ADIC_EMITE_NF   = 100.00;

    /**
     * Valor base por cliente:
     * faturamento necessário = custo / (1 - margem);
     * valor base = faturamento necessário / clientes estimados.
     * Com os valores atuais dá ~R$ 857,14.
     */
    public static double valorBase() {
        double faturamentoNecessario = CUSTO_MENSAL / (1 - MARGEM_LUCRO);
        return faturamentoNecessario / CLIENTES_ESTIMADOS;
    }

    public static double multiplicadorRegime(String regime) {
        if ("Lucro Presumido".equals(regime)) return MULT_PRESUMIDO;
        if ("Lucro Real".equals(regime))      return MULT_REAL;
        return MULT_SIMPLES; // Simples Nacional (padrão)
    }

    public static double multiplicadorRamo(String ramo) {
        if ("Comércio".equals(ramo))  return MULT_COMERCIO;
        if ("Indústria".equals(ramo)) return MULT_INDUSTRIA;
        return MULT_SERVICO; // Serviço (padrão)
    }

    public static double adicionalFuncionarios(int qtd) {
        if (qtd <= 0)  return 0;
        if (qtd <= 5)  return 100;
        if (qtd <= 10) return 200;
        if (qtd <= 20) return 350;
        if (qtd <= 50) return 600;
        return 1000;
    }

    public static double adicionalSocios(int qtd) {
        if (qtd <= 1) return 0;   // 0 ou 1 sócio não adiciona nada
        if (qtd == 2) return 50;
        if (qtd <= 5) return 100; // 3 a 5
        return 150;               // mais de 5
    }

    public static double adicionalNotasFiscais(int qtd) {
        if (qtd <= 20)  return 0;
        if (qtd <= 50)  return 100;
        if (qtd <= 100) return 200;
        if (qtd <= 300) return 350;
        if (qtd <= 500) return 600;
        return 1000;
    }

    public static double adicionalFaturamento(double faturamento) {
        if (faturamento <= 10000)   return 0;
        if (faturamento <= 50000)   return 100;
        if (faturamento <= 100000)  return 200;
        if (faturamento <= 500000)  return 350;
        if (faturamento <= 1000000) return 600;
        return 1000;
    }
}