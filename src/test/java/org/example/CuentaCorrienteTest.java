package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuentaCorrienteTest {

    private CuentaCorriente cuentaCorriente;

    @BeforeEach
    void setUp(){
        cuentaCorriente = new CuentaCorriente("1111111A", "José García");
    }

    @Test
    void ingresarDineroCuentaACero() {
        double ingreso = 200;
        assertEquals(cuentaCorriente.getSaldo(), 0);

        cuentaCorriente.ingresarDinero(ingreso);
        assertEquals(cuentaCorriente.getSaldo(), ingreso);
    }

    @Test
    void ingresarDineroCuentaConAhorros() {
        double ahorro = 1000;
        double ingreso = 200;

        cuentaCorriente.setSaldo(ahorro);
        assertEquals(cuentaCorriente.getSaldo(), ahorro);

        cuentaCorriente.ingresarDinero(ingreso);
        assertEquals(cuentaCorriente.getSaldo(), ahorro + ingreso);
    }

    @Test
    void sacarDineroHaySaldo() {
        double ahorro = 1000;
        double extracto = 200;

        cuentaCorriente.setSaldo(ahorro);
        assertEquals(cuentaCorriente.getSaldo(), ahorro);

        boolean resultado = cuentaCorriente.sacarDinero(extracto);
        assertEquals(cuentaCorriente.getSaldo(), ahorro - extracto);
        assertTrue(resultado);
    }

    @Test
    void sacarDineroNoHaySaldo() {
        double saldoInicial = 100;
        double extracto = 200;

        cuentaCorriente.setSaldo(saldoInicial);
        assertEquals(cuentaCorriente.getSaldo(), saldoInicial);

        boolean resultado = cuentaCorriente.sacarDinero(extracto);
        assertFalse(resultado);
        assertEquals(saldoInicial, cuentaCorriente.getSaldo());
    }

    @Test
    void sacarTodo() {
        int dinero = 300;
        cuentaCorriente.setSaldo(dinero);
        assertEquals(dinero, cuentaCorriente.getSaldo());
        boolean sacado = cuentaCorriente.sacarDinero(dinero);
        assertEquals(0, cuentaCorriente.getSaldo());
        assertTrue(sacado);
    }

    @Test
    void sacarDineroSuperiorLimite() {
        int saldoInicial = 1000, sacar = 500; //limite 399
        cuentaCorriente.setSaldo(saldoInicial);
        boolean resultado = cuentaCorriente.sacarDinero(sacar);
        assertEquals(saldoInicial, cuentaCorriente.getSaldo());
        assertFalse(resultado);
    }

}