package com.category.app.strategies.print.impl

import spock.lang.Specification

class CarsPrintStrategyTest extends Specification {

    def 'should print unique elements in reverse order appended with hash code'() {
        given:
            def strategy = new CarsPrintStrategy()
            def data = ['AUDI', 'BMW', 'Audi', 'VW', 'OPEL', 'Opel']
            def buffer = new ByteArrayOutputStream()
            System.out = new PrintStream(buffer)
            def expected = 'vw(7336a2c49b0045fa1340bf899f785e70)\r\n' +
                    'opel(f65b7d39472c52142ea2f4ea5e115d59)\r\n' +
                    'bmw(71913f59e458e026d6609cdb5a7cc53d)\r\n' +
                    'audi(4d9fa555e7c23996e99f1fb0e286aea8)\r\n'
        when:
            strategy.printCategory data
        then:
            buffer.toString() == expected
    }
}
