import java.util.*;

class Articulo {
    private String nombre;
    private double costo;
    private double precioVenta;
    
    public Articulo(String nombre, double costo, double markup) {
        this.nombre = nombre;
        this.costo = costo;
        this.precioVenta = costo * (1 + markup / 100);
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public double getCosto() {
        return costo;
    }
    
    public double getPrecioVenta() {
        return precioVenta;
    }
}

class Rubro {
    private String nombre;
    private double markup;
    private List<Articulo> articulos;
    
    public Rubro(String nombre, double markup) {
        this.nombre = nombre;
        this.markup = markup;
        this.articulos = new ArrayList<Articulo>();
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public double getMarkup() {
        return markup;
    }
    
    public void setMarkup(double markup) {
        this.markup = markup;
        for (Articulo articulo : articulos) {
            articulo = new Articulo(articulo.getNombre(), articulo.getCosto(), markup);
        }
    }
    
    public void agregarArticulo(String nombre, double costo) {
        Articulo articulo = new Articulo(nombre, costo, markup);
        articulos.add(articulo);
    }
    
    public void imprimirArticulos() {
        System.out.println(nombre + ":");
        for (Articulo articulo : articulos) {
            System.out.println("- " + articulo.getNombre() + " - Precio de venta: " + articulo.getPrecioVenta());
        }
    }
}

class Administrador {
    private List<Rubro> rubros;
    
    public Administrador() {
        this.rubros = new ArrayList<Rubro>();
    }
    
    public void agregarRubro(String nombre, double markup) {
        Rubro rubro = new Rubro(nombre, markup);
        rubros.add(rubro);
    }
    
    public void modificarMarkup(String nombreRubro, double markup) {
        Rubro rubro = buscarRubro(nombreRubro);
        if (rubro != null) {
            rubro.setMarkup(markup);
        }
    }
    
    public void agregarArticulo(String nombreRubro, String nombreArticulo, double costo) {
        Rubro rubro = buscarRubro(nombreRubro);
        if (rubro != null) {
            rubro.agregarArticulo(nombreArticulo, costo);
        }
    }
    
    public void imprimirArticulosPorRubro() {
        for (Rubro rubro : rubros) {
            rubro.imprimirArticulos();
        }
    }
    
    private Rubro buscarRubro(String nombreRubro) {
        for (Rubro rubro : rubros) {
            if (rubro.getNombre().equals(nombreRubro)) {
                return rubro;
            }
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        Administrador admin = new Administrador();
        
        admin.agregarRubro("Electrónica", 30);
        admin.agregarArticulo("Electrónica", "Smartphone", 500);
        admin.agregarArticulo("Electrónica", "Televisor", 1000);
        admin.agregarArticulo("Electrónica", "Notebook", 800);
        
        admin.imprimirArticulosPorRubro();
        
    }
}