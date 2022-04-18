/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.de.la.vida;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Un tablero de juego de n x m
 * @author Miguel Alberto Bazán Silva
 */
public class tablero {
    ArrayList<ArrayList<celula>> t;
    int n_filas;
    int n_columnas;
    ArrayList<ArrayList<celula>> siguiente_tablero = new ArrayList<>();
    
    public tablero() {
        this.configurar_tablero();
        this.menu();
    }
    public tablero(int aux){
    }
    
    public void menu () {
        Scanner scanner = new Scanner(System.in);
        String aux;
        do{
            this.imprimir_tablero();
            System.out.println("0.-Siguiente...");
            System.out.println("1.- Menú Extendido");

            aux = scanner.next();
        
            switch(aux){
                case "1": 
                    this.menu_extendido();
                    break;
                case "0":
                    this.siguiente_t();
                    break;
                default:
                    System.out.println("Invalid Input\nTry Again");
            }
        }while(!(aux=="1"||aux=="\n"));
        this.menu();
    }
    public void menu_extendido () {
        Scanner scanner = new Scanner(System.in);
        int aux;
        do{
            System.out.println("\tMenú Extendido");
            System.out.println("1.- Nueva Configuración");
            System.out.println("2.- Salir");
            System.out.println("3.- Salto de n Iteraciones");
            System.out.println("4.- Siguiente Estado del Tablero");

            aux = scanner.nextInt();
        
            switch(aux){
                case 1: 
                    this.configurar_tablero();
                    break;
                case 2:
                    System.out.println("\nGracias por usar el programa\n");
                    System.exit(0);
                    break;
                case 4:
                    this.siguiente_t();
                    break;
                case 3:
                    System.out.print("\nn iteraciones: ");
                    int n = scanner.nextInt();
                    this.iterar(n);
                    break;
                default:
                    System.out.println("Invalid Input\nTry Again");
            }
        }while(!(aux==1||aux==2||aux==3));
    }
    
    public void configurar_tablero (){
        Scanner scanner = new Scanner(System.in);
        t = new ArrayList<>();
        
        System.out.println("\tConfiguración Inicial del Tablero");
        
        System.out.println("Ingrese las dimensiones del tablero (Filas x Columnas)\n");
        System.out.print("n: ");
        this.n_filas = scanner.nextInt();
        System.out.print("m: ");
        this.n_columnas = scanner.nextInt();
        
        System.out.println("\n\tIngresar los valores\n");
        
        
        for (int i = 0; i < this.n_filas; i++){
            System.out.println("\nIngresar la fila #"+(i+1));
            String f = scanner.next();
            System.out.println();
            ArrayList<celula> fila = new ArrayList<celula>();
            boolean err=false;
            for (int j = 0; j < this.n_columnas; j++){
                celula c = null;
                switch(f.charAt(j)){
                    case '#':
                        c = this.crear_celula(i, j, true, fila);
                        fila.add(c);
                        break;
                    case '~':
                        c = this.crear_celula(i, j, false, fila);
                        fila.add(c);
                        break;
                    default:
                        System.out.println("La Fila tiene Caracteres Invalidos\nIntente de Nuevo");
                        err=true;
                        j=this.n_columnas;
                        break;
                }
            }
            if(err==false) this.t.add(fila);
            else i--;
        }
    }
    public celula crear_celula(int fila, int columna, boolean estado, ArrayList<celula> L_fila){
        celula vecinoUp=null;
        celula vecinoDown=null;
        celula vecinoLeft=null;
        celula vecinoRight=null;
        celula vecinoUpLeft=null;
        celula vecinoUpRight=null;
        celula vecinoDownLeft=null;
        celula vecinoDownRight=null;
        
        celula c = new celula(estado,vecinoUp,vecinoDown,vecinoLeft,vecinoRight,vecinoUpLeft,vecinoUpRight,vecinoDownLeft,vecinoDownRight);
        
        if(columna-1>=0){
            c.vecinoLeft = this.obtener_celula(fila, columna-1, L_fila);
            this.obtener_celula(fila, columna-1, L_fila).vecinoRight = c;
        }
        if(fila-1>=0 && columna-1>=0){
            c.vecinoUpLeft = this.obtener_celula(fila-1, columna-1);
            this.obtener_celula(fila-1, columna-1).vecinoDownRight = c;
        }
        if(fila-1>=0){
            c.vecinoUp = this.obtener_celula(fila-1, columna);
            this.obtener_celula(fila-1, columna).vecinoDown = c;
        }
        if(fila-1>=0 && columna+1<this.n_columnas){
            c.vecinoUpRight = this.obtener_celula(fila-1, columna+1);
            this.obtener_celula(fila-1, columna+1).vecinoDownLeft = c;
        }
        return c;
    }
    public celula obtener_celula(int fila, int columna){
        celula c = t.get(fila).get(columna);
        return c;
    }
    public celula obtener_celula(int fila, int columna, ArrayList<celula> L_fila){
        celula c = L_fila.get(columna);
        return c;
    }
    
    public void siguiente_t() {
        
        this.siguiente_tablero = (ArrayList<ArrayList<celula>>) this.clone();
        
        for (int i = 0; i < this.n_filas; i++){
            for (int j = 0; j < this.n_columnas; j++){
                this.siguiente_tablero.get(i).get(j).sigiente_estado();
            }
        }
        this.actualizar_estados();
    }
    public void actualizar_estados(){
        for (int i = 0; i < this.n_filas; i++){
            for (int j = 0; j < this.n_columnas; j++){
                this.t.get(i).get(j).estado = this.siguiente_tablero.get(i).get(j).estado;
            }
        }
    }
    
    public void iterar(int n){
        for (int i = 0; i<n; i++){
            this.siguiente_t();
        }
    }
    
    public void imprimir_tablero(){
        System.out.println();
        for (int i = 0; i < this.n_filas; i++){
            for (int j = 0; j < this.n_columnas; j++){
                boolean aux = this.t.get(i).get(j).estado;
                if(aux == true) System.out.print('#');
                else System.out.print('~');
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public ArrayList<ArrayList<celula>> clone() {
        ArrayList<ArrayList<celula>> nuevo = new ArrayList<>();
        for (int i = 0; i < this.n_filas; i++){
            ArrayList<celula> nueva_fila = new ArrayList<>();
            for (int j = 0; j< this.n_columnas; j++){
                nueva_fila.add((celula) this.t.get(i).get(j).clone());
            }
            nuevo.add(nueva_fila);
        }
        return nuevo;
    }
}
