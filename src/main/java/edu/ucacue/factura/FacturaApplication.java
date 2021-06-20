package edu.ucacue.factura;

import edu.ucacue.factura.controlador.Index;
import edu.ucacue.factura.controlador.factura.FacturaUI;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


//import edu.ucacue.factura.controlador.Principal;

@SpringBootApplication
public class FacturaApplication {

	public static void main(String[] args) {
		//SpringApplication.run(FacturaApplication.class, args);
		
		/*ConfigurableApplicationContext contexto = new SpringApplicationBuilder(FacturaApplication.class)
		.headless(false)		
		.web(WebApplicationType.NONE)
		.run(args);
		
		
		Principal p = contexto.getBean(Principal.class);
		p . GUI ();
		p.GUI (); */

		ConfigurableApplicationContext contexto = new SpringApplicationBuilder(FacturaApplication.class)
			    .headless(false)
			    .web(WebApplicationType.NONE)
			    .run(args);

				/*PrincipalGui principaGui= contexto.getBean(PrincipalGui.class);
				principaGui.setVisible(true);*/
				
				Index index= contexto.getBean(Index.class);
				index.setVisible(true);
	}

}
