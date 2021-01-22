package daos;

import java.util.List;

public interface DAO<T> {

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
