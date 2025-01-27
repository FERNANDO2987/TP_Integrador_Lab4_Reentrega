package negocio;

import java.util.List;

import entidad.Prestamo;

public interface PrestamoNeg {
	public List<Prestamo> ListarPrestamos();
	public boolean RechazarPrestamo(int idPrestamo, String observacion);
	public boolean AprobarPrestamo(int idPrestamo, String observacion);
}
