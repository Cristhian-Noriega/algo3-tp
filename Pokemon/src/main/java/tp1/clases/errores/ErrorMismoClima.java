package tp1.clases.errores;

public class ErrorMismoClima extends Error{
        public ErrorMismoClima(String clima){
            super(String.format("El clima actual ya es %s", clima));
        }
}
