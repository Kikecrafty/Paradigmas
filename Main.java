import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AlumnoDao alumnoDao = new AlumnoDaoImp();
        int opcion;
        Scanner consola = new Scanner(System.in);

        do {
            System.out.println("-------------------");
            System.out.println("Operaciones");
            System.out.println("1.-Agregar  Alumno.");
            System.out.println("2.-Buscar  Alumno.");
            System.out.println("3.-Actualizar Alumno.");
            System.out.println("4.-Borrar Alumno.");
            System.out.println("5.-Mostrar Alumnos");
            System.out.println("6.-Salir.");
            System.out.println("-------------------");
            System.out.println("Elige tu opcion:");
            opcion = consola.nextInt();

            switch (opcion) {
                case 1:
                    ingresarUsuario(alumnoDao, consola);
                    break;
                case 2:
                    buscarUsuario(alumnoDao, consola);
                    break;
                case 3:
                    actualizarUsuario(alumnoDao, consola);
                    break;
                case 4:
                    borrarAlumno(alumnoDao, consola);
                    break;
                case 5:
                    System.out.println("Saliendo....");
                    break;
                case 6:
                mostraAlumno(alumnoDao, consola);
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        } while (opcion != 6);
    }

    private static void ingresarUsuario(AlumnoDao alumnoDao, Scanner consola) {
        int noCuenta;
        String nombre, apellido, situacionAcademica;
        double altura;

        System.out.println("Nuevo Alumno:");
        System.out.println("Ingresa el no.Cuenta:");
        noCuenta = consola.nextInt();
        System.out.println("Ingresa el nombre: ");
        nombre = consola.nextLine();
        System.out.println("Ingresa el apellido:");
        apellido = consola.nextLine();
        System.out.println("Ingresa la altura:");
        altura = consola.nextDouble();
        System.out.println("Ingresa el estado Academico:");
        System.out.println("REGULAR , IRREGULAR O BAJA");
        situacionAcademica = consola.nextLine();
        SituacionAcademica situacionAcademica1 = SituacionAcademica.valueOf(situacionAcademica.toUpperCase());

        Alumno nuevoAlumno = new Alumno(noCuenta, nombre, apellido, altura, situacionAcademica1);
        try {
            int resultadoInsercion = alumnoDao.insert(nuevoAlumno);
            System.out.println("Resultado de la inserción: " +
                    resultadoInsercion);
        } catch (Exception e) {
            System.out.println("Error al insertar el alumno: " + e.getMessage());
        }
    }

    private static void buscarUsuario(AlumnoDao alumnoDao, Scanner consola) {
        int noCuenta;
        System.out.println("Alumno a buscar");
        System.out.println("Introduce el no.Cuenta:");
        noCuenta = consola.nextInt();
        System.out.println();

        try {
            Alumno alumnoBuscado = alumnoDao.get(noCuenta);
            if (alumnoBuscado != null) {
                System.out.println("Alumno encontrado:\n" + alumnoBuscado);
            } else {
                System.out.println("No se encontró ningún alumno con el No.Cuenta proporcionado.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar al alumno " + e.getMessage());
        }
    }

    private static void actualizarUsuario(AlumnoDao alumnoDao, Scanner consola) {
        int noCuentaActualizacion;
        System.out.print("Ingrese el No.Cuenta del alumno a actualizar: ");
        noCuentaActualizacion = consola.nextInt();
        consola.nextLine();

        try {
            Alumno alumnoaActualizar = alumnoDao.get(noCuentaActualizacion);
            if (alumnoaActualizar != null) {
                System.out.println("Alumno " + alumnoaActualizar);

                String nombre, apellido, situacionAcademica;
                double altura;

                System.out.println("--Nuevos datos Alumno:--");
                System.out.println("Ingresa el nombre: ");
                nombre = consola.nextLine();
                System.out.println("Ingresa el apellido:");
                apellido = consola.nextLine();
                System.out.println("Ingresa la altura:");
                altura = consola.nextDouble();
                System.out.println("Ingresa el estado Academico:");
                System.out.println("REGULAR , IRREGULAR O BAJA");
                situacionAcademica = consola.nextLine();
                SituacionAcademica situacionAcademica1 = SituacionAcademica.valueOf(situacionAcademica.toUpperCase());
                alumnoaActualizar.setNombre(nombre);
                alumnoaActualizar.setApellido(apellido);
                alumnoaActualizar.setAltura(altura);
                alumnoaActualizar.setSituacionAcademica(situacionAcademica1);
                int resultadoActualizacion = alumnoDao.update(alumnoaActualizar);
                System.out.println("Resultado de la actualización: " + resultadoActualizacion);
                System.out.println("Alumno después de la actualización: " + alumnoaActualizar);
            } else {
                System.out.println("No se encontro el alumno");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar " + e.getMessage());
        }
    }

    private static void borrarAlumno(AlumnoDao alumnoDao, Scanner consola) {
        int noCuenta;
        System.out.println("Eliminar alumno");
        System.out.println("Numero de cuenta del alumno a eliminar:");
        noCuenta = consola.nextInt();
        try {
            Alumno alumnoAEliminar = alumnoDao.get(noCuenta);
            String opcion;
            if (alumnoAEliminar != null) {
                System.out.println("Estas seguro de esta accion: Si/No");
                opcion = consola.nextLine();
                if (opcion.equalsIgnoreCase("Si ") || opcion.equalsIgnoreCase("si")) {
                    int eliminar = alumnoDao.delete(alumnoAEliminar);
                    System.out.println("Alumno eliminado con exito " + eliminar);
                } else {
                    System.out.println("No se encontro el alumno. ");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar al alumno " + e.getMessage());
        }
    }

    private static void mostraAlumno(AlumnoDao alumnoDao, Scanner consola) {
        try {
            List<Alumno> alumnos = alumnoDao.getAll();
            if (!alumnos.isEmpty()) {
                System.out.println("Lista de todos los alumnos:");
                for (Alumno a : alumnos) {
                    System.out.println(a);
                }
            } else {
                System.out.println("No hay alumnos registrados.");
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de alumnos: " +
                    e.getMessage());
        }
    }
}