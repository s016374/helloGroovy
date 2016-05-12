import groovy.sql.Sql

//@Grab('org.hibernate:hibernate-c3p0:4.3.10.Final')
//@groovy.lang.GrabConfig(systemClassLoader = true)
/**
 * Created by s016374 on 16/5/10.
 */

def url = 'jdbc:oracle:thin:@10.1.5.2:1521:clapp'
def user = 'eam'
def password = 'MfryGZ6T'
def driver = 'oracle.jdbc.driver.OracleDriver'

def sql = Sql.newInstance(url, user, password, driver) /*newInstance() 需要手动close(), 用withInstance() 自动close*/

//Create table
//sql.execute '''CREATE TABLE Groovy_tmp
//   (id NUMBER(*,0),
//    firstname VARCHAR2(64),
//    lastname VARCHAR2(64)
//   )
//'''


//Create rows
/*execute执行sql, executeInsert可以替换?占位符 相当于java的PreparedStatement*/
//def myKeyNames = ['ID'] /*用于接受结果*/
//
//sql.execute "INSERT INTO Groovy_tmp (id, firstname, lastname) VALUES (1, 'Dierk', 'Koenig')"
//
//def insertSql = 'INSERT INTO Groovy_tmp (id, firstname, lastname) VALUES (?, ?, ?)'
//def params = [11, 'Sam', 'Smith']
//def key1 = sql.executeInsert insertSql, params, myKeyNames
//println key1[0]
//
//def id = 22
//def first = 'Jack'
//def last = 'Button'
//def key2 = sql.executeInsert "INSERT INTO Groovy_tmp (id, firstname, lastname) VALUES (${id}, ${first}, ${last})", myKeyNames
//println key2[0]


//Read rows
//way1: query
sql.query('SELECT firstname, lastname FROM Groovy_tmp') { resultSet ->
    while (resultSet.next()) {
        def first = resultSet.getString(1)
        def last = resultSet.getString(2)
        println "$first $last"
    }
}

//way2: eachRow
sql.eachRow('SELECT firstname, lastname FROM Groovy_tmp') { row ->
    def first = row[0]
    def last = row[1]
    println "$first $last"
}

//way3: firstRow
def firstRow = sql.firstRow('SELECT firstname, lastname FROM Groovy_tmp')
println firstRow

//way4: rows
List rows = sql.rows('SELECT firstname, lastname FROM Groovy_tmp')
println rows


//updating rows
//way1: execute
sql.execute "INSERT INTO Groovy_tmp (lastname) VALUES ('Thorvaldsson')"
sql.execute "UPDATE Groovy_tmp SET firstname='Sam' where lastname='Thorvaldsson'"

//way2: sql.executeUpdate
def updateSql = "UPDATE Groovy_tmp SET lastname='Jack' where lastname='Thorvaldsson'"
result = sql.executeUpdate updateSql
println result


//Deleting rows
//way: execute
//sql.execute "DELETE FROM Groovy_tmp WHERE lastname = 'Smith'"
sql.close()

