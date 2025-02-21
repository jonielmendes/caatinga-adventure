package objeto;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Onca extends SuperObjeto{

    public OBJ_Onca(){
        nome = "onca";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objetos/onca.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        colisao = true;
    }
}

