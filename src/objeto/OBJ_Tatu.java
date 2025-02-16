package objeto;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Tatu extends SuperObjeto{

    public OBJ_Tatu(){
        nome = "tatu";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objetos/tatu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        colisao = true;
    }
}
