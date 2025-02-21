package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class TelaInicio extends JFrame {
    private Image fundo;

    public TelaInicio(Runnable onIniciarJogo) {
        setTitle("Tela Início");
        setSize(800, 600); // Tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela
        setResizable(false);

        // Carrega a imagem de fundo
        fundo = new ImageIcon("res/imagens/tela_inicio.png").getImage(); // Caminho da imagem

        // Painel personalizado para desenhar o fundo
        JPanel painel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fundo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        painel.setLayout(null); // Usamos layout nulo para posicionar os botões manualmente

        // Carrega a fonte pixelada (PIXroma_8.ttf)
        String caminhoFonte = "res/fontes/PIXroma_8.ttf";
        File arquivoFonte = new File(caminhoFonte);

        Font fonteRetro;
        try {
            fonteRetro = Font.createFont(Font.TRUETYPE_FONT, arquivoFonte).deriveFont(20f);
            System.out.println("Fonte carregada com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            fonteRetro = new Font("Arial", Font.BOLD, 20); // Fallback para uma fonte padrão
            System.err.println("Erro ao carregar a fonte. Usando fallback.");
        }

        // Botão "Iniciar Jogo" personalizado
        JButton botaoJogar = new JButton("JOGAR") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Bordas arredondadas
                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 0, 0, 0)); // Borda transparente
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30); // Bordas arredondadas
                g2.dispose();
            }
        };

        // Personalização do botão "Jogar"
        botaoJogar.setBounds(200, 480, 180, 50); // Posição (x = 200, y = 480)
        botaoJogar.setFont(fonteRetro); // Aplica a fonte pixelada
        botaoJogar.setForeground(Color.WHITE); // Cor do texto
        botaoJogar.setBackground(new Color(34, 139, 34)); // Verde escuro
        botaoJogar.setFocusPainted(false); // Remove o contorno ao focar
        botaoJogar.setBorderPainted(false); // Remove a borda padrão

        // Efeito de hover (quando o mouse passa por cima)
        botaoJogar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botaoJogar.setBackground(new Color(50, 205, 50)); // Verde mais claro ao passar o mouse
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                botaoJogar.setBackground(new Color(34, 139, 34)); // Volta ao verde escuro
            }
        });

        // Ação ao clicar no botão "Jogar"
        botaoJogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pré-carrega os recursos antes de iniciar o jogo
                new Thread(() -> {
                    // Simula o carregamento de recursos (substitua pelo carregamento real)
                    try {
                        Thread.sleep(1000); // Simula um tempo de carregamento
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                    // Fecha a tela inicial e inicia o jogo
                    SwingUtilities.invokeLater(() -> {
                        dispose(); // Fecha a tela inicial
                        onIniciarJogo.run(); // Executa a ação de iniciar o jogo
                    });
                }).start();
            }
        });

        // Botão "Instruções" personalizado
        JButton botaoInstrucoes = new JButton("DICAS") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Bordas arredondadas
                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 0, 0, 0)); // Borda transparente
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30); // Bordas arredondadas
                g2.dispose();
            }
        };

        // Personalização do botão "Instruções"
        botaoInstrucoes.setBounds(420, 480, 180, 50); // Posição (x = 420, y = 480)
        botaoInstrucoes.setFont(fonteRetro); // Aplica a fonte pixelada
        botaoInstrucoes.setForeground(Color.WHITE); // Cor do texto
        botaoInstrucoes.setBackground(new Color(34, 139, 34)); // Verde escuro
        botaoInstrucoes.setFocusPainted(false); // Remove o contorno ao focar
        botaoInstrucoes.setBorderPainted(false); // Remove a borda padrão

        // Efeito de hover (quando o mouse passa por cima)
        botaoInstrucoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botaoInstrucoes.setBackground(new Color(50, 205, 50)); // Verde mais claro ao passar o mouse
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                botaoInstrucoes.setBackground(new Color(34, 139, 34)); // Volta ao verde escuro
            }
        });

        // Ação ao clicar no botão "Instruções"
        botaoInstrucoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a tela inicial
                new TelaInstrucao(() -> new TelaInicio(onIniciarJogo).setVisible(true)); // Abre a tela de instruções
            }
        });

        painel.add(botaoJogar); // Adiciona o botão "Jogar" ao painel
        painel.add(botaoInstrucoes); // Adiciona o botão "Instruções" ao painel
        add(painel); // Adiciona o painel à janela

        setVisible(true); // Torna a tela inicial visível
    }
}