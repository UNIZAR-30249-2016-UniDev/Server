package puertosyadaptadores;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import aplicacion.SimuladorEspacio;
import dominio.EspacioRepository;

/**
 * Encargado de lanzar los simuladores de espacios cada hora
 */
@WebListener
public class SimuladorHandler implements ServletContextListener {
	private Logger logger = Logger.getGlobal();

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("SimuladorHandler finished");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("SimuladorHandler started");

		Timer timer = new Timer();
		long hour = 1 * 1000 * 60 * 60;
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				logger.info("Simulacion");
				Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

				EspacioRepository repo = new EspacioRepositoryPostgre();
				SimuladorEspacio simulador = new SimuladorEspacio(repo);
				simulador.run();
			}

		}, 0, hour);
	}
}
