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

        gerarHeader("Configuracoes", "double");
        int op = 0;

        while(op != 6) {
            gerarMenu("Ver as configuracoes", "Alterar nome", "Alterar nivel", "Alterar audio", "Alterar resolucao", "Sair e Salvar");
            try{
                op = sc.nextInt();
                sc.nextLine();

                switch(op) {
                    case 1: verConfiguracoes(); break;
                    case 2: alterarNome(); break;
                    case 3: alterarNivel(); break;
                    case 4: alterarAudio(); break;
                    case 5: alterarResolucao(); break;
                    case 6:
                        System.out.println("Salvando configuracoes...");
                        break;
                    default:
                        System.out.println("Opcao invalida!");
                        break;
                }
            } catch(InputMismatchException e) {
                System.out.println("erro: Por favor, digite um numero: ");
                sc.nextLine();
            }
        }
        manager.salvar(configuracoes);
        System.out.println("Configuracoes salvas em 'config_jogo.json'.");
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
            sc.nextLine();

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

    private static void alterarResolucao() {
        gerarMenu("256x144", "426x240", "640x360", "854x480", "1080x720", "1920x1080", "2560x1440", "3840x2160");

        try{
            int op = sc.nextInt();
            String resolucao = configuracoes.getResolucaoTela();

            if(op > 0 && op < 9) {
                switch (op) {
                    case 1: resolucao = "256x144"; break;
                    case 2: resolucao = "426x240"; break;
                    case 3: resolucao = "640x360"; break;
                    case 4: resolucao = "854x480"; break;
                    case 5: resolucao = "1080x720"; break;
                    case 6: resolucao = "1920x1080"; break;
                    case 7: resolucao = "2560x1440"; break;
                    case 8: resolucao = "3840x2160"; break;
                }
                configuracoes.setResolucaoTela(resolucao);
            } else {
                System.out.println("Digite entre 1 e 9...");
            }
        } catch(InputMismatchException e) {
            System.out.println("erro: Você deve escrever os numeros. " + e.getMessage());
        }
    }

    public static void gerarMenu(String... titulos) {
        for(int i = 0; i < titulos.length; i++) {
            System.out.println((i+1) + ". " + titulos[i]);
        }
        System.out.println("Escolha um: ");
    }
}