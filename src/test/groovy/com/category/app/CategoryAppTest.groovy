package com.category.app

import com.category.app.exceptions.NoSuchCategoryException
import com.category.app.strategies.print.CategoryPrintStrategy
import spock.lang.Specification

class CategoryAppTest extends Specification {

    static final CONFIG_FILE = 'src/test/resources/config.yml'
    static final INPUT_FILE = 'src/test/resources/input.txt'

    CategoryApp app

    def setup() {
        app = new CategoryApp(CONFIG_FILE)
    }

    def 'should load data from file and group them by category'() {
        when:
            app.loadData INPUT_FILE
            def groupedData = app.getProcessedData()
            def categories = app.getAllCategories()
        then:
            groupedData.with {
                size() == categories.size()
                keySet().containsAll(categories)
                !get(categories.get(idx as int)).empty
            }
        where:
            idx << [0, 1]
    }

    def 'should add new category'() {
        when:
            app.addCategory 'test'
        then:
            app.allCategories.containsAll 'test'
    }

    def 'should remove category'() {
        given:
            def category = app.allCategories.get 0
        when:
            app.removeCategory category
        then:
            !app.getAllCategories().contains(category)
    }

    def 'should add a new print strategy for an existing category'() {
        given:
            app.addCategory 'test'
            def buffer = new ByteArrayOutputStream()
            System.out = new PrintStream(buffer)
            def expected = 'test\r\n'
        when:
            app.addPrintStrategy('test', new CategoryPrintStrategy() {
                @Override
                void printCategory(List<String> data) {
                    println 'test'
                }
            })
        and:
            app.printCategory'test'
        then:
            buffer.toString() == expected
    }

    def 'should throw NoSuchCategoryException when printing non-existent category'() {
        when:
            app.printCategory('foo')
        then:
            def exception = thrown(NoSuchCategoryException)
            exception.message == 'Category: foo does not exist'
    }
}
