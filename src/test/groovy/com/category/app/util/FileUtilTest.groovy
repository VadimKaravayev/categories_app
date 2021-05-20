package com.category.app.util

import spock.lang.Specification

class FileUtilTest extends Specification {

    def 'should read lines from file and put them into list'() {
        given:
            File file = File.createTempFile('test', '.txt')
            file.append('foo\n')
            file.append('bar')
        when:
            def list = FileUtil.readLinesFromFile(file.toString())
        then:
            list.size() == 2
            list.contains('foo')
            list.contains('bar')
        cleanup:
            file.delete()
    }
}
