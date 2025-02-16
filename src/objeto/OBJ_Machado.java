package objeto;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Machado extends SuperObjeto{

    public OBJ_Machado(){
        nome = "machado";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objetos/machado.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
