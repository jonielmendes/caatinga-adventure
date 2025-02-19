package main;

import javax.swing.*;

public class Main {
    private JFrame window;
    private GamePainel gamePainel; // Painel do jogo

    public Main() {
        // Exibe
        // Carrega o jogo em segundo plano
        carregarJogoEmSegundoPlano();
    }

    private void exibirTelaInicial() {
        // Cria a tela inicial e passa a ação de iniciar o jogo
        new TelaInicio(this::iniciarJogo);
    }

    private void carregarJogoEmSegundoPlano() {
        // Usa uma thread separada para carregar o jogo
        new Thread(() -> {
            window = new JFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setTitle("Caatinga Adventure");

            gamePainel = new GamePainel(); // Cria o painel do jogo
            window.add(gamePainel);

            window.pack();
            window.setLocationRelativeTo(null);

            gamePainel.setupGame(); // Configura o jogo
        }).start();
    }

    private void iniciarJogo() {
        // Exibe a janela do jogo (já carregada em segundo plano)
        window.setVisible(true);
        gamePainel.startGameThread(); // Inicia o loop do jogo
    }

    public static void main(String[] args) {
        // Executa a classe Main
        SwingUtilities.invokeLater(() -> {
            new Main();
        });
    }
}