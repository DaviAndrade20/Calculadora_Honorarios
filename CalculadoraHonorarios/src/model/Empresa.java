package model;

/**
 * Apenas transporta os dados lidos da tela até a calculadora.
 * Não tem lógica de cálculo.
 */
public class Empresa {

    private String razaoSocial;
    private String cnpj;
    private String regime;
    private String ramo;

    private int funcionarios;
    private int socios;
    private int notasFiscais;
    private double faturamento;

    private boolean proLabore;
    private boolean emiteNf;

    private boolean departamentoPessoal;
    private boolean escritaFiscal;
    private boolean contabilidade;
    private boolean societario;

    private String observacoes;

    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String v) { this.razaoSocial = v; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String v) { this.cnpj = v; }

    public String getRegime() { return regime; }
    public void setRegime(String v) { this.regime = v; }

    public String getRamo() { return ramo; }
    public void setRamo(String v) { this.ramo = v; }

    public int getFuncionarios() { return funcionarios; }
    public void setFuncionarios(int v) { this.funcionarios = v; }

    public int getSocios() { return socios; }
    public void setSocios(int v) { this.socios = v; }

    public int getNotasFiscais() { return notasFiscais; }
    public void setNotasFiscais(int v) { this.notasFiscais = v; }

    public double getFaturamento() { return faturamento; }
    public void setFaturamento(double v) { this.faturamento = v; }

    public boolean isProLabore() { return proLabore; }
    public void setProLabore(boolean v) { this.proLabore = v; }

    public boolean isEmiteNf() { return emiteNf; }
    public void setEmiteNf(boolean v) { this.emiteNf = v; }

    public boolean isDepartamentoPessoal() { return departamentoPessoal; }
    public void setDepartamentoPessoal(boolean v) { this.departamentoPessoal = v; }

    public boolean isEscritaFiscal() { return escritaFiscal; }
    public void setEscritaFiscal(boolean v) { this.escritaFiscal = v; }

    public boolean isContabilidade() { return contabilidade; }
    public void setContabilidade(boolean v) { this.contabilidade = v; }

    public boolean isSocietario() { return societario; }
    public void setSocietario(boolean v) { this.societario = v; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String v) { this.observacoes = v; }
}