import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper

/**
 * Created by s016374 on 16/5/9.
 */

class Person {
    String name
    int age
}

def jsonSlurper = new JsonSlurper()
def object = jsonSlurper.parseText('{ "name": "John Doe" } /* some comment */')
def object1 = jsonSlurper.parseText('{"myList":[1,2,3,4,5,6]}')
def object2 = jsonSlurper.parseText '''
    {
      "a":1,
      "b":2
      }'''
assert object instanceof Map
println(object.name)
assert object1.myList instanceof List
println(object1.myList)
assert object2.a.class == Integer
println(object2.b)

def json = JsonOutput.toJson([name: 'John Doe', age: 42])
println json
def json1 = JsonOutput.toJson([new Person(name: 'Sam', age: 10), new Person(name: 'Jack', age: 11)])
println JsonOutput.prettyPrint(json1)


JsonBuilder builder = new JsonBuilder()
builder.records {
    car {
        name 'HSV Maloo'
        make 'Holden'
        year 2006
        country 'Australia'
        record {
            type 'speed'
            description 'production pickup truck with speed of 271kph'
        }
    }
}
println JsonOutput.prettyPrint(builder.toString())