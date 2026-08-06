package org.example;

import io.github.kamilszewc.javaansitextcolorizer.Colorizer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final GerenciadorDeConfiguracoes manager = new GerenciadorDeConfiguracoes("config_jogo.json");
    private static final Scanner sc = new Scanner(System.in);
    private static ConfiguracoesJogo configuracoes;

    public static void gerarHeader(String titulo, String tipo) {
        //Criação da string da separação e int quantidade de repetições
        String sep;
        int i = 50;

        //Decisão de tipo do separador
        switch (tipo) {
            case "single":
                sep = "-";
                break;

            case "double":
                sep = "=";
                break;

            case "plus":
                sep = "+";
                break;

            case "special":
                sep = "-+";
                i = 25;
                break;

            default:
                sep = "-";
                break;
        }

        //Repetição do caractere de separação
        for(int j = 0; j < i; j++) {
            System.out.print(Colorizer.color(sep, Colorizer.Color.BLUE));
        }

        //Reprodução do título
        System.out.print(Colorizer.color("\n  " + titulo + "\n", Colorizer.Color.YELLOW));

        //Repetição do caractere de separação
        for(int j = 0; j < i; j++) {
            System.out.print(Colorizer.color(sep, Colorizer.Color.BLUE));
        }

        //Pular linha
        System.out.println();

        //Finalização do Header
    }

    public static void main(String[] args) {
        configuracoes = manager.carregar();

        gerarHeader("Configurações", "double");
        int op = 0;

        while(op != 5) {
            gerarMenu("Ver as configurações", "Alterar nome", "Alterar nivel", "Alterar audio", "Sair");
            try{
                op = sc.nextInt();
                sc.nextLine();

                switch(op) {
                    case 1: verConfiguracoes(); break;
                    case 2: alterarNome(); break;
                    case 3: alterarNivel(); break;
                    case 4: alterarAudio(); break;
                    case 5:
                        System.out.println("Salvando configurações...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch(InputMismatchException e) {
                System.out.println("erro: Por favor, digite um número: ");
                sc.nextLine();
            }
        }
        manager.salvar(configuracoes);
        System.out.println("Configurações salvas em 'config_jogo.json'.");
    }

    private static void verConfiguracoes() {
        System.out.println(configuracoes.toString());
    }

    private static void alterarNome() {
        System.out.println("Digite um novo nome: ");
        String nome = sc.nextLine();
        configuracoes.setNomeJogador(nome);
        System.out.println("Nome do jogador alterado para: " + nome);
    }

    private static void alterarNivel() {
        gerarMenu("Easy", "Medium", "Hard", "Insane", "Madhouse");

        try {
            int nivel = sc.nextInt();

            if(nivel > 0 && nivel < 6) {
                configuracoes.setNivelDificuldade(nivel);
                System.out.println("Dificuldade do jogo alterado para: " + nivel);
            } else{
                System.out.println("Nível inválido, selecione os disponíveis.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida; Digite um número.");
            sc.nextLine();
        }
    }

    private static void alterarAudio() {
        boolean audioAtual = configuracoes.isAudioHabilitado();
        configuracoes.setAudioHabilitado(!audioAtual);
        String statusAudio = configuracoes.isAudioHabilitado() ? "Habilitado" : "Desabilitado";
        System.out.println("Audio agora está: " + statusAudio);
    }

    public static void gerarMenu(String... titulos) {
        for(int i = 0; i < titulos.length; i++) {
            System.out.println((i+1) + ". " + titulos[i]);
        }
        System.out.println("Escolha um: ");
    }
}