package project.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import project.controllerInterface.BaseEntityCrud;
import project.model.BaseEntity;

public abstract class BaseController<T extends BaseEntity, R extends JpaRepository<T, Long>> implements BaseEntityCrud<T> {
	
	
	protected abstract R getDao();

	@Override
	public T save(T entity) {
		return getDao().save(entity);
	}

	@Override
	public List<T> save(Iterable<T> entities) {
		return getDao().save(entities);
	}

	@Override
	public T saveAndFlush(T entity) {
		return getDao().saveAndFlush(entity);
	}

	@Override
	public List<T> findAll() {
		return getDao().findAll();
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return getDao().findAll(pageable);
	}

	@Override
	public List<T> findAll(Sort sort) {
		return getDao().findAll(sort);
	}

	@Override
	public List<T> findAll(Iterable<Long> ids) {
		return getDao().findAll(ids);
	}

	@Override
	public long count() {
		return getDao().count();
	}

	@Override
	public T findOne(Long id) {
		return getDao().findOne(id);
	}

	@Override
	public boolean exists(Long id) {
		return getDao().exists(id);
	}

	@Override
	public void delete(Long id) {
		getDao().delete(id);
	}

	@Override
	public void delete(T entity) {
		getDao().delete(entity);
	}

	@Override
	public void delete(Iterable<T> entities) {
		getDao().delete(entities);
	}

	@Override
	public void deleteAll() {
		getDao().deleteAll();
	}
	
	

}
