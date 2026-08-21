package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Concentra a lógica da fórmula. Recebe uma Empresa e devolve o
 * honorário sugerido, já arredondado para 2 casas decimais.
 */
public class CalculoHonorarios {

    public double calcular(Empresa e) {

        // 1) Base × multiplicadores (regime e ramo)
        double valor = ConfiguracaoHonorarios.valorBase();
        valor *= ConfiguracaoHonorarios.multiplicadorRegime(e.getRegime());
        valor *= ConfiguracaoHonorarios.multiplicadorRamo(e.getRamo());

        // 2) Soma dos adicionais de serviços
        double adicionais = 0;
        if (e.isDepartamentoPessoal()) adicionais += ConfiguracaoHonorarios.ADIC_DEP_PESSOAL;
        if (e.isEscritaFiscal())       adicionais += ConfiguracaoHonorarios.ADIC_ESCRITA_FISCAL;
        if (e.isContabilidade())       adicionais += ConfiguracaoHonorarios.ADIC_CONTABILIDADE;
        if (e.isSocietario())          adicionais += ConfiguracaoHonorarios.ADIC_SOCIETARIO;

        // 3) Adicionais por faixa
        adicionais += ConfiguracaoHonorarios.adicionalFuncionarios(e.getFuncionarios());
        adicionais += ConfiguracaoHonorarios.adicionalSocios(e.getSocios());
        adicionais += ConfiguracaoHonorarios.adicionalNotasFiscais(e.getNotasFiscais());
        adicionais += ConfiguracaoHonorarios.adicionalFaturamento(e.getFaturamento());

        // 4) Pró-labore e emissão de NF
        if (e.isProLabore()) adicionais += ConfiguracaoHonorarios.ADIC_PRO_LABORE;
        if (e.isEmiteNf())   adicionais += ConfiguracaoHonorarios.ADIC_EMITE_NF;

        double total = valor + adicionais;
        return arredondar(total);
    }

    /** Arredonda para 2 casas (padrão para dinheiro). */
    private double arredondar(double valor) {
        return BigDecimal.valueOf(valor)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}