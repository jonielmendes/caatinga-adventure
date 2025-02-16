package objeto;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Umbuzeiro extends SuperObjeto{

        public OBJ_Umbuzeiro(){
            nome = "umbuzeiro";

            try {
                image = ImageIO.read(getClass().getResourceAsStream("/objetos/umbuzeiro.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            colisao = true;
        }
}
