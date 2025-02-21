package objeto;

import main.GamePainel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObjeto {

    public BufferedImage image;
    public String nome;
    public boolean colisao = false;
    public int worldX, worldY;
    public Rectangle areaSolida = new Rectangle(0, 0, 48, 48);
    public int areaSolidaDefaultX = 0;
    public int areaSolidaDefaultY = 0;

    public void draw(Graphics2D g2, GamePainel gamePainel) {
        int screenX = worldX - gamePainel.player.worldX + gamePainel.player.screenX;
        int screenY = worldY - gamePainel.player.worldY + gamePainel.player.screenY;

        if (worldX + gamePainel.tileSize >gamePainel.player.worldX - gamePainel.player.screenX &&
                worldX - gamePainel.tileSize< gamePainel.player.worldX + gamePainel.player.screenX &&
                worldY + gamePainel.tileSize> gamePainel.player.worldY - gamePainel.player.screenY &&
                worldY - gamePainel.tileSize< gamePainel.player.worldY + gamePainel.player.screenY) {

            g2.drawImage(image, screenX, screenY, gamePainel.tileSize, gamePainel.tileSize, null);
        }
    }
}
