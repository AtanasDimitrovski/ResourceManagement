package project.controllerInterface;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import project.model.BaseEntity;

public interface BaseEntityCrud<T extends BaseEntity> {

public T save(T entity);

	public List<T> save(Iterable<T> entities);
	
	public T saveAndFlush(T entity);

	public List<T> findAll();
	
	public Page<T> findAll(Pageable pageable);
	
	public List<T> findAll(Sort sort);
	
	public List<T> findAll(Iterable<Long> ids);
	
	public long count();
	
	public T findOne(Long id);
	
	public boolean exists(Long id);

	public void delete(Long id);
	
	public void delete(T entity);
	
	public void delete(Iterable<T> entities);
	
	public void deleteAll();

}
