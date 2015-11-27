package paqueteREST;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/TFG")
public class Servicio {
		
	//Instanciamos la clase que contiene los métodos para 
	//relacionar nuestra app con la base de datos	
	private MetodosBD bd = new MetodosBD();
	
	/**
	 * Método tipo GET que produce un JSON con todos los estudiantes de la base de datos
	 * @return una lista de estudiantes que los contendrá a todos
	 * @throws Exception 
	 */
	@GET
	@Path("/all")
	@Produces ({"application/json"})
	public List<EstudianteTFG> getAllEstudiantes() throws Exception{
		return bd.getAll();
	}
	
	/**
	 * Método tipo GET que produce un texto con los datos del 
	 * estudiante cuyos apellidos juntos se han indicado en la url
	 * @param apellidos los apellidos juntos que se han indicado en el Path
	 * @return un String con los datos del estudiante deseado
	 * @throws Exception
	 */
	@GET
	@Path("/estudiante/{apellidos}")
	@Produces (MediaType.TEXT_PLAIN)
	public String Estudiante(@PathParam("apellidos")String apellidos) throws Exception{
		return bd.getEstudiante(apellidos).toString();
	}
	
	/**
	 * Método tipo PUT que consume un JSON que contendrá los datos que se desean 
	 * modificar (tema, estado, fecha de presentación y calificación) 
	 * y modificará dichos datos del estudiante
	 * @param apellidos los apellidos juntos que se han indicado en el Path
	 * @param estudiante JSON con los datos a modificar
	 * @throws Exception
	 */
	@PUT
	@Path("/update/{apellidos}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void Update(@PathParam("apellidos") String apellidos, 
			EstudianteTFG estudiante) throws Exception{
		bd.updateEstudiante(apellidos,estudiante);
	}
	
	/**
	 * Método tipo DELETE que borrará el estudiante que se le indique en la url
	 * @param apellidos los apellidos juntos que se han indicado en el Path
	 * @throws Exception
	 */
	@DELETE
	@Path("/delete/{apellidos}")
	public void Delete(@PathParam("apellidos")String apellidos) throws Exception{
		bd.removeEstudiante(apellidos);
	}
	
	/**
	 * Método tipo POST que añadirá un estudiante,
	 * consume un JSON con los datos de dicho estudiante
	 * @param estudiante JSON con los datos del estudiante que se desea añadir
	 * @throws Exception
	 */
	@POST
	@Path("/addEstudiante/")
	@Consumes(MediaType.APPLICATION_JSON)
	public void AddEstudiante(EstudianteTFG estudiante) throws Exception{
		bd.addEstudiante(estudiante);
	}	
	
	/**
	 * Método tipo POST que añade un estudiante pasandole los datos
	 * mediante un formulario, consume el formulario en cuestión
	 * @param nombre nombre del estudiante
	 * @param apellido1 primer apellido del estudiante
	 * @param apellido2 segundo apellido del estudiante
	 * @param tema tema del trabajo de fin de grado del estudiante
	 * @param tutor1 director del TFG
	 * @param tutor2 co-director del TFG
	 * @param estadoString estado del trabajo, 
	 * puede estar en desarrollo o presentado
	 * @param fecha fecha de presentación del TFG
	 * @param calificacion calificación obtenida en el TFG
	 * @throws Exception
	 */
	@POST
	@Path("/addEstudianteForm/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)	
	public void AddEstudianteForm(@FormParam("nombre") String nombre,
			@FormParam("apellido1") String apellido1,
			@FormParam("apellido2") String apellido2, @FormParam("tema") String tema,
			@FormParam("tutor1") String tutor1,	@FormParam("tutor2") String tutor2, 
			@FormParam("estado") String estadoString, 
			@FormParam("fechaPresentacion") String fecha, 
			@FormParam("calificacion") float calificacion) throws Exception{
		 SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		 Date fechaDate = null;
	        try {
	            fechaDate = formato.parse(fecha);
	        } 
	        catch (ParseException ex) 
	        {
	            System.out.println(ex);
	        }
	    EstudianteTFG estudiante=new EstudianteTFG();
	    estudiante.setNombre(nombre);
	    estudiante.setApellido1(apellido1);
	    estudiante.setApellido2(apellido2);
	    estudiante.setTema(tema);
	    estudiante.setTutor1(tutor1);
	    estudiante.setTutor2(tutor2);
	    estudiante.setEstado(EstudianteTFG.Estado.valueOf(estadoString));
	    estudiante.setFechaPresentacion(fechaDate);
	    estudiante.setCalificacion(calificacion);
		
		bd.addEstudiante(estudiante);
	}
}
