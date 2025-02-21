package entidade;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entidade {
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCount = 0;
    public int spriteDelay = 1;
    public Rectangle areaSolida;
    public int areaSolidaDefaultX, areaSolidaDefaultY;
    public boolean colisaoon = false;
}
