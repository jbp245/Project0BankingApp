package services;

import java.util.List;

public interface Service<T> {

		// guarantees DAO implementations
		// will be extended by more custom service interfaces 
		
		// create
		boolean addT(T t);
		
		// read
		List<T> getAll();

		T getT(int i);
		
		T getT(T t);
		
		// update
		boolean updateT(T t);
		
		// delete
		boolean deleteT(T t);
		
		boolean deleteT(int id);
}
