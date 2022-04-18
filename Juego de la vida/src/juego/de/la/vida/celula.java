
package juego.de.la.vida;

/**
 * Una celula (una celda del tablero)
 * @author Miguel Alberto Bazán Silva
 */
public class celula {
    //Celulas vecinas
    celula vecinoUp;
    celula vecinoDown;
    celula vecinoLeft;
    celula vecinoRight;
    celula vecinoUpLeft;
    celula vecinoUpRight;
    celula vecinoDownLeft;
    celula vecinoDownRight;
    
    boolean estado = false; // False: Muerta || True: Viva
    
    public celula(  celula vecinoUp,
                    celula vecinoDown, 
                    celula vecinoLeft, 
                    celula vecinoRight, 
                    celula vecinoUpLeft,
                    celula vecinoUpRight,
                    celula vecinoDownLeft,
                    celula vecinoDownRight
                ){
        this.vecinoUp = vecinoUp;
        this.vecinoDown = vecinoDown;
        this.vecinoLeft = vecinoLeft;
        this.vecinoRight = vecinoRight;
        this.vecinoUpLeft = vecinoUpLeft;
        this.vecinoUpRight = vecinoUpRight;
        this.vecinoDownLeft = vecinoDownLeft;
        this.vecinoDownRight = vecinoDownRight;
        this.estado = false;
    }
    public celula(  boolean estado,
                    celula vecinoUp,
                    celula vecinoDown, 
                    celula vecinoLeft, 
                    celula vecinoRight, 
                    celula vecinoUpLeft,
                    celula vecinoUpRight,
                    celula vecinoDownLeft,
                    celula vecinoDownRight
                ){
        this.vecinoUp = vecinoUp;
        this.vecinoDown = vecinoDown;
        this.vecinoLeft = vecinoLeft;
        this.vecinoRight = vecinoRight;
        this.vecinoUpLeft = vecinoUpLeft;
        this.vecinoUpRight = vecinoUpRight;
        this.vecinoDownLeft = vecinoDownLeft;
        this.vecinoDownRight = vecinoDownRight;
        this.estado = estado;
    }

    public int cambiar_estado(){
        this.estado = !this.estado;
        return 0;
    }
    
    public boolean sigiente_estado(){
        int i = 0;
        
        if (this.vecinoUp != null) if (this.vecinoUp.estado == true) i++;
        if (this.vecinoDown != null) if (this.vecinoDown.estado == true) i++;
        if (this.vecinoLeft != null) if (this.vecinoLeft.estado == true) i++;
        if (this.vecinoRight != null) if (this.vecinoRight.estado == true) i++;
        if (this.vecinoUpLeft != null) if (this.vecinoUpLeft.estado == true) i++;
        if (this.vecinoUpRight != null) if (this.vecinoUpRight.estado == true) i++;
        if (this.vecinoDownLeft != null) if (this.vecinoDownLeft.estado == true) i++;
        if (this.vecinoDownRight != null) if (this.vecinoDownRight.estado == true) i++;
        
        if(this.estado==true && (i > 3 || i < 2)) this.cambiar_estado();
        else if(this.estado==false && (i == 3))this.cambiar_estado();
        
        return this.estado;
    }
    public celula clone(){
        celula c = new celula(  this.estado,
                                this.vecinoUp ,
                                this.vecinoDown,
                                this.vecinoLeft,
                                this.vecinoRight,
                                this.vecinoUpLeft,
                                this.vecinoUpRight,
                                this.vecinoDownLeft,
                                this.vecinoDownRight
        );
        return c;
    }
}
