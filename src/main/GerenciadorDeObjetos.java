package main;

import objeto.*;

public class GerenciadorDeObjetos {
    GamePainel gamePainel;
    public GerenciadorDeObjetos(GamePainel gamePainel) {
        this.gamePainel = gamePainel;
    }

    public void setObjet(){
        gamePainel.objetos[0] = new OBJ_Machado();
        gamePainel.objetos[0].worldX = 23 * gamePainel.tileSize;
        gamePainel.objetos[0].worldY = 7 * gamePainel.tileSize;

        gamePainel.objetos[1] = new OBJ_Picareta();
        gamePainel.objetos[1].worldX = 23 * gamePainel.tileSize;
        gamePainel.objetos[1].worldY = 40 * gamePainel.tileSize;

        gamePainel.objetos[2] = new OBJ_Plantamedicinal();
        gamePainel.objetos[2].worldX = 38 * gamePainel.tileSize;
        gamePainel.objetos[2].worldY = 8 * gamePainel.tileSize;

        gamePainel.objetos[3] = new OBJ_Onca();
        gamePainel.objetos[3].worldX = 10 * gamePainel.tileSize;
        gamePainel.objetos[3].worldY = 11 * gamePainel.tileSize;

        gamePainel.objetos[4] = new OBJ_Raposa();
        gamePainel.objetos[4].worldX = 8 * gamePainel.tileSize;
        gamePainel.objetos[4].worldY = 28 * gamePainel.tileSize;

        gamePainel.objetos[5] = new OBJ_Tatu();
        gamePainel.objetos[5].worldX = 12 * gamePainel.tileSize;
        gamePainel.objetos[5].worldY = 22 * gamePainel.tileSize;

        gamePainel.objetos[6] = new OBJ_Umbuzeiro();
        gamePainel.objetos[6].worldX = 10 * gamePainel.tileSize;
        gamePainel.objetos[6].worldY = 7 * gamePainel.tileSize;
    }

}
