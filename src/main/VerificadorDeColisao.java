package main;

import entidade.Entidade;

public class VerificadorDeColisao {
    GamePainel gamePainel;
    public VerificadorDeColisao(GamePainel gamePainel) {
        this.gamePainel = gamePainel;

    }

    public void VerificaBloco(Entidade entidade){
        int entidadeLeftWorldX = entidade.worldX + entidade.areaSolida.x;
        int entidadeRightWorldX = entidade.worldX + entidade.areaSolida.x + entidade.areaSolida.width;
        int entidadeTopWorldY = entidade.worldY + entidade.areaSolida.y;
        int entidadeBottomWorldY = entidade.worldY + entidade.areaSolida.y + entidade.areaSolida.height;

        int entidadeLeftCol = entidadeLeftWorldX / gamePainel.tileSize;
        int entidadeRightCol = entidadeRightWorldX / gamePainel.tileSize;
        int entidadeTopRow = entidadeTopWorldY / gamePainel.tileSize;
        int entidadeBottomRow = entidadeBottomWorldY / gamePainel.tileSize;

        int blocoNum1, blocoNum2;

        switch (entidade.direction) {
            case "up":
                entidadeTopRow = (entidadeTopWorldY - entidade.speed) / gamePainel.tileSize;
                blocoNum1 =gamePainel.gerenciadorDeBlocos.mapablocoNum[entidadeLeftCol][entidadeTopRow];
                blocoNum2 =gamePainel.gerenciadorDeBlocos.mapablocoNum[entidadeRightCol][entidadeTopRow];
                if(gamePainel.gerenciadorDeBlocos.bloco[blocoNum1].colisao == true || gamePainel.gerenciadorDeBlocos.bloco[blocoNum2].colisao == true){
                    entidade.colisaoon = true;
                }
                break;
            case "down":
                entidadeBottomRow = (entidadeBottomWorldY + entidade.speed) / gamePainel.tileSize;
                blocoNum1 =gamePainel.gerenciadorDeBlocos.mapablocoNum[entidadeLeftCol][entidadeBottomRow];
                blocoNum2 =gamePainel.gerenciadorDeBlocos.mapablocoNum[entidadeRightCol][entidadeBottomRow];
                if(gamePainel.gerenciadorDeBlocos.bloco[blocoNum1].colisao == true || gamePainel.gerenciadorDeBlocos.bloco[blocoNum2].colisao == true){
                    entidade.colisaoon = true;
                }

                break;
            case "left":
                entidadeLeftCol = (entidadeLeftWorldX - entidade.speed) / gamePainel.tileSize;
                blocoNum1 =gamePainel.gerenciadorDeBlocos.mapablocoNum[entidadeLeftCol][entidadeTopRow];
                blocoNum2 =gamePainel.gerenciadorDeBlocos.mapablocoNum[entidadeLeftCol][entidadeBottomRow];
                if(gamePainel.gerenciadorDeBlocos.bloco[blocoNum1].colisao == true || gamePainel.gerenciadorDeBlocos.bloco[blocoNum2].colisao == true){
                    entidade.colisaoon = true;
                }
                break;
            case "right":
                entidadeRightCol = (entidadeRightWorldX + entidade.speed) / gamePainel.tileSize;
                blocoNum1 =gamePainel.gerenciadorDeBlocos.mapablocoNum[entidadeRightCol][entidadeTopRow];
                blocoNum2 =gamePainel.gerenciadorDeBlocos.mapablocoNum[entidadeRightCol][entidadeBottomRow];
                if(gamePainel.gerenciadorDeBlocos.bloco[blocoNum1].colisao == true || gamePainel.gerenciadorDeBlocos.bloco[blocoNum2].colisao == true){
                    entidade.colisaoon = true;
                }
                break;
        }

    }
}
