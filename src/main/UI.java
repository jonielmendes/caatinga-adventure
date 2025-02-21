package main;

import objeto.OBJ_Machado;
import objeto.OBJ_Picareta;
import objeto.OBJ_Plantamedicinal;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePainel gamePainel;
    Font arial_40;
    BufferedImage picaretaImage;
    BufferedImage machadoImage;
    BufferedImage plantaMedImage;
    public boolean mensagemOn = false;
    public String mensagem = "";
    int contadorMensagem = 0;
    public boolean finalizarJogo = false;

    public UI(GamePainel gamePainel) {
        this.gamePainel = gamePainel;
        arial_40 = new Font("Arial", Font.PLAIN, 20);

        OBJ_Picareta picareta = new OBJ_Picareta();
        picaretaImage = picareta.image;
        OBJ_Machado machado = new OBJ_Machado();
        machadoImage = machado.image;
        OBJ_Plantamedicinal plantamedicinal = new OBJ_Plantamedicinal();
        plantaMedImage = plantamedicinal.image;
    }

    public void showMensagem(String texto, String tipoObjeto) {
        mensagem = texto;
        mensagemOn = true;

        // Ajusta o tempo de exibição da mensagem com base no tipo de objeto
        if (tipoObjeto.equals("animal")) {
            contadorMensagem = -240; // Dura o dobro do tempo (240 frames)
        } else {
            contadorMensagem = 0; // Tempo normal (120 frames)
        }
    }

    public void draw(Graphics2D g2) {

        if (finalizarJogo) {
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, gamePainel.screenWidth, gamePainel.screenHeight);
            g2.setColor(Color.WHITE);
            g2.setFont(arial_40);
            g2.drawString("Fim de jogo!", gamePainel.screenWidth / 2 - 80, gamePainel.screenHeight / 2);
            return;
        }


        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        // Desenha as imagens dos objetos lado a lado
        int startX = gamePainel.tileSize / 2;
        int y = gamePainel.tileSize / 2;
        int spacing = gamePainel.tileSize + 13;

        g2.drawImage(picaretaImage, startX, y, gamePainel.tileSize, gamePainel.tileSize, null);
        g2.drawString("x " + gamePainel.player.contemPicareta, startX, y + gamePainel.tileSize + 20);

        g2.drawImage(machadoImage, startX + spacing, y, gamePainel.tileSize, gamePainel.tileSize, null);
        g2.drawString("x " + gamePainel.player.contemMachado, startX + spacing, y + gamePainel.tileSize + 20);

        g2.drawImage(plantaMedImage, startX + 2 * spacing, y, gamePainel.tileSize, gamePainel.tileSize, null);
        g2.drawString("x " + gamePainel.player.contemPlantaMed, startX + 2 * spacing, y + gamePainel.tileSize + 20);


        // Desenha a mensagem
        if (mensagemOn) {
            int messageX = gamePainel.tileSize / 2;
            int messageY = gamePainel.screenHeight - gamePainel.tileSize * 3; // Ajuste para posicionar mais acima
            int maxWidth = gamePainel.screenWidth - 40; // Define a largura máxima da mensagem

            // Quebra automática da mensagem em várias linhas
            FontMetrics fm = g2.getFontMetrics();
            String[] words = mensagem.split(" ");
            StringBuilder currentLine = new StringBuilder();
            java.util.List<String> lines = new java.util.ArrayList<>();

            for (String word : words) {
                if (fm.stringWidth(currentLine + " " + word) > maxWidth) {
                    lines.add(currentLine.toString());
                    currentLine = new StringBuilder(word);
                } else {
                    if (!currentLine.isEmpty()) currentLine.append(" ");
                    currentLine.append(word);
                }
            }
            lines.add(currentLine.toString()); // Adiciona a última linha

            // Desenha cada linha corretamente espaçada
            int lineHeight = fm.getHeight();
            for (String line : lines) {
                g2.drawString(line, messageX, messageY);
                messageY += lineHeight;
            }

            // Controla o tempo de exibição da mensagem
            contadorMensagem++;
            if (contadorMensagem > (contadorMensagem < 0 ? 240 : 120)) {
                mensagemOn = false;
                contadorMensagem = 0;
            }
        }
    }
}