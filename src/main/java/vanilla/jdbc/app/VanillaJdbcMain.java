package vanilla.jdbc.app;
import java.util.List;

import vanilla.jdbc.db.VanillaJdbcBlobsDao;
import vanilla.jdbc.db.VanillaJdbcExampleDao;
import vanilla.jdbc.dm.Person;

public class VanillaJdbcMain {

	
	public static void main(String[] args) {
		
		VanillaJdbcExampleDao vanillaMe = new VanillaJdbcExampleDao();
		
		// ensure an empty database
		vanillaMe.deleteAll();
		
		vanillaMe.insert(new Person(19990, "fistZZZ1", "lastZZZ1", 66));
		vanillaMe.insert(new Person(19991, "fistZZZ2", "lastZZZ2", 66));
		vanillaMe.insert(new Person(19992, "fistZZZ3", "lastZZZ3", 66));
		vanillaMe.insert(new Person(19993, "fistZZZ4", "lastZZZ4", 66));
		vanillaMe.insert(new Person(19994, "fistZZZ5", "lastZZZ5", 66));
		
		List<Person> peopleList = vanillaMe.getPeopleUsingStatement();
		peopleList.forEach(o->System.out.println(o.toString()));
		
		System.out.println("\n");
		Person pupdate = peopleList.get(peopleList.size() - 1);
		pupdate.setFirstName("Namechanged");
		vanillaMe.update(pupdate);
		peopleList = vanillaMe.getPeopleUsingStatement();
		peopleList.forEach(o->System.out.println(o.toString()));
		
		System.out.println("\nNow deleting one item:");
		vanillaMe.delete(peopleList.get(peopleList.size() - 1));
		peopleList = vanillaMe.getPeopleUsingStatement();
		peopleList.forEach(o->System.out.println(o.toString()));
		
		Person person = vanillaMe.getPersonUsingPreparedStatement(19994);
		System.out.println("\nJust found person with id = " + 19994 + "==> " + person);
		
		System.out.println("\nNow manually insert someone cagey about their age = ");
		//vanillaMe.insert(new Person(2000, "Cagey", "Caldly", null));
		
		
		//-----------------------------------------------------------------------------------
		
		VanillaJdbcBlobsDao blobDao = new VanillaJdbcBlobsDao();
		blobDao.blogTest();
		
		//-----------------------------------------------------------------------------------
	}

	
	// TEST WASNULL BY manually entering a record in the db ====> WORKING FINE
//	public static void main(String[] args) {
//		
//		// delete from jdbc_employees
//		// insert into jdbc_employees values (2000, 'cagey', 'Joe', null);  ==> omitting age
//		// insert into jdbc_employees values (2000, 'cagey', 'Joe', 77);    ==> with an age
//		
//		VanillaJdbcExampleDao vanillaMe = new VanillaJdbcExampleDao();
//		
//		System.out.println("Expect people without age entered to have been reset to 100!");
//		List<Person> peopleList = vanillaMe.getPeopleUsingStatement();
//		peopleList.forEach(o->System.out.println(o.toString()));
//	}
	
	
	
}
