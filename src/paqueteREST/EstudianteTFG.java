package paqueteREST;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EstudianteTFG {
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String tema;
	private String tutor1, tutor2;
	enum Estado{
		ENDESARROLLO, PRESENTADO;
	}
	private Estado estado;
	private Date fechaPresentacion;
	private float calificacion;
	
	/**
	 * Constructor predeterminado
	 */
	public EstudianteTFG(){}
	
	/**
	 * M�todo para consultar el nombre del alumno
	 * @return un String con el nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * M�todo para definir el nombre del alumno
	 * @param nombreAlumno un String con el nombre del alumno
	 */
	public void setNombre(String nombreAlumno) {
		this.nombre = nombreAlumno;
	}
	
	/**
	 * M�todo para consultar el tema del TFG que va a relizar el alumno
	 * @return un String con el tema
	 */
	public String getTema() {
		return tema;
	}
	
	/**
	 * M�todo para definir el tema del TFG que va a realizar el alumno
	 * @param temaProyecto un String con el tema
	 */
	public void setTema(String temaProyecto) {
		this.tema = temaProyecto;
	}

	/**
	 * M�todo para consultar el director del TFG del alumno
	 * @return el nombre completo del director del TFG
	 */
	public String getTutor1() {
		return tutor1;
	}
	
	/**
	 * M�todo para definir el director del TFG del alumno
	 * @param tutor1 nombre completo del director del TFG
	 */
	public void setTutor1(String tutor1) {
		this.tutor1 = tutor1;
	}
	
	/**
	 * M�todo para consultar el co-director del TFG del alumno
	 * @return nombre completo del co-director del TFG
	 */
	public String getTutor2() {
		return tutor2;
	}
	
	/**
	 * M�todo para definir el co-director del TFG del alumno
	 * @param tutor2 nombre completo del co-director del TFG
	 */
	public void setTutor2(String tutor2) {
		this.tutor2 = tutor2;
	}
	
	/**
	 * M�todo para consultar la fecha de presentaci�n del TFG, 
	 * s�lo si el trabajo ha sido presentado
	 * @return fecha de presentaci�n del TFG
	 */
	public Date getFechaPresentacion() {
		assert getEstado()==Estado.PRESENTADO;
		return fechaPresentacion;
	}
	
	/**
	 * M�todo para definir la fecha de presentaci�n del TFG,
	 * s�lo si el trabajo ha sido presentado
	 * @param fechaPresentacion Fecha de presentaci�n del TFG
	 */
	public void setFechaPresentacion(Date fechaPresentacion) {
		assert getEstado()==Estado.PRESENTADO;
		this.fechaPresentacion = fechaPresentacion;
	}
	
	/**
	 * M�todo para consultar la calificaci�n obtenida en el TFG,
	 * s�lo si el trabajo ha sido presentado
	 * @return nota del TFG
	 */
	public float getCalificacion() {
		assert getEstado()==Estado.PRESENTADO;
		return calificacion;
	}
	
	/**
	 * M�todo para definir la calificaci�n del TFG,
	 * s�lo si �ste ha sido presentadp
	 * @param calificacion nota del TFG 
	 */
	public void setCalificacion(float calificacion) {
		assert getEstado()==Estado.PRESENTADO;
		this.calificacion = calificacion;
	}
	
	/**
	 * M�todo para consultar el estado del TFG que
	 * puede estar presentado o en desarrollo
	 * @return estado del TFG
	 */
	public Estado getEstado() {
		return estado;
	}
	
	/**
	 * M�todo para definir el estado del TFG que
	 * puede estar presentado o en desarrollo
	 * @param estado variable de tipo Enum(Estado)
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	/**
	 * M�todo para consultar el primer apellido del alumno
	 * @return String con el primer apellido del alumno
	 */
	public String getApellido1() {
		return apellido1;
	}
	
	/**
	 * M�todo para definir el primer apellido del alumno
	 * @param apellido1 primer apellido del alumno
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	
	/**
	 * M�todo para consultar el segundo apellido del alumno
	 * @return String con el segundo apellido del alumno
	 */
	public String getApellido2() {
		return apellido2;
	}
	
	/**
	 * M�todo para definir el segundo apellido del alumno
	 * @param apellido2 segundo apellido del alumno
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
	/**
	 * M�todo que devuelve una cadena con los datos del alumno en formato legible
	 */
	@Override
	public String toString() {
		String cad= "Nombre del estudiante: " + nombre +' '+apellido1+' '+apellido2+ "\n"
				+ "Tema del TFG: " + tema + "\n"
				+ "Director: " + tutor1+"\n";
				if(tutor2!=null) {
					cad+="Co-director: "+tutor2;
				}
				cad +="estado=" + estado
				+ ", fecha de presentacion=" + fechaPresentacion 
				+ ", calificacion=" + calificacion + "]";
				return cad;
	}
	
}


