
public abstract class Ingrediente {
    protected float precio;

    Ingrediente(float prc) { precio = prc;}

    public String toString() {
        return this.getClass().getSimpleName();
    }

    public float getPrecio() {return precio;}
}
class Ingrediente1 extends Ingrediente {
    Ingrediente1() {
        super(3);
    }
}
class Ingrediente2 extends Ingrediente {
    Ingrediente2() {
        super(1);
    }
}
class Ingrediente3 extends Ingrediente {
    Ingrediente3() {
        super(2);
    }
}
class Ingrediente4 extends Ingrediente {
    Ingrediente4() {
        super(1.3f);
    }
}
class Ingrediente5 extends Ingrediente {
    Ingrediente5() {
        super(1.8f);
    }
}
