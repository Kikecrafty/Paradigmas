
public class Alumno implements Comparable<Alumno> {
    private int noCuenta;
    private String nombre;
    private String apellido;
    private double altura;
    private SituacionAcademica situacionAcademica; 

    public Alumno(int noCuenta, String nombre, String apellido, double altura, SituacionAcademica situacionAcademica) {
        this.noCuenta = noCuenta;
        this.nombre = nombre;
        this.apellido = apellido;
        this.altura = altura;
        this.situacionAcademica = situacionAcademica;
    }

    public int getNoCuenta() {
        return noCuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public double getAltura() {
        return altura;
    }

    public SituacionAcademica getSituacionAcademica() {
        return situacionAcademica;
    }
    
    @Override
    public int compareTo(Alumno otroAlumno) {
        return otroAlumno.noCuenta - this.noCuenta ;
    }
    
    @Override
    public String toString() {
        return "No. Cuenta: " + noCuenta + ", Nombre: " + nombre + ", Apellido: " + apellido + ", Altura: " + altura + ", Situación Académica: " + situacionAcademica;
    }

}
