package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.proyecto.modelo.Cuenta;

import java.time.LocalDate;
import java.util.Collection;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

    // Método para obtener todas las cuentas
    @Query(value = "SELECT * FROM cuentas", nativeQuery = true)
    Collection<Cuenta> darCuentas();
    
    @Query(value = "SELECT * FROM cuentas WHERE tipoCuenta = :tipoCuenta ", nativeQuery = true)
    Collection<Cuenta> darCuentasGerenteGeneralPorTipo(@Param("tipoCuenta") String tipoCuenta);

    @Query(value = "SELECT * FROM cuentas WHERE saldo BETWEEN :limite_inf_saldo and :limite_sup_saldo", nativeQuery = true)
    Collection<Cuenta> darCuentasGerenteGeneralPorSaldos(@Param("limite_inf_saldo") Integer limite_inf_saldo, @Param("limite_sup_saldo") Integer limite_sup_saldo);
    
    @Query(value = "SELECT * FROM cuentas WHERE fechacreacion = :limite_inf_fecha", nativeQuery = true)
    Collection<Cuenta> darCuentasGerenteGeneralPorFechaCreacion(@Param("limite_inf_fecha") String limite_inf_fecha);

    @Query(value = "SELECT * FROM cuentas WHERE fechaultimatransaccion = :limite_inf_fecha", nativeQuery = true)
    Collection<Cuenta> darCuentasGerenteGeneralPorFechaUltimaTransaccion(@Param("limite_inf_fecha") String limite_inf_fecha);

    @Query(value = "SELECT * FROM cuentas WHERE tipoCuenta = :tipoCuenta WHERE idoficina = :idoficina", nativeQuery = true)
    Collection<Cuenta> darCuentasGerenteOficinaPorTipo(@Param("tipoCuenta") String tipoCuenta, @Param("idoficina") Integer idoficina);

    @Query(value = "SELECT * FROM cuentas WHERE saldo BETWEEN :limite_inf_saldo and :limite_sup_saldo  WHERE idoficina = :idoficina", nativeQuery = true)
    Collection<Cuenta> darCuentasGerenteOficinaPorSaldos(@Param("limite_inf_saldo") Integer limite_inf_saldo, @Param("limite_sup_saldo") Integer limite_sup_saldo, @Param("idoficina") Integer idoficina);
    
    @Query(value = "SELECT * FROM cuentas WHERE fechacreacion BETWEEN :limite_inf_fecha and  :limite_sup_fecha  WHERE idoficina = :idoficina", nativeQuery = true)
    Collection<Cuenta> darCuentasGerenteOficinaPorFechaCreacion(@Param("limite_inf_fecha") String limite_inf_fecha, @Param("limite_sup_fecha") String limite_sup_fecha, @Param("idoficina") Integer idoficina);

    @Query(value = "SELECT * FROM cuentas WHERE fechacreacion BETWEEN :limite_inf_fecha and  :limite_sup_fecha  WHERE idoficina = :idoficina", nativeQuery = true)
    Collection<Cuenta> darCuentasGerenteOficinaPorFechaUltimaTransaccion(@Param("limite_inf_fecha") String limite_inf_fecha, @Param("limite_sup_fecha") String limite_sup_fecha, @Param("idoficina") Integer idoficina);

    @Query(value = "SELECT * FROM cuentas WHERE numdoccliente = :numDoc", nativeQuery = true)
    Collection<Cuenta> darCuentasCliente(@Param("numDoc") Integer numDoc);

    // Método para obtener una cuenta por su número
    @Query(value = "SELECT * FROM cuentas WHERE numCuenta = :numCuenta", nativeQuery = true)
    Cuenta darCuenta(@Param("numCuenta") Integer numCuenta);

    // Método para insertar una nueva cuenta
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cuentas (numCuenta, tipoCuenta, saldo, estado, fechaUltimaTransaccion, numDocCliente, fechaCreacion, idoficina) " +
            "VALUES (:numCuenta, :tipoCuenta, :saldo, :estado, :fechaUltimaTransaccion, :numDocCliente, :fechaCreacion, :idoficina)", nativeQuery = true)
    void insertarCuenta(@Param("numCuenta") Integer integer, @Param("tipoCuenta") String tipoCuenta,
                        @Param("saldo") Integer saldo, @Param("estado") String estado,
                        @Param("fechaUltimaTransaccion") LocalDate fechaUltimaTransaccion,
                        @Param("numDocCliente") Integer numDocCliente, @Param("fechaCreacion") LocalDate fechaCreacion, @Param("idoficina") Integer idoficina);

    // Método para actualizar una cuenta existente
    @Modifying
    @Transactional
    @Query(value = "UPDATE cuentas SET saldo = :saldo, fechaUltimaTransaccion = :fechaUltimaTransaccion " +
            "WHERE numCuenta = :numCuenta", nativeQuery = true)
    void actualizarSaldoCuenta(@Param("numCuenta") Integer numCuenta,
                          @Param("saldo") Integer saldo,
                          @Param("fechaUltimaTransaccion") LocalDate fechaUltimaTransaccion);

    @Modifying
    @Transactional
    @Query(value = "UPDATE cuentas SET estado = :estado " +
            "WHERE numCuenta = :numCuenta", nativeQuery = true)
    void actualizarEstadoCuenta(@Param("numCuenta") Integer numCuenta,
                          @Param("estado") String estado);


}
