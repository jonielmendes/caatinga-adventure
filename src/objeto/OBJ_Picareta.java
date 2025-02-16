package objeto;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Picareta extends SuperObjeto{

    public OBJ_Picareta(){
       nome = "picareta";
       try {
              image = ImageIO.read(getClass().getResourceAsStream("/objetos/picareta.png"));
         } catch (IOException e) {
              e.printStackTrace();
       }
       colisao = true;
    }
}
