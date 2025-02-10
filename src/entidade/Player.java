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

    public Player(GamePainel gamePainel, ControladorTecla controladorTecla) {
        this.gamePainel = gamePainel;
        this.controladorTecla = controladorTecla;

        screenX = gamePainel.screenWidth /2 - (gamePainel.tileSize /2);
        screenY = gamePainel.screenHeight /2 - (gamePainel.tileSize /2);

        areaSolida = new Rectangle();
        areaSolida.x = 8;
        areaSolida.y = 16;
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
        spriteNum = 1; // Inicialização da variável spriteNum
        spriteCount = 0; // Inicialização da variável spriteCount
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
        if (controladorTecla.upPressed == true || controladorTecla.downPressed == true ||
                controladorTecla.leftPressed == true || controladorTecla.rightPressed == true) {

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

            if(colisaoon == false){
                switch (direction){
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            spriteCount++;
            if (spriteCount > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else {
                    spriteNum = 1;
                }
                spriteCount = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                } else if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                } else if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gamePainel.tileSize, gamePainel.tileSize, null);
    }
}