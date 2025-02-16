package objeto;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Plantamedicinal extends SuperObjeto{

    public OBJ_Plantamedicinal(){
        nome = "plantamedicinal";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objetos/plantamedicinal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        colisao = true;
    }
}
