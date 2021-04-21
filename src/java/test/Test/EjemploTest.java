package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Properties;
import java.util.logging.Logger;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EjemploTest {
  
  private static final Logger LOG = Logger.getLogger(EjemploTest.class.getCanonicalName());
  
  private static final String UNIDAD_PERSITENCIA_PRUEBAS = "Proyectto-SII";
  private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
  private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
  
  private static EJBContainer ejbContainer;
  private static Context ctx;
  
  @BeforeClass
  public static void setUpClass() {
    Properties properties = new Properties();
    properties.setProperty(GLASSFISH_CONFIGI_FILE_PROPERTY, CONFIG_FILE);
    ejbContainer = EJBContainer.createEJBContainer(properties);
    ctx = ejbContainer.getContext();
  }
  
  @Before
  public void setup() throws NamingException  {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombreUnidadPersistencia);
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    //TODO: Crear base de batos con usando las importaciones
    em.getTransaction().commit();
  }

  @Test
  public void testComprobarImportaciones() {
    //TODO: Comprobar que las importaciones son correctas    
  }
  
  @Test
  public void testModificarAlumno() {
    //TODO: Comprobar que la clase modificar Alumno funciona correctamente    
  }
  
  @Test
  public void testInsertarLoteIngredientesIncorrectos() {

    try {
      final String productoSalchicha = "Salchicha";

      Lote lote = new Lote ("ST1", null, BigDecimal.TEN, Date.valueOf("2021-04-11"));
      lote.setLoteIngredientes(new HashMap<Ingrediente, String>());

      Producto salchicha = gestionProductos.obtenerProductoEIngredientes(productoSalchicha);
      lote.getLoteIngredientes().put(
          salchicha.getIngredientes().stream()
          .findAny().get(), 
          "");

      LOG.info("Test: "+lote.getLoteIngredientes().keySet());
      
      try {
        gestionLotes.insertarLote(productoSalchicha, lote);
        fail("Debe lanzar excepción");
      } catch (IngredientesIncorrectosException e) {
        // OK
      } catch (TrazabilidadException e) {
        fail("Debe lanzar excepción de ingredientes incorrectos");
      } 

    } catch (TrazabilidadException e) {
      throw new RuntimeException(e);
    }
  }
  
  @Test
  public void testInsertarLoteExistente() {

    try {
      final String nombreProducto = "Chorizo";

      Lote lote = new Lote ("LT1", null, BigDecimal.TEN, Date.valueOf("2021-04-11"));
      lote.setLoteIngredientes(new HashMap<Ingrediente, String>());

      Producto chorizo = gestionProductos.obtenerProductoEIngredientes(nombreProducto);
      lote.getLoteIngredientes().put(
          chorizo.getIngredientes().stream()
          .findAny().get(), 
          "");

      try {
        gestionLotes.insertarLote(nombreProducto, lote);
        fail("Debe lanzar excepción de lote existente");
      } catch (LoteExistenteException e) {
        // OK
      } catch (TrazabilidadException e) {
        fail("Debe lanzar excepción de lote existente");
      } 

    } catch (TrazabilidadException e) {
      throw new RuntimeException(e);
    }
  }
  
  @Test
  public void testObtenerLotes() {
    try {
      List<Lote> lotes = gestionLotes.obtenerLotesDeProducto("Chorizo");
      assertEquals(2, lotes.size());
    } catch (TrazabilidadException e) {
      fail("No debería lanzar excepción");
    }
  }
  
  @Test
  public void testObtenerLotesProductoNoEncontrado() {
    try {
      List<Lote> lotes = gestionLotes.obtenerLotesDeProducto("Arroz");
      fail("Debería lanzar excepción de producto no encontrado");
    } catch (ProductoNoEncontradoException e) {
      // OK
    } catch (TrazabilidadException e) {
      fail("Debería lanzar excepción de producto no encontrado");
    }
  }
  
  @Test
  public void testActualizarLote() {
    
    final String nombreProducto = "Chorizo";
    
    final long nuevaCantidad = 1234L;
    final String nuevaFecha = "2020-01-01";
    final String nuevoLoteCarne = "ABCD";
    final String nombreIngrediente = "Carne picada";
    String codigoLote1=null;
    
    try {
      
      List<Lote> lotes = gestionLotes.obtenerLotesDeProducto(nombreProducto);
      Lote lote1 = lotes.get(0);
      
      codigoLote1 = lote1.getCodigo(); 
      
      lote1.setCantidad(BigDecimal.valueOf(nuevaCantidad));
      lote1.setFechaFabricacion(Date.valueOf(nuevaFecha));
      Ingrediente carne = lote1.getLoteIngredientes().keySet().stream()
        .filter(ingrediente->{return ingrediente.getNombre().equals(nombreIngrediente);})
        .findAny().get();
      
      lote1.getLoteIngredientes().put(carne, nuevoLoteCarne);
      
      gestionLotes.actualizarLote(nombreProducto, lote1);

    } catch (TrazabilidadException e) {
      fail("Lanzó excepción al actualizar");
    }


    try {
      final String codigoLoteActualizado=codigoLote1;
      Lote loteActualizado = gestionLotes.obtenerLotesDeProducto(nombreProducto).stream()
          .filter(lote->{return lote.getCodigo().equals(codigoLoteActualizado);})
          .findAny().get();
      
      assertTrue(BigDecimal.valueOf(nuevaCantidad).compareTo(loteActualizado.getCantidad())==0);
      assertEquals(Date.valueOf(nuevaFecha), loteActualizado.getFechaFabricacion());
      Ingrediente carne = loteActualizado.getLoteIngredientes().keySet().stream()
          .filter(ingrediente->{return ingrediente.getNombre().equals(nombreIngrediente);})
          .findAny().get();
      
      assertEquals(nuevoLoteCarne, loteActualizado.getLoteIngredientes().get(carne));
    } catch (TrazabilidadException e) {
      fail("No debería lanzar excepción");
    }
  }
  
  @Test
  public void testActualizarLoteProductoNoEncontrado() {
    final String nombreProducto = "Chorizo";
    final String otroProducto = "Arroz";
    
    final long nuevaCantidad = 1234L;
    final String nuevaFecha = "2020-01-01";
    final String nuevoLoteCarne = "ABCD";
    final String nombreIngrediente = "Carne picada";
    
    try {
      
      List<Lote> lotes = gestionLotes.obtenerLotesDeProducto(nombreProducto);
      Lote lote1 = lotes.get(0);
      
      lote1.setCantidad(BigDecimal.valueOf(nuevaCantidad));
      lote1.setFechaFabricacion(Date.valueOf(nuevaFecha));
      Ingrediente carne = lote1.getLoteIngredientes().keySet().stream()
        .filter(ingrediente->{return ingrediente.getNombre().equals(nombreIngrediente);})
        .findAny().get();
      
      lote1.getLoteIngredientes().put(carne, nuevoLoteCarne);
      
      gestionLotes.actualizarLote(otroProducto, lote1);
      fail("Debería lanzar excepción de producto no encontrado");
    } catch (ProductoNoEncontradoException e) {
      // OK
    } catch (TrazabilidadException e) {
      fail("Debería lanzar excepción de producto no encontrado");
    }
  }
  
  @Test
  public void testActualizarLoteoNoEncontrado() {
    final String nombreProducto = "Chorizo";
    final String nuevoCodigoLote = "OtroCodigo";
    
    try {
      List<Lote> lotes = gestionLotes.obtenerLotesDeProducto(nombreProducto);
      Lote lote1 = lotes.get(0);
      lote1.setCodigo(nuevoCodigoLote);
      gestionLotes.actualizarLote(nombreProducto, lote1);
      fail("Debería lanzar excepción de lote no encontrado");
    } catch (LoteNoEncontradoException e) {
      // OK
    } catch (TrazabilidadException e) {
      fail("Debería lanzar excepción de lote no encontrado");
    }
  }
  
  @Test
  public void testActualizarLoteIngredientesIncorrectos() {
    final String nombreProducto = "Chorizo";
    final String nombreIngrediente = "Carne picada";
    
    try {
      
      List<Lote> lotes = gestionLotes.obtenerLotesDeProducto(nombreProducto);
      Lote lote1 = lotes.get(0);
      
      Ingrediente carne = lote1.getLoteIngredientes().keySet().stream()
        .filter(ingrediente->{return ingrediente.getNombre().equals(nombreIngrediente);})
        .findAny().get();
      
      lote1.getLoteIngredientes().remove(carne);
      
      gestionLotes.actualizarLote(nombreProducto, lote1);
      fail("Debería lanzar excepción de ingredientes incorrectos");
    } catch (IngredientesIncorrectosException e) {
      // OK
    } catch (TrazabilidadException e) {
      fail("Debería lanzar excepción de ingredientes incorrectos");
    } 
  }
  
  @Test
  public void testEliminarLote() {
    try {
      final String nombreProducto = "Chorizo";
      List<Lote>  lotes = gestionLotes.obtenerLotesDeProducto(nombreProducto);
      
      Lote lote1 = lotes.get(0);
      
      gestionLotes.eliminarLote(nombreProducto, lote1);
      
      lotes = gestionLotes.obtenerLotesDeProducto(nombreProducto);
      assertEquals(1, lotes.size());
      
    } catch (TrazabilidadException e) {
      fail("No debería lanzarse excepción");
    }
  }
  
  @Test
  public void testEliminarLoteProductoNoEncontrado() {
    try {
      final String nombreProducto = "Chorizo";
      final String otroProducto = "Arroz";
      
      List<Lote>  lotes = gestionLotes.obtenerLotesDeProducto(nombreProducto);
      
      Lote lote1 = lotes.get(0);

      gestionLotes.eliminarLote(otroProducto, lote1);
      fail("Debería lanzar la excepción de producto no encontrado");
    } catch (ProductoNoEncontradoException e) {
      // OK
    } catch (TrazabilidadException e) {
      fail("Debería lanzar la excepción de producto no encontrado");
    }
  }
  
  @Test
  public void testEliminarLoteNoEncontrado() {
    try {
      final String nombreProducto = "Chorizo";
      
      List<Lote>  lotes = gestionLotes.obtenerLotesDeProducto(nombreProducto);
      Lote lote1 = lotes.get(0);
      
      lote1.setCodigo("Otro");

      gestionLotes.eliminarLote(nombreProducto, lote1);
      fail("Debería lanzar la excepción de lote no encontrado");
    } catch (LoteNoEncontradoException e) {
      // OK
    } catch (TrazabilidadException e) {
      fail("Debería lanzar la excepción de lote no encontrado");
    }
  }
  
  @Test
  public void testEliminarTodosLotes() {
    try {
      final String nombreProducto = "Chorizo";
      gestionLotes.eliminarTodosLotes(nombreProducto);
      
      List<Lote> lotes = gestionLotes.obtenerLotesDeProducto(nombreProducto);
      assertEquals(0, lotes.size());
      
    } catch (TrazabilidadException e) {
      fail("No debería lanzarse excepción");
    }
  }
  
  @Test
  public void testEliminarTodosLotesProductoNoEncontrado() {
    try {
      final String nombreProducto = "Arroz";
      gestionLotes.eliminarTodosLotes(nombreProducto);
      
      fail("Debería lanzar la excepción de producto no encontrado");
    } catch (ProductoNoEncontradoException e) {
      // OK
    } catch(TrazabilidadException e) {
      fail("Debería lanzar la excepción de producto no encontrado");
    }
  }
  
  @AfterClass
  public static void tearDownClass() {
    if (ejbContainer != null) {
      ejbContainer.close();
    }
  }

}