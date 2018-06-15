// Crear una tienda de Pizza donde los clientes pueden elegir entre 4 ingredientes
// y 3 tamaños (personal, mediana y grande). Cada ingrediente tiene diferente precio.
// El cliente puede seleccionar máximo 4 igredientes.
// El resultado final debe ser el PRECIO TOTAL (incluido IVA).
import java.util.ArrayList;
import java.text.DecimalFormat;

enum Tamanos {
    Grande(4), Mediana(3), Personal(2);
    private float value;
    private Tamanos(float value){this.value = value;}
    public float getValue () {return value;}
}

public class Pizza {
    private ArrayList<Ingrediente> ingredientes;
    private Tamanos tamano;
    private static final int IVA = 12;//%

    Pizza(ArrayList<Ingrediente> ing, Tamanos t){
        ingredientes = ing;
        tamano = t;
    }
    public String calcularPrecioFinal() {
        float precioIng = 0;
        for(Ingrediente i: ingredientes) {
            precioIng += i.getPrecio();
        }
        float precioFinal = (precioIng + tamano.getValue()) * (IVA + 100) / 100;
        String format = new DecimalFormat("#.00").format(precioFinal);
        return format;
    }
    public String toString() {
        String totalIngredientes = "";
        for(Ingrediente s: ingredientes) {
            totalIngredientes += s.toString() + " ";
        }
        return "Ingredientes: " + totalIngredientes +
            "\nTamano: " + tamano.toString() +
            "\nPrecio final " + calcularPrecioFinal();
    }
    public Tamanos getTamaño() {return tamano;}
    public ArrayList<Ingrediente> getIngredientes(){return ingredientes;}
}
