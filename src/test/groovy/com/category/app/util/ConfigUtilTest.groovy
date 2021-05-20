package com.category.app.util

import spock.lang.Specification

class ConfigUtilTest extends Specification {

    def 'should read config file and return config bean'() {
        given:
            def filePath = 'src/test/resources/config.yml'
            def ANIMALS = 'animals'
            def NUMBERS = 'numbers'
        when:
            def bean = ConfigUtil.getConfig filePath
        then:
            bean.with {
                categories.with {
                    size() == 2
                    contains ANIMALS
                    contains NUMBERS
                }
                strategies.with {
                    size() == 2
                    containsKey ANIMALS
                    containsKey NUMBERS
                    get ANIMALS == 'com.category.app.strategies.print.impl.AnimalsPrintStrategy'
                    get NUMBERS == 'com.category.app.strategies.print.impl.NumbersPrintStrategy'
                }
            }
    }
}
