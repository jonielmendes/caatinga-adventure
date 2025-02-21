package objeto;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Raposa extends SuperObjeto {

    public OBJ_Raposa() {
        nome = "raposa";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objetos/raposa.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        colisao = true;
    }
}
