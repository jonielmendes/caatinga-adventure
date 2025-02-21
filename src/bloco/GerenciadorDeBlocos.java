package bloco;

import main.GamePainel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

public class GerenciadorDeBlocos {
    GamePainel gamePainel;
   public Bloco[] bloco;
   public int mapablocoNum [][];

    public GerenciadorDeBlocos(GamePainel gamePainel) {
        this.gamePainel = gamePainel;
        bloco = new Bloco[10];
        mapablocoNum = new int[gamePainel.maxWorldCol][gamePainel.maxWorldRow];
        ;

        getBlocoImage();
        loadMap("/mapas/caatingafase1.txt");
    }

    public void getBlocoImage() {
        try {
            bloco[0] = new Bloco();
            bloco[0].image = ImageIO.read(getClass().getResourceAsStream("/blocos/grass.png"));

            bloco[1] = new Bloco();
            bloco[1].image = ImageIO.read(getClass().getResourceAsStream("/blocos/wall.png"));
            bloco[1].colisao = true;

            bloco[2] = new Bloco();
            bloco[2].image = ImageIO.read(getClass().getResourceAsStream("/blocos/water.png"));
            bloco[2].colisao = true;

            bloco[3] = new Bloco();
            bloco[3].image = ImageIO.read(getClass().getResourceAsStream("/blocos/earth.png"));

            bloco[4] = new Bloco();
            bloco[4].image = ImageIO.read(getClass().getResourceAsStream("/blocos/tree.png"));
            bloco[4].colisao = true;

            bloco[5] = new Bloco();
            bloco[5].image = ImageIO.read(getClass().getResourceAsStream("/blocos/sand.png"));

            bloco[6] = new Bloco();
            bloco[6].image = ImageIO.read(getClass().getResourceAsStream("/blocos/cacto.png"));

            bloco[7] = new Bloco();
            bloco[7].image = ImageIO.read(getClass().getResourceAsStream("/blocos/mandacaru.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try (InputStream is = getClass().getResourceAsStream(filePath);
             BufferedReader br = new BufferedReader(new java.io.InputStreamReader(is))) {

            if (is == null) {
                throw new IOException("Arquivo do mapa não encontrado!");
            }

            for (int row = 0; row < gamePainel.maxWorldRow; row++) {
                String line = br.readLine();
                if (line == null) break;
                String[] numbers = line.split(" ");
                for (int col = 0; col < gamePainel.maxWorldCol; col++) {
                    mapablocoNum[col][row] = Integer.parseInt(numbers[col]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gamePainel.maxWorldCol && worldRow < gamePainel.maxWorldRow) {
            int blocoNum = mapablocoNum[worldCol][worldRow];

            int worldX = worldCol * gamePainel.tileSize;
            int worldY = worldRow * gamePainel.tileSize;
            int screenX = worldX - gamePainel.player.worldX + gamePainel.player.screenX;
            int screenY = worldY - gamePainel.player.worldY + gamePainel.player.screenY;

            if (worldX + gamePainel.tileSize >gamePainel.player.worldX - gamePainel.player.screenX &&
                worldX - gamePainel.tileSize< gamePainel.player.worldX + gamePainel.player.screenX &&
                worldY + gamePainel.tileSize> gamePainel.player.worldY - gamePainel.player.screenY &&
                worldY - gamePainel.tileSize< gamePainel.player.worldY + gamePainel.player.screenY) {

                g2.drawImage(bloco[blocoNum].image, screenX, screenY, gamePainel.tileSize, gamePainel.tileSize, null);
            }



                worldCol++;

            if (worldCol == gamePainel.maxWorldCol) {
                worldCol = 0;
                worldRow++;

            }
        }

    }
}