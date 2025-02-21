package entidade;

import main.GamePainel;
import main.ControladorTecla;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entidade {
    GamePainel gamePainel;
    ControladorTecla controladorTecla;
    int spriteNum;
    int spriteCount;

    public final int screenX;
    public final int screenY;
    public int contemPicareta = 0;
    public int contemMachado = 0;
    public int contemPlantaMed = 0;

    public Player(GamePainel gamePainel, ControladorTecla controladorTecla) {
        this.gamePainel = gamePainel;
        this.controladorTecla = controladorTecla;

        screenX = gamePainel.screenWidth / 2 - (gamePainel.tileSize / 2);
        screenY = gamePainel.screenHeight / 2 - (gamePainel.tileSize / 2);

        areaSolida = new Rectangle();
        areaSolida.x = 8;
        areaSolida.y = 16;
        areaSolidaDefaultX = areaSolida.x;
        areaSolidaDefaultY = areaSolida.y;
        areaSolida.width = 32;
        areaSolida.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gamePainel.tileSize * 23;
        worldY = gamePainel.tileSize * 21;
        speed = 4;
        direction = "down";
        spriteNum = 1;
        spriteCount = 0;
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Erro: Um ou mais arquivos de imagem não foram encontrados.");
            e.printStackTrace();
        }
    }

    public void update() {
        if (controladorTecla.upPressed || controladorTecla.downPressed ||
                controladorTecla.leftPressed || controladorTecla.rightPressed) {

            if (controladorTecla.upPressed) {
                direction = "up";
            }
            if (controladorTecla.downPressed) {
                direction = "down";
            }
            if (controladorTecla.leftPressed) {
                direction = "left";
            }
            if (controladorTecla.rightPressed) {
                direction = "right";
            }

            colisaoon = false;
            gamePainel.verificadorDeColisao.VerificaBloco(this);

            int objetoIndex = gamePainel.verificadorDeColisao.verificaObjeto(this, true);
            pegarObjeto(objetoIndex);

            if (!colisaoon) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCount++;
            if (spriteCount > 12) {
                spriteNum = (spriteNum == 1) ? 2 : 1;
                spriteCount = 0;
            }
        }
    }

    public void pegarObjeto(int i) {
        if (i != 999) {
            if (gamePainel.objetos[i] == null) {
                return;
            }

            String nomeObjeto = gamePainel.objetos[i].nome.trim().toLowerCase();

            switch (nomeObjeto) {
                case "picareta":
                    gamePainel.playSE(1);
                    contemPicareta++;
                    gamePainel.objetos[i] = null;
                    gamePainel.ui.showMensagem("Você pegou uma picareta!", "objeto");
                    break;

                case "plantamedicinal":
                    gamePainel.playSE(1);
                    contemPlantaMed++;
                    gamePainel.objetos[i] = null;
                    gamePainel.ui.showMensagem("Você pegou uma planta medicinal!", "objeto");
                    break;

                case "machado":
                    gamePainel.playSE(1);
                    contemMachado++;
                    gamePainel.objetos[i] = null;
                    gamePainel.ui.showMensagem("Você pegou um machado!", "objeto");
                    break;

                case "onca":
                    if (contemMachado > 0) {
                        gamePainel.playSE(4);
                        gamePainel.objetos[i] = null;
                        contemMachado--;
                        gamePainel.ui.showMensagem("\"A onça-pintada ( Panthera onca ) é o maior predador da Caatinga, essencial para o controle das populações de presas e equilíbrio ecológico. Porém, ela sofre com a manipulação de seu habitat, causada pela destruição das florestas e expansão das áreas urbanas. Sem espaço para viver, a onça é obrigada a se aproximar das zonas humanas, colocando sua sobrevivência em risco.\"", "animal");
                    }
                    break;

                case "raposa":
                    if (contemPlantaMed > 0) {
                        gamePainel.playSE(2);
                        gamePainel.objetos[i] = null;
                        contemPlantaMed--;
                        gamePainel.ui.showMensagem("A raposa-do-campo (Lycalopex vetulus) é vital para o equilíbrio da Caatinga, controlando populações de insetos e roedores. No entanto, ela enfrenta sérias ameaças, como envenenamento por pesticidas, perseguição por ser confundida com predadores de criações e mortes ao buscar alimento em comunidades. " +
                                "Proteger essa espécie é essencial para preservar a biodiversidade e o ecossistema da Caatinga.\"", "animal");
                    }
                    break;

                case "tatu":
                    if (contemPicareta > 0) {
                        gamePainel.playSE(3);
                        gamePainel.objetos[i] = null;
                        contemPicareta--;
                        gamePainel.ui.showMensagem("O tatu-bola (Tolypeutes tricinctus) é essencial para a Caatinga, pois fertiliza o solo e controla indiretamente. No entanto, está ameaçado pela caça ilegal, já que se envolve em vez de fugir, tornando-se um alvo fácil. Preservá-lo é fundamental para o equilíbrio do bioma.", "animal");
                    }
                    break;

                case "umbuzeiro":
                    gamePainel.ui.finalizarJogo = true;
                    gamePainel.stopMusic();
                    gamePainel.playSE(5);
                    break;

            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = (spriteNum == 1) ? up1 : up2;
                break;
            case "down":
                image = (spriteNum == 1) ? down1 : down2;
                break;
            case "left":
                image = (spriteNum == 1) ? left1 : left2;
                break;
            case "right":
                image = (spriteNum == 1) ? right1 : right2;
                break;
        }
        g2.drawImage(image, screenX, screenY, gamePainel.tileSize, gamePainel.tileSize, null);
    }
}