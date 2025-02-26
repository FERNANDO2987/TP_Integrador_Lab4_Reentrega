package negocio;

import entidad.Transferencia;

public interface TransferenciaNeg {
	public boolean agregarTransferencia(Transferencia transferencia);
	public boolean validarCbuOrigen(Transferencia transferencia);
	public boolean validarCbuDestino(Transferencia transferencia);
	public boolean validarDineroOrigen(Transferencia transferencia);
	public boolean validarDetalle(Transferencia transferencia);
}
