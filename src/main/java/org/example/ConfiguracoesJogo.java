package org.example;

public class ConfiguracoesJogo {
    private String nomeJogador;
    private int nivelDificuldade;
    private boolean audioHabilitado;
    private String resolucaoTela;

    public ConfiguracoesJogo(){
        this.nomeJogador = "Jojo";
        this.nivelDificuldade = 3;
        this.audioHabilitado = true;
        this.resolucaoTela = "1920x1080";
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public int getNivelDificuldade() {
        return nivelDificuldade;
    }

    public boolean isAudioHabilitado() {
        return audioHabilitado;
    }

    public String getResolucaoTela() {
        return resolucaoTela;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public void setNivelDificuldade(int nivelDificuldade) {
        this.nivelDificuldade = nivelDificuldade;
    }

    public void setAudioHabilitado(boolean audioHabilitado) {
        this.audioHabilitado = audioHabilitado;
    }

    public void setResolucaoTela(String resolucaoTela) {
        this.resolucaoTela = resolucaoTela;
    }

    @Override
    public String toString() {
        String statusAudio = audioHabilitado ? "Habilitado" : "Desabilitado";

        return "ConfiguracoesJogo {" +
                "\n nomeJogador = '" + nomeJogador +
                ",\n nivelDificuldade = " + nivelDificuldade +
                ",\n audio = " + statusAudio +
                ",\n resolucaoTela = '" + resolucaoTela + "\n" +
                '}';
    }
}
