/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author LN710Q
 */
public class Filtro {
    private int id;
    private String nombre;
    private String propietario;
    private int edad;    
    private String numInscripcion;
    private boolean existencia;
    
    public Filtro(){
    }
    
    public Filtro(int id,String nombre, int edad, String propietario, boolean existencia)
    {
        this.id=id;
        this.edad=edad;
        this.propietario=propietario;
        this.existencia=existencia;
        this.nombre=nombre;
    }
    
    public Filtro(String codigo, int edad, String propietario, boolean existencia){
        this.nombre=codigo;
        this.edad=edad;
        this.propietario=propietario;
        this.existencia=existencia;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public boolean getExistencia() {
        return existencia;
    }

    public void setExistencia(boolean existencia) {
        this.existencia = existencia;
    }

    
    
    
}
