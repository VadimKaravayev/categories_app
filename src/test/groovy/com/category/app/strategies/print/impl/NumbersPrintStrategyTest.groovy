package com.category.app.strategies.print.impl

import spock.lang.Specification

class NumbersPrintStrategyTest extends Specification {

    def 'should print unique elements followed by colon and number of occurrences'() {
        given:
            def strategy = new NumbersPrintStrategy()
            def data = ['one', 'three', 'two', 'one', 'three', 'six', 'six']
            def buffer = new ByteArrayOutputStream()
            System.out = new PrintStream(buffer)
            def expected = 'six: 2\r\none: 2\r\ntwo: 1\r\nthree: 2\r\n'
        when:
            strategy.printCategory data
        then:
            buffer.toString() == expected
    }
}
