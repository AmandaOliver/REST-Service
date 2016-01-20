package paqueteREST;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import paqueteREST.EstudianteTFG.Estado;

public class MetodosBD {
	  private Connection connect = null;
	  private Statement statement = null;
	  private ResultSet resultSet = null;
	  
/**
 * método para simplificar el cierre del resulSet
 */
 private void close() {
	    try {
	      if (resultSet != null) {
	        resultSet.close();
	      }

	      if (statement != null) {
	        statement.close();
	      }

	      if (connect != null) {
	        connect.close();
	      }
	    } catch (Exception e) {

	    }
}
/**Método que devuelve una lista de todos los estudiantes que están realizando 
 * o han realizado un TFG con un determinado profesor
 *  
 *  @return lista de tipo EstudianteTFG que contiene todos 
 *  los estudiantes de la Base de Datos
 */
public List<EstudianteTFG> getAll() throws Exception{
	try{
		 List<EstudianteTFG> listaEstudiantes = new ArrayList<EstudianteTFG>();
		
		 //Conexión con la base de datos
		 Class.forName("com.mysql.jdbc.Driver");
		 connect = DriverManager.getConnection("jdbc:mysql://localhost/Estudiantes?"
				 + "user=sqluser&password=sqluserpw");
		 statement = connect.createStatement();
		 
		 //Consulta que se desea hacer
		 resultSet = statement.executeQuery("select * from Estudiantes.estudiantes");
		 
		 //Bucle que procesa cada estudiante de la base de datos
		 while (resultSet.next()) {
			 
			  //Se crea un objeto EstudianteTFG para almacenar cada estudiante
			  EstudianteTFG estudiante=new EstudianteTFG();
			  
			  //Se obtienen los datos de cada estudiante y se introducen en nuestro objeto
			  String nombre = resultSet.getString("nombre");
			  estudiante.setNombre(nombre);
			  String apellido1 = resultSet.getString("apellido1");
			  estudiante.setApellido1(apellido1);
			  String apellido2 = resultSet.getString("apellido2");
			  estudiante.setApellido2(apellido2);
		      String tema = resultSet.getString("tema");
		      estudiante.setTema(tema);
		      String tutor1= resultSet.getString("tutor1");
		      estudiante.setTutor1(tutor1);
		      String tutor2= resultSet.getString("tutor2");
		      estudiante.setTutor2(tutor2);
		      String estado= resultSet.getString("estado");
		      estudiante.setEstado(EstudianteTFG.Estado.valueOf(estado));
		      if(EstudianteTFG.Estado.valueOf(estado)==EstudianteTFG.Estado.PRESENTADO) {
		    	  Date fechaPresentacion = resultSet.getDate("fechaPresentacion");
		    	  estudiante.setFechaPresentacion(fechaPresentacion);
		    	  float calificacion = resultSet.getFloat("calificacion");
		    	  estudiante.setCalificacion(calificacion);
		      }
		      
		      //Se añade el estudiante a la lista
			  listaEstudiantes.add(estudiante);
			  
		}
		 return listaEstudiantes;
	}catch (Exception e) {
	      throw e;
	} finally {
	  close();	  
	}
	
}

/**Método que obtiene los datos de un alumno concreto filtrando por sus apellidos,
 *  si el alumno buscado no se encuentra se devuelve null
 *
 *@param apellidos array de strings que contiene los apellidos del alumno juntos
 *@return devuelve EstudianteTFG cuyos apellidos coinciden con los pasados como parámetros
 * @throws Exception 
 */
public EstudianteTFG getEstudiante(String apellidos) throws Exception{
	try{
		  //Conexión con la base de datos
		  Class.forName("com.mysql.jdbc.Driver");
		  connect = DriverManager.getConnection("jdbc:mysql://localhost/Estudiantes?"
		              + "user=sqluser&password=sqluserpw");
		  statement = connect.createStatement();
		 
		  //Consulta que se desea realizar
		  resultSet = statement
		          .executeQuery("select * "
		          		+ "from Estudiantes.estudiantes "
		          		+ "where concat(apellido1, apellido2) = '"+ apellidos+"'");
		  resultSet.next();
		  
		  //Se crea el objeto EstudianteTFG donde vamos a 
		  //guardar los datos de nuestro estudiante
		  EstudianteTFG estudiante=new EstudianteTFG();
		  
		  //Se obtienen los datos del estudiante y se instroducen en el objeto
		  String nombre = resultSet.getString("nombre");
		  estudiante.setNombre(nombre);
		  String apellido1 = resultSet.getString("apellido1");
		  estudiante.setApellido1(apellido1);
		  String apellido2 = resultSet.getString("apellido2");
		  estudiante.setApellido2(apellido2);
	      String tema = resultSet.getString("tema");
	      estudiante.setTema(tema);
	      String tutor1= resultSet.getString("tutor1");
	      estudiante.setTutor1(tutor1);
	      String tutor2= resultSet.getString("tutor2");
	      estudiante.setTutor2(tutor2);
	      String estado= resultSet.getString("estado");
	      estudiante.setEstado(EstudianteTFG.Estado.valueOf(estado));
	      if(EstudianteTFG.Estado.valueOf(estado)==EstudianteTFG.Estado.PRESENTADO) {
	    	  Date fechaPresentacion = resultSet.getDate("fechaPresentacion");
	    	  estudiante.setFechaPresentacion(fechaPresentacion);
	    	  float calificacion = resultSet.getFloat("calificacion");
	    	  estudiante.setCalificacion(calificacion);
	      }
	      return estudiante;
	}catch (Exception e) {
	      throw e;
	} finally {
	  close();	  
	}
	
}

/** método que obtiene un estudiante de la lista y modifica sus atributos
 * 
 * @param apellidos String que contiene los apellidos del estudiante que queremos modificar
 * @param estudiante tipo de objeto EstudianteTFG con los datos nuevos.
 * @throws Exception 
 */
public void updateEstudiante(String apellidos, EstudianteTFG estudiante) throws Exception{
	try{
		
	 //Conexión con la base de datos
	 Class.forName("com.mysql.jdbc.Driver");
	 connect = DriverManager.getConnection("jdbc:mysql://localhost/Estudiantes?"
	              + "user=sqluser&password=sqluserpw");
	 statement = connect.createStatement();
	
	 //Consulta (update) que se desea realizar
	 boolean hay_algo=false;
	 String update= "update Estudiantes.estudiantes set ";
    
	 if (estudiante.getTema()!=""){
		 update+="tema='"+estudiante.getTema()+"'";
		 hay_algo=true;
	 }
	 if(estudiante.getEstado()!=EstudianteTFG.Estado.NULL && hay_algo){
		 update+=", estado='"+estudiante.getEstado()+"'";
	 }
	 else if (estudiante.getEstado()!=EstudianteTFG.Estado.NULL && !hay_algo){
		 update+="estado='"+estudiante.getEstado()+"'";
		 hay_algo=true;
	 }
	
	 if(estudiante.getFechaPresentacion()!=null && hay_algo){
		 update+=", fechaPresentacion='"+new java.sql.Date(estudiante.getFechaPresentacion().getTime())+"'";
	 }
	 else if(estudiante.getFechaPresentacion()!=null && !hay_algo){
		 update+="fechaPresentacion='"+new java.sql.Date(estudiante.getFechaPresentacion().getTime())+"'";
		 hay_algo=true;
	 }
	 
	 if(estudiante.getCalificacion()!=0.0f && hay_algo){		
		 update+=", calificacion='"+estudiante.getCalificacion()+"'";
	 }
	 else if(estudiante.getCalificacion()!=0.0f && !hay_algo){
		 update+="calificacion='"+estudiante.getCalificacion()+"'";
		 hay_algo=true;
	 }
	 
	 update+=" where concat(apellido1, apellido2) = '"
		+ apellidos+"';";
	 
	
	 //Ejecución del update
	 statement.execute(update);
	 
	}catch (Exception e) {
	      throw e;
	} finally {
	  close();	  
	}
	
}

/**
 * método que elimina un estudiante de la lista
 * @param apellidos String que contiene los apellidos del estudiante que deseamos eliminar
 * @throws Exception 
 */
public void removeEstudiante(String apellidos) throws Exception{
	try{
		 
		 //Conexión con la base de datos
		 Class.forName("com.mysql.jdbc.Driver");
		 connect = DriverManager.getConnection("jdbc:mysql://localhost/Estudiantes?"
		              + "user=sqluser&password=sqluserpw");
		 statement = connect.createStatement();
		 
		 //Consulta (delete) que se desea realizar
		 String delete= "delete from Estudiantes.estudiantes "
			+ "where concat(apellido1, apellido2) = '"+ apellidos+"'";
		 
		 //Ejecución del delete
		 statement.execute(delete);
		 
		}catch (Exception e) {
		      throw e;
		} finally {
		  close();	  
		}
}

/**
 * método que añade un estudiante a la lista
 * @param estudiante objeto de tipo EstudianteTFG que queremos añadir
 * @throws Exception 
 */
public void addEstudiante(EstudianteTFG estudiante) throws Exception{
	try{
		
		 //Conexión con la base de datos
		 Class.forName("com.mysql.jdbc.Driver");
		 connect = DriverManager.getConnection("jdbc:mysql://localhost/Estudiantes?"
		              + "user=sqluser&password=sqluserpw");
		 statement = connect.createStatement();

		 //Consulta(insert) que se desea realizar
		 String insert=" insert into estudiantes values(default, '"+estudiante.getNombre()+"',"
		 		+ " '"+estudiante.getApellido1()+"', '"+estudiante.getApellido2()+"', '"
				+ estudiante.getTema()+"', "
		 		+ "'"+estudiante.getTutor1()+"', '"+estudiante.getTutor2()+"', '"
				+ estudiante.getEstado()+"', ";
		 if(estudiante.getFechaPresentacion()!=null){
			 insert+="'"+new java.sql.Date(estudiante.getFechaPresentacion().getTime())+"', ";
		 }
		 else insert+="null,";
		 		
		 		insert+= estudiante.getCalificacion()+")";
		 
		 //Ejecución del delete
		 statement.execute(insert);
		 
		}catch (Exception e) {
		      throw e;
		} finally {
		  close();	  
		}
}
}
