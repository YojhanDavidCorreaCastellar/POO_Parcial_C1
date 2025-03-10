import java.sql.Time;
import java.util.Date;

interface Radio {
    String getNombreRadio();
    String getFrecuencia();
    String getTipo();
}

interface Productora {
    String getRfc();
    String getNombreProductora();
    String getTelefonos();
    String getProductores();
}

class Consorcio implements Radio, Productora {
    private String rfc;
    private String nombreProductora;
    private String telefonos;
    private String nombreRadio;
    private String frecuencia;
    private String tipo;
    private Productor[] listaProductores;
    private int contadorProductores;

    public Consorcio(String rfc, String nombreProductora, String telefonos, String nombreRadio, String frecuencia, String tipo) {
        this.rfc = rfc;
        this.nombreProductora = nombreProductora;
        this.telefonos = telefonos;
        this.nombreRadio = nombreRadio;
        this.frecuencia = frecuencia;
        this.tipo = tipo;
        this.listaProductores = new Productor[10];
        this.contadorProductores = 0;
    }

    @Override
    public String getNombreRadio() {
        return nombreRadio;
    }

    @Override
    public String getFrecuencia() {
        return frecuencia;
    }

    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public String getRfc() {
        return rfc;
    }

    @Override
    public String getNombreProductora() {
        return nombreProductora;
    }

    @Override
    public String getTelefonos() {
        return telefonos;
    }

    @Override
    public String getProductores() {
        String total = "";
        for (int i = 0; i < contadorProductores; i++) {
            Productor productor = listaProductores[i];
            total += "- " + productor.nombre + " (" + productor.getTipoProduccion() + ")\n";
        }
        return total;
    }

    public boolean agregarProductor(Productor productor) {
        if (contadorProductores >= listaProductores.length) {
            System.out.println("Error: No se pueden agregar más productores. Capacidad máxima alcanzada.");
            return false;
        }
        listaProductores[contadorProductores++] = productor;
        return true;
    }

    public void setNombreRadio(String nombreRadio) {
        this.nombreRadio = nombreRadio;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public void setNombreProductora(String nombreProductora) {
        this.nombreProductora = nombreProductora;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }
}

//----------------------------------------------------------------
//CLASE PROGRAMA
//----------------------------------------------------------------

class Programa {
    private String nombre;
    private String genero;
    private boolean esResumen;
    private Date fecha;
    private Programa[] programas;
    private int contadorProgramas;

    public Programa (String nombre, String genero, boolean esResumen, int capacidadBase) {
        this.nombre = nombre;
        this.genero = genero;
        this.esResumen = esResumen;
        if (esResumen) {
            this.programas = new Programa[20];
            this.contadorProgramas = 0;                  
        } else {
            this.programas = null;                      
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isEsResumen() {
        return esResumen;
    }
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public void setEsResumen(boolean esResumen) {
        this.esResumen = esResumen;
        if (!esResumen) {
            this.programas = null;
            this.contadorProgramas = 0;
        }
    }

    public void agregarProgramaBase(Programa programa) {
        if (!esResumen || programas == null) {
            System.out.println("Este programa no es un resumen, no puede tener programas base.");
            return;
        }
        if (contadorProgramas >= programas.length) {
            System.out.println("No se pueden agregar más programas base. Capacidad máxima alcanzada.");
            return;
        }
        programas[contadorProgramas++] = programa; 
    }

    public Programa[] getProgramasBase() {
        return programas;
    }
}

//----------------------------------------------------------------
//CLASE PERSONA
//----------------------------------------------------------------


class Persona {
    protected int cedula;
    protected String nombre;
}

//----------------------------------------------------------------
//CLASE CONDUCTOR HEREDA DE PERSONA
//----------------------------------------------------------------


class Conductor extends Persona {
    private Operador[] operadoresPref;
    private int contadorOperadores;

    public Conductor(int cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.operadoresPref = new Operador[10];
        this.contadorOperadores = 0;
    }

    public boolean agregarOperadorPreferido(Operador operador) {
        if (contadorOperadores >= operadoresPref.length) {
            System.out.println("Error: El array está lleno. No se puede agregar más operadores.");
            return false;
        }

        for (int i = 0; i < contadorOperadores; i++) {
            if (operadoresPref[i] != null && operadoresPref[i].getCedula() == operador.getCedula()) {
                System.out.println("Error: El operador con cedula " + operador.getCedula() + " ya está en la lista.");
                return false; // Operador duplicado
            }
        }
        operadoresPref[contadorOperadores++] = operador;
        return true;
    }

    public void mostrarOperadoresPreferidos() {
        System.out.println("Operadores preferidos del conductor " + nombre + ":");
        for (int i = 0; i < contadorOperadores; i++) {
            Operador operador = operadoresPref[i];
            if (operador != null) {
                System.out.println("  - Cedula: " + operador.getCedula() + ", Nombre: " + operador.getNombre());
            }
        }
    }
}

//----------------------------------------------------------------
//CLASE OPERADOR HEREDA DE PERSONA
//----------------------------------------------------------------

class Operador extends Persona {
    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

//----------------------------------------------------------------
// CLASE PRODUCTOR HEREDA DE PERSONA
//----------------------------------------------------------------

class Productor extends Persona {
    private String tipoProduccion;

    public Productor(int cedula, String nombre, String tipoProduccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.tipoProduccion = tipoProduccion;
    }
    public String getTipoProduccion() {
        return tipoProduccion;
    }

    public void setTipoProduccion(String tipoProduccion) {
        this.tipoProduccion = tipoProduccion;
    }

}

//----------------------------------------------------------------
// CLASE EMISION
//----------------------------------------------------------------

class Emision {
    private Date fecha = new Date();
    private Time horaInicio = new Time(0);
    private int duracion = 0;
    private boolean esRepeticion = false;
    private Programa programa;

    public Emision(Date fecha, Time horaInicio, int duracion, boolean esRepeticion, Programa programa) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.duracion = duracion;
        this.esRepeticion = esRepeticion;
        this.programa = programa;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public boolean isEsRepeticion() {
        return esRepeticion;
    }

    public void setEsRepeticion(boolean esRepeticion) {
        this.esRepeticion = esRepeticion;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public void mostrarInformacionEmision() {
        System.out.println("Fecha: " + fecha);
        System.out.println("Hora de inicio: " + horaInicio);
        System.out.println("Duración: " + duracion + " minutos");
        System.out.println("Es repetición: " + (esRepeticion ? "Sí" : "No"));
        System.out.println("Programa asociado: " + (programa != null ? programa.getNombre() : "Ninguno"));
    }

}

//----------------------------------------------------------------
// CLASE ENCUESTA
//----------------------------------------------------------------

class Encuesta {
    private int totalEncuestados;
    private int aprobaciones;
    private int rechazos;
    private int indiferencias;
    private Emision emision;

    public Encuesta(int totalEncuestados, int aprobaciones, int rechazos, int indiferencias, Emision emision) {
        this.totalEncuestados = totalEncuestados;
        this.aprobaciones = aprobaciones;
        this.rechazos = rechazos;
        this.indiferencias = indiferencias;
        this.emision = emision;
    }

    public int getTotalEncuestados() {
        return totalEncuestados;
    }

    public void setTotalEncuestados(int totalEncuestados) {
        this.totalEncuestados = totalEncuestados;
    }

    public int getAprobaciones() {
        return aprobaciones;
    }

    public void setAprobaciones(int aprobaciones) {
        this.aprobaciones = aprobaciones;
    }

    public int getRechazos() {
        return rechazos;
    }

    public void setRechazos(int rechazos) {
        this.rechazos = rechazos;
    }

    public int getIndiferencias() {
        return indiferencias;
    }

    public void setIndiferencias(int indiferencias) {
        this.indiferencias = indiferencias;
    }

    public Emision getEmision() {
        return emision;
    }

    public void setEmision(Emision emision) {
        this.emision = emision;
    }

    public void mostrarResultadosEncuesta() {
        System.out.println("Resultados de la encuesta para la emisión del programa " + emision.getPrograma().getNombre());
        System.out.println("Total encuestados: " + totalEncuestados);
        System.out.println("Aprobaciones: " + aprobaciones);
        System.out.println("Rechazos: " + rechazos);
        System.out.println("Indiferencias: " + indiferencias);
    }
}

//----------------------------------------------------------------
// CLASE MAIN PARA CREAR OBJETOS
//----------------------------------------------------------------G

public class emisoras {
    public static void main(String[] args) {
        Consorcio consorcio = new Consorcio("123456", "Televisa", "1234567890", "Televisa Radio", "100.5 FM", "Musical");

        Productor productor1 = new Productor(8745, "Juan", "Producción de radio");
        Productor productor2 = new Productor(4515, "Luisa", "Producción de televisión");

        consorcio.agregarProductor(productor1);
        consorcio.agregarProductor(productor2);

        System.out.println("----- CONSORCIOS -------");
        System.out.println("Nombre de la radio: " + consorcio.getNombreRadio());
        System.out.println("Frecuencia: " + consorcio.getFrecuencia());
        System.out.println("Tipo: " + consorcio.getTipo());
        System.out.println("RFC: " + consorcio.getRfc());
        System.out.println("Nombre de la productora: " + consorcio.getNombreProductora());
        System.out.println("Teléfonos: " + consorcio.getTelefonos());
        System.out.println("Productores: \n" + consorcio.getProductores());


        Programa programa1 = new Programa("MTV", "Musical", false, 0);
        Programa programa2 = new Programa("ESPN", "Deportivo", false, 0);
        Programa programa3 = new Programa("Noticias Caracol", "Informativo", true, 0);

        programa3.setEsResumen(true);
        programa3.agregarProgramaBase(programa1);
        programa3.agregarProgramaBase(programa2);

        System.out.println("----- PROGRAMAS -------");
        System.out.println("Programa: " + programa1.getNombre() + ", Género: " + programa1.getGenero());
        System.out.println("Programa: " + programa2.getNombre() + ", Género: " + programa2.getGenero());
        
        System.out.println();
        System.out.println("Resumen: " + programa3.getNombre() + ", Género: " + programa3.getGenero());
        System.out.println("Programas base del resumen:");
        for (Programa p : programa3.getProgramasBase()) {
            if (p != null) {
                System.out.println("  - " + p.getNombre());
            }
        }

        System.out.println();
        System.out.println("----- EMISIONES -------");
        Emision emision1 = new Emision(new Date(), new Time(0), 60, false, programa1);
        Emision emision2 = new Emision(new Date(), new Time(0), 120, true, programa2);
        Emision emision3 = new Emision(new Date(), new Time(0), 30, false, programa3);

        emision1.mostrarInformacionEmision();
        System.out.println();

        emision2.mostrarInformacionEmision();
        System.out.println();

        emision3.mostrarInformacionEmision();
        System.out.println();

        System.out.println("----- ENCUESTAS -------");
        Encuesta encuesta1 = new Encuesta(100, 80, 10, 10, emision1);
        Encuesta encuesta2 = new Encuesta(200, 150, 20, 30, emision2);
        Encuesta encuesta3 = new Encuesta(50, 40, 5, 5, emision3);

        encuesta1.mostrarResultadosEncuesta();
        System.out.println();

        encuesta2.mostrarResultadosEncuesta();
        System.out.println();
        
        encuesta3.mostrarResultadosEncuesta();

        System.out.println();
        System.out.println("----- OPERADORES -------");
        Conductor conductor = new Conductor(123456, "Juan");
        Operador operador1 = new Operador();
        operador1.setCedula(123456);
        operador1.setNombre("Pedro");

        Operador operador2 = new Operador();
        operador2.setCedula(654321);
        operador2.setNombre("Pablo");

        conductor.agregarOperadorPreferido(operador1);
        conductor.agregarOperadorPreferido(operador2);
        conductor.mostrarOperadoresPreferidos();
    }
}

