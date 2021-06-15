package edu.ucacue.factura.infraestructura.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ucacue.factura.modelo.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	
	//Producto  findByNombre ( String  nombre );


	
	
	List<Producto> findByNombreLike(String nombre);

	//List<Producto> findByProductoAndCodigo(String producto, String codigo);

	List<Producto> findByNombre(String nombre);
	


	// JPQL
	/*@Query("select p from Persona p where p.nombre =  :nombre")
	List<Producto> buscarPorNombres(@Param("nombre") String nombre);*/

	/*@Query("select p from Persona p where p.apellido = :apellido")
	List<Producto> buscarPorApellido(@Param("apellido") String apellido);*/

	/*@Query("select p from Persona p where p.apellido = :apellido and p.nombre = :nombre")
	List<Producto> buscarPorApellidoYnombre(@Param("apellido") String apellido, @Param("nombre") String nombre);*/
	
	/*@Query("select p from Persona p where p.nombre like :nombre")
	List<Producto> buscarPorNombreLike(@Param("nombre") String nombre);*/
	
	@Query("select p from Producto p where p.nombre like %:nombre%")
	List<Producto> buscarPorNombreLike(@Param("nombre") String nombre);


	
	




}
