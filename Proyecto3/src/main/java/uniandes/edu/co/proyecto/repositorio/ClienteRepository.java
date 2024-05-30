package uniandes.edu.co.proyecto.repositorio;

import uniandes.edu.co.proyecto.modelo.Cliente;
import uniandes.edu.co.proyecto.modelo.Cuenta;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Collection;
import java.util.List;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, Integer> {

    public class MaxIdResultCuenta {
        private int maxIdCuenta;
    
        public int getmaxIdCuenta() {
            return maxIdCuenta;
        }
    
        public void setmaxIdCuenta(int maxIdCuenta) {
            this.maxIdCuenta = maxIdCuenta;
        }
    }

    

    @Query("{}")
    Collection<Cliente> darClientes();

    @Query("{ '_id' : ?0 }")
    Cliente darClientePorNumeroDocumento(Integer numerodocumento);

    @Query(value = "{}", sort = "{_id : -1}", fields = "{_id : 1}")
    Collection<Cliente> findTop1ByOrderByNumerodocumentoDesc();

    @Aggregation(pipeline={
    "{ $unwind: '$cuentas' }", 
    "{$project: {id_cliente: '$_id', numcuenta: '$cuentas.numcuenta', tipocuenta: '$cuentas.tipocuenta', saldo: '$cuentas.saldo', estado: '$cuentas.estado', fechaultimatransaccion: '$cuentas.fechaultimatransaccion', fechacreacion: '$cuentas.fechacreacion', id_oficina: '$cuentas.id_oficina'}}"
    })
    List<Cuenta> obtenerTodasLasCuentas();

    @Aggregation(pipeline={
        "{ $unwind: '$cuentas' }", 
        "{$match: {'cuentas.numcuenta': ?0}}",
        "{$project: {id_cliente: '$_id', numcuenta: '$cuentas.numcuenta', tipocuenta: '$cuentas.tipocuenta', saldo: '$cuentas.saldo', estado: '$cuentas.estado', fechaultimatransaccion: '$cuentas.fechaultimatransaccion', fechacreacion: '$cuentas.fechacreacion', id_oficina: '$cuentas.id_oficina'}}"
    })
    Cuenta obtenerCuentaPorNumCuenta(int numCuenta);

    @Aggregation(pipeline={"{$unwind: '$cuentas'}",
        "{$match:{'cuentas.tipocuenta': ?0}}",
        "{$project: {id_cliente: '$_id', numcuenta: '$cuentas.numcuenta', tipocuenta: '$cuentas.tipocuenta', saldo: '$cuentas.saldo', estado: '$cuentas.estado', fechaultimatransaccion: '$cuentas.fechaultimatransaccion', fechacreacion: '$cuentas.fechacreacion', id_oficina: '$cuentas.id_oficina'}}"})
        List<Cuenta> darCuentasPorTipo(String tipoCuenta);

    @Aggregation(pipeline={"{$unwind: '$cuentas'}",
        "{$match:{'cuentas.saldo': {$gte: ?0, $lte: ?1}}}",
        "{$project: {id_cliente: '$_id', numcuenta: '$cuentas.numcuenta', tipocuenta: '$cuentas.tipocuenta', saldo: '$cuentas.saldo', estado: '$cuentas.estado', fechaultimatransaccion: '$cuentas.fechaultimatransaccion', fechacreacion: '$cuentas.fechacreacion', id_oficina: '$cuentas.id_oficina'}}"})
        List<Cuenta> darCuentasPorSaldos(int saldo1, int saldo2);

    @Aggregation(pipeline={"{$unwind: '$cuentas'}",
        "{$match:{'cuentas.fechacreacion': ?0}}",
        "{$project: {id_cliente: '$_id', numcuenta: '$cuentas.numcuenta', tipocuenta: '$cuentas.tipocuenta', saldo: '$cuentas.saldo', estado: '$cuentas.estado', fechaultimatransaccion: '$cuentas.fechaultimatransaccion', fechacreacion: '$cuentas.fechacreacion', id_oficina: '$cuentas.id_oficina'}}"})
        List<Cuenta> darCuentasPorFechaCreacion(Date fecha);

    @Aggregation(pipeline={"{$unwind: '$cuentas'}",
        "{$match:{'cuentas.fechaultimatransaccion': ?0}}",
        "{$project: {id_cliente: '$_id', numcuenta: '$cuentas.numcuenta', tipocuenta: '$cuentas.tipocuenta', saldo: '$cuentas.saldo', estado: '$cuentas.estado', fechaultimatransaccion: '$cuentas.fechaultimatransaccion', fechacreacion: '$cuentas.fechacreacion', id_oficina: '$cuentas.id_oficina'}}"})
        List<Cuenta> darCuentasPorFechaUltimaTransaccion(Date fecha);

    @Aggregation(pipeline={"{$unwind: '$cuentas'}",
        "{$match:{'cuentas.id_cliente': ?0}}",
        "{$project: {id_cliente: '$_id', numcuenta: '$cuentas.numcuenta', tipocuenta: '$cuentas.tipocuenta', saldo: '$cuentas.saldo', estado: '$cuentas.estado', fechaultimatransaccion: '$cuentas.fechaultimatransaccion', fechacreacion: '$cuentas.fechacreacion', id_oficina: '$cuentas.id_oficina'}}"})
        List<Cuenta> darCuentasCliente(int id);
    
    @Aggregation(pipeline={"{$unwind: '$cuentas'}",
    "{$match: {$and: [{'cuentas.tipocuenta': ?0}, {'cuentas.saldo': {$gte: ?1, $lte: ?2}}, {'cuentas.fechacreacion': ?3}, {'cuentas.fechaultimatransaccion': ?4}]}}",
    "{$project: {id_cliente: '$_id', numcuenta: '$cuentas.numcuenta', tipocuenta: '$cuentas.tipocuenta', saldo: '$cuentas.saldo', estado: '$cuentas.estado', fechaultimatransaccion: '$cuentas.fechaultimatransaccion', fechacreacion: '$cuentas.fechacreacion', id_oficina: '$cuentas.id_oficina'}}"})
    List<Cuenta> darCuentasPorFiltro(String tipoCuenta, int saldo1, int saldo2, Date fecha, Date fecha2, int idCliente);


    @Aggregation(pipeline = {
        "{$unwind: '$cuentas'}",
        "{$group: {_id: null, maxIdCuenta: {$max: '$cuentas.numcuenta'}}}"
    })
    List<MaxIdResultCuenta> findMaxIdCuenta();

    void deleteById(Integer numerodocumento);

    @Query(value = "{'cuentas.numcuenta': ?0}")
    Cliente findByNumCuenta(int numCuenta);

    @Query("{ '_id' : ?0 }")
    @Update("{$set: { 'tipodocumento': ?1, 'nombre': ?2, 'nacionalidad': ?3, 'direccionfisica': ?4, 'direccionelectronica': ?5, 'telefono': ?6, 'ciudad': ?7, 'departamento': ?8, 'codigopostal': ?9, 'password': ?10 }}")
    void actualizarCliente(Integer numerodocumento, String tipodocumento, String nombre, String nacionalidad, String direccionfisica, String direccionelectronica, String telefono, String ciudad, String departamento, Integer codigopostal, String password);
}
