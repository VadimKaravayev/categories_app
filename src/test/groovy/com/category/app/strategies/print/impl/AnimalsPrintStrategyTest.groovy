package com.category.app.strategies.print.impl


import spock.lang.Specification

class AnimalsPrintStrategyTest extends Specification {

    def 'should print unique and a-z sorted elements'() {
        given:
            def strategy = new AnimalsPrintStrategy()
            def data = ['horse', 'dog', 'cow', 'dog']
            def buffer = new ByteArrayOutputStream()
            System.out = new PrintStream(buffer)
            def expected = 'cow\r\ndog\r\nhorse\r\n'
        when:
            strategy.printCategory data
        then:
            buffer.toString() == expected

    }
}
