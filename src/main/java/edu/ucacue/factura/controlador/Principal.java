package edu.ucacue.factura.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.ucacue.factura.infraestructura.repositorio.PersonaRepository;
import edu.ucacue.factura.modelo.Persona;


@Controller
public class Principal {
	
	
	@Autowired
	PersonaRepository personaRepository;


	public Principal() {

		
	}

	public void insertarPersona()
	{
		
		try {
		Persona p1= new Persona(1, "Edison", "Paredez", "345345", "55345345");
		System.out.print("hola");
		personaRepository.save(p1);
		}catch (Exception e) {
			System.out.println("Existe un errror el momento de guardar la persona");
		
		}

		/*List<Persona> personas = personaRepository.findAll();
		
		for (Persona p : personas) {
		
			System.out.println(p.getNombre() + " " + p.getApellido());
			
		}*/
		

		Persona encontrado= personaRepository.findByCedula("55345345");
		System.out.println(encontrado);
		
		}
	}
