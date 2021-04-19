package exceptions;

public class GrupoNoEncontradoException extends Exception {
	public GrupoNoEncontradoException () {};
    
    public GrupoNoEncontradoException(String message) {
      super(message);
    }

}
