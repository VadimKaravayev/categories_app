package com.category.app.util

import spock.lang.Specification

class ReflectUtilTest extends Specification {

    def 'should create AnimalsPrintStrategy instance from fully qualified name string'() {
        given:
            def className = 'com.category.app.strategies.print.impl.AnimalsPrintStrategy'
        when:
            def object = ReflectUtil.getObjectFromString className
        then:
            object.class.simpleName == 'AnimalsPrintStrategy'
    }

    def 'should create NumbersPrintStrategy instance from fully qualified name string'() {
        given:
            def className = 'com.category.app.strategies.print.impl.NumbersPrintStrategy'
        when:
            def object = ReflectUtil.getObjectFromString className
        then:
            object.class.simpleName == 'NumbersPrintStrategy'
    }

    def 'should create CarsPrintStrategy instance from fully qualified name string'() {
        given:
            def className = 'com.category.app.strategies.print.impl.CarsPrintStrategy'
        when:
            def object = ReflectUtil.getObjectFromString className
        then:
            object.class.simpleName == 'CarsPrintStrategy'
    }

    def 'should return null on wrong class name'() {
        expect:
            ReflectUtil.getObjectFromString('wrong.class.name') == null
    }
}
